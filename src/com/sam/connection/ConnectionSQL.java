package com.sam.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sam.Util.JDBCUtil;



public final class ConnectionSQL {
	//public static final Logger LOG=Logger.getLogger(ConnectionManager.class); 
	private static Connection connection;
	
	public ConnectionSQL()
	{
		
	}
	
	/**
	 * This method will be used to establish connection with database
	 * @return connection
	 */
	public static Connection getConnection(){
	System.out.println("Called to give connection");
		
		final String driver=JDBCUtil.getProperty("driver");
		final String url=JDBCUtil.getProperty("url");
		final String user=JDBCUtil.getProperty("user");
		final String password=JDBCUtil.getProperty("password");
		try {
			
				Class.forName(driver);
				connection=DriverManager.getConnection(url, user, password);
		
		}	
		 catch (SQLException e) 
		{
			System.out.println("failed to give connection");
			//LOG.error("fail to give connection");
		} catch (ClassNotFoundException e)
		{
			System.out.println("failed to find class");
			//LOG.error("fail to find the class");
		}
		return connection;
		}
	
	/**
	 * This method is used to close the connection variable
	 */
	public static void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//LOG.error("fail to close the Connection");
		}
	}
	/*public static void main(String[] args) throws SQLException {
		
		Connection con= ConnectionSQL.getConnection();
		
	PreparedStatement stmt = con.prepareStatement("SELECT * FROM data_etsfs; ");
	
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
			    String x = rs.getString("name");
			    String s = rs.getString("email");
			    System.out.println(x+"----"+s);
			}
			System.out.println(stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			stmt.close();
			con.close();
		}
	}
	*/
	
		
}
