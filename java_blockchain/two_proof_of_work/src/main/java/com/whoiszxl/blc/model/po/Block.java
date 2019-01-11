package com.whoiszxl.blc.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Block {

	/** 区块高度 */
	private Long height;
	
	/** 上一个区块的hash值  */
	private String prevBlockHash;
	
	/** 交易数据  */
	private String data;

	/** 时间戳 */
	private Long timestamp;
	
	/** hash值 */
	private String hash;
	
	/** 工作量校驗值 */
	private Long nonce;
}
