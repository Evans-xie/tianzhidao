package runtheworld.service;

import runtheworld.entity.Danmu;
import runtheworld.exception.ServerInnerException;

import java.util.List;

/**
 * @author evans 2018/5/5 12:57
 */

public interface DanmuService {
	public List<Danmu> getLatestDanmu(long viewId,Integer offset,Integer limit) throws ServerInnerException;
	public List<Danmu> getLatestDanmu(long viewId,Integer limit) throws ServerInnerException;
	public void putDanmu(long viewId,Danmu record) throws ServerInnerException;

	/**
	 * mysql 实现
	 */
	public List<Danmu> pullDanmu(long viewId);
	public List<Danmu> pullMyDanmu(long userId);
	public void pushDanmu(Danmu record);
	public void deleteDanmu(long danmuId);
}
