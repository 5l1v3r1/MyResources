/*=====================================================================================
                                author       : Joseph Appeah
								date         : 07/06/2016
								description  : javascript email client
								frameworks   : javascript
=====================================================================================*/

var jsec_sender, jsec_password, jsec_recepient, jsec_subject, jsec_message, jsec_client;
var jsec_attachment;

var email = {
	/*
		check that CORS headers are set
		source: 
	*/
	createCORSRequest: function(method, url){
		var xhr = new XMLHttpRequest();
		if ("withCredentials" in xhr){
			xhr.open(method, url, false);
		} else if (typeof XDomainRequest != "undefined"){
			xhr = new XDomainRequest();
			xhr.open(method, url);
		} else {
			xhr = null;
		}
		return xhr;
	},
	
	/*
		executes final send action
	*/
	send: function(){
		var xhr = email.createCORSRequest('POST','http://localhost:8080/v1/js-email-client');
		if(xhr){
				xhr.onload = function(){
					result = xhr.responseText;
				}
		}
		xhr.send(email.getRequestBody());
	},
	
	/*
		set sender email address. e.g. abc@hostname.com
		type: string
	*/
	setSender: function(sender){
		if(email.isValidEmail(sender)){
			jsec_sender = sender;
		}else {
			throw "Invalid sender email address"
		}
	},
	
	/*
		set recepient email address. e.g. abc@hostname.com
		type: string
	*/
	setRecepient: function(recepient){
		if(email.isValidEmail(recepient)){
			jsec_recepient = recepient;
		}else{
			throw "Invalid recepient email address"
		}
	},
	
	/*
		set sender email account password
		type: string
	*/
	setPassword: function(password){
		jsec_password = password;
	},
	
	/*
		set email subject
		type: string
	*/
	setSubject: function(subject){
		jsec_subject = subject;
	},
	
	/*
		set email message
		type: string
	*/
	setMessage: function(message){
		jsec_message = message;
	},
	
	/*
		set email account host. if not gmail/yahoo/hotmail
		type: string
	*/
	setHost: function(client){
		jsec_client = client;
	},
	
	/*
		set email attachment
		type: file
	*/
	setAttachment: function(attachment){
		var reader = new FileReader();
		var blob = new Blob([attachment]);
		reader.onload = function(){
			jsec_attachment = this.result;
        }
		reader.readAsBinaryString(blob);
	},
	
	setParameters: function(sender, passphrase, recepient, message, subject, client, attachment){
		jsec_sender = sender;
		jsec_password = passphrase;
		jsec_recepient = recepient;
		jsec_subject = subject;
		jsec_message = message;
		jsec_host = client;
		jsec_attachment = btoa(attachment);
	},

	/*
		validate email address
	*/
	isValidEmail: function(email) {
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	},
	
	/*
		generate request body
	*/
	getRequestBody: function(){
		if(jsec_sender === null || jsec_sender.length === 0) throw "Invalid sender email address. try email.setSender('abc@hostname');"
		if(jsec_recepient === null || jsec_sender.length === 0) throw "Invalid recepient email address. try email.setRecepient('abc@hostname');"
		if(jsec_password === null || jsec_sender.length === 0) throw "No sender account password set. try email.setPassword('password');"
		
		return email = JSON.stringify( { 
			"sender"     : jsec_sender, 
			"recepient"  : jsec_recepient,
			"password"   : jsec_password,
			"subject"    : jsec_subject,
			"message"    : jsec_message,
			"attachment" : jsec_attachment,
			"host"       : jsec_client
		} )
	}
}

function emai(){
	var email_ = email;
	return email_;
}