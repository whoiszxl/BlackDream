package com.whoiszxl.blc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoiszxl.blc.model.Block;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/d")
@Api(tags = {"dreaming測試接口集"}, value = "測試接口說明")
public class DreamingController {

	
	@ApiOperation(tags="dreaming測試接口", value = "dreaming測試接口說明", notes = "dreaming提示內容", consumes="application/x-www-form-urlencoded")
	@GetMapping("/dreaming")
	public Block dreaming(@ApiParam(name="hight",value="區塊高度",required=false) Long hight) {		
		return new Block(hight, new Byte("000000000000000000000000000"), new Byte("000000000000000000000000000"), System.currentTimeMillis(), new Byte("000000000000000000000000000"));
	}
}
