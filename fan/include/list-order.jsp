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
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/fan.css"/>  
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/panel-fan.css">
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/table.css">  
  <script src="/shares/jquery-ui-1.9.0/jquery-1.8.2.js"></script>
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/util.js"></SCRIPT>

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
	   var a="<%=viewId%>";
		if  (a=="FANORDER2"){
		//alert("已结算");
		$(".btn-edit").hide();
		$(".btn-buy").hide(); 
        $(".btn-change").hide(); 
		}
      $(".btn-see").click(function() {
        //alert("================");   		
		var a="<%=viewId%>";
		if  (a=="FANORDER2"){
        $(window.parent.document).find("#right").attr("src","seeFanOrderBuy.do?id="+$(this).attr("id"));		
		}
		else
		{
		$(window.parent.document).find("#right").attr("src","seeFanOrder.do?id="+$(this).attr("id"));
		}
	});	
	    $(".btn-edit").click(function() {
        //alert("================");   		
		var a="<%=viewId%>";
		if  (a=="FANORDER2"){
		//alert("已结算");
		return; 
		}
        $(window.parent.document).find("#right").attr("src","li-order-item.do?v=fanorderitem&id="+$(this).attr("id"));
	});	
	   $(".btn-change").click(function() {
	   if ($("#targetnu").val()==""){window.alert("===请选择台位=======");return;}
	      jQuery.ajax({
              type:'GET',
			  dataType:'string',			  
              url:'changeTable.do'+"?orderId="+$(this).attr("id")+"&tableNu="+$(this).attr("tabnu")+"&targetNu="+$("#targetnu-info").text(),			
              cache:false,   			  
			  error:function(data){						  
			  $("#msg").html("登录失败!");
			  },			  
			  success:function(data){			  
			   window.alert(data);
			  }
			  })
      
	  });
	  $(".btn-buy").click(function() {
        //alert("================");   		
		var a="<%=viewId%>";
        if  (a=="FANORDER2"){
		alert("已结算");
		return; 
		}
		$(window.parent.document).find("#right").attr("src","buyFanOrder.do?id="+$(this).attr("id"));
	});		
	//$("ul.display li").width(document.documentElement.clientWidth-10);	
	//window.onresize = resizeDiv; 
	function resizeDiv(){
	//$("ul.display li").width(document.documentElement.clientWidth-10);	
	}
});

</script>
<style type="text/css"> 
a.swap { background-position: left bottom; } 
.btn-see,.btn-edit,.btn-buy,.btn-change{cursor:pointer;background: url(btn_bg.png) no-repeat;width:125px;height:23px;border:0px;margin-top:1px;color:#fff}
</style>
</head>
<body>
<%
String findKey=(String)hashMap.get("findKey");
if (findKey==null) findKey="";
%>
<div class="pagebar-container"><html:viewDefaultPager /><div class="sou-bar"><input type="text" id="keyWord"  value="<%=findKey%>"/><img onclick="find();" src="/shares/resources/images/sou-btn.png" /></div></div>
<div class="container"> 
<ul class="display" id="list">
<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
		%>		
<li style="height:120px;">
<div class="content_block">
			<a href="li-dish-buy.do?v=dish"><img src="ordernow.gif" style="width:80px;height:60px"></a>
			<h2>定单:<%=viewDatas[i][1]%></h2> 
			<span style="float:right"><input class="btn-edit" type="button"  value="改单" id="<%=viewDatas[i][0]%>"></span>			 						
			<div style="height:24px">台桌:<%=viewDatas[i][4]%>|人数:8</div>
			<span style="float:right"><input class="btn-change" type="button" value="换台" id="<%=viewDatas[i][0]%>" tabnu="<%=viewDatas[i][4]%>"></span>			
			<div style="height:24px">时间:<%=viewDatas[i][3]%></div>
			<span style="float:right"><input class="btn-see" type="button" value="详情" id="<%=viewDatas[i][0]%>"></span>
			<div style="height:24px">工号:<%=viewDatas[i][2]%></div>				
			<span style="float:right"><input class="btn-buy" type="button" value="买单" id="<%=viewDatas[i][0]%>"></span>
		</div>	
	</li>
	<% } %>     
</ul>	
</div> 
<!-- end data  -->
<div style="float:right;clear:both;margin-right:10px;"><span id="targetnu-info">选择台位</span><input type="hidden" name="targetnu" id="targetnu" value=""><input type="button"  class="btn-select" onclick="dListForId('FANDININGTABLE','1','targetnu')" value="." class="eform-btn-select"> </div>
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
</body>
</html>

