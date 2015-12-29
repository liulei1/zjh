package cn.ustc.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import cn.ustc.domain.Consult;
import cn.ustc.domain.ConsultCheck;
import cn.ustc.utils.TypeChangeUtils;
import cn.ustc.web.dao.ConsultCheckDAO;
import cn.ustc.web.dao.ConsultDAO;
import cn.ustc.web.dao.impl.ConsultCheckDAOImpl;
import cn.ustc.web.dao.impl.ConsultDAOImpl;

public class ConsultService {
	private static final String ALLOW = "1"; //允许
	private static final String REJECT = "2";	//拒绝
	
	ConsultDAO consultDAO = new ConsultDAOImpl();
	ConsultCheckDAO consultCheckDAO = new ConsultCheckDAOImpl();
	
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
	 * @param fileRootPath
	 * @return
	 */
	public String restoreFile(File file, String fileRootPath){
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

	public List<Consult> conlistList() {
		List<Consult> list =  consultDAO.findAll();
		return list;
	}

	public Consult findById(Integer id) {
		return consultDAO.findById(id);
	}

	/**
	 * 咨询 批准
	 * @param id
	 * @param consultCheck
	 * @return
	 */
	public boolean consultAllow(Integer id, ConsultCheck consultCheck) {
		consultCheck.setState(ALLOW);	// 插入审核表中
		consultCheckDAO.insert(consultCheck);
		
		int res = consultDAO.check(id,ALLOW);
		if(res > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 咨询 拒绝
	 * @param id
	 * @param consultCheck
	 * @return
	 */
	public boolean consultReject(Integer id, ConsultCheck consultCheck) {
		consultCheck.setState(ALLOW);	// 插入审核表中
		consultCheckDAO.insert(consultCheck);
		
		int res = consultDAO.check(id,REJECT);
		if(res > 0){
			return true;
		}else{
			return false;
		}
	}
}
