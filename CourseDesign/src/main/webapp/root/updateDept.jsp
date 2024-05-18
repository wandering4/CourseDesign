<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>添加部门</title>
    <link rel="stylesheet" href="../static/css/pintuer.css">
    <link rel="stylesheet" href="../static/css/admin.css">
    <script src="../static/js/jquery.js"></script>
    <script src="../static/js/pintuer.js"></script>
</head>
<script>
    function checkDept() {
        var dname = $("#dname").val();
        var loc = $("#loc").val();
        if (!dname || dname.trim() === "") {
            alert("请输入部门名称!");
            return false;
        }
        if (!loc || loc.trim() === "") {
            alert("请选择位置!");
            return false;
        }
        return true;
    }
</script>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改部门</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="modifyDept.action" onsubmit="return checkDept()">
            <input type="hidden" id="deptno" name="deptno" value="${Dept.deptno}" readonly>
            <div class="form-group">
                <div class="label">
                    <label>部门名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" id="dname" name="dname" value="${Dept.dname}" style="width:30%"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>部门位置：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" id="loc" name="loc" value="${Dept.loc}" style="width:30%"/>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>部门描述：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" name="description"  style="height:80px;">${Dept.description}</textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>