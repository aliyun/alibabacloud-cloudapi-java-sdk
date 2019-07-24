package com.aliyuncs.cloudapi.openapi.sdk.common;

/**
 * OpenAPI操作日志记录工具, 可以从此派生自己的Logger
 *
 * @author ailan.gl 2019-03-30
 */
public interface OperationLogger {
	/**
	 * 启动一个Logging
	 * @param action
	 * @param requestMessage
	 * @return
	 */
	OperationContext begin(String action, String requestMessage);
}
