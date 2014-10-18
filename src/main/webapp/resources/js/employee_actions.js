function deleteEmp(obj , address){
	var conBox = confirm("Are you sure ?");
	if(conBox){
		$.ajax
		({
		   type: $(".delemp").attr("method"),
		   url: $(".delemp").attr("action"),
		   data: $(obj).parent().serialize(),
		   success: function(response) {
			alert(response);
			var redirectLocation = $("#navigation_left ul li.employees a").attr('href');
			console.log("redirect location = " + redirectLocation + "?currentPage="+currPage);
			
			$.ajax
			({ url: redirectLocation,
			   success: function(html) {
				$("#ajax-content").empty().append(html);
				}
			});
			
		//	$(".table").load(address + "?currentPage="+ currPage +" #tabb");
			
		//	window.location.href = redirectLocation;
		//	window.location.reload(true);
	//		$(e.target).closest("tr").remove;
			}
		});
		return false;
	} else {
	//	$(obj).dialog("close");
		return false;
	}
}

function newEmp(obj, address){
	$.ajax
	({ type: $("#newemp").attr("method"),
	   url: $("#newemp").attr("action"),
	   data: $("#newemp").serialize(),
	   success: function(html) {


		}
	});
}

function editEmp(obj, address){
	$.ajax
	({ type: $("#editemp").attr("method"),
	   url: $("#editemp").attr("action"),
	   data: $("#editemp").serialize(),
	   success: function(html) {


		}
	});
}		
		
function initActionButtons(address){

  $(".table").on("click",".delempbtn",function(e) {
	e.preventDefault();
	deleteEmp(this, address);
  });

  $("#newemp").on("click", function(e) {
	e.preventDefault();
	newEmp(this, address);	
  });

	$("#editemp").on("click", function(e) {
	e.preventDefault();
	editEmp(this, address);	
  });
  

}