/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.setInterval(function(){
    $("#intro-button").addClass('animated bounce');
    window.setTimeout(function(){	
		$("intro-button").removeClass('animated bounce');
    },4000);
},10000);