package com.cn.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author 徐新凯
 * @date 2017年5月26日 上午11:34:49
 * @description 写文件
 */
public class DataFileWriteUtil {
	public static void write(String fileName, String filePath,
			String fileContent) {
		// file(内存)----输入流---->【程序】----输出流---->file(内存)
		File file = new File(filePath, fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();// 创建文件
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 向文件写入内容(输出流)
		String str = fileContent;
		byte bt[] = new byte[1024];
		bt = str.getBytes();
		try {
			FileOutputStream in = new FileOutputStream(file);
			try {
				in.write(bt, 0, bt.length);
				in.close();
				// boolean success=true;
				System.out.println("写入文件成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
