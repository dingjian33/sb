package nc.bs.hzyb.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.DAOException;
import nc.bs.hzyb.plugins.SdVoucherTask;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.hzyb.pub.Log;

/**
 * 该类用JDBC API 完成对中间表的回写
 * 
 * @author liutya
 * 
 */
// 中间表回写，不用NC里的方法
public class MidTableExcute {

	private static MidTableExcute excute = new MidTableExcute();
	private java.sql.Statement stmt = null;
	private Connection con = null;
	public String dbName;

	private MidTableExcute() {
	}

	public static MidTableExcute getInstance() {
		return excute;
	}

	public int oracleUpdate(String dbname, String Sql) throws SQLException,
			ClassNotFoundException {
		SdVoucherTask sd = new SdVoucherTask();
		int k = -1;
		this.setDbName(dbname);
		Log.getInstance().info("dbname" + dbname);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@" + sd.getDBIp() + ":"
					+ sd.getSid();

			Log.getInstance().info("数据库地址url" + url);
			String username = sd.getUsername();
			String password = sd.getPassword();
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			k = stmt.executeUpdate(Sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return k;
	}

	public Map<String, Object> sql(String dbname, String Sql)
			throws SQLException, ClassNotFoundException {
		SdVoucherTask sd = new SdVoucherTask();

		ResultSet rs = null;
		Map<String, Object> data = new HashMap<String, Object>();
		this.setDbName(dbname);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@" + sd.getDBIp() + ":"
					+ sd.getSid();
			String username = sd.getUsername();
			String password = sd.getPassword();
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(Sql);
			while (rs.next()) {
				ResultSetMetaData rsMeta = rs.getMetaData();
				int co = rsMeta.getColumnCount();
				for (int i = 1; i <= co; i++) {
					data.put(rsMeta.getColumnLabel(i), rs.getObject(i));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return data;
	}

	public Object oracleQuery(String sql1, ResultSetProcessor processor)
			throws DAOException {
		Object obj = null;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql1);
			obj = pstmt.executeQuery(sql1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return dbName;
	}
}