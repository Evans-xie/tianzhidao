package runtheworld.service;

import runtheworld.entity.Danmu;

import java.util.List;

/**
 * @author evans 2018/5/22 14:06
 */

public interface ZanService {
    public void doZan(long danmuId,long userId);
    public void deleteZan(long danmuId,long userId);
    public List<Danmu> pullMyZan(long userId);
}
