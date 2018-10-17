package com.sam.finalProject;

import java.util.Random;





public class Algorithm {
	
	static int M;
	
	// Random number generator method (key1 and key2 matrix) --------------------------------------
	
	public static int[][] RandomClassTest1(int m)     
	{
	    	int[][] key1 = new int[m][m];
	    	Random rand = new Random();
	    	for (int i = 0; i < m; i++) {
	    		for(int j=0;j<m;j++){
	    		int n = rand.nextInt(10);
	   	    	  key1[i][j] = n;
	    		}
	    	}
	    	return key1;
	}
	
	
	//Method to find the Matrix size and Appending * to the data --------------------------------------
	
	public static char[][] pMatrix(int matrix_order, String data) {
			String expectedData; char[][] pMatrix;
			pMatrix = new char[matrix_order][matrix_order];
			if(data.length() < matrix_order*matrix_order){
				int remainingLength = matrix_order*matrix_order - data.length();
				StringBuilder sb = new StringBuilder(data);
				while(remainingLength !=0){
					sb.append('*');
					remainingLength--;
				}
				expectedData = sb.toString();
				pMatrix = toMatrix(expectedData,matrix_order);
				
			}
			else if(data.length() == matrix_order*matrix_order){
				expectedData = data.substring(0, data.lastIndexOf(data.substring(matrix_order*matrix_order, data.length())));
				pMatrix = toMatrix(expectedData,matrix_order);
			}
			return pMatrix;
			
	}
	
	// Method to check if the char is a number,special_char,Alphabet --------------------------------------
	
	//getting ASCII code method --------------------------------------
	public static int getAscii(char c){   
		int ascii = (int) c; 
		return ascii;
	}
	
	
	// String to matrix conversion method--------------------------------------
	
	private static char[][] toMatrix(String expectedData, int matrix_order) {   //changing into a matrix method
		// TODO Auto-generated method stub
		char[][] array = new char [matrix_order][matrix_order];int k=0;
		for(int i=0; i<matrix_order;i++){
			for(int j=0;j<matrix_order;j++){
				array[i][j] = expectedData.charAt(k);
				k++;
			}
		}
		return array;	
	}
	
	//Encryption Method--------------------------------------
	
	public static int encryption(int k1,int k2, char zigzagchar, int M){
            
            
		int e = 0,p=0;
            switch (M) {
                case 15://For special chars  !#$%&()*+',-./
                    p = getAscii(zigzagchar)-31;
                    e = (((((k1+p) % M) +k2 ) % M))+32;
                    break;
                case 6: //For special chars [\]^_`
                    p = getAscii(zigzagchar)-90;
                    e = (((((k1+p) % M) +k2 ) % M)+91);
                    break;
                 case 4: //For special chars [\]^_`
                        p = getAscii(zigzagchar)- 122;
                        e = (((((k1+p) % M) +k2 ) % M)+123);
                        break;
                    
                case 7: //For special chars :;<=>?@
                    p = getAscii(zigzagchar) - 57;
                    e = ((((k1+p) % M) +k2 ) % M)+58;
                    break;
                case 26://For alphabets
                    int p1=getAscii(zigzagchar);
                    if(p1 >= 65 && p1 <= 90){
                        p1 = p1-64;
                        e = ((((k1+p1) % M) +k2 ) % M)+65;
                        
                    }
                    else if(p1 >= 97 && p1 <= 122){
                        p1 = p1-96;
                        e = ((((k1+p1) % M) +k2 ) % M)+97;
                    }
                    break;
                case 10://For numbers
                    p =(int) zigzagchar-47;
                    e = ((((k1+p) % M) +k2 ) % M)+48;
                    break;
                default:
                    break;
            }
		return e;   
	}
	
	//Printing zigzag matrix method --------------------------------------

