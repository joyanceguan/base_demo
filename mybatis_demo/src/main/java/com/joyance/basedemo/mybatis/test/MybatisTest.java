package com.joyance.basedemo.mybatis.test;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.joyance.basedemo.mybatis.mapper.ConfigMapper;
import com.joyance.basedemo.mybatis.parser.Parser;
import com.joyance.basedemo.mybatis.persistence.Config;
import com.joyance.basedemo.mybatis.proxy.MapperProxy;

public class MybatisTest {

	public static void main(String[] args) {
		//mapper代理
		MapperProxy proxys = Parser.getMapperProxy();
    	ConfigMapper configMapper =proxys.getMapper(ConfigMapper.class);
    	//查询
    	Config result = configMapper.selectById(14);
    	System.out.println(JSON.toJSONString(result,true));
    	
//    	List<Config> list =configMapper.queryAll(1996000);
//    	System.out.println(JSON.toJSONString(list,true));
    	
    	//新增
//    	Config config = new Config();
//    	config.setC_desc("test_mybaits_desc");
//    	config.setC_key("test_mybaits_key");
//    	config.setC_value("test_mybaits_value");
//    	config.setIndex_key(1L);
//    	config.setStatus(1);
//    	int flag = configMapper.save(config);
//    	System.out.println(flag==1?"保存成功":"保存失败");
    	//修改
//    	config.setC_value("test_mybaits_key_111");
//    	config.setC_desc("test_mybaits_desc_111");
//    	config.setId(1996016);
//    	int flag = configMapper.update(config);
//    	System.out.println(flag==1?"保存成功":"保存失败");
    
//    	int flag = configMapper.deleteById(1996016);
//    	System.out.println(flag==1?"删除成功":"删除失败");
	}
}
