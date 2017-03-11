<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<% HashMap hashMap =(HashMap)request.getAttribute("hashMap"); 
   String showName=(String)hashMap.get("showName");  
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
<html:viewDefaultBody />
<div class="pagebar-container"><html:viewDefaultPager /></div>
<div id="dialogDel"  title="F1JEE操作提示?" style="padding:5px;width:400px;">
<p>删除选中的记录?</p>
</div>
</body>
</html>

