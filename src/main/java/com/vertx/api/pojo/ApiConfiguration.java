package com.vertx.api.pojo;

import java.util.List;

public class ApiConfiguration
{
  String apiid;
  String[] paramIn;
  String className;
  List<String> optionalParam;

  public String getApiid()
  {
    return this.apiid;
  }

  public void setApiid(String apiid) {
    this.apiid = apiid;
  }

  public String[] getParamIn() {
    return this.paramIn;
  }

  public void setParamIn(String[] paramIn) {
    this.paramIn = paramIn;
  }

  public String getClassName() {
    return this.className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public List<String> getOptionalParam() {
    return this.optionalParam;
  }

  public void setOptionalParam(List<String> optionalParam) {
    this.optionalParam = optionalParam;
  }
}