	public static char[][] printZMatrix(char[][] array) { 	

		System.out.println("before transposition");
            for (char[] array1 : array) {
                for (int j = 0; j<array.length; j++) {
                    System.out.print(array1[j] + " ");
                }
                System.out.println();
            }
		
		        if(array == null || array.length == 0 || array[0].length == 0) ;
		        int m = array.length,counter = 0 ;
		        char[] res = new char[m*m];
		        char[][] zigoutput = new char[m][m];
		        int j = 0;
		        for(int i = 0; i < 2*m - 1; i++){
		            if(i % 2 == 1){
		                for(int y = Math.min(i, m - 1); y >= Math.max(0, i - m + 1); y--){
		                    res[j++] = array[i-y][y];
		                }
		            } else{
		                for(int x = Math.min(i, m - 1); x >= Math.max(0, i - m + 1); x--){
		                    res[j++] = array[x][i-x];
		                }
		            }
		        }
		        System.out.println("zigzag matrix is::: ");
		        //System.out.println("----------------------------------");
		       for(int i=0;i<m;i++){
		        	for(j=0;j<m;j++){
		        		zigoutput[i][j] = res[counter];
		        		System.out.print(zigoutput[i][j]+" ");
		        		counter++;
		        	}
		        	System.out.println();
		        }
		       // System.out.println("----------------------------------");
		  return zigoutput;  
	}
	
	
	// Folding method	--------------------------------------
	
	public static int[][] folding(int[][] encryptedMatrix){
		int[][] temp;
		int temp1 =0,k =0;
            //System.out.println("folding matrix");
            for (int[] encryptedMatrix1 : encryptedMatrix) {
                for (int j = 0; j<encryptedMatrix.length; j++) {
                    System.out.print(encryptedMatrix1[j] + "  ");
                }
                System.out.println();
            }
		temp = new int[encryptedMatrix.length][encryptedMatrix.length];
		temp1 = (encryptedMatrix.length) /2;
		while(temp1 != 0){
			for(int i=0+k;i<encryptedMatrix.length-k;i++){
				temp[0+k][i]= encryptedMatrix[encryptedMatrix.length-1-k][i];
				temp[encryptedMatrix.length-1-k][i] = encryptedMatrix[0+k][i];
			}
			temp1--;
			k = k+1;
		}
		for(int i=1;i<encryptedMatrix.length-1;i++){
			for(int j=0;j<encryptedMatrix.length;j++){
				if(temp[i][j] == 0){
				temp[i][j] = encryptedMatrix[i][j];
				}
				else{
					temp[i][j] = temp[i][j];
				}
			}
		}
		encryptedMatrix = temp;k=0;
		temp1 = (encryptedMatrix.length)/2;
		
		temp = new int[encryptedMatrix.length][encryptedMatrix.length];
		while(temp1 !=0){
			for(int i=0+k;i<encryptedMatrix.length-k;i++){
				temp[i][0+k]= encryptedMatrix[i][encryptedMatrix.length-1-k];
				temp[i][encryptedMatrix.length-1-k] = encryptedMatrix[i][0+k];
			}
			temp1--;k = k+1;
		}
		for(int i=1;i<encryptedMatrix.length-1;i++){
			for(int j=0;j<encryptedMatrix.length;j++){
				if(temp[j][i] == 0){
					temp[j][i] = encryptedMatrix[j][i];
				}
				else{
					temp[j][i] = temp[j][i];
				}
			}
		}
		encryptedMatrix = temp;
		return encryptedMatrix;
	}
	
	
	
	//Shifting --------------------------------------
	
