package runtheworld.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import runtheworld.dto.Message;
import runtheworld.entity.Danmu;
import runtheworld.entity.UserDemo.UserDanmu;
import runtheworld.exception.InvalidDanmuException;
import runtheworld.exception.RepeatZanException;
import runtheworld.exception.ServerInnerException;
import runtheworld.service.DanmuService;
import runtheworld.service.ZanService;

import java.util.List;


/**
 * @author evans 2018/5/5 11:43
 */

@RestController
@RequestMapping("/danmu")
@Api(description = "弹幕相关接口,没做权限验证")
public class DanmuController {
    @Autowired
    DanmuService danmuService;

    @Autowired
    ZanService zanService;

    /**
     * 发送弹幕接口
     *
     * @param viewId
     * @param danmu
     * @return
     */
    @ApiOperation(value = "发送弹幕", notes = "在对应的全景图view下发弹幕", position = 0)
    @PostMapping("/view{viewId}")
    public Message pushDanmu(@PathVariable("viewId") Long viewId, @RequestBody Danmu danmu) {
        if (danmu != null) {
            try {
                danmuService.pushDanmu(danmu);
//                danmuService.putDanmu(viewId, danmu);
                return new Message(200, "发送弹幕成功");
            } catch (InvalidDanmuException e) {
                return new Message(401, e.getMessage());
            } catch (Exception e) {
                return new Message(500, e.getMessage());
            }
        } else {
            return new Message(400, "无效弹幕");
        }
    }

    /**
     * 获取弹幕接口
     *
     * @param viewId
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "获取弹幕", notes = "获取对应的全景图view下的弹幕,用viewid=1测试", position = 1)
    @GetMapping("/view{viewId}")
    public Message getDanmu(@PathVariable("viewId") Long viewId,
                            @RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "50") int size) {
        try {
            PageHelper.startPage(page, size);
            List<Danmu> danmus = danmuService.pullDanmu(viewId);
            //todo:如何选择出现的弹幕
//            PageInfo<Danmu> pageInfo=new PageInfo<>(danmus);

//            List<Danmu> result = danmuService.getLatestDanmu(viewId, size);
//            return new Message(200, "获取成功").putData("danmusPageInfo", pageInfo);
            return new Message(200, "获取成功").putData("danmus", danmus);
        } catch (ServerInnerException e) {
            return new Message(500, e.getMessage());
        }
    }

    /**
     * 获取我发的弹幕
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户所发弹幕", notes = "根据userid获取", position = 2)
    @GetMapping("/my")
    public Message pullDanmu(@RequestParam("userId") Long userId) {
        try {
            List<Danmu> danmus = danmuService.pullMyDanmu(userId);
            return new Message(200, "获取成功").putData("danmus", danmus);
        } catch (Exception e) {
            return new Message(500, e.getMessage());
        }
    }

    /**
     * 删除用户发送的弹幕
     *
     * @param userDanmu
     * @return
     */
    @ApiOperation(value = "删除弹幕", notes = "根据userid删除danmuId对应的弹幕，返回用户所发送的所有弹幕", position = 3)
    @DeleteMapping("/my")
    public Message deleteDanmu(@RequestBody UserDanmu userDanmu) {
        try {
            danmuService.deleteDanmu(userDanmu.getDanmuId());
            return pullDanmu(userDanmu.getUserId());
        } catch (Exception e) {
            return new Message(500, e.getMessage());
        }
    }

    /**
     * 点赞
     *
     * @param userDanmu
     * @return
     */
    @ApiOperation(value = "对弹幕点赞", notes = "userId用户对danmuId弹幕点赞", position = 4)
    @PostMapping("/zan")
    public Message doZan(@RequestBody UserDanmu userDanmu) {
        try {
            zanService.doZan(userDanmu.getDanmuId(), userDanmu.getUserId());
            return new Message(200, "点赞成功");
        } catch (RepeatZanException e) {
            return new Message(401, e.getMessage());
        } catch (ServerInnerException e) {
            return new Message(500, e.getMessage());
        }
    }

    @ApiOperation(value = "取消点赞", notes = "需userId和danmuId", position = 5)
    @DeleteMapping("/zan")
    public Message cancelZan(@RequestBody UserDanmu userDanmu) {
        try {
            zanService.deleteZan(userDanmu.getDanmuId(), userDanmu.getUserId());
            return new Message(200, "取消成功");
        } catch (Exception e) {
            return new Message(500, e.getMessage());
        }
    }

    /**
     * 获取我赞的弹幕
     *
     * @return
     */
    @ApiOperation(value = "获取用户所赞的弹幕", notes = "需userId", position = 6)
    @GetMapping("/zan")
    public Message pullZan(@RequestParam("userId") Long userId) {
        try {
            List<Danmu> result = zanService.pullMyZan(userId);
            return new Message(200, "获取成功").putData("danmus", result);
        } catch (Exception e) {
            return new Message(500, e.getMessage());
        }
    }
}
