package cn.ustc.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Encoder;

public class UploadAndDownloadUtils {
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * @param filename 下载文件名
	 * @param agent 客户端浏览器
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public static String encodeDownloadFilename(String filename, String agent)
			throws IOException {
		if (agent.contains("Firefox")) { // 火狐浏览器
			filename = "=?UTF-8?B?"
					+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
					+ "?=";
		} else { // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
	
	/**
	 * 由路径 获取文件名
	 * @param filePath
	 * @return
	 */
	public static String checkFileName(String filePath) {
		int begin = filePath.indexOf("/");
//		int end = filePath.lastIndexOf(".");
//		return filePath.substring(begin, end);
		return filePath.substring(begin+1);
	}
	
	/**
	 * 存储上传文件,文件用UUID命名
	 * @param file 要存储的文件
	 * @param fileRootPath 要存储的路径
	 * @return
	 */
	public static String restoreFile(File file, String fileRootPath){
		String filePath = fileRootPath + "/" + UUID.randomUUID().toString();
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(file, destFile);
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 存储上传文件,文件用用户的Id命名.文件已存在则删除源文件
	 * @param file 要存储的文件
	 * @param fileRootPath 要存储的根路径
	 * @param id 用户id
	 * @return 文件在项目中的绝对地址
	 */
	public static String restoreFile(File file, String fileRootPath, String id){
		
		String filePath =  fileRootPath + "/" + id + ".png";
		File destFile = new File(filePath);
		// 若存在则先删除
		if(destFile.exists()){
			destFile.delete();
		}
		try {
			FileUtils.copyFile(file, destFile);
			return id+".png";
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