	public static String shifting(int[][] folded_matrix){
		int counter =0;
		StringBuilder sb = new StringBuilder();
	
		StringBuilder sb2 = new StringBuilder();
		
		
            for (int[] folded_matrix1 : folded_matrix) {
                for (int j = 0; j<folded_matrix.length; j++) {
                    if (getAscii((char) folded_matrix1[j]) < 58 && getAscii((char) folded_matrix1[j]) >= 48) {
                        M = 10;
                        int actualIndex = 0;
                        char x;
                        actualIndex = getAscii((char) folded_matrix1[j]) - 48 + counter;
                        while(actualIndex >= 10){
                            actualIndex = actualIndex - M;
                        }
                        x = (char) (48 + actualIndex);
                        sb.append(x);
                        sb2.append(getAscii(x)).append(" ");
                    }
                    else if (getAscii((char) folded_matrix1[j]) >= 32 && getAscii((char) folded_matrix1[j]) <= 47  ) {
                        M = 15;
                        int actualIndex = 0;
                        char x;
                        actualIndex = getAscii((char) folded_matrix1[j]) - 32 + counter;
                        while(actualIndex >= 15){
                            actualIndex = actualIndex - M;
                        }
                        x = (char) (32 + actualIndex);
                        sb.append(x);
                        sb2.append(getAscii(x)).append(" ");
                    } else if (getAscii((char) folded_matrix1[j]) >= 58 && getAscii((char) folded_matrix1[j]) <= 64) {
                        M = 7;
                        int actualIndex = 0;
                        char x;
                        actualIndex = getAscii((char) folded_matrix1[j]) - 58 + counter;
                        System.out.println("actual index--"+actualIndex);
                        while(actualIndex >= 7){
                            actualIndex = actualIndex - M;
                        }
                      //  System.out.println("actualindex after iteration--"+actualIndex);
                        x = (char) (58 + actualIndex);
                       // System.out.println("x--val"+x);
                        sb.append(x);
                        sb2.append(getAscii(x)).append(" ");
                    } else if (getAscii((char) folded_matrix1[j]) >= 91 && getAscii((char) folded_matrix1[j]) <= 96) {
                        M = 6;
                        int actualIndex = 0;
                        char x;
                        actualIndex = getAscii((char) folded_matrix1[j]) - 91 + counter;
                        //System.out.println("actual index--"+actualIndex);
                        while(actualIndex >= 6){
                            actualIndex = actualIndex - M;
                        }
                      //  System.out.println("actualindex after iteration--"+actualIndex);
                        x = (char) (91 + actualIndex);
                        sb.append(x);
                        sb2.append(getAscii(x)).append(" ");
                    }
                    else if (getAscii((char) folded_matrix1[j]) >= 123 && getAscii((char) folded_matrix1[j]) <= 126) {
                        M = 4;
                        int actualIndex = 0;
                        char x;
                        actualIndex = getAscii((char) folded_matrix1[j]) - 122 + counter;
                        while(actualIndex >= 4){
                            actualIndex = actualIndex - M;
                        }
                        x = (char) (123 + actualIndex);
                        sb.append(x);
                        sb2.append(getAscii(x)).append(" ");
                    }
                    else if (getAscii((char) folded_matrix1[j]) >= 65 && getAscii((char) folded_matrix1[j]) <= 90 ||
                            getAscii((char) folded_matrix1[j]) >= 97 && getAscii((char) folded_matrix1[j]) <= 122) {
                        //System.out.println("counter--"+counter);
                        int actualIndex = 0;
                        M = 26;
                        char x;
                        if (getAscii((char) folded_matrix1[j]) >= 65 && getAscii((char) folded_matrix1[j]) <= 90) {
                            actualIndex = getAscii((char) folded_matrix1[j]) - 65 + counter;
                            while(actualIndex > 25){
                                actualIndex = actualIndex - 26;
                            }
                            x = (char) (65 + actualIndex);
                        } else {
                            actualIndex = getAscii((char) folded_matrix1[j]) - 97 + counter;
                            while(actualIndex > 25){
                                actualIndex = actualIndex - 26;
                            }
                            x = (char) (97 + actualIndex);
                        }
                        sb2.append(getAscii(x)).append(" ");
                        sb.append(x);
                    } 
                    counter++;
                }
            }
               // System.out.println("shifted string in ascii format--"+sb2.toString());
                        
		return sb.toString();
	}
	
	public static String xorOperation(String shifted_string, int[][] key1){	
		int k = 0;StringBuilder sb = new StringBuilder();       
                for (int[] key11 : key1) {
                    for (int j = 0; j<key1.length; j++) {
                        sb.append((char) (getAscii(shifted_string.charAt(k)) ^ key11[j]));
                       // sb.append(",");
                       
                        k++;
                    }
                }
                System.out.println("after xor---"+sb.toString());
		return sb.toString();
		
	}
	
	
	
