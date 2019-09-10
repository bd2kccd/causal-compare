angular
  .module('app')
  .factory('comparisonData', function() {

        // Initializer

        function Data() {
            this.dataTable = [];
            this.stdTable = [];
            this.headings = [];
            this.package = null;
            this.algorithm = null;
            this.time = null;
            this.runs = null;
            this.parameters = [];
            this.statistics = [];
        };

        // Setters

        Data.prototype.setDataTable = function(data) {
            data = data.split("\n");
            this.headings = data[0].split(",");
            for (i in data) {
                if (i != 0 && data[i] !== "") {
                    this.dataTable.push(data[i].split(","));
                }
            }
        };

        Data.prototype.setStdTable = function(data) {
            data = data.split("\n");
            for (i in data) {
                if (i != 0 && data[i] !== "") {
                    this.stdTable.push(data[i].split(","));
                }
            }
        };

        Data.prototype.setIgnore = function(column_index) {
            this.setToNull(column_index);
            this.removeFromList(column_index);
        };

        Data.prototype.setPackage = function(column_index) {
            this.setToNull(column_index);
            this.removeFromList(column_index);
            this.package = column_index;
        };

        Data.prototype.setAlgorithm = function(column_index) {
            this.setToNull(column_index);
            this.removeFromList(column_index);
            this.algorithm = column_index;
        };

        Data.prototype.setTime = function(column_index) {
            this.setToNull(column_index);
            this.removeFromList(column_index);
            this.time = column_index;
        };

        Data.prototype.setRuns = function(runs) {
            this.runs = runs;
        };

        Data.prototype.addParameter = function(column_index) {
            this.setToNull(column_index);
            this.removeFromList(column_index);
            this.parameters.push(column_index);
        };

        Data.prototype.addStatistic = function(column_index) {
            this.setToNull(column_index);
            this.removeFromList(column_index);
            this.statistics.push(column_index);
        };

        // Getters

        Data.prototype.getHeadings = function() {
            return this.headings;
        };

        Data.prototype.getDataTable = function() {
            return this.dataTable;
        };

        Data.prototype.getPackage = function() {
            return this.package;
        };

        Data.prototype.getStdTable = function() {
            return this.stdTable;
        };

        Data.prototype.getAlgorithm = function() {
            return this.algorithm;
        };

        Data.prototype.getTime = function() {
            return this.time;
        };

        Data.prototype.getRuns = function() {
            return this.runs;
        };

        Data.prototype.getParameters = function() {
            return this.parameters;
        };

        Data.prototype.getStatistics = function() {
            return this.statistics;
        };

        // Auxillary Functions

        Data.prototype.setToNull = function(column_index) {
            if (this.package == column_index) {
                this.package = null;
            } else if (this.algorithm == column_index) {
                this.algorithm = null;
            } else if (this.time == column_index) {
                this.time = null;
            }
        };

        Data.prototype.removeFromList = function(column_index) {
            let index = this.parameters.indexOf(column_index);
            if (index > -1) {
                this.parameters.splice(index, 1);
            }
            index = this.statistics.indexOf(column_index);
            if (index > -1) {
                this.statistics.splice(index, 1);

            }
        };

        Data.prototype.reset = function() {
            this.dataTable = [];
            this.stdTable = [];
            this.headings = [];
            this.package = null;
            this.algorithm = null;
            this.time = null;
            this.runs = null;
            this.parameters = [];
            this.statistics = [];
        };

        return new Data();

    });
