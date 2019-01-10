package com.whoiszxl.blc.model.po;

import java.util.LinkedList;

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
public class Blockchain {

	private LinkedList<Block> blocks;
}
