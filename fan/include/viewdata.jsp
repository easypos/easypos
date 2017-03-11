<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="com.f1jeeframework.model.FormItemBean"%>
<%@ page import="com.f1jeeframework.view.ViewExpandAction"%>
<%@ page import="com.f1jeeframework.view.TypeBean"%>
<%@ page import="com.f1jeeframework.util.*"%>
<%@ page import="java.util.*"%>
<%
HashMap hashMap =(HashMap)request.getAttribute("hashMap");
List titleList=(List)hashMap.get("titleList");
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
String sFormName=(String)hashMap.get("sFormName");
String colCounts=(String)hashMap.get("colCounts");
String rows=(String)hashMap.get("rows");
String rowCounts=(String)hashMap.get("rowCounts");
String iStart=(String)hashMap.get("iStart");
String pages=(String)hashMap.get("pages");
String jumps=(String)hashMap.get("jumps");
String recNums=(String)hashMap.get("recNums");
String iNowPage=(String)hashMap.get("iNowPage");
String iCount=(String)hashMap.get("iCount");
Vector indexList=(Vector)hashMap.get("indexList");
String[][]  viewDatas=(String[][])hashMap.get("viewDatas");
String[]  ids=(String[])hashMap.get("ids");  
String context =request.getParameter("context");
%>  
<form name="ViewForm" id="ViewForm" method="post">
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
<div class="forum-container">
	<TABLE id="list-table">
		<tbody>
			<TR>
				<Th width="24px">
					<input type=checkbox  class="checkbox" name="CheckedAll" id="CheckedAll">
				</Th>				
				<%
				if (viewEdit.equals("1")) {
			%>
				<%
				}
			%>
				<%
				int idPos = 0;
				for (int i = 0; i < titleList.size(); i++) {
					FormItemBean formItemBean = (FormItemBean) titleList.get(i);
					String inList = formItemBean.getInList();
					if (inList == null)
						inList = "";
					if (!inList.equals("1")) {
						String tmp = formItemBean.getTitle().trim();
			%>
				<Th><%=tmp%></Th>
				<%
				}
				}
			%>		
             <Th width="80px">
					操作
				</Th>			
			</TR>
		</tbody>
		<tbody id="tab">
			<%
			for (int i = 0; i < viewDatas.length; i++) {
				String id = ids[i];
		%>
			<tr name=tr<%=i + 1%> id=tr<%=i + 1%>>
				<!--
		<td width="30px"><%=(new Integer(rows).intValue())
						* (new Integer(iNowPage).intValue() - 1) + i + 1%>
		</td>
                -->
				<td width="20px">
					<input class="checkbox" type=checkbox	name="items" id="checkBox<%=id%>"   value=<%=id%>>
				</td>				
				<!-- 数据 -->
				<%
				for (Integer j = 0; j < new Integer(colCounts).intValue(); j++) {
						//System.out.println("i:"+i+"j"+j+"___"+viewDatas[i][j]);
						String s = viewDatas[i][j];
						if (!indexList.contains(j.toString())) {
						if (s == null || s.equals("?") || s.equals("")
								|| s.equals("null")) {
							s = "-";
						} 
			%>
				<td title='<%=StringUtils.escapeHTMLTags(s)%>'><%=s%></td>
				<%
				}
                               }
			%>
				
			<td width="50px">				
				<ul id="jsddm" class="typemenu">
	<li><a href="#">菜单▼</a>
		<ul>		
		<%  if (viewAdd.equals("1")) { %>        
           <a href="#" name="Add" id="Add"   onclick="add();"/>&nbsp;增加</a> 
                        <% } %>  
	         				<li><a href="#" onclick="edit('<%=id%>')">&nbsp;修改</a>
					<a href="#" onclick="dlgDel('<%=id%>','<%=i + 1%>')">&nbsp;删除</a>
					<a href="#" onclick="see('<%=id%>','<%=i + 1%>')">&nbsp;查看</a>

		</ul>
	</li>
	
</ul>	
				</td> 	
			</tr>
			<%
			}
		%>
		</tbody>
	</table>
</div>
</form>
<!-- end data  -->
