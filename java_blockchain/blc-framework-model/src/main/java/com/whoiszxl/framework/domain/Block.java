package com.whoiszxl.framework.domain;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Builder
@AllArgsConstructor
public class Block implements Serializable{
    private static final long serialVersionUID = -8658098014589887530L;

    //区块高度
    private Long height;
    
    //上一个区块的hash值
    private String prevBlockHash;
    
    //交易数据
    private Object data;
    
    //时间戳
    private Long timestamp;
    
    //hash值
    private String hash;
    
    //nonce计算值
    private Long nonce;
}
