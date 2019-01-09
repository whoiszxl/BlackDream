package com.whoiszxl.blc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoiszxl.blc.model.Block;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/d")
@Api(tags = {"dreaming測試接口集"}, value = "測試接口說明")
public class DreamingController {

	
	@GetMapping("/dreaming")
	@ApiOperation(tags="dreaming測試接口", value = "dreaming測試接口說明", notes = "dreaming提示內容", consumes="application/x-www-form-urlencoded")
	@ApiImplicitParam(name="hight",value="區塊高度",required=false,paramType="query")
	public Block dreaming(Long hight) {		
		return null;
	}
}
