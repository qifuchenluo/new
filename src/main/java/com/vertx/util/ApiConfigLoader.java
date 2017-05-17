package com.vertx.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vertx.api.pojo.ApiConfiguration;
import com.vertx.property.PropertyLoader;

public class ApiConfigLoader
{
  static Logger logger = LoggerFactory.getLogger(ApiConfigLoader.class);
  public static Map<String, ApiConfiguration> apiConfigurationMap;

  public static Map<String, ApiConfiguration> loadApiConfig()
  {
    if (apiConfigurationMap == null) {
      try {
        apiConfigurationMap = new HashMap();

        String fatherPackage = PropertyLoader.getString("api.father.package");

        List<String> classNames = PackageUtil.getClassName(fatherPackage, false);
        for (String className : classNames) {
          ApiConfiguration apiConfiguration = new ApiConfiguration();

          //String apiid = className.substring(className.lastIndexOf(".") + 1).toLowerCase().replace("api", "");
          String apiid = className.substring(className.lastIndexOf(".") + 1).replace("api", "");
          apiConfiguration.setApiid(apiid);
          apiConfiguration.setClassName(className);
         
          apiConfigurationMap.put(apiConfiguration.getApiid(), apiConfiguration);
          logger.info("加载API：apiid={},class={}", new Object[] { apiConfiguration.getApiid(), apiConfiguration.getClassName() });
        }

        logger.info("成功加载{}个API", Integer.valueOf(apiConfigurationMap.size()));
      }
      catch (Exception e)
      {
        logger.error(ApiConfigLoader.class.getSimpleName(), e);
      }
    }
    return apiConfigurationMap;
  }
}