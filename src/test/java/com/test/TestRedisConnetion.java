package com.test;

import redis.clients.jedis.Jedis;

public class TestRedisConnetion {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("47.106.140.79",6379);
        System.out.println(jedis.ping());
        jedis.close();
    }
}
