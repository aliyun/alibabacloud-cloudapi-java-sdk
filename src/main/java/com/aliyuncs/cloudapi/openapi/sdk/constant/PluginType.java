package com.aliyuncs.cloudapi.openapi.sdk.constant;

/**
 * @author ailan.gl 2019-04-01
 */
public enum PluginType {
	/**
	 *
	 */
	IP_CONTROL("ipControl"),
	/**
	 *
	 */
	TRAFFIC_CONTROL("trafficControl"),
	/**
	 *
	 */
	BACKEND_SIGNATURE("backendSignature"),
	/**
	 *
	 */
	JWT("jwtAuth"),
	/**
	 *
	 */
	CACHING("caching"),
	/**
	 *
	 */
	CORS("cors"),
	/**
	 *
	 */
	ROUTING("routing"),
	;

	private String value;
	PluginType(String value) {
		this.value = value;
	}

	public String openapiValue() {
		return value;
	}
}
