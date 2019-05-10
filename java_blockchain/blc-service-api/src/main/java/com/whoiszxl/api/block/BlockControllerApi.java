package com.whoiszxl.api.block;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Block操作接口", description = "block的相关操作")
public interface BlockControllerApi {


    @ApiOperation("测试用")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "str", value = "测试语", required = true, paramType = "query", dataType = "string") })
    public String test(String str);
    
    
    @ApiOperation("创建一个带创世区块的区块链")
    public String createBlockchainWithGenesisBlock();
}
