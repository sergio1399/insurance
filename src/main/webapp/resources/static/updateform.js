$('#ndsSum').keyup(function(event) {
    if ($(this).val().trim().length > 0) {
        $('#sumWithNds').val( Number(parseFloat($(this).val()) + parseFloat($('#sumNoNds').val())).toFixed(2) );
        $('#ndsRate').val(Math.round((parseFloat($(this).val()) / parseFloat($('#sumWithNds').val())) * 100));
    }
});

$('#sumWithNds').keyup(function(event) {
    if ($(this).val().trim().length > 0) {
        $('#sumNoNds').val( Number(parseFloat($(this).val()) - parseFloat($('#ndsSum').val())).toFixed(2) );
    }
});

$('#sumNoNds').keyup(function(event) {
    if ($(this).val().trim().length > 0) {
        $('#sumWithNds').val( Number(parseFloat($(this).val()) + parseFloat($('#ndsSum').val())).toFixed(2) );
    }
});

$('#ndsRate').keyup(function(event) {
    if ($(this).val().trim().length > 0) {
        $('#ndsSum').val( Number((parseFloat($(this).val()) * parseFloat($('#sumWithNds').val())) / 100).toFixed(2) );
        $('#sumWithNds').val( Number(parseFloat($('#ndsSum').val()) + parseFloat($('#sumWithNds').val())).toFixed(2) );
    }
});