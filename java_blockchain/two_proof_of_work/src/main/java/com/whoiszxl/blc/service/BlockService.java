package com.whoiszxl.blc.service;

import com.whoiszxl.blc.common.ServerResponse;
import com.whoiszxl.blc.model.po.Block;
import com.whoiszxl.blc.model.po.Blockchain;

/**
 * 區塊相關服務接口
 * @author whoiszxl
 *
 */
public interface BlockService {

	/**
	 * 創建一個區塊
	 * @param data
	 * @param height
	 * @param prevBlockHash
	 * @return
	 */
	ServerResponse<Block> createBlock(String data, Long height, String prevBlockHash);
	
	/**
	 * 創建一個創世區塊
	 * @param data
	 * @param height
	 * @param prevBlockHash
	 * @return
	 */
	ServerResponse<Block> createGenesisBlock(String data);
	
	/**
	 * 查詢鏈中所有區塊
	 * @return
	 */
	ServerResponse<Blockchain> selectAllBlock();
	
	/**
	 * 獲取鏈中最後一個區塊
	 * @return
	 */
	Block getLastBlock();
	
	/**
	 * 添加一個區塊到鏈中
	 * @param block
	 * @return
	 */
	boolean addBlock(Block block);
}
