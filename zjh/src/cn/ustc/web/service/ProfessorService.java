package cn.ustc.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.ustc.domain.Professor;
import cn.ustc.web.dao.ProfessorDAO;
import cn.ustc.web.dao.impl.ProfessorDAOImpl;

@Transactional
public class ProfessorService {
	@Autowired
	private ProfessorDAOImpl professorDAO;
	public void setProfessorDAO(ProfessorDAOImpl professorDAO) {
		this.professorDAO = professorDAO;
	}

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

	public int update(Professor Professor) {
		return professorDAO.update(Professor);
	}

	public void deleteProfessorById(String id) {
		professorDAO.deleteById(id);
	}

	public List<Professor> findProfessorByName(String name) {
		return professorDAO.findProfessorByName(name);
	}

}
