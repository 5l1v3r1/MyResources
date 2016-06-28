
var app  = angular.module('app', []);
var app2 = angular.module('app-2', []);
var app3 = angular.module('app-3', []);
var app4 = angular.module('app-4', []);
var app5 = angular.module('app-5',[]);

angular.element(document).ready(function() {
	angular.bootstrap($('#application-three'),['app-3']);
	angular.bootstrap($('#application-two'), ['app-2']);
	angular.bootstrap($('#application-four'),['app-4']);
	angular.bootstrap($('#application-five'),['app-5'])
});