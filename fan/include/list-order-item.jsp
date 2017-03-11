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
<link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/menu.css"> 
 <link rel="stylesheet" type="text/css" href="/shares/resources/css/themes/base/ui.all.css">  
  <script type="text/javascript" src="/shares/js/jquery-1.5.1.min.js"></script>		  
  <script src="/shares/js/ui/ui.core.js"></script>
  <script src="/shares/js/ui/ui.widget.js"></script>
  <script src="/shares/js/ui/ui.mouse.js"></script>
  <script src="/shares/js/ui/ui.draggable.js"></script>
 <script src="/shares/js/ui/ui.resizable.js"></script>
 <script type="text/javascript"	src="/fan/js/plug-in/jquery.info.js"></script>
 <script type="text/javascript"	src="/fan/include/top.js"></script>
 <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/util.js"></SCRIPT>
 <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.form.js"></SCRIPT>  
<script type="text/javascript"> 
id="";
status="0";
$(document).ready(function(){
      var options = {
             		  //target:        '#output1',   // 用服务器返回的数据 更新 id为output1的内容.	       
					   beforeSubmit:  showRequest,  // 提交前
				        success:       showResponse,  // 提交后 				        
				        resetForm: false       				       
				    };				    
    $('#ViewForm').ajaxForm(options); 	
	 // 提交前
	function showRequest(formData, jqForm, options) { 			  
				  return true;				  
				  }                  				
	function showResponse(responseText, statusText)  {               		      
			  $("#msg").html(responseText);    
              if (status=="1")
			  {status="退"}
			  else if (status=="2"){
			  status="赠";
			  }
			  else
			  {status="-"}
              $("#status"+id).html("状态:"+status);			  
             }   		   
      $(".btn-call").click(function() {       
	      alert($(this).attr("id"));
	      $("#ViewForm").attr("action","a.do");
		  $("#ViewForm").submit();
	   });	
	  $(".btn-status").click(function() {        
	     id=$(this).attr("id");
		 status="1";
         a="changeOrderItemStatus.do?status=1&id="+id;	      	     
		 $("#ViewForm").attr("action",a);
		 $("#ViewForm").submit();
	  });
	  $(".btn-free").click(function() {         
	     id=$(this).attr("id");
		 status="2";
         a="changeOrderItemStatus.do?status=2&id="+id;	      	     
	     $("#ViewForm").attr("action",a);
		 $("#ViewForm").submit();	  
	 });			
});
</script>
<style type="text/css"> 
a.swap { background-position: left bottom; } 
.btn-status,.btn-call,.btn-free{cursor:pointer;background: url(btn_bg.png) no-repeat;width:125px;height:23px;border:0px;color:#fff}
</style>
</head>
<body>
<form id="ViewForm">
<input type="hidden" name="orderid" value="<%=request.getParameter("id")%>" />
<%
String findKey=(String)hashMap.get("findKey");
if (findKey==null) findKey="";
%>
<div class="pagebar-container"><html:viewDefaultPager /></div>
<div class="container"> 
<ul class="display" id="list">
<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
		%>		
<li>
<div class="content_block">
			<a href="#"><img src="ordernow.gif" style="width:80px;height:60px"></a>
			<h2>名称:<%=viewDatas[i][4]%></h2> 
			<span style="float:right"><input class="btn-call" type="button"  value="催菜" id="<%=viewDatas[i][0]%>"></span>			 						
			<div style="height:23px">单价:<%=viewDatas[i][5]%></div>
			<span style="float:right"><input class="btn-status" type="button" value="退菜" id="<%=viewDatas[i][0]%>"></span>			
			<div style="height:23px" id="status<%=viewDatas[i][0]%>">状态:<%=viewDatas[i][19]%></div>
			<span style="float:right"><input class="btn-free" type="button" value="赠送" id="<%=viewDatas[i][0]%>"></span>						
		</div>	
	</li>
	<% } %>     
</ul>	
</div> 
<!-- end data  -->
<div class="pagebar-container"><html:viewDefaultPager /></div>
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
<div id="msg"></div>
</form>
</body>
</html>

