<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 引入 Bootstrap -->
    <link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/qing_style/css/navtop_new.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
    <title>需求信息</title>
    <script type="text/javascript">
        $(function(){
            var type = "${user.usertype}";
            if(type != 0){
                $(".btnview").hide();
            }
        });
    </script>
    <style>
        .control-label {
            margin-bottom: 0;
            padding-top: 7px;
            text-align: right;
        }
        .pull-left{ margin-left: 40px; }
        .form-btn-group {  padding: 15px 16px; }
        h1 small {
            font-size: 18px;
            padding-left: 15px;
        }
        hr{
            -moz-border-bottom-colors: none;
            -moz-border-left-colors: none;
            -moz-border-right-colors: none;
            -moz-border-top-colors: none;
            border-color: #f6f6f6 -moz-use-text-color #b4b4b4;
            border-image: none;
            border-style: solid none;
            border-width: 1px 0;
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div id="LG" class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="login-wrapper">
                <div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">

    <div class="form-group">
    <h1>
        需求信息
        <small>Business users can recharge the account here.</small>
    </h1>
    <hr>
    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Title</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${model.title}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Budget</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${model.budget}&nbsp;元</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Details</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${model.details}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-4 control-label">File</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <s:a action="consult_download" namespace="/consult">
                                <s:param name="id" value="model.id"></s:param>
                                <s:property value="model.filePath"/>
                            </s:a>
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Field</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${model.category}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Remark</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${model.remark}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">State</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">${model.state}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 form-btn-group">
                            <s:a action="consult_allow" namespace="/consult">
                                <s:param name="id" value="id"/>
                                <button type="button" class="btn btn-success pull-right btnview">批准</button>
                            </s:a>
                        </div>
                        <div class="col-sm-8 form-btn-group">
                            <s:a action="consult_reject" namespace="/consult">
                                <s:param name="id" value="id"/>
                                <button type="button" class="btn btn-warning pull-left btnview">拒绝</button>
                            </s:a>
                            <!--a href="#" onClick="javascript:history.go(-1)" ><span class="glyphicon glyphicon-circle-arrow-left">返回</span></a-->                        </div>
                    </div>
                    </div>
                </div></div></div></div>
<div class="footer">
    <p><strong>ZJH</strong> &copy; 2016 All Rights Reserved <a class="links" href="#">进入后台</a></p>
</div>
</body>
</html>
