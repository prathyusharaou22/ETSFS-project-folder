package com.sam.Util;

import java.util.ResourceBundle;

public class JDBCUtil {

	private JDBCUtil(){
		
	}
	
	public static String getProperty(final String key){
		String value;
		final ResourceBundle rBundle=ResourceBundle.getBundle("jdbc");
		value=rBundle.getString(key);
		return value;
	}	
}
