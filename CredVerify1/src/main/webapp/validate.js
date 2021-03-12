function validatePassword(){
	var password = document.getElementById("password"), confirm_password = document.getElementById("confirm_password");
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
    document.getElementById('confirm_password').style.color = 'red';
  } else {
    confirm_password.setCustomValidity('');
    document.getElementById('confirm_password').style.color = 'black';
  }
}
		
function validateEmail(){
	var email = document.getElementById('email');
	var re = new RegExp(/\w+\.?\w*\@\w+\.\w+\.?\w*/);
	if (re.test(email.value)){
		email.setCustomValidity('');
	}else{
		email.setCustomValidity("Please enter a VALID email!");
	}
}

