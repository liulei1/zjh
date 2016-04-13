package cn.ustc.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ustc.domain.Professor;
import cn.ustc.domain.User;
import cn.ustc.web.service.ProfessorService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ProfessorActionTest {
	@Autowired
	private ProfessorService professorService;
	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}
	@Test
	public void testGetProfessors() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetProfessorService() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister() {
		Professor professor = new Professor();
		professor.setAddress("suzhou");
		professor.setEmail("123@qwe.com");
		professor.setName("liu");
		professor.setUsertype(User.PROFESSOR);
		
		professorService.insertProfessor(professor);
		System.out.println();
	}

	@Test
	public void testManagement() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckProfessorName() {
		fail("Not yet implemented");
	}

}
