package com.example.springbootwebsocket.server;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootwebsocket.domain.Message;
import com.example.springbootwebsocket.utils.StringUtil;

/**
 * 公共逻辑
 */
public abstract class BaseController extends AbstractWsController {

	private static final String CONNECT_TYPE_TEXT = "text";

	/**
	 * 接受客户端发送的字符串
	 *
	 * @param message 字符串消息
	 */
	@Override
	protected void onMessage(String message) {
		Message msg = JSONObject.parseObject(message, Message.class);
		msg.setHost(getUserName());
		if (CONNECT_TYPE_TEXT.equals(getConnectType())) {
			msg.setMsg(StringUtil.txt2htm(msg.getMsg()));
			if (msg.getDests() == null) {
				broadcast2All(msg.toString());
			} else {
				broadcast2Special(msg.toString(), msg.getDests());
			}
		} else {
			broadcast2Others(msg.toString());
		}
	}

}
