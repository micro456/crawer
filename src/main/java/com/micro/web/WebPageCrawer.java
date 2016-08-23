package com.micro.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.micro.base.Crawer;
import com.micro.base.WebCrawer;

public class WebPageCrawer extends WebCrawer implements Crawer {

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getLocalUrl(String url) {
		File dir = null;
		if (!(dir = new File(saveDir)).exists()) {
			log("save dir not exits, create local dir : " + saveDir);
			String createResultMessage = dir.mkdir() ? "create success!" : "create error";
			log(createResultMessage);
		}
		String domainName = getDomainNameFromUrl(url);
		StringBuffer localUrl = new StringBuffer();
		return localUrl.append(saveDir).append(File.separator).append(domainName).append(".").append(suffix).toString();
	}

	@Override
	public void downloadUrlToDir(String url) throws Exception {
		log("craw page from url");
		if (urlList.contains(url)) 
			throw new Exception(url + "已存在");
		urlList.add(url);
		URLConnection connection = null;
		StringBuffer content = new StringBuffer();
		try {
			connection = new URL(url).openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String oneLine = null;
			while ((oneLine = reader.readLine()) != null) {
				content.append(oneLine).append("\n");
			}
		} catch (Exception ex) {
			log("连接失败");
			log("stack message : " + ex.getMessage());
			return;
		}
		if (content == null || content.equals("")) {
			System.out.println("内容获取为空");
			throw new Exception("内容获取失败");
		}
		
		try {
			// 字符流保存文件
			String localUrl = getLocalUrl(url);
			log("localUrl : " + localUrl);
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(localUrl)));
			log("save content ");
			log(content.toString());
			writer.write(content.toString());
			writer.close();
		} catch (Exception ex) {
			log("save file exception.");
			log("stack message : " + ex.getMessage());
			return;
		}
		log("craw success ! end program ..");
	}
}
