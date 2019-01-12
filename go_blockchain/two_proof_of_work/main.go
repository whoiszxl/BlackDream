package main

import (
	"whoiszxl.com/BlackDream/go_blockchain/two_proof_of_work/blc"
	"fmt"
)


func main() {
	//data string,height int64,prevBlockHash []byte
	block := blc.NewBlock("Test",1,[]byte{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0})

	fmt.Println("--------------------")

	bytes := block.Serialize()

	fmt.Println(bytes)

	block = blc.DeserializeBlock(bytes)

	fmt.Printf("%d\n",block.Nonce)
	fmt.Printf("%x\n",block.Hash)
}