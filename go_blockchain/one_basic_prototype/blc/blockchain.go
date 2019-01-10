package blc

type Blockchain struct {
	//存储有序区块
	Blocks []*Block
}

// 增加区块到区块链里面
func (blc *Blockchain) AddBlockToBlockchain(data string,height int64,preHash []byte) {
	//创建新的区块
	newBlock := NewBlock(data, height, preHash)
	//往链中添加区块
	blc.Blocks = append(blc.Blocks,newBlock)
}

//1.创建带有创世区块的区块链
func CreateBlockchainWithGenesisBlock() *Blockchain {
	//创建创世区块
	genesisBlock := CreateGenesisBlock("介系一个船新的创系区块")
	//返回链对象
	return &Blockchain{[]*Block{genesisBlock}}
}