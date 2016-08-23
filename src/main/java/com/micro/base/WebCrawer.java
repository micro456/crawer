package com.micro.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mapc WebCrawer.java 2016年8月23日
 * @Description
 */
public abstract class WebCrawer {
	
	protected String saveDir = null;

	protected List<String> urlList = new ArrayList<String>();

	protected String suffix = null;

	public String getDomainNameFromUrl(String url) {
		log("get domain name from url : " + url);
		int start = url.indexOf(".");
		String tempContent = url.substring(start + 1);
		tempContent = tempContent.substring(0, tempContent.indexOf("."));
		log("get domain name : " + tempContent);
		return tempContent;
	}
	
	protected void log(String content) {
		// 暂时打印到控制台
		System.out.println(this.getClass().getName() + ":" + content);
	}
	
	public abstract void downloadUrlToDir(String url) throws Exception;
}
