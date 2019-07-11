import "./index.css";

import Web3 from "web3";
import { default as contract } from 'truffle-contract'
import ecommerce_store_artifacts from '../../build/contracts/EcommerceStore.json';

var EcommerceStore = contract(ecommerce_store_artifacts);

const ipfsAPI = require('ipfs-api');
const ethUtil = require('ethereumjs-util');

const ipfs = ipfsAPI({host:'localhost', port:'5001', protocol:'http'});



const App = {
  web3: null,
  account: null,
  meta: null,

  start: async function() {
    const { web3 } = this;
    EcommerceStore.setProvider(web3.currentProvider);
    renderStore();

    var reader;

    web3.eth.getAccounts().then(function(a) {
      console.log(a[0]);
    });

    $("#product-image").change(function(event) {
      const file = event.target.files[0]
      reader = new window.FileReader()
      reader.readAsArrayBuffer(file)
    });

    $("#add-item-to-store").submit(function(event) {
      console.log("开始添加商品了");
      const req = $("#add-item-to-store").serialize();
      let params = JSON.parse('{"' + req.replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
      let decodedParams = {}
      Object.keys(params).forEach(function(v) {
        decodedParams[v] = decodeURIComponent(decodeURI(params[v]));
      });
      saveProduct(reader, decodedParams);
      event.preventDefault();
    });

  }
};

function renderStore() {
  console.log("开始渲染商品");
  EcommerceStore.deployed().then(function(i) {

    i.productIndex().then(function(index) {
      for (let j = 1; j <= index; j++) {
        i.getProduct.call(j).then(function(p) {
          $("#product-list").append(buildProduct(p, j));
        });
      }
    });
  });
}





window.App = App;

window.addEventListener("load", function() {
  if (window.ethereum) {
    // use MetaMask's provider
    App.web3 = new Web3(window.ethereum);
    window.ethereum.enable(); // get permission to access accounts
  } else {
    console.warn(
      "No web3 detected. Falling back to http://127.0.0.1:9545. You should remove this fallback when you deploy live",
    );
    // fallback - use your fallback strategy (local node / hosted node + in-dapp id mgmt / fail)
    App.web3 = new Web3(
      new Web3.providers.HttpProvider("http://127.0.0.1:9545"),
    );
  }

  App.start();
});







function buildProduct(product, id) {
  let node = $("<div/>");
  node.addClass("col-sm-3 text-center col-margin-bottom-1");
  node.append("<img src='https://ipfs.io/ipfs/" + product[3] + "' width='150px' />");
  node.append("<div>" + product[1] + "</div>");
  node.append("<div>" + product[2] + "</div>");
  node.append("<div>" + new Date(product[5] * 1000) + "</div>");
  node.append("<div>" + new Date(product[6] * 1000) + "</div>");
  node.append("<div>Ether " + product[7] + "</div>");
  node.append("<a href='product.html?Id=" + id + "'class='btn btn-primary'>Show</a>")
  return node;
};







function saveProduct(reader, decodedParams) {
  let imageId, descId;
  saveImageToIpfs(reader).then(function(id) {
    console.log("添加图片");
    imageId = id;
    saveTextBlobToIpfs(decodedParams["product-description"]).then(function(id) {
      descId = id;
      saveProductToBlockChain(decodedParams, imageId, descId);
    })
  });
}


function saveProductToBlockChain(params, imageId, descId) {
  console.log(params);
  let auctionStartTime = Date.parse(params["product-auction-start"]) / 1000;
  let auctionEndTime = auctionStartTime + parseInt(params["product-auction-end"]) * 24 * 60 * 60

  web3.eth.getAccounts(function(err, result) {
    console.log("resulr" + result);
  });

  EcommerceStore.deployed().then(function(i) {
    web3.eth.getAccounts(function(err, payAddress) {
      i.addProductToStore(params["product-name"], params["product-category"], imageId, descId, auctionStartTime,
      auctionEndTime, web3.toWei(params["product-price"], 'ether'), parseInt(params["product-condition"]), 
      {
        from: payAddress[0],
        gas: 623164
      }).then(function(f) {
        console.log(f);
        $("#msg").show();
        $("#msg").html("Your product was successfully added to your store!");
      });
    });
    
  });

}



function saveImageToIpfs(reader) {
  return new Promise(function(resolve, reject) {
    const buffer = Buffer.from(reader.result);
    ipfs.add(buffer).then((response) => {
      console.log(response);
      resolve(response[0].hash);
    }).catch((err) => {
      console.log(err);
      reject(err);
    });
  });
} 


function saveTextBlobToIpfs(blob) {
  return new Promise(function(resolve, reject) {
    const descBuffer = Buffer.from(blob, 'utf-8');
    ipfs.add(descBuffer).then((response) => {
      console.log(response);
      resolve(response[0].hash);
    }).catch((err) => {
      console.log(err);
      reject(err);
    });
  });
}



/** 工具类 */

function displayEndHours(seconds) {
  let current_time = getCurrentTimeInSeconds()
  let remaining_seconds = seconds - current_time;

  if (remaining_seconds <= 0) {
    return "Auction has ended";
  }

  let days = Math.trunc(remaining_seconds / (24 * 60 * 60));

  remaining_seconds -= days * 24 * 60 * 60
  let hours = Math.trunc(remaining_seconds / (60 * 60));

  remaining_seconds -= hours * 60 * 60

  let minutes = Math.trunc(remaining_seconds / 60);

  if (days > 0) {
    return "Auction ends in " + days + " days, " + hours + ", hours, " + minutes + " minutes";
  } else if (hours > 0) {
    return "Auction ends in " + hours + " hours, " + minutes + " minutes ";
  } else if (minutes > 0) {
    return "Auction ends in " + minutes + " minutes ";
  } else {
    return "Auction ends in " + remaining_seconds + " seconds";
  }
}


function getCurrentTimeInSeconds() {
  return Math.round(new Date() / 1000);
}

function displayPrice(amt) {
  return 'Ξ' + web3.fromWei(amt, 'ether');
}

function Utf8ArrayToStr(array) {
  var out, i, len, c;
  var char2, char3;

  out = "";
  len = array.length;
  i = 0;
  while (i < len) {
    c = array[i++];
    switch (c >> 4) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
        // 0xxxxxxx
        out += String.fromCharCode(c);
        break;
      case 12:
      case 13:
        // 110x xxxx   10xx xxxx
        char2 = array[i++];
        out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
        break;
      case 14:
        // 1110 xxxx  10xx xxxx  10xx xxxx
        char2 = array[i++];
        char3 = array[i++];
        out += String.fromCharCode(((c & 0x0F) << 12) |
          ((char2 & 0x3F) << 6) |
          ((char3 & 0x3F) << 0));
        break;
      default:
        break;
    }
  }

  return out;
}