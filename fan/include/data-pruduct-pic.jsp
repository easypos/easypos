<%@ page contentType="text/html;charset=gb2312"%>
<div style="margin:10px auto;">
<div class="list_photo-inline">
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
				if (tmp.equals("序号") || tmp.toLowerCase().equals("id")) {
					idPos = i;
				} else {
				}
			}
		}
	%>
        <% if (viewDatas.length==0) { %>         
        <li>   没有记录  </li>
        <% } %> 
	<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = ids[i];			
		%>		

<div class="list_photo-box">
<dl>
<dt>
<strong><%=viewDatas[i][0]%></strong>  
 <i></i>
</dt>	
	<dd><%=viewDatas[i][2]%></dd>	
	<dd class="photo"><%
				if (viewEdit.equals("1")) {
			%> <a href="#" onclick="see(<%=id%>)">[查看]</a> <%
 	}
 %> |<%
 	if (viewSee.equals("1")) {
 %> <a href="#" onclick="edit(<%=id%>)">[修改]</a> <%
 	}
 %></dd>
   <dd><img src="resources/images/37.png"/><%=viewDatas[i][3]%></dd>	   
</dl>
</div>
                     		<%
			}
		%>
        
</div>
</div>
</div>
<!-- end data  -->

