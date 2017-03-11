<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>"表单"</title>
<style type="text/css">
td{height:24px;}
</style>

<LINK REL="stylesheet" TYPE="text/css" HREF="/shares/resources/css/type1.css"/>  
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
  <script type="text/javascript" src="/shares/js/ui/ui.datepicker.js"></script>
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
  <link rel="stylesheet" type="text/css" href="/shares/resources/css/f1ui/default/menu.css">    
  <script type="text/javascript">
  $(document).ready(function(){	
    $('#switcher').themeswitcher();	
	//$( "input:submit, button").button();
	$("#a").click(function() {   
    alert("=============")
	id=$("#companycode").val();
	$("#attach-form").attr("action","/fan/AjaxUploadDish.do?companycode="+id);
	alert("/fan/AjaxUploadDish.do?companycode="+id);
    $("#errMsg").val("上传...")
	$("#attach-form").submit();
	});
  });
   
  </script>     
  <script type="text/javascript"  src="/shares/js/themeswitchertool.js"></script>  
</head>
<body>  
 <div class="panel" title="附件">  
<div id="errMsg" ></div>

<table class="f1-ui-table-1" border="0" cellspacing="2" cellpadding="0" width="100%">	
	<tr>
		<th style="width:100px"><label>项目</label></th>
		<th class="detail">属性</th>
	</tr>			        	
	<tr>
		<td ><label>启用</label></td>
		<td class="detail"><select name="status" id="status"><option selected value=0>是</option><option value=0>是</option><option value=1>否</option></select> </td>
	</tr>	
	<tr>
		<td ><label>公司代码</label></td>

		<td class="detail"><span id="companycode-info">眉州东坡</span><input type="hidden" name="companycode" id="companycode" value="2"><input type="button" onclick="dListForId_('fancompany','2','companycode','fan')" value="." class="eform-btn-select"> </td>
	</tr>	
</table>
<form id="attach-form" action="/fan/AjaxUploadDish.do" method="post"  enctype="multipart/form-data">
<input type=file id="attach-form-file" size=20 name="file1" multiple="multiple"><input type="button" id="a" value="上传" /><br/>
</form>  
 </div> 
</body>
</html>
