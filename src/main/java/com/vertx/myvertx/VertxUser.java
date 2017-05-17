package com.vertx.myvertx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Container;
import org.vertx.java.platform.Verticle;
import com.vertx.api.ApiRouter;
import com.vertx.config.VertxServerConfig;
import com.vertx.config.WebServerConfig;
import com.vertx.handler.RouterHandler;
import com.vertx.util.SchedulerExecutors;

public class VertxUser extends Verticle
{ //应用组件，当Verticle被部署的时候，其start方法会被调用
  RouteMatcher routeMatcher = new RouteMatcher();
  Map<String, Handler<HttpServerRequest>> routerHanderMap = new HashMap();
  Logger logger = LoggerFactory.getLogger(VertxUser.class);
  
  public void start() { 
	  HttpServer server = this.vertx.createHttpServer();
      WebServerConfig webServerConfig = new WebServerConfig();

      JsonObject config = new JsonObject();
      if (this.container == null) {
        config.putString("webHost", "localhost");
        config.putNumber("webPort", Integer.valueOf(8080));
      } else {
        config = this.container.config();
      }
     //入口
      String webHost = config.getString("webHost");
      int webPort = config.getInteger("webPort").intValue();
      VertxServerConfig.port = webPort;
      
      this.routerHanderMap.put("/api/manager", new Handler<HttpServerRequest>() {	
      public void handle(HttpServerRequest req) {
    	  new RouterHandler(new VertxServerConfig(VertxUser.this.vertx, req, VertxUser.this.vertx.eventBus()));
      }//调用RouterHandler
    });
    
    webServerConfig.setRouterHanderMap(this.routerHanderMap);
    
    if (webServerConfig != null) {
        for (Map.Entry entry : webServerConfig.getRouterHanderMap().entrySet()) {
          String pattern = (String)entry.getKey();
          Handler handler = (Handler)entry.getValue();
          routeMatcher.get(pattern, handler);
          routeMatcher.post(pattern, handler);
        }
      }

     routeMatcher.noMatch(new Handler<HttpServerRequest>() {
        public void handle(HttpServerRequest req) {
	        req.response().headers().set("Content-Type", "text/html; charset=UTF-8"); 
	        req.response().end("<html><body><h1>Unknown Request:"+req.path()+"!!</h1></body></html>"); 
        }
      });
    
     server.requestHandler(routeMatcher).listen(webPort,webHost);
     
     initAPIRouter();// 初始化api路由
     this.logger.info("VertxToken started!");
   }

  
	  public void initAPIRouter()
	  {
	    ApiRouter.init();
	  }
}