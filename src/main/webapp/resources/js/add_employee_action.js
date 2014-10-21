var tips;
var allFields;
var password;
var email;
var name;
var emailRegex;

var firstName;
var lastName;
var phoneNumber;
var roles;
var repassword;

	function checkPasswordMatch(p1, p2) {
  //  var password = $("#txtNewPassword").val();
  //  var confirmPassword = $("#txtConfirmPassword").val();

    if (p1.val() != p2.val()){
        p2.addClass( "ui-state-error" );
		updateTips( "Passwords must be the same!");
		return false;
	}
    else{
        $("#divCheckPasswordMatch").html("Passwords match.");
		return true;
	}
}

	function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
 
    function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n );
        return false;
      } else {
        return true;
      }
    }

function initAddButton(address){
	// ADD ACTION
	var dialog, form,
 
      emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
	  firstName = $( "#firstName" );
	  lastName = $( "#lastName" );
	  phoneNumber = $( "#phoneNumber" );
	  email = $( "#email" );
	  roles = $( "#rolesSet" );
      password = $( "#password" );
	  repassword = $( "#repassword" );
    //  allFields = $( [] ).add( name ).add( email ).add( password );
	  allFields = $( [] ).add( firstName).add( lastName).add( phoneNumber).add( email ).add( password ).add( repassword );
      tips = $( ".validateTips" );
 
    
	function addUser() {
      var valid = true;
      allFields.removeClass( "ui-state-error" );
 
      valid = valid && checkLength( firstName, "firstname", 3, 16 );
	  valid = valid && checkLength( lastName, "lastname", 3, 16 );
	  valid = valid && checkLength( phoneNumber, "phone number", 3, 16 );
      valid = valid && checkLength( email, "email", 6, 80 );
      valid = valid && checkLength( password, "password", 5, 16 );
 
      valid = valid && checkRegexp( firstName, /^[a-z]([0-9a-z_\s])+$/i, "Firstname may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
      valid = valid && checkRegexp( lastName, /^[a-z]([0-9a-z_\s])+$/i, "Lastname may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
	  valid = valid && checkRegexp( email, emailRegex, "eg. ui@jquery.com" );
      valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
		
	  valid = valid && checkPasswordMatch(password, repassword);
	  
      if ( valid ) {
	  
	  // ajax call -> controller get data -> zapis do bazy -> on success reload page
	  
		console.log("valid. saving...");
	  
     /*   $( "#users tbody" ).append( "<tr>" +
          "<td>" + name.val() + "</td>" +
          "<td>" + email.val() + "</td>" +
          "<td>" + password.val() + "</td>" +
        "</tr>" ); */
        dialog.dialog( "close" );
      }
      return valid;
    }
    
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 300,
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
    });
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
    });
}

