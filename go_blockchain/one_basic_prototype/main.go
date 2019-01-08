package main

import (
	"whoiszxl.com/BlackDream/go_blockchain/one_basic_prototype/blc"
	"fmt"
)


func main() {
	block := blc.NewBlock("生成区块", 1, []byte{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0})

	fmt.Print("打印生成的区块:")
	fmt.Println(block)
}