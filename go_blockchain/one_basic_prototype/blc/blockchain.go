package blc

type Blockchain struct {
	//存储有序区块
	Blocks []*Block
}

//1.创建带有创世区块的区块链
func CreateBlockchainWithGenesisBlock() *Blockchain {
	//创建创世区块
	genesisBlock := CreateGenesisBlock("介系一个船新的创系区块")
	//返回链对象
	return &Blockchain{[]*Block{genesisBlock}}
}