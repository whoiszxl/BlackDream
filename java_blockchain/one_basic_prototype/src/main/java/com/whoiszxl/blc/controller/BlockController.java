package com.whoiszxl.blc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whoiszxl.blc.common.ServerResponse;
import com.whoiszxl.blc.model.po.Block;
import com.whoiszxl.blc.model.po.Blockchain;
import com.whoiszxl.blc.service.BlockService;
import com.whoiszxl.blc.service.BlockchainService;
import com.whoiszxl.blc.service.impl.BlockServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/block")
@Api(tags = {"區塊接口集"}, value = "區塊相關接口")
public class BlockController {

	
	@Autowired
	private BlockService blockService;
	
	@Autowired
	private BlockchainService blockchainService;
	
	@GetMapping("/create")
	@ApiOperation(tags="創建一個創世區塊鏈", value = "創建一個創世區塊鏈", notes = "創建一個創世區塊鏈", consumes="application/x-www-form-urlencoded")
	//@ApiImplicitParam(name="hight",value="區塊高度",required=false,paramType="query")
	public ServerResponse<Blockchain> dreaming() {		
		ServerResponse<Blockchain> blockchains = blockchainService.CreateBlockchainWithGenesisBlock();
		return blockchains;
	}
	
	@GetMapping("/all")
	@ApiOperation(tags="查詢區塊鏈中的所有區塊", value = "查詢區塊鏈中的所有區塊", notes = "查詢區塊鏈中的所有區塊", consumes="application/x-www-form-urlencoded")
	public ServerResponse<Blockchain> selectAll() {
		return blockService.selectAllBlock();
	}
	
	@GetMapping("/add")
	@ApiOperation(tags="添加一個區塊到區塊鏈中", value = "添加一個區塊到區塊鏈中", notes = "添加一個區塊到區塊鏈中", consumes="application/x-www-form-urlencoded")
	public ServerResponse<Block> addBlock(@RequestParam("data")String data) {
		return blockchainService.addBlockToBlockchain(data);
	}
}
