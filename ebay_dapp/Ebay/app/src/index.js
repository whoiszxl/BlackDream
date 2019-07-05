import "./index.css";

import Web3 from "web3";
import { default as contract } from 'truffle-contract'
import ecommerce_store_artifacts from '../../build/contracts/EcommerceStore.json';

var EcommerceStore = contract(ecommerce_store_artifacts);

const ipfsAPI = require('ipfs-api');
const ethUtil = require('ethereumjs-util');

const ipfs = ipfsAPI({host:'whoiszxl.com', port:'5001', protocol:'http'});

const App = {
  web3: null,
  account: null,
  meta: null,

  start: async function() {
    const { web3 } = this;
    EcommerceStore.setProvider(web3.currentProvider);
    renderStore();
  }
};

function renderStore() {
  console.log("开始渲染商品");
  EcommerceStore.deployed().then(function(i) {

    i.productIndex().then(function(index) {
      for (let j = 1; j <= index; j++) {
        i.getProduct.call(j).then(function(p) {
          $("#product-list").append(buildProduct(p));
        });
      }
    });
  });
}

function buildProduct(product) {
  let node = $("<div/>");
  node.addClass("col-sm-3 text-center col-margin-bottom-1");
  node.append("<img src='https://ipfs.io/ipfs/" + product[3] + "' width='150px' />");
  node.append("<div>" + product[1]+ "</div>");
  node.append("<div>" + product[2]+ "</div>");
  node.append("<div>" + product[5]+ "</div>");
  node.append("<div>" + product[6]+ "</div>");
  node.append("<div>Ether " + product[7] + "</div>");
  return node;
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
