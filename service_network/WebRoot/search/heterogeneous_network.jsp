<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Heterogeneous Network-Service Network</title>
    <meta charset="utf-8">

    <!-- Link styles -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen">

    <!-- Link scripts -->
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>  
    <script type="text/javascript" src="js/echarts3/echarts-all-3.js"></script>
    <script type="text/javascript" src="js/echarts3/dataTool.min.js"></script>
    <script type="text/javascript" src="js/echarts3/china.js"></script>
    <script type="text/javascript" src="js/echarts3/world.js"></script>
    <script type="text/javascript" src="js/echarts3/bmap.min.js"></script> 
   

    <!--[if lt IE 9]>
    <script type="text/javascript" src="js/html5.js"></script>
    <![endif]-->
</head>
<script type="text/javascript">
var _html = "heterogeneous_mat.jsp";
function loadhtml(_html){
	 html=_html;//修改当前默认的加载页面
	$("#contentDiv").load(_html);
 }
</script>
<body onload="loadhtml('heterogeneous_mat.jsp')">

<div class="container">

   <%@ include file="header.jsp" %>
	<nav><!-- Define the navigation menu 异构网络 -->
        <ul>
            <li><a href="keywordsearch.html" target="_blank">Home</a></li>
            <li class="active"><a href="heterogeneous_network.jsp">Heterogeneous Network</a></li>
            <li><a href="homogeneous_network.jsp" target="_blank">Homogeneous Network</a></li>
            <li><a href="statistics.jsp"  target="_blank">Statistics</a></li>
        </ul>
    </nav>
    <div id="submain"><!-- Define submain content section -->
        <section id="subcontent"><!-- Define the content section #2 -->
            <div id="heterogeneousNetworkDIV">
          
            <!-- 显示图 -->
              <div id="contentDiv" style="height:99.5%;width: 99.5%;border-color: #e7e7e7;border-style: solid;border-width: 1px;"></div>  
            </div>
        </section>
    </div>

    <%@ include file="footer.jsp" %>

</div>
</body>
</html>