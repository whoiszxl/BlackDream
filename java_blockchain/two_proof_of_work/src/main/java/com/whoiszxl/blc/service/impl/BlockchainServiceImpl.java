package com.whoiszxl.blc.service.impl;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoiszxl.blc.common.ServerResponse;
import com.whoiszxl.blc.model.po.Block;
import com.whoiszxl.blc.model.po.Blockchain;
import com.whoiszxl.blc.service.BlockService;
import com.whoiszxl.blc.service.BlockchainService;
import com.whoiszxl.blc.utils.BlockUtils;
import com.whoiszxl.blc.utils.CryptUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BlockchainServiceImpl implements BlockchainService {

	@Autowired
	private BlockService blockService;
	
	@Override
	public ServerResponse<Blockchain> CreateBlockchainWithGenesisBlock() {
		ServerResponse<Block> createGenesisBlock = blockService.createGenesisBlock("介系一个船新的创系区块");
		if(createGenesisBlock.isSuccess()) {
			LinkedList<Block> blocks = new LinkedList<>();
			blocks.add(createGenesisBlock.getData());
			return ServerResponse.createBySuccess(Blockchain.builder()
					.blocks(blocks)
					.build());
		}else {
			log.error("創建創世區塊鏈失敗咯");
			return ServerResponse.createByErrorMessage("創世區塊鏈創建失敗");
		}
	}

	@Override
	public ServerResponse<Block> addBlockToBlockchain(String data) {
		//先獲取到最後一個區塊
		Block lastBlock = blockService.getLastBlock();
		Block block = Block.builder()
				.height(lastBlock.getHeight()+1)
				.prevBlockHash(lastBlock.getHash())
				.data(data)
				.timestamp(System.currentTimeMillis())
				.hash(null)
				.build();
		block = BlockUtils.setHash(block);
		boolean addBlockFlag = blockService.addBlock(block);
		if(addBlockFlag) {
			return ServerResponse.createBySuccess(block);
		}
		return ServerResponse.createByError();
	}
}
