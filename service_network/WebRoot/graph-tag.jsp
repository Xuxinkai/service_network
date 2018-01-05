<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div id="container" style="height: 100%;width: 100%"></div>
<script type="text/javascript">

var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
myChart.showLoading();
$.getJSON('realtionToJson?_relationType=tag&_weight='+_weight,function(webkitDep){
	myChart.hideLoading();
	option = {
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
                            source: edge.tag_id_one-1,
                            target: edge.tag_id_two-1,
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
                edgeSymbol: ['', 'arrow'],
                lineStyle: {
                    normal: {
                        width: 1,
                        curveness: 0.3,
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

</script>