package com.aliyuncs.cloudapi.openapi.sdk.constant;

import com.aliyuncs.utils.StringUtils;

/**
 * @author ailan.gl 2019-03-30
 */
public enum ParameterType {
	/**
	 * 未知类型
	 */
	UNKNOWN(""),
	/**
	 * string
	 */
	STRING("String"),
	/**
	 * integer
	 */
	INTEGER_32("Int"),
	/**
	 * long
	 */
	INTEGER("Long"),
	/**
	 * float
	 */
	NUMBER("Double"),
	/**
	 * boolean
	 */
	BOOLEAN("Boolean"),
	/**
	 * 数组类型
	 */
	ARRAY("Array"),
	/**
	 * 文件类型:
	 * If type is "file", the consumes MUST be either "multipart/form-data", " application/x-www-form-urlencoded" or both and the parameter MUST be in "formData".
	 */
	FILE("File"),
	;

	private String openapiValue;

	ParameterType(String openapiValue) {
		this.openapiValue = openapiValue;
	}

	public String openapiValue() {
		return this.openapiValue;
	}

	public static ParameterType fromText(String s) {
		if (StringUtils.isEmpty(s)) {
			return UNKNOWN;
		}

		s = s.toLowerCase();
		if ("int".equals(s.toLowerCase()) || "integer".equals(s.toLowerCase())) {
			return INTEGER_32;
		} else if ("string".equals(s.toLowerCase())) {
			return STRING;
		} else if ("long".equals(s.toLowerCase())) {
			return INTEGER;
		} else if ("float".equals(s.toLowerCase()) || "real".equals(s.toLowerCase())) {
			return NUMBER;
		} else if ("double".equals(s.toLowerCase()) || "number".equals(s.toLowerCase())) {
			return NUMBER;
		} else if ("boolean".equals(s.toLowerCase()) || "bool".equals(s.toLowerCase())) {
			return BOOLEAN;
		}
		throw new IllegalArgumentException("Unknown parameter type:" + s);
	}
}
