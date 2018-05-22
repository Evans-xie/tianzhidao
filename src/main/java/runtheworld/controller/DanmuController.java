package runtheworld.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import runtheworld.dto.Message;
import runtheworld.entity.Danmu;
import runtheworld.exception.InvalidDanmuException;
import runtheworld.exception.RepeatZanException;
import runtheworld.exception.ServerInnerException;
import runtheworld.service.DanmuService;
import runtheworld.service.ZanService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author evans 2018/5/5 11:43
 */

@Controller
@RequestMapping("danmu")
public class DanmuController {
    @Autowired
    DanmuService danmuService;

    @Autowired
    ZanService zanService;

    @PostMapping("/view/{viewId}")
    @ResponseBody
    public Message pushDanmu(@PathVariable("viewId") Long viewId, HttpServletRequest request, @RequestBody Danmu danmu) {
        if (danmu != null) {
            try {
                danmuService.pushDanmu(danmu);
//                danmuService.putDanmu(viewId, danmu);
                return new Message(200, "发布弹幕成功");
            } catch (InvalidDanmuException e) {
                return new Message(401, e.getMessage());
            } catch (Exception e) {
                return new Message(500, e.getMessage());
            }
        } else {
            return new Message(400, "无效弹幕");
        }
    }

    @GetMapping("/view/{viewId}")
    @ResponseBody
    public Message getDanmu(@PathVariable("viewId") Long viewId,
                            @RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "50") int size) {
        try {
            PageHelper.startPage(page, size);
            List<Danmu> danmus = danmuService.pullDanmu(viewId);
//            List<Danmu> result = danmuService.getLatestDanmu(viewId, size);
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
    @GetMapping("/my/{userId}")
    @ResponseBody
    public Message pullDanmu(@PathVariable("userId") Long userId) {
        try {
            List<Danmu> danmus = danmuService.pullMyDanmu(userId);
            return new Message(200, "获取成功").putData("danmus", danmus);
        } catch (Exception e) {
            return new Message(500, e.getMessage());
        }
    }

    @DeleteMapping("/{danmuId}/{userId}")
    @ResponseBody
    public Message deleteDanmu(@PathVariable("danmuId") Long danmuId, @PathVariable Long userId) {
        try {
            danmuService.deleteDanmu(danmuId);
            return pullDanmu(userId);
        } catch (Exception e) {
            return new Message(500, e.getMessage());
        }
    }

    @PostMapping("/zan/{danmuId}/{userId}")
    @ResponseBody
    public Message doZan(@PathVariable("danmuId") Long danmuId, @PathVariable("userId") Long userId) {
        try {
            zanService.doZan(danmuId, userId);
            return new Message(200, "点赞成功");
        } catch (RepeatZanException e) {
            return new Message(401, e.getMessage());
        } catch (ServerInnerException e) {
            return new Message(500, e.getMessage());
        }
    }

    @DeleteMapping("/zan/{danmuId}/{userId}")
    @ResponseBody
    public Message cancelZan(@PathVariable("danmuId") Long danmuId, @PathVariable("userId") Long userId) {
        try {
            zanService.deleteZan(danmuId, userId);
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
    @GetMapping("/zan/{userId}")
    @ResponseBody
    public Message pullZan(@PathVariable("userId") Long userId) {
        try {
            List<Danmu> result = zanService.pullMyZan(userId);
            return new Message(200, "获取成功").putData("danmus", result);
        } catch (Exception e) {
            return new Message(500, e.getMessage());
        }
    }
}
