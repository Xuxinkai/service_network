package com.cn.util;

import java.util.List;

import com.cn.bean.ApiNet;
import com.cn.bean.ApiRelation;
import com.cn.bean.DomainNet;
import com.cn.bean.DomainRelation;
import com.cn.bean.TagNet;
import com.cn.bean.TagRelation;
import com.cn.dao.ApiNetDao;
import com.cn.dao.ApiRelationDao;
import com.cn.dao.DomainNetDao;
import com.cn.dao.DomainRelationDao;
import com.cn.dao.TagNetDao;
import com.cn.dao.TagRelationDao;
import com.cn.dao.impl.ApiNetDaoImpl;
import com.cn.dao.impl.ApiRelationDaoImpl;
import com.cn.dao.impl.DomainNetDaoImpl;
import com.cn.dao.impl.DomainRelationDaoImpl;
import com.cn.dao.impl.TagNetDaoImpl;
import com.cn.dao.impl.TagRelationDaoImpl;

import net.sf.json.JSONObject;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月22日 下午2:34:25
 * @description api,tag,domain的关系bean转json
 */
public class RelationToJsonUtil {
	/**
	 * 
	 * @param relationType
	 *            关系类型，api tag domain
	 * @param weight
	 *            边的权重
	 * @return api,tag,domain 转化的json数据
	 */
	public static String getJson(String relationType, String weight) {
		String jsonData = null;
		if ("api".equals(relationType)) {
			ApiNetDao adao = new ApiNetDaoImpl();
			List<ApiNet> as = adao.getAllApi();

			ApiRelationDao ardao = new ApiRelationDaoImpl();
			List<ApiRelation> ars = ardao.getApiRelationByEdgeWeight(weight);

			jsonData = nodesBeanListToJson(as) + edgesBeanListToJson(ars);
		} else if ("tag".equals(relationType)) {
			TagNetDao tdao = new TagNetDaoImpl();
			List<TagNet> ts = tdao.getAllTag();

			TagRelationDao trdao = new TagRelationDaoImpl();
			List<TagRelation> trs = trdao.getTagRelationByEdgeWeight(weight);

			jsonData = nodesBeanListToJson(ts) + edgesBeanListToJson(trs);

		} else if ("domain".equals(relationType)) {
			DomainNetDao ddao = new DomainNetDaoImpl();
			List<DomainNet> ds = ddao.getAllDomain();

			DomainRelationDao drdao = new DomainRelationDaoImpl();
			List<DomainRelation> drs = drdao
					.getDomainRelationByEdgeWeight(weight);
			// for(DomainRelation a:drs){
			// System.out.println(a.getDomain_id_one()+","+a.getDomain_id_two());
			// }
			jsonData = nodesBeanListToJson(ds) + edgesBeanListToJson(drs);
			// System.out.println(jsonData);
		}

		return jsonData;
	}

	/**
	 * 将java对象List集合转换成json字符串
	 * 
	 * @param beans
	 * @return
	 */
	public static String nodesBeanListToJson(List<?> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("{\"nodes\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(beanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
		}
		rest.append("],");
		return rest.toString();
	}

	public static String edgesBeanListToJson(List<?> beans) {
		StringBuffer rest = new StringBuffer();
		rest.append("\"edges\":[");
		int size = beans.size();
		for (int i = 0; i < size; i++) {
			rest.append(beanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));
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
	public static String beanToJson(Object bean) {
		JSONObject json = JSONObject.fromObject(bean);
		return json.toString();

	}
}
