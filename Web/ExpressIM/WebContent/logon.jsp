<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高速公路绿色通道稽查综合管理系统</title>
<link href="includes/easyui/themes/default/easyui.css" rel="stylesheet"   type="text/css"/>
    <link href="includes/easyui/themes/icon.css" rel="stylesheet"   type="text/css"/>
    <link href="includes/easyui/themes/color.css" rel="stylesheet"   type="text/css"/>
    <link href="includes/app/util/richeditor/jquery.cleditor.css" rel ="stylesheet"  type="text/css"/>
    <link href="includes/app/util/checkbox/css/all.css?v=1.0.2" rel ="stylesheet"  type="text/css"/>
    <link href="includes/app/util/uploadify/uploadify.css" rel ="stylesheet"  type="text/css"/>
    <link href="includes/menu/menu.css" rel ="stylesheet"  type="text/css"/>
    <link href="includes/app/css/main.css" rel ="stylesheet"  type="text/css"/>
    <script language="javascript" src="includes/app/data/city.js" type="text/javascript" ></script>
    <script language="javascript" src="includes/easyui/jquery.min.js" type="text/javascript" ></script>
    <script language="javascript" src="includes/easyui/jquery.easyui.min.js" type="text/javascript"  charset="gbk"></script>
    <script language="javascript" src="includes/app/util/checkbox/icheck.js" type="text/javascript" ></script>
    <script language="javascript" src="includes/app/util/richeditor/jquery.cleditor.js" type="text/javascript" ></script>
    <script language="javascript" src="includes/app/util/uploadify/jquery.uploadify.js" type="text/javascript"></script>
    <script language="javascript" src="includes/app/util/lib.js" type="text/javascript" ></script>
</head>
<body  style="background-color:rgb(228,239,255)">
	<%
		if (session.getAttribute("logon_user") != null) {
			response.sendRedirect("logon.jsp");
		}
	%>
	<div class="window-shadow" style="z-index: 90; position: absolute; top: 20%; left:30%; height: 405px; width: 657px;">
        <div style="position:relative;width:656px;height:401px;z-index:99;" class="logon-window-background "> 
            <img style="position:relative;left:400px;top:170px;width:160px;height:160px;" src="resource/images/login_icon.png"/>
             <span style="position:relative;left:20px;top:220px;width:80px;height:80px;font-size:8pt;color:#777777;">
                Copyright © 2010-2015 Stardust Technology. Version: Beta indoor 1.0.1
             </span>
            <div style="position:relative;top:-100px;left:50px;">
            	<form name="logonForm" method="post" action="logon">
                <table>
                    <tbody>
                        <tr>
                            <td style="font-size:22pt;font-weight:bold;color:#777777;">Expressway IM Administration</td>
                        </tr>
                        <tr>
                            <td style="font-size:16px;font-weight:bold;font-family:微软雅黑;color:#999999;">高速公路绿色通道稽查综合管理系统</td>
                        </tr>
                        <tr style="height:30px;">
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <div style="padding:10px 10px 10px 10px">
                                   <div id='result'></div>
                                </div>
                                <div style="padding:10px 10px 10px 10px"> <input ID="username" name="username" class="easyui-textbox" data-options="iconCls:'icon-man'" style="width:250px;height:30px;"></input></div>
                                <div style="padding:10px 10px 10px 10px"> <input ID="password" name="password" type="password" class="easyui-textbox" data-options="iconCls:'icon-lock'" style="width:250px;height:30px;"></input></div>
                            </td>
                        </tr>
                        <tr>
                            <td align="center"><div style="padding:10px 10px 10px 10px">
                            	<input type="submit" name="button" id="button" value="登录" style="width:160px;height:30px" 
                                    class="logon-btn logon-btn-info">
                            </div></td>
                        </tr>
                    </tbody>
                 </table>
                 </form>
            </div>
        </div>
    </div>
</body>
</html>