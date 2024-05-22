
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>添加学生</title>
    <link rel="stylesheet" href="../static/css/pintuer.css">
    <link rel="stylesheet" href="../static/css/admin.css">
    <script type="text/javascript" src="../static/js/jquery.js"></script>
    <script src="../static/js/pintuer.js"></script>
    <style>
        #uploadFile {
            display: inline-block;
            padding: 8px 16px;
            background-color: #4CAF50; /* 绿色背景 */
            color: white; /* 白色字体 */
            border: 2px solid #4CAF50; /* 绿色边框 */
            border-radius: 4px; /* 小圆角 */
            text-decoration: none; /* 移除下划线 */
            font-size: 14px; /* 字体大小 */
            font-weight: bold; /* 加粗字体 */
            transition: background-color 0.3s, border-color 0.3s; /* 添加过渡效果 */
        }

        #uploadFile:hover {
            background-color: #45a049; /* 鼠标悬停时更改为深绿色 */
            border-color: #45a049; /* 鼠标悬停时更改边框颜色为深绿色 */
        }


    </style>
    <script type="text/javascript">
        var originPhoto="${EmpDetail.photo}";
        $(function () {
            var hireDateString = "${EmpDetail.hireDate}";

            // 将日期字符串转换为 Date 对象
            var hireDate = new Date(hireDateString);

            var year = hireDate.getFullYear();
            var month = (hireDate.getMonth() + 1 < 10 ? '0' : '') + (hireDate.getMonth() + 1);
            var day = (hireDate.getDate() < 10 ? '0' : '') + hireDate.getDate();

            // 将年、月、日拼接为指定格式的字符串
            var formattedDate = year + '-' + month + '-' + day;

            document.getElementById('hireDate').value =formattedDate;

                showAllDept();
                showAllMgr();

                $("form").submit(function () {
                    $(this).data("submitting", true);
                });


                window.addEventListener("beforeunload", function (event) {
                    // 检查是否是表单提交
                    var formSubmit = $("form").data("submitting");

                    // 如果不是表单提交，则执行 fileDelete 函数
                    if (!formSubmit) {
                        fileDelete($("#photoInput").val());
                    }else{
                        if($("#photoInput").val()!=originPhoto){
                            fileDelete(originPhoto);
                        }
                    }
                });
            }
        )
        function uploadFile() {
            //获取上传文件
            var photoFile=$("#photo")[0].files[0];
            //文件可能未上传
            if(photoFile==undefined){
                alert("您还未选中文件");
                return;
            }
            //通过Ajax向后台发送文件
            //将文件装入FormData对象才能提交
            var formData=new FormData();
            formData.append("avatar",photoFile);
            //只有用post才能提交非文本数据
            $.ajax({
                type:"post",
                data:formData,
                url:"fileUpload.action",
                /*告诉浏览器发送的是对象，不能转换成字符串*/
                processData:false,
                /*设置请求类型为二进制*/
                contentType:false,
                success:function (result) {
                    if(result.alert!==undefined){
                        alert(result.alert);
                        return;
                    }
                    //接受后台响应信息
                    if(result.warn!=null)
                        alert(result.warn);
                    //删除旧文件
                    var oldFileName=$("#photoInput").val();
                    if(oldFileName!=originPhoto){
                        fileDelete(oldFileName);
                    }


                    // 图片回显
                    var fileName=result.newFileName;
                    var contentType=(result.fileType);
                    $("#headImg").attr("src",fileName);



                    //将文件名放入form表单
                    $("#photoInput").val(fileName);
                    $("#filetypeInput").val(contentType);

                }
            })


        }

        function fileDelete(oldFileName) {
            if(!oldFileName||oldFileName.trim()===""){
                return;
            }
            $.ajax({
                type:"get",
                data:{"name":oldFileName},
                contentType: 'application/json',
                url:"fileDelete.action"
            })

        }
        function showAllDept() {
            $.ajax({
                type:"GET",
                url:"showAllDept.action",
                success:function(depts){
                    $('#deptno').html('<option value="">--请选择--</option>');
                    $.each(depts,function(i,e){
                        var optionString = '<option value="' + e.deptno + '"';
                        if ('${EmpBasic.deptno}' == e.deptno) {
                            optionString += ' selected';
                        }
                        optionString += '>' + e.dname + '</option>';
                        $('#deptno').append(optionString);
                    })
                }
            })
        }
        function showAllMgr() {
            $.ajax({
                type:"GET",
                url:"showAllEmpBasic.action",
                success:function(areas){
                    $('#mgr').html('<option value="">--请选择--</option>');
                    $.each(areas,function(i,e){
                        var optionString = '<option value="' + e.empno + '"';
                        if ('${EmpDetail.mgr}' == e.empno) {
                            optionString += ' selected';
                        }
                        optionString += '>' + e.ename + '</option>';
                        $('#mgr').append(optionString)
                    })
                }
            })
        }
        function getSelectedGender() {
            var genderRadios = document.getElementsByName('gender');
            for (var i = 0; i < genderRadios.length; i++) {
                if (genderRadios[i].checked) {
                    return true;
                }
            }
            // 如果没有选中的性别，则返回空字符串或其他默认值
            return false;
        }
        function checkEmp() {
            var ename=$("#ename").val();
            var deptno=$("#deptno").val();
            var age=$("#age").val();
            var sal=$("#sal").val();
            var comm=$("#comm").val();
            var genderCheck=getSelectedGender();
            var job=$("#job").val();
            if (!ename || ename.trim() === "") {
                alert("请输入姓名!");
                return false;
            }
            if(!genderCheck){
                alert("请选择性别!");
                return false;
            }
            if (!age || age.trim() === "") {
                alert("请输入年龄!");
                return false;
            }
            if (!job || job.trim() === "") {
                alert("请输入职务!");
                return false;
            }
            if (!deptno || deptno.trim() === "") {
                alert("请选择部门!");
                return false;
            }
            if (isNaN(age)) {
                alert("年龄必须是数字!");
                return false;
            }
            if (isNaN(sal)) {
                alert("工资必须是数字!");
                return false;
            }
            if (isNaN(comm)) {
                alert("补助必须是数字!");
                return false;
            }
            return true;
        }


    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改员工</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="modifyEmp.action" onsubmit="return checkEmp()">
            <input type="hidden"  name="empno" value="${EmpBasic.empno}" readonly>
            <div class="form-group">
                <div class="label">
                    <label>姓名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input"  id="ename" name="ename" value="${EmpBasic.ename}"  style="width:30%"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>性别：</label>
                </div>
                <div class="field"  style="padding-top:8px;">
                    男: <input type="radio" id="male" name="gender" value="男" ${EmpDetail.gender == '男' ? 'checked' : ''} />
                    女: <input type="radio" id="female" name="gender" value="女" ${EmpDetail.gender == '女' ? 'checked' : ''} />
                </div>

            </div>

            <div class="form-group">
                <div class="label">
                    <label>年龄：</label>
                </div>
                <div class="field">
                    <input type="text" class="input"  id="age" name="age" value="${EmpBasic.age}"  style="width:30%"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>职务：</label>
                </div>
                <div class="field">
                    <input type="text" class="input"  id="job" name="job" value="${EmpBasic.job}"  style="width:30%"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>入职日期：</label>
                </div>
                <div class="field">
                    <input type="date" class="input" id="hireDate"  name="hireDate" style="width:30%" value=""/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>手机号：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" id="phone" name="phone" value="${EmpDetail.phone}" style="width:30%"/>
                </div>
            </div>



            <!--把部门编号和直属领导做成一个异步选择器-->

            <div class="form-group">
                <div class="label">
                    <label>直属领导：</label>
                </div>
                <div class="field">
                    <select class="input" id="mgr" name="mgr" style="width:30%" value="">
                        <option value="">--请选择--</option>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>所在部门：</label>
                </div>
                <div class="field">

                    <select class="input" style="width:30%" id="deptno" name="deptno" value="${EmpBasic.deptno}">
                        <option value="">--请选择--</option>
                    </select>

                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>薪水：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" id="sal" name="sal" value="${Salary.sal}" style="width:30%"/>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>补助：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" id="comm" name="comm" value="${Salary.comm}" style="width:30%"/>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>头像上传：</label>
                </div>
                <div class="field">
                    <input id="photo" type="file">
                    <!--                    图片回显-->
                    <br/>
                    <img id="headImg" style="width: 200px;height: 200px" src="${EmpDetail.photo}">
                    <br/>
                    <a id="uploadFile" href="javascript:void(0)" onclick="uploadFile()">立即上传</a>
                </div>

            </div>


            <input id="photoInput"  type="hidden" name="photo" value="${EmpDetail.photo}">
            <input id="filetypeInput"  type="hidden" name="filetype">



            <div class="form-group">
                <div class="label">
                    <label>描述：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" id="description" name="description" style="height:80px;">${EmpDetail.description}</textarea>
                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"  > 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>