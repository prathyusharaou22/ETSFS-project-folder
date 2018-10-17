package  com.sam.finalProject;

import java.util.ArrayList;
import java.util.List;
import com.sam.decryption.DecryptionCls;


public class StepByStepEncryption {
	static int M;
	String encAttribute;

	public List<String> encryMethod(String encAttribute) {
		
		System.out.println(encAttribute);
		
		List<String> encListWithKey = new ArrayList<String>();
			String data = null;
			String shifted_string, finalEncyafterXorOperation;
			
		data = (String) encAttribute;
		int matrix_order = (int) Math.ceil(Math.sqrt(data.length()));
		char[][] pMatrix,zigzagMat;
		int[][] key1, key2,encryptedMatrix,folded_matrix; 
		
		key1= new int[matrix_order][matrix_order];
		key2 = new int[matrix_order][matrix_order];
		
		encryptedMatrix = new int[matrix_order][matrix_order];
		
		folded_matrix = new int[matrix_order][matrix_order];
		
		pMatrix = new char[matrix_order][matrix_order];
		
		zigzagMat = new char[matrix_order][matrix_order];
		
		key1 = Algorithm.RandomClassTest1(matrix_order);
		
		key2 = Algorithm.RandomClassTest1(matrix_order);
		
		/* System.out.println("key1 :- ");
			for(int i=0;i<matrix_order;i++){
				for(int j=0;j<matrix_order;j++){
					 System.out.print(key1[i][j]+"  ");
				}
				System.out.println();
			}
			
			System.out.println("key2 :- ");
			for(int i=0;i<matrix_order;i++){
				for(int j=0;j<matrix_order;j++){
					 System.out.print(key2[i][j]+"  ");
				}
				System.out.println();
			}*/
		
		StringBuffer key1Str, key2Str;
		key1Str = new StringBuffer();
		key2Str = new StringBuffer();
		
		for(int i=0;i<matrix_order;i++){
			for(int j=0;j<matrix_order;j++){
				key1Str.append(key1[i][j]);
				key2Str.append(key2[i][j]);
			}
		}
		
		pMatrix = Algorithm.pMatrix(matrix_order,data);
		
		zigzagMat =Algorithm.printZMatrix(pMatrix);	

		 System.out.println("zigzag Matrix for encryp :");
		for(int i=0;i<matrix_order;i++){
			for(int j=0;j<matrix_order;j++){
				 System.out.print(zigzagMat[i][j]+"  ");
			}
			System.out.println();
		}
		
		
		for(int i=0;i<matrix_order;i++){
			for(int j=0;j<matrix_order;j++){
				if(Algorithm.getAscii(zigzagMat[i][j]) < 58 && Algorithm.getAscii(zigzagMat[i][j]) >= 48){
					M = 10;
				}
				else if(Algorithm.getAscii(zigzagMat[i][j]) >= 65 && Algorithm.getAscii(zigzagMat[i][j]) <= 90 || 
						Algorithm.getAscii(zigzagMat[i][j]) >= 97 && Algorithm.getAscii(zigzagMat[i][j]) <= 122){
					M = 26;
				}
				else if(Algorithm.getAscii(zigzagMat[i][j]) >= 32 && Algorithm.getAscii(zigzagMat[i][j]) <= 47){
					M = 15;	
				}
                 else if(Algorithm.getAscii(zigzagMat[i][j]) >= 58 && Algorithm.getAscii(zigzagMat[i][j]) <= 64){
                   M = 7;
                }
                else if(Algorithm.getAscii(zigzagMat[i][j]) >= 91 && Algorithm.getAscii(zigzagMat[i][j]) <= 96){
                 M = 6;
                }
                else if(Algorithm.getAscii(zigzagMat[i][j]) >= 123 && Algorithm.getAscii(zigzagMat[i][j]) <= 126){
                    M = 4;
                  }
                
               encryptedMatrix[i][j] = Algorithm.encryption(key1[i][j],key2[i][j],zigzagMat[i][j],M);
			}
		}
		
		 System.out.println("Encrypted Matrix for encryp :");
			for(int i=0;i<matrix_order;i++){
				for(int j=0;j<matrix_order;j++){
					 System.out.print((char) (encryptedMatrix[i][j])+"  ");
				}
				System.out.println();
			}
		
		
		//----------  Folded call ------------
           folded_matrix = Algorithm.folding(encryptedMatrix); 
	   System.out.println("folded Matrix :");
	    
            for (int[] folded_matrix1 : folded_matrix) {
                for (int j = 0; j<folded_matrix.length; j++) {
                    System.out.print((char) folded_matrix1[j] + " ");
                }
            }

            System.out.println();
	    //-------------- Shifting call ----------------
	    shifted_string = Algorithm.shifting(folded_matrix);
        System.out.println("Shifted string--"+shifted_string);
		    
        finalEncyafterXorOperation =  Algorithm.xorOperation(shifted_string,key1);
		     encListWithKey.add(0,finalEncyafterXorOperation);
                     encListWithKey.add(1,key1Str.toString());
                     encListWithKey.add(2,key2Str.toString());
	     return encListWithKey;
	}
		public static void main(String[] args) {
			StepByStepEncryption sbe = new StepByStepEncryption();
			List<String> encListWithKey;
                        encListWithKey = new ArrayList<>();
			String encAttribute = "{|}~!!@#$%^&(";
			encListWithKey = sbe.encryMethod(encAttribute);
			DecryptionCls dc = new DecryptionCls();
			dc.actualDecryption(encListWithKey);	
		}
}
