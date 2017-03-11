<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<html>  
 <head> 
<title>-</title>
<link rel="stylesheet" href="/shares/jquery-ui-1.9.0/themes/ysk-restaurant/jquery.ui.all.css">
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/panel-crm.css">
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/table.css">    
    <script src="/shares/jquery-ui-1.9.0/jquery-1.8.2.js"></script>
    <script src="/shares/jquery-ui-1.9.0/external/jquery.bgiframe-2.1.2.js"></script>
    <script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.core.js"></script>
    <script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.widget.js"></script>
 	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.mouse.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.button.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.draggable.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.position.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.resizable.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.dialog.js"></script>
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.tabs.js"></script>		
	<script src="/shares/jquery-ui-1.9.0/ui/jquery.ui.datepicker.js"></script>		  
  <script type="text/javascript" src="/shares/js/ui/i18n/ui.datepicker-zh-TW.js"></script>
  <SCRIPT LANGUAGE="JavaScript"  SRC="/shares/js/form.js"></SCRIPT>  
  <SCRIPT LANGUAGE="JavaScript"  SRC="/shares/js/form-zh-CN.js"></SCRIPT>   
  <!--
  <SCRIPT LANGUAGE="JavaScript"  SRC="/shares/fckeditor/fckeditor.js"></SCRIPT>
  -->
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.table.js"></SCRIPT>  
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/util.js"></SCRIPT>
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/strUtil.js"></SCRIPT>
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/chkutil.js"></SCRIPT>    
  <SCRIPT LANGUAGE="JavaScript" SRC="/shares/js/plug-in/jquery.form.3.18.js"></SCRIPT>    
<script type="text/javascript"> 
$(document).ready(function(){	
    $( "#tabs" ).tabs();
    jQuery.datepicker.setDefaults(jQuery.extend({dateFormat:"yy-mm-dd",showButtonPanel: true,showOn: 'button',buttonImage: '/shares/resources/images/calendar.gif',buttonImageOnly: true}));
	//$("#jsddm1").typemenu();
	//$(".typemenu").menu();	
	$("#begin_").datepicker();
	$("#end_").datepicker();	    	    
});
</script>
</head>
<body>
<form name="form-report" id="form-report" method="post" action="reportForBuyType.do">
 <script type="text/javascript">
   $(function() {  
   //alert("test");
   $("#btn-save").click(function() {$("#form-report").submit();});     
	});
  </script>  
<div class="path ui-widget-header ui-corner-all" style="padding-left:5px;"><h2>�������ͳ��</h2></div>  
<div class="panel" title="����">
<fieldset>
<legend>�����ѯ����</legend>
<table border="0" cellspacing="2" cellpadding="0" width="100%">
	<tr>
		
		<td ><label>��ʼ</label></td>
		<td class="detail"><input name="begin_" id="begin_" value="<%=request.getAttribute("begin_")%>"></td>	
		<td ><label>��Χ</label></td>
		<td class="detail"><input name="end_" id="end_" value="<%=request.getAttribute("end_")%>"></td>	
	</tr>	    
	<tr>
		<td ><label>��ʾ��ʽ</label></td>
		<td class="detail" >
		<input type="radio" name="chart" value="1" checked="checked" />��ͼ
		<input type="radio" name="chart" value="2" />��ͼ				
		</td>		
		<td ><label>...</label></td>
		<td class="detail">
		<input class="btn-default" type="button" id="btn-save" value="��ѯ">|<input class="btn-default" type="button" id="btn-export" value="����">
		</td>	
	</tr>	    
</table>
</fieldset>
</div>
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">����(��������)</a></li>				
		
	</ul>	
	<div id="tabs-1">	     
     <table id ="view_table">
	 <tbody>
     <tr><th>��Ŀ</th><th>���</th></tr>     	  
	  <%
	HashMap hashMap=(HashMap)request.getAttribute("types");		
	Set entries = hashMap.entrySet();
		Iterator it1 = entries.iterator();
		int rowCount = 0;
		while (it1.hasNext()) {
			Map.Entry entry = (Map.Entry) it1.next();
			String key = (String) entry.getKey();
			List types_ = (List) entry.getValue();
			if  (types_.size()>0){
	%>       
      <tr><th colspan="2"><%=key%></th></tr>         
        <%
			for (int j = 0; j < types_.size(); j++) {
			    
				Map type_ = (Map) types_.get(j);
				if (type_==null) continue;
				String itemvalue = (String) type_.get("itemvalue");
				Object sum1= (Object) type_.get("sum1");				
				if (sum1==null) sum1="0.00";
				else
				{
				
				}
	  %>
	    <tr><td><%=itemvalue%></td><td><%=sum1%></td></tr>         
        <%	
		}
	%>
	
     
    <%
		}
		}
	%>
	</tbody>
	 </table>	
	 <div id="sum"></div>	 
	</div>	
	<div id="tabs-2">     
	<center>
	<div id="div-report">û������</div>	
	</center>
	</div>
</div>
<script type="text/javascript">
   $(function() {     
    var type=<%=request.getParameter("type")%>;
    //alert(type)	;
	var myDate = new Date();
    myDate.getYear();        //��ȡ��ǰ���(2λ)
    var yy=myDate.getFullYear();    //��ȡ���������(4λ,1970-????)
    var mm=myDate.getMonth()+1;       //��ȡ��ǰ�·�(0-11,0����1��)
    var dd=myDate.getDate();
    var wd=myDate.getDay();         //��ȡ��ǰ����X(0-6,0����������)
    //myDate.getTime();        //��ȡ��ǰʱ��(��1970.1.1��ʼ�ĺ�����)
    //myDate.getHours();       //��ȡ��ǰСʱ��(0-23)
    //myDate.getMinutes();     //��ȡ��ǰ������(0-59)
    //myDate.getSeconds();     //��ȡ��ǰ����(0-59)
    //myDate.getMilliseconds();    //��ȡ��ǰ������(0-999)
    //myDate.toLocaleDateString();     //��ȡ��ǰ����       
	if (type==1){
	  $("#begin_").val(yy+"-"+mm+"-"+dd); 	
      //$("#form-report").submit();	
	}
	else if (type==2)
	{
	  $("#begin_").val(yy+"-"+mm+"-"+1); 	
	  if (mm==12) {
	      yy=yy+1
       $("#end_").val(yy+"-"+1+"-"+1); 	
	  }
	  else
	  {
	   mm=mm+1;
	   $("#end_").val(yy+"-"+mm+"-"+1); 	
	  }
      //$("#form-report").submit();	
	}
	else
	{
	}
	});
  </script>
</form>
</body>
</html>

