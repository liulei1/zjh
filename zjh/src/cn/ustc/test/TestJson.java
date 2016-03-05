package cn.ustc.test;

import org.junit.Test;

import cn.ustc.domain.Company;

public class TestJson {

	@Test
	public void test() {
		Company comp=new Company();
		comp.setName("小李");
		System.out.println(comp);
	}

}
