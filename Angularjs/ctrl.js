app.controller('ctrl-one', function($scope){
	$scope.first = 'First';
	$scope.last  = 'Last';
});

app2.controller('ctrl-two', function($scope){
	$scope.entries = ['This','as','a', 'dynamic','list'];
});

app4.controller('ctrl-four', function($scope){
	$scope.items = [];
	$scope.addItem =  function(){
		$scope.items.push($scope.item);
	}
});

app5.controller('ctrl-five', function($scope){
	$scope.items = [];
	$scope.addItem =  function(){
		$scope.items.push($scope.item);
	}
});

app6.controller('ctrl-six', function($scope,$http){
	var result;
	$http({
		method : 'GET',
		url : 'http://localhost:8086/compare_rm_images_service/randomize?env=UAT'
	}).then(
	function(response){
		result = response.data.result;
	});	
	
	$scope.updatelist = function(){
		console.log('run');
		$scope.items = result.CF[$scope.index];
	}
	
});

app7.controller('ctrl-seven', function($scope, $http){
	$http({
		method : 'GET',
		url : 'http://localhost:8086/compare_rm_images_service/randomize?env=UAT'
	}).then(
	function(response){
		$scope.items = response.data.result.CF;
	});	

});

app8.controller('ctrl-eight', function($scope, $http){
	$http({method:'GET',url:'http://localhost:8086/compare_rm_images_service/randomize?env=UAT'}).then(function(response){ $scope.items = response.data.result.CPS});
	
});

app10.controller('ctrl-ten', function($scope){
	$scope.shadow = function(e){
		$scope.xcod = e.clientX;
		$scope.ycod = e.clientY;
		$('#shadow').css('left', e.clientX+"px");
		$('#shadow').css('top', e.clientY+"px");
	}
});