<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- 选项卡-->
         <section id="TabControlcontent" style="height: 5%;width: 100%;"><!-- Define the featured content section -->
            <ul>
                <li>
                    <a class="more" href="javascript:void()" onclick="loadhtml('heterogeneous_mat.jsp')" >MASHUP-API-TAG</a>
                </li>
                <li>
                    <a class="more" href="javascript:void()" onclick="loadhtml('heterogeneous_ma.jsp')" >MASHUP-API</a>
                </li>
                <li>
                    <a class="more" href="javascript:void()" onclick="loadhtml('heterogeneous_mt.jsp')" >MASHUP-TAG</a>
                </li>
                <li class="active">
                    <a class="more" href="javascript:void()" onclick="loadhtml('heterogeneous_at.jsp')" >API-TAG</a>
                </li>
            </ul>
        </section>

<!-- 这个是显示a-t的异构网络图   -->
<div id="atcontainer" style="height: 700px;width: 100%;"></div>
Related characteristics indicators:
<div id="tongjiDiv" style="height: 19.8%;width: 100%;border-color: #e7e7e7;border-style: solid;border-width: 1px;">
				<br>
				下载数据文件，然后可以使用Cytoscape软件来分析该复杂网络的指标特性。<br/>
				<a href="javascript:void();" onclick="filedown()">度与度分布、聚集系数、平均最短路径长度、网络密度与集中性等分析数据下载</a><br>
				
</div>
<script type="text/javascript">
function filedown(){
	var url="AnalyzeDataDownLoad?fileName=api-tag.txt";
	window.location.href=url;
}
var dom = document.getElementById("atcontainer");
var myChart = echarts.init(dom);
var app = {};
option = null;
myChart.showLoading();
$.getJSON('ApiTagRelationToJson', function (webkitDep) {
    myChart.hideLoading();

    option = {
    		title: {
                text: 'Heterogeneous Network',
                subtext: 'Api-Tag',
                top: 'top',
                left: 'left'
            },
            
			legend: {
	            data: ['API', 'TAG']
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
});
</script>