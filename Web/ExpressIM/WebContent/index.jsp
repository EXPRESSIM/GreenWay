<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高速公路绿色通道稽查系统</title>
<style type="text/css">
        .app-background 
        {
            background-color:rgb(53,109,186);
            background-image:url('resource/images/Wallpaper.jpg');
            background-size:cover; 
            background-repeat:no-repeat;
            overflow:hidden;
        }
        
        .app-statusbar-left
        {
            background-image:url('resource/images/status_left.gif');
            background-repeat:no-repeat;
            width:24px;
            height:25px;
        }
        
         .app-statusbar-divide
        {
            background-image:url('resource/images/status_divider.jpg');
            background-repeat:repeat-x;
            height:25px;
            width:2px;
        }
        
        .app-statusbar-back
        {
            background-image:url('resource/images/status_back.jpg');
            background-repeat:repeat-x;
            height:25px;
            font-size:14px;
            font-weight:bold;
            color:#555555;
            font-family:Arial;
        }
        
       .app-statusbar-icon
        {
            background-image:url('resource/images/star_red.gif');
            background-repeat:no-repeat;
            height:16px;
            font-size:16px;
        }
        .app-logo 
        {
            background-image:url('resource/images/stardust.png');
            width:860px;
            height:129px;
        }
    </style>
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
    <script language="javascript" src="includes/app/data/data-grid-config.js" type="text/javascript" ></script>
</head>
<body class="app-background">
	<%
		if (session.getAttribute("logon_user") == null) {
			response.sendRedirect("logon.jsp");
		}
	%>
  <script  language="javascript" type="text/javascript">
  	$(document).ready(function () {
        var menu = new ExpressIM.Menu({menuType:"admin-menu"});
        menu.render($('#menuContainer'));
    });
  </script>
  <div style="position:absolute;left:80px;top:20px;">
      <!-- <img  src="resource/images/" width="127" height="69" />  -->
  </div>
  <div id="menuContainer" style="position:relative;left:80px;top:100px;"></div>
      <div id="statusBar" style="position:absolute;right:0px;bottom:0px;">
        <table cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td>
                    <div class="app-statusbar-left"></div>
                </td>
                 <td>
                    <div class="app-statusbar-back" style="width:30px;"><div class="app-statusbar-icon"  style="position:relative;top:5px;"></div></div>
                </td>
                 <td>
                    <div class="app-statusbar-back" style="width:400px;"><span style="padding-top:10px;">高速公路绿色通道稽查系统</span></div>
                </td>
            </tr>
        </table>
    </div>
    <div style="position:absolute;left:10px;bottom:5px;">
            <img src="resource/images/stardust.png" width="600" height="80" />
    </div>

    <div id="taskContainer"></div>
    <div id="dialogContainer"></div>
</body>
</html>