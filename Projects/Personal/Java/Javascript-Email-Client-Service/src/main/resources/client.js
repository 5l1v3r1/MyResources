/*================================================================
					author		: Joseph Appeah
					date  		: 07/05/2016
					description : Javascript Email Client
					Frameworks	: Javascript, jquery, ajax
================================================================*/

$.getScript("jquery.jcryption.3.0.1.js");
/*

*/
var sendEmail = class {

	var sender, recepient, message, client, password, attachment, subject;
	var url="http://localhost:8080/v1/emailclient";

	function setSender(sender){
		this.sender = sender;
	}

	function setRecepient(recepient){
		this.recepient = recepient;
	}

	function setMessage(message){
		this.message = message;
	}

	function setAttachment(attachment){
		this.attachment = attachment;
	}

	function setSubject(subject){
		this.subject = subject;
	}

	function setPassword(password){
		this.password = password;
	}

	function setHost(host){
		this.client = host;
	}

	function send(){
		$.post(url, 
			{ 
				sender: sender, 
				recepient: recepient, 
				password: password, 
				host: client, 
				subject: subject,
				attachment: attachment,
				message: message
			}
			).done(function(result) {
    			return result.responseText;
  			});
	}
}

function sendEmail(sender,password,recepient,message){

}

function sendEmail(sender,password,recepient,message,attachment){

}

function sendEmail(sender,password,recepient,message,host){

}

function sendEmail(sender,password,recepient,message,attachment,host){

}



