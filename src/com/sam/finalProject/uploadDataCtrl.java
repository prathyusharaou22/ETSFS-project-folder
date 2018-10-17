package com.sam.finalProject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.opencsv.CSVReader;
import com.sam.dao.DataAccessObj;
import com.sam.helper.bean.EncryptedDataBean;
import com.sam.helper.bean.KeysBean;
import com.sam.helper.bean.ReadFileBean;

/**
 * Servlet implementation class uploadDataCtrl
 */
@WebServlet(name = "uploadDataCtrl", urlPatterns = {"/uploadDataCtrl"})
@MultipartConfig

public class uploadDataCtrl extends HttpServlet {
	
	static int M;
     
    	private static final long serialVersionUID = 1L;
		protected void doPost(HttpServletRequest request,
    	        HttpServletResponse response)
    	        throws ServletException, IOException {
    		
			ArrayList<Long> timesForEncryption = null;
    		HttpSession session = request.getSession();
    		RequestDispatcher rDispatcher;
    	    response.setContentType("text/html;charset=UTF-8");
    	    
    	    String name,email,department,password,address, ssn;
    	    
    	    name = request.getParameter("name");
    	    email = request.getParameter("email");
    	    department = request.getParameter("department");
    	    password = request.getParameter("password");
    	    address = request.getParameter("address");
    	    ssn = request.getParameter("social");
    	    
    	    
    	    ReadFileBean rfb = new ReadFileBean();
			
			List<ReadFileBean> ls_for_singleDataSet = new ArrayList<ReadFileBean>() ;

    	    timesForEncryption =  new ArrayList<Long>();
			// Setting the data to the bean
				rfb.setName(name);
				rfb.setAddress(address);
				rfb.setDepartment(department);
				rfb.setEmailId(email);
				rfb.setPassword(password);
				rfb.setSsn(ssn);
				//System.out.println("name--->"+name);
    	    if(!name.equals(null) &&  name.length() !=0){
    	    	//System.out.println("came");
    	    	try {
    				ls_for_singleDataSet.add(rfb);
    				timesForEncryption = actualEncryption(ls_for_singleDataSet);
    			} catch (Exception e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}	
    	    }
    	    else{
        	    final Part filePart = request.getPart("file");
        	    InputStream filecontent = null;
        	    try {

        	        filecontent = filePart.getInputStream();

        	        List<ReadFileBean> ls_for_file = arrayOfListDataFromCSV(filecontent);
        	        timesForEncryption =  actualEncryption(ls_for_file);	
        	    } catch (Exception e) {
        	    	rDispatcher = request.getRequestDispatcher("error.jsp");
        	       	rDispatcher.forward(request, response);
        	    	e.printStackTrace();
        	    }
    	    }

    	    session.setAttribute("aesTime", timesForEncryption.get(0));
       	    session.setAttribute("desTime", timesForEncryption.get(1));
       	    session.setAttribute("encTime", timesForEncryption.get(2));
       	    
       	    rDispatcher = request.getRequestDispatcher("EncrypetedTimeResults.jsp");
       	    
       	    rDispatcher.forward(request, response);


    	}
    	
    	// Method to get the array of lists from file Path 
		
		private  List<ReadFileBean> arrayOfListDataFromCSV(InputStream filecontent) throws Exception {
			
			List<ReadFileBean> ls= new ArrayList<ReadFileBean>();
			 try {
			        ReadFromFile readcsv = new ReadFromFile();
					ls =readcsv.readFile1(filecontent);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 return ls;
		}
		
		
		
		
		private ArrayList<Long> actualEncryption(List<ReadFileBean> unencryptedData) throws Exception{
    		
    		
    		ArrayList<Long> encTimes= new ArrayList<Long>();
		
			List<EncryptedDataBean> AESEnc = new ArrayList<EncryptedDataBean>();
			List<EncryptedDataBean> DESEnc = new ArrayList<EncryptedDataBean>();
			
			//Encryption starts here  (data should be passed)
			List<String> emailEncKeyList,passwordEncKeyList,ssnEncKeyList;
			EncryptedDataBean encData = null;
			KeysBean kb = null;
			List<EncryptedDataBean> encryptedActualData = new ArrayList<EncryptedDataBean>();

			List<KeysBean> keysList = new ArrayList<KeysBean>();
			
			long encStartTime1 =0;
			
			long encend_time = 0;
			 encStartTime1 = System.currentTimeMillis();
			for(int i1=0;i1<unencryptedData.size();i1++){
				
	    		encData = new EncryptedDataBean();
	    		kb= new KeysBean();
	    		
				StepByStepEncryption sbse = new StepByStepEncryption();
				emailEncKeyList = sbse.encryMethod(unencryptedData.get(i1).getEmailId());
				passwordEncKeyList = sbse.encryMethod(unencryptedData.get(i1).getPassword());
				ssnEncKeyList = sbse.encryMethod(unencryptedData.get(i1).getSsn());
				
				kb.setKey1String(emailEncKeyList.get(1)+","+passwordEncKeyList.get(1)+","+ssnEncKeyList.get(1));
				kb.setKey2String(emailEncKeyList.get(2)+","+passwordEncKeyList.get(2)+","+ssnEncKeyList.get(2));
				
				encData.setName(unencryptedData.get(i1).getName());
				encData.setEncEmailId(emailEncKeyList.get(0));
				encData.setDepartment(unencryptedData.get(i1).getDepartment());
				encData.setEncPassword(passwordEncKeyList.get(0));
				encData.setAddress(unencryptedData.get(i1).getAddress());
				encData.setEncSsn(ssnEncKeyList.get(0));
				
				keysList.add(i1,kb);
				
				encryptedActualData.add(i1,encData);
					
			}
			encend_time = System.currentTimeMillis();
			
			// Enc time calculation 
			long ourEncTime =  (encend_time - encStartTime1);
			System.out.println("enc encryption time--"+(encend_time - encStartTime1));
			
			// AES enc time calculation
			
			long AESStartTime =  System.currentTimeMillis();
			AESEnc = AESEncryptionDecryption.AES(unencryptedData);
			long DESStartTime =  System.currentTimeMillis();
			
			System.out.println("AES encryption time--"+(DESStartTime - AESStartTime));
			
			// DES enc time calculation
			
			long AESTime = (DESStartTime - AESStartTime);
			DESEnc = DESEncryptionDecryption.DES(unencryptedData);

			long DESEndTime =  System.currentTimeMillis();
			long DECtime = (DESEndTime - DESStartTime);

			System.out.println("DES encryption time--"+(DESEndTime - DESStartTime));
			
			encTimes.add(0,AESTime);encTimes.add(1,DECtime);encTimes.add(2,ourEncTime/10);
			
			
			// Inserting into the database

			DataAccessObj.insertIntoAESTable(AESEnc);
			DataAccessObj.insertIntoDESTable(DESEnc);
			DataAccessObj.insertIntoEncTable(encryptedActualData,keysList);
			
			return encTimes;
			
			/*for(int i=0;i<attributes1[1].size();i++){
				
				email_array.add((String) attributes1[1].get(i));
				password_array.add((String) attributes1[3].get(i));
				ssn_array.add((String) attributes1[5].get(i));
				//System.out.println(attributes[0].get(i)+" "+email_array.get(i)+" "+attributes[2].get(i)+" "
						//	+password_array.get(i)+" "+attributes[4].get(i)+" "+ssn_array.get(i));
			}*/
		}
			
	}

