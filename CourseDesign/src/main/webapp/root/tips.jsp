<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统信息</title>
    <link rel="stylesheet" href="../static/css/pintuer.css">
    <style type="text/css">
        *{ margin:0px; padding:0px;}
        .error-container{ background:#fff; border:1px solid #0ae;  text-align:center; width:450px; margin:250px auto; font-family:Microsoft Yahei; padding-bottom:30px; border-top-left-radius:5px; border-top-right-radius:5px;  }
        .error-container h1{ font-size:16px; padding:12px 0; background:#0ae; color:#fff;}
        .errorcon{ padding:35px 0; text-align:center; color:#0ae; font-size:18px;}
        .errorcon i{ display:block; margin:12px auto; font-size:30px; }
        .errorcon span{color:red;}
        h4{ font-size:14px; color:#666;}
        a{color:#0ae;}
    </style>
    <script src="../static/js/jquery.js" type="text/javascript"></script>
</head>

<script>
    $(function () {
        var operationResult = "<%= request.getParameter("operationResult") %>";
        var href = "<%= request.getParameter("href") %>";
        console.log(href);
        // 根据操作结果切换显示内容
        if (operationResult == "false") {
            document.getElementById("successIcon").style.display="none";
            document.getElementById("failureMessage").style.display = "inline"; // 显示失败消息
        }

        function countdown(href) {
            var timeLeft = parseInt(document.getElementById("wait").textContent);
            if (timeLeft > 0) {
                document.getElementById("wait").textContent = timeLeft - 1; // 更新剩余时间
                setTimeout(function() { countdown(href); }, 1000); // 每秒更新一次倒计时
            } else {
                // 时间到，执行页面跳转
                window.location.href = href;
            }
        }
        countdown(href);
    });

</script>
<body class="no-skin">
<div class="error-container">
    <h1> 后台管理系统-信息提示 </h1>
    <div class="errorcon">
        <span  id="successIcon" style="display: inline"><i class="icon-smile-o" style="color: blue"></i><a style="color: blue">操作成功</a></span>
        <span id="failureMessage" style="display:none;"><i class="icon-frown-o"></i>操作失败!</span>

    </div>
    <h4 class="smaller">页面自动 <a id="href" href="<%=request.getParameter("href")%>">跳转</a> 等待时间： <b id="wait">3</b> 秒钟</h4>

</div>

</body>
</html>

