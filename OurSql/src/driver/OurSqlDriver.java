package driver;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class OurSqlDriver implements Driver{
	static {
	     try {
			DriverManager.registerDriver(new OurSqlDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	@Override
	public boolean acceptsURL(String arg0) throws SQLException {
		System.out.println("check url");
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(arg0, ":");
		if(st.hasMoreTokens()) {
			String jdbc=st.nextToken();
			if(jdbc.equals("jdbc")) {
				if(st.hasMoreTokens()) {
					jdbc=st.nextToken();
					if(jdbc.equals("OurSql")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Connection connect(String arg0, Properties arg1) throws SQLException {
		System.out.println("connect");
		// TODO Auto-generated method stub
		if(!acceptsURL(arg0)) {
			return null;
		}
		Connection connection = null;
		connection = new OurSqlConnection();
		return connection;

	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

}
