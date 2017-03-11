 <SCRIPT LANGUAGE="JavaScript">
  <%
     if (varList!= null){
         for(int i=0;i<varList.length;i++) {
     %>
         //alert('<%=varList[i][0]%>')
         permitFormSave(true,'<%=varList[i][0]%>');         
     <%
      }
       }
     %> 
 
 </SCRIPT>