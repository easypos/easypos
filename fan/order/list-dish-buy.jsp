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
   String appName=(String)hashMap.get("viewId");   
   String engineName=(String)hashMap.get("engineName");
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
<link rel="stylesheet" href="/shares/resources/css/themes/base/ui.all.css">
<style>
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/fan.css"	TITLE="SID OA">
<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/fan-list-buy.css">
<link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/panel-fan.css">
 <script src="/shares/jquery-ui-1.9.0/jquery-1.8.2.js"></script>
    <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.table.js"></SCRIPT>
    <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.panel.js"></SCRIPT>
    <script src="/shares/jquery-ui-1.9.0/external/jquery.bgiframe-2.1.2.js"></script>
    <script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.core.js"></script>
    <script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.widget.js"></script>
 	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.mouse.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.button.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.draggable.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.position.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.resizable.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.dialog.js"></script>	   
  <script type="text/javascript" src="/shares/js/ui/ui.datepicker.js"></script>
  <script type="text/javascript" src="/shares/js/ui/i18n/ui.datepicker-zh-TW.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/util.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/strUtil.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/chkutil.js"></SCRIPT>
<SCRIPT src="/shares/js/list.js" type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.form.js"></SCRIPT>
<script src="/shares/o2o/js/order_m.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
var id,title,count,price;
    $(".btn-add").click(function() {
	            id=$(this).attr("id");
				title=$(this).attr("title");
				price=$(this).attr("price");				
				
				window.parent.frames["right"].showShoppingCart();
		});
});
</script>
</head>
<body>
<form name="ViewForm" id="ViewForm" method="post">
  <input name="appName" id="appName"  type="hidden" value=<%=appName%>>
  </input>
  <input name="viewName" id="viewName"  type="hidden" value=<%=appName%>>
  </input>
  <input id="li-name" type="hidden" value=<%=engineName%>>
  </input>  
  
  <%
String findKey=(String)hashMap.get("findKey");
if (findKey==null) findKey="";
%>
    <div id="type-menu">
      <dl class=choose>
        <dt>类别</dt>
        <dd>
          <html:viewDefaultType />
        </dd>
        </dt>
      </dl>
    </div>  
  <div class="list-right1"> <a href="li-dish-buy.do?v=Dish">单品</a>|<a href="li-dish-buy.do?&v=Dishsuit1">固定套餐</a>|<a href="li-dish-buy.do?v=Dishsuit2">组合套餐</a>
    
	<div class="pagebar-container">
      <html:viewDefaultPager />
      <div class="sou-bar">
        <input type="text" id="keyWord"  value="<%=findKey%>"/>
        <img onclick="find();" src="/shares/resources/images/sou-btn.png" /></div>
    </div>
	
    <ul class="thumb_view"  id="list">
      <%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = viewDatas[i][0];			
		%>
      <li>
        <div class="content_block">
          <center>
            <a href="#"><img src="/ysk/attachfiles/dish/<%=viewDatas[i][0]%>/<%=viewDatas[i][1]%>" width="80" height="60"></a>
            <h2><%=viewDatas[i][2]%> <img src="/shares/images/menu/money.png"><%=viewDatas[i][3]%></h2>            
			<br>
			<input type="button" class="btn-add" type="button" value="购买" img_="/<%=viewDatas[i][0]%>/<%=viewDatas[i][1]%>" id="<%=viewDatas[i][0]%>" title="<%=viewDatas[i][2]%>" price="<%=viewDatas[i][3]%>">            
            <p><%=viewDatas[i][6]%></p>
          </center>
        </div>
      </li>
      <% } %>
    </ul>
    <!-- end data  -->
    <div class="pagebar-container">
      <html:viewDefaultPager />
    </div>
  </div>
</form>
</body>
</html>
