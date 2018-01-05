package com.cn.servlet.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.bean.WebApi;
import com.cn.bean.WebApiRelation;
import com.cn.util.DataFileWriteUtil;
import com.cn.util.network.analyze.ApiSimilarityAnalyze;
/**
 * 
 * @author 徐新凯
 * @date 2017年5月2日 下午5:07:25
 * @description WebApiRelation转化为json数据，
 */
@SuppressWarnings("serial")
public class WebApiRelationToJsonServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");// 这条语句指明了向客户端发送的内容格式和采用的字符编码．
		PrintWriter out = response.getWriter();
		//'WebApiRelationToJson?_tagweight='+tagweight+'&_mashupweight='+mashupweight
		String tagweight = request.getParameter("_tagweight");
		String mashupweight = request.getParameter("_mashupweight");
		String edge = request.getParameter("_edge");
		//System.out.print("api::mashupweight:"+mashupweight+",tagweight:"+tagweight+",edge:"+edge);
		String relationJson = "";
		List<WebApi> listApi = ApiSimilarityAnalyze.getFirstNApis(100);//获取调用api个数前80的mashups Mashup包最多API的前80个Mashup 
		for(int i = 0;i < listApi.size();i++){
			listApi.get(i).setApi_id(i);
		}
		
		List<WebApiRelation> listwar = ApiSimilarityAnalyze.calcApiSimilarity(listApi, Double.parseDouble(tagweight) ,Double.parseDouble(mashupweight) , Double.parseDouble(edge));//计算这些mashup的相似度
		
		//System.out.println("---nodes:"+listApi.size()+",edges:"+listwar.size());
		String fileContent = "";
		String fileName = "api-mashupweight"+mashupweight+"-tagweight"+tagweight+"-edge"+edge+".txt";
		String filePath = this.getServletContext().getRealPath("/search/analyzeData");
		for(WebApiRelation w:listwar){
			
			fileContent = fileContent + w.getApi_one()+" "+w.getApi_two()+" "+w.getWeight()+"\r\n";
		}
		DataFileWriteUtil.write(fileName, filePath, fileContent);
		String nodes = nodesBeanListToJson(listApi);
		String edges = edgesBeanListToJson(listwar);
		relationJson = nodes + edges;
		out.println(relationJson);// 利用PrintWriter对象的方法将数据发送给客户端 　　
		out.close();
			
		}
	/**
	 * 将java对象List集合转换成json字符串
	 * 
	 * @param beans
	 * @return
	 */
	public String nodesBeanListToJson(List<WebApi> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("{\"nodes\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(WebApibeanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
		}
		rest.append("],");
		return rest.toString();
	}

	public String edgesBeanListToJson(List<WebApiRelation> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("\"edges\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(WebApiRelationbeanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
		}
		rest.append("]}");
		return rest.toString();
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param bean
	 * @return
	 */
	public  String WebApibeanToJson(WebApi bean) {
		String json = "{\"api_id\":"+bean.getApi_id()+",\"api_name\":\""+bean.getApi_name()+"\"}";
		
		return json;

	}
	public  String WebApiRelationbeanToJson(WebApiRelation bean) {
		String json = "{\"api_id_one\":"+bean.getApi_one()+",\"api_id_two\":"+bean.getApi_two()+",\"edge\":\""+bean.getWeight()+"\"}";
		return json;

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		}

}
