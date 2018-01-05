package com.cn.servlet.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.bean.WebTag;
import com.cn.bean.WebTagRelation;
import com.cn.util.DataFileWriteUtil;
import com.cn.util.network.analyze.TagSimilarityAnalyze;
/**
 * 
 * @author 徐新凯
 * @date 2017年5月2日 下午11:28:22
 * @description  WebTagRelation 转json数据  显示出来
 */
@SuppressWarnings("serial")
public class WebTagRelationToJsonServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");// 这条语句指明了向客户端发送的内容格式和采用的字符编码．
		PrintWriter out = response.getWriter();
		String apiweight = request.getParameter("_apiweight");
		String mashupweight = request.getParameter("_mashupweight");
		String edge = request.getParameter("_edge");
		
		//System.out.print("tag::apiweight:"+apiweight+",mashupweight:"+mashupweight+",edge:"+edge);
		String relationJson = "";
		
		List<WebTag> listTag = TagSimilarityAnalyze.getFirstNTagsByMashupAndApi(100);
		for(int i = 0;i < listTag.size();i++){
			listTag.get(i).setTag_id(i);
		}
		List<WebTagRelation> listwtr = TagSimilarityAnalyze.calcTagSimilarity(listTag, Double.parseDouble(apiweight),Double.parseDouble(mashupweight),Double.parseDouble(edge));
		//System.out.println("---nodes:"+listTag.size()+",edges:"+listwtr.size());
		String fileContent = "";
		String fileName = "tag-apiweight"+apiweight+"-mashupweight"+mashupweight+"-edge"+edge+".txt";
		String filePath = this.getServletContext().getRealPath("/search/analyzeData");
		for(WebTagRelation w:listwtr){
			//System.out.println(w.getTag_one()+" "+w.getTag_two()+" "+w.getWeight());
			fileContent = fileContent + w.getTag_one()+" "+w.getTag_two()+" "+w.getWeight()+"\r\n";
		}
		DataFileWriteUtil.write(fileName, filePath, fileContent);
		relationJson = nodesBeanListToJson(listTag)+edgesBeanListToJson(listwtr);
		out.println(relationJson);// 利用PrintWriter对象的方法将数据发送给客户端 　　
		out.close();
			
		}
	/**
	 * 将java对象List集合转换成json字符串
	 * 
	 * @param beans
	 * @return
	 */
	public String nodesBeanListToJson(List<WebTag> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("{\"nodes\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(WebTagbeanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
		}
		rest.append("],");
		return rest.toString();
	}

	public String edgesBeanListToJson(List<WebTagRelation> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("\"edges\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(WebTagRelationbeanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
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
	public  String WebTagbeanToJson(WebTag bean) {
		String json = "{\"tag_id\":"+bean.getTag_id()+",\"tag_name\":\""+bean.getTag_name()+"\"}";
		
		return json;

	}
	public  String WebTagRelationbeanToJson(WebTagRelation bean) {
		String json = "{\"tag_id_one\":"+bean.getTag_one()+",\"tag_id_two\":"+bean.getTag_two()+",\"edge\":\""+bean.getWeight()+"\"}";
		return json;

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		}

}
