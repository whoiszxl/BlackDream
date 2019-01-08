package com.whoiszxl.blc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Block {

	/** 区块高度 */
	private Long height;
	
	/** 上一个区块的hash值  */
	private Byte prevBlockHash;
	
	/** 交易数据  */
	private Byte data;

	/** 时间戳 */
	private Long timestamp;
	
	/** hash值 */
	private Byte hash;
}
