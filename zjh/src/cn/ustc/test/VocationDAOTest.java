package cn.ustc.test;

import java.text.DecimalFormat;
import java.util.List;

import org.junit.Test;

import cn.ustc.domain.Vocation;
import cn.ustc.web.dao.VocationDAO;
import cn.ustc.web.dao.impl.VocationDAOImpl;

public class VocationDAOTest {

	@Test
	public void testListVocation() {
		VocationDAO vd = new VocationDAOImpl();
		List<Vocation> listVocation = vd.listVocation();
		System.out.println(listVocation);
	}
	
	@Test
	public void testFormate(){
		DecimalFormat df = new DecimalFormat("0");    
		Double d = new Double("1.397545871E10");  
		System.out.println(df.format(d));
	}

}
