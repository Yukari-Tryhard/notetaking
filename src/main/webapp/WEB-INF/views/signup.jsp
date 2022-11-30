<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Spring MVC 5 - form handling | Java Guides</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
    <script>
        var accessToken = localStorage.getItem("access_token");
        var refreshToken = localStorage.getItem("refresh_token");

        if (accessToken){
            $.ajax({
                url: "/dashboard",
                type: "GET",
                beforeSend: function (xhr) {
                    console.log("Bearer " + accessToken);
                    debugger;
                    xhr.setRequestHeader("Authorization", "Bearer " + accessToken);
                },
                success: function (){
                    window.location.href = "/dashboard"
                },
                fail: function(xhr, textStatus, errorThrown) {

                }
            })
        }
        else if (refreshToken){
            $.ajax({
                url: "/api/v1/authenticate/refresh-token",
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer " + refreshToken);
                },
                success: function (data){
                    localStorage.setItem("access_token", data.access_token);
                    localStorage.setItem("refresh_token", data.refresh_token);
                    $.ajax({
                        url: "/dashboard",
                        type: 'GET',
                        beforeSend: function (xhr) {
                            xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("access_token"));
                        },
                        success: function (){
                            window.location.href = "/dashboard"
                        }
                    })
                },
                fail: function(xhr, textStatus, errorThrown) {

                }
            })
        }
    </script>
</head>
<body>
<div class="container-xl mx-auto linear-bg">
    <div class="login-container">
        <div class="login-header">Get started now</div>
        <div class="login-sub-header">If you already knew us. <a>Login</a></div>
        <form id="login-form">
            <div class="login-field">
                <label for="email">Email</label>
                <input id="email" name="email" type="text">
            </div>
            <div class="login-field">
                <label for="password">Password</label>
                <input id="password" name="password" type="password">
            </div>
            <div class="login-field">
                <label for="retype-password">Confirm your password</label>
                <input id="retype-password" name="password" type="password">
            </div>
            <div id="error-message"></div>
            <button id="login-button">Create account</button>
        </form>
    </div>
</div>
<div id="circle-1" class="float"><img src="https://i.ibb.co/GP7X22B/circle-1.png"></div>
<div id="circle-2" class="float"><img src="https://i.ibb.co/C57W2V9/circle-2.png"></div>
<div id="circle-3" class="float"><img src="https://i.ibb.co/Rgvx3Vq/circle-3.png"></div>
</body>
<style>
    .linear-bg{
        background-color: #131837;
        background-image: url("https://i.ibb.co/k5z6xSs/login-background.png");
        background-size: cover;
        width: 100vw;
        height: 100vh;
        font-family: sans-serif;
    }
    .float{
        position: absolute;
    }
    #circle-1{
        top:0;
        left: 0;
        width: 20%;
        aspect-ratio: 1;
    }
    #circle-2{
        bottom: 5%;
        left: 20%;
        width: 18%;
        aspect-ratio: 1;
    }
    #circle-3{
        top: 15%;
        right: 30%;
        width: 13%;
        aspect-ratio: 1;
    }
    .login-container{

        box-sizing: border-box;
        position: absolute;
        width: 40vw;
        height: 80vh;
        left: 30vw;
        top: 10vh;

        /* From https://css.glass */
        background: rgba(255, 255, 255, 0.1);
        border-radius: 16px;
        box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
        backdrop-filter: blur(3.8px);
        -webkit-backdrop-filter: blur(3.8px);
        border: 1px solid rgba(255, 255, 255, 0.16);
        z-index: 999;
    }
    #login-form{
        position: absolute;
        left: 50%;
        transform: translate(-50%);
    }
    .login-header{
        padding-top: 65px;
        padding-bottom: 5px;
        font-style: normal;
        font-weight: 700;
        font-size: 45px;
        line-height: 68px;
        align-items: center;
        text-align: center;

        background: linear-gradient(239.47deg, #FF62E6 13.86%, #48C7FE 68.55%);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        background-clip: text;
        text-fill-color: transparent;
    }
    .login-field{

        color: white;

        display: flex;
        flex-direction: column;
    }
    .login-sub-header{
        color: white;
        text-align: center;
        margin-bottom: 20px;
    }
    #login-button{
        color: white;
        background: #906AE5;
        padding: 10px 20px;
        border-radius: 5px;

        font-style: normal;
        font-weight: 600;
        font-size: 15px;
        line-height: 20px;

        top: 80%;
        left: 50%;
        transform: translate(50%);
    }
    #login-button:hover{
        background: #6A37DD;
     }
    input{
        height: 35px;
        width: 300px;
        background: linear-gradient(90deg, rgba(255, 255, 255, 0.32) 0%, rgba(255, 255, 255, 0.16) 97%);
        border: 1px solid rgba(255, 255, 255, 0.4);
        backdrop-filter: blur(25px);
        /* Note: backdrop-filter has minimal browser support */
        margin: 15px 0;
        border-radius: 5px;
    }
    input:hover{
        border-color: #E130AC;
    }
    input:focus{
        border-color: aliceblue;
    }

    a{
        text-decoration:underline;
        font-weight: bold;
    }
    a:hover{
        color: #6A37DD;
        cursor: pointer;
    }




</style>
<script>
    $("#login-form").submit(function (e) {
        event.preventDefault();
        var email = $("#email").val();
        var password = $("#password").val();
        // debugger;
        $.post("/api/v1/authenticate",{email: email, password: password})
            .done(function (data) {
                localStorage.setItem("access_token", data.access_token);
                localStorage.setItem("refresh_token", data.refresh_token);

                $.ajax({
                    url: "/dashboard",
                    type: "GET",
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("access_token"));
                    },
                    success: function (){
                        window.location.href = "/dashboard"
                    }
                })
            })
            .fail(function (data){
                $("#error-message").html(data.responseJSON.message);
                console.log(data);
            })
    })
</script>
</html>