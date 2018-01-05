<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
<span>有<font color="red">${apiCountUsedByMashup }</font>个API被Mashup使用过<br>平均使用次数为：<font color="red">${averageUseofAPI }</font>次<br></span>
</div>
<!-- 被Mashup使用的API使用次数分布-->
<div id="container5" style="height: 250px;width: 100%;"></div>
<!-- 被Mashup使用最多的前30个API -->
<div id="container4" style="height: 200px;width: 100%;"></div>
<script>
var dom4 = document.getElementById("container4");
var myChart4 = echarts.init(dom4);
var app4 = {};
option4 = null;
option4 = {
		title: {
            text: '被Mashup使用最多的前30个API',
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
	            name:'被使用的次数',
	            type:'bar',
	            barWidth: '60%',
	            data:[${seriesData}]
	        }
	    ]
	};
myChart4.setOption(option4);
if (option4 && typeof option4 === "object") {
    myChart4.setOption(option4, true);
}
</script>
<script>
var dom5 = document.getElementById("container5");
var myChart5 = echarts.init(dom5);
var app5 = {};
option5 = null;
option5 = {
	    title : {
	        text: '被Mashup使用的API使用次数分布',
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
	        data: ['没有被Mashup使用','1次','2-5次','6-15次','16-99次','100次及100次以上']
	    },
	    series : [
	        {
	            name: '被Mashup使用次数',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
					{value:${zero}, name:'没有被Mashup使用'},
	                {value:${one}, name:'1次'},
	                {value:${twoFive}, name:'2-5次'},
	                {value:${sixFifteen}, name:'6-15次'},
	                {value:${sixteenNinetyNine}, name:'16-99次'},
	                {value:${upOneHundred}, name:'100次及100次以上'}
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

myChart5.setOption(option5);
if (option5 && typeof option5 === "object") {
    myChart5.setOption(option5, true);
}
</script>
