
var app   = angular.module('app-1', ['ngAnimate']);
var app2  = angular.module('app-2', ['ngAnimate']);
var app3  = angular.module('app-3', ['ngAnimate']);
var app4  = angular.module('app-4', ['ngAnimate']);
var app5  = angular.module('app-5', ['ngAnimate']);
var app6  = angular.module('app-6', ['ngAnimate']);
var app7  = angular.module('app-7', ['ngAnimate']);
var app8  = angular.module('app-8', ['ngAnimate']);
var app9  = angular.module('app-9', ['ngAnimate']);
var app10 = angular.module('app-10', ['ngAnimate']);

angular.element(document).ready(function() {
	angular.bootstrap($('#application-three'),['app-3']);
	angular.bootstrap($('#application-two'), ['app-2']);
	angular.bootstrap($('#application-four'),['app-4']);
	angular.bootstrap($('#application-five'),['app-5']);
	angular.bootstrap($('#application-six'),['app-6']);
	angular.bootstrap($('#application-seven'),['app-7']);
	angular.bootstrap($('#application-eight'),['app-8']);
	angular.bootstrap($('#application-nine'), ['app-9']);
	angular.bootstrap($('#application-ten'), ['app-10']);
});