package com.hatsukoi.genesis.remoting.transport;

import com.hatsukoi.genesis.common.URL;
import com.hatsukoi.genesis.remoting.ChannelHandler;
import com.hatsukoi.genesis.remoting.Codec;

/**
 * @author gaoweilin
 * @date 2022/06/07 Tue 3:59 AM
 */
public abstract class AbstractEndpoint extends AbstractPeer {

    private Codec codec;

    private int timeout;

    private int connectTimeout;

    public AbstractEndpoint(URL url, ChannelHandler handler) {
        super(url, handler);
        // TODO: 初始化成员变量
    }

    protected static Codec getChannelCodec(URL url) {
        String codecName = url.getParameter(Constants.CODEC_KEY, "telnet");
        if (ExtensionLoader.getExtensionLoader(Codec2.class).hasExtension(codecName)) {
            return ExtensionLoader.getExtensionLoader(Codec2.class).getExtension(codecName);
        } else {
            return new CodecAdapter(ExtensionLoader.getExtensionLoader(Codec.class)
                    .getExtension(codecName));
        }
    }
}
