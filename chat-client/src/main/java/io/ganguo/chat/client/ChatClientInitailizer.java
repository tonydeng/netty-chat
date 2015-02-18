package io.ganguo.chat.client;

import io.ganguo.chat.client.handler.UserHandler;
import io.ganguo.chat.core.codec.PacketDecoder;
import io.ganguo.chat.core.codec.PacketEncoder;
import io.ganguo.chat.core.handler.IMHandlerManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ChatClientInitailizer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();

		pipeline.addLast("decoder", new PacketDecoder(8192, 0, 4));
		pipeline.addLast("encoder", new PacketEncoder());

		pipeline.addLast("handler", new ChatClientHandler());

		initIMHandler();
	}

	private void initIMHandler() {
		IMHandlerManager.getInstance().register(UserHandler.class);
	}
}