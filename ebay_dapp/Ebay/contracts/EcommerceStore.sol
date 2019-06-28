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
    }

    //构造函数，初始化参数
    constructor() public {
        productIndex = 0;
    }

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

}