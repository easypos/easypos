<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<html>  
 <head> 
<title>-</title>
<link rel="stylesheet" href="/shares/jquery-ui-1.9.0/themes/Vader/jquery.ui.all.css">
 <LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/fan.css"/>  
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
    $('#form-report').ajaxForm(
	                {					   
             		   //target:        '#output1',   // 用服务器返回的数据 更新 id为output1的内容.	       
					    beforeSubmit:  showRequest,  // 提交前
				        dataType:'json',
						success:       processJson,  // 提交后 				        						
				        resetForm: false       				       
				    }
	); 	
	 // 提交前
	function showRequest(formData, jqForm, options) { 			  
	              $("#div-report").html("load...")
				  return true;				  
				  }                  				
	function processJson(data)  {     
      //        alert("="); 	
      //        alert("<img src="+data[0].img+"</img>");	
			  $("#div-report").html("load...")
			  $("#div-report").html("<img src=/fan/servlet/DisplayChart?filename="+data[0].img+"></img>");			  
			  var list=data[0].data;			  
			  for ( var i = 0; i < list.length; i++) {			  
			  $("#div-data").append($("<tr><td>"+list[i][0]+"</td>"+"<td>"+list[i][1]+"</td></tr>"));			  			  			   
			  }			  
             } 
				    
});
</script>
</head>
<body>
<form name="form-report" id="form-report" method="post" action="reportForOrdertype.do">
 <script type="text/javascript">
   $(function() {  
   $("#btn-save").click(function() {$("#form-report").submit();});   
	});
  </script>
   <script>
  $(document).ready(function(){
    $('#switcher').themeswitcher();
  });
  </script>  
  <script type="text/javascript"  src="/shares/js/themeswitchertool.js"></script>  
<div class="panel" title="输入">
<fieldset>
<legend>输入查询条件</legend>
<table border="0" cellspacing="2" cellpadding="0" width="100%">
	<tr>
		<td ><label>统计方式</label></td>
		<td class="detail">
		<input type="radio" name="type" value="1" checked="checked"/>按天
		<input type="radio" name="type" value="2" />按月
		<input type="radio" name="type" value="3" />按年		
		</td>
		<td ><label>开始</label></td>
		<td class="detail"><input name="begin_" id="begin_"></td>	
		<td ><label>范围</label></td>
		<td class="detail"><input name="end_" id="end_"></td>	
	</tr>	    
	<tr>
		<td ><label>显示方式</label></td>
		<td class="detail" >
		<input type="radio" name="chart" value="1" checked="checked" />柱图
		<input type="radio" name="chart" value="2" />饼图				
		</td>
		<td ><label>数量</label></td>
		<td class="detail"><input name="count" id="count" value="10"></td>	
		<td ><label>...</label></td>
		<td class="detail"><input class="btn-default" type="button" id="btn-save" value="查询"></td>	
	</tr>	    
</table>
</fieldset>
</div>
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">图表</a></li>
		<li><a href="#tabs-2">数据</a></li>		
	</ul>
	<div id="tabs-1">     
	<center>
	<div id="div-report">没有数据</div>
	</center>
	</div>

	<div id="tabs-2">	
     <center>	
     <table class="div-table-1" id="div-data">     <tr><th>项目</th><th>金额</th><tr>     </table>
	 </center>
	</div>
	
</div>

</form>
</body>
</html>

