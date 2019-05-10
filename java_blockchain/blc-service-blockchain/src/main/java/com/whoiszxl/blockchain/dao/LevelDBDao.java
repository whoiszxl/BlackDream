package com.whoiszxl.blockchain.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteOptions;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LevelDBDao<T> {
    
    @Value("${leveldb.filepath}")
    private String filePath;
    private final Charset CHARSET = Charset.forName("UTF-8");
    
    /**
     * 设置key/value 字符串键值对
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        DBFactory factory = Iq80DBFactory.factory;
        Options options = new Options();
        DB db = null;
        try {
            db = factory.open(new File(filePath), options);
            // 会写入磁盘中
            db.put(bytes(key), bytes(value));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    /**
     * 获取key/value 字符串键值对
     * @param key
     * @return
     */
    public String get(String key) {
        DBFactory factory = Iq80DBFactory.factory;
        Options options = new Options();
        DB db = null;
        try {
            db = factory.open(new File(filePath), options);
            byte[] bs = db.get(bytes(key));
            return new String(bs, CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    
    public void addObject(String key, T t) {
        DBFactory factory = Iq80DBFactory.factory;
        Options options = new Options();
        DB db = null;
        try {
            db = factory.open(new File(filePath), options);
            WriteOptions writeOptions = new WriteOptions();
            writeOptions.snapshot(true);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            db.put(bytes(key), baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public T getObject(String key) {
        DBFactory factory = Iq80DBFactory.factory;
        Options options = new Options();
        DB db = null;
        
        try {
            db = factory.open(new File(filePath), options);
            byte[] valueByte = db.get(bytes(key));
            ByteArrayInputStream bais = new ByteArrayInputStream(valueByte);
            ObjectInputStream ois = new ObjectInputStream(bais);
            T result = (T) ois.readObject();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    } 
    
    
    
    public byte[] bytes(String str) {
        return str.getBytes(CHARSET);
    }
}
