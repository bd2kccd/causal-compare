angular
  .module('app')
  .controller('plotCtrl', ['$scope', 'comparisonData', function($scope, comparisonData) {
    $scope.headings = comparisonData.getHeadings();
    $scope.table = comparisonData.getDataTable();
    $scope.packages = [];
    $scope.algorithms = [];
    $scope.parameters = [];
    $scope.statistics = [];
    $scope.plotted = -1;

    // Parmeters outer initalize flags

	for (i in comparisonData.getParameters()) {
    	let index = comparisonData.getParameters()[i];
	   	$scope.parameters.push({"parameter" : index, "values" : []});
    }

    for (i in $scope.table) {

    	// Package/Algorithm initalize flags

	    if (comparisonData.getPackage() != null && comparisonData.getAlgorithm() != null) {
	    	let package = $scope.table[i][comparisonData.getPackage()];
	    	let contains = false;
	    	for (j in $scope.packages) {
	    		if (package == $scope.packages[j].package) {
	    			contains = true;
	    			break;
	    		}
	    	}
	    	if (!contains) {
	   			$scope.packages.push({"package" : package, "algorithms" : []});
			}
			let algorithm = $scope.table[i][comparisonData.getAlgorithm()];
			contains = false;
	    	for (j in $scope.packages) {
	    		if (package == $scope.packages[j].package) {
	    			for (k in $scope.packages[j].algorithms) {
	    				if (algorithm == $scope.packages[j].algorithms[k].value) {
	    					contains = true;
	    					break;
	    				}
	    			}
	    		}
	    	}
			if (!contains) {
				let element = {"value" : algorithm, "active" : false};
	   			$scope.packages[j].algorithms.push(element);
			}
	   	}

	   	// Algorithm initalize flags

	    if (comparisonData.getAlgorithm() != null) {
	    	let algorithm = $scope.table[i][comparisonData.getAlgorithm()];
	    	let contains = false;
			for (j in $scope.algorithms) {
				if (algorithm == $scope.algorithms[j].value) {
					contains = true;
					break;
				}
			}
			if (!contains) {
				let element = {"value" : algorithm, "active" : false};
	   			$scope.algorithms.push(element);
			}
	 	}

	 	// Parameters inner initalize flags

	 	if (comparisonData.getParameters().length > 0) {
	 		for (j in $scope.parameters) {
	 			let value = $scope.table[i][$scope.parameters[j].parameter];
	 			if (value != "-") {
		    		let contains = false;
					for (k in $scope.parameters[j].values) {
						if (value == $scope.parameters[j].values[k].value) {
							contains = true;
							break;
						}
					}
					if (!contains) {
						let element = {"value" : value, "active" : false};
		   				$scope.parameters[j].values.push(element);
					}
				}
	 		}
	 	}
 	}

 	// Statistics initalize flags

    for (statistic in comparisonData.getStatistics()) {
		let element = {"value" : comparisonData.getStatistics()[statistic], "active" : false};
    	$scope.statistics.push(element);
    }

    // Set layouts for plots

    let layout = {barmode: 'group'};

	let timeLayout = {
		xaxis: {
	    	type: 'log'
	   	},
	  	margin: {
	    	l: 300,
	    	r: 20,
	    	b: 50,
	    	t: 20,
	    	pad: 5
	   	}
	};

	// Method for showing package/algorithm

	$scope.showPackage = function() {
		return comparisonData.getPackage()!=null && comparisonData.getAlgorithm()!=null;
	};

	// Method for showing algorithm

	$scope.showAlgorithm = function() {
		return comparisonData.getAlgorithm()!=null && comparisonData.getPackage()==null;
	};

	// Method for showing statistics

	$scope.showStatistics = function() {
		return $scope.statistics.length > 0;
	};

	// Method for plotting the results

    $scope.plot = function() {
    	let selected = 0;
    	let name = "";
    	let toPlot = [];

    	let elapsed = [];
		let timeName = [];
		let timeError = [];

    	if ($scope.plotted == 1) {
    		$scope.plotted = 0;
		}

		// Check if instance meets criteria

    	for (i in $scope.table) {

    		selected = 0;
    		name = "";

    		// Package/Algorithm match

		    if (comparisonData.getPackage() != null && comparisonData.getAlgorithm() != null) {
		    	selected = -1;
		    	let pkg = $scope.table[i][comparisonData.getPackage()];
		    	let alg = $scope.table[i][comparisonData.getAlgorithm()];
		    	for (j in $scope.packages) {
		    		if ($scope.packages[j].package == pkg) {
		    			for (k in $scope.packages[j].algorithms) {
		    				if ($scope.packages[j].algorithms[k].value == alg && $scope.packages[j].algorithms[k].active) {
		    					selected = 0;
		    					name += pkg + " " + alg;
		    					break;
		    				}
		    			}
		    		}
		    		if (selected == 0) {
		    			break;
		    		}
		    	}
		    } else if (comparisonData.getAlgorithm() != null) {
		    	selected = -1;
		    	let alg = $scope.table[i][comparisonData.getAlgorithm()];
		    	for (j in $scope.algorithms) {
		    		if ($scope.algorithms[j].value == alg && $scope.algorithms[j].active) {
		    			selected = 0;
		    			name += alg;
		    			break;
		    		}
		    	}
		    }

		    // Parameters match

		    if (selected == 0) {
			    for (j in comparisonData.getParameters()) {
			    	selected -= 1;
			    	let param = $scope.table[i][comparisonData.getParameters()[j]];
					if (param == "-") {
						selected += 1;
					} else {
				    	for (k in $scope.parameters[j].values) {
				    		if ($scope.parameters[j].values[k].value == param && $scope.parameters[j].values[k].active) {
				    			selected += 1;
				    			name += " " + param;
				    			break;
				    		}
			    		}
			    	}
		    		if (selected < 0) {
		    			break;
		    		}
		    	}
			}

			// Statistics match

			if (selected == 0) {
				let stats = [];
				let results = [];
				let error = []
				for (j in $scope.statistics) {
					if ($scope.statistics[j].active) {
						$scope.plotted = 1;
						stats.push($scope.headings[$scope.statistics[j].value])
						results.push($scope.table[i][$scope.statistics[j].value]);
						if (comparisonData.getRuns() != null && comparisonData.getStdTable().length != 0) {
							let std = comparisonData.getStdTable()[i][$scope.statistics[j].value];
							let n = comparisonData.getRuns()
  							error.push(((std / Math.sqrt(n)) * tdistr(n-1, 0.025)).toFixed(3));
  						}
					}
				}
				let current = null;
				if (error.length != 0) {
					current = {
	     				x: stats,
	     				y: results,
	     				name: name,
	     				type: 'bar',
	     				error_y: {
      						type: 'data',
      						array: error,
      						visible: true
    					}
	    			};
				} else {
					current = {
	     				x: stats,
	     				y: results,
	     				name: name,
	     				type: 'bar'
	    			};
    			}
    			toPlot.push(current);
    			if (comparisonData.getTime() != null) {
	    			elapsed.push($scope.table[i][comparisonData.getTime()]);
	    			timeName.push(name);
					if (comparisonData.getRuns() != null && comparisonData.getStdTable().length != 0) {
						let std = comparisonData.getStdTable()[i][comparisonData.getTime()];
						let n = comparisonData.getRuns()
  						timeError.push(((std / Math.sqrt(n)) * tdistr(n-1, 0.025)).toFixed(3));
  					}
    			}
			}
		}

		// Plot plots if matching criteria

  		if ($scope.plotted == 1) {

  			Plotly.newPlot('statisticsResults', toPlot, layout);

  			if (comparisonData.getTime() != null) {
  				let toTime = null;
  				if (timeError.length != 0) {
  					toTime = {
		     			x: elapsed,
		     			y: timeName,
		     			orientation: 'h',
		     			type: 'bar',
	     				error_x: {
      						type: 'data',
      						array: timeError,
      						visible: true
    					}
		    		}
  				} else {
		    		toTime = {
		     			x: elapsed,
		     			y: timeName,
		     			orientation: 'h',
		     			type: 'bar'
		    		}
	    		}
  				Plotly.newPlot('timeResults', [toTime], timeLayout);
			}

		// Remove plots if no matching criteria

  		} else if ($scope.plotted == 0) {
  			Plotly.purge('statisticsResults');
  			if (comparisonData.getTime() != null) {
  				Plotly.purge('timeResults');
			}
  		}
    };

  }]);
