package main

import (
	"whoiszxl.com/BlackDream/go_blockchain/one_basic_prototype/blc"
	"fmt"
)


func main() {
	block := blc.CreateGenesisBlock("生成创世区块")
	
	fmt.Print("打印生成的区块:")
	fmt.Println(block)
}