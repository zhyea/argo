package com.zhyea.argo.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;

/**
 * 日志IP获取
 *
 * @author robin
 * @since 2025/5/10 13:17
 */
public class LocalIpConverter extends ClassicConverter {

	private String ip = null;

	@Override
	public String convert(ILoggingEvent iLoggingEvent) {
		if (null == this.ip) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception e) {
				return "";
			}
		}

		return this.ip;
	}
}