	/*	@SuppressWarnings("unchecked")
	public static void main (String args[]) throws IOException {
		//System.out.println( "Enter the string");
		 
	    long start_time= System.currentTimeMillis();
		String data = null;
		Read_line rclass1= new Read_line();
		@SuppressWarnings("rawtypes")
		List[] attributes = new List[6];
		List[] attributes1 = new List[6];
		
		
		attributes = rclass1.readFile1("");
		
		
		for(int i=0;i<attributes.length;i++){
			attributes1[i]=new ArrayList<String>();}
		
		
		List<String> name_array,email_array,department_array,password_array,address_array,ssn_array;
		name_array = new ArrayList<String>();
		email_array = new ArrayList<String>();
		department_array = new ArrayList<String>();
		password_array =  new ArrayList<String>();
		address_array = new ArrayList<String>();
		ssn_array = new ArrayList<String>();
		
		String shifted_string;
		for(int i1=1;i1<attributes.length;i1++){
			
			for(int j1=0;j1<attributes[i1].size();j1++){	
				
				
				data = (String) attributes[i1].get(j1);
				int matrix_order = (int) Math.ceil(Math.sqrt(data.length()));
				char[][] pMatrix,zigzagMat;
				int[][] key1, key2,encryptedMatrix,folded_matrix; 
				
				key1= new int[matrix_order][matrix_order];
				key2 = new int[matrix_order][matrix_order];
				
				encryptedMatrix = new int[matrix_order][matrix_order];
				
				folded_matrix = new int[matrix_order][matrix_order];
				
				pMatrix = new char[matrix_order][matrix_order];
				
				zigzagMat = new char[matrix_order][matrix_order];
				
				
				
				//System.out.println("Printing key1");
				key1 = RandomClassTest1(matrix_order);
				
				
				
				
				//System.out.println("Printing key2");
				key2 = RandomClassTest1(matrix_order);
				
				
				pMatrix = pMatrix(matrix_order,data);
				zigzagMat = printZMatrix(pMatrix);	
				
				
				for(int i=0;i<matrix_order;i++){
					for(int j=0;j<matrix_order;j++){
						if(getAscii(zigzagMat[i][j]) < 58 && getAscii(zigzagMat[i][j]) >= 48){
							M = 10;
						}
						else if(getAscii(zigzagMat[i][j]) >= 65 && getAscii(zigzagMat[i][j]) <= 90 || 
								getAscii(zigzagMat[i][j]) >= 97 && getAscii(zigzagMat[i][j]) <= 122){
							M = 26;
							
						}
						else if(getAscii(zigzagMat[i][j]) >= 32 && getAscii(zigzagMat[i][j]) <= 47 || 
								getAscii(zigzagMat[i][j]) >= 58 && getAscii(zigzagMat[i][j]) <= 64 ||
								getAscii(zigzagMat[i][j]) >= 92 && getAscii(zigzagMat[i][j]) <= 96 || 
								getAscii(zigzagMat[i][j]) >= 123 && getAscii(zigzagMat[i][j]) <= 126){
							M = 30;	
						}	
						 encryptedMatrix[i][j] =encryption(key1[i][j],key2[i][j],zigzagMat[i][j],M);
					}
				}
				
				//----------  Folded call ------------
				
			    folded_matrix = folding(encryptedMatrix); 
			    
			    //-------------- Shifting call ----------------
			    
			    shifted_string = shifting(folded_matrix);
			    attributes1[i1].add(shifted_string);
			}
		 }
		for(int i=0;i<attributes1[1].size();i++){
			//name_array.add((String) attributes1[0].get(i));
			email_array.add((String) attributes1[1].get(i));
			//department_array.add((String) attributes1[2].get(i));
			password_array.add((String) attributes1[3].get(i));
			//address_array.add((String) attributes1[4].get(i));
			System.out.println(attributes[0].get(i)+" "+email_array.get(i)+" "+attributes[2].get(i)+" "
						+password_array.get(i)+" "+attributes[4].get(i));
		}
		long end_time = System.currentTimeMillis();
		System.out.println(end_time-start_time);
	}*/

}
	 
	



