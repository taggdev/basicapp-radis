package com.simpleradis.util;

import com.simpleradis.annotation.Refresh;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashSet;

@Component
public class JedisUtil {

	Jedis jedis;
	
	public Jedis getClient() throws Exception {
		if (jedis == null) {
			create();
		}
		return jedis;
	}
	
	public void close() throws IOException {
		jedis.close();
		jedis = null;
	}
	
	@Refresh
	public void create() throws Exception {

		HostAndPort hostAndPort = new HostAndPort("localhost", 6379);
		HashSet<HostAndPort> hashSet = new HashSet<>();
		hashSet.add(hostAndPort);

		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setLifo(true);
		poolConfig.setFairness(false);
		poolConfig.setMaxWaitMillis(1000);
		poolConfig.setMinEvictableIdleTimeMillis(1800000);
		poolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
		poolConfig.setNumTestsPerEvictionRun(3);
		poolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
		poolConfig.setTestOnCreate(false);
		poolConfig.setTestOnBorrow(false);
		poolConfig.setTestOnReturn(false);
		poolConfig.setTestWhileIdle(false);
		poolConfig.setBlockWhenExhausted(true);
		poolConfig.setJmxEnabled(true);
		poolConfig.setTimeBetweenEvictionRunsMillis(-1);
		poolConfig.setJmxNamePrefix("pool");
		poolConfig.setMaxTotal(30);
		poolConfig.setMaxIdle(8);
		poolConfig.setMinIdle(0);

//        jedis = new JedisCluster(hostAndPort, 1000, 1000, 3, "P@ssw0rd", poolConfig);
        jedis = new Jedis(hostAndPort.getHost(), hostAndPort.getPort(), 1000, 1000);
	}
}
