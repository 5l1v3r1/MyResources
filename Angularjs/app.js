
var app  = angular.module('app-1', []);
var app2 = angular.module('app-2', []);
var app3 = angular.module('app-3', []);
var app4 = angular.module('app-4', []);
var app5 = angular.module('app-5', []);
var app6 = angular.module('app-6', []);
var app7 = angular.module('app-7', []);
var app8 = angular.module('app-8', []);

angular.element(document).ready(function() {
	angular.bootstrap($('#application-three'),['app-3']);
	angular.bootstrap($('#application-two'), ['app-2']);
	angular.bootstrap($('#application-four'),['app-4']);
	angular.bootstrap($('#application-five'),['app-5']);
	angular.bootstrap($('#application-six'),['app-6']);
	angular.bootstrap($('#application-seven'),['app-7']);
	angular.bootstrap($('#application-eight'),['app-8']);
});