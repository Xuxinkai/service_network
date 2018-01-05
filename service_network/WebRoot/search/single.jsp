<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>${api.api_name }-Service Network</title>
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
    <script>
        $(window).load(function() {
            $('#slider').nivoSlider({
                effect:'random',
                slices:15,
                boxCols:8,
                boxRows:8,
                animSpeed:500,
                pauseTime:4000,
                directionNav:false,
                directionNavHide:false,
                controlNav:true,
                captionOpacity:0.9
            });
        });
    </script>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="js/html5.js"></script>
    <![endif]-->
</head>
<body>

<div class="container">

   <%@ include file="header.jsp" %>
	<nav><!-- Define the navigation menu -->
        <ul>
            <li class="active"><a href="keywordsearch.html">Home</a></li>
            <li><a href="heterogeneous_network.jsp" target="_blank">Heterogeneous Network</a></li>
            <li><a href="homogeneous_network.jsp" target="_blank">Homogeneous Network</a></li>
            <li><a href="statistics.jsp"  target="_blank">Statistics</a></li>
        </ul>
    </nav>
    
    <div id="submain"><!-- Define submain content section -->
        <section id="subcontent"><!-- Define the content section #2 -->
            <div id="left">
                <ul>
                    <li>
                        <h3>Web API</h3><br>
                        <p><span class="more">API Name</span> ${api.api_name } </p>
                        <p><span class="more">Category</span>  ${api.api_category }  </p>
                        <p><span class="more">The tpo ten similar apis:</span><br>  
                        <c:forEach items="${listwar }" var="war">
                        <a  target="_blank" style="text-decoration:none;" href="apiInfomation.html?api_name=${war.api_two_name }&api_id=${war.api_two }">${war.api_two_name }</a>:${war.weight }<br>
                        </c:forEach>
						 </p>
                        <p><span class="more">Tags(${tagSum})</span>  ${api.api_tags }  </p>
                        <p><span class="more">Mashups(${mashupSum })</span> ${api.api_mashups }</p>
                        <p><span class="more">Comments</span>${api.api_comments }</p>
                        
                        <span class="more">URL</span> <a  href="${api.api_url }" target="_blank" >${api.api_url }</a>
                    </li>
                    
                </ul>
            </div>
            <div id="right">

                <div class="block"><!-- Archives block -->
                    <h3>Network Graph</h3>
                    <div id="GraphDiv" style="border-color: #e7e7e7;border-style: solid;border-width: 1px;height: 500px;width: 100%">
                    <div id="container1" style="height: 100%;width: 100%"></div>
                    <!--  img alt="" src="images/networkgraphtemp.png" width="100%" height="100%">-->
                    </div>
                </div>

                

            </div>
        </section>
    </div>

    <%@ include file="footer.jsp" %>

</div>
<script type="text/javascript">

var dom = document.getElementById("container1");
var myChart = echarts.init(dom);
var app = {};
option = null;
myChart.showLoading();
var webkitDep = ${jsonData};
	myChart.hideLoading();
	
	option = {
			legend: {
	            data: ['API', 'TAG', 'MASHUP']
	        },
			series:[{
				type: 'graph',
	            layout: 'force',
	            animation: false,
	            roam: true,//滑轮缩放的开关
	            draggable: true,//可以拖动
	            
	            data: webkitDep.nodes.map(function (node, idx) {
	                node.id = idx;
	                return node;
	            }),
	            categories: webkitDep.categories,
	            label: {
	                normal: {
	                    position: 'right',
	                    formatter: '{b}',
	                    show:true,
	                    textStyle: {
	                    	color:'#000'
	                    }
	                    
	                }
	            },
	            force: {
	                // initLayout: 'circular'
	                // repulsion: 20,
	                edgeLength: 5,
	                repulsion: 20,
	                gravity: 0.2
	            },
	            edges: webkitDep.links
			}]
	};
	myChart.setOption(option);
	

if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
</script>
</body>
</html>