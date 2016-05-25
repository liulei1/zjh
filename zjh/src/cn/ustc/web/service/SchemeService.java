package cn.ustc.web.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Consult;
import cn.ustc.domain.Message;
import cn.ustc.domain.Professor;
import cn.ustc.domain.Scheme;
import cn.ustc.utils.DateUtils;
import cn.ustc.web.dao.ConsultDAO;
import cn.ustc.web.dao.MessageDAO;
import cn.ustc.web.dao.SchemeDAO;

/**
 * 方案
 * @author liu存储
 *
 */
@Transactional
public class SchemeService {
	@Autowired
	private SchemeDAO schemeDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private ConsultDAO consultDAO;
	
	/**
	 * 发布解决方案
	 * @param scheme
	 */
	public void publish(Scheme scheme){
		String cons_id = scheme.getCons_id();
		Consult consult = consultDAO.findById(cons_id);
		
		Message message = new Message();
		message.setRecipientId(consult.getCom_id());
		message.setType(Message.TOCOMPANY);
		message.setSendTime(DateUtils.dateToString(new Date()));
		message.setState(Message.UNREAD);
		message.setTitle("您的咨询 "+consult.getTitle()+" 有了回复");
		// TODO 发布方案后发送的消息内容写什么
		message.setContent(scheme.getProfessor().getName() + "对您的咨询-"+ consult.getTitle() + "提供了解决方案");
		schemeDAO.add(scheme);
		messageDAO.addMessage(message);
	}
	
	/**
	 * 删除方案
	 * @param scheme
	 */
	public void delete(Scheme scheme){
		schemeDAO.delete(scheme);
	}
	
	/**
	 * 所有解决方案
	 * @return
	 */
	public List<Scheme> findAll(){
		return schemeDAO.findAll();
	}
	
	/**
	 * 条件查询
	 * @param criteria
	 * @return
	 */
	public List<Scheme> findByDetachedCriteria(DetachedCriteria criteria){
		return schemeDAO.findByDetachedCriteria(criteria);
	}
	
	/**
	 * 根据id 获取方案
	 * @param id
	 * @return
	 */
	public Scheme findById(String id) {
		return schemeDAO.findById(id);
	}

	/**
	 * 查找专家发布的所有方案
	 * @param professor
	 * @return 
	 */
	public List<Scheme> findMyScheme(Professor professor) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Scheme.class);
		criteria.add(Restrictions.eq("professor", professor));
		return schemeDAO.findByDetachedCriteria(criteria);
	}
	//查询指定条数的方案，做分页
	public List<Scheme> findByDetachedCriteria(DetachedCriteria criteria,int page,int pageSize){
		return schemeDAO.findByDetachedCriteriaPage(criteria,page,pageSize);
	}

	public int getCountByPorfessorID(String id) {
		// TODO Auto-generated method stub
		return schemeDAO.getCountByPorfessorID(id);
	}
}
