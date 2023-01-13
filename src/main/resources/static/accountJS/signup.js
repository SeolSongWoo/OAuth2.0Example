$(function (){
    if($('#user_name').val() != '' || $('#user_email').val() != '') {
        $('#user_name').attr('readonly',true);
        $('#user_email').attr('readonly',true);
    }
});



function signup () {

    const userDTO = JSON.stringify({
        'user_name' : $('#user_name').val(),
        'user_id' : $('#user_id').val(),
        'user_password' : $('#user_password').val(),
        'user_type' : $('#user_type').val(),
        'user_email' : $('#user_email').val(),
        'user_sex' : $('#user_sex').val(),
    });

    $.ajax({
        type : "POST"
        , url : "/login/signup/post"
        , dataType : "json"
        ,contentType: 'application/json'
        , data : userDTO
        , success : function(data){

        }
    });


}