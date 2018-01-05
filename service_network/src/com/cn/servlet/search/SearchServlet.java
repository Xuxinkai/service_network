package com.cn.servlet.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.bean.WebApi;
import com.cn.bean.WebMashup;
import com.cn.util.lucene.ApiLuceneUtil;
import com.cn.util.lucene.MashupLuceneUtil;
import com.cn.util.network.analyze.ApiSimilarityAnalyze;
import com.cn.util.network.analyze.MashupSimilarityAnalyze;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月10日 下午3:05:03
 * @description 搜索处理
 */
@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApiLuceneUtil apiUtil = new ApiLuceneUtil();
		MashupLuceneUtil mashupUtil = new MashupLuceneUtil();
		String keyWord = request.getParameter("keyWord");
		// List<WebApi> listApi = apiDao.getWebApiByKeyWord(keyWord);
		if (keyWord == null) {
			keyWord = "";
		}
		if (keyWord.contains("<font color='red'>")) {
			keyWord = keyWord.replace("<font color='red'>", "").replace("</font>", "");

		}
		// System.out.println("===="+keyWord);
		List<WebApi> listApi = null;
		List<WebMashup> listMashup = null;
		if(keyWord == ""){
			listApi = ApiSimilarityAnalyze.getFirstNApis(500);
			listMashup = MashupSimilarityAnalyze.getFirstNMashups(500);
		}else{
			try {
				listApi = apiUtil.searchIndex(keyWord);
				listMashup = mashupUtil.searchIndex(keyWord);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if (keyWord == "") {
			keyWord = "please input key word";
		}
		request.setAttribute("listApi", listApi);
		request.setAttribute("listMashup", listMashup);	
		request.setAttribute("keyWord", keyWord);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
