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

$('tbody tr').hover(function(){
    $(this).find('td').addClass('hovered');
}, function(){
    $(this).find('td').removeClass('hovered');
});

//filter results based on query
function filter(selector, query, tdId) {
    query =   $.trim(query); //trim white space
    query = query.replace(/ /gi, '|'); //add OR for regex query

    $(selector).each(function() {
        ($(this).children(tdId).text().search(new RegExp(query, "i")) < 0) ? $(this).hide().removeClass('visible') : $(this).show().addClass('visible');
    });
}

function strictFilter(selector, query, tdId) {
    query =   $.trim(query); //trim white space

    $(selector).each(function() {
        ($(this).children(tdId).text().search(new RegExp(query, "i")) < 0) ? $(this).hide().removeClass('visible') : $(this).show().addClass('visible');
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

    //reapply zebra rows
    $('.visible td').removeClass('odd');
    zebraRows('.visible:odd td', 'odd');
});

$('#filterType').change(function() {
	alert($(this).val());
    if ($(this).val() == 'ALL') {
        $('tbody tr').removeClass('visible').show().addClass('visible');
	}
    else {
        filter('tbody tr', $(this).val(), '#contractType');
    }

    //reapply zebra rows
    $('.visible td').removeClass('odd');
    zebraRows('.visible:odd td', 'odd');
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
    else {
        strictFilter('tbody tr', $(this).val(), '#signDate');
    }

    //reapply zebra rows
    $('.visible td').removeClass('odd');
    zebraRows('.visible:odd td', 'odd');
});
