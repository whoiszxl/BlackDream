package com.whoiszxl.blc.service;

import com.whoiszxl.blc.common.ServerResponse;
import com.whoiszxl.blc.model.po.Blockchain;

public interface BlockchainService {

	
	ServerResponse<Blockchain> CreateBlockchainWithGenesisBlock();
	
}
