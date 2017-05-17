package com.vertx.config;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

//import org.apache.http.client.fluent.Request;
import org.vertx.java.core.MultiMap;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;

public class VertxServerConfig
{
  private Vertx vertx;
  private HttpServerRequest httpServerRequest;
  private HttpServerResponse httpServletResponse;
  private EventBus eventBus;
  private Logger logger;
  private JsonObject config;
  public static int port;
  public SocketAddress address; 


  public VertxServerConfig(Vertx vertx, HttpServerRequest httpServerRequest, EventBus eventBus)
  {
    this.vertx = vertx;
    this.httpServerRequest = httpServerRequest;
    this.eventBus = eventBus;
  }

  public VertxServerConfig(Vertx vertx, HttpServerRequest httpServerRequest, EventBus eventBus, Logger logger) {
    this.vertx = vertx;
    this.httpServerRequest = httpServerRequest;
    this.eventBus = eventBus;
    this.logger = logger;
  }

  public Vertx getVertx() {
    return this.vertx;
  }

  public void setVertx(Vertx vertx) {
    this.vertx = vertx;
  }

  public HttpServerRequest getHttpServerRequest() {
    return this.httpServerRequest;
  }

  public void setHttpServerRequest(HttpServerRequest httpServerRequest) {
    this.httpServerRequest = httpServerRequest;
  }
  
  public HttpServerResponse getHttpServerResponse() {
	    return this.httpServletResponse;
	  }
  
  public void setHttpServerResponse(HttpServerResponse httpServletResponse) {
	    this.httpServletResponse = httpServletResponse;
	  }

  public static Map<String, String> multiMap2HashMap(MultiMap multiMap) {
    Map map = new HashMap();
    for (String key : multiMap.names()) {
      map.put(key, multiMap.get(key));
    }
    return map;
  }
  public static String getip(HttpServerRequest httpServerRequest){
	  String ip = httpServerRequest.remoteAddress().getHostString();
	  return ip;
	  
  }
  public EventBus getEventBus() {
    return this.eventBus;
  }

  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public Logger getLogger() {
    return this.logger;
  }

  public void setLogger(Logger logger) {
    this.logger = logger;
  }

  public JsonObject getConfig() {
    return this.config;
  }

  public void setConfig(JsonObject config) {
    this.config = config;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    port = port;
  }
}