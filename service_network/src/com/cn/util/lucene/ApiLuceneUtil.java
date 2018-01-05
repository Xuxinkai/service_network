package com.cn.util.lucene;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.cn.bean.WebApi;
import com.cn.dao.WebApiDao;
import com.cn.dao.impl.WebApiDaoImpl;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月10日 下午10:31:06
 * @description
 */
public class ApiLuceneUtil {
	private static String apiIndexPath = ApiLuceneUtil.class.getResource("/")
			.getPath() + "apiIndexDir";

	// private static String mashupIndexPath =
	// LuceneUtil.class.getResource("/").getPath()+"mashupIndexDir";
	@Test
	public void createIndex() throws Exception {
		/**
		 * 1、创建一个Api对象，并且把信息存放进去 2、调用indexWriter的API把数据存放在索引库中 3、关闭indexWriter
		 */
		// 创建一个Api对象，并且把信息存放进去
		List<WebApi> listApi = new ArrayList<WebApi>();
		WebApiDao apiDao = new WebApiDaoImpl();
		listApi = apiDao.getWebApiByKeyWord("");
		// 调用indexWriter的API把数据存放在索引库中
		/**
		 * 创建一个IndexWriter 参数三个 1、索引库 指向索引库的位置 2、分词器
		 */
		// 创建索引库
		Directory directory = FSDirectory.open(new File(apiIndexPath));
		// 创建分词器
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
		IndexWriter indexWriter = new IndexWriter(directory, analyzer,
				MaxFieldLength.LIMITED);
		// 把一个article对象转化成document
		for (WebApi api : listApi) {
			Document document = new Document();
			Field idField = new Field("id", api.getApi_id() + "", Store.YES, Index.NOT_ANALYZED);
			Field nameField = new Field("name", api.getApi_name(), Store.YES, Index.ANALYZED);
			Field tagsField = new Field("tags", api.getApi_tags(), Store.YES, Index.ANALYZED);
			Field categoryField = new Field("category", api.getApi_category(), Store.YES, Index.ANALYZED);
			Field commentsField = new Field("comments", api.getApi_comments(), Store.YES, Index.ANALYZED);
			Field urlField = new Field("url", api.getApi_url(), Store.YES, Index.NOT_ANALYZED);
			Field mashupsField = new Field("mashups", api.getApi_mashups(), Store.YES, Index.NOT_ANALYZED);
			document.add(idField);
			document.add(nameField);
			document.add(tagsField);
			document.add(categoryField);
			document.add(commentsField);
			document.add(urlField);
			document.add(mashupsField);
			indexWriter.addDocument(document);
		}
		// 关闭indexWriter
		indexWriter.close();
	}

	public List<WebApi> searchIndex(String keyWord) throws Exception {
		/**
		 * 1、创建一个 IndexSearch对象 2、调用search方法进行检索 3、输出内容
		 */
		// 创建一个 IndexSearch对象
		Directory directory = FSDirectory.open(new File(apiIndexPath));
		IndexSearcher indexSearcher = new IndexSearcher(directory);
		// 调用search方法进行检索
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
		String[] fields =  new String[] { "name", "tags", "category", "comments" };
//		//设置字段的权重
//	    Map<String, Float> boosts = new HashMap<String, Float>();
//	    boosts.put("name", 0.5f);
//	    boosts.put("tags", 0.2f);
//	    boosts.put("category", 0.2f);
//	    boosts.put("comments", 0.1f);
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_30,fields, analyzer);
		Query query = null;
		
		query = queryParser.parse(keyWord);// 关键词查询
		

		TopDocs topDocs = indexSearcher.search(query, 10000);// 前10000个数据 按照分数排序
		int count = topDocs.totalHits;// 根据关键词查询出来的总的记录数
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		/***********************************************************************/
		/**
		 * 给关键字加上前缀和后缀
		 */
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
		/**
		 * scorer封装了关键字
		 */
		Scorer scorer = new QueryScorer(query);
		Highlighter highlighter = new Highlighter(formatter, scorer);
		/**
		 * 创建一个摘要
		 * 
		 */
		Fragmenter fragmenter = new SimpleFragmenter(200);//显示出200个字符
		highlighter.setTextFragmenter(fragmenter);
		/***********************************************************************/

		List<WebApi> listApi = new ArrayList<WebApi>();
		for (ScoreDoc scoreDoc : scoreDocs) {
			float score = scoreDoc.score;// 关键词得分
			int index = scoreDoc.doc;// 索引的下标
			if (score > 0.1) {
				Document document = indexSearcher.doc(index);

				String name = highlighter.getBestFragment(analyzer, "name",
						document.get("name"));
				String tags = highlighter.getBestFragment(analyzer, "tags",
						document.get("tags"));
				String category = highlighter.getBestFragment(analyzer,
						"category", document.get("category"));
				String comments = highlighter.getBestFragment(analyzer,
						"comments", document.get("comments"));
				// 把document转化成WebApi
				WebApi api = new WebApi();
				api.setApi_id(Integer.parseInt(document.get("id")));
				if (name != null) {
					api.setApi_name(name);
				} else {
					api.setApi_name(document.get("name"));
				}
				if (tags != null) {
					api.setApi_tags(tags);
				} else {
					api.setApi_tags(document.get("tags"));
				}
				if (category != null) {
					api.setApi_category(category);
				} else {
					api.setApi_category(document.get("category"));
				}
				if (comments != null) {
					api.setApi_comments(comments);
				} else {
					api.setApi_comments(document.get("comments"));
				}
				api.setApi_url(document.get("url"));
				api.setScore(score);
				api.setApi_mashups(document.get("mashups"));
				listApi.add(api);
			}

		}
		return listApi;
	}
}
