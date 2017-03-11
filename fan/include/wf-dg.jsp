<div title="用户" id="DlgUser" style="width:420px;height:160px;padding:10px">   
   <li>选择需要分派的用户</li>      
   <li><input name="$$Users" id="Users" size="40" readonly style="font:12pt;height:24px"><a href="#" class="easyui-linkbutton" id="btn-user" >.</a></li>   
   </div>
<div title="会签" id="DlgCycle" style="width:420px;height:380px;padding:20px">   
   <li>普通成员</li>      
   <li><input name="CycleUser" id="CycleUser" size="40" readonly style="font:12pt;height:24px"><a href="#" class="easyui-linkbutton" id="btn-cycle-user" >.</a></li>
   <li>管理成员</li>   
   <li><input name="CycleOwner" id="CycleOwner" size="40" readonly style="font:12pt;height:24px"><a href="#" class="easyui-linkbutton" id="btn-cycle-owner" >.</a></li>   
   </div>
  <div title="跳转" id="DlgJump" style="width:420px;height:380px;padding:20px">
  <li>选择任务</li>
  <li><html:wfSelectJump id="JumpTask_" size="10"/>  </li>
  <li>选择用户</li>
  <li><input name="jumpUser_" style="width:300px;height:24px;" id="jumpUser_" readonly>
  <a href="#" class="easyui-linkbutton" id="btn-jump-user" >选择</a>
  </li>
  </div>
  <div title="任务" id="DlgDecide">
  <html:wfSelectDecide id="DecideTask_" size="10"/> 
  </div>