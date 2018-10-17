package com.sam.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sam.connection.ConnectionSQL;
import com.sam.helper.bean.DecryptBean;
import com.sam.helper.bean.EncryptedDataBean;
import com.sam.helper.bean.IdAndSSnkeysBean;
import com.sam.helper.bean.KeysBean;


public class DataAccessObj {
	
	private transient static Connection dbConnection;
	private transient static PreparedStatement preparedStatement;
	static ResultSet rs ;
	public static int insertIntoEncTable(List<EncryptedDataBean> encryptedActualData, 
			List<KeysBean> keysList) throws SQLException{
		
		String sqlInsertQuery = "INSERT INTO data_ELWSEA_withOTP values (?,?,?,?,?,?,?,?,?)", deleteQuery="Truncate table data_ELWSEA_withOTP";
		int status1 = 0;
		dbConnection = ConnectionSQL.getConnection();
		try {

			preparedStatement = dbConnection.prepareStatement(deleteQuery);
			preparedStatement.executeUpdate();
			preparedStatement = dbConnection.prepareStatement(sqlInsertQuery);
				for(int i=0;i<encryptedActualData.size();i++){
					if(!encryptedActualData.get(i).getEncSsn().equals(null)){
						
					preparedStatement.setInt(1, (i+1));
					preparedStatement.setString(2, (String) encryptedActualData.get(i).getName());
					preparedStatement.setString(3, (String) encryptedActualData.get(i).getEncEmailId());
					preparedStatement.setString(4, (String) encryptedActualData.get(i).getDepartment());
					preparedStatement.setString(5, (String) encryptedActualData.get(i).getEncPassword());
					preparedStatement.setString(6, (String) encryptedActualData.get(i).getAddress());
					preparedStatement.setString(7, (String) encryptedActualData.get(i).getEncSsn());
					preparedStatement.setString(8, (String) keysList.get(i).getKey1String());
					preparedStatement.setString(9, (String) keysList.get(i).getKey2String());
					
					status1 = preparedStatement.executeUpdate();
				    System.out.println("Data entered into table statues:"+status1);		 
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			dbConnection.close();
		}
		
		
		return status1;
		
	}
	
	public static int insertIntoAESTable(List<EncryptedDataBean> aESEnc) throws SQLException{
		
		String sqlInsertQuery = "INSERT INTO data_AES values (?,?,?,?,?,?)", deleteQuery="Truncate table data_AES";
		int status1 = 0;
		dbConnection = ConnectionSQL.getConnection();
		try {

			preparedStatement = dbConnection.prepareStatement(deleteQuery);
			preparedStatement.executeUpdate();
			preparedStatement = dbConnection.prepareStatement(sqlInsertQuery);
			for(int i=0;i<aESEnc.size();i++){
				if(!aESEnc.get(i).getEncSsn().equals(null)){
					
				preparedStatement.setString(1, (String) aESEnc.get(i).getName());
				preparedStatement.setString(2, (String) aESEnc.get(i).getEncEmailId());
				preparedStatement.setString(3, (String) aESEnc.get(i).getDepartment());
				preparedStatement.setString(4, (String) aESEnc.get(i).getEncPassword());
				preparedStatement.setString(5, (String) aESEnc.get(i).getAddress());
				preparedStatement.setString(6, (String) aESEnc.get(i).getEncSsn());
				status1 = preparedStatement.executeUpdate();	

				System.out.println("AES data entered into table statues:-"+status1);
			}}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			
			e.printStackTrace();
		}
		finally{
			dbConnection.close();
		}
		
		
		return status1;
		
	}
	
	public static int insertIntoDESTable(List<EncryptedDataBean> dESEnc) throws SQLException{
		
			String sqlInsertQuery = "INSERT INTO data_DES values (?,?,?,?,?,?)", deleteQuery="Truncate table data_DES";
			int status1 = 0;
			dbConnection = ConnectionSQL.getConnection();
			try {
	
				preparedStatement = dbConnection.prepareStatement(deleteQuery);
				preparedStatement.executeUpdate();
				preparedStatement = dbConnection.prepareStatement(sqlInsertQuery);
				for(int i=0;i<dESEnc.size();i++){
					System.out.println("ivalue--"+dESEnc.get(i).getName());
				//	if(!dESEnc.get(i).getEncSsn().equals(null)){
						preparedStatement.setString(1, (String) dESEnc.get(i).getName());
						preparedStatement.setString(2, (String) dESEnc.get(i).getEncEmailId());
						preparedStatement.setString(3, (String) dESEnc.get(i).getDepartment());
						preparedStatement.setString(4, (String) dESEnc.get(i).getEncPassword());
						preparedStatement.setString(5, (String) dESEnc.get(i).getAddress());
						preparedStatement.setString(6, (String) dESEnc.get(i).getEncSsn());
						status1 = preparedStatement.executeUpdate();	
						System.out.println("DES data entered into table statues:"+status1);
			//	}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				dbConnection.close();
			}		
			return status1;
		}
	
	public static List<IdAndSSnkeysBean> getIdSSn(String tableName){
		
		List<IdAndSSnkeysBean> lsb = new ArrayList<IdAndSSnkeysBean>();
		IdAndSSnkeysBean idssnb ;
		String getSSnAndId = "Select id,ssn,key1,key2 from "+tableName+";";
		dbConnection = ConnectionSQL.getConnection();	
		try {
			preparedStatement = dbConnection.prepareStatement(getSSnAndId);
			rs =  preparedStatement.executeQuery();
			System.out.print("rs size--"+rs.getRow());
			while(rs.next()){
				 idssnb = new IdAndSSnkeysBean();
				idssnb.setId(rs.getInt("id"));
				idssnb.setSsn(rs.getString("ssn"));
				idssnb.setKey1(rs.getString("key1"));
				idssnb.setKey2(rs.getString("key2"));
				
				lsb.add(idssnb);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lsb;
		
	}
	
	public static EncryptedDataBean getDecryptedData(int id){
		
		System.out.print("id---"+id);
		String queryForRetrievalOfData = "Select * from data_ELWSEA_withOTP where id ="+id;
		EncryptedDataBean edb = new EncryptedDataBean();
		try {
			preparedStatement = dbConnection.prepareStatement(queryForRetrievalOfData);
			rs =  preparedStatement.executeQuery();
			while(rs.next()){
				edb.setAddress(rs.getString("address"));
				edb.setDepartment(rs.getString("department"));
				edb.setEncEmailId(rs.getString("email"));
				edb.setEncPassword(rs.getString("password"));
				edb.setName(rs.getString("name"));

				System.out.print("address---"+edb.getAddress());
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return edb;
		
	}
	
public static List<EncryptedDataBean> getDecryptedData(){
		
		List<EncryptedDataBean> edblist = new ArrayList<EncryptedDataBean>();
		String queryForRetrievalOfData = "Select * from data_ELWSEA_withOTP";
		dbConnection = ConnectionSQL.getConnection();
		EncryptedDataBean edb = null;
		try {
			preparedStatement = dbConnection.prepareStatement(queryForRetrievalOfData);
			rs =  preparedStatement.executeQuery();
			while(rs.next()){
				edb = new EncryptedDataBean();
				edb.setAddress(rs.getString("address"));
				edb.setDepartment(rs.getString("department"));
				edb.setEncEmailId(rs.getString("email"));
				edb.setEncPassword(rs.getString("password"));
				edb.setName(rs.getString("name"));
				edb.setEncSsn(rs.getString("ssn"));	
				edb.setKey1(rs.getString("key1"));
				edb.setKey2(rs.getString("key2"));
				edblist.add(edb);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return edblist;
		
	}
	
	
	}
