import React, { Component } from 'react';
import './App.css';

const ipfsAPI = require('ipfs-api');
const ipfs = ipfsAPI({host:'118.126.92.128', port:'5001', protocol:'http'});


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
              // 110x xxxx 10xx xxxx
              char2 = array[i++];
              out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
              break;
          case 14:
              // 1110 xxxx 10xx xxxx 10xx xxxx
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

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      strHash: null,
      strContent: null
    }
  }

  saveTextBlobOnIpfs = (blob) => {
    return new Promise(function(resolve, reject) {
      const descBuffer = Buffer.from(blob, 'utf-8');
      ipfs.add(descBuffer).then((response) => {
        console.log(response);
        resolve(response[0].hash);
      }).catch((err) => {
        console.log(err);
        reject(err);
      })
    })
  }


  render() {
    return (
      <div className="App">
        <input
        ref="ipfsContent"
        style={{width: 200,height: 40,borderWidth:2}}/>
        <button onClick={() => {
        let ipfsContent = this.refs.ipfsContent.value;
        console.log(ipfsContent);
        this.saveTextBlobOnIpfs(ipfsContent).then((hash) => {
          console.log(hash);
          this.setState({strHash: hash});
        });  

        }}>提交到IPFS</button>
        <p>{this.state.strHash}</p>
        <button onClick={() => {
          console.log('从IPFS中读取数据了');
          ipfs.cat(this.state.strHash).then((stream) => {
            console.log(stream);
            let strContent = Utf8ArrayToStr(stream);
            this.setState({strContent: strContent});
          });
        }}>读取数据</button>
        <h1>{this.state.strContent}</h1>
      </div>
    );
 }
}
export default App;