/**
* <p>Title: StringUtils.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2016</p>
* <p>Company: carcredit</p>
* @author xiaogang
* @date 2017年5月4日 上午11:40:29
* @version 1.0
*/
package com.qqw.demo.quartz.utils;

import java.text.DecimalFormat;
import java.util.UUID;

/**
* <p>Title: StringUtils</p>
* <p>Description: </p>
* <p>Company: carcredit</p> 
* @author xiaogang
*/
public class StringUtils {
	
	
	/**
	* <p>Title: isEmpty</p>
	* <p>Description: 字符串不为空</p>
	* @author xiaogang
	* @param object
	* @return
	 */
	public static boolean isEmpty(Object object){
	    if(object==null){
	        return true;
        }
        String str = object.toString();
		if (str == null || str.length() == 0 || "".equals(str)) {
            return true;
        }
		return false;
	}
	/**
	* <p>Title: isEmptyNotZero</p>
	* <p>Description: 不为空 并且 不为0</p>
	* @author xiaogang
	* @param str
	* @return
	 */
	public static boolean isEmptyNotZero(String str){
		if (str == null || str.length() == 0 || "".equals(str) || "0".equals(str)) {
            return false;
        }
		return true;
	}
	/**
	* <p>Title: isEmpty</p>
	* <p>Description: 字符串不为空</p>
	* @author xiaogang
	* @param object
	* @return
	 */
	public static boolean isNotEmpty(Object object){
		return !isEmpty(object);
	}
	
	public static String transString(Double data){
		if(data==null){
			return "";
		}
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		return decimalFormat.format(data);
	}

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }


}
