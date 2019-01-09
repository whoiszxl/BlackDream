package com.whoiszxl.blc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoiszxl.blc.model.Block;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/block")
@Api(tags = {"區塊接口集"}, value = "區塊相關接口")
public class BlockController {

	
	@GetMapping("/create")
	@ApiOperation(tags="創建一個區塊", value = "創建一個區塊", notes = "創建一個區塊", consumes="application/x-www-form-urlencoded")
	//@ApiImplicitParam(name="hight",value="區塊高度",required=false,paramType="query")
	public Block dreaming() {		
		
		return null;
	}
}
