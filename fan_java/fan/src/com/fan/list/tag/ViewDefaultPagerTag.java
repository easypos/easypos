package com.fan.list.tag;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import com.f1jeeframework.http.tag.ViewPagerTag;

public class ViewDefaultPagerTag extends ViewPagerTag {
	public int doStartTag() throws JspTagException {
		JspWriter out = pageContext.getOut();
		params.clear();
		add("f");
		add("type");
		add("v");
		add("id");
		try {
			out.print(pager());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (SKIP_BODY);
	}

	public String pager() {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		HashMap hashMap = (HashMap) request.getAttribute("hashMap");
		StringBuffer str = new StringBuffer();
		int pages = new Integer((String) hashMap.get("pages"));
		int iNowPage = new Integer((String) hashMap.get("iNowPage"));
		String listName = (String) hashMap.get("engineName");
		String rowCounts = (String) hashMap.get("rowCounts");
		str.append("<div class=\"pages-list\">每页20条," + iNowPage + "/" + pages
				+ ",共" + rowCounts + "条");
		if (iNowPage > 1) {
			int pPrev = new Integer(iNowPage) - 1;
			str.append("<a href=\"" + listName + "?p=" + pPrev + paramValues()
					+ "\">上页</a>");
		}
		for (int i = 5; i > 0; i--) {
			if (iNowPage - i > 0) {
				str.append("<a href=\"" + listName + "?p="
						+ new Integer(iNowPage - i) + paramValues() + "\">"
						+ new Integer(iNowPage - i) + "</a>");
			}
		}
		str.append("<a class=\"cur\" href=\"" + listName + "?p=" + iNowPage
				+ paramValues() + "\">" + iNowPage + "</a>");
		for (int i = 1; i < 5; i++) {
			if (iNowPage + i < pages) {
				str.append("<a href=\"" + listName + "?p="
						+ new Integer(iNowPage + i) + paramValues() + "\">"
						+ new Integer(iNowPage + i) + "</a>");
			}
		}

		if (pages > 1) {
			if (iNowPage < pages) {
				int pNext = iNowPage + 1;
				str.append("<a href=\"" + listName + "?p=" + pNext
						+ paramValues() + "\">下页</a>");
			}
		}
		str.append("</div>");
		return str.toString();
	}
}
