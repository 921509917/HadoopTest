package cn.hadoop.test;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestJSON {
	public static void main(String[] args) {
//		checkout();
		checkout2();
	}

	public static void insert() {

	}


	/**
	 * 判断输出的文件中，是否存在重复的名称
	 */
	public static void checkout2() {
		BufferedReader br = null;
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();

		try {
			br = new BufferedReader(new FileReader("WebContent/data/out10.txt"));
			String line = "";
			while((line = br.readLine()) != null) {
				String[] strs = line.split("======");
				set1.add(strs[0]);
				set2.add(strs[1]);
			}

			System.out.println(set1.size()+"========"+set2.size());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void checkout() {
		List<String> gsodList = readCountryList();
		List<String> jsonList = readJson();

		BufferedWriter bw1 = null, bw2 = null;
		try {
			bw1 = new BufferedWriter(new FileWriter("WebContent/data/out10.txt"));	// 相同
			bw2 = new BufferedWriter(new FileWriter("WebContent/data/out20.txt"));	// 相关

			for(int j = 0; j<gsodList.size(); j++) {
				System.out.println(gsodList.get(j));
				String country = gsodList.get(j).split("===")[1];

				for(int i = 0; i<jsonList.size(); i++) {

					String json = jsonList.get(i);

					System.out.println(gsodList.get(j)+"-----"+json);

					if(isContains(country, json) == 0) {
						bw1.write(gsodList.get(j) + "======" + json);
						bw1.newLine();
						jsonList.remove(i);
						break;
					}else if(isContains(country, json) == 1){

						bw2.write(gsodList.get(j) + "======" + json);
						bw2.newLine();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw1.close();
				bw2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取country-list.txt文件中国家（区域）的全称，并保存到文件中
	 */
	public static List<String> readCountryList() {
		List<String> list = new ArrayList<String>();

		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader("WebContent/data/country-list.txt"));
			bw = new BufferedWriter(new FileWriter("WebContent/data/gsodCountry.txt"));
			String str = null;
			while((str = br.readLine()) != null) {
				if(str.startsWith("FIPS") || "".equals(str))
					continue;
				// 简称
				String aliases = str.substring(0, 10).trim();
				// 全称
				String country = str.substring(11).trim();

				bw.write(aliases+"==="+country);
				bw.newLine();

				list.add(aliases+"==="+country);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}


	/**
	 * 判断从world.json文件中解析的国家名称和country-list.txt文件中解析的国家名称 是否有相同的部分
	 * @param str1	国家名称
	 * @param str2	国家名称
	 * @return int  -1表示输入的两个国家名称没有任何关系
	 * 				 0 表示输入两个国家名称相同
	 * 				 1 表示输入两个国家名称相关（是否相同还需进一步手动确定）
	 */
	public static int isContains(String str1, String str2) {
		String[] strs1 = str1.split(" ");
		String[] strs2 = str2.split(" ");

		int flag = -1;

		// 输入的两个名称相同或者去除掉 . 之后相同的
		if(str1.toUpperCase().equals(str2.toUpperCase())
				||
				(str1.toUpperCase().replace(".", "").contains(str2.toUpperCase().replace(".", "")))
				||
				(str2.toUpperCase().replace(".", "").contains(str1.toUpperCase().replace(".", ""))))
			return flag = 0;

		// 名称按照空格切分之后，除了指定字符串是否存在单词相同（这里当做相关）
		for(String s_strs1 : strs1) {

			for(String s_strs2 : strs2) {

				if((s_strs1.toUpperCase()).equals(s_strs2.toUpperCase())
						&& !(s_strs1.toUpperCase()).equals("AND")
						&& !(s_strs1.toUpperCase()).equals("ST.")
						&& !(s_strs1.toUpperCase()).equals("OF"))
					flag = 1;

			}
		}

		return flag;
	}


	/**
	 * 读取world.json文件中国家（区域）的名称信息，并保存到文件中
	 */
	public static List<String> readJson() {
		List<String> list = new ArrayList<String>();

		BufferedReader br = null;
		BufferedWriter bw = null;
		// 读取json文件
		try {
			br = new BufferedReader(new FileReader("WebContent/data/world.json"));
			bw = new BufferedWriter(new FileWriter("WebContent/data/jsonCountry.txt"));
			String str = null;
			String data = "";
			// 将从JSON文件中读取得到的数据存储在字符串中
			while ((str = br.readLine()) != null) {
				data = data + str + "\n";
			}
			// 将字符串中的数据初始化到JSONObject对象中
			JSONObject dataJson = new JSONObject(data);
			// 将 features 属性中的数据初始化为JSONObject对象的数组
			JSONArray features = dataJson.getJSONArray("features");
			// 遍历数组
			for (int i = 0; i < features.length(); i++) {
				// 获取features数组的第i个json对象
				JSONObject info = features.getJSONObject(i);
				// 找到properties的json对象
				JSONObject properties = info.getJSONObject("properties");
				// 读取properties对象里的name字段值
				String name = properties.getString("name").trim();
				// 去除掉国家（区域）名称为空的字段
				if(!"".equals(name) && name!=null) {
					// 将国家信息写入到文件中
					bw.write(name);
					bw.newLine();

					list.add(name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {	// 关闭流
			try {
				bw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
