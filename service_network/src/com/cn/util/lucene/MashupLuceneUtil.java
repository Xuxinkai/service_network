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
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.cn.bean.WebMashup;
import com.cn.dao.WebMashupDao;
import com.cn.dao.impl.WebMashupDaoImpl;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月11日 下午5:08:06
 * @description
 */
public class MashupLuceneUtil {
	private static String mashupIndexPath = MashupLuceneUtil.class.getResource("/").getPath() + "mashupIndexDir";

	@Test
	public void createIndex() throws Exception {
		/**
		 * 1、创建一个Api对象，并且把信息存放进去 2、调用indexWriter的API把数据存放在索引库中 3、关闭indexWriter
		 */
		// 创建一个Api对象，并且把信息存放进去
		List<WebMashup> listMashup = new ArrayList<WebMashup>();
		WebMashupDao mashupDao = new WebMashupDaoImpl();
		listMashup = mashupDao.getMashupByKeyWord("");
		// 调用indexWriter的API把数据存放在索引库中
		/**
		 * 创建一个IndexWriter 参数三个 1、索引库 指向索引库的位置 2、分词器
		 */
		// 创建索引库
		Directory directory = FSDirectory.open(new File(mashupIndexPath));
		// 创建分词器
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
		IndexWriter indexWriter = new IndexWriter(directory, analyzer,
				MaxFieldLength.LIMITED);
		// 把一个article对象转化成document
		for (WebMashup mashup : listMashup) {
			Document document = new Document();
			Field idField = new Field("id", mashup.getMashup_id() + "",
					Store.YES, Index.NOT_ANALYZED);
			Field nameField = new Field("name", mashup.getMashup_name(),
					Store.YES, Index.ANALYZED);
			Field tagsField = new Field("tags", mashup.getMashup_tags(),
					Store.YES, Index.ANALYZED);
			Field apisField = new Field("apis", mashup.getMashup_apis(),
					Store.YES, Index.ANALYZED);
			document.add(idField);
			document.add(nameField);
			document.add(tagsField);
			document.add(apisField);
			indexWriter.addDocument(document);
		}
		// 关闭indexWriter
		indexWriter.close();
	}

	public List<WebMashup> searchIndex(String keyWord) throws Exception {
		/**
		 * 1、创建一个 IndexSearch对象 2、调用search方法进行检索 3、输出内容
		 */
		// 创建一个 IndexSearch对象
		Directory directory = FSDirectory.open(new File(mashupIndexPath));
		IndexSearcher indexSearcher = new IndexSearcher(directory);
		// 调用search方法进行检索
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
		String[] fields =  new String[] { "name", "tags", "apis" };
//		//设置字段的权重
//	    Map<String, Float> boosts = new HashMap<String, Float>();
//	    boosts.put("name", 0.5f);
//	    boosts.put("tags", 0.3f);
//	    boosts.put("apis", 0.2f);
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_30,fields, analyzer);
		Query query = null;
		
		query = queryParser.parse(keyWord);// 关键词
		
		TopDocs topDocs = indexSearcher.search(query, 9000);// 前400个数据 按照分数排序
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
		 * Fragmenter fragmenter = new SimpleFragmenter(100);
		 * highlighter.setTextFragmenter(fragmenter);
		 */
		/***********************************************************************/

		List<WebMashup> listMashup = new ArrayList<WebMashup>();
		for (ScoreDoc scoreDoc : scoreDocs) {
			float score = scoreDoc.score;// 关键词得分
			int index = scoreDoc.doc;// 索引的下标
			if (score > 0.1) {// 当得分大于0.1时 记录
				Document document = indexSearcher.doc(index);

				String name = highlighter.getBestFragment(analyzer, "name",
						document.get("name"));
				String tags = highlighter.getBestFragment(analyzer, "tags",
						document.get("tags"));
				String apis = highlighter.getBestFragment(analyzer, "category",
						document.get("apis"));
				// 把document转化成WebApi
				WebMashup mashup = new WebMashup();
				mashup.setMashup_id(Integer.parseInt(document.get("id")));
				if (name != null) {
					mashup.setMashup_name(name);
				} else {
					mashup.setMashup_name(document.get("name"));
				}
				if (tags != null) {
					mashup.setMashup_tags(tags);
				} else {
					mashup.setMashup_tags(document.get("tags"));
				}
				if (apis != null) {
					mashup.setMashup_apis(apis);
				} else {
					mashup.setMashup_apis(document.get("apis"));
				}

				mashup.setScore(score);
				listMashup.add(mashup);
			}

		}
		// System.out.println("result："+listApi.size()+";"+count);
		// for(WebApi api:listApi){
		// System.out.println(api.getApi_id()+"："+api.getApi_name()+";"+api.getApi_tags()+";"+api.getApi_category()+";"+api.getScore());
		// }
		return listMashup;
	}

}
