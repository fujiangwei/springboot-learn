package com.example.springbootwebsocket.controller;

import com.example.springbootwebsocket.server.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/9/11
 * @time: 23:38
 * @modifier:
 * @since:
 */
@Controller
@RequestMapping(value = "mywebsocket")
public class WebSocketController {

    @GetMapping(value = "chat/{sid}")
    public String chat(ModelMap modelMap, @PathVariable String sid) {
        modelMap.put("sid", sid);

        return "chat";
    }

    /**
     * 测试 打开两个页面：
     http://localhost:8083/checkcentersys/checkcenter/socket/20
     http://localhost:8083/checkcentersys/checkcenter/socket/22
     向前端推送数据：
     http://localhost:8083/checkcentersys/checkcenter/socket/push/20?message=cccccccccc
     http://localhost:8083/checkcentersys/checkcenter/socket/push/22?message=xxxxx123xxxx
     * @param modelMap
     * @param sid
     * @return
     */
    @GetMapping(value = "chat2/{sid}")
    public String chat2(ModelMap modelMap, @PathVariable String sid) {
        modelMap.put("sid", sid);

        return "chat2";
    }

    @GetMapping(value = "index/{sid}")
    public String index(ModelMap modelMap, @PathVariable String sid) {
        modelMap.put("sid", sid);

        return "index";
    }

    @GetMapping(value = "redirect")
    public String redirect() {
        return "index";
    }

    @GetMapping(value = "add")
    @ResponseBody
    public String add(String result, String message) throws IOException {
        if (result.equals("1")) {
            return result;
        } else {
            WebSocketServer.sendInfo(message, "00001");
            return result;
        }
    }

    // 推送数据接口
    @ResponseBody
    @RequestMapping("/sendInfo/{sid}")
    public String sendInfo2Web(@PathVariable String sid, String message) {
        try {
            WebSocketServer.sendInfo(message, sid);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "ok";
    }
}
