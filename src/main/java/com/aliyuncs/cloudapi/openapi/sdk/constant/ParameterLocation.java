package com.aliyuncs.cloudapi.openapi.sdk.constant;

import com.aliyuncs.utils.StringUtils;

/**
 * @author ailan.gl 2019-03-30
 */
public enum ParameterLocation {
	/**
	 * Initial value
	 */
	NONE("", ""),
	/**
	 * in Http Header
	 */
	HEADER("header", "HEAD"),
	/**
	 * in Http QueryString
	 */
	QUERY("query", "QUERY"),
	/**
	 * in http request path
	 */
	PATH("path", "PATH"),
	/**
	 * in http body
	 */
	BODY("body", ""),
	/**
	 * HTTP form parameter
	 */
	FORM_DATA("formData", "BODY"),
	/**
	 * 域名参数
	 */
	HOST("host", "HOST"),
	;


	private String swaggerValue;
	private String openapiValue;

	ParameterLocation(String swaggerValue, String openapiValue) {
		this.swaggerValue = swaggerValue;
		this.openapiValue = openapiValue;
	}

	public String swaggerValue() {
		return this.swaggerValue;
	}

	public String openapiValue() {
		return this.openapiValue;
	}

	public static ParameterLocation fromText(String in) {
		if (StringUtils.isEmpty(in)) {
			return ParameterLocation.NONE;
		}
		String s = in.toLowerCase();
		if ("query".equals(s)) {
			return ParameterLocation.QUERY;
		} else if ("head".equals(s) || "header".equals(s)) {
			return ParameterLocation.HEADER;
		} else if ("path".equals(s)) {
			return ParameterLocation.PATH;
		} else if ("form".equals(s) || "formdata".equals(s)) {
			return ParameterLocation.FORM_DATA;
		} else if ("body".equals(s)) {
			return ParameterLocation.BODY;
		}
		throw new IllegalArgumentException("invalid location:" + in);
	}
}
