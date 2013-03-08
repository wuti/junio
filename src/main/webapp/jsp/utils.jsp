<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/jsp/head-1.8.jsp"%>

<body>

	<form id="myForm">
		<fieldset>
			<legend>QUICK DEPLOY AND COPY</legend>
			<label>Select input</label>
			<select name="myPattern">
					<option value="itmsCore">itmsCore</option>
					<option value="itmsUI">itmsUI</option>
			</select>
			<label>sourceFilePath</label>
			<input type="text" id="javaFilePath" name="sourceFilePath"
					placeholder="Java Path here" class="input-xxlarge"/>
			<label>targetFilePath</label>
			<input type="text" id="javaFilePath" name="targetFilePath" value="C:\Users\Steve\Desktop\"
					placeholder="C:\Users\Steve\Desktop\" class="input-xxlarge"/>
			<label>Select opertion</label>
			<select id="myOperation">
					<option value="copy" selected="selected">copy</option>
					<option value="deploy">deploy</option>
			</select>
			<label>Click here</label>
			<button type="button" id="myButton" class="btn" >go</button>
			<label>Status Code here</label>
			<span id="mySpan" class="label label-info">Status here.</span>
		</fieldset>
	</form>

<script type="text/javascript">
	 var myJ=jQuery.noConflict();
	 
		 myJ(function() {
			 myJ('button#myButton').on('click',function(){
				 var myOperation = myJ('select#myOperation').val();
				 var myURL = "";
				 if(myOperation=="copy"){
					 myURL = "${app}/example/quickCopyClass.action"
				 }else if(myOperation=="deploy"){
					 myURL = "${app}/example/quickDeployClass.action"
				 }else{
					 return;
				 }
				 myJ.ajax({
					    type: "POST",
					    url: myURL,
					    data: myJ('form#myForm').serialize(),
					    statusCode: {
					        500: function() {
					         	//myJ('span#mySpan').text("fail");
					        }
					    },
					    timeout: 5000,
					    //超时会在error处理
					    success: function(jsonMsg) {
					    	myJ('span#mySpan').text(myJ.parseJSON(jsonMsg).message);
					    },
					    error: function(jqXHR, textStatus, errorThrown) {
					    	myJ('span#mySpan').text("uncaught server error,check log");
					    }
					});
				});
		 });
</script>
</body>
</html>