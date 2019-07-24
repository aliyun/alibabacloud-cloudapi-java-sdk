package com.aliyuncs.cloudapi.openapi.sdk.common;

import com.aliyuncs.exceptions.ClientException;

/**
 * @author ailan.gl 2019-03-30
 */
public interface OperationContext {
	/**
	 *
	 * @param requestId
	 */
	void success(String requestId, String msg);

	/**
	 *
	 * @param ex
	 */
	void failed(ClientException ex);
}
