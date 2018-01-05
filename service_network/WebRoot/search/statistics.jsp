<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Statistics-Service Network</title>
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
   

    
</head>

<body>

<div class="container">

   <%@ include file="header.jsp" %>
	<nav><!-- Define the navigation menu 异构网络 -->
        <ul>
            <li><a href="keywordsearch.html" target="_blank">Home</a></li>
            <li><a href="heterogeneous_network.jsp" target="_blank">Heterogeneous Network</a></li>
            <li><a href="homogeneous_network.jsp" target="_blank">Homogeneous Network</a></li>
            <li class="active"><a href="statistics.jsp">Statistics</a></li>
            
        </ul>
    </nav>
    <div id="submain"><!-- Define submain content section -->
        <section id="subcontent"><!-- Define the content section #2 -->
           <div id="statisticsDiv">
           <ul>
           <li><a href="javascript:void()" onclick="showDiv('statisticsDiv1','statistics/statistics1.jsp')">1.Mashup总数、API总数、Tag总数</a></li>
           <div id="statisticsDiv1" style="display: none ;width: 50%;" >
           </div>
           <hr style="height:1px;border:none;border-top:1px dashed;" />
           <li><a href="javascript:void()" onclick="showDiv('statisticsDiv2','statistics/Statistics2Servlet')">2.Mashup包含的API数量分布及包含最多API的前30个Mashup</a></li>
           <div id="statisticsDiv2" style="display: none ;width: 99.8%;" >
           </div>
            <hr style="height:1px;border:none;border-top:1px dashed;" />
           <li><a href="javascript:void()" onclick="showDiv('statisticsDiv3','statistics/Statistics3Servlet')">3.被Mashup使用的API数量、平均使用次数、使用次数分布、使用最多的前30个API</a></li>
            <div id="statisticsDiv3" style="display: none ;width: 99.8%;" >
           </div>
            <hr style="height:1px;border:none;border-top:1px dashed;" />
           <li><a href="javascript:void()" onclick="showDiv('statisticsDiv4','statistics/Statistics4Servlet')">4.API的类别数量以及每个类别拥有的API数量</a></li>
           <div id="statisticsDiv4" style="display: none ;width: 99.8%;" >
           </div>
           <hr style="height:1px;border:none;border-top:1px dashed;" />
           <li><a href="javascript:void()" onclick="showDiv('statisticsDiv5','statistics/Statistics5Servlet')">5.被API使用的Tag数量、平均使用次数、使用次数分布、使用最多的前30个Tag</a></li>
           <div id="statisticsDiv5" style="display: none ;width: 99.8%;" >
           </div>
           <hr style="height:1px;border:none;border-top:1px dashed;" />
           <li><a href="javascript:void()" onclick="showDiv('statisticsDiv6','statistics/Statistics6Servlet')">6.被Mashup使用的Tag数量、平均使用次数、使用次数分布、使用最多的前30个Tag</a></li>
            <div id="statisticsDiv6" style="display: none ;width: 99.8%;" >
           </div>
            <hr style="height:1px;border:none;border-top:1px dashed;" />
           <li>7.Mashup、API、Provider的地理位置分布</li>
           </ul>
           </div>
        </section>
    </div>

    <%@ include file="footer.jsp" %>

</div>
<script type="text/javascript">
function showDiv(divName,_html){
	$("#"+divName).show();
	$("#"+divName).load(_html);
}
</script>
</body>
</html>