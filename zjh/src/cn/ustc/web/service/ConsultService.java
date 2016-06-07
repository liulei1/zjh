package cn.ustc.web.service;

import java.awt.image.RescaleOp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Consult;
import cn.ustc.domain.ConsultCheck;
import cn.ustc.domain.Message;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.domain.Scheme;
import cn.ustc.utils.DateUtils;
import cn.ustc.utils.GetPropertiesUtil;
import cn.ustc.web.dao.ConsultCheckDAO;
import cn.ustc.web.dao.ConsultDAO;
import cn.ustc.web.dao.MessageDAO;
import cn.ustc.web.dao.ProfessorDAO;
import cn.ustc.web.dao.ProjectDAO;
import cn.ustc.web.dao.SchemeDAO;

@Transactional(rollbackFor=Exception.class)
public class ConsultService {
	@Autowired
	private ConsultDAO consultDAO;
	@Autowired
	private ConsultCheckDAO consultCheckDAO;
	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private SchemeDAO schemeDAO;
	@Autowired
	private ProfessorDAO professorDAO;
	
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
		
		// 使用算法，推送给专家
		this.sendMessageToProfessor();
		
		if(res > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 推送信息给专家的方法
	 */
	private void sendMessageToProfessor() {
		// TODO 使用算法来确定推送的人，推送的内容
		List<Professor> professors = professorDAO.findAll();
		int num = Integer.parseInt(GetPropertiesUtil.getProperties().getProperty("MessageSendNumber"));
		if(num>professors.size()){
			num = professors.size();
		}
		for (int i = 0; i < num; i++) {
			Random random = new Random();
			int index = random.nextInt(professors.size());
			Message message = new Message();
			// 发送给专家
			message.setType(Message.TOPROFESSOR);
			message.setSendTime(DateUtils.dateToString(new Date()));
			message.setState(Message.UNREAD);
			message.setTitle("有新的需求需要您来完成");
			// TODO 添加推送消息的内容
			message.setContent("咨询期待您来解决");
			message.setRecipientId(professors.get(index).getId());
			// 发送消息
			messageDAO.addMessage(message);
			professors.remove(index);
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
		
		Scheme scheme = schemeDAO.findById(project.getScm_id());
		// 发送消息给专家
		Message message = new Message();
		// 发送给专家
		message.setRecipientId(scheme.getProfessor().getId());
		message.setType(Message.TOPROFESSOR);
		message.setSendTime(DateUtils.dateToString(new Date()));
		message.setState(Message.UNREAD);
		message.setTitle("您的方案被接受");
		// TODO 发布需求后发送的消息内容写什么
		message.setContent("恭喜，您对需求-"+consult.getTitle()+" 提供的方案得到了对方的接受！");
		messageDAO.addMessage(message);
		return true;
	}
	
	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Consult> findConsultsByDetachedCriteria(DetachedCriteria criteria){
		return consultDAO.findByDetachedCriteria(criteria);
	}
	
	/**
	 * 条件查询
	 * @param criteria 条件
	 * @param firstResult 起始记录下标
	 * @param maxResults 最多记录数
	 * @return
	 */
	public List<Consult> findByDetachedCriteria(DetachedCriteria criteria, int firstResult, int maxResults){
		return consultDAO.findByDetachedCriteria(criteria, firstResult, maxResults);
	}
	
	/**
	 * 获取记录的总条数
	 * @return
	 */
	public int getCount(String companyId){
		return consultDAO.getCount(companyId);
	}

	public int allowCount(){
		return consultDAO.getAllowCount();
	}

	/**
	 * 获取推荐的咨询
	 * @param maxSize 推荐的咨询条数
	 * @return
	 */
	public List<Consult> getRecommendConsult(int maxSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Consult.class);
		criteria.add(Restrictions.eq("state", Consult.ALLOW));
		criteria.addOrder(Order.desc("release_date"));
		return consultDAO.findConsultByCriteria(criteria,maxSize);
	}
	
	/**
	 * 根据条件查询
	 * @param criteria
	 * @return
	 */
	public List<Consult> findConsultByCriteria(DetachedCriteria criteria){
		return consultDAO.findByDetachedCriteria(criteria);
	}

	/**
	 * 查询企业正在进行的项目个数
	 * @param id 企业用户id
	 * @return
	 */
	public int findCompanyOnGoingConsultCount(String id) {
		return consultDAO.findCompanyOnGoingConsultCount(id);
	}
}
