package com.whoiszxl.blc.service;

import com.whoiszxl.blc.common.ServerResponse;
import com.whoiszxl.blc.model.po.Block;
import com.whoiszxl.blc.model.po.Blockchain;

public interface BlockchainService {


	/**
	 * 創建一個帶有創世區塊的區塊鏈
	 * @return
	 */
	ServerResponse<Blockchain> CreateBlockchainWithGenesisBlock();
	
	
	ServerResponse<Block> addBlockToBlockchain(String data);
}
