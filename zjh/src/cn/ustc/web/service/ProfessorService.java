package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Company;
import cn.ustc.domain.Professor;
import cn.ustc.web.dao.ProfessorDAO;
/**
 * 专家service
 * @author liu
 *
 */
@Transactional
public class ProfessorService {
	@Autowired
	private ProfessorDAO professorDAO;
	private List<Professor> professors=null;

	/**
	 * 插入专家
	 * @param professor
	 * @return
	 */
	public boolean insertProfessor(Professor professor){
		int res = 0;
		res = professorDAO.insertProfessor(professor);
		if (res > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 专家登录
	 * @param professor
	 * @return
	 */
	public Professor login(Professor professor) {
		Professor loginProfessor = professorDAO.findProfessorByProfessorNameAndPwd(professor.getName(), professor.getPassword());
		return loginProfessor;
	}

	/**
	 * 查找所有专家
	 * @return
	 */
	public List<Professor> findAllProfessor() {
		List<Professor> Professors = professorDAO.findAll();
		return Professors;
	}

	/**
	 * 根据id查找专家
	 * @param id
	 * @return
	 */
	public Professor findProfessorById(String id) {
		Professor Professor = professorDAO.findByProfessorID(id);
		return Professor;
	}

	/**
	 * 根据id,更新传入的属性值
	 * @param professor
	 */
	public void updateInfo(Professor professor) {
		Professor update = this.copyUpdateProfessor(professor);
		professorDAO.update(update);
	}
	
	/**
	 * 更新传入的持久化对象
	 * @param professor
	 */
	public void update(Professor professor) {
		professorDAO.update(professor);
	}

	/**
	 * 根据id删除专家
	 * @param id
	 */
	public void deleteProfessorById(String id) {
		professorDAO.deleteById(id);
	}

	/**
	 * 根据名称查找专家
	 * @param name
	 * @return
	 */
	public List<Professor> findProfessorByName(String name) {
		return professorDAO.findProfessorByName(name);
	}
	
	/**
	 * 根据领域查找专家
	 * @param cat
	 * @return
	 */
	public List<Professor> findProfessorByVocation(String cat) {
		return professorDAO.findProfessorVocation(cat);
	}

	/**
	 * 复制更新的内容到持久对象中
	 * @param professor
	 * @return
	 */
	private Professor copyUpdateProfessor(Professor professor){
		Professor instance = professorDAO.findByProfessorID(professor.getId());
		instance.setName(professor.getName());
		instance.setEmail(professor.getEmail());
		instance.setTelephone(professor.getTelephone());
		instance.setAddress(professor.getAddress());
		instance.setWebsite(professor.getWebsite());
		instance.setSex(professor.getSex());
		instance.setField(professor.getField());
		instance.setIntroduction(professor.getIntroduction());
		return instance;
	}
	
	/**
	 * 找到所有未审核的用户
	 * @return
	 */
	public List<Professor> findAllUnaudit() {
		professors=professorDAO.findAllUnaudit();
		return professors;
	}
	
	/**
	 * 通过注册审核
	 * @param id
	 */
	public void pass(String id) {
		professorDAO.pass(id);
	}

	/**
	 * 审核注册不通过
	 * @param id
	 */
	public void refuse(String id) {
		professorDAO.refuse(id);
	}
	/**
	 * 获取推荐的专家
	 * @return
	 */
	public List<Professor> getRecommendProfessor(int maxSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Professor.class);
		// 用户状态必须为可用
//		criteria.add(Restrictions.eq("state", "1"));
		criteria.addOrder(Order.desc("points"));
		return professorDAO.findProfessorByCriteria(criteria, maxSize);
	}

	/**
	 * 获取专家总数
	 * @return
	 */
	public int getProfessorCount(){
		return professorDAO.getProfessorCount();
	}

	/**
	 * 分页条件查询
	 * @param criteria 条件
	 * @param firstResult 首记录下标
	 * @param maxResults 最大记录数
	 * @return
	 */
	public List<Professor> findByDetachedCriteria(DetachedCriteria criteria,int firstResult, int maxResults) {
		return professorDAO.findByDetachedCriteria(criteria, firstResult, maxResults);
	}
}
