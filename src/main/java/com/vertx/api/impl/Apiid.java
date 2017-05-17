package com.vertx.api.impl;
import java.io.BufferedReader;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.RandomStringUtils;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import com.vertx.config.VertxServerConfig;
import com.vertx.handler.RouterHandler;
import com.vertx.property.PropertyLoader;
import com.vertx.api.ApiDefault;
import com.vertx.api.ApiRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.MultiMap;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;
public class Apiid implements ApiService{
	Logger logger = LoggerFactory.getLogger(Apiid.class);
    public HttpServerRequest httpRequest; 
    static String fbdsServerUrl = PropertyLoader.getString("fbds.server.url");
    private JRedis jredis = null;
    public Apiid(){
    	 String host= PropertyLoader.getString("redis.server");
	     String port= PropertyLoader.getString("redis.port");
	     String db = PropertyLoader.getString("redis.db");
		 jredis = getJRedis(host, Integer.parseInt(port),  Integer.parseInt(db));
    }
   	
	public JSONObject request(MultiMap para) {
		// TODO Auto-generated method stub
		//先判断ak是否对的，user和ak是否对应
		JSONObject ResultObj = new JSONObject();
	    String result  = new String();
	    try {	
			if(!para.contains("un"))
			{
				ResultObj.put("error", "缺少用户名");
				return ResultObj;
			}
			if(!para.contains("key")){
				ResultObj.put("error", "缺少密钥");
				return ResultObj;
			}
			if(!para.contains("apinum")){
				ResultObj.put("error", "缺少接口编码");
				return ResultObj;
			}
			String user = para.get("un").toString();//用户名
			//boolean existsuser = isExists(user);
			boolean existsuser = hsExists("usertable", user);
			if(!existsuser){
				ResultObj.put("error", "该用户未申请密钥");
				return ResultObj;
			}			
			String input_key = para.get("key").toString();//用户输入密钥
			//判断redis里密钥和用户输入是否一致
			String storekey = new String(jredis.hget("usertable", user));
			if (!input_key.equals(storekey)){
				ResultObj.put("error", "用户名密码不一致");
				return ResultObj;
			}
			//判断该用户密钥是否可用
			String status = new String(jredis.hget(input_key, "STATUS"));
			if (status == "0"){
				ResultObj.put("warn", "该密钥已失效");
				return ResultObj;
			}
			//判断ip,如果ip是0.0.0.0或者在ip列表里面
			String iplist = new String(jredis.hget(input_key, "IPLIST"));		
			String[] ipr = iplist.split(";");
			//String requestip = para.contains("ip") ? RouterHandler.multiMap.get("ip") : null;
			String requestip = RouterHandler.ip;
			//String[] ip_host = requestip.substring(1,requestip.length()).split(":");
			//String ip = requestip.substring(1,requestip.length());
			//this.logger.info("获取请求ip后"+ip_host[0]);
			if (ipr[0].equals("0.0.0.0")||strexists(ipr,requestip)){
			//if (ipr[0] == "0.0.0.0"){
				String apinum = para.get("apinum").toString();
				boolean existsapi = hsExists(input_key,apinum);
				if(!existsapi){
					ResultObj.put("error", "该用户未申请此接口");
					return ResultObj;
				}	
				String count = new String(jredis.hget(input_key,apinum));
				String cnt;
				String strcnt;
				int int_cnt = 0;
				if (count == "0")
				{
					ResultObj.put("warn", "剩余调用次数为0");
					return ResultObj;
				}
				else if(count == "-1"){
					cnt = count;
					strcnt = "无限次";
				}
				else{					
					try {
					     int_cnt = Integer.parseInt(count);
					} catch (NumberFormatException e) {
					    e.printStackTrace();
					}
					int_cnt = --int_cnt;
					strcnt = Integer.toString(int_cnt);
					jredis.hset(input_key, apinum, strcnt);
				}
				ResultObj.put("status","success");
				ResultObj.put("api",apinum);
				//ResultObj.put("ip",ip_host[0]);
				ResultObj.put("ip",requestip);
				ResultObj.put("count",strcnt);
				return ResultObj;
			}
			else{
				ResultObj.put("error", "访问ip受限");
				return ResultObj;
			}
	    }	
			catch (RedisException e)
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
	private boolean hsExists(String key,String field) throws RedisException {
		return jredis.hexists(key, field);
	}
	private boolean strexists(String[] list,String str) {
		for(int i = 0; i < list.length; i++){
	        if(list[i].equals(str)){
	            return true;
	         }
	    }
	    return false;
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
