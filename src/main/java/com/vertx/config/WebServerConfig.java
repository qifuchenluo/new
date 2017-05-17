package com.vertx.config;

import java.util.Map;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;

public class WebServerConfig
{
  Map<String, Handler<HttpServerRequest>> routerHanderMap;

  public Map<String, Handler<HttpServerRequest>> getRouterHanderMap()
  {
    return this.routerHanderMap;
  }

  public void setRouterHanderMap(Map<String, Handler<HttpServerRequest>> routerHanderMap) {
    this.routerHanderMap = routerHanderMap;
  }
}