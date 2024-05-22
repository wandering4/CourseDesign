
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>奇点科技</title>
    <link rel="stylesheet" href="static/css/index.css">
    <link href='static/css/boxicons.min.css' rel='stylesheet'>
    <script src="static/js/jquery.js"></script>
</head>
<body>
<!-- NAVBAR CREATION -->
<header class="header">
    <nav class="navbar">
        <a href="welcome.html">主页</a>
        <a href="#">关于我们</a>
        <a href="#">加入我们</a>
        <a href="#">帮助</a>
    </nav>
</header>
<!-- LOGIN FORM CREATION -->
<div class="background"></div>
<div class="container">
    <div class="item">
        <h2 class="logo"><i class='bx bxl-xing'></i>奇点技术</h2>
        <div class="text-item">
            <h2>欢迎使用 <br><span>
                    人力资源管理系统
                </span></h2>
            <p style="color:#9e98f4">孤帆远影碧空尽，唯见长江天际流</p>
            <div class="social-icon">
                <a href="#"><i class='bx bxl-facebook'></i></a>
                <a href="#"><i class='bx bxl-twitter'></i></a>
                <a href="#"><i class='bx bxl-youtube'></i></a>
                <a href="#"><i class='bx bxl-instagram'></i></a>
                <a href="#"><i class='bx bxl-linkedin'></i></a>
            </div>
        </div>
    </div>
    <div class="login-section">
        <div class="form-box login">
            <form action="login.action">
                <h2>登录</h2>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-envelope'></i></span>
                    <input type="text" required name="username" id="username">
                    <label >用户名</label>
                </div>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-lock-alt' ></i></span>
                    <input type="password" required name="password" id="password" >
                    <label>密码</label>
                </div>
                <div class="remember-password">
                    <label for=""><input type="checkbox">记住密码</label>
                    <a href="#">忘记密码？</a>
                </div>
                <span style="color:red" >
                    <%
                    String mess=request.getParameter("loginMessage");
                    if(mess!=null) out.print(mess);
                    %>
                </span>
                <button class="btn">登录</button>
                <div class="create-account">
                    <p>创建一个新用户? <a href="#" class="register-link">注册</a></p>
                </div>
            </form>
        </div>
        <div class="form-box register">
            <form action="">

                <h2>注册</h2>

                <div class="input-box">
                    <span class="icon"><i class='bx bxs-user'></i></span>
                    <input type="text" required name="username" id="unameI" onblur="checkUname()">
                    <label >用户名</label>
                </div>
                <span id="unameInfo" style="color: red; font-size: 3px;"></span>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-lock-alt' ></i></span>
                    <input type="password" required name="password" id="enrollPassword">
                    <label>密码</label>
                </div>
                <div class="remember-password">
                    <label for=""><input type="checkbox">我同意相关协议</label>
                </div>
                <span  style="color:red" >暂不支持注册</span>
                <button class="btn">注册</button>
                <div class="create-account">
                    <p>已经有了一个账户? <a href="#" class="login-link">登录</a></p>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- SIGN UP FORM CREATION -->

<script src="static/js/index.js"></script>
<script>
    function checkUname(){
        $("#unameInfo").text("");
        $.ajax(
            {
                type: "Get",
                url: "unameCheck.action",
                data: "uname=" + $("#unameI").val(),
                success: function (returnInfo) {
                },
            }
        )
    }

</script>
</body>
</html>
