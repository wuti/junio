<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/jsp/head-1.8.jsp"%>

<body>

	<form id="myForm">
		<fieldset>
			<legend>Legend</legend>
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
			<label>The last submit</label>
			<button type="button" id="myButton" class="btn" >COPY CLASS</button>
		</fieldset>
	</form>

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
					          console.log("unexpected system error");
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
</body>
</html>