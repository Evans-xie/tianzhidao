package runtheworld.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtheworld.dao.DanmuMapper;
import runtheworld.dao.DanmuZanMapper;
import runtheworld.entity.Danmu;
import runtheworld.entity.DanmuZan;
import runtheworld.entity.DanmuZanExample;
import runtheworld.exception.RepeatZanException;
import runtheworld.exception.ServerInnerException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author evans 2018/5/22 14:34
 */
@Service
public class ZanServiceImpl implements ZanService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DanmuMapper danmuMapper;
    @Autowired
    DanmuZanMapper danmuZanMapper;

    @Override
    public void doZan(long danmuId, long userId) {
        int result = 0;
        try {
            DanmuZan danmuZan = new DanmuZan(danmuId, userId, new Date(), new Date());
            danmuZan.setIsDeleted((byte) 0);
            result = danmuZanMapper.insert(danmuZan);
            System.out.println("result:" + result);
            //联合主键重复，可能有两种情况，1：重复点赞，2：点赞后取消了再点赞--》改isDelete 1值为0
            if (result <= 0) {
                DanmuZanExample example = new DanmuZanExample();
                example.createCriteria().andDanmuIdEqualTo(danmuId).andUserIdEqualTo(userId);
                DanmuZan zan = danmuZanMapper.selectByExample(example).get(0);
                if (zan.getIsDeleted() == 0) {
                    throw new RepeatZanException("重复点赞");
                } else {
                    zan.setIsDeleted((byte) 0);
                    zan.setGmtModified(new Date());
                    danmuZanMapper.updateByPrimaryKey(zan);
                }
            }
        } catch (RepeatZanException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServerInnerException("数据库异常");
        }
    }

    @Override
    public void deleteZan(long danmuId, long userId) {
        try {
            danmuZanMapper.deleteByUk(danmuId, userId, new Date());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServerInnerException("数据库异常");
        }
    }

    @Override
    public List<Danmu> pullMyZan(long userId) {
        try {
            DanmuZanExample example=new DanmuZanExample();
            example.createCriteria().andUserIdEqualTo(userId).andIsDeletedEqualTo((byte)0);
            List<DanmuZan> danmuZans =danmuZanMapper.selectByExample(example);
            List<Danmu> result=new ArrayList<>();
            for(DanmuZan danmuZan:danmuZans){
                result.add(danmuMapper.selectByPrimaryKey(danmuZan.getDanmuId()));
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServerInnerException("数据库异常");
        }
    }
}
