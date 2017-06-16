<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>电子发票平台</title>
<link rel="stylesheet" type="text/css" href="${base}/ui/css/dzfppt.css" />
<script type="text/javascript" src="${base}/ui/js/dzfppt.js"></script>
<script type="text/javascript" src="${base}/ui/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${base}/ui/js/jquery-form.js"></script>
<script type="text/javascript">
$(function(){
	$("#submit").click(function () {
		var options={
			 url:"${base}/hello/getData.do", //form提交数据的地址
			 type:"post", //form提交的方式(method:post/get)
			 //target:"#msg", //服务器返回的响应数据显示在元素(Id)号确定
			 //beforeSubmit:function(){}, //提交前执行的回调函数
			 success:function(data){
				 //alert(data.code);
				 //$().apendJSON.stringify(a);
				 var result = "{<br/>"
					 +'"code"&nbsp;:&nbsp;"' + data.code +'",<br/>'
					 +'"result"&nbsp;:&nbsp;"' + data.result +'",<br/>'
					 +'"message"&nbsp;:&nbsp;"' + data.msg +'"<br/>'
				 	+"}";
				 $("#msg").empty();
				 $("#msg").append(result);
			 }, //提交成功后执行的回调函数
			 dataType:"json", //服务器返回数据类型
			 //clearForm:true, //提交成功后是否清空表单中的字段值
			 //restForm:true, //提交成功后是否重置表单中的字段值，即恢复到页面加载时的状态
			 timeout:6000 //设置请求时间，超过该时间后，自动退出请求，单位(毫秒)。
		}
		$("#handlerfrom").ajaxForm(options);
	});
});
</script>
</head>  
<body>  
	<div class="d-mbody">
	
		<div class="d-hbody">
			<div class="d-hbody-content">
				<a href="${base}/index/getindex.do" class="d-hbody-menus">首页</a>
				<a href="${base}/download/getindex.do" class="d-hbody-menus">资料下载</a>
				<a href="${base}/api/getindex.do" class="d-hbody-menus">API</a>
				<a href="${base}/tool/getindex.do" class="d-hbody-menusd">网页测试工具</a>
			</div>
		</div>
		
		<hr/>
		
		<div class="d-mtool">
			<h3>电子发票平台接口调试工具</h3>
			<hr>
			<div class="d-htool-content">
				<div class="content-head">
										此工具旨在帮助接入电子发票平台的企业开发者检测调用【电子发票平台API】时发送的请求参数是否正确，提交相关信息后可获得服务器的验证结果				
				</div>
				<ol>
					<li><b>说明：</b></li>
					<li>1.选择调用的API</li>
					<li>2.在报文文本框中添加完整的报文</li>
					<li>3.点击Test，即可得到相应的调试信息</li>
				</ol>
				
				<div class="content-body">
					<form id="handlerfrom" name="handlerfrom" method="post" action="">
						<div class="content-body-type">
							<label class="content-body-title">一、接口类型：</label>
							<div class="content-body-type-selects">
								<select class="selects-box"  name="impType">
									<#list boxs as box>
										<option value="${box_index+1}">${box}</option>
									</#list>
								</select>
							</div>
						</div>
						<br/>
						<div class="content-body-arg">
							<label class="wangrui">二、参数：</label>
							<br/><br/>
							<div class="content-body-arg-test">
									<div class="body-input">
										<span class="content-body-arg-title">完整xml报文：</span>
										<div class="content-body-arg-body">
											<textarea rows=15" cols="70" name="xmlData">
											</textarea>
										</div><br/>
										<span>调用接口的返回数据数据:</span><br/>
										<div class="tips">
											<span id="msg"></span>
										</div>
									</div>
									<input type="submit" class="unsubmit" id="submit" value="Test">
							</div>
						</div>
					</form>
					<div class="content-result"></div>
				</div>
			</div>
		</div>
	</div>  
</body>
</html>