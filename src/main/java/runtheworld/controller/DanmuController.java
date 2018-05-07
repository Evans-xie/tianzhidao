package runtheworld.controller;

import org.omg.CORBA.DynAnyPackage.Invalid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import runtheworld.dto.Message;
import runtheworld.entity.Danmu;
import runtheworld.exception.InvalidDanmuException;
import runtheworld.exception.ServerInnerException;
import runtheworld.service.DanmuService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author evans 2018/5/5 11:43
 */

@Controller
@RequestMapping("danmu")
public class DanmuController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DanmuService danmuService;

	@PostMapping("/view/{viewId}")
	@ResponseBody
	public Message pushDanmu(@PathVariable("viewId") int viewId, HttpServletRequest request, @RequestBody Danmu danmu) {
		if (danmu != null) {
			try {
				danmu.setUserId(request.getSession().getId());
				danmuService.putDanmu("view" + viewId, danmu);
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
	public Message getDanmu(@PathVariable("viewId") int viewId,
							@RequestParam(value = "page", defaultValue = "1") int page,
							@RequestParam(value = "size", defaultValue = "50") int size) {
		try {
			List<Danmu> result = danmuService.getLatestDanmu("view" + viewId, size);
			return new Message(200, "获取成功").putData("danmu", result);
		} catch (ServerInnerException e) {
			return new Message(500, e.getMessage());
		}
	}
}
