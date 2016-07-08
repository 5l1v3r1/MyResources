
$(document).ready(function(){
	resizeFontById('intro-text');
	resizeFontById('info-heading-1');
	resizeFontById('info-description-1');
	resizeFontById('info-heading-2');
	resizeFontById('info-description-2');	
	actionWhenReached('function-1', animateById('function-1','slideInLeft'));
	actionWhenReached('function-2', animateById('function-2','slideInRight'));
});
