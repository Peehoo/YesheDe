'use strict';

angular.module('starterApp').controller(
		'InsertBookCtrl',
		function($http) {

			var self = this
			this.selectedLocation = {}

			this.book = {
				title : 'title',
				productionYear : new Date(),
				numCopies : 0,
				pressPlateLocationId : '',
				text : ''
			}

			this.insertBook = function() {
				$http.post('/yeshede/books', JSON.stringify(this.book))
						.success(function(data, status, headers, config) {
							// this callback will be called asynchronously
							// when the response is available
							console.log(data);
						}).error(function(data, status, headers, config) {
							// called asynchronously if an error occurs
							// or server returns response with an error status.
						});
			}

			this.getLocations = function() {
				return $http.get('/yeshede/locations').success(
						function(data, status, headers, config) {
							self.locations = data;
						}).error(function(data, status, headers, config) {
					// called asynchronously if an error occurs
					// or server returns response with an error status.
				});
			}

			this.getLocations()
		});
