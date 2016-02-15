<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log Trace</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/dashboard.css"/> " rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>

    <script>
        $(document).ready(function(){
            $("#navleft li").click(function() {
                $(this).addClass("active");
                //$("#navleft li:not(this)").removeClass("active");
            });
        });
    </script>

</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Project</a>
            </div>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">
            <div id="navleft" class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="<c:url value="/home"/>">Home</a></li>
                    <li role="presentation"><a href="<c:url value="/config/edit"/>">配置</a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <decorator:body />
            </div>
        </div>
    </div>
</body>
</html>
