package com.fan.list.tag;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import com.f1jeeframework.http.tag.ViewTag;

public class ViewShopTypeTag extends ViewTag {
	public int doStartTag() throws JspTagException {
		try {
			JspWriter out = pageContext.getOut();
			try {
				
				out.print(html());
			} catch (Exception e) {
				out.print("无类别");
			}
		} catch (IOException ioe) {
			System.out.print("无类别");
		}
		return (SKIP_BODY);
	}

	public String html() throws Exception {
		params.clear();
		add("f");		
		add("v");
		add("id");				
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		HashMap hashMap = (HashMap) request.getAttribute("hashMap");
		String[][] types = (String[][]) hashMap.get("types");
		String listName = (String) hashMap.get("engineName");
		StringBuffer str = new StringBuffer();		
		for (int i = 0; i < types.length; i++) {			
			str.append("<a href="+listName+"?type="+types[i][0]+paramValues()+">");
			str.append(types[i][1]);			
			str.append("</a>");
		}
		
		return str.toString();
	}

}
