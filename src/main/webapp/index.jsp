<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/jsp/head-1.7.1.jsp"%>

<form action="loginAction_login.action" method="post">
	用户编号：<input type="text" name="user.userNo" value="<s:property value="user.userNo" />"/></br>
	用户密码：<input type="password" name="user.password"/></br>
	<font color="red"><s:property value="tip" /></font>
	<input type="submit" />
</form>
<%@ include file="/jsp/foot-1.7.1.jsp"%>