package com.whoiszxl.blc.utils;

import java.nio.ByteBuffer;

import com.whoiszxl.blc.model.Block;

public class BlockUtils {
	
	
	/**
	 * 將整型轉為字節數組
	 * @param num
	 * @return
	 */
	public static byte[] int2ByteArray(Integer num) {
		if(num != null) {
			return ByteBuffer.allocate(8).putInt(num).array();
		}
		return null;
	}
	
	
	/**
	 * 設置區塊的hash值
	 * @param block
	 * @return
	 */
	public static Block setHash(Block block) {
		
		return null;
	}
}
