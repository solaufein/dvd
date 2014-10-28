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
	
function initDeleteButton(address){
  // DELETE ACTION
  $(".table").on("click",".delempbtn",function(e) {		// oddelegowanie .delempbtn - za kazdym razem szuka w .table przycisku o tej klasie
	e.preventDefault();
	deleteEmp(this, address);
  });
}