package com.test.design;

import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import com.test.design.MD5Utils;

/**
 * Hello world!
 *
 */
public class JredisConnect 
{
	public void connect(String url) {		
		// TODO Auto-generated method stub
		// Connecting to Redis server on localhost
		//实例化一个客户端
		url = "192.168.159.129";
		Jedis jedis = new Jedis(url);
		//=================================================
		// check whether server is running or not
		//ping下，看看是否通的		
    	Map<String, String> ak1=new HashMap<String, String>();
		Map<String, String> ak2=new HashMap<String, String>();
		Map<String, String> user1=new HashMap<String, String>();
		user1.put("au", "zjdx");
		String pwd = MD5Utils.getPwd(user1.get("au").concat("slat"));
		System.out.println(pwd);
		user1.put("secretkey",pwd);
		//保存一个
		jedis.set("leiTest", "localhost Connection  sucessfully");
		//删除
		jedis.del("leiTest");
		//获取一个
		System.out.println("通过key获取value：    " + jedis.get("leiTest"));
        System.out.println( "Hello World!" );
		System.out.println("Server is running: " + jedis.ping());		
	}
    public static void main( String[] args)
    {
    	
    }
}
