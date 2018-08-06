package nc.bs.hzjy.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import nc.bs.hzjy.plugins.SdVoucherTask;

;

//”√jdbc¡¨Ω”
public class MidTableExcute {
	private java.sql.Statement stmt = null;
	private Connection con = null;
	public String dbName;

	public int oracleUpdate(String dbname, String Sql) throws SQLException,
			ClassNotFoundException {
		int k = -1;
		this.setDbName(dbname);
		try {
			SdVoucherTask sd = new SdVoucherTask();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@" + sd.getDBIp() + ":"
					+ sd.getSid();
			String username = sd.getUsername();
			String password = sd.getPassword();
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			k = stmt.executeUpdate(Sql);
			con.commit();
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

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return dbName;
	}
}