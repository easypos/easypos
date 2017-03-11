<%@ page contentType="text/html;charset=gb2312"%>
<TABLE width="100%" aabgcolor1="#B1C8FD" bgcolor="#ffffff" border="0"
	cellspacing="1" cellpadding="0">
	<TR>
		<TD WIDTH="2%" class="td1">行</TD>
		<TD WIDTH="2%" class="td1">选</TD>
		<TD class="td1" WIDTH="2%">改</TD>
		<TD class="td1" WIDTH="2%">查</TD>
		<%
			int idPos = 0;
			for (int i = 0; i < titleList.size(); i++) {
				FormItemBean formItemBean = (FormItemBean) titleList
						.get(new Integer(i));
				String inList = formItemBean.getInList();
				if (inList == null)
					inList = "";
				if (!inList.equals("1")) {
					String tmp = formItemBean.getTitle().trim();
					if (tmp.equals("序号") || tmp.toLowerCase().equals("id")) {
						idPos = i;
					} else {
		%>
		<TD class="td1"><%=tmp%></TD>
		<%
			}
				}
			}
		%>
	</TR>
	<%
		for (int i = 0; i < viewDatas.length; i++) {
			String id = ids[i];
	%>
	<tr class=tr1 name=tr<%=i+1%> id=tr<%=i+1%> onmouseover="tr1(this,<%=i+1%>)" onmouseout="tr2(this,<%=i+1%>)"
		title="用鼠标单击可打开">
		<td class="td1_" height="22"><%=(new Integer(rows).intValue())
						* (new Integer(iNowPage).intValue() - 1) + i + 1%>
		</td>
		<td><input type=checkbox name=tmpDelete <%=i+1%> id=tmpDelete
			<%=i+1%> value=<%=id%> onclick="selectCheck(this,<%=i+1%>)"></td>
		<td>
		<%
			if (viewEdit.equals("1")) {
		%> <img src="/jbpm/images/editicon.gif"
			style="cursor: hand;" target="newwin" onclick="edit(<%=id%>)">
		<%
			}
		%>
		</td>
		<td>
		<%
			if (viewSee.equals("1")) {
		%> <img src="images/viewicon.gif"
			style="cursor: hand;" target="newwin" onclick="see('<%=id%>')">
		<%
			}
		%> </img></td>
		<!-- 数据 -->
		<%
			for (Integer j = 0; j < new Integer(colCounts).intValue(); j++) {
					//System.out.println("i:"+i+"j"+j+"___"+viewDatas[i][j]);				
                              if (indexList.contains(j.toString())){ continue;}
                              String s = viewDatas[i][j];
					if (!(j == idPos)) {
						if (s == null || s.equals("?") || s.equals("")
								|| s.equals("null")) {
							s = "-";
						} else {
							//s = com.sid.util.StringUtils_.escapeHTMLTags(s);
						}
		%>
		<td><%=s%></td>
		<%
			}
				}
		%>
	</tr>
	<%
		}
	%>
</table>
<!-- end data  -->
