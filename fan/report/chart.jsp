<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<html>  
<head> 
<title>-</title>
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
    jQuery.datepicker.setDefaults(jQuery.extend({dateFormat:"yy-mm-dd",showButtonPanel: true,showOn: 'button',buttonImage: '/shares/resources/images/calendar.gif',buttonImageOnly: true}));
	//$("#jsddm1").typemenu();
	//$(".typemenu").menu();	
	$("#begin_").datepicker();
	//$("#end_").datepicker();
	    var options = {
             		  //target:        '#output1',   // �÷��������ص����� ���� idΪoutput1������.	       
					   beforeSubmit:  showRequest,  // �ύǰ
				        success:       showResponse,  // �ύ�� 				        
				        resetForm: false       				       
				    };				    
    $('#form-report').ajaxForm(options); 	
	 // �ύǰ
	function showRequest(formData, jqForm, options) { 			  
	              $("#div-report").html("load...")
				  return true;				  
				  }                  				
	function showResponse(responseText, statusText)  {     
              //alert("<img src="+responseText+"</img>");	
			  $("#div-report").html("load...")
			  $("#div-report").html("<img src=/fan/servlet/DisplayChart?filename="+responseText+"></img>");			  
             } 
				    
});
</script>
  <script>
  $(document).ready(function(){
    $('#switcher').themeswitcher();
  });
  </script>  
  <script type="text/javascript"  src="/shares/js/themeswitchertool.js"></script> 
</head>
<body>
<form name="form-report" id="form-report" method="post" action="report.do">
 <script type="text/javascript">
   $(function() {  
   $("#btn-save").click(function() {$("#form-report").submit();});
	});
  </script>  
<div class="panel" title="����">
<fieldset>
<legend>�����ѯ����</legend>
<table border="0" cellspacing="2" cellpadding="0" width="100%">
	<tr>
		<td ><label>ͳ�Ʒ�ʽ</label></td>
		<td class="detail">
		<input type="radio" name="type" value="1" checked="checked"/>����
		<input type="radio" name="type" value="2" />����
		<input type="radio" name="type" value="3" />����		
		</td>
		<td ><label>��ʼ</label></td>
		<td class="detail"><input name="begin_" id="begin_"></td>	
		<td ><label>��Χ</label></td>
		<td class="detail"><input name="count" id="count" value="10"></td>						
	</tr>	    
	<tr>
		<td ><label>��ʾ��ʽ</label></td>
		<td class="detail" colspan="5">
		<input type="radio" name="chart" value="1" checked="checked" />��ͼ
		<input type="radio" name="chart" value="2" />��ͼ		
		</td>
	</tr>	    
</table>
</fieldset>
<div class="f1j-ui-toolbar">##    
  <input type="button" id="btn-save" value="��ѯ">
  </div> 
</div>

<fieldset style="margin:10px">
<legend>���۱���</legend>
<center>
<div id="div-report">û������</div>
</center>
</fieldset>
</form>
</body>
</html>

