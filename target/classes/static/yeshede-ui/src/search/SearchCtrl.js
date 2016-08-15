'use strict';
angular.module('starterApp').controller(
		'SearchCtrl',
		function($timeout, $q, $log, $http) {

			var self = this;
			self.simulateQuery = false;
			self.isDisabled = false;
			self.querySearch = querySearch;
			self.selectedItemChange = selectedItemChange;
			self.searchTextChange = searchTextChange;
			
			self.books = {};
			self.texts = {};
						
			self.listBooks = function() {
				return $http.get('/yeshede/books').success(
						function(data, status, headers, config) {
							self.books = data;
						}).error(function(data, status, headers, config) {
					// called asynchronously if an error occurs
					// or server returns response with an error status.
				});
			}
			self.listTexts = function() {
				return $http.get('/yeshede/texts').success(
						function(data, status, headers, config) {
							self.texts = data;
						}).error(function(data, status, headers, config) {
					// called asynchronously if an error occurs
					// or server returns response with an error status.
				});
			}
			
			self.listBooks();
			self.listTexts();

			
			
			// ******************************
			// Internal methods
			// ******************************
			/**
			 * Search for repos... use $timeout to simulate remote dataservice
			 * call.
			 */
			function querySearch(query) {
				var results = query ? self.books.filter(createFilterForBooks(query))
						: self.books, deferred;
				return results;
			}
			
			function searchTextChange(text) {
				
			}

			function selectedItemChange(item) {
			}

			/**
			 * Create filter function for a query string
			 */
			function createFilterForBooks(query) {
				return function filterFn(book) {
					if(book.title) return (book.title.indexOf(query) > -1);
					if(book.author) return (book.author.indexOf(query) > -1);
				};
			}
			
			function createFilterForTexts(query) {
				return function filterFn(text) {
					if(text.title) return (text.title.indexOf(query) === 0);
					if(text.author) return (text.author.indexOf(query) === 0);
				};
			}

		})
