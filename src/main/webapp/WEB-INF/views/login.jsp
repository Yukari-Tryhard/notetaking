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
            window.location.href = "/dashboard?token=" + accessToken;
            $.ajax({
                url: "/dashboard",
                type: "GET",
                beforeSend: function (xhr) {
                    console.log("Bearer " + accessToken);
                    debugger;
                    xhr.setRequestHeader("Authorization", "Bearer " + accessToken);
                },
                success: function (){
                    window.location.href = "/dashboard?token=" + accessToken;
                },
                fail: function(xhr, textStatus, errorThrown) {
                    console.log("stop");
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
                    window.location.href = "/dashboard" + data.access_token;
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
            <div id="error-message"></div>
            <button id="login-button">Login</button>
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
        height: 50vh;
        left: 30vw;
        top: 25vh;

        /* From https://css.glass */
        background: rgba(255, 255, 255, 0.1);
        border-radius: 16px;
        box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
        backdrop-filter: blur(3.8px);
        -webkit-backdrop-filter: blur(3.8px);
        border: 1px solid rgba(255, 255, 255, 0.16);
        z-index: 999;
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
                window.location.href = "/dashboard" + data.access_token;
            })
            .fail(function (data){
            $("#error-message").html(data.responseJSON.message);
            console.log(data);
        })
    })
</script>
</html>