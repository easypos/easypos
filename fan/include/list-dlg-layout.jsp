<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<% HashMap hashMap =(HashMap)request.getAttribute("hashMap"); 
   String showName=(String)hashMap.get("showName");  
   String appName=(String)hashMap.get("viewId");
   String engineName=(String)hashMap.get("engineName");   
%>
<html>
<tiles:insertAttribute name="header"/>
<body>
<form name="ViewForm" id="ViewForm" method="post">
<input id="appName" type="hidden" value=<%=appName%>></input>
<input id="li-name" type="hidden" value=<%=engineName%>></input>
<center><div id="errInfo" class="errInfo" style="display:none;"></div></center>

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
<dl class=choose>
  <dt>搜索</dt>
   <dd> <input  name="keyWord" id="keyWord"  value="<%=findKey%>" /><img onclick="find();" src="resources/images/form/search.gif" /></dd>
   </dt>
</dl>
</div>
<div class="pagebar-container ui-widget-content ui-corner-all"><html:viewDefaultPager /></div>
<html:viewDlgBody />
<div class="pagebar-container ui-widget-content ui-corner-all"><html:viewDefaultPager /></div>
<!--
<div id="dialogDel"  title="F1JEE操作提示?" style="padding:5px;width:400px;">
<p>删除选中的记录?</p>
</div> 
-->
</form>
<script type="text/javascript">
//alert("==========");
var layer1=window.parent.document.getElementById("layer1");
layer1.style.display="none";
var layer2=window.parent.document.getElementById("layer2");
layer2.style.display="block";
</script>
<script type="text/javascript">
$(document).ready(function(){	
    $('#switcher').themeswitcher();
	$("#jsddm1").typemenu();
	$(".typemenu").menu();	
	//$( "input:submit, a, button").button();
	//$( "input:submit, a, button").with(200);
	//$( "input:submit, a, button").height(20);	
		//$( "a", ".demo" ).click(function() { return false; });
});
</script> 
</body>
</html>

