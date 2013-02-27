<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
$('#user_reg_form2_dealerUserNo').val(currentUser);
	</script>
<form id="user_reg_form2" method="post">
			<div style="padding:20px;" >
				<table cellpadding="0" cellspacing="0" class="form-table">
					<tr>
						<td style="width:80px">经销商用户号:</td>
						<td style="width:200px">
							<input name="user.dealerUserNo" style="width:150px" 
							class="easyui-validatebox" required="true"
							id="user_reg_form2_dealerUserNo"></input>
							<font color="red">*</font>
						</td>
						<td style="width:80px">用户名:</td>
						<td style="width:200px">
							<input name="user.username" style="width:150px" class="easyui-validatebox" required="true"></input>
							<font color="red">*</font>
						</td>
						
					</tr>
					<tr>
						<td >姓名:</td>
						<td>
							<input name="user.name" style="width:150px" class="easyui-validatebox" required="true"></input>
							<font color="red">*</font>
						</td>
						<td>电子邮件:</td>
						<td><input name="user.email" style="width:150px"  class="easyui-validatebox" validType="email" ></input></td>
					</tr>
					<tr>
						<td>常用手机:</td>
						<td>
						<input name="user.mobilephone" style="width:150px" class="easyui-validatebox" required="true"></input>
						<font color="red">*</font>
						</td>
						<td>固定电话:</td>
						<td><input name="user.telephone" style="width:150px"></input></td>
					</tr>
					<tr>
						<td>邮编:</td>
						<td><input name="user.postcode" style="width:150px"></input></td>
						<td>常用QQ:</td>
						<td><input name="user.qq" style="width:150px"></input></td>
					</tr>
					<tr>
						<td>联系地址:</td>
						<td colspan="3">
						<input name="user.address" style="width:430px" class="easyui-validatebox" required="true"></input>
						<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td>售后专卖店:</td>
						<td colspan="3"><input name="user.specialtyStore" style="width:430px"></input></td>
					</tr>
				</table>
			</div>
		
		</form>
		<div style="text-align:center">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="SysUser.Reg.next3()">下一步</a>
		</div>
