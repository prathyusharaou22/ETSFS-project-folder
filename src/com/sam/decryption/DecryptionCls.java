package com.sam.decryption;

import java.util.List;

import com.sam.finalProject.Algorithm;

public class DecryptionCls {
	
	public static String actualDecryption(List<String> encListWithKeyE){

		int[][] decKey1, decKey2; int M = 0;
		String key1Str, key2Str, encrytedText;
		encrytedText = encListWithKeyE.get(0);key1Str = encListWithKeyE.get(1);key2Str = encListWithKeyE.get(2);
		int matrix_order = (int) Math.sqrt(key1Str.length());
		//System.out.println("matrix_order--"+matrix_order);
		decKey1= new int[matrix_order][matrix_order];
		decKey2 = new int[matrix_order][matrix_order];
		
                //Key1 and Key2 matrix formation
		decKey1 = matrixConstruction(key1Str, matrix_order);
		decKey2 = matrixConstruction(key2Str, matrix_order);
		
		
		int[][] unfoldedMatrix = new int[matrix_order][matrix_order];
		unfoldedMatrix = Algorithm.folding(matrixConstruction(reverseShifting(inverserOfXor(encrytedText, decKey1)),matrix_order));
		
		System.out.println("unfolded matrix");
		for(int i=0;i<matrix_order;i++){
			for(int j=0;j<matrix_order;j++){
				System.out.print(unfoldedMatrix[i][j]+"  ");
			}
			System.out.println();
		}
		char[][] zigZagDecMat = new char[matrix_order][matrix_order];

		for(int i=0;i<matrix_order;i++){
			for(int j=0;j<matrix_order;j++){
					if(unfoldedMatrix[i][j] < 58 && unfoldedMatrix[i][j] >= 48){
						M = 10;
						if( (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+7 < 0){
							zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+7+48+10);
						}
						else{
							zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+7+48);
						}
					}
	                else if(unfoldedMatrix[i][j] >= 32 && unfoldedMatrix[i][j] <= 47 ){
						M = 15;	
	                   if((zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+3 >= 0){
	                          zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+32+3);
						}
						else{
	                         zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+32+15+3);
						}
					}else if(unfoldedMatrix[i][j] >= 58 && unfoldedMatrix[i][j] <= 64){ 
	                           M =7;
	                        int x = zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M)+3;
	                        System.out.println("x--7->"+x);	
	                    if( (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+3  <= 0){
	                         zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+58+6+3);
						}
						else{
							zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+58+2);
						}
					}     
	                else if(unfoldedMatrix[i][j] >= 91 && unfoldedMatrix[i][j] <= 96){
	                           M =6;
	                    if( (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+5 > 0){
	                        zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+92+6-3);
						}
						else{
							zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+92+6+3);
						}
					} 
	                else if(unfoldedMatrix[i][j] >= 123 && unfoldedMatrix[i][j] <= 126){
                        M =4;
                        
		                 if( (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+3 > 0){
		                     zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+124+2);
							}
							else{
								zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+124+2);
							}
					} 
					else if(unfoldedMatrix[i][j] >= 65 && unfoldedMatrix[i][j] <= 90){
						M = 26;
						if( (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+30 < 26){
	
							zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+25+70);
						}
						else if( (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+30 > 25){
	
							zigZagDecMat[i][j] =(char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+69);
						}
						
					}
					else if(unfoldedMatrix[i][j] >= 97 && unfoldedMatrix[i][j] <= 122){
						M = 26;
						if((zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M)) == -25){
							
							zigZagDecMat[i][j] ='z';
						}
						else{
							zigZagDecMat[i][j] = (char) ((char) (zigzagMatrixGeneration(decKey1[i][j],decKey2[i][j],unfoldedMatrix[i][j],M))+25+96); 
						}	
					}
				    
			}
		}
		InverseTransposition inv = new InverseTransposition();
		char[][] a = inv.inverrse(zigZagDecMat);
		StringBuilder str = new StringBuilder();
		 System.out.println("zigzag Matrix for decryp:");
			for(int i=0;i<matrix_order;i++){
				for(int j=0;j<matrix_order;j++){
					 System.out.print( zigZagDecMat[i][j]+"  ");
				}
				System.out.println();
			}
		System.out.println("inverse transf :");
				for(int i=0;i<matrix_order;i++){
					for(int j=0;j<matrix_order;j++){
						 System.out.print( a[i][j]+"  ");
						 str.append(a[i][j]);
					}
					System.out.println();
		}
				String decryptedString ="";
				if(str.toString().contains("*")){
					decryptedString = str.substring(0, str.indexOf("*")).toString();
					 System.out.print("Decrypted string--"+str.substring(0, str.indexOf("*")).toString());
						
				}
				else{
					decryptedString = str.toString();
					
				}
				return decryptedString;	
	}
	
	private static int zigzagMatrixGeneration(int k1, int k2, int unfoldedMatrix, int M) {
		int d=0;
		d = ((unfoldedMatrix - k2) % M - k1) % M;
		return d;   	
	}

	private static String inverserOfXor(String encryptedText, int[][] decKey1) {
		int count=0;StringBuilder shiftedString = new StringBuilder();
                int k=0;
		String[] split = encryptedText.split(",");
            for (int i=0;i<decKey1.length;i++) {
                for (int j = 0; j<decKey1.length; j++) {
                    shiftedString.append((char) ((int) encryptedText.charAt(k) ^ (decKey1[i][j] - 48)));
                    k++;
                }
            }
		System.out.println("after inverse xor=="+shiftedString.toString());
		return shiftedString.toString();
	}
	
	
	public static int[][] matrixConstruction(String key, int m)     
	{
	    	int[][] key1and2 = new int[m][m];
	    	int count = 0;
	    	for (int i = 0; i < m; i++) {
	    		for(int j=0;j<m;j++){
	   	    	  key1and2[i][j] = key.charAt(count);
	   	    	  count++;
	    		}
	    	}
	    	return key1and2;
	}
	
	public static String reverseShifting(String str){
		String spl_chr= " !#$%&()*+',-./:;<=>?@[,_`{|}~";
                String spl_char_7 = "*-./:@_";
		StringBuilder sb = new StringBuilder();
		int M =0;
		for(int i=0;i<str.length();i++){
			char getasciivalue = (char) Algorithm.getAscii(str.charAt(i));
			int index1 = 0;int actualCharAsciiValue;
			 if(getasciivalue >= 65 && getasciivalue <= 90){
				index1 = getasciivalue - 65 - i; 
				 while(index1 < 0){
					index1 =  index1 + 26;
				 }
				 sb.append((char) (index1+65));
			 }
			 else if(getasciivalue >= 97 && getasciivalue <= 122){
				 M = 26;
				// index1 = getasciivalue - 97;
				 actualCharAsciiValue = getasciivalue -97- i;
				 while(actualCharAsciiValue < 0){
					 actualCharAsciiValue =  actualCharAsciiValue + M;
				 }
				 sb.append((char) (char) (actualCharAsciiValue+97));
			 }
                          else if(getasciivalue < 58 && getasciivalue >= 48){
				 M = 10;
				 actualCharAsciiValue = getasciivalue - 48 - i;
				 while(actualCharAsciiValue < 0){
					 actualCharAsciiValue = actualCharAsciiValue + M;
				 }
				 sb.append((char) (char) (actualCharAsciiValue + 48));	 
			 }
			 else if(getasciivalue >= 32 && getasciivalue <= 47){
				 M = 15;
				// actualCharAsciiValue = spl_char_7.indexOf((char) getasciivalue) - i;
                                 actualCharAsciiValue = getasciivalue - 32 - i;
				 while(actualCharAsciiValue < 0){
					 actualCharAsciiValue = actualCharAsciiValue + M;
				 }
                                 sb.append((char) (char) (actualCharAsciiValue+32));
				 //sb.append(spl_char_7.charAt(actualCharAsciiValue));
			 }
                         
              else if(getasciivalue >= 58 && getasciivalue <= 64){
				 M = 7;
                                 actualCharAsciiValue = getasciivalue - 58 - i;
				 
				 while(actualCharAsciiValue < 0){
					 actualCharAsciiValue = actualCharAsciiValue + M;
				 }
                                 sb.append((char) (char) (actualCharAsciiValue+58));
			 }
               else if(getasciivalue >= 91 && getasciivalue <= 96 ){
				 M = 6;
                                 actualCharAsciiValue = getasciivalue - 91 - i;
				 
				 while(actualCharAsciiValue < 0){
					 actualCharAsciiValue = actualCharAsciiValue + M;
				 }
                                 System.out.println("actualCharAsciiValue--"+actualCharAsciiValue);
                                 sb.append((char) (char) (actualCharAsciiValue + 91));
			 }
               else if(getasciivalue >= 123 && getasciivalue <= 126 ){
  				 M = 4;
                                   actualCharAsciiValue = getasciivalue - 123 - i;
  				 
  				 while(actualCharAsciiValue < 0){
  					 actualCharAsciiValue = actualCharAsciiValue + M;
  				 }
                  sb.append((char) (char) (actualCharAsciiValue + 123));
  			 }
			
		}
		 System.out.println("Reverse Shifting string--"+sb.toString());
		 matrixConstruction(str,(int) Math.sqrt(str.length()));
		 return sb.toString();
	}
	 
}
