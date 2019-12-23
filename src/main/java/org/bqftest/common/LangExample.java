package org.bqftest.common;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class LangExample {

	public static void main(String[] args){
	    
		
		//http://commons.apache.org/lang/
		try {
			
			//StringUtils
			testIsEmpty();
			testTrim();
			testSplit();
			testJoin();
			
			//StringEscapeUtils: Java, Java Script, HTML and XML
			testEscape();
			
			//RandomStringUtils
			testRandom();
			
			//ArrayUtils
			testArray();
					
			//DateUtils
			testDate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testIsEmpty() {		
		//isEmpty
		System.out.println(StringUtils.isEmpty(null));      // true
		System.out.println(StringUtils.isEmpty(""));        // true
		System.out.println(StringUtils.isEmpty(" "));       // false
		System.out.println(StringUtils.isEmpty("bob"));     // false
		System.out.println(StringUtils.isEmpty("  bob  ")); // false
		
		//isBlank
		System.out.println(StringUtils.isBlank(null));      // true
		System.out.println(StringUtils.isBlank(""));        // true
		System.out.println(StringUtils.isBlank(" "));       // true
		System.out.println(StringUtils.isBlank("bob"));     // false
		System.out.println(StringUtils.isBlank("  bob  ")); // false
	}

	private static void testTrim() {
		System.out.println(StringUtils.trim(null)); // null
		System.out.println(StringUtils.trim("")); // ""
		System.out.println(StringUtils.trim("     ")); // ""
		System.out.println(StringUtils.trim("abc")); // "abc"
		System.out.println(StringUtils.trim("    abc")); // "abc"
		System.out.println(StringUtils.trim("    abc  ")); // "abc"
		System.out.println(StringUtils.trim("    ab c  ")); // "ab c"
	     
		System.out.println(StringUtils.strip(null)); // null
	    System.out.println(StringUtils.strip("")); // ""
	    System.out.println(StringUtils.strip("   ")); // ""
	    System.out.println(StringUtils.strip("abc")); // "abc"
	    System.out.println(StringUtils.strip("  abc")); // "abc"
	    System.out.println(StringUtils.strip("abc  ")); // "abc"
	    System.out.println(StringUtils.strip(" abc ")); // "abc"
	    System.out.println(StringUtils.strip(" ab c ")); // "ab c"
	     
	    System.out.println(StringUtils.strip("  abcyx", "xyz")); // "  abc"
	    
	    System.out.println(StringUtils.stripStart("yxabcxyz  ", "xyz")); // "abcxyz  "
	    System.out.println(StringUtils.stripEnd("  xyzabcyx", "xyz")); // "  xyzabc"
	}

	private static void testSplit() {		
		//默认半角空格分割
		String str1 = "aaa bbb ccc";
		String[] dim1 = StringUtils.split(str1); // => ["aaa", "bbb", "ccc"]
		
		System.out.println(dim1.length);//3
		System.out.println(dim1[0]);//"aaa"
		System.out.println(dim1[1]);//"bbb"
		System.out.println(dim1[2]);//"ccc"
		
		//指定分隔符
		String str2 = "aaa,bbb,ccc";
		String[] dim2 = StringUtils.split(str2, ","); // => ["aaa", "bbb", "ccc"]
		
		System.out.println(dim2.length);//3
		System.out.println(dim2[0]);//"aaa"
		System.out.println(dim2[1]);//"bbb"
		System.out.println(dim2[2]);//"ccc"
		
		//去除空字符串
		String str3 = "aaa,,bbb";
		String[] dim3 = StringUtils.split(str3, ","); // => ["aaa", "bbb"]
		
		System.out.println(dim3.length);//2
		System.out.println(dim3[0]);//"aaa"
		System.out.println(dim3[1]);//"bbb"
		
		//包含空字符串
		String str4 = "aaa,,bbb";
		String[] dim4 = StringUtils.splitPreserveAllTokens(str4, ","); // => ["aaa", "", "bbb"]
		
		System.out.println(dim4.length);//3
		System.out.println(dim4[0]);//"aaa"
		System.out.println(dim4[1]);//""
		System.out.println(dim4[2]);//"bbb"
		
		//指定分割的最大次数（超过后不分割）
		String str5 = "aaa,bbb,ccc";
		String[] dim5 = StringUtils.split(str5, ",", 2); // => ["aaa", "bbb,ccc"]
		
		System.out.println(dim5.length);//2
		System.out.println(dim5[0]);//"aaa"
		System.out.println(dim5[1]);//"bbb,ccc"
	}
	
	private static void testJoin() {		
		//数组元素拼接
		String[] array = {"aaa", "bbb", "ccc"};
		String result1 = StringUtils.join(array, ","); 
		
		System.out.println(result1);//"aaa,bbb,ccc"
		
		//集合元素拼接
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		String result2 = StringUtils.join(list, ",");
		
		System.out.println(result2);//"aaa,bbb,ccc"
	}
	
	private static void testEscape() {		
		System.out.println(StringEscapeUtils.escapeCsv("测试测试哦"));//"测试测试哦"
		System.out.println(StringEscapeUtils.escapeCsv("测试,测试哦"));//"\"测试,测试哦\""
		System.out.println(StringEscapeUtils.escapeCsv("测试\n测试哦"));//"\"测试\n测试哦\""

		System.out.println(StringEscapeUtils.escapeHtml4("<p>测试测试哦</p>"));//"&lt;p&gt;测试测试哦&lt;/p&gt;"
		System.out.println(StringEscapeUtils.escapeJava("\"rensaninng\"，欢迎您！"));//"\"rensaninng\"\uFF0C\u6B22\u8FCE\u60A8\uFF01"
		
		System.out.println(StringEscapeUtils.escapeEcmaScript("测试'测试哦"));//"\u6D4B\u8BD5\'\u6D4B\u8BD5\u54E6"
		System.out.println(StringEscapeUtils.escapeXml("<tt>\"bread\" & \"butter\"</tt>"));//"&lt;tt&gt;&quot;bread&quot; &amp; &quot;butter&quot;&lt;/tt&gt;"
	}
	
	private static void testRandom() {
		// 10位英字
		System.out.println(RandomStringUtils.randomAlphabetic(10));
		
		// 10位英数
		System.out.println(RandomStringUtils.randomAlphanumeric(10));
		
		// 10位ASCII码
		System.out.println(RandomStringUtils.randomAscii(10));
		
		// 指定文字10位
		System.out.println(RandomStringUtils.random(10, "abcde"));		
	}

	private static void testArray() {		
		// 追加元素到数组尾部
		int[] array1 = {1, 2};
		array1 = ArrayUtils.add(array1, 3); // => [1, 2, 3]
		
		System.out.println(array1.length);//3
		System.out.println(array1[2]);//3
		
		// 删除指定位置的元素
		int[] array2 = {1, 2, 3};
		array2 = ArrayUtils.remove(array2, 2); // => [1, 2]
		
		System.out.println(array2.length);//2
		
		// 截取部分元素
		int[] array3 = {1, 2, 3, 4};
		array3 = ArrayUtils.subarray(array3, 1, 3); // => [2, 3]
		
		System.out.println(array3.length);//2
		
		// 数组拷贝
		String[] array4 = {"aaa", "bbb", "ccc"};
		String[] copied = (String[]) ArrayUtils.clone(array4); // => {"aaa", "bbb", "ccc"}
				
		System.out.println(copied.length);//3		
		
		// 判断是否包含某元素
		String[] array5 = {"aaa", "bbb", "ccc", "bbb"};
		boolean result1 = ArrayUtils.contains(array5, "bbb"); // => true		
		System.out.println(result1);//true
		
		// 判断某元素在数组中出现的位置（从前往后，没有返回-1）
		int result2 = ArrayUtils.indexOf(array5, "bbb"); // => 1		
		System.out.println(result2);//1

		// 判断某元素在数组中出现的位置（从后往前，没有返回-1）
		int result3 = ArrayUtils.lastIndexOf(array5, "bbb"); // => 3
		System.out.println(result3);//3
		
		// 数组转Map
		Map<Object, Object> map = ArrayUtils.toMap(new String[][]{
			{"key1", "value1"},
			{"key2", "value2"}
		});
		System.out.println(map.get("key1"));//"value1"
		System.out.println(map.get("key2"));//"value2"
		
		// 判断数组是否为空
		Object[] array61 = new Object[0];
		Object[] array62 = null;
		Object[] array63 = new Object[]{"aaa"};
		
		System.out.println(ArrayUtils.isEmpty(array61));//true
		System.out.println(ArrayUtils.isEmpty(array62));//true
		System.out.println(ArrayUtils.isNotEmpty(array63));//true
		
		// 判断数组长度是否相等
		Object[] array71 = new Object[]{"aa", "bb", "cc"};
		Object[] array72 = new Object[]{"dd", "ee", "ff"};
		
		System.out.println(ArrayUtils.isSameLength(array71, array72));//true
		
		// 判断数组元素内容是否相等
		Object[] array81 = new Object[]{"aa", "bb", "cc"};
		Object[] array82 = new Object[]{"aa", "bb", "cc"};
		
		System.out.println(ArrayUtils.isEquals(array81, array82));
		
		// Integer[] 转化为 int[]
		Integer[] array9 = new Integer[]{1, 2};
		int[] result = ArrayUtils.toPrimitive(array9);
		
		System.out.println(result.length);//2
		System.out.println(result[0]);//1
		
		// int[] 转化为 Integer[] 
		int[] array10 = new int[]{1, 2};
		Integer[] result10 = ArrayUtils.toObject(array10);
		
		System.out.println(result.length);//2
		System.out.println(result10[0].intValue());//1
	}

	private static void testDate() throws Exception {		
		// 生成Date对象
		Date date = DateUtils.parseDate("2010/01/01 11:22:33", new String[]{"yyyy/MM/dd HH:mm:ss"});

		// 10天后
		Date tenDaysAfter = DateUtils.addDays(date, 10); // => 2010/01/11 11:22:33
		System.out.println(DateFormatUtils.format(tenDaysAfter, "yyyy/MM/dd HH:mm:ss"));

		// 前一个月
		Date prevMonth = DateUtils.addMonths(date, -1); // => 2009/12/01 11:22:33
		System.out.println(DateFormatUtils.format(prevMonth, "yyyy/MM/dd HH:mm:ss"));

		// 判断是否是同一天
		Date date1 = DateUtils.parseDate("2010/01/01 11:22:33", new String[]{"yyyy/MM/dd HH:mm:ss"});
		Date date2 = DateUtils.parseDate("2010/01/01 22:33:44", new String[]{"yyyy/MM/dd HH:mm:ss"});
		System.out.println(DateUtils.isSameDay(date1, date2));
		
		// 日期格式化
		System.out.println(DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
	}
	
}
