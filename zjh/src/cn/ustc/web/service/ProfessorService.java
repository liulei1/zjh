package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Professor;
import cn.ustc.web.dao.ProfessorDAO;

@Transactional
public class ProfessorService {
	@Autowired
	private ProfessorDAO professorDAO;
	
	private List<Professor> professors=null;

	public boolean insertProfessor(Professor professor){
		int res = 0;
		res = professorDAO.insertProfessor(professor);
		if (res > 0) {
			return true;
		}
		return false;
	}

	public Professor login(Professor professor) {
		Professor loginProfessor = professorDAO.findProfessorByProfessorNameAndPwd(professor.getName(), professor.getPassword());
		return loginProfessor;
	}

	public List<Professor> findAllProfessor() {
		List<Professor> Professors = professorDAO.findAll();
		return Professors;
	}

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

	public void deleteProfessorById(String id) {
		professorDAO.deleteById(id);
	}

	public List<Professor> findProfessorByName(String name) {
		return professorDAO.findProfessorByName(name);
	}
	
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
}
