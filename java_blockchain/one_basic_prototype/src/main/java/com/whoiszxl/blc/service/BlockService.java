package com.whoiszxl.blc.service;

import com.whoiszxl.blc.common.ServerResponse;
import com.whoiszxl.blc.model.Block;

/**
 * 區塊相關服務接口
 * @author whoiszxl
 *
 */
public interface BlockService {

	ServerResponse<Block> createBlock(String data, Long height, byte[] prevBlockHash);
	
}
