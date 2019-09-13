package com.example.springbootwebsocket.server;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Websocket 视频通讯
 */
@ServerEndpoint(value = "/websocket/chat/video")
@Component
public class VideoController extends BaseMediaController {

	private static final List<AbstractWsController> CONNECTIONS = new CopyOnWriteArrayList<>();

	@Override
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		super.onOpen(session, config);
	}

	@Override
	@OnClose
	public void onClose() {
		super.onClose();
	}

	@Override
	@OnMessage(maxMessageSize = 10000000)
	public void onMessage(String message) {
		super.onMessage(message);
	}

	@Override
	@OnMessage(maxMessageSize = 10000000)
	public void onMessage(ByteBuffer message) {
		super.onMessage(message);
	}

	@Override
	@OnError
	public void onError(Throwable t) {
	}

	@Override
	List<AbstractWsController> getConnections() {
		return CONNECTIONS;
	}

	@Override
	String getConnectType() {
		return "video";
	}

}
