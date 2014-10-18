var currPage;
var noOfPages;

function hideAllNavigationButtons(){
		$("#firstlink").hide();
        $("#previouslink").hide();
		$("#nextlink").hide();
        $("#lastlink").hide();
}

function displayNavigationButtons(currentPage, numberOfPages){
	if (currentPage != 1){
		$("#firstlink").show();
        $("#previouslink").show();
	} else {
		$("#firstlink").hide();
        $("#previouslink").hide();
	}
	
	if (currentPage < numberOfPages){
		$("#nextlink").show();
        $("#lastlink").show();
	} else {
		$("#nextlink").hide();
        $("#lastlink").hide();
	}
}

function nextlink(){
    currPage += 1;
	console.log("no of pages = " + noOfPages);
	console.log("current page = " + currPage);
    $(".table").empty().append(loading);

    $.ajax
    ({ type: $("#nextlink").attr("method"),
       url: $("#nextlink").attr("action"),
	   data: {currentPage : currPage},
       success: function(html) {
        $(".table").empty().append(html);
            displayNavigationButtons(currPage, noOfPages);
			
        }
    });

    $("#page").text(currPage + " / " + noOfPages);
    return false;
}

function firstlink() {
    currPage = 1;
	console.log("no of pages = " + noOfPages);
	console.log("current page = " + currPage);
    $(".table").empty().append(loading);

    $.ajax
    ({ type: $("#firstlink").attr("method"),
       url: $("#firstlink").attr("action"),
       data: $("#firstlink").serialize(),
       success: function(html) {
        $(".table").empty().append(html);
            displayNavigationButtons(currPage, noOfPages);
        }
    });

    $("#page").text(currPage + " / " + noOfPages);
    return false;
}

function previouslink() {
    currPage = currPage - 1;
	console.log("no of pages = " + noOfPages);
	console.log("current page = " + currPage);
    $(".table").empty().append(loading);

    $.ajax
    ({ type: $("#previouslink").attr("method"),
       url: $("#previouslink").attr("action"),
	   data: {currentPage : currPage}, 
       success: function(html) {
        $(".table").empty().append(html);
            displayNavigationButtons(currPage, noOfPages);
        }
    });

    $("#page").text(currPage + " / " + noOfPages);
    return false;
}

function lastlink() {
    currPage = noOfPages;                       
	console.log("no of pages = " + noOfPages);
	console.log("current page = " + currPage);
    $(".table").empty().append(loading);

    $.ajax
    ({ type: $("#lastlink").attr("method"),
       url: $("#lastlink").attr("action"),
       data: $("#lastlink").serialize(),
       success: function(html) {
        $(".table").empty().append(html);
            displayNavigationButtons(currPage, noOfPages);
        }
    });

    $("#page").text(currPage + " / " + noOfPages);
    return false;
}

function checkNoOfPages(currPage, noOfPages){
    if (currPage == 1){
      $("#firstlink").hide();
      $("#previouslink").hide();
    }
    if (currPage == noOfPages){
      $("#nextlink").hide();
      $("#lastlink").hide();
    }
}

function initPaginationButtons(pages){
  noOfPages = pages;
  currPage = 1;

  console.log("no of pages = " + noOfPages);
  console.log("current page = " + currPage);

  $("#nextlink").on("click", function(e){
      e.preventDefault();
      nextlink();
  });
  $("#firstlink").on("click", function(e){
      e.preventDefault();
      firstlink();
  });
  $("#previouslink").on("click", function(e){
      e.preventDefault();
      previouslink();
  });
  $("#lastlink").on("click", function(e){
      e.preventDefault();
      lastlink();
  });
  
 // hideAllNavigationButtons();
  displayNavigationButtons(currPage, noOfPages);
 // checkNoOfPages(currPage, noOfPages);
}