package runtheworld.service;

import runtheworld.entity.Danmu;
import runtheworld.exception.ServerInnerException;

import java.util.List;

/**
 * @author evans 2018/5/5 12:57
 */

public interface DanmuService {
	public List<Danmu> getLatestDanmu(String key,long offset,long limit) throws ServerInnerException;
	public List<Danmu> getLatestDanmu(String key,long limit) throws ServerInnerException;
	public void putDanmu(String key,Danmu record) throws ServerInnerException;
}
