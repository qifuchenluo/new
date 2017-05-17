package com.vertx.api;

import java.util.Map;
import org.vertx.java.core.MultiMap;

import com.alibaba.fastjson.JSONObject;
import com.vertx.property.PropertyLoader;

import org.vertx.java.core.json.JsonObject;

public abstract interface ApiService
{
  public static String rpcEnable = PropertyLoader.getString("api.rpc.enable");
  public abstract JSONObject request(MultiMap para);
}
