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