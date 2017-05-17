package com.vertx.api.impl;
import java.io.BufferedReader;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.RandomStringUtils;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//import net.sf.json.JSONObject;
import org.jredis.ClientRuntimeException;
import org.jredis.JRedis;
import org.jredis.RedisException;
import org.jredis.connector.ConnectionSpec;
import org.jredis.ri.alphazero.JRedisClient;
import org.jredis.ri.alphazero.connection.DefaultConnectionSpec;
import org.jredis.ri.alphazero.support.DefaultCodec;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.fbds.common.Constants;
import com.vertx.api.ApiService;
import com.vertx.property.PropertyLoader;
//import com.vertx.util.ApacheHttpClientImpl;
import com.test.design.*;
import com.test.design.UHttpClient.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.MultiMap;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;
public class User implements ApiService{
	Logger logger = LoggerFactory.getLogger(User.class);
    public HttpServerRequest httpRequest; 
    static String fbdsServerUrl = PropertyLoader.getString("fbds.server.url");
    private JRedis jredis = null;
    public User(){
    	 String host= PropertyLoader.getString("redis.server");
	     String port= PropertyLoader.getString("redis.port");
	     String db = PropertyLoader.getString("redis.db");
		 jredis = getJRedis(host, Integer.parseInt(port),  Integer.parseInt(db));
    }
   	
	public JSONObject request(MultiMap para)  {
		// TODO Auto-generated method stub
		//传入用户名，传出用户密码，并写入redis
		JSONObject ResultObj = new JSONObject();
	    String result  = new String();
	    try {	
			if(!para.contains("un"))
			{
				ResultObj.put("status", "error");
				return ResultObj;
			}
		String user = para.get("un").toString();//用户名
		boolean exists = isExists(user);
		String userkey = null;
		if(exists){//如果存在，返回密钥
			userkey = new String(jredis.hget("usertable",user)); 
		}
		else{//如果不存在，生成密钥写入redis并返回
			userkey = MD5Utils.getPwd(user.concat("slat"));
			jredis.hset("usertable", user, userkey);
		}
		ResultObj.put("user", user);
		ResultObj.put("userkey", userkey);
		return ResultObj;		
	}catch (RedisException e)
		{
		ResultObj.put("status", "10003");
		e.printStackTrace();
		return ResultObj;
	}
	}
	private boolean isExists(String username) throws RedisException {
		// TODO Auto-generated method stub
		return jredis.exists(username);
	}
	 private static org.jredis.JRedis getJRedis(String host, int port, int db) {
			// TODO Auto-generated method stub
			JRedis redis = null;
			try {
				ConnectionSpec conn = DefaultConnectionSpec.newSpec().setAddress(InetAddress.getByName(host)).setPort(port)
						.setDatabase(db);
				//conn.setCredentials("opensure");//没有密码认证
				redis = new JRedisClient(conn);
			} catch (ClientRuntimeException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return redis;
		}
}
