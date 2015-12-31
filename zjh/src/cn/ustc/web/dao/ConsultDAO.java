package cn.ustc.web.dao;

import java.util.List;

import cn.ustc.domain.Consult;

public interface ConsultDAO {

	public int insert(Consult consult);

	public List<Consult> findAll();

	public Consult findById(Integer id);

	public int check(Integer id, String state);

	public List<Consult> findUncheckConsult();
	
	public List<Consult> findAllowConsult();

}
