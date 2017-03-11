<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<% HashMap hashMap =(HashMap)request.getAttribute("hashMap"); 
   String showName=(String)hashMap.get("showName");  
   String appName=(String)hashMap.get("viewId");
   String findKey=(String)hashMap.get("findKey");
   if (findKey==null) findKey="";
   String engineName=(String)hashMap.get("engineName");
%>
<html>
<tiles:insertAttribute name="header"/>
<body>
<form name="ViewForm" id="ViewForm" method="post">
<input name="appName" id="appName"  type="hidden" value=<%=appName%>></input>
<input id="li-name" type="hidden" value=<%=engineName%>></input>
<div class="ui-widget-header ui-corner-all" style="padding-left:5px;"><h2>${hashMap.showName}</h2></div>
<center><div id="errInfo" class="errInfo" style="display:none;"></div></center>
<ul id="jsddm1" class="type">
<li><a href="#">Àà±ð</a>
<div><html:viewDefaultType/></div>
</li>
</ul>
<div class="search" style="margin-top:0px; margin-left:-2px; margin-right:3px;">
  <li>
    <input type="text" id="keyWord"  value="<%=findKey%>" />
  </li>
  <li><img onclick="find();" src="resources/images/form/search.gif" /></li>  
</div>

<div class="pagebar-container ui-widget-content ui-corner-all"><html:viewDefaultPager /></div>
<html:viewDefaultBody />
<div class="pagebar-container ui-widget-content ui-corner-all"><html:viewDefaultPager /></div>
</form>
<script type="text/javascript">
$(document).ready(function(){	
    $('#switcher').themeswitcher();
	$("#jsddm1").typemenu();
	$(".typemenu").menu();		
    $("tr:odd").addClass("odd");
	$("tr:even").addClass("even");	  
  
	//$( "input:submit, a, button").button();
	//$( "input:submit, a, button").with(200);
	//$( "input:submit, a, button").height(20);	
	//$( "a", ".demo" ).click(function() { return false; });
});
</script> 
</body>
</html>

