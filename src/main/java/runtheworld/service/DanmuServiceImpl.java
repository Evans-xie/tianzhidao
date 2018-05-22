package runtheworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtheworld.dao.DanmuMapper;
import runtheworld.entity.Danmu;
import runtheworld.entity.DanmuExample;
import runtheworld.exception.InvalidDanmuException;
import runtheworld.exception.ServerInnerException;
import runtheworld.util.DanmuValidUtil;

import java.util.Date;
import java.util.List;

/**
 * @author evans 2018/5/6 21:33
 */

@Service
public class DanmuServiceImpl implements DanmuService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DanmuService4Redis danmuService4Redis;
    @Autowired
    DanmuMapper danmuMapper;

    @Override
    public List<Danmu> getLatestDanmu(long viewId, Integer offset, Integer limit) {
        try {
            String key = "view" + viewId;
            return danmuService4Redis.getLatestDanmu(key, offset, limit);
        } catch (Exception e) {
            throw new ServerInnerException("redis操作异常");
        }
    }

    @Override
    public List<Danmu> getLatestDanmu(long viewId, Integer limit) throws ServerInnerException {
        try {
            String key = "view" + viewId;
            return danmuService4Redis.getLatestDanmu(key, limit);
        } catch (Exception e) {
            throw new ServerInnerException("redis操作异常");
        }
    }


    /**
     * @param viewId
     * @param record
     */
    @Override
    public void putDanmu(long viewId, Danmu record) {
        long result = 0;
        try {
            String key = "view" + viewId;
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

    @Override
    public List<Danmu> pullDanmu(long viewId) {
        try {
            DanmuExample example = new DanmuExample();
            example.createCriteria().andViewIdEqualTo(viewId).andIsDeletedEqualTo((byte)0);
            example.setOrderByClause("gmt_create desc");
            return danmuMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServerInnerException("数据库异常");
        }
    }

    @Override
    public List<Danmu> pullMyDanmu(long userId) {
        try {
            DanmuExample example=new DanmuExample();
            example.createCriteria().andUserIdEqualTo(userId).andIsDeletedEqualTo((byte)0);
            return danmuMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServerInnerException("数据库异常");
        }
    }

    @Override
    public void pushDanmu(Danmu record) {
        try {
            Date time = new Date();
            //验证弹幕内容
            DanmuValidUtil.verifyDanmu(record.getContents());
            record.setGmtCreate(time);
            record.setGmtModified(time);
            danmuMapper.insertSelective(record);
        } catch (InvalidDanmuException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServerInnerException("数据库异常");
        }
    }

    @Override
    public void deleteDanmu(long danmuId) {
        try {
            danmuMapper.deleteById(danmuId, new Date());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServerInnerException("数据库异常");
        }
    }
}
