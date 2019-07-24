package com.aliyuncs.cloudapi.openapi.sdk.common;

/**
 *
 * @author ailan.gl 2019-03-30
 */
public class OperationLoggerFactory {
	private static OperationLogger defaultLogger = new OperationLogger() {
		@Override
		public OperationContext begin(String action, String s) {
			return new OperationContextSimple(action, s);
		}
	};

	public static void setDefaultLogger(OperationLogger logger) {
		defaultLogger = logger;
	}

	public static OperationLogger getLogger(String name) {
		return defaultLogger;
	}
}
