    $(document).ready(function(){       	
	// �Ƽ���Ʒ
	$(".btn-set-product").click(function(){	
	status=$(this).attr("status");
	msg=$(this).attr("msg");
	cmd=$(this).attr("cmd");
    tmp=window.confirm(msg+"��Ҫ������");		
    //alert(cmd+"?id="+str+"&status="+status);	
    if (tmp ==false)
    {
        return;
    }		
		//var str="��ѡ�е��ǣ�\r\n";		
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
			  //$("#errInfo").info("����û�����!");
			  //alert(data);
			  location.reload(); 
			  },			  
			  success:function(data){	
               //delTr();
               //$("#errInfo").info("�������!");			   
			   //alert(data);
			   location.reload(); 
			  }
			  });
		//alert(str);
	});		
  });
  