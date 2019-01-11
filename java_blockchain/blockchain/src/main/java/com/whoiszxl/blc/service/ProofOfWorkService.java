package com.whoiszxl.blc.service;

import com.whoiszxl.blc.model.po.Block;

/**
 * 工作量證明服務
 * @author whoiszxl
 *
 */
public interface ProofOfWorkService {

	
	/**
	 * 計算工作量和hash
	 * @param block
	 * @return
	 */
	Block work(Block block);
	
}
