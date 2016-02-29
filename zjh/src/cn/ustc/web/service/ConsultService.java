package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Consult;
import cn.ustc.domain.ConsultCheck;
import cn.ustc.domain.Project;
import cn.ustc.domain.Scheme;
import cn.ustc.web.dao.impl.ConsultCheckDAOImpl;
import cn.ustc.web.dao.impl.ConsultDAOImpl;
import cn.ustc.web.dao.impl.ProjectDAOImpl;

@Transactional
public class ConsultService {
	public static final String UNCHECK = "0";	//拒绝
	public static final String ALLOW = "1"; 	//允许
	public static final String REJECT = "2";	//拒绝
	
	private ConsultDAOImpl consultDAO;
	private ConsultCheckDAOImpl consultCheckDAO;
	private ProjectDAOImpl projectDAO;
	
	public void setConsultDAO(ConsultDAOImpl consultDAO) {
		this.consultDAO = consultDAO;
	}
	public void setConsultCheckDAO(ConsultCheckDAOImpl consultCheckDAO) {
		this.consultCheckDAO = consultCheckDAO;
	}
	public void setProjectDAO(ProjectDAOImpl projectDAO) {
		this.projectDAO = projectDAO;
	}
	/**
	 * 发布咨询
	 * @param consult
	 * @return
	 */
	public boolean publish(Consult consult){
		int res = consultDAO.insert(consult);
		if(res > 0){
			return true;
		}
		return false;
	}

	/**
	 * 查询全部咨询
	 * @return List
	 */
	public List<Consult> consultList() {
		List<Consult> list =  consultDAO.findAll();
		return list;
	}

	/**
	 * 查询所有未审核的咨询
	 * @return List
	 */
	public List<Consult> unCheckConsultList() {
		List<Consult> list =  consultDAO.findUncheckConsult();
		return list;
	}
	
	/**
	 * 查询所有 审核通过
	 * @return
	 */
	public List<Consult> allowConsultList() {
		List<Consult> list =  consultDAO.findAllowConsult();
		return list;
	}
	
	/**
	 * 根据 id 查找
	 * @param id
	 * @return
	 */
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
		int res = consultDAO.check(id,ALLOW); // 更新需求信息的状态
		
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
		int res = consultDAO.check(id,REJECT); // 更新需求信息状态
		
		if(res > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 咨询选取方案
	 * @param project
	 * @return
	 */
	public boolean consultRecieve(Project project){
		projectDAO.insert(project);
		return true;
	}
	
	public List<Consult> findConsultsByDetachedCriteria(DetachedCriteria criteria){
		return consultDAO.findByDetachedCriteria(criteria);
	}
}
