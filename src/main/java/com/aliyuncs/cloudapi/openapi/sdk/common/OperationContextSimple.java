package com.aliyuncs.cloudapi.openapi.sdk.common;

import com.aliyuncs.exceptions.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ailan.gl 2019-03-31
 */
public class OperationContextSimple implements OperationContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationContextSimple.class);

	private String action;
	private String requestMessage;
	private long begin;

	OperationContextSimple(String action, String requestMessage) {
		this.action = action;
		this.requestMessage = requestMessage;
		this.begin = System.currentTimeMillis();
	}

	@Override
	public void success(String requestId, String result) {
		long costMs = System.currentTimeMillis() - begin;
		LOGGER.info("{}<{}> cost={}ms {} -> {}", action, requestId, costMs, requestMessage, result);
	}

	@Override
	public void failed(ClientException ex) {
		long costMs = System.currentTimeMillis() - begin;
		LOGGER.info("{}<{}> cost={}ms {} -> {}", action, ex.getRequestId(), costMs, requestMessage, ex.toString());
	}
}
