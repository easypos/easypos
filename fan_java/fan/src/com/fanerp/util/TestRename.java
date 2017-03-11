package com.fanerp.util;

import org.junit.Test;

public class TestRename {
	@Test
	public void a() {

		String from = "D:/Tomcat 6.0/webapps/fan/attachfiles/dish/coffe/esse";
		String to = "D:/Tomcat 6.0/webapps/fan/attachfiles/dish/coffe/esse_png";

		try {
			// FileUtil.copy(from, to);
			FileUtil.copyDirectiory_(from, to, "a", ".png");			
			//FileUtil.listDirectiory("E:/技术支持/esse/菜单","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
