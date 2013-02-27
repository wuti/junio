<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	//命名空间
	sy.ns('SysUser.Reg');

	SysUser.Reg.next2=function(){
		$('#user_reg_p1').panel('refresh','page/user/userRegister2.jsp').panel('setTitle','基本资料填写');
	}
	SysUser.Reg.next3=function(){
		$('#user_reg_form2').form('submit',{
			url: 'user/addUserBaseInfo.action',
			onSubmit:function(){return $(this).form('validate');},
			success:function(){
				$('#user_reg_p1').panel('refresh','page/user/userRegister3.html').panel('setTitle','账户资料填写');
			}
		});
		
	}
	SysUser.Reg.finish=function(){
		
	}
	$(function() {
		$(":radio").click(function(){ 
			var selected = $(":radio:checked").val();
			if(selected == 'yes'){
				$('#user_reg1_next').linkbutton('enable');
			}else{
				$('#user_reg1_next').linkbutton('disable');
			}
		});
	});
</script>

<div style="height:300px;background:#fafafa;padding:5px;">
		<div id="user_reg_p1" class="easyui-panel" style="width:630px;height:400px;padding:10px;"
				title="会员注册" iconCls="icon-save"
				collapsible="false" >
			<textarea name="message" style="width:590px;height:300px;">请仔细阅读下面的协议，只有接受协议才能继续进行注册。 
 1．服务条款的确认和接纳
　　公司用户服务的所有权和运作权归公司拥有。公司所提供的服务将按照有关章程、服务条款和操作规则严格执行。注册一经提交,即表示您同意我们的服务条款，并且已经阅读并理解我们的注册协议。
 2． 公司服务简介
　　公司通过国际互联网为用户提供在线办公网络服务。
　　用户同意： 
　　1)提供及时、详尽及准确的个人资料。 
　　2)不断更新注册资料，符合及时、详尽、准确的要求。所有原始键入的资料将引用为注册资料。 
　　3)用户同意遵守《中华人民共和国保守国家秘密法》、《中华人民共和国计算机信息系统安全保护条例》、《计算机软件保护条例》等有关计算机及互联网规定的法律和法规、实施办法。在任何情况下，公司合理地认为用户的行为可能违反上述法律、法规，公司可以在任何时候，不经事先通知终止向该用户提供服务。用户应了解国际互联网的无国界性，应特别注意遵守当地所有有关的法律和法规。
 3． 服务条款的修改
　　公司会不定时地修改服务条款，服务条款一旦发生变动，将会在相关页面上提示修改内容。
 4． 用户隐私制度
　　尊重用户个人隐私是公司的 基本政策。公司不会公开、编辑或透露用户的邮件内容，除非有法律许可要求，或公司在诚信的基础上认为透露这些信件在以下三种情况是必要的： 
　　1)遵守有关法律规定，遵从合法服务程序。 
　　2)保持维护公司的商标所有权。 
　　3)在紧急情况下竭力维护用户个人和社会大众的隐私安全。 
　　4)符合其他相关的要求。 
 5．用户的帐号，密码和安全性
　　一旦注册成功成为公司用户，您将得到一个密码和帐号。如果您不保管好自己的帐号和密码安全，将对因此产生的后果负全部责任。另外，每个用户都要对其帐户中的所有活动和事件负全责。您可随时根据指示改变您的密码。用户同意若发现任何非法使用用户帐号或安全漏洞的情况，立即通知公司。
 6．有限责任
　　公司对任何直接、间接、偶然、特殊及继起的损害不负责任，这些损害来自：不正当使用邮件服务，在网上购买商品或服务，在网上进行交易，非法使用邮件服务或用户传送的信息所变动。
 7．用户责任 
　　用户单独承担传输内容的责任。用户必须遵循： 
　　1)从中国境内向外传输技术性资料时必须符合中国有关法规。 
　　2)使用邮件服务不作非法用途。 
　　3)不干扰或混乱网络服务。 
　　4)不在留言簿发表任何与政治相关的信息。 
　　5)遵守所有使用邮件服务的网络协议、规定、程序和惯例。
　　6)不得利用本站危害国家安全、泄露国家秘密，不得侵犯国家社会集体的和公民的合法权益。
　　7)不得利用本站制作、复制和传播下列信息： 
　　　1、煽动抗拒、破坏宪法和法律、行政法规实施的；
　　　2、煽动颠覆国家政权，推翻社会主义制度的；
　　　3、煽动分裂国家、破坏国家统一的；
　　　4、煽动民族仇恨、民族歧视，破坏民族团结的；
　　　5、捏造或者歪曲事实，散布谣言，扰乱社会秩序的；
　　　6、宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖、教唆犯罪的；
　　　7、公然侮辱他人或者捏造事实诽谤他人的，或者进行其他恶意攻击的；
　　　8、损害国家机关信誉的；
　　　9、其他违反宪法和法律行政法规的；
　　　10、进行商业广告行为的。
　　用户不能利用邮件服务作连锁邮件，垃圾邮件或分发给任何未经允许接收信件的人。用户须承诺不传输任何非法的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的和淫秽的信息资料。另外，用户也不能传输任何教唆他人构成犯罪行为的资料；不能传输长国内不利条件和涉及国家安全的资料；不能传输任何不符合当地法规、国家法律和国际法 律的资料。未经许可而非法进入其它电脑系统是禁止的。若用户的行为不符合以上的条款，公司将取消用户服务帐号。
 8．附加信息服务
　　用户在享用公司提供的免费服务的同时，同意接受公司提供的各类附加信息服务。
 9．解释权
　　本注册协议的解释权归公司所有。如果其中有任何条款与国家的有关法律相抵触，则以国家法律的明文规定为准。
			</textarea>
			</br>
			<input name="agree" type="radio" class="easyui-validatebox" required="true" value="yes">同意
			<input name="agree" type="radio" class="easyui-validatebox" required="true" value="no">不同意
			
			<a href="#" id="user_reg1_next" class="easyui-linkbutton" disabled="true" iconCls="icon-ok" onclick="SysUser.Reg.next2()">下一步</a>
		</div>
	</div>
	

