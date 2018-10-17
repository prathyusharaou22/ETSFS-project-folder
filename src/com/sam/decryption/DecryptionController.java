package com.sam.decryption;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opencsv.CSVWriter;
import com.sam.dao.DataAccessObj;
import com.sam.helper.bean.DecryptBean;
import com.sam.helper.bean.EncryptedDataBean;
import com.sam.helper.bean.IdAndSSnkeysBean;

/**
 * Servlet implementation class DecryptionController
 */
@WebServlet(name = "DecryptionController", urlPatterns = {"/DecryptionController"})
@MultipartConfig
public class DecryptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecryptionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("camein");
		RequestDispatcher rDispatcher;String tableName = "data_ELWSEA_withOTP";
	    response.setContentType("text/html;charset=UTF-8");
		List<IdAndSSnkeysBean> idssn = new ArrayList<IdAndSSnkeysBean>();
		EncryptedDataBean edb = new EncryptedDataBean();
		DecryptBean dbop = new DecryptBean();

		HttpSession session = request.getSession();
	    String ssn;
	    ssn = request.getParameter("ssn");
	    
		List<String> list;int id = 0;
		// to find the id with ssn
		
		if(!ssn.equals(null) && ssn.length() !=0){
			idssn = DataAccessObj.getIdSSn(tableName);
			String DecryptedSSN =null;
			int i=0;boolean flag = false;String message = "notFound";
			int sizeOfList = idssn.size();
			
			
			while(!flag ){
				String keys1[] = idssn.get(i).getKey1().split(",");
					String keys2[] = idssn.get(i).getKey2().split(",");
					list = new ArrayList<String>();list.add(idssn.get(i).getSsn());list.add(keys1[2]);list.add(keys2[2]);
					DecryptedSSN = DecryptionCls.actualDecryption(list);
					System.out.println(DecryptedSSN);
					
					if(DecryptedSSN.equals(ssn)){
						message = "found a match";
						flag = true;
						id  = idssn.get(i).getId();
						edb = DataAccessObj.getDecryptedData(id);
						dbop =DoDecryption(keys1,keys2,edb);
					}
					i++;	
					if(i == sizeOfList){
						flag = true;
					}				
			}
			
			if(message.equals("found a match")){
				System.out.print("address setting the ui-"+dbop.getAddress());
				session.setAttribute("name",dbop.getName());
				session.setAttribute("address",dbop.getAddress());
				session.setAttribute("email",dbop.getDecEmailId());
				session.setAttribute("password",dbop.getDecPassword() );
				session.setAttribute("department",dbop.getDepartment() );
				session.setAttribute("ssn", DecryptedSSN);
				rDispatcher = request.getRequestDispatcher("DecryptedOutput.jsp");
		   	    rDispatcher.forward(request, response);
			}
			else{
				session.setAttribute("errorMessage", "DECRYPTION ERROR");
				session.setAttribute("errorForNull", "SSN NOT FOUND");
				rDispatcher = request.getRequestDispatcher("error.jsp");
		   	    rDispatcher.forward(request, response);
			}
		}
		else{
			List<DecryptBean> dblist = new ArrayList<DecryptBean>();
			List<EncryptedDataBean> edblist = new ArrayList<EncryptedDataBean>();
			edblist = DataAccessObj.getDecryptedData();
			DecryptBean db;
			String[] key1array, key2array ;List<String> tempList;
			for(int i=0;i<edblist.size();i++){
				db = new DecryptBean();
				key1array = edblist.get(i).getKey1().split(",");
				key2array = edblist.get(i).getKey2().split(",");
				db.setName(edblist.get(i).getName());
				tempList = new ArrayList<String>();tempList.add(edblist.get(i).getEncEmailId());tempList.add(key1array[0]);tempList.add(key2array[0]);
				db.setDecEmailId(DecryptionCls.actualDecryption(tempList));
				db.setDepartment(edblist.get(i).getDepartment());
				tempList = new ArrayList<String>();tempList.add(edblist.get(i).getEncPassword());tempList.add(key1array[1]);tempList.add(key2array[1]);
				db.setDecPassword(DecryptionCls.actualDecryption(tempList));
				db.setAddress(edblist.get(i).getAddress());
				tempList = new ArrayList<String>();tempList.add(edblist.get(i).getEncSsn());tempList.add(key1array[2]);tempList.add(key2array[2]);
				db.setDecSsn(DecryptionCls.actualDecryption(tempList));
				dblist.add(i,db);
			}
			writeToCSVFile(dblist);
			rDispatcher = request.getRequestDispatcher("DataBaseDataDecrypt.jsp");
			rDispatcher.forward(request, response);
		}
	}
	
	
	private void writeToCSVFile(List<DecryptBean> dblist) {
		
			
		try {
			// use FileWriter constructor that specifies open for appending
			 CSVWriter writer = new CSVWriter(new FileWriter("/Users/rajakaruparthi/Desktop/yourfile.csv"), ',');
			
			List<String[]> ls= new ArrayList<String[]>();
				for(int i=0;i<dblist.size();i++){
					
					String[] entries = {dblist.get(i).getName(),dblist.get(i).getDecEmailId(),dblist.get(i).getDepartment(),dblist.get(i).getDecPassword(),
							dblist.get(i).getAddress(),dblist.get(i).getDecSsn()};
					System.out.println(dblist.get(i).getDecSsn());
					ls.add(entries);
				}
				writer.writeAll(ls);
			    System.out.print("Data entered into file");
			 writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private DecryptBean DoDecryption(String[] keys1, String[] keys2,
			EncryptedDataBean edb) {
		// TODO Auto-generated method stub
		DecryptBean db = new DecryptBean();
		List<String> emaills = new ArrayList<>();
		emaills.add(edb.getEncEmailId());emaills.add(keys1[0]);emaills.add(keys2[0]);
		
		List<String> passwordls = new ArrayList<>();
		passwordls.add(edb.getEncPassword());passwordls.add(keys1[1]);passwordls.add(keys2[1]);
		
		db.setAddress(edb.getAddress());
		db.setDepartment(edb.getDepartment());
		db.setName(edb.getName());
		db.setDecEmailId(DecryptionCls.actualDecryption(emaills));
		db.setDecPassword(DecryptionCls.actualDecryption(passwordls));
		return db;	
	}

}
