$('#ndsSum').keyup(function(event) {
    $('#sumWithNds').val($(this).val() + $('#sumNoNds').val());
    $('#ndsRate').val(Math.round(($(this).val()/$('#sumWithNds').val())*100));
});

$('#sumWithNds').keyup(function(event) {
    $('#sumNoNds').val($(this).val() - $('#ndsSum').val());
});

$('#sumNoNds').keyup(function(event) {
    $('#sumWithNds').val($(this).val() + $('#ndsSum').val());
});

$('#ndsRate').keyup(function(event) {
    $('#ndsSum').val(($(this).val() * $('#sumWithNds').val()) / 100);
    $('#sumWithNds').val($('#ndsSum').val() + $('#sumWithNds').val());
});