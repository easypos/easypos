<%@ page contentType="text/html;charset=gb2312"%>
<!--Slides-->

<%
		int idPos = 0;
		for (int i = 0; i < titleList.size(); i++) {
			FormItemBean formItemBean = (FormItemBean) titleList
					.get(new Integer(i));
			String inList = formItemBean.getInList();
			if (inList == null)
				inList = "";
			if (!inList.equals("1")) {
				String tmp = formItemBean.getTitle().trim();
				if (tmp.equals("ÐòºÅ") || tmp.toLowerCase().equals("id")) {
					idPos = i;
				} else {
				}
			}
		}
	%>
        <% if (viewDatas.length==0) { %>         
        <li>   Ã»ÓÐ¼ÇÂ¼  </li>
        <% } %> 
<div id="slider">
<ul>
		<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = ids[i];			
		%>
	<li><a href="#"><%=viewDatas[i][0]%></a></li>	
           <%
			}
		%>      
		</ul>
</div>
<!-- end data  -->
