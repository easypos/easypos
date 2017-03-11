<%
HashMap hashMap =(HashMap)request.getAttribute("hashMap");
String cssFile=(String)hashMap.get("cssFile");
cssFile=(cssFile==null)?"form":cssFile;
String jsp_=(String)hashMap.get("jsp_");
if (jsp_==null) jsp_="";
if (jsp_.equals("")) jsp_="";

HashMap titleList=(HashMap)hashMap.get("titleList");
String viewAdd =(String)hashMap.get("viewAdd");
if (viewAdd==null) viewAdd="0";
String viewDel =(String)hashMap.get("viewDel");
if (viewDel==null) viewDel="0";
String viewEdit =(String)hashMap.get("viewEdit");
if (viewEdit==null) viewEdit="0";
String viewSee =(String)hashMap.get("viewSee");
if (viewSee==null) viewSee="0";
//String catKeys =(String)hashMap.get("catKeys");
String catKey=(String)hashMap.get("catKey");
String catCode=(String)hashMap.get("catCode");
Vector keys=(Vector)hashMap.get("keys");
String findKey=(String)hashMap.get("findKey");
if (findKey==null) findKey="";
String engineName=(String)hashMap.get("engineName");
String viewMenuAppPath=(String)hashMap.get("viewMenuAppPath");
String formName=(String)hashMap.get("formName");
String agentName=(String)hashMap.get("agentName");
String addAppName=(String)hashMap.get("addAppName");
String delAppName=(String)hashMap.get("delAppName");
String editAppName=(String)hashMap.get("editAppName");
String seeAppName=(String)hashMap.get("seeAppName");
String appName=(String)hashMap.get("appName");
String showName=(String)hashMap.get("showName");
String sFormName=(String)hashMap.get("sFormName");
String colCounts=(String)hashMap.get("colCounts");
String rows=(String)hashMap.get("rows");
String rowCounts=(String)hashMap.get("rowCounts");
String iStart=(String)hashMap.get("iStart");
String pages=(String)hashMap.get("pages");
String jumps=(String)hashMap.get("jumps");
String recNums=(String)hashMap.get("recNums");
String iNowPage=(String)hashMap.get("iNowPage");
Vector actionList=(Vector)hashMap.get("actionList");
String iCount=(String)hashMap.get("iCount");
Vector indexList=(Vector)hashMap.get("indexList");
String[][]  viewDatas=(String[][])hashMap.get("viewDatas");
String[]  ids=(String[])hashMap.get("ids");  
String context =request.getParameter("context");
Vector parameters=new Vector(); 
parameters.addElement("a");
parameters.addElement("b");
String[][] viewMenus=(String[][])hashMap.get("viewMenus"); 
%>
<input name="engineName" type="hidden" value=<%=engineName%>></input>
<input name="tmpDocIDs" type="hidden"></input>
<input name="appName" type="hidden" value=<%=appName%>></input>
<input name="delAppName" type="hidden" value=<%=delAppName%>></input>
<input name="addAppName" type="hidden" value=<%=addAppName%>></input>
<input name="editAppName" type="hidden" value=<%=editAppName%>></input>
<input name="seeAppName" type="hidden" value=<%=seeAppName%>></input>
<input name="tableName" type="hidden" value=<%=formName%>></input>
<input name="recNums" type="hidden" value=<%=recNums%>></input>
<input name="iCount" type="hidden" value=<%=iCount%>></input>     
<input name="pages" type="hidden" value=<%=pages%>></input>     
<input name="rows" type="hidden" value=<%=rows%>></input> 
<input name="catKeyName" type="hidden" value=<%=catKey%>></input>
<input name="findKey" type="hidden" value=<%=findKey%>></input>           
<input name="iStart" type="hidden" value=<%=iStart%>></input>    
<input name="iNowPage" type="hidden" value=<%=iNowPage%>></input>
<input name="context" type="hidden" value=<%=context%>></input>
<%
for(Integer i=0;i<parameters.size();i++){    
   String reservParam=(String)parameters.elementAt(i);
   String reservParamValue=(String)request.getAttribute(reservParam);
   reservParamValue=(reservParamValue==null)?"":reservParamValue;
%>    
   <input name="<%=reservParam%>" type="hidden" value=<%=reservParamValue%>></input>
<%
}
%>




    