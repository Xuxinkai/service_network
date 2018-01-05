<%@page import="java.util.Map.Entry"%>
<%@page import="com.cn.util.network.analyze.ApiSimilarityAnalyze"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table {
    white-space: normal;
    line-height: normal;
    font-weight: normal;
    font-variant: normal;
    font-style: normal;
    color: -webkit-text;
    text-align: start;
    border-collapse: collapse;
    border-spacing: 0;
}
.table{width:100%;}
.table th{border:1px #d2d2d2 solid;height:40px;line-height:40px;}
.table td{border:1px #d2d2d2 solid;padding:10px 8px;}
.table tr:nth-child(odd){background:#f8f8f8;}
.table tr:hover{background:#f9f9f9;}
.table td a{color:#19a97b;margin:0 5px;cursor:pointer;}
.table td .inner_btn{background:#F9F;color:white;padding:5px 8px;border-radius:2px;}
.table td .inner_btn:hover{background:#ffa4ff;color:#f8f8f8;}
.table td .cut_title{width:265px;}
</style>
<!-- 4.API的类别数量以及每个类别拥有的API数量 -->
<div style="width: 100%;">
	<table style="width: 100%" class="table">
		<thead>
			<tr>
				<th colspan="12">API的类别共有<%=ApiSimilarityAnalyze.getCategoryCountForApi() %>个,下面是各类别拥有的API数量</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="width: 10%">类别名</td>
				<td style="width: 6.6%">API数量</td>
				<td style="width: 10%">类别名</td>
				<td style="width: 6.6%">API数量</td>
				<td style="width: 10%">类别名</td>
				<td style="width: 6.6%">API数量</td>
				<td style="width: 10%">类别名</td>
				<td style="width: 6.6%">API数量</td>
				<td style="width: 10%" style="width: 9%">类别名</td>
				<td style="width: 6.6%">API数量</td>
				<td style="width: 10%">类别名</td>
				<td style="width: 6.6%">API数量</td>
			</tr>
		<%
		Map<String,String> categoryMap = ApiSimilarityAnalyze.getNumberOfApisOwnedByCategory();
		String td = "";
		int i = 0;
		for(Entry<String, String> entry : categoryMap.entrySet()){
			td = td + "<td>"+entry.getKey()+"</td><td>"+entry.getValue().split(",").length+"</td>";
			i++;
			if(i%6 == 0){
				out.println("<tr>"+td+"</tr>");
				td = "";
			}
			if(i == 67){
				out.println("<tr>"+td+"</tr>");
			}
			
		}
		
		%>
			
		</tbody>
	</table>

</div>

