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
<link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/panel-fan.css">  
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/fan-list-buy.css">
<script type="text/javascript"	src="/shares/js/jquery-1.3.2.min.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/util.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/strUtil.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/chkutil.js"></SCRIPT>      
<SCRIPT src="/shares/js/list.js" type="text/javascript"></SCRIPT>
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
	  $(".btn-add").click(function() {
	  var tableNu=$(this).attr("tableNu");
	  var status=$(this).attr("status");
	  var id=$(this).attr("id");
	  if (status=="0") title="开台";
	  if (status=="1") title="撤台";
	  r=window.confirm("台号"+tableNu+title+",要继续吗？");
	  if (r ==false){
	  return;
	  }	  
	  //alert(id);
	  //alert("#ermsg"+id);
	  $("#ermsg"+id).html("正在处理...");	  
	  a=status;
	  if (a=="0"){status="1";}
	  if (a=="1"){status="0";}
	  //alert("status"+status);
	   $.ajax({
              type:"POST",
              url:"saveTableStatus.do",			  
			  data:{id:id,status:status}, 		
              dataType:"text",		  
			  success:function(data){			  			  
			  if ((data.substring(0,1))=="s")
			  {
			   $("#"+id).show();
			   if (status=="0"){$("#"+id).val("开台");}
			   if (status=="1"){$("#"+id).val("撤消");}			   			   
			   $("#"+id).attr("status",status);		               			   
			  }
               $("#ermsg"+id).html("");	  			  
			  }
			  })
	  //alert($(this).attr("id"));				
	  //$right=$("#right",window.parent.document);
	  $diningtableid=$(window.parent.frames["right"].document).find("#diningtableid");								
	  $diningtableid.val($(this).attr("tableNu"));
	  //window.parent.frames["right"].add($(this).attr("id"),$(this).attr("title"));
	  //$menu.append("<li style=\"height:24px;line-height:24px;background:#f1f1f1;border:1px solid #000;\"><input type=hidden value=\"a\">"+$(this).attr("title")+"<span style=\"float:right;\"><input type=button value=\"x\"></span></li>");
	});	
	$(".list-right").width(document.documentElement.clientWidth-160);		
});
</script>

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
<div class="pagebar-container"><html:viewDefaultPager /></div>
<!-- <a href="#" class="switch_thumb">Switch Thumb</a>   -->
<ul class="thumb_view">
<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
			String status=null;
	        String a=viewDatas[i][2];
	        if (a.equals("闲置")){status="0";}
	        if (a.equals("就绪")){status="1";}
	        if (a.equals("使用")){status="2";}			
		%>		
<li>
		<div class="content_block ">			
			<center>	         
			<h2 class="status<%=status%>"><center><%=viewDatas[i][1]%></center></h2>	
            <div id="ermsg<%=viewDatas[i][0]%>"></div>			
			<br>          	
	<%
			if (a.equals("0")){
			%>
	         <input type="button" class="btn-add" value="开台" class="btn-add" status="<%=a%>" id="<%=viewDatas[i][0]%>" tableNu="<%=viewDatas[i][1]%>">						
			<%
			}			
			%>		
			<%
			if (a.equals("1")){
			%>
			<input type="button" class="btn-add" value="撤消"  status="<%=a%>" id="<%=viewDatas[i][0]%>" tableNu="<%=viewDatas[i][1]%>">						
			<%
			}			
			%>
            <%
			if (a.equals("2")){
			%>
			<%=viewDatas[i][3]%>			
			<%
			}			
			%> 			
			<p><%=viewDatas[i][1]%></p>
			</center>
		</div>
	</li>
	<% } %>     
</ul>	
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

