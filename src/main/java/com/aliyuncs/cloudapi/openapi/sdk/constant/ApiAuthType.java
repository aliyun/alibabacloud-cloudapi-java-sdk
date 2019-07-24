package com.aliyuncs.cloudapi.openapi.sdk.constant;

/**
 * @author ailan.gl 2019-04-01
 */
public enum ApiAuthType {
	/**
	 * 只允许已授权的APP调
	 */
	APP,
	/**
	 *
	 */
	ANONYMOUS,
	/**
	 *
	 */
	OPENID,
	/**
	 *
	 */
	APPOPENID,
	;

	public String openapiValue() {
		return this.name();
	}
}
