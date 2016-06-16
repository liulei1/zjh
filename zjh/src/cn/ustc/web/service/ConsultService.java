package cn.ustc.web.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Company;
import cn.ustc.domain.Consult;
import cn.ustc.domain.ConsultCheck;
import cn.ustc.domain.Message;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Project;
import cn.ustc.domain.Scheme;
import cn.ustc.utils.DateUtils;
import cn.ustc.utils.GetPropertiesUtil;
import cn.ustc.web.dao.CompanyDAO;
import cn.ustc.web.dao.ConsultCheckDAO;
import cn.ustc.web.dao.ConsultDAO;
import cn.ustc.web.dao.MessageDAO;
import cn.ustc.web.dao.ProfessorDAO;
import cn.ustc.web.dao.ProjectDAO;
import cn.ustc.web.dao.SchemeDAO;
/**
 * 咨询service
 * @author liu
 *
 */
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
	@Autowired
	private CompanyDAO companyDAO;
	
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
	 * 咨询 批准 -- 不发送消息
	 * @param consultId 咨询的Id
	 * @param consultCheck 审核记录
	 * @return
	 */
	public boolean consultAllow(String consultId, ConsultCheck consultCheck) {
		consultCheck.setState(Consult.ALLOW);	// 插入审核表中
		consultCheckDAO.insert(consultCheck);
		int res = consultDAO.check(consultId,Consult.ALLOW); // 更新需求信息的状态
		
		// 使用算法，推送给专家
		this.sendMessageToProfessor();
		
		if(res > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 咨询批准 -- 发送消息
	 * @param consult 咨询
	 * @param company 发布的企业
	 * @param consultCheck 审核记录
	 * @return
	 */
	public String consultAllow(Consult consult, Company company, ConsultCheck consultCheck) {
		double pay = Double.valueOf(consult.getBudget());
		double balance = Double.valueOf(company.getBalance());
		
		if(balance > pay){
			// 插入审核表中
			consultCheck.setState(Consult.ALLOW);
			consultCheckDAO.insert(consultCheck);
			
			// 更新企业的余额
			company.setBalance(String.valueOf(balance-pay));
			companyDAO.update(company);
			
			// 更新需求信息的状态
			consultDAO.check(consult.getId(),Consult.ALLOW);
			
			// 发送消息
			String title = "审核结果";
			String content = "您的咨询 "+ consult.getTitle() +" 审核通过";
			String recipientId = company.getId();
			int type = Message.TOCOMPANY;
			this.sendMessage(title, content, recipientId, type);
			return "success";
		}else {
			// 审核拒绝，原因余额不足
			consultCheck.setState(Consult.REJECT);
			consultCheck.setRejectReason("Balance Not Enough");
			consultCheckDAO.insert(consultCheck);
			
			// 更新需求信息的状态
			consultDAO.check(consult.getId(),Consult.REJECT);
			
			// 发送消息
			String title = "审核结果";
			String content = "您的咨询 "+ consult.getTitle() +" 审核失败。原因：余额不足.";
			String recipientId = company.getId();
			int type = Message.TOCOMPANY;
			this.sendMessage(title, content, recipientId, type);
			return "balanceNotEnough";
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
		message.setContent("恭喜，您对需求 - "+consult.getTitle()+" 提供的方案得到了对方的接受！");
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

	/**
	 * 查看待接收的咨询
	 * @return
	 */
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

	/**
	 * 查找专家提交了方案，暂未形成项目的咨询
	 * @param professorId
	 */
	public List<Consult> findProfessorApplyConslut(String professorId) {
		return consultDAO.findProfessorApplyConslut(professorId);
	}

	/***********************************************私有方法*************************************************/
	
	/**
	 * 发送消息
	 * @param title 消息标题
	 * @param content 消息内容
	 * @param recipientId 消息的接受者
	 * @param type 消息的类型.发送给专家Message.TOPROFESSOR.还是发送给企业Message.TOCOMPANY
	 */
	private void sendMessage(String title, String content, String recipientId, int type){
		Message message = new Message();
		message.setType(type);
		message.setSendTime(DateUtils.dateToString(new Date()));
		message.setState(Message.UNREAD);
		message.setTitle(title);
		message.setContent(content);
		message.setRecipientId(recipientId);
		// 发送消息
		messageDAO.addMessage(message);
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
//			Message message = new Message();
//			// 发送给专家
//			message.setType(Message.TOPROFESSOR);
//			message.setSendTime(DateUtils.dateToString(new Date()));
//			message.setState(Message.UNREAD);
//			message.setTitle("有新的需求需要您来完成");
//			message.setContent("咨询期待您来解决");
//			message.setRecipientId(professors.get(index).getId());
//			// 发送消息
//			messageDAO.addMessage(message);
			
			// TODO 添加推送消息的内容
			String title = "推送信息";
			String content = "新的咨询期待您来解决";
			String recipientId = professors.get(index).getId();
			int type = Message.TOPROFESSOR;
			this.sendMessage(title, content, recipientId, type);
			professors.remove(index);
		}
	}
}
