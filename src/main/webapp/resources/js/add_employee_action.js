	function checkPasswordMatch(tip, p1, p2) {
  //  var password = $("#txtNewPassword").val();
  //  var confirmPassword = $("#txtConfirmPassword").val();

		if (p1.val() != p2.val()){
			p2.addClass( "ui-state-error" );
			updateTips(tip, "Passwords must be the same!");
			return false;
		}
		else{
			$("#divCheckPasswordMatch").html("Passwords match.");
			return true;
		}
	}
	
	function updateTips( tip, t ) {
      tip
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tip.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
	
	function checkLength( tip, o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( tip, "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
 
	function checkRegexp( tip, o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( tip, n );
        return false;
      } else {
        return true;
      }
    }

	function submitFormViaAjax(){
       $.ajax({
			type: "POST",
		//    contentType: "application/json; charset=utf-8",	// default is 'application/x-www-form-urlencoded; charset=UTF-8', if you want send data as JSON change it - i think
			dataType: "json",     // expected data type in return
			url: "your controller url",
		//	data: "{'data1':'" + value1+ "', 'data2':'" + value2+ "', 'data3':'" + value3+ "'}",	// json data   
			data: $("#form").serialize(),	// normal data
			success: function (data) {
			   // logic, on success
			}
		});
	}
	
function initAddButton(address){
	// ADD ACTION
	var dialog, form,
 
      emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
	  firstName = $( "#firstName" ),
	  lastName = $( "#lastName" ),
	  phoneNumber = $( "#phoneNumber" ),
	  email = $( "#email" ),
	  roles = $( "#rolesSet" ),
      password = $( "#password" ),
	  repassword = $( "#repassword" ),
	  createUserButton = $( "#create-user" ),
	  allFields = $( [] ).add( firstName ).add( lastName ).add( phoneNumber ).add( email ).add( password ).add( repassword ),
      tips = $( ".validateTips" );
 
    
	function addUser() {
      var valid = true;
      allFields.removeClass( "ui-state-error" );
 
      valid = valid && checkLength( tips, firstName, "firstname", 3, 16 );
	  valid = valid && checkLength( tips, lastName, "lastname", 3, 16 );
	  valid = valid && checkLength( tips, phoneNumber, "phone number", 3, 16 );
      valid = valid && checkLength( tips, email, "email", 6, 80 );
      valid = valid && checkLength( tips, password, "password", 5, 16 );
 
      valid = valid && checkRegexp( tips, firstName, /^[a-z]([0-9a-z_\s])+$/i, "Firstname may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
      valid = valid && checkRegexp( tips, lastName, /^[a-z]([0-9a-z_\s])+$/i, "Lastname may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
	  valid = valid && checkRegexp( tips, email, emailRegex, "eg. ui@jquery.com" );
      valid = valid && checkRegexp( tips, password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
		
	  valid = valid && checkPasswordMatch(tips, password, repassword);
	  
      if ( valid ) {
	  // ajax call -> controller get data -> zapis do bazy -> on success reload page
	  
		console.log("valid. saving...");
	  
		var datas = form.serialize();
		console.log("data = " + datas);
		
		var urls = createUserButton.attr("value");
		console.log("url = " + urls);
		
		  $.ajax({
				type: "POST",
				url: urls,
				data: datas,	
				success: function (data) {
				   // logic, on success
					var redirectLocation = $("#navigation_left ul li.employees a").attr('href');
					console.log("redirect location = " + redirectLocation + "?currentPage="+currPage);
				
					$.ajax
					({ url: redirectLocation,
					   success: function(html) {
						$("#ajax-content").empty().append(html);
						}
					});
				}
			}); 
		
        dialog.dialog( "close" );
      }
      return valid;
    }
    
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 500,
      width: 350,
      modal: true,
      buttons: {
        "Create an account": addUser,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" );
      }
    });
 
    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
      addUser();
	//  addUser(this);
    });
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
	//  return false;
    });
}

