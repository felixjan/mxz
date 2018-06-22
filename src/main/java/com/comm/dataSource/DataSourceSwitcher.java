package com.comm.dataSource;

public class DataSourceSwitcher {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}
	public static void setMaster() {
		try {
			clearDataSource();
			setDataSource("master");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void setSlave() {
		try {
			clearDataSource();
			setDataSource("slave");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void setLogMaster() {
		try {
			clearDataSource();
			setDataSource("logMaster");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void setLogSlave() {
		try {
			clearDataSource();
			setDataSource("logSlave");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getDataSource() {
		return (String) contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}
