package com.vertx.property;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyLoader
{
  private static Properties properties = null;

  public static Properties getProperties() {
    return properties;
  }

  public static String getString(String key)
  {
    if (StringUtils.isBlank(key)) {
      return null;
    }
    return properties.getProperty(key.trim());
  }

  public static int getInt(String key) {
    String value = getString(key);
    if (value != null) {
      return Integer.parseInt(value.trim());
    }
    return 0;
  }

  public static long getLong(String key) {
    String value = getString(key);
    if (value != null) {
      return Long.parseLong(value.trim());
    }
    return 0L;
  }

  public static boolean getBoolean(String key) {
    String value = getString(key);
    return Boolean.parseBoolean(value);
  }

  static
  {
    if (properties == null) {
      InputStream inStream = null;
      BufferedReader br = null;
      try {
        inStream = new BufferedInputStream(new FileInputStream("api.properties"));

        br = new BufferedReader(new InputStreamReader(inStream));
        properties = new Properties();
        properties.load(br);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } finally {
        try {
          if (br != null) {
            br.close();
          }
          if (inStream != null)
            inStream.close();
        }
        catch (Exception e)
        {
        }
      }
    }
  }

  static class StringUtils
  {
    private static boolean isBlank(String key)
    {
      if ((key == null) || ("".equals(key.trim()))) {
        return true;
      }
      return false;
    }
  }
}
