angular
  .module('app', [
    'ui.router',
    'ui.bootstrap',
    'ngAnimate',
    'ngSanitize'
  ])
  .config(['$urlRouterProvider', '$stateProvider', function($urlRouterProvider, $stateProvider) {
    $urlRouterProvider.otherwise('/load');
    $stateProvider
      .state('load', {
        url: '/load',
        templateUrl: 'view/load.html',
        controller: 'loadCtrl'
      })
      .state('plot', {
        url: '/plot',
        templateUrl: 'view/plot.html',
        controller: 'plotCtrl'
      })
  }])