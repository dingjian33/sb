package nc.bs.hzyb.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import nc.bs.framework.common.InvocationInfo;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.RuntimeEnv;

public class ConfigUtils {

	private static String cfgfilepath = "ierp/bin/importDataConfig.properties";

	public static String getConfigPath() {
		return cfgfilepath;
	}

	public static String getDataSource() {
		if (RuntimeEnv.getInstance().isRunningInServer()) {
			return InvocationInfoProxy.getInstance().getUserDataSource();
		} else {
			return new InvocationInfo(null, null, null, null, null)
					.getUserDataSource();
		}
	}

	// 获取中间件数据源
	public static String getMidDataSource() {
		Properties prop = new Properties();
		String ds = null;
		try {
			FileInputStream inStream = new FileInputStream(new File(
					ConfigUtils.getConfigPath()));
			prop.load(inStream);
			ds = prop.getProperty("midDataSource");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
