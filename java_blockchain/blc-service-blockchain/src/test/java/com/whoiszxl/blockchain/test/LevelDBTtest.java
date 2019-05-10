package com.whoiszxl.blockchain.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.whoiszxl.blockchain.BlockChainApplication;
import com.whoiszxl.blockchain.dao.LevelDBDao;
import com.whoiszxl.framework.domain.Block;

@RunWith(SpringRunner.class)   
@SpringBootTest(classes={BlockChainApplication.class})// 指定启动类
public class LevelDBTtest {

    @Autowired
    private LevelDBDao<Block> levelDBDao;
    
    @Test
    public void testSet() {
        levelDBDao.set("name1", "whoiszxl");
        levelDBDao.set("name2", "whoisphq");
        Block block = new Block(1000L, "pre", "first block", 1000L, "0xa1ad2b7721c57abc156cb760dcf441383b908531", 10000L);
        levelDBDao.addObject("o1", block);
    }
    
    @Test
    public void testGet() {
        String string1 = levelDBDao.get("name1"); 
        String string2 = levelDBDao.get("name2");
        Block block = levelDBDao.getObject("o1");
        System.out.println(string1);
        System.out.println(string2);
        System.out.println(block);
    }
}
