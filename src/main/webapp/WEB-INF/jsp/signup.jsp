<%--
  Created by IntelliJ IDEA.
  User: dnjfg
  Date: 2023-01-09
  Time: 오후 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body class="vsc-initialized">
<div class="container">
    <div class="form-signin">
        <h2 class="form-signin-heading">Please sign up</h2>
        <p>
            <label for="user_name" class="sr-only">Username</label>
            <input type="text" id="user_name" name="AccountInFor" class="form-control" placeholder="이름" required="" autofocus="" value="${NaverUserData.name}">
        </p>
        <p>
            <label for="user_id" class="sr-only">Username</label>
            <input type="text" id="user_id" name="AccountInFor" class="form-control" placeholder="아이디" required="" autofocus="">
        </p>
        <p>
            <label for="user_password" class="sr-only">Password</label>
            <input type="password" id="user_password" name="AccountInFor" class="form-control" placeholder="비밀번호" required="">
        </p>
            <input type="hidden" id="user_type" name="AccountInFor" value="${NaverUserData.type}"/>
        <p>
            <label for="user_email" class="sr-only">Username</label>
            <input type="text" id="user_email" name="AccountInFor" class="form-control" placeholder="이메일" required="" autofocus="" value="${NaverUserData.email}">
        </p>
        <p>
            <label for="user_sex" class="sr-only">Username</label>
            <input type="text" id="user_sex" name="AccountInFor" class="form-control" placeholder="성별" required="" autofocus="">
        </p>
        <button class="btn btn-lg btn-primary btn-block" onclick="signup();">회원가입</button>
    </div>
</div>
<script src="/resources/accountJS/signup.js"></script>
</body>
</html>