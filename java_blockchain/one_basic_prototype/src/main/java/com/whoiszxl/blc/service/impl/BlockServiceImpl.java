package com.whoiszxl.blc.service.impl;

import org.springframework.stereotype.Service;

import com.whoiszxl.blc.common.ServerResponse;
import com.whoiszxl.blc.model.Block;
import com.whoiszxl.blc.service.BlockService;
import com.whoiszxl.blc.utils.BlockUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BlockServiceImpl implements BlockService {
	
	@Override
	public ServerResponse<Block> createBlock(String data, Long height, byte[] prevBlockHash) {
		Block block = Block.builder()
		.height(height)
		.prevBlockHash(prevBlockHash)
		.data(new byte[]{})
		.timestamp(System.currentTimeMillis())
		.hash(null)
		.build();
		Block finalBlock = BlockUtils.setHash(block);
		log.info("設置hash值后的區塊結構：{}", finalBlock);
		return ServerResponse.createBySuccess(finalBlock);
	}

}
