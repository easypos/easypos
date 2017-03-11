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
	 $(".btn-edit").click(function() {        
        $(window.parent.document).find("#right").attr("src","loadDish.do?id="+$(this).attr("id"));
	});
	$(".btn-see").click(function() {        
        $(window.parent.document).find("#right").attr("src","seeDish.do?id="+$(this).attr("id"));
	});
	//alert(document.documentElement.clientWidth-154);
	$(".list-right").width(document.documentElement.clientWidth-154);
});
</script>
<style type="text/css"> 
* {
	margin: 0;
	padding: 0;
}
 
img {
	border: none;
}
h1 {
	font: 5em normal Georgia, 'Times New Roman', Times, serif;
	text-align:center;
	margin-bottom: 20px;
}
h1 span {color: #e7ff61; }
h1 small{
	font: 0.2em normal Verdana, Arial, Helvetica, sans-serif;
	text-transform:uppercase;
	letter-spacing: 1.5em;
	display: block;
	color: #ccc;
}
.container {
	margin: 0 auto;	
	overflow: hidden;
}

a.swap { background-position: left bottom; }
.btn-add{cursor:pointer;background: url(btn_add.png) no-repeat;width:125px;height:23px;border:0px;}
.btn-edit{cursor:pointer;background: url(btn_bg.png) no-repeat;color:#fff;width:125px;height:23px;border:0px;}
.btn-see{cursor:pointer;background: url(btn_bg.png) no-repeat;color:#fff;width:125px;height:23px;border:0px;}
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
<div id="type-menu">
<dl class=choose>
  <dt>类别</dt>
   <dd><a href="li-dish.do?v=Dish">单品</a>|<a href="li-dish.do?&v=Dishsuit1">固定套餐</a>|<a href="li-dish.do?v=Dishsuit2">组合套餐</a></dd>
   </dt>
</dl>
</div>

<div class="pagebar-container"><html:viewDefaultPager /><div class="sou-bar"><input type="text" id="keyWord"  value="<%=findKey%>"/><img onclick="find();" src="/shares/resources/images/sou-btn.png" /></div></div>
<ul class="display" id="list">
<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
		%>		
<li>
		<div class="content_block">
			<a href="#"><%=viewDatas[i][1]%></a>
			<h2><%=viewDatas[i][2]%></h2>
            <span style="float:right;"><input class="btn-edit" type="button" value="修改    Edit" id="<%=viewDatas[i][0]%>"></span>			 						
			<div>拼音码:<%=viewDatas[i][6]%></div> 			
			<span style="float:right;"><input class="btn-see" type="button" value="查看    View" id="<%=viewDatas[i][0]%>"></span>			 						
			<div>价格:<%=viewDatas[i][3]%></div> 			 
			<div>编号:<%=viewDatas[i][6]%></div> 			 
			<div>单位:<%=viewDatas[i][5]%></div>
		</div>
	</li>
	<% } %>     
</ul>	
<!-- end data  -->
<div class="pagebar-container"><html:viewDefaultPager /><a href="loadDish.do">+</a></div>
</div>
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

