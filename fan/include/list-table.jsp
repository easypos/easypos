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
	  $("ul.display").fadeOut("fast", function() {
	  	$(this).fadeIn("fast").addClass("thumb_view"); 
		 });
	  }, function () {
      $(this).removeClass("swap");
	  $("ul.display").fadeOut("fast", function() {
	  	$(this).fadeIn("fast").removeClass("thumb_view");
		});
	});  
});
</script>
<style type="text/css"> 
* {	margin: 0;padding: 0;}
img {border: none;}
h1 {
	font: 5em normal Georgia, 'Times New Roman', Times, serif;
	text-align:center;
	margin-bottom: 20px;
}
h1 span { 	color: #e7ff61; }
h1 small{
	font: 0.2em normal Verdana, Arial, Helvetica, sans-serif;
	text-transform:uppercase;
	letter-spacing: 1.5em;
	display: block;
	color: #ccc;
} 
.container {
	width: 758px;
	margin: 0 auto;	
	overflow: hidden;
}
ul.display {
	float: left;
	width: 756px;
	margin: 0;
	padding: 0;
	list-style: none;
	border: 1px solid #333;	
	background: #fff;
}
ul.display li {
	float: left;
	width: 754px;
	padding: 10px 0;
	margin: 0;
	border-top: 0px solid #111;
	border-right: 0px solid #111;
	border-bottom: 0px solid #333;
	border-left: 0px solid #333;
}
ul.display li a {
	color: #000;
	text-decoration: none;
}
ul.display li .content_block {
	padding: 0 1px;
}
ul.display li .content_block h2 {
	margin: 0;
	padding: 5px;
	font-weight: bold;
	font-size: 1.7em;
    color:#fff;	
}
ul.display li .content_block p {
	margin: 0;
	padding: 1px 1px 1px 1px;
	font-size: 1.2em;
	width:200px;
	color:#000;
}
ul.display li .content_block a img{
	padding: 5px;
	border: 0px solid #ccc;
	background: #fff;
	margin: 0 1px 0 0;
	float: left;
} 

ul.thumb_view li{
	width: 100px;
}
ul.thumb_view li h2 {
	display1: inline;
	width:88px;
	height:62px;
	line-height:62px;
	background:url(/fan/images/table-use.jpg) no-repeat;
	
}
ul.thumb_view li p{
	display: none;
}
ul.thumb_view li .content_block a img {
	margin: 0 0 10px;
} 
a.switch_thumb {
	width: 122px;
	height: 26px;
	line-height: 26px;
	padding: 0;
	margin: 10px 0;
	display: block;
	background: url(switch.gif) no-repeat;
	outline: none;
	text-indent: -9999px;
}
a:hover.switch_thumb {
	filter:alpha(opacity=75);
	opacity:.75;
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=75)";
}
a.swap { background-position: left bottom; }
 
 </style>
</head>
<body>
<%
String findKey=(String)hashMap.get("findKey");
if (findKey==null) findKey="";
%>
<div id="type-menu">
<dl class=choose>
  <dt>Àà±ð</dt>
   <dd>
      <html:viewDefaultType />
   </dd>
   </dt>
</dl>
</div>
<div class="pagebar-container"><html:viewDefaultPager /></div>
<div class="container"> 
<!-- <a href="#" class="switch_thumb">Switch Thumb</a>   -->
<ul class="display thumb_view">
<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
		%>		
<li>
		<div class="content_block ">
			
			<h2 style="color:#ff7400;"><%=viewDatas[i][1]%></h2>
			<p><%=viewDatas[i][4]%></p>
		</div>
	</li>
	<% } %>     
</ul>	
</div>
<!-- end data  -->
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

