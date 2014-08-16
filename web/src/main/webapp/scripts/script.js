
// This function is used by the login screen to validate user/pass
// are entered.
function validateRequired(form) {
    var bValid = true;
    var focusField = null;
    var i = 0;
    var fields = new Array();
    oRequired = new required();

    for (x in oRequired) {
        if ((form[oRequired[x][0]].type == 'text' || form[oRequired[x][0]].type == 'textarea' || form[oRequired[x][0]].type == 'select-one' || form[oRequired[x][0]].type == 'radio' || form[oRequired[x][0]].type == 'password') && form[oRequired[x][0]].value == '') {
           if (i == 0)
              focusField = form[oRequired[x][0]];

           fields[i++] = oRequired[x][1];

           bValid = false;
        }
    }

    if (fields.length > 0) {
       focusField.focus();
       alert(fields.join('\n'));
    }

    return bValid;
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function confirmDelete(obj) {
    var msg = "Are you sure you want to delete this " + obj + "?";
    ans = confirm(msg);
    return ans;
}

// 18n version of confirmDelete. Message must be already built.
function confirmMessage(obj) {
    var msg = "" + obj;
    ans = confirm(msg);
    return ans;
}


var substringMatcher = function(URL) {
	return function findMatches(q, cb) {
		var matches, substrRegex;

		// an array that will be populated with substring matches
		matches = [];

		// regex used to determine if a string contains the substring `q`
		substrRegex = new RegExp(q, 'i');

		$.ajax({
			url : URL,
			type : "GET",
			data : {
				query : q
			},
			dataType : "json",
			success : function(response) {
				// iterate through the pool of strings and for any string that
				// contains the substring `q`, add it to the `matches` array
				$.each(response, function(i, str) {
					if (substrRegex.test(str.label)) {
						// the typeahead jQuery plugin expects suggestions to a
						// JavaScript object, refer to typeahead docs for more info
						matches.push({
							value : str.label,
							id : str.value
						});
						cb(matches);
					}
				});
			}
		});
	};
};

var substringAreaMatcher = function(URL) {
	return function findMatches(q, cb) {
		var matches, substrRegex;

		// an array that will be populated with substring matches
		matches = [];

		// regex used to determine if a string contains the substring `q`
		substrRegex = new RegExp(q, 'i');

		$.ajax({
			url : URL,
			type : "GET",
			data : {
				query : q,
				cityId:$('#cityId').val(),
			},
			dataType : "json",
			success : function(response) {
				// iterate through the pool of strings and for any string that
				// contains the substring `q`, add it to the `matches` array
				$.each(response, function(i, str) {
					if (substrRegex.test(str.label)) {
						// the typeahead jQuery plugin expects suggestions to a
						// JavaScript object, refer to typeahead docs for more info
						matches.push({
							value : str.label,
							id : str.value
						});
						cb(matches);
					}
				});
			}
		});
	};
};


function initializeTypeahead(fieldId, URL) {
	$('#' + fieldId).typeahead({
		hint : false,
		highlight : false,
		minLength : 1
	}, {
		name : fieldId,
		displayKey : 'value',
		source : substringMatcher(URL),
	}).bind(
			'typeahead:selected',
			function(obj, data, name) {
				if ($('#' + fieldId + 'Id') != undefined
						&& $('#' + fieldId + 'Id').length > 0) {
					$('#' + fieldId + 'Id').val(data.id);
				}
			});
}

function initializeAreaTypeahead(fieldId, URL) {
	$('#' + fieldId).typeahead({
		hint : false,
		highlight : false,
		minLength : 1
	}, {
		name : fieldId,
		displayKey : 'value',
		source : substringAreaMatcher(URL),
	}).bind(
			'typeahead:selected',
			function(obj, data, name) {
				if ($('#' + fieldId + 'Id') != undefined
						&& $('#' + fieldId + 'Id').length > 0) {
					$('#' + fieldId + 'Id').val(data.id);
				}
			});
}