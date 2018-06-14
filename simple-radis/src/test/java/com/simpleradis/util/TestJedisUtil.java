package com.simpleradis.util;

import com.simpleradis.config.SimpleredisAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SimpleredisAppConfig.class})
public class TestJedisUtil {

    @Autowired
    protected JedisUtil jedisUtil;

    @Test
    public void testSetJedis() {
//        JedisUtil jedisUtil = new JedisUtil();

        try {
            Jedis jedis = jedisUtil.getClient();
            jedis.set("test:test-add", "Hello world");
            jedis.expire("test:test-add", 30);
            String jsonSbm3gCusInfo = jedis.get("test:test-add");
            System.out.println("===> " + jsonSbm3gCusInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testListJedis() {
        try {
            Jedis jedis = jedisUtil.getClient();

            jedis.lpush("test:list:queue#tasks", "firstTask");
            jedis.lpush("test:list:queue#tasks", "secondTask");

            String task = jedis.rpop("test:list:queue#tasks");
            System.out.println("===>task : " + task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetsJedis() {
        try {
            Jedis jedis = jedisUtil.getClient();

            jedis.sadd("test:sets:nicknames", "nickname#1");
            jedis.sadd("test:sets:nicknames", "nickname#2");
            jedis.sadd("test:sets:nicknames", "nickname#1");

            Set<String> nicknames = jedis.smembers("test:sets:nicknames");
            for(String rank:nicknames) {
                System.out.println("\t===> nickname : " + rank);
            }

            boolean exists = jedis.sismember("test:sets:nicknames", "nickname#1");
            System.out.println("===>exist : " + exists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHashesJedis() {
        try {
            Jedis jedis = jedisUtil.getClient();

            jedis.hset("test:hashes:user#1", "name", "Peter");
            jedis.hset("test:hashes:user#1", "job", "politician");

            jedis.hset("test:hashes:user#2", "name", "Steave");
            jedis.hset("test:hashes:user#2", "job", "police");

            String name = jedis.hget("test:hashes:user#1", "name");
            System.out.println("===>name : " + name);
            name = jedis.hget("test:hashes:user#1", "job");
            System.out.println("===>job : " + name);

            Map<String, String> fields = jedis.hgetAll("test:hashes:user#1");
            for( Map.Entry<String, String> ss:fields.entrySet()) {
                System.out.println("\t===>key : " + ss.getKey()+" | value : "+ss.getValue());

            }

            String job = fields.get("job");
            System.out.println("or ===>job : " + job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSortedSetsJedis() {
        try {
            Jedis jedis = jedisUtil.getClient();

            Map<String, Double> scores = new HashMap<>();

            scores.put("PlayerOne", 3000.0);
            scores.put("PlayerTwo", 1500.0);
            scores.put("PlayerThree", 8200.0);

            scores.keySet().forEach(player -> {
                jedis.zadd("test:sort-set:ranking", scores.get(player), player);
            });

            String player = jedis.zrevrange("test:sort-set:ranking", 0, 1).iterator().next();
            System.out.println("===>player : " + player);
            long rank = jedis.zrevrank("test:sort-set:ranking", "PlayerOne");
            System.out.println("===>rank : " + rank);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTransactionsJedis() {
        try {
            Jedis jedis = jedisUtil.getClient();

            String friendsPrefix = "test:transaction:friends#";
            String userOneId = "4352523";
            String userTwoId = "5552321";

            Transaction t = jedis.multi();
            t.sadd(friendsPrefix + userOneId, userTwoId);
            t.sadd(friendsPrefix + userTwoId, userOneId);
            t.exec();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPipeliningJedis() {
        try {
            Jedis jedis = jedisUtil.getClient();

            String userOneId = "4352523";
            String userTwoId = "4849888";

            Pipeline p = jedis.pipelined();
            p.sadd("test:pipelining:searched#" + userOneId, "paris");
            p.zadd("test:pipelining:ranking", 126, userOneId);
            p.zadd("test:pipelining:ranking", 325, userTwoId);
            Response<Boolean> pipeExists = p.sismember("test:pipelining:searched#" + userOneId, "paris");
            Response<Set<String>> pipeRanking = p.zrange("test:pipelining:ranking", 0, -1);
            p.sync();

            Boolean exists = pipeExists.get();
            System.out.println("===>player : " + exists);
            Set<String> ranking = pipeRanking.get();
            for(String rank:ranking) {
                System.out.println("\t===> rank : " + rank);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //http://www.baeldung.com/jedis-java-redis-client-library

}
