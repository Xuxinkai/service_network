<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- 选项卡-->
         <section id="TabControlcontent" style="height: 5%;width: 100%;"><!-- Define the featured content section -->
            <ul>
                <li>
                    <a class="more" href="javascript:void()" onclick="loadhtml('homogeneous_mashup.jsp')" >MASHUP-MASHUP</a>
                </li>
                <li>
                   <a class="more" href="javascript:void()" onclick="loadhtml('homogeneous_api.jsp')" >API-API</a>
                </li>
                <li class="active">
                    <a class="more" href="javascript:void()" onclick="loadhtml('homogeneous_tag.jsp')" >TAG-TAG</a>
                </li>
            </ul>
        </section>
<!-- 权值-->
<div id="weightdiv" style="height: 5%;width: 100%;">
<center>
WEIGHT:&nbsp;&nbsp;&nbsp;&nbsp;
 API:<input type="text" value="" id="apiweight" name="apiweight" style="width: 50px;"  onchange="changinputvalue('api');"/>&nbsp;&nbsp;
 MASHUP:<input type="text" value="" id="mashupweight" name="mashupweight" style="width: 50px;"  onchange="changinputvalue('mashup');"/>&nbsp;&nbsp;
 EDGE>=
  <select class="select" style="width: 50px;" id="selectEdge"  onclick="changeEdge()">
	<c:forEach begin="1" end="8" var="i"><option value="0.0${i }">0.0${i }</option></c:forEach>
  </select>&nbsp;&nbsp;
  <a class="more" href="javascrip:void()" onclick="submitToChangeNetwork()">OK</a>
</center>
</div>
<!-- 这个是显示tag的同构网络图   默认显示api和mashup的权值都为0.5 -->
<div id="Tagcontainer" style="height: 700px;width: 100%;border-color: #e7e7e7;border-style: solid;border-width: 1px;"></div>
 <!-- 这个是显示该tag同构网络的一些复杂网络相关特性指标（度与度分布、聚集系数、平均最短路径长度、网络密度与集中性） -->
 Related characteristics indicators:
<div id="tongjiDiv" style="height: 19.8%;width: 100%;border-color: #e7e7e7;border-style: solid;border-width: 1px;">
				<br>
				下载数据文件，然后可以使用Cytoscape软件来分析该复杂网络的指标特性。<br/>
				<a href="javascript:void();" onclick="filedown()">度与度分布、聚集系数、平均最短路径长度、网络密度与集中性等分析数据下载</a><br>
				
</div>
<script type="text/javascript">
var apiweight = 0.5;
var mashupweight = 0.5;
var edge = 0.01;//以上是默认的数据
$("#apiweight").val(apiweight);
$("#mashupweight").val(mashupweight);
function changinputvalue(type){
	if(type == 'api'){
		var value = 1 - $("#apiweight").val();
		apiweight = $("#apiweight").val();
		mashupweight = value.toFixed(1);
		$("#mashupweight").val(mashupweight);
	}
	if(type == 'mashup'){
		var value = 1 - $("#mashupweight").val();
		mashupweight = $("#mashupweight").val();
		apiweight = value.toFixed(1);
		$("#apiweight").val(apiweight);
	}
}
function changeEdge(){
	edge = $("#selectEdge").val();
}

var dom = document.getElementById("Tagcontainer");
var myChart = echarts.init(dom);
var app = {};
option = null;
myChart.showLoading();
$.getJSON('WebTagRelationToJson?_apiweight='+apiweight+'&_mashupweight='+mashupweight+'&_edge='+edge,function(webkitDep){
	myChart.hideLoading();
	option = {
			title: {
                text: 'The Tag`s Homogeneous Network',
                top: 'top',
                left: 'center'
            },
			series:[{
				type: 'graph',
	            layout: 'force',
	            animation: false,
	            roam: true,//滑轮缩放的开关
	            
	            draggable: true,//可以拖动
	            data: webkitDep.nodes.map(function (node) {
                    return {
                        id: node.tag_id,
                        name: node.tag_name,
                        symbolSize: 5,
                        itemStyle: {
                            normal: {
                                color: "#ff0000"
                            }
                        }
                    };
                }),
                edges: webkitDep.edges.map(function (edge) {
                		return {
                            source: edge.tag_id_one,
                            target: edge.tag_id_two,
                            value: edge.edge,
                            //设置边显示的样式
                            label: {
                            	normal:{
                            		show:true,//显示边的值
                            		position:'middle',//值显示的位置
                            		formatter: '{c}'//值显示的格式
                            	}
                            }
                        };
                    
                }),
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
                    //initLayout: 'circular',
                    //repulsion: 20,
                    edgeLength: 5,
                    repulsion: 20,
                    gravity: 0.2
                },
                edgeSymbol: ['arrow', 'arrow'],
                lineStyle: {
                    normal: {
                        width: 1,
                        curveness: 0.0,
                        opacity:1
                    }
                }
	            
			}]
	};
	myChart.setOption(option);
	
});
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
function submitToChangeNetwork(){
	option = null;
	myChart.showLoading();
	$.getJSON('WebTagRelationToJson?_apiweight='+apiweight+'&_mashupweight='+mashupweight+'&_edge='+edge,function(webkitDep){
		myChart.hideLoading();
		option = {
				title: {
	                text: 'The Tag`s Homogeneous Network',
	                top: 'top',
	                left: 'center'
	            },
				series:[{
					type: 'graph',
		            layout: 'force',
		            animation: false,
		            roam: true,//滑轮缩放的开关
		            
		            draggable: true,//可以拖动
		            data: webkitDep.nodes.map(function (node) {
	                    return {
	                        id: node.tag_id,
	                        name: node.tag_name,
	                        symbolSize: 5,
	                        itemStyle: {
	                            normal: {
	                                color: "#ff0000"
	                            }
	                        }
	                    };
	                }),
	                edges: webkitDep.edges.map(function (edge) {
	                		return {
	                            source: edge.tag_id_one,
	                            target: edge.tag_id_two,
	                            value: edge.edge,
	                            //设置边显示的样式
	                            label: {
	                            	normal:{
	                            		show:true,//显示边的值
	                            		position:'middle',//值显示的位置
	                            		formatter: '{c}'//值显示的格式
	                            	}
	                            }
	                        };
	                    
	                }),
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
	                    //initLayout: 'circular',
	                    //repulsion: 20,
	                    edgeLength: 5,
	                    repulsion: 20,
	                    gravity: 0.2
	                },
	                edgeSymbol: ['arrow', 'arrow'],
	                lineStyle: {
	                    normal: {
	                        width: 1,
	                        curveness: 0.0,
	                        opacity:1
	                    }
	                }
		            
				}]
		};
		myChart.setOption(option);
		
	});
	myChart.setOption(option, true);
}

function filedown(){
	var url="AnalyzeDataDownLoad?fileName=tag-apiweight"+apiweight+"-mashupweight"+mashupweight+"-edge"+edge+".txt";
	window.location.href=url;
}
</script>