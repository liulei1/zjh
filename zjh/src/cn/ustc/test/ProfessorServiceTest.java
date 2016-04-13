package cn.ustc.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ustc.domain.Professor;
import cn.ustc.web.dao.ProfessorDAO;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ProfessorServiceTest {
	@Autowired
	private ProfessorDAO professorDAO;
	
	@Test
	public void testFindAllProfessor() {
		List<Professor> professors = professorDAO.findAll();
		for (int i = 0; i < professors.size(); i++) {
			System.out.println(professors.get(i));
		}
		System.out.println("---");
		Iterator<Professor> iterator = professors.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
