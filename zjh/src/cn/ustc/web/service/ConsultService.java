package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Consult;
import cn.ustc.domain.ConsultCheck;
import cn.ustc.domain.Project;
import cn.ustc.web.dao.ConsultCheckDAO;
import cn.ustc.web.dao.ConsultDAO;
import cn.ustc.web.dao.ProjectDAO;

@Transactional(rollbackFor=Exception.class)
public class ConsultService {
	@Autowired
	private ConsultDAO consultDAO;
	@Autowired
	private ConsultCheckDAO consultCheckDAO;
	@Autowired
	private ProjectDAO projectDAO;
	
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
	public Consult findById(String id) {
		return consultDAO.findById(id);
	}

	/**
	 * 咨询 批准
	 * @param id
	 * @param consultCheck
	 * @return
	 */
	public boolean consultAllow(String id, ConsultCheck consultCheck) {
		consultCheck.setState(Consult.ALLOW);	// 插入审核表中
		consultCheckDAO.insert(consultCheck);
		int res = consultDAO.check(id,Consult.ALLOW); // 更新需求信息的状态
		
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
	public boolean consultReject(String id, ConsultCheck consultCheck) {
		consultCheck.setState(Consult.ALLOW);	// 插入审核表中
		consultCheckDAO.insert(consultCheck);
		int res = consultDAO.check(id,Consult.REJECT); // 更新需求信息状态
		
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
	public boolean consultRecieve(Project project, Consult consult){
		projectDAO.insert(project);
		consult.setState(Consult.COMPLETED);
		consultDAO.update(consult);
		return true;
	}
	
	public List<Consult> findConsultsByDetachedCriteria(DetachedCriteria criteria){
		return consultDAO.findByDetachedCriteria(criteria);
	}
}
