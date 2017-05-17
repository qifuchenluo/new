package com.vertx.handler;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.MultiMap;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServerRequest;
import com.vertx.api.ApiRouter;
import com.vertx.config.VertxServerConfig;
//获取某一待办事项的逻辑方法
public class RouterHandler {
	  public static  MultiMap multiMap;	 
	  Logger logger = LoggerFactory.getLogger(RouterHandler.class);
	  public HttpServerRequest httpRequest;
	  EventBus eventBus;
	  public static String ip;
	  public RouterHandler(VertxServerConfig vs) {
		  httpRequest = vs.getHttpServerRequest();
		  multiMap = httpRequest.params();
		  //multiMap = vs.getHttpServerRequest().params();
          ApiRouter apiRoute = new ApiRouter(multiMap);//核心
         // String ip = multiMap.contains("ip") ? multiMap.get("ip") : null;
          ip = vs.getip(httpRequest);
          String result = apiRoute.routeApi();
          System.out.println(String.format("%s", result));
          //System.out.println(result);
          this.logger.info(result);
          
          //String requestip = VertxServerConfig.getip(vs.getHttpServerRequest());
          vs.getHttpServerRequest().response().headers().set("Content-Type", "text/html; charset=UTF-8"); 
          vs.getHttpServerRequest().response().end(result); 
          }
	
}
