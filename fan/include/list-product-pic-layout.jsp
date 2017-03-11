<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<%@ page import="com.f1jeeframework.http.AppSession"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="com.f1jeeframework.view.service.ReportService"%>
<%@ page import="com.f1jeeframework.view.ReportSession"%>
<%@ page import="com.f1jeeframework.model.FormItemBean"%>
<%@ page import="com.f1jeeframework.view.ReportSession"%>
<%@ page import="com.f1jeeframework.model.ViewBean"%>
<% 
	HashMap hashMap = (HashMap) request.getAttribute("hashMap");
	ApplicationContext cxt = AppSession.getApplicationContext();
		ReportService reportService = (ReportService) cxt
				.getBean("reportService"); 
   String showName=(String)hashMap.get("showName");        
		String viewId = (String) hashMap.get("viewId");
		ReportSession reportSession = reportService.getReportSession(viewId);
		ViewBean viewBean = reportSession.getViewBean();		
		String addApp = (String) hashMap.get("addAppName");
		String delApp = (String) hashMap.get("delAppName");
		String editApp = (String) hashMap.get("editAppName");
		String seeApp = (String) hashMap.get("seeAppName");
		Vector indexs = (Vector) hashMap.get("indexList");
		Vector roles = (Vector) hashMap.get("roles");
		String[][] viewDatas = (String[][]) hashMap.get("viewDatas");
		String[] ids = (String[]) hashMap.get("ids");
		String colCounts = (String) hashMap.get("colCounts");
%>
<html>
<tiles:insertAttribute name="header"/>
<body>
<center><div id="errInfo" class="errInfo" style="display:none;"></div></center>
<%@ page contentType="text/html;charset=GBK"%>
<%
String findKey=(String)hashMap.get("findKey");
if (findKey==null) findKey="";
%>
<ul id="jsddm1" class="typemenu">
<li><a href="#">类别1▼</a>
<div><html:viewDefaultType /></div>
</li>
</ul>
<div class="search" style="margin-top:0px; margin-left:-2px; margin-right:3px;">
  <li>
    <input type=text name="SearchInput" id="find"  value="<%=findKey%>">
  </li>
  <li><img onclick="find();" src="resources/images/form/search.gif" /></li>  
</div>
</div>
<div class="pagebar-container"><html:viewDefaultPager /></div>
<div style="margin:10px auto;">
<div class="list_photo-inline">	
        <% if (viewDatas.length==0) { %>         
        <li>   没有记录  </li>
        <% } %> 
	<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
		%>		

<div class="list_photo-box">
<dl>
<dt>
<strong><%=viewDatas[i][1]%></strong>  
 <i></i>
</dt>	
	<dd><%=viewDatas[i][3]%></dd>	
	<dd class="photo"><%
				if (editApp.equals("1")) {
			%> <a href="#" onclick="see(<%=id%>)">[查看]</a> <%
 	}
 %> |<%
 	if (seeApp.equals("1")) {
 %> <a href="#" onclick="edit(<%=id%>)">[修改]</a> <%
 	}
 %></dd>
   <dd><img src="resources/images/37.png"/><%=viewDatas[i][4]%></dd>	   
</dl>
</div>
                     		<%
			}
		%>
        
</div>
</div>
</div>
<!-- end data  -->
<!--
<div class="pagebar-container"><html:viewDefaultPager /></div>
<div id="dialogDel"  title="F1JEE操作提示?" style="padding:5px;width:400px;">
<p>删除选中的记录?</p>
</div>
-->
<script type="text/javascript">
//var layer1=window.parent.document.getElementById("layer1");
//layer1.style.display="none";
//var layer2=window.parent.document.getElementById("layer2");
$layer1=$("#layer1",window.parent.document);
$layer1.fadeOut();
$layer1.hide();
$layer2=$("#layer2",window.parent.document);
//layer2.style.display="block";

//$layer2.css("opacity","0.1")
//$layer2.animate({opacity:"1"},200)
//$layer2.fadeIn();
//$layer2.fadeIn("slow");
$layer2.show();
</script>
</body>
</html>

