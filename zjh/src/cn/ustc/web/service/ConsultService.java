package cn.ustc.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cn.ustc.domain.Consult;
import cn.ustc.utils.TypeChangeUtils;
import cn.ustc.web.dao.ConsultDAO;
import cn.ustc.web.dao.impl.ConsultDAOImpl;

public class ConsultService {
	private static final String ALLOW = "1"; //允许
	private static final String REJECT = "2";	//拒绝
	
	ConsultDAO consultDAO = new ConsultDAOImpl();
	public boolean Publish(Consult consult){
		int res = consultDAO.insert(consult);
		if(res > 0){
			return true;
		}
		return false;
	}

	/**
	 * 存储上传文件
	 * @param file
	 * @param filePath
	 * @return
	 */
	public boolean restoreFile(File file, String filePath){
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(file, destFile);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Consult> conlistList() {
		List<Consult> list =  consultDAO.findAll();
		return list;
	}

	public Consult findById(Integer id) {
		return consultDAO.findById(id);
	}

	public boolean consultAllow(Integer id) {
		int res = consultDAO.check(id,ALLOW);
		if(res > 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean consultReject(Integer id) {
		int res = consultDAO.check(id,REJECT);
		if(res > 0){
			return true;
		}else{
			return false;
		}
	}
}
