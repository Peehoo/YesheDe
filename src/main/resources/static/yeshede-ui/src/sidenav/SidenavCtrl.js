'use strict';

angular.module('starterApp')
  .controller('SidenavCtrl', function ($mdSidenav) {

  this.openSideNavPanel = function() {
      $mdSidenav('left').open();
    }
});
