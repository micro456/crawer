package com.micro.base;

/**
 * 
 * @author mapc
 * Crawer.java
 * 2016年8月23日
 * @Description
 */
public interface Crawer {
	
	public String getSaveDir();
	
	public String getLocalUrl(String url);
	
	public String getSuffix();
}
