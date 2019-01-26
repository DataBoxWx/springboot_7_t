function login(contextPath) {
    var userName = $("#userName").val();
    var password = $("#password").val();
    var checkLgoin = $("#checkLogin").is(":checked");
    if(userName == ""){
        $("#errorMessage").html("用户名不能为空");
        return;
    }
    if(password == ""){
        $("#errorMessage").html("密码不能为空");
        return;
    }
    if($("#autoLogin").val() != "1"){
        password = $.md5(password);
    }
    $.ajax({
        url : contextPath + "/admin/login",
        data : {"userName" : userName,
                "password" : password,
                "flag" : checkLgoin},
        dataType : "json",
        type : "post",
        success : function (responseMessage) {
            if(responseMessage.errorCode == "0"){
                window.location.href=contextPath +"/admin/profile";
            }else {
                $("#errorMessage").html(responseMessage.errorMessage);
            }
        },
        error : function () {
            $("#errorMessage").html("网络错误");
        }
    })
}