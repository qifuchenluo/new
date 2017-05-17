package com.vertx.api;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.MultiMap;
import com.vertx.api.pojo.ApiConfiguration;
import com.vertx.handler.RouterHandler;
import com.vertx.util.ApiConfigLoader;

public class ApiRouter {
	  Logger logger = LoggerFactory.getLogger(ApiRouter.class);
	  static Map<String, ApiConfiguration> apiConfigurationMap;
	  MultiMap multiMap;
	  
	  public static synchronized void init()
	  {
	    apiConfigurationMap = ApiConfigLoader.loadApiConfig();
	  }
	   
	  public ApiRouter(MultiMap multiMap)
	  {
	    this.multiMap = multiMap;
	  }
	  public String routeApi()
	  {
	    String apiid;
	    if (this.multiMap.contains("apiid"))
	      apiid = this.multiMap.get("apiid").toString();
	    
	    else
	      return "缺少apiid参数";
	    String className = null;
	    for (Map.Entry entry : apiConfigurationMap.entrySet()) {
	        if (((String)entry.getKey()).equals(apiid)) {
	          className = ((ApiConfiguration)entry.getValue()).getClassName();
	        }
	    }
	    //this.logger.info("className:"+className);
	      if (className == null) {
	    	  return "apiid "+apiid+"不存在";
	      }       
	    try
	    {
	      System.out.println(className);
	      Class clazz = Class.forName(className);
		  ApiService apiInstance = (ApiService)clazz.newInstance();
		  if (apiInstance != null) {
		      return apiInstance.request(multiMap).toString();
		  }
		  return "route api error";
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	      return "ClassNotFoundException error";
	    } catch (InstantiationException e){
	      e.printStackTrace();
		  return "InstantiationException error";
	    } catch (IllegalAccessException e){
	      e.printStackTrace();
		  return "IllegalAccessException error";
	    } 
	  }
}