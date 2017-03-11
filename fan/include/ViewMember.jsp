<%@ page contentType="text/html;charset=gb2312"%>
<!--  wait msg -->
<div id="sending" style="position:absolute; top:200;left:1; z-index:0; visibility:hidden">
    <table width="100%" height="50" border="0" cellspacing="6" cellpadding="0" class="form">
        <tr>
            <td bgcolor="#FFFFCC" align="center">
                <div align="center">
                    请稍候...
                </div>
            </td>
        </tr>
    </table>
</div>
<TABLE width='100%' border="0" cellspacing="0" bgcolor="#B1C8FD" cellpadding="0">
    <TR>
        <TD bgcolor="#ffffff" width="100%" valign="top">
            <TABLE width='100%' border="0" cellspacing="0" cellpadding="0">
                <TR>
                    <!-- <TD height="22" class="path" background="images/post/a2.gif"> -->
                    <TD height="22" class="td1">  
                        列表->
                        <%=showName%>
                    </TD>
                </TR>
            </TABLE>                   
            <%@ include file="/include/viewloop.jsp"%>                                   
            <%@ include file="/include/viewdata.jsp"%>                                   
            <%@ include file="/include/viewselect.jsp"%>                                                   
            <!-- 控制视图高度 -->
        </TD>
        </TR>  
    <!-- <div style="position:absolute; left:0px; bottom:0px"> -->
</TABLE>
<SCRIPT>
           initButtons(<%=iNowPage%>,<%=pages%>);
</SCRIPT>   
<!-- /div>   -->
        


