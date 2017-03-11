$(document).ready(function(){
$("input[type='checkbox']").css("width","24px");
a="li-ajax-table.f1j";
var c = $("form").serialize();
       //  alert(c);
       // alert(decodeURIComponent(c));
     $('#switcher').themeswitcher();
     $("#jsddm1").typemenu();
     $(".typemenu").menu();	
	//$( "input:submit, a, button").button();
	//$( "input:submit, a, button").with(200);
	//$( "input:submit, a, button").height(20);	
	//$( "a", ".demo" ).click(function() { return false; });
	$("#btn-more").click(function(){
	alert("============");
    $.post(a,
    {
     v:"T_Product",
     city:"Duckburg"
    },
     function(data,status){
     //alert("Data: " + data + "\nStatus: " + status);
	 $("#tab").append(data);
	  $("#jsddm1").typemenu();
     $table_=$(data);
	// $trs=$table_.children();
	 $trs=$("#tab").children();
	 alert($trs.length);
	 $table_.addClass("odd");
	 $table_.addClass("even");
	 
	 
	 $(".typemenu").menu();		 
    });
   });	
});


