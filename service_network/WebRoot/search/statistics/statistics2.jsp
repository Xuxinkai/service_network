<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 这个是显示图的  Mashup包含的API数量分布-->
<div id="container3" style="height: 250px;width: 100%;"></div>
<!-- 这个是显示图的  Mashup包含最多API的前30个Mashup -->
<div id="container2" style="height: 200px;width: 100%;"></div>
<script>
var dom2 = document.getElementById("container2");
var myChart2 = echarts.init(dom2);
var app2 = {};
option2 = null;
option2 = {
		title: {
            text: '包含最多API的前30个Mashup',
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
	            data : [${xAxisData}],
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
	            name:'包含api的数量',
	            type:'bar',
	            barWidth: '60%',
	            data:[${seriesData}]
	        }
	    ]
	};
myChart2.setOption(option2);
if (option2 && typeof option2 === "object") {
    myChart2.setOption(option2, true);
}
</script>
<script>
var dom3 = document.getElementById("container3");
var myChart3 = echarts.init(dom3);
var app3 = {};
option3 = null;
option3 = {
	    title : {
	        text: 'Mashup包含的API数量分布',
	        subtext: '数据来源于本地数据库',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: ['没使用API','包含1个','2-8个','9-15个','包含16个及16个以上']
	    },
	    series : [
	        {
	            name: '使用API个数',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
					{value:${zero}, name:'没使用API'},
	                {value:${one}, name:'包含1个'},
	                {value:${twoEight}, name:'2-8个'},
	                {value:${NineFifteen}, name:'9-15个'},
	                {value:${upSixteen}, name:'包含16个及16个以上'}
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};

myChart3.setOption(option3);
if (option3 && typeof option3 === "object") {
    myChart3.setOption(option3, true);
}
</script>
