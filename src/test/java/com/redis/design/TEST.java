package com.redis.design;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jredis.ClientRuntimeException;
import org.jredis.JRedis;
import org.jredis.RedisException;
import org.jredis.connector.ConnectionSpec;
import org.jredis.ri.alphazero.JRedisClient;
import org.jredis.ri.alphazero.connection.DefaultConnectionSpec;
import org.jredis.ri.alphazero.support.DefaultCodec;

import com.alibaba.fastjson.JSONObject;
//import com.vertx.util.ApacheHttpClientImpl;

public class TEST {
	private JRedis jredis = null;
	public TEST() {
		String jredisHost = "192.168.122.111";
		int jredisPort = 6379;
		jredis = getJRedis(jredisHost, jredisPort, 0);//use database 16
	}
	
	private static JRedis getJRedis(String host,int port,int db) {
	    JRedis jredis = null;
	    try {
	      ConnectionSpec conn = DefaultConnectionSpec.newSpec()
	      .setAddress(InetAddress.getByName(host)).setPort(port)
	      .setDatabase(db);
	      jredis = new JRedisClient(conn);
	    }
	    catch (ClientRuntimeException e) {
	      e.printStackTrace();
	    }
	    catch (UnknownHostException e) {
	      e.printStackTrace();
		}
	    return jredis;
}
	public static void main(String[] args) throws RedisException
	{
		 JRedis jredis = getJRedis("192.168.8.72",6379,0);
		 //byte[] bytes = jredis.hget("appkey:AEF2A3165EB130D3F352BEA236232660","total");
         String bbbb="{\"dubboInterfaceObjectList\":[{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1001\",\"id\":\"XC1001\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1002\",\"id\":\"XC1002\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1003\",\"id\":\"XC1003\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1004\",\"id\":\"XC1004\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1005\",\"id\":\"XC1005\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1006\",\"id\":\"XC1006\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1007\",\"id\":\"XC1007\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1008\",\"id\":\"XC1008\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"xc-1009\",\"id\":\"XC1009\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ001\",\"id\":\"ZJJ001\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ002\",\"id\":\"ZJJ002\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ003\",\"id\":\"ZJJ003\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ004\",\"id\":\"ZJJ004\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ005\",\"id\":\"ZJJ005\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ006\",\"id\":\"ZJJ006\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ007\",\"id\":\"ZJJ007\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ008\",\"id\":\"ZJJ001\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"},{\"apiRouterTypeEnum\":\"NONE\",\"apiStatusEnum\":\"ACTIVATE\",\"group\":\"ZJJ009\",\"id\":\"ZJJ009\",\"iface\":\"com.asiainfo.zjapi.nodeserver.service.ApiService\",\"providerId\":\"33\",\"version\":\"1.0.0\"}],\"iPPortObjectList\":[{\"ip\":\"192.168.8.72\",\"port\":8089}],\"providerId\":\"33\"}";
         jredis.hset("apiConfiguration", "33", bbbb);
		 byte[] bytes = jredis.hget("apiConfiguration","33");
         String value = DefaultCodec.toStr(bytes);
         System.out.println(value);	
	}
}
