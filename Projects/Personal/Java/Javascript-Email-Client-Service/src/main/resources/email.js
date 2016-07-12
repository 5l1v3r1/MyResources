/*=====================================================================================
                                author       : Joseph Appeah
								date         : 07/06/2016
								description  : javascript email client
								frameworks   : javascript
=====================================================================================*/


function email(){
	var FD  = new FormData();
	var allfunctions=[];
	
	/*
		encrypt the contents of function call to prevent browser viewing
	*/
	this.encrypt = function(){
        for ( var i in window) {
			if((typeof window[i]).toString()=="function"){
				allfunctions.push(window[i].name);
			}
		}
	};
	
	/*
		check that CORS headers are set
		source: 
	*/
	this.createCORSRequest = function(method, url){
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
	};
	
	
	/*
		executes final send action
	*/
	this.send = function(){
		
		var xhr = email.createCORSRequest('POST','http://localhost:8080/v1/js-email-client');
		if(xhr){
				xhr.onload = function(){
					result = xhr.responseText;
				}
		}
		xhr.send(FD);
		
	};
	
	
	/*
		set sender email address. e.g. abc@hostname.com
		type: string
	*/
	this.setSender = function(sender){

		if(email.isValidEmail(sender)){
			FD.append('sender', sender);
		}else {
			throw "Invalid sender email address"
		}
		
	};
	
	
	/*
		set recepient email address. e.g. abc@hostname.com
		type: string
	*/
	this.setRecepient = function(recepient){
		
		if(email.isValidEmail(recepient)){
			FD.append('recepient', recepient);
		}else{
			throw "Invalid recepient email address"
		}
		
	};
	
	
	/*
		set sender email account password
		type: string
	*/
	this.setPassword = function(password){
		
		FD.append('password', password);
		
	};
	
	
	/*
		set email subject
		type: string
	*/
	this.setSubject = function(subject){
		
		FD.append('subject', subject);

	};
	
	
	/*
		set email message
		type: string
	*/
	this.setMessage = function(message){
		
		FD.append('message', message);

	};
	
	
	/*
		set email account host. if not gmail/yahoo/hotmail
		type: string
	*/
	this.setHost = function(client){
		
		FD.append('host', client);

	};
	
	
	/*
		set email attachment
		type: file
	*/
	this.setAttachment = function(attachment){
		
		FD.append('attachment', attachment);

	};
	
	/*
		set all parameters for the client
	*/
	this.setParameters = function(sender, password, recepient, message, subject, attachment, client){

	};
	
	this.setParameters =  function(sender, password, recepient, message, subject){
		
	}

	
	/*
		validate email address
	*/
	this.isValidEmail = function(email) {
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	};
}

var email = new email();