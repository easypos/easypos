<%@ page contentType="text/html;charset=gb2312"%>
<!--  wait msg -->
<div id="sending" style="position:absolute; top:200;left:1; z-index:0; visibility:hidden">
    <table width="100%" height="50" border="0" cellspacing="6" cellpadding="0" class="form">
        <tr>
            <td bgcolor="#FFFFCC" align="center">
                <div align="center">
                    ���Ժ�...
                </div>
            </td>
        </tr>
    </table>
</div>

<!-- border -->



<TABLE width='100%' border="0" cellspacing="0" bgcolor="#B1C8FD" cellpadding="0">
    <TR>
        <TD bgcolor="#ffffff" width="100%" valign="top">
            <TABLE width='100%' border="0" cellspacing="0" cellpadding="0">
                <TR>
                    <!-- <TD height="22" class="path" background="images/post/a2.gif"> -->
                    <TD height="22" class="td1">  
                        bbs-><%=showName%>
                    </TD>
                </TR>
            </TABLE>
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="100%" height="28">                                                            
                        ��ʾ����
                        <select name=Rows class=formselect type=combox 
                                onchange="selectRow()">
                            <option selected>
                                <%=rows%>
                                </option>
                            <option>15</option>
                            <option>
                                20
                            </option>
                            <option>
                                40
                            </option>                                        
                        </select>
                        ��ת��
                        <select name=jump class=formselect type=combox 
                                onchange="jumpPage()">
                            <% 
                            for(int i=0;i<new Integer(pages).intValue();i++) {  
                            %> 
                            <option><%=i%></option>
                            <% } %> 
                        </select>
                        <input type=text value="" name="SearchInput">
                        <input type=button  value="��" name="Add"  onclick="find();">
                                        <%  if (viewAdd.equals("1")) { %> 
                        <input type=button  value="����" name="Add" id="Add"     onclick="add();">      
                        <% } %>  
                        <%  if (viewDel.equals("1")) { %>    
                        <input type=button  value="ɾ������" name="Del" id="Del" disabled=true  onclick="del();">                     
                        <% } %> 
                    </td>
                </tr>
                <tr>
                    <td height="4" background="images/post/ss.gif"></td>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="1" cellpadding="0">
                <tr align="center">
                    <td height="20" class="td2">
                        <input name=iRows type=hidden value=<%=rowCounts%>>
                                                                           ÿҳ�����ʾ<%=rows%>��,��ǰ�ǵ�<%=iNowPage%>ҳ,��<%=pages%>ҳ,��<%=rowCounts%>����¼
                                                                           <input name=iPage type=hidden>
                        <a href="#" name="home" onclick="homePage();">��ǰ</a>
                        <a href="#" name="last" onclick="lastPage();">��ҳ</a>
                        <a href="#" name="next" onclick="nextPage();">��ҳ</a>
                        <a href="#" name="end"  onclick="endPage();">���</a>                      
                        
                    </td>
                </tr>
            </table>
            <TABLE width="100%" aabgcolor1="#B1C8FD" bgcolor="#ffffff" border="0" 
                   cellspacing="0" cellpadding="0">                
                <% for (int i = 0; i < viewDatas.length; i++) {
                        String id=ids[i]; 
                %> 
                <tr class=tr1 name=tr<%=i+1%> id=tr<%=i+1%>>
                  <TABLE width="100%" aabgcolor1="#B1C8FD" bgcolor="#ffffff" border="0" 
                   cellspacing="1" cellpadding="0">
                     <tr class=tr1 >
                    <% 
                      String s0 = viewDatas[i][0];                      
                      String s1 = viewDatas[i][1];
                      String s2 = viewDatas[i][2];                     
                      String s3 = viewDatas[i][3];                                              
                      String s4 = viewDatas[i][4];                      
                      String s5 = viewDatas[i][5];                      
                    %>
                    <td  class=td1>
                        <input type=checkbox name=tmpDelete<%=i+1%> id=tmpDelete<%=i+1%>  value= <%=id%>
                           onclick="selectCheck(this,<%=i+1%>)" >                                        
                       </td>
                    <td  class=td1>
                      ����:<%=s2%><span class="reply_title" style="FLOAT: right;"> ������:<%=s3%>  </span>                        
                    </td>                                           
                    </tr>                                       
                    <tr class=tr1 >
                      <td valign="top">
                       <div><%=s1%></div><div><%=s0%></div>                       
                      </td>
                      <td width="100%" valign="top"><%=s5%></td> 
                    </tr>   
                     <tr class=tr1 >
                      <td valign="top"><%=(new Integer(rows).intValue())*(new Integer(iNowPage).intValue()-1)+i+1%>¥</td>
                      <td width="100%" valign="top">
                        <%  if (viewEdit.equals("1")) { %> 
                        <img src="/jbpm/images/editicon.gif" border=0 onclick="edit(<%=id%>)" target=newwin>�޸�</a>
                        <% } %>                          
                       </td> 
                    </tr>     
                    </table>
                </tr>                                                     
                <% } %>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <TR>
                        <TD >
                            <input type=checkbox  value="ȫѡ" name= selectCheckBox onclick="selectCheckBox_(this,'<%=recNums%>');refreshButton('<%=recNums%>','<%=iCount%>');"> ȫѡ
                        </TD>
                    </TR>
                </table>    
            </TABLE>
            <!-- ������ͼ�߶� -->
        </TD>
    </TR>
</TABLE>
<!-- <div style="position:absolute; left:0px; bottom:0px"> -->
<TABLE width="100%">
    <TR align="center">
        <TD class="td2">
            <TABLE width="100%">
                <TR align="center">
                    <TD height="38" >�ҿ���ִ�еĲ���                                         
                        <%
                        for(int i=0 ;i<actionList.size();i++){
                        ViewExpandAction viewAction=(ViewExpandAction)actionList.elementAt(i);
                        String codeName=viewAction.getCodeName();
                        String code=viewAction.getCode();		
                        %>
                        <input type=button  value=<%=codeName%> name=expand<%=i+1%>  id=name=expand<%=i+1%>  disabled=true  onclick="expandAction('<%=code%>')">                  
                        
                        <% } %> 
                    </TD>
                </TR>
            </TABLE>
        </TD>
    </TR>
</TABLE>  

<!--border -->


<SCRIPT>
           initButtons(<%=iNowPage%>,<%=pages%>);
</SCRIPT>   
<!-- /div>   -->
        


