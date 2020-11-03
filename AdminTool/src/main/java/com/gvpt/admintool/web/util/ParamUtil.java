/*
 * Created on 20 Jun 2017 ( Time 10:16:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.gvpt.admintool.web.util;

import com.gvpt.admintool.common.util.DateUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class ParamUtil {
	public static String getString(HttpServletRequest request, String param){
		return request.getParameter(param);
	}
	
	public static Integer getInteger(HttpServletRequest request, String param){
		String parameter = request.getParameter(param);
		if(parameter != null && !parameter.isEmpty()){
			return Integer.valueOf(parameter);
		} else {
			return null;
		}
	}
	
	public static Long getLong(HttpServletRequest request, String param){
		String parameter = request.getParameter(param);
		if(parameter != null && !parameter.isEmpty()){
			return Long.valueOf(parameter);
		} else {
			return null;
		}
	}
	
	public static Date getDate(HttpServletRequest request, String param){
		return DateUtil.parse(request.getParameter(param));
	}

	public static boolean getBoolean(HttpServletRequest request, String param) {
		String parameter = request.getParameter(param);
		if(parameter != null && !parameter.isEmpty()){
			return true;
		}
		return false;
	}
}