	$(function() {		        
     $('#find').click(function() {
     findUser();
     });
	//�Ƶ��ұ�
	$('#add').click(function() {
	//��ȡѡ�е�ѡ�ɾ����׷�Ӹ��Է�
		$('#select1 option:selected').appendTo('#select2');
	});
	//�Ƶ����
	$('#remove').click(function() {
		$('#select2 option:selected').appendTo('#select1');
	});
	//ȫ���Ƶ��ұ�
	$('#add_all').click(function() {
		//��ȡȫ����ѡ��,ɾ����׷�Ӹ��Է�
             $o=$('#select1 option');
		$o.appendTo('#select2');
                
	});
	//ȫ���Ƶ����
	$('#remove_all').click(function() {
		$('#select2 option').appendTo('#select1');
	});
	//˫��ѡ��
	$('#select1').dblclick(function(){ //��˫���¼�
		//��ȡȫ����ѡ��,ɾ����׷�Ӹ��Է�
		$("option:selected",this).appendTo('#select2'); //׷�Ӹ��Է�
	});
	//˫��ѡ��
	$('#select2').dblclick(function(){
	   $("option:selected",this).appendTo('#select1');
	});
});

function groupUser(id){
   $("#select1").empty();  
   $.post("findGroupUser.do","id="+id,function(data){
      var str="";  
      $(data).find('result').each(function() {                        
                        var user = $(this).text();      
                        $("#select1").append("<option value='"+user+"'>"+user+"</option>");  
                        //$("<option value='"+user+"'>"+user+"</option>").appendTo($("#select1"));                            

                         
                    });                      
                  // $('.msg').text(data).css({"color":"red","background-color":"yellow"});   
                }) 
}

function findUser(id){
   $("#select1").empty();  
   $.post("getUsers.do","username="+$('#userid').val(),function(data){
      var str="";  
      $(data).find('result').each(function() {                        
                        var user = $(this).text();      
                        $("#select1").append("<option value='"+user+"'>"+user+"</option>");  
                        //$("<option value='"+user+"'>"+user+"</option>").appendTo($("#select1"));                            

                         
                    });                      
                  // $('.msg').text(data).css({"color":"red","background-color":"yellow"});   
                }) 
}
