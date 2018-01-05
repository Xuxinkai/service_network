<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>服务网络</title>
	   <script type="text/javascript" src="js/echarts3/echarts-all-3.js"></script>
       <script type="text/javascript" src="js/echarts3/dataTool.min.js"></script>
       <script type="text/javascript" src="js/echarts3/china.js"></script>
       <script type="text/javascript" src="js/echarts3/world.js"></script>
       <script type="text/javascript" src="js/echarts3/bmap.min.js"></script>
       <script src="js/jquery.js"></script>
       <link rel="stylesheet" type="text/css" href="css/style.css" />
  </head>
  
  <body onload="loadhtml('graph-api.jsp');">
 
   	<div style="margin-left: auto;margin-right:auto;width: 80%;">
	    <div id="contentDiv" style="height:100%;border-color: red;border-style: dotted;border-width: 1px;width: 73%;float:left;"></div>
	    <div id="changeButton" style="height:100%;border-color: black;border-style: dotted;border-width: 1px;width: 26.6%;float: right;">
	    <table class="table">
	    <tr><td rowspan="3" width="40%">Choose Level View</td>
	    <td><input type="radio" name="level" value="api" checked="checked" onchange="changeShowLevel('api')"/> Api Level View</td></tr>
	    <tr><td><input type="radio" name="level" value="tag" onchange="changeShowLevel('tag')"/> Tag Level View</td></tr>
	    <tr><td><input type="radio" name="level" value="domain" onchange="changeShowLevel('domain')"/> Domain Level View</td></tr>
	    </table>
	    <table class="table">
	    <tr>
	    <td rowspan="2" width="40%">Choose Layout</td> 
	    <td><a href="#graph-circular-layout" class="inner_btn" onclick="loadhtml('graph-circular-layout.jsp');">Circular Layout</a></td></tr>
	    
	    <tr>
	    <td><a href="#graph-diy" class="inner_btn" onclick="loadhtml('graph-api.jsp');"> DIY</a></td>
	    </tr>
	    </table>
	    
	    <table class="table">
	    <tr><td><input type="checkbox" value="" checked="checked" onclick="changeShowNodeValue()"/>Show Node Value</td></tr>
	    <tr><td><input type="checkbox" value="" checked="checked" onclick="changeShowEdgeValue()"/>Show Edge Weight Value</td></tr>
	    <tr><td>Filter When Weight >
		    <select class="select" style="width: 50px;" onchange="changeWeight()" id="selectWeight">
		   <c:forEach begin="1" end="8" var="i"><option value="0.${i }">0.${i }</option></c:forEach>
	        </select>
       </td></tr>
       <tr><td><a href="search/keywordsearch.html" target="_blank">Key Word Search</a> </td></tr>
       
	    </table>
	    </div>
    </div>
  </body>
</html>
 <script type="text/javascript">
 var _weight=0.1;//默认边值
 var html = "graph-api.jsp";//默认加载的页面
  function loadhtml(_html){
	 html=_html;//修改当前默认的加载页面
	$("#contentDiv").load(_html);
  }
 function changeShowLevel(_realtionType){//切换显示的level   api  tag domain
	 //alert(_realtionType);
	 if(_realtionType == "api"){
		 html = "graph-api.jsp";
	 }else if(_realtionType == "tag"){
		 html = "graph-tag.jsp";
	 }else if(_realtionType == "domain"){
		 html = "graph-domain.jsp";
	 }
	 
	 $("#contentDiv").load(html);
 }
  function changeShowValue(){
	  
  }
  function changeWeight(){
	  var weight = $("#selectWeight").val();//获取权重值
	  _weight=weight;
	  $("#contentDiv").load(html);
  }
  </script>
  