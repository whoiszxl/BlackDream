package main

import (
	"whoiszxl.com/BlackDream/go_blockchain/two_proof_of_work/blc"
)


func main() {
	// 创世区块
	blockchain := blc.CreateBlockchainWithGenesisBlock()
	defer blockchain.DB.Close()
}