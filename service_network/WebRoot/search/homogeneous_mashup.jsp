<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 选项卡-->
<section id="TabControlcontent" style="height: 5%;width: 100%;">
	<!-- Define the featured content section -->
		<ul>
		<li class="active"><a class="more" href="javascript:void()" onclick="loadhtml('homogeneous_mashup.jsp')">MASHUP-MASHUP</a></li>
		<li><a class="more" href="javascript:void()" onclick="loadhtml('homogeneous_api.jsp')">API-API</a></li>
		<li><a class="more" href="javascript:void()" onclick="loadhtml('homogeneous_tag.jsp')">TAG-TAG</a></li>
		</ul>
</section>
<!-- 权值-->
<div id="weightdiv" style="height: 5%;width: 100%;">
	<center>
		WEIGHT:&nbsp;&nbsp;&nbsp;&nbsp;
		API:<input type="text" value="" id="apiweight" name="apiweight" style="width: 50px;" onchange="changinputvalue('api');"//>&nbsp;&nbsp; 
		TAG:<input type="text" value="" id="tagweight" name="tagweight" style="width: 50px;" onchange="changinputvalue('tag');"//>&nbsp;&nbsp; 
		EDGE>=
		  <select class="select" style="width: 50px;" onchange="changeEdge()" id="selectEdge">
			<c:forEach begin="2" end="8" var="i"><option value="0.${i }">0.${i }</option></c:forEach>
		  </select>&nbsp;&nbsp;
		<a class="more" href="javascrip:void()" onclick="submitToChangeNetwork()">OK</a>
	</center>
</div>

<!-- 这个是显示mashup的同构网络图   默认显示api和tag的权值都为0.5 -->
<div id="Mashupcontainer" style="height: 700px;width: 100%;border-color: #e7e7e7;border-style: solid;border-width: 1px;"></div>
 <!-- 这个是显示该mashup同构网络的一些复杂网络相关特性指标（度与度分布、聚集系数、平均最短路径长度、网络密度与集中性） -->
 <br>
Related characteristics indicators:

<div id="tongjiDiv"  style="height: 19.8%;width: 100%;border-color: #e7e7e7;border-style: solid;border-width: 1px;">
				<br>
				下载数据文件，然后可以使用Cytoscape软件来分析该复杂网络的指标特性。<br/>
				<a href="javascript:void();" onclick="filedown()">度与度分布、聚集系数、平均最短路径长度、网络密度与集中性等分析数据下载</a><br>
				
</div>

<script type="text/javascript">
var apiweight = 0.5;
var tagweight = 0.5;

var edge = 0.2;//以上是默认的数据
$("#apiweight").val(apiweight);
$("#tagweight").val(tagweight);
function changinputvalue(type){
	if(type == 'tag'){
		var value = 1 - $("#tagweight").val();
		tagweight = $("#tagweight").val();
		apiweight = value.toFixed(1);
		$("#apiweight").val(apiweight);
	}
	if(type == 'api'){
		var value = 1 - $("#apiweight").val();
		apiweight = $("#apiweight").val();
		tagweight = value.toFixed(1);
		$("#tagweight").val(tagweight);
	}
}
function changeEdge(){
	edge = $("#selectEdge").val();
}


	var dom = document.getElementById("Mashupcontainer");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	myChart.showLoading();
	$.getJSON('WebMashupRelationToJson?_tagweight='+tagweight+'&_apiweight='+apiweight+'&_edge='+edge, function(webkitDep) {
		myChart.hideLoading();
		option = {
				title: {
	                text: 'The Mashup`s Homogeneous Network',
	                //subtext: 'Mashup-Api-Tag',
	                top: 'top',
	                left: 'center'
	            },
			series : [ {
				type : 'graph',
				layout : 'force',
				animation : false,
				roam : true,//滑轮缩放的开关

				draggable : true,//可以拖动
				data : webkitDep.nodes.map(function(node) {
					return {
						id : node.mashup_id,
						name : node.mashup_name,
						symbolSize : 5,
						itemStyle : {
							normal : {
								color : "#ff0000"
							}
						}
					};
				}),
				edges : webkitDep.edges.map(function(edge) {
					return {
						source : edge.mashup_id_one,
						target : edge.mashup_id_two,
						value : edge.edge,
						//设置边显示的样式
						label : {
							normal : {
								show : true,//显示边的值
								position : 'middle',//值显示的位置
								formatter : '{c}'//值显示的格式
							}
						}
					};

				}),
				label : {
					normal : {
						position : 'right',
						formatter : '{b}',
						show : true,
						textStyle : {
							color : '#000'
						}

					}
				},
				force : {
					//initLayout: 'circular',
					//repulsion: 20,
					edgeLength : 5,
					repulsion : 20,
					gravity : 0.2
				},
				edgeSymbol : [ 'arrow', 'arrow' ],
				lineStyle : {
					normal : {
						width : 1,
						curveness : 0,
						opacity : 1
					}
				}

			} ]
		};
		myChart.setOption(option);

	});
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
function submitToChangeNetwork(){
	option = null;
	myChart.showLoading();
	$.getJSON('WebMashupRelationToJson?_tagweight='+tagweight+'&_apiweight='+apiweight+'&_edge='+edge, function(webkitDep) {
		myChart.hideLoading();
		option = {
				title: {
					text: 'The Mashup`s Homogeneous Network',
	                top: 'top',
	                left: 'center'
	            },
			series : [ {
				type : 'graph',
				layout : 'force',
				animation : false,
				roam : true,//滑轮缩放的开关

				draggable : true,//可以拖动
				data : webkitDep.nodes.map(function(node) {
					return {
						id : node.mashup_id,
						name : node.mashup_name,
						symbolSize : 5,
						itemStyle : {
							normal : {
								color : "#ff0000"
							}
						}
					};
				}),
				edges : webkitDep.edges.map(function(edge) {
					return {
						source : edge.mashup_id_one,
						target : edge.mashup_id_two,
						value : edge.edge,
						//设置边显示的样式
						label : {
							normal : {
								show : true,//显示边的值
								position : 'middle',//值显示的位置
								formatter : '{c}'//值显示的格式
							}
						}
					};

				}),
				label : {
					normal : {
						position : 'right',
						formatter : '{b}',
						show : true,
						textStyle : {
							color : '#000'
						}

					}
				},
				force : {
					//initLayout: 'circular',
					//repulsion: 20,
					edgeLength : 5,
					repulsion : 20,
					gravity : 0.2
				},
				edgeSymbol : [ 'arrow', 'arrow' ],
				lineStyle : {
					normal : {
						width : 1,
						curveness : 0,
						opacity : 1
					}
				}

			} ]
		};
		myChart.setOption(option);

	});
	if (option && typeof option === "object") {
		myChart.setOption(option, true);
	}
}
function filedown(){
	var url="AnalyzeDataDownLoad?fileName=mashup-apiweight"+apiweight+"-tagweight"+tagweight+"-edge"+edge+".txt";
	window.location.href=url;
}
</script>