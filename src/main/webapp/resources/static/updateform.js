$('#ndsSum').keyup(function(event) {
    if ($(this).val().trim().length > 0) {
        $('#sumWithNds').val(parseFloat($(this).val()) + parseFloat($('#sumNoNds').val()));
        $('#ndsRate').val(Math.round((parseFloat($(this).val()) / parseFloat($('#sumWithNds').val())) * 100));
    }
});

$('#sumWithNds').keyup(function(event) {
    if ($(this).val().trim().length > 0) {
        $('#sumNoNds').val(parseFloat($(this).val()) - parseFloat($('#ndsSum').val()));
    }
});

$('#sumNoNds').keyup(function(event) {
    if ($(this).val().trim().length > 0) {
        $('#sumWithNds').val(parseFloat($(this).val()) + parseFloat($('#ndsSum').val()));
    }
});

$('#ndsRate').keyup(function(event) {
    if ($(this).val().trim().length > 0) {
        $('#ndsSum').val((parseFloat($(this).val()) * parseFloat($('#sumWithNds').val())) / 100);
        $('#sumWithNds').val(parseFloat($('#ndsSum').val()) + parseFloat($('#sumWithNds').val()));
    }
});