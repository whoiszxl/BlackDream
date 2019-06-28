pragma solidity ^0.5.8;

contract EcommerceStore {
    //产品状态：开放,已售，未售
    enum ProductStatus {Open, Sold, UnSold}
    //产品条件: 新的，旧的
    enum ProductCondition {New, Used}

    //统计有多少人提交，每一次提交+1
    uint public productIndex;

    //卖家钱包地址 => (商品ID => 商品结构体)
    mapping(address => mapping(uint => Product)) stores;
    //存储卖家的钱包地址
    mapping(uint => address) productIdInStore;
    
    address public defaultHighestBidder;


    struct Bid {
        address bidder; //投标人钱包地址
        uint productId; //投标的商品ID
        uint value; //fake值
        bool revealed; //是否已经公告
    }

    struct Product {
        uint id; //商品ID
        string name; //商品名称
        string category; //商品分类
        string imageLink; //商品图片Hash
        string descLink; //详情Hash
        uint auctionStartTime; //拍卖开始时间
        uint auctionEndTime; //拍卖结束时间
        uint startPrice; //起拍价
        address highestBidder; //出价最高的投标人
        uint highestBid; //最高出价
        uint secondHighestBid; //第二高出价
        uint totalBids; //总报价
        ProductStatus status; //商品状态
        ProductCondition condition; //商品条件
        mapping(address => mapping(bytes32 => Bid)) bids;
    }

    //构造函数，初始化参数
    // constructor() public {
    //     productIndex = 0;
    // }

    /* 添加商品到区块链中 */
    function addProductToStore(string memory _name, string memory _category, string memory _imageLink, string memory _descLink, uint _auctionStartTime, uint _auctionEndTime, uint _startPrice, uint _productCondition) public {
        require(_auctionStartTime < _auctionEndTime);
        productIndex += 1;
        Product memory product = Product(productIndex, _name, _category, _imageLink, _descLink, _auctionStartTime, _auctionEndTime, _startPrice, defaultHighestBidder, 0, 0, 0, ProductStatus.Open, ProductCondition(_productCondition));
        stores[msg.sender][productIndex] = product;
        productIdInStore[productIndex] = msg.sender;
    }

    /* 通过商品ID读取商品信息 */
    function getProduct(uint _productId)public view returns(uint, string memory, string memory, string memory, string memory, uint, uint, uint, ProductStatus, ProductCondition) {
        Product memory product = stores[productIdInStore[_productId]][_productId];
        return (product.id, product.name, product.category, product.imageLink, product.descLink, product.auctionStartTime, product.auctionEndTime, product.startPrice, product.status, product.condition);
    }

    /** 投标 */
    function bid(uint _productId, bytes32 _bid) payable public returns(bool) {
        Product storage product = stores[productIdInStore[_productId]][_productId];
        //有效性校验
        require(now >= product.auctionStartTime);
        require(now <= product.auctionEndTime);
        require(msg.value > product.startPrice);
        require(product.bids[msg.sender][_bid].bidder == 0);
        product.bids[msg.sender][_bid] = Bid(msg.sender, _productId, msg.value, false);
        product.totalBids += 1;
        return true;
    }


    /** 公告 */
    function revealBid(uint _productId, string _amount, string _secret) public {
        //通过传入的商品ID获取到商品详细信息
        Product storage product = stores[productIdInStore[_productId]][_productId];
        require(now > product.auctionEndTime);
        //获取到加密后的key
        bytes32 sealedBid = keccak256(_amount, _secret);

        //通过加密key到商品的bids中拿到投标人的信息,并且需要地址存在，
        Bid memory bidInfo = product.bids[msg.sender][sealedBid];
        require(bidInfo.bidder > 0);
        require(bidInfo.revealed == false);

        uint refund; //退款
        uint amount = stringToUint(_amount);

        //如果投标人的fake报价小于了实际报价，那退款就是fake报价
        if (bidInfo.value < amount) {
            refund = bidInfo.value;
        }else {
            //如果没有最高出价的投标人,那就把当前调用合约的用户设置为最高价投标人，并设置最高出价和第二高的出价，并将fake报价减去实际报价，得到退款的差价
            if (product.highestBidder == 0) {
                product.highestBidder = msg.sender;
                product.highestBid = amount;
                product.secondHighestBid = product.startPrice;
                refund = bidInfo.value - amount;
            }else {
                //存在最高出价的投标人，判断当前的出价是否大于最高出价
                if (amount > product.highestBid) {
                    //当前出价大于最高出价，最高出价顺延为第二高
                    product.secondHighestBid = product.highestBid;
                    //当前用户要给最高出价的人出价的本金
                    product.highestBidder.transfer(product.highestBid);
                    //将最高出价人和最高出价金额设置为当前用户的值
                    product.highestBidder = msg.sender;
                    product.highestBid = amount;
                    refund = bidInfo.value - amount;
                }else if (amount > product.secondHighestBid) {
                    //如果当前出价仅大于第二高出价，那仅将本次出价设置为第二高出价并退回此次出价的金额
                    product.secondHighestBid = amount;
                    refund = amount;
                }else {
                    //退钱咯
                    refund = amount;
                }
            }

            //如果退款金额大于0，给自己转这个refund，并将公告状态设置为已公告
            if (refund > 0) {
                msg.sender.transfer(refund);
                product.bids[msg.sender][sealedBid].revealed = true;
            }
        }
    }


    /** 字符串转uint */
    function stringToUint(string s) pure private returns (uint) {
        bytes memory b = bytes(s);
        uint result = 0;
        for (uint i = 0; i < b.length; i++) {
            if (b[i] >= 48 && b[i] <= 57) {
                result = result * 10 + (uint(b[i]) - 48);
            }
        }
        return result; 
    }
}