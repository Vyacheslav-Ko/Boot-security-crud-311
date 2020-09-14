$(document).ready(function() {

    $('.table #editButton').on('click',function(event){

        event.preventDefault();

        var href= $(this).attr('href');
        var id =  $(this).data('id')
        console.log(id)

        $.get(href, function(user, status){
            $('#idEd').val(id);
            $('#firstnameEd').val();
            $('#lastnameEd').val();
            $('#ageEd').val();
            $('#emailEd').val();
            $('#passwordEd').val();
        });

        $('#editModal').modal()

    });

    $('.table #deleteButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();

    });

});