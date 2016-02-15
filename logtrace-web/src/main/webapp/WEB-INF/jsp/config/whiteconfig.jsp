<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log Trace Configuration</title>
</head>
<body>
    <h1 class="page-header">系统配置</h1>
    <div class="container-fluid">
        <!-- Single button -->
        <div class="row">
            <div class="col-lg-2">
                所属系统:
            </div>
            <div class="col-lg-8">
                <select name="system" id="system">
                    <option value="matrix">matrix</option>
                    <option value="default">default</option>
                </select>
            </div>
        </div>
        <br/><br/>
        <div class="row">
            <div class="col-lg-2">
                是否启用:
            </div>
            <div class="col-lg-8">
                <input type="radio" name="status" id="statusOpen" value="1">启用
                &nbsp;&nbsp;<input type="radio" name="status" id="statusClose" value="0">关闭
            </div>
        </div>
        <br/><br/>
        <div class="row">
            <div class="col-lg-2">
                详细配置信息:
            </div>
            <div class="col-lg-8">
                <textarea id="configJson" name="configJson"></textarea>
            </div>
        </div>
    </div>
</body>
</html>
