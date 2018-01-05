package com.cn.util.network.analyze;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月24日 上午11:10:08
 * @description 写文件，输出分数的文件
 */
public class WriteNetworkScoreFile {

	public static void writeFile(String filePath, String fileName, String text) {
		try {
			String path = filePath + fileName;
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true); // 如果追加方式用true
			StringBuffer sb = new StringBuffer();
			sb.append(text + "\r\n");
			out.write(sb.toString().getBytes("utf-8"));// 注意需要转换对应的字符集
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
