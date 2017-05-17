package com.vertx.api;

import java.util.Map;

import org.vertx.java.core.MultiMap;

public class ApiDefault {
	 public static String getparams(Map<String,String> paramMap){
		  String paramstr = new String();
		  for(Map.Entry<String, String> entry:paramMap.entrySet()){   
			     paramstr += entry.getKey()+"="+entry.getValue()+"&"; 
		  }  
		  return paramstr.substring(0,paramstr.length()-1);
	  }
	 public static String getpara(MultiMap para){
		  String parastr = new String();
		  for(Map.Entry<String, String> entry:para.entries()){   
			     parastr += entry.getKey()+"="+entry.getValue()+"&"; 
		  }  
		  return parastr.substring(0,parastr.length()-1);
	  }
}
