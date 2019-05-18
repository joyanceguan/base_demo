package com.joyance.springdemo.parser;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class DocumentReader {

	/**
	 * 创建Document对象
	 * 
	 * @param inputStream
	 * @return
	 */
	public Document createDocument(InputStream inputStream) {
		Document document = null;
		try {
			SAXReader reader = new SAXReader();
			document = reader.read(inputStream);
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 //根据路径获取document
    public Document getDocByPath(String path){
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
    	Document document = createDocument(inputStream);
    	return document;
    }

}
