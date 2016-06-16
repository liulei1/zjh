package cn.ustc.web.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Administer;
import cn.ustc.web.dao.AdministerDAO;
/**
 * 管理员service
 * @author liu
 *
 */
@Transactional
public class AdministerService {
	@Autowired
	private AdministerDAO administerDAO;

	/**
	 * 插入管理员用户
	 * @param Administer
	 * @return
	 */
	public boolean insertAdminister(Administer Administer){
		int res = 0;
		res = administerDAO.insertAdminister(Administer);
		if (res > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	public Administer login(Administer admin) {
		Administer loginAdminister=administerDAO.findByAdminInfo(admin.getName(),admin.getPassword());
		return loginAdminister;
	}

	/**
	 * 查找所有管理员
	 * @return
	 */
	public List<Administer> findAllAdminister() {
		List<Administer> Administers = administerDAO.findAll();
		return Administers;
	}

	/**
	 * 根据i的查找管理员
	 * @param id
	 * @return
	 */
	public Administer findAdministerById(String id) {
		Administer Administer = administerDAO.findByAdministerID(id);
		return Administer;
	}

	/**
	 * 更新管理员信息
	 * @param Administer
	 * @return
	 */
	public int update(Administer Administer) {
		return administerDAO.update(Administer);
	}

	/**
	 * 删除管理员
	 * @param id
	 */
	public void deleteAdministerById(String id) {
		administerDAO.deleteById(id);
	}

	/**
	 * 更具用户名查找管理员
	 * @param name
	 * @return
	 */
	public List<Administer> findAdministerByName(String name) {
		return administerDAO.findAdministerByName(name);
	}

}
