<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!-- Jstl and config start -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="app" scope="page" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Steve Admin</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
		  <script src="js/excanvas.min.js"></script>
            <script src="js/jquery.min.js"></script>
            <script src="js/jquery.ui.custom.js"></script>
          <script src="js/bootstrap.min.js"></script>
		 <script type="text/javascript">
		 var myJ=jQuery.noConflict();
		 myJ(function() {
			 myJ('button#myButton').on('click',function(){
				 myJ.ajax({
					    type: "POST",
					    url: "${app}/example/quickCopyClass.action",
					    data: myJ('form#myForm').serialize(),
					    statusCode: {
					        500: function() {
					          alert("unexpected system error");
					        }
					    },
					    timeout: 5000,
					    //超时会在error处理
					    success: function(jsonMsg) {
					    	alert("Request success"+jsonMsg);
					    },
					    error: function(jqXHR, textStatus, errorThrown) {
					    	alert( "Request failed: " + textStatus );
					    }
					});
				});
		 });
		</script>
	</head>
	<body>
		
		
		<div id="header">
			<h1><a href="./dashboard.html">Steve Admin</a></h1>		
		</div>
		
		<div id="search">
			<input type="text" placeholder="Search here..."/><button type="submit" class="tip-right" title="Search"><i class="icon-search icon-white"></i></button>
		</div>
		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
                <li class="btn btn-inverse" ><a title="" href="#"><i class="icon icon-user"></i> <span class="text">Profile</span></a></li>
                <li class="btn btn-inverse dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Messages</span> <span class="label label-important">5</span> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a class="sAdd" title="" href="#">new message</a></li>
                        <li><a class="sInbox" title="" href="#">inbox</a></li>
                        <li><a class="sOutbox" title="" href="#">outbox</a></li>
                        <li><a class="sTrash" title="" href="#">trash</a></li>
                    </ul>
                </li>
                <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li>
                <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
            </ul>
        </div>
            
		<div id="sidebar">
			<a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
			<ul>
				<li class="active"><a href="../../index.jsp"><i class="icon icon-home"></i> <span>Dashboard</span></a></li>
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i> <span>Form elements</span> <span class="label">3</span></a>
					<ul>
						<li><a href="form-common.html">Common elements</a></li>
						<li><a href="form-validation.html">Validation</a></li>
						<li><a href="form-wizard.html">Wizard</a></li>
					</ul>
				</li>
				<li><a href="dashboard.html"><i class="icon icon-tint"></i> <span>Buttons &amp; icons</span></a></li>
				<li><a href="buttons.html"><i class="icon icon-tint"></i> <span>Buttons &amp; icons</span></a></li>
				<li><a href="interface.html"><i class="icon icon-pencil"></i> <span>Interface elements</span></a></li>
				<li><a href="tables.html"><i class="icon icon-th"></i> <span>Tables</span></a></li>
				<li><a href="grid.html"><i class="icon icon-th-list"></i> <span>Grid Layout</span></a></li>
				<li class="submenu">
					<a href="#"><i class="icon icon-file"></i> <span>Sample pages</span> <span class="label">4</span></a>
					<ul>
						<li><a href="invoice.html">Invoice</a></li>
						<li><a href="chat.html">Support chat</a></li>
						<li><a href="calendar.html">Calendar</a></li>
						<li><a href="gallery.html">Gallery</a></li>
					</ul>
				</li>
				<li>
					<a href="charts.html"><i class="icon icon-signal"></i> <span>Charts &amp; graphs</span></a>
				</li>
				<li>
					<a href="widgets.html"><i class="icon icon-inbox"></i> <span>Widgets</span></a>
				</li>
			</ul>
		
		</div>
		
		<div id="style-switcher">
			<i class="icon-arrow-left icon-white"></i>
			<span>Style:</span>
			<a href="#grey" style="background-color: #555555;border-color: #aaaaaa;"></a>
			<a href="#blue" style="background-color: #2D2F57;"></a>
			<a href="#red" style="background-color: #673232;"></a>
		</div>
		
		<div id="content">
			<div id="content-header">
				<h1>Dashboard</h1>
				<div class="btn-group">
					<a class="btn btn-large tip-bottom" title="Manage Files"><i class="icon-file"></i></a>
					<a class="btn btn-large tip-bottom" title="Manage Users"><i class="icon-user"></i></a>
					<a class="btn btn-large tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span class="label label-important">5</span></a>
					<a class="btn btn-large tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a>
				</div>
			</div>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="current">Dashboard</a>
			</div>
		<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>QuickCopyClass</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="#" method="get" id="myForm" class="form-horizontal">
									<div class="control-group">
										<label class="control-label">Select input</label>
										<div class="controls">
											<select>
												<option>ITMS CORE</option>
												<option>ITMS UI</option>
												<option>WATERMELON</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Eclipse absolute path</label>
										<div class="controls">
											<input type="text" id="javaFilePath" name="javaFilePath" placeholder="D:\programTool\myGit\junio\src\main\java\com\foo\common\base\utils\FooUtils.java" />
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" id="myButton" class="btn btn-primary">Go!</button>
									</div>
								</form>
							</div>
						</div>						
					</div>
				</div>
			</div>
</div>
	</body>
</html>
