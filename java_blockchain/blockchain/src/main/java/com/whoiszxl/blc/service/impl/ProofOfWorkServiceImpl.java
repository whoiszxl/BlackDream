package com.whoiszxl.blc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.whoiszxl.blc.model.po.Block;
import com.whoiszxl.blc.service.ProofOfWorkService;
import com.whoiszxl.blc.utils.BlockUtils;
import com.whoiszxl.blc.utils.CryptUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProofOfWorkServiceImpl implements ProofOfWorkService {

	@Value("${const.targetBit}")
	private Long targetBit;
	
	@Override
	public Block work(Block block) {
		long nonce = 0;
		
		while(true) {
			byte[] mergeBlockParam = BlockUtils.mergeBlockParam(block, nonce, targetBit);
			String blockStr = mergeBlockParam.toString();
			String blockHash = CryptUtils.string2SHA256(blockStr);
			log.info("【工作計算塊hash】 {}", blockHash);
			
			if(blockHash.startsWith("0000")) {
				block.setHash(blockHash);
				block.setNonce(nonce);
				break;
			}
			
			nonce++;
		}
		return block;
	}

}
