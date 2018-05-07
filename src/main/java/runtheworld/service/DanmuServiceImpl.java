package runtheworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtheworld.entity.Danmu;
import runtheworld.exception.InvalidDanmuException;
import runtheworld.exception.ServerInnerException;
import runtheworld.util.DanmuValidUtil;

import java.util.List;

/**
 * @author evans 2018/5/6 21:33
 */

@Service
public class DanmuServiceImpl implements DanmuService {
	@Autowired
	DanmuService4Redis danmuService4Redis;

	@Override
	public List<Danmu> getLatestDanmu(String key, long offset, long limit) {
		try {
			return danmuService4Redis.getLatestDanmu(key, offset, limit);
		} catch (Exception e) {
			throw new ServerInnerException("redis操作异常");
		}
	}

	@Override
	public List<Danmu> getLatestDanmu(String key, long limit) throws ServerInnerException {
		try {
			return danmuService4Redis.getLatestDanmu(key, limit);
		} catch (Exception e) {
			throw new ServerInnerException("redis操作异常");
		}
	}


	/**
	 * @param key
	 * @param record
	 */
	@Override
	public void putDanmu(String key, Danmu record) {
		long result = 0;
		try {
			DanmuValidUtil.verifyDanmu(record.getContents());
			result = danmuService4Redis.put(key, record);
		} catch (InvalidDanmuException e) {
			throw e;
		} catch (Exception e) {
			throw new ServerInnerException("redis操作异常");
		}
		if (result < 0) {
			throw new ServerInnerException("redis数据插入失败");
		}
	}
}
