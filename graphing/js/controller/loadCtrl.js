angular
  .module('app')
  .controller('loadCtrl', ['$scope', 'comparisonData', function($scope, comparisonData) {
    $scope.dataTable = "";
    $scope.stdTable = "";
    $scope.headings = [];
    $scope.hideData = true;
    $scope.hideStd = true;
    $scope.runs = ""

    // Method to load a statistics file

    $scope.loadStats = function(element) {
      let file = element.files[0];
      let textType = /text.*/;
      if (file.type.match(textType)) {
        $scope.headings = [];
        comparisonData.reset(); 
        let reader = new FileReader();
        reader.onload = function(e) {
          $scope.dataTable = reader.result;
          let headings = $scope.dataTable.split("\n")[0].split(",");
          for (i in headings) {
            $scope.headings.push({"heading" : headings[i], "assignment" : "Ignore"});
          }
          $scope.hideData = false;
          comparisonData.setDataTable($scope.dataTable);
          $scope.$apply();
        }
        reader.readAsText(file); 
      } else {
        alert("File not supported.");
      }
    };

    // Method to load a configuration file

    $scope.loadConfig = function(element) {
      let file = element.files[0];
      let textType = /text.*/;
      if ($scope.headings.length == 0) {
        alert("Please load a statistics file first.");
      } else if (file.type.match(textType)) {
        let reader = new FileReader();
        reader.onload = function(e) {
          let config = (reader.result.split("\n")[0].split(","));
          for (i in $scope.headings) {
            let assigned = config[i]
            $scope.headings[i].assignment = assigned;
            if (assigned == "Ignore") {
              comparisonData.setIgnore(i);
            } else if (assigned == "Package") {
              comparisonData.setPackage(i);
            } else if (assigned == "Algorithm") {
              comparisonData.setAlgorithm(i);
            } else if (assigned == "Parameter") {
              comparisonData.addParameter(i);
            } else if (assigned == "Statistic") {
              comparisonData.addStatistic(i);
            } else if (assigned == "Time") {
              comparisonData.setTime(i);
            }
          }
          $scope.$apply();
        }
        reader.readAsText(file); 
      } else {
        alert("File not supported.");
      }
    };

    // Method to load a standard deviation file

    $scope.loadStd = function(element) {
      let file = element.files[0];
      let textType = /text.*/;
      if (file.type.match(textType)) {
        let reader = new FileReader();
        reader.onload = function(e) {
          $scope.stdTable = reader.result;
          let headings = $scope.stdTable.split("\n")[0].split(",");
          let valid = true;
          if (headings.length != $scope.headings.length) {
            valid = false;
          } else {
            for (i in headings) {
              if ($scope.headings[i].heading != headings[i]) {
                valid = false;
              }
            }
          }
          if (valid) {
            $scope.hideStd = false;
            comparisonData.setStdTable($scope.stdTable);
            $scope.$apply();
          } else {
            alert("File does not match statistics file.");
          }
        }
        reader.readAsText(file); 
      } else {
        alert("File not supported.");
      }
    };

    // Method for updating the comparison data when an assignment is changed

    $scope.assignmentChanged = function(element) {
      let assigned = element.heading.assignment;
      let heading = element.heading.heading;
      let index = 0;
      for (i in $scope.headings) {
        if ($scope.headings[i].heading == heading) {
          index = i;
          break;
        }
      }
      if (assigned == "Ignore") {
        comparisonData.setIgnore(index);
      } else if (assigned == "Package") {
        comparisonData.setPackage(index);
      } else if (assigned == "Algorithm") {
        comparisonData.setAlgorithm(index);
      } else if (assigned == "Parameter") {
        comparisonData.addParameter(index);
      } else if (assigned == "Statistic") {
        comparisonData.addStatistic(index);
      } else if (assigned == "Time") {
        comparisonData.setTime(index);
      }
      if (["Package", "Algorithm", "Time"].includes(assigned)) {
        for (i in $scope.headings) {
          if ($scope.headings[i].heading != heading && $scope.headings[i].assignment == assigned) {
            $scope.headings[i].assignment = "Ignore";
          }
        }
      }
    };

    // Method for updating the number of runs

    $scope.runsChanged = function() {
      comparisonData.setRuns($scope.runs);
    }

    // Method for displaying help

    $scope.help = function() {
      alert('Load a statistics file by clicking the "Load Stats" button to the left.  You will need to associate each column heading of the statistics file with a column type either by using the dropdown boxes which appear below, or by clicking "Load Config" to load a configuration file.  Additionally, 95% confidence intervals will be plotted if you upload a standard deviation file by clicking "Load Std" and enter the number of runs your statistics are averaged over in the "Number of Runs" box. For examples of how these file should be formated, please download the example files.  Click "Plot" in the upper right-hand corner to begin plotting results.');
    };

  }]);