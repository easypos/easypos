<%@ page contentType="text/html;charset=gb2312"%>
<SCRIPT LANGUAGE="JavaScript">
// ѡ���Ķ���֯
function selectGroup(){
  var url="SelectGroup.do";
  var dlg= window.open(url,"slectDlg",'top=20,left=50,width=420,height=570,scrollbars=no,resizable=no');  
}
// ѡ���Ķ��û� 
function selectUser(){
 AddressWin=window.open("PickUserDlgAction.do","AddressWin","resizable=1,scrollbars=1,status=no,toolbar=no,location=no,menu=no,width=640,height=480,top=20,left=30");
 AddressWin.focus();
}
</SCRIPT>
<!--
<tr>
    <td class="caption" >�Ķ���Χ</td>
    <td class="detail" ><input  name="reader" size="60" value="<%=request.getAttribute("readUsers")==null?"":(String)request.getAttribute("readUsers")%>">
    <input class="small" type="button" value="ѡ��" onclick="selectGroup();">
    </td>  
  </tr>
-->

<br>
 <table>
  <tr>
    <td>ʹ�÷�Χ</td>
    <td><input  name="$$Users" size="80" value="<%=request.getAttribute("readUsers")==null?"":(String)request.getAttribute("readUsers")%>">
    <input type="button" value="ѡ��" onclick="selectUser();">
    </td>  
  </tr>
</table>