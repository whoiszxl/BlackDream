package com.whoiszxl.blc.utils;

import java.nio.ByteBuffer;
import com.whoiszxl.blc.model.po.Block;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockUtils {
	
	
	/**
	 * 將Long轉為字節數組
	 * @param num
	 * @return
	 */
	public static byte[] int2ByteArray(Long num) {
		if(num != null) {
			return ByteBuffer.allocate(8).putLong(num).array();
		}
		return null;
	}
	
	
	/**
	 * 設置區塊的hash值
	 * @param block
	 * @return
	 */
	public static Block setHash(Block block) {
		//1. 轉換高度為字節數組
		byte[] heightBytes = int2ByteArray(block.getHeight());
		log.info("數組高度轉換字節后：{}", heightBytes);

		//時間戳轉二進制字符串再轉字節數組
		String binaryString = Long.toBinaryString(block.getTimestamp());
		byte[] timestampBytes = binaryString.getBytes();
		log.info("時間戳轉換字節后：{}", timestampBytes);
		
		//拼接屬性
		byte[] blockBytes = CryptUtils.concatAll(heightBytes, block.getPrevBlockHash(), block.getData(), timestampBytes);
		log.info("組合后的區塊字節數組：{}", blockBytes);
		
		//sha256加密
		byte[] blockHash = CryptUtils.string2SHA256(blockBytes.toString());
		block.setHash(blockHash);
		return block;
	}
}
