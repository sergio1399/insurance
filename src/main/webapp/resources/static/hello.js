function post(path, params, method) {
	method = method || "post"; 

	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);

	for ( var key in params) {
		if (params.hasOwnProperty(key)) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);

			form.appendChild(hiddenField);
		}
	}

	document.body.appendChild(form);
	form.submit();
}

//used to apply alternating row styles
function zebraRows(selector, className)
{
    $(selector).removeClass(className).addClass(className);
}

//filter results based on query
function filter(selector, query, tdId) {
    query =   $.trim(query); //trim white space
    query = query.replace(/ /gi, '|'); //add OR for regex query

    $(selector).each(function() {
        ($(this).children(tdId).text().search(new RegExp(query, "i")) < 0) ? $(this).hide().removeClass('visible') : $(this).show().addClass('visible');
    });
}

function strictFilter(selector, query, tdId) {
    //query =   $.trim(query);

    $(selector).each(function() {
        ($(this).children(tdId).text() === query) ? $(this).show().addClass('visible') : $(this).hide().removeClass('visible');
    });
}

//default each row to visible
$('tbody tr').addClass('visible');

$('#filterSN').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else {
        filter('tbody tr', $(this).val(), '#seriaNumber');
    }

});

$('#filterType').change(function() {
    if ($(this).val() == 'ВСЕ') {
        $('tbody tr').removeClass('visible').show().addClass('visible');
	}
    else {
        strictFilter('tbody tr', $(this).val(), '#contractType');
    }

});

$('#filterSign').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else if (event.keyCode == 13) {
        strictFilter('tbody tr', $(this).val(), '#signDate');
    }
});

$('#filterOpen').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else if (event.keyCode == 13) {
        strictFilter('tbody tr', $(this).val(), '#openDate');
    }
});

$('#filterExp').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else if (event.keyCode == 13) {
        strictFilter('tbody tr', $(this).val(), '#expDate');
    }
});

$('#filterSumNoNds').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else if (event.keyCode == 13) {
        strictFilter('tbody tr', $(this).val(), '#sumNoNds');
    }
});

$('#filterRate').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else if (event.keyCode == 13) {
        strictFilter('tbody tr', $(this).val(), '#ndsRate');
    }
});

$('#filterSumNds').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else if (event.keyCode == 13) {
        strictFilter('tbody tr', $(this).val(), '#ndsSum');
    }
});

$('#filterSumWithNds').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else if (event.keyCode == 13) {
        strictFilter('tbody tr', $(this).val(), '#sumWithNds');
    }
});

$('#filterAccord').change(function() {
    if ($(this).val() == 'ВСЕ') {
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }
    else {
        strictFilter('tbody tr', $(this).val(), '#accord');
    }

});

$('#filterTS').keyup(function(event) {
    //if esc is pressed or nothing is entered
    if (event.keyCode == 27 || $(this).val() == '') {
        //if esc is pressed we want to clear the value of search box
        $(this).val('');

        //we want each row to be visible because if nothing
        //is entered then all rows are matched.
        $('tbody tr').removeClass('visible').show().addClass('visible');
    }

    //if there is text, lets filter
    else {
        filter('tbody tr', $(this).val(), '#vehicle');
    }
});