package main

import (
	"whoiszxl.com/BlackDream/go_blockchain/one_basic_prototype/blc"
	"fmt"
)


func main() {
	genesisBlockchain := blc.CreateBlockchainWithGenesisBlock()

	fmt.Println("-----------------")
	fmt.Println(genesisBlockchain)

	fmt.Println(genesisBlockchain.Blocks)
	fmt.Println(genesisBlockchain.Blocks[0])
}