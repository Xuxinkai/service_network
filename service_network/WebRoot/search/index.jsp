<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html><head>
    <title>Home-Service Network</title>
    <meta charset="utf-8">
	
    <!-- Link styles -->
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen">

    <!-- Link scripts -->
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>   
<script type="text/javascript">
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
 <style type="text/css">
 p#back-to-top{
  position:fixed;
  bottom:100px;
  right:80px;
}
p#back-to-top a{
  text-align:center;
  text-decoration:none;
  color:#d1d1d1;
  display:block;
  width:30px;
  /*使用CSS3中的transition属性给跳转链接中的文字添加渐变效果*/
  -moz-transition:color1s;
  -webkit-transition:color1s;
  -o-transition:color1s;
}
p#back-to-top a:hover{
  color:#979797;
}
p#back-to-top a span{
  background:#d1d1d1 url(images/arrow_up.png) no-repeat center center;
  border-radius:6px;
  display:block;
  height:60px;
  width:80px;
  margin-bottom:5px;
  /*使用CSS3中的transition属性给<span>标签背景颜色添加渐变效果*/
  -moz-transition:background1s;
  -webkit-transition:background1s;
  -o-transition:background1s;
}
#back-to-top a:hover span{
  background:#979797 url(images/arrow_up.png) no-repeat center center;
}
 
 </style>
 <script type="text/javascript">
$(document).ready(function(){
 //首先将#back-to-top隐藏
 $("#back-to-top").hide();
 //当滚动条的位置处于距顶部600像素以下时，跳转链接出现，否则消失
 $(function () {
  $(window).scroll(function(){
   if ($(window).scrollTop()>600){
    $("#back-to-top").fadeIn(500);
   }else{
    $("#back-to-top").fadeOut(500);
   }
 });
 //当点击跳转链接后，回到页面顶部位置
 $("#back-to-top").click(function(){
  $('body,html').animate({scrollTop:0},500);
   return false;
  });
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
    <div id="mainDiv"><!-- 主要显示的界面 -->
    <div id="main"><!-- Define the main content section -->
        <section id="content"><!-- Define the featured content section -->
            <ul>
            	<li>
                    <h4>Key Word：</h4>
                    <p>${keyWord }</p>
                </li>
                <li>
                    <h4>Search Statistics：</h4>
                    <p>A total of ${fn:length(listApi)+fn:length(listMashup)} results</p>
                </li>
                <li>
                    <a href="#apiresult" name="apiresult"><h4>Web API：</h4><p>${fn:length(listApi)} results</p></a>
                </li>
                <li>
                    <a href="#mashupresult" name="mashupresult"><h4>MASHUP：</h4><p>${fn:length(listMashup)} results</p></a>
                </li>
            </ul>
        </section>
    </div>

    <div id="submain"><!-- Define submain content section -->
        <section id="subcontent"><!-- Define the content section #2 -->
            <div  id="apiresult">
            
                <ul >
               		<c:if test="${fn:length(listApi) != 0}"><li><h2>Web API：</h2></li></c:if> 
               		<c:forEach items="${listApi }" var="api" >
               		<hr style="height:1px;border:none;border-top:1px dashed;" />
                    <li>
                        <a target="_blank" href="apiInfomation.html?api_name=${api.api_name }&api_id=${api.api_id }" style="text-decoration:none;"><h3>${api.api_name }</h3></a>
                        <p><span class="more">Category</span> ${api.api_category } 
                        | <span class="more">Tags</span> ${api.api_tags } 
                        | <span class="more">Score</span> ${api.score }</p>
                        <p><span class="more">Comments</span> ${api.api_comments }... ...</p>
                        
                        <span class="more">URL</span> <a  href="${api.api_url }" target="_blank" >${api.api_url }</a>
                    </li>
                   </c:forEach>
                   
                   <li><center><a href="javascript:void(0);" onclick="addMoreApi()">加载更多</a></center></li>
                </ul>
                </div>
             </div>
             <div id="mashupresult">
                <ul>
               		<c:if test="${fn:length(listMashup) != 0}"><li><h2>MASHUP：</h2></li></c:if>
               		<c:forEach items="${listMashup }" var="mashup" begin="0" end="4">
               		<hr style="height:1px;border:none;border-top:1px dashed;" />
                    <li>
                        <h3>${mashup.mashup_name }</h3><br>
                        <span class="more">Score</span>${mashup.score }<br>
                        <span class="more">Tags</span>${mashup.mashup_tags }<br>
                        <span class="more">APIs</span> 
                        <c:forTokens items="${mashup.mashup_apis}" delims="+" var="api">
                        <a href="keywordsearch.html?keyWord=${api }">${api }</a>,
                        </c:forTokens>
                    </li>
                    </c:forEach>
                    <li><center><span>加载更多</span></center></li>
                </ul>
            </div>
        </section>
    </div>
    <%@ include file="footer.jsp" %>
    </div>
    

</div>
<p id="back-to-top"><a href="#top"><span></span></a></p>

</body>
</html>
