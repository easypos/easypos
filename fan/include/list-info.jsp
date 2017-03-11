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
    ReportService reportService = (ReportService) cxt.getBean("reportService"); 
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
<head>
<title><%=showName%></title>
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/fan.css"	TITLE="SID OA">
<script type="text/javascript"	src="/shares/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){ 
	$("a.switch_thumb").toggle(function(){
	  $(this).addClass("swap"); 
	  $("#list").fadeOut("fast", function() {
	  	$(this).fadeIn("fast").addClass("thumb_view").removeClass("display"); 
		 });
	  }, function () {
      $(this).removeClass("swap");
	  $("#list").fadeOut("fast", function() {
	  	$(this).fadeIn("fast").removeClass("thumb_view").addClass("display");
		});
	});
    $(".btn-see").click(function() {        
        $(window.parent.document).find("#right").attr("src","seeInfo.do?id="+$(this).attr("id"));
	});	
	 $(".btn-edit").click(function() {        
        $(window.parent.document).find("#right").attr("src","loadInfo.do?id="+$(this).attr("id"));
	});
    $(".btn-add").click(function() {        
        $(window.parent.document).find("#right").attr("src","loadInfo.do");
	});		
});
</script>
<style type="text/css"> 
a.swap { background-position: left bottom; } 
.btn-add{cursor:pointer;background: url(btn_add.png) no-repeat;width:125px;height:23px;border:0px;}
.btn-edit{cursor:pointer;background: url(btn_edit.png) no-repeat;width:125px;height:23px;border:0px;}
.btn-see{cursor:pointer;background: url(btn_see.png) no-repeat;width:125px;height:23px;border:0px;}
</style>
</head>
<body>
<%
String findKey=(String)hashMap.get("findKey");
if (findKey==null) findKey="";
%>
<div id="type-menu">
<dl class=choose>
  <dt>类别</dt>
   <dd><html:viewDefaultType /></dd>
   </dt>
</dl>
</div>
<div class="pagebar-container"><html:viewDefaultPager /><div class="sou-bar"><input type="text" id="keyWord"  value="<%=findKey%>"/><img onclick="find();" src="/shares/resources/images/sou-btn.png" /></div></div>
<div class="container"> 
<ul class="display" id="list">
<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
		%>		
<li>
		<div class="content_block">
			<a href="#"><img src="ordernow.gif" style="width:80px;height:60px"></a>
			<h2><%=viewDatas[i][2]%></h2> 
			<span style="float:right"><input class="btn-edit" type="button"  id="<%=viewDatas[i][0]%>"></span>			 						
			<div>类别:<%=viewDatas[i][3]%></div>
			<div>作者:<%=viewDatas[i][1]%></div>
			<div>时间:<%=viewDatas[i][5]%></div>
			<span style="float:right"><input class="btn-see" type="button" id="<%=viewDatas[i][0]%>"></span>
			<br>			
			<p>-</p>
		</div>
	</li>
	<% } %>     
</ul>	
</div>
<!-- end data  -->
<div class="pagebar-container"><html:viewDefaultPager /></div>
<div style="float:right"><input class="btn-add" type="button"></div>			 						
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

