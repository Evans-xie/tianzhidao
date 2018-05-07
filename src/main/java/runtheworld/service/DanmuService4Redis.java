package runtheworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import runtheworld.entity.Danmu;
import runtheworld.util.RedisUtil;
import runtheworld.util.SerializeUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author evans 2018/5/6 15:19
 */
@Service
public class DanmuService4Redis {

	@Autowired
	RedisTemplate redisTemplate;

	public List<Danmu> getLatestDanmu(String key,long limit){
		return getLatestDanmu(key, 0	, limit);
	}
	/**
	 * 获取最新的limit条记录
	 * @param key "view"+viewId
	 * @param limit 数量
	 * @return
	 */
	public List<Danmu> getLatestDanmu(String key,long offset,long limit){
		return (List<Danmu>) redisTemplate.opsForList().range(key, offset, limit);
	}

	public Long put(String key,Danmu record){
		byte[] data=SerializeUtil.serialize(record);
		return redisTemplate.opsForList().leftPush(key, record);
	}

	public Boolean delete(String key, long seconds){
		return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}
}
