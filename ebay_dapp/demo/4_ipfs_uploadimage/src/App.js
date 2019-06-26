import React, {Component} from 'react'

const ipfsAPI = require('ipfs-api');
const ipfs = ipfsAPI({host:'118.126.92.128', port:'5001', protocol:'http'});

let saveImageOnIpfs = (reader) => {
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

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      imgSrc: null
    }
  }
  render() {
    return (<div className="App">
    <h2>图片上传到IPFS</h2>
    <div>
      <label id="file">Choose file to upload</label>
      <input type="file" ref="file" id="file" name="file" multiple="multiple"/>
    </div>
    <div>
    <button onClick={() => {
    var file = this.refs.file.files[0];
    var reader = new FileReader();
    // reader.readAsDataURL(file);
    reader.readAsArrayBuffer(file)
    reader.onloadend = (e) => {
      console.log(reader);
      //上传数据到ipfs
      saveImageOnIpfs(reader).then((hash) => {
        console.log("上传图片后的hash:" + hash);
        this.setState({imgSrc: hash});
      });
    }
    }}>Submit</button>
    </div>

    {
    this.state.imgSrc
    ? <div>
    <h2>{"http://118.126.92.128:8080/ipfs/" + this.state.imgSrc}</h2>
    <img alt="图片呢" style={{
    width: 1600
    }} src={"http://118.126.92.128:8080/ipfs/" + this.state.imgSrc}/>
    </div>
    : <img alt=""/>
    }
    </div>);
    }

  }
  export default App

