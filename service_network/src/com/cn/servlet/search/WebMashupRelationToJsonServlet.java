package com.cn.servlet.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.cn.bean.WebMashup;
import com.cn.bean.WebMashupRelation;
import com.cn.util.DataFileWriteUtil;
import com.cn.util.network.analyze.MashupSimilarityAnalyze;
/**
 * 
 * @author 徐新凯
 * @date 2017年5月2日 下午5:07:25
 * @description WebMashupRelation转化为json数据，
 */
@SuppressWarnings("serial")
public class WebMashupRelationToJsonServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");// 这条语句指明了向客户端发送的内容格式和采用的字符编码．
		PrintWriter out = response.getWriter();
		String apiweight = request.getParameter("_apiweight");
		String tagweight = request.getParameter("_tagweight");
		String edge = request.getParameter("_edge");
		//System.out.print("mashup::apiweight:"+apiweight+",tagweight:"+tagweight+",edge:"+edge);
		String relationJson = "";
		List<WebMashup> listMashup = MashupSimilarityAnalyze.getFirstNMashups(100);//获取调用api个数前80的mashups Mashup包最多API的前80个Mashup 
		for(int i = 0;i < listMashup.size();i++){
			listMashup.get(i).setMashup_id(i);
		}
		List<WebMashupRelation> listwmr = MashupSimilarityAnalyze.calcMashupSimilarity(listMashup, Double.parseDouble(apiweight),  Double.parseDouble(tagweight), Double.parseDouble(edge));//计算这些mashup的相似度
		
		//这个json的数据后面的edges是前面端点的位置  不是mashup的id   一直想错了。。。。。。。
		//System.out.println("---nodes:"+listMashup.size()+",edges:"+listwmr.size());
		String fileContent = "";
		String fileName = "mashup-apiweight"+apiweight+"-tagweight"+tagweight+"-edge"+edge+".txt";
		String filePath = this.getServletContext().getRealPath("/search/analyzeData");
		for(WebMashupRelation wmr:listwmr){
			//System.out.println(wmr.getMashup_one()+" "+wmr.getMashup_two()+" "+wmr.getWeight());
			fileContent = fileContent + wmr.getMashup_one()+" "+wmr.getMashup_two()+" "+wmr.getWeight()+"\r\n";
		}
		//System.out.println(fileName+"----"+filePath);
		//System.out.println(fileContent.trim());
		DataFileWriteUtil.write(fileName, filePath, fileContent);

		String nodes = nodesBeanListToJson(listMashup);
		String edges = edgesBeanListToJson(listwmr);
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
	public String nodesBeanListToJson(List<WebMashup> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("{\"nodes\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(WebMashupbeanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
		}
		rest.append("],");
		return rest.toString();
	}

	public String edgesBeanListToJson(List<WebMashupRelation> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("\"edges\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(WebMashupRelationbeanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
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
	public  String WebMashupbeanToJson(WebMashup bean) {
		String json = "{\"mashup_id\":"+bean.getMashup_id()+",\"mashup_name\":\""+bean.getMashup_name()+"\"}";
		
		return json;

	}
	public  String WebMashupRelationbeanToJson(WebMashupRelation bean) {
		String json = "{\"mashup_id_one\":"+bean.getMashup_one()+",\"mashup_id_two\":"+bean.getMashup_two()+",\"edge\":\""+bean.getWeight()+"\"}";
		return json;

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
