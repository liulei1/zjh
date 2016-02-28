package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Professor;
import cn.ustc.domain.Scheme;
import cn.ustc.web.dao.impl.SchemeDAO;

/**
 * 方案
 * @author liu存储
 *
 */
@Transactional
public class SchemeService {
	
	private SchemeDAO schemeDAO;
	public void setSchemeDAO(SchemeDAO schemeDAO) {
		this.schemeDAO = schemeDAO;
	}
	
	/**
	 * 发布解决方案
	 * @param scheme
	 */
	public void publish(Scheme scheme){
		schemeDAO.add(scheme);
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
}
