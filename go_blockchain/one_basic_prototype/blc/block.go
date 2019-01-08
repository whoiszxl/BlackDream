package blc

import (
	"time"
	"strconv"
	"fmt"
	"bytes"
	"crypto/sha256"
)

//区块结构体
type Block struct {
	//1.区块高度
	Height int64
	//2.上一个区块的hash值
	PrevBlockHash []byte
	//3.交易数据
	Data []byte
	//4.时间戳
	Timestamp int64
	//5.hash值
	Hash []byte
}

//给区块设置hash值
func (block *Block) SetHash() {
	//1.将区块高度转字节
	fmt.Println("【设置区块hash值】区块高度字符串:" + strconv.FormatInt(block.Height, 10))
	heightBytes := IntToHex(block.Height)
	fmt.Print("【设置区块hash值】区块高度字节码:")
	fmt.Println(heightBytes)
	
	//2.将时间戳转二进制
	timeString := strconv.FormatInt(block.Timestamp, 2)
	fmt.Println("【设置区块hash值】区块时间字符串:" + timeString)
	timeBytes := []byte(timeString)
	fmt.Println("【设置区块hash值】区块时间字节数组:" + string(timeBytes))	

	//3.拼接所有属性
	blockBytes := bytes.Join([][]byte{heightBytes, block.PrevBlockHash, block.Data, timeBytes}, []byte{})

	//4.生成hash值
	hash := sha256.Sum256(blockBytes)
	block.Hash = hash[:]
}


//创建新的区块
func NewBlock(data string, height int64, prevBlockHash []byte) *Block {

	block := &Block{height, prevBlockHash, []byte(data), time.Now().Unix(), nil}
	block.SetHash()

	return block
}