package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Administer;
import cn.ustc.web.dao.AdministerDAO;

@Transactional
public class AdministerService {
	@Autowired
	private AdministerDAO administerDAO;

	public boolean insertAdminister(Administer Administer){
		int res = 0;
		res = administerDAO.insertAdminister(Administer);
		if (res > 0) {
			return true;
		}
		return false;
	}

	public Administer login(Administer admin) {
		Administer loginAdminister=administerDAO.findByAdminInfo(admin.getName(),admin.getPassword());
		return loginAdminister;
	}

	public List<Administer> findAllAdminister() {
		List<Administer> Administers = administerDAO.findAll();
		return Administers;
	}

	public Administer findAdministerById(String id) {
		Administer Administer = administerDAO.findByAdministerID(id);
		return Administer;
	}

	public int update(Administer Administer) {
		return administerDAO.update(Administer);
	}

	public void deleteAdministerById(String id) {
		administerDAO.deleteById(id);
	}

	public List<Administer> findAdministerByName(String name) {
		return administerDAO.findAdministerByName(name);
	}

}
