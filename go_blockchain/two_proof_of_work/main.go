package main

import (
	"whoiszxl.com/BlackDream/go_blockchain/two_proof_of_work/blc"
	"fmt"
)


func main() {
	blockchain := blc.CreateBlockchainWithGenesisBlock()

	// 新区块
	blockchain.AddBlockToBlockchain("100 -> wangfei",blockchain.Blocks[len(blockchain.Blocks) - 1].Height + 1,blockchain.Blocks[len(blockchain.Blocks) - 1].Hash)
	blockchain.AddBlockToBlockchain("300 -> huixian",blockchain.Blocks[len(blockchain.Blocks) - 1].Height + 1,blockchain.Blocks[len(blockchain.Blocks) - 1].Hash)

	fmt.Println("-----------------------------")
	fmt.Println(blockchain)
	fmt.Println(blockchain.Blocks)
}