<%@page import="com.cn.util.network.analyze.TagSimilarityAnalyze"%>
<%@page import="com.cn.util.network.analyze.ApiSimilarityAnalyze"%>
<%@page import="com.cn.util.network.analyze.MashupSimilarityAnalyze"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
pageContext.setAttribute("mashupCount", MashupSimilarityAnalyze.getMashupCount());
pageContext.setAttribute("apiCount", ApiSimilarityAnalyze.getApiCount());
pageContext.setAttribute("tagCount", TagSimilarityAnalyze.getTagsCount());
%>
<!-- 这个是显示图的 -->
<div id="container" style="height: 200px;width: 100%;"></div>
<script>
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
option = {
		title: {
            text: '数量统计图',
            top: 'top',
            left: 'center'
        },
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : ['Mashup', 'Api', 'Tag'],
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'数量',
	            type:'bar',
	            barWidth: '60%',
	            data:[${mashupCount}, ${apiCount}, ${tagCount}]
	        }
	    ]
	};
myChart.setOption(option);
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
</script>
