package com.whoiszxl.blockchain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoiszxl.api.block.BlockControllerApi;

@RestController
@RequestMapping("/block")
public class BlockController implements BlockControllerApi{

    @Override
    @GetMapping("/test")
    public String test(String str) {
        return "hello world " + str;
    }

    @Override
    public String createBlockchainWithGenesisBlock() {
        
        return null;
    }

}
