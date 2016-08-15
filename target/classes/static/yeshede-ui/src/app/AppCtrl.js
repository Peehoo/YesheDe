'use strict';

angular.module('starterApp')
  .controller('AppCtrl', function ($scope, $mdToast, $mdSidenav,//
                                   $q, NotificationsService) {

    this.currentPage = "search";

    this.navigate = function(page) {
      this.currentPage = page;
      this.closeSidenav();
    }

    function showMessage (type, toastPosition) {
      return function (message) {

        if (_.isEmpty(message)) {
          return $q.when();

        } else {

          return $mdToast.show(
            $mdToast.simple()
              .content(message)
              .position(toastPosition)
          );

        }
      };
    }

    this.closeSidenav = function() {
      $mdSidenav('left').close();
    }

    function toggleSideNav() {
      return debounce(function() {
        // Component lookup should always be available since we are not using `ng-if`
        $mdSidenav('sidenav')
          .toggle()
          .then(function () {
            $log.debug("toggle " + navID + " is done");
          });
      }, 200);
    }

    var showError = showMessage('error', 'top left');
    var showInfo = showMessage('info', 'bottom right');

    $scope.$watch(function () {
      return NotificationsService.notifications;
    }, function (notifications) {

      showError(notifications.error)
        .finally(function () {
          return showInfo(notifications.info);
        })
        .finally(function () {
          NotificationsService.clear();
        })
      ;

    }, true);

  });

