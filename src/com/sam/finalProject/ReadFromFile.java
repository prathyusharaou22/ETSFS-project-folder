package com.sam.finalProject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.sam.helper.bean.ReadFileBean;

public class ReadFromFile {
	
	@SuppressWarnings("resource")
	List<ReadFileBean> readFile1(InputStream filecontent) throws IOException {
		
		CSVReader reader = new CSVReader(new InputStreamReader(filecontent));
	     String [] s = null;
	     ReadFileBean rfb;

			List<ReadFileBean> ls= new ArrayList<ReadFileBean>();	
			int i =0;
			 while ((s = reader.readNext()) != null) {
	    	 i++;
	    	 if(i>1){
	    	 rfb = new ReadFileBean();
				rfb.setName(s[0]);
				rfb.setEmailId(s[1]);
				rfb.setDepartment(s[2]);
				rfb.setAddress(s[4]);
				rfb.setPassword(s[3]);
				rfb.setSsn(s[5]);	
				ls.add(i-2,rfb);
	    	 }
	        
	     }
			for(int i1=0;i1<ls.size();i1++){
				System.out.println(ls.get(i1).getAddress()+"--"+ls.get(i1).getDepartment());
			}
		return ls;
	}
	/*public static void main (String args[]) throws IOException {
			ReadFromFile rf = new ReadFromFile();
			rf.readFile1("/Users/rajakaruparthi/Desktop/DataSet/dataSet_test.csv");
		}*/
	
}
