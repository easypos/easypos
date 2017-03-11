    $(document).ready(function(){       	
	// 推荐产品
	$(".btn-set-product").click(function(){	
	status=$(this).attr("status");
	msg=$(this).attr("msg");
	cmd=$(this).attr("cmd");
    tmp=window.confirm(msg+"，要继续吗？");		
    //alert(cmd+"?id="+str+"&status="+status);	
    if (tmp ==false)
    {
        return;
    }		
		//var str="你选中的是：\r\n";		
		var str="";		
		$('[name=items]:checkbox:checked').each(function(){
			//str+=$(this).val()+"\r\n";
			str+=$(this).val()+",";
		})
		//alert(str);
		$( "#loading").dialog( "open" );
		jQuery.ajax({
              type:'GET',
			  dataType:'string',
              url:cmd+"?id="+str+"&status="+status,			
              cache:false,   			  
			  error:function(data){	              			  
			  //$("#errInfo").info("操作没有完成!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("操作完成!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});		
  });
  