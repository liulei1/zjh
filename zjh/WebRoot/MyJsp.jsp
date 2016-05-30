<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>company user</title>
    <link href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/jquery/ajaxfileupload.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<style>
 .fileInputContainer{
        height:256px;
        background:url(qing_style/img/list/04.jpg) no-repeat;
        background-size:100% 100%;
        position:relative;
        width: 256px;
        border-style:solid;
		border-width:1px;
    }
    .fileInput{
        height:256px;
        overflow: hidden;
        font-size: 300px;
        position:absolute;
        outline: medium none;
        right:0;
        top:0;
        opacity: 0;
        filter:alpha(opacity=0);
        cursor:pointer;
    }
</style>
</head>
<script type="text/javascript">
	//上传图片
	function uploadFile(id){
		//判断图片格式是否正确
		var fileName = $("#img").val();
		fileName = fileName.toLowerCase();
		var fileType = fileName.substring(fileName.lastIndexOf(".")+1);
		if(fileType != "jpg" && fileType != "png" && fileType!="jpeg" && fileType != "gif" && fileType != "bmp"){
			alert('上传文件格式错误，全部附件为.jpg、.png、.jpeg、.gif 或 .bmp格式的图片！');
       	 	return false;
		}
	
		$.ajaxFileUpload(
        {
            url: '${pageContext.request.contextPath}/json/uploadCompanyImage', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: id, //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            data: {"fileSize":"10"}, //将图片的上限大小传入后台 
            success: function (data)  //服务器成功响应处理函数
            {
            	if(data.imgPath != null){
                	$("#imgView").css("background", "url("+data.imgPath+") no-repeat");
            	}else{
            		alert("Upload Fail, Please try again!");
            	}
            },
            error: function (data)//服务器响应失败处理函数
            {
                alert("Upload Fail");
            }
        });
        return false;
	}
</script>
<body>
<div class="fileInputContainer" id="imgView"><input class="fileInput" id="img" type="file" name="file" onchange="uploadFile('img')"><p style="margin-top: 235px;text-align: center;">点击上传头像<p></div>
<hr>
<!-- <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="carousel slide" id="carousel-960739">
				<ol class="carousel-indicators">
					<li class="active" data-slide-to="0" data-target="#carousel-960739">
					</li>
					<li data-slide-to="1" data-target="#carousel-960739">
					</li>
					<li data-slide-to="2" data-target="#carousel-960739">
					</li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img alt="" src="img/1.jpg" />
						<div class="carousel-caption">
							<h4>
								棒球
							</h4>
							<p>
								棒球运动是一种以棒打球为主要特点，集体性、对抗性很强的球类运动项目，在美国、日本尤为盛行。
							</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="img/2.jpg" />
						<div class="carousel-caption">
							<h4>
								冲浪
							</h4>
							<p>
								冲浪是以海浪为动力，利用自身的高超技巧和平衡能力，搏击海浪的一项运动。运动员站立在冲浪板上，或利用腹板、跪板、充气的橡皮垫、划艇、皮艇等驾驭海浪的一项水上运动。
							</p>
						</div>
					</div>
					<div class="item">
						<img alt="" src="img/3.jpg" />
						<div class="carousel-caption">
							<h4>
								自行车
							</h4>
							<p>
								以自行车为工具比赛骑行速度的体育运动。1896年第一届奥林匹克运动会上被列为正式比赛项目。环法赛为最著名的世界自行车锦标赛。
							</p>
						</div>
					</div>
				</div> <a data-slide="prev" href="#carousel-960739" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-960739" class="right carousel-control">›</a>
			</div> 
		</div>
	</div>
</div> -->
<h2>创建模态框（Modal）</h2>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
   开始演示模态框
</button>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
             	  模态框（Modal）标题
            </h4>
         </div>
         <div class="modal-body">
           	 在这里添加一些文本
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary">
              	 提交更改
            </button>
         </div>
      </div>
	</div>
</div>
</body>
</html>

