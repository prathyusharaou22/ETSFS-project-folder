package  com.sam.decryption;

public class InverseTransposition {
	
	public char[][] inverrse(char[][] zigZagDecMat){
		
		int size = zigZagDecMat.length;
		char[][] a = new char[size][size];
            switch (size) {
                case 2:
                    a =  zigZagDecMat;
                    break;
                case 3:
                    a[0][0] = zigZagDecMat[0][0];
                    a[0][1] = zigZagDecMat[0][1];
                    a[0][2] = zigZagDecMat[1][2];
                    a[1][0] = zigZagDecMat[0][2];
                    a[1][1] = zigZagDecMat[1][1];
                    a[1][2] = zigZagDecMat[2][0];
                    a[2][0] = zigZagDecMat[1][0];
                    a[2][1] = zigZagDecMat[2][1];
                    a[2][2] = zigZagDecMat[2][2];
                    break;
                case 4:
                    System.out.println("came in");
                    a[0][0]=zigZagDecMat[0][0];
                    a[0][1]=zigZagDecMat[0][1];
                    a[0][2]=zigZagDecMat[1][1];
                    a[0][3]=zigZagDecMat[1][2];
                    a[1][0]=zigZagDecMat[0][2];
                    a[1][1]=zigZagDecMat[1][0];
                    a[1][2]=zigZagDecMat[1][3];
                    a[1][3]=zigZagDecMat[3][0];
                    a[2][0]=zigZagDecMat[0][3];
                    a[2][1]=zigZagDecMat[2][0];
                    a[2][2]=zigZagDecMat[2][3];
                    a[2][3]=zigZagDecMat[3][1];
                    a[3][0]=zigZagDecMat[2][1];
                    a[3][1]=zigZagDecMat[2][2];
                    a[3][2]=zigZagDecMat[3][2];
                    a[3][3]=zigZagDecMat[3][3];
                    break;
                case 5:
                    a[0][0]=zigZagDecMat[0][0];
                    a[0][1]=zigZagDecMat[0][1];
                    a[0][2]=zigZagDecMat[1][0];
                    a[0][3]=zigZagDecMat[1][1];
                    a[0][4]=zigZagDecMat[2][4];
                    a[1][0]=zigZagDecMat[0][2];
                    a[1][1]=zigZagDecMat[0][4];
                    a[1][2]=zigZagDecMat[1][2];
                    a[1][3]=zigZagDecMat[2][3];
                    a[1][4]=zigZagDecMat[3][0];
                    a[2][0]=zigZagDecMat[0][3];
                    a[2][1]=zigZagDecMat[1][3];
                    a[2][2]=zigZagDecMat[2][2];
                    a[2][3]=zigZagDecMat[3][1];
                    a[2][4]=zigZagDecMat[4][1];
                    a[3][0]=zigZagDecMat[1][4];
                    a[3][1]=zigZagDecMat[2][1];
                    a[3][2]=zigZagDecMat[3][2];
                    a[3][3]=zigZagDecMat[4][0];
                    a[3][4]=zigZagDecMat[4][2];
                    a[4][0]=zigZagDecMat[2][0];
                    a[4][1]=zigZagDecMat[3][3];
                    a[4][2]=zigZagDecMat[3][4];
                    a[4][3]=zigZagDecMat[4][3];
                    a[4][4]=zigZagDecMat[4][4];
                    break;
                case 6:
                    a[0][0]=zigZagDecMat[0][0];
                    a[3][0]=zigZagDecMat[1][3];
                    a[0][1]=zigZagDecMat[0][1];
                    a[3][1]=zigZagDecMat[1][5];
                    a[0][2]=zigZagDecMat[0][5];
                    a[3][2]=zigZagDecMat[3][0];
                    a[0][3]=zigZagDecMat[1][0];
                    a[3][3]=zigZagDecMat[3][5];
                    a[0][4]=zigZagDecMat[2][2];
                    a[3][4]=zigZagDecMat[4][3];
                    a[0][5]=zigZagDecMat[2][3];
                    a[3][5]=zigZagDecMat[5][2];
                    a[1][0]=zigZagDecMat[0][2];
                    a[4][0]=zigZagDecMat[1][4];
                    a[1][1]=zigZagDecMat[0][4];
                    a[4][1]=zigZagDecMat[3][1];
                    a[1][2]=zigZagDecMat[1][1];
                    a[4][2]=zigZagDecMat[3][4];
                    a[1][3]=zigZagDecMat[2][1];
                    a[4][3]=zigZagDecMat[4][4];
                    a[1][4]=zigZagDecMat[2][4];
                    a[4][4]=zigZagDecMat[5][1];
                    a[1][5]=zigZagDecMat[4][1];
                    a[4][5]=zigZagDecMat[5][3];
                    a[2][0]=zigZagDecMat[0][3];
                    a[5][0]=zigZagDecMat[3][2];
                    a[2][1]=zigZagDecMat[1][2];
                    a[5][1]=zigZagDecMat[3][3];
                    a[2][2]=zigZagDecMat[2][0];
                    a[5][2]=zigZagDecMat[4][5];
                    ;
                    a[2][3]=zigZagDecMat[2][5];
                    a[5][3]=zigZagDecMat[5][0];
                    a[2][4]=zigZagDecMat[4][0];
                    a[5][4]=zigZagDecMat[5][4];
                    a[2][5]=zigZagDecMat[4][2];
                    a[5][5]=zigZagDecMat[5][5];
                    break;
                case 7:
                    a[0][0]=zigZagDecMat[0][0];
                    a[4][0]=zigZagDecMat[1][3];
                    a[0][1]=zigZagDecMat[0][1];
                    a[4][1]=zigZagDecMat[2][5];
                    a[0][2]=zigZagDecMat[0][5];
                    a[4][2]=zigZagDecMat[3][2];
                    a[0][3]=zigZagDecMat[0][6];
                    a[4][3]=zigZagDecMat[4][3];
                    a[0][4]=zigZagDecMat[2][0];
                    a[4][4]=zigZagDecMat[5][1];
                    a[0][5]=zigZagDecMat[2][1];
                    a[4][5]=zigZagDecMat[5][5];
                    a[0][6]=zigZagDecMat[3][6];
                    a[4][6]=zigZagDecMat[6][3];
                    a[1][0]=zigZagDecMat[0][2];
                    a[5][0]=zigZagDecMat[2][6];
                    a[1][1]=zigZagDecMat[0][4];
                    a[5][1]=zigZagDecMat[3][1];
                    a[1][2]=zigZagDecMat[1][0];
                    a[5][2]=zigZagDecMat[4][4];
                    a[1][3]=zigZagDecMat[1][6];
                    a[5][3]=zigZagDecMat[5][0];
                    a[1][4]=zigZagDecMat[2][2];
                    a[5][4]=zigZagDecMat[5][6];
                    a[1][5]=zigZagDecMat[3][5];
                    a[5][5]=zigZagDecMat[6][2];
                    a[1][6]=zigZagDecMat[4][0];
                    a[5][6]=zigZagDecMat[6][4];
                    a[2][0]=zigZagDecMat[0][3];
                    a[6][0]=zigZagDecMat[3][0];
                    a[2][1]=zigZagDecMat[1][1];
                    a[6][1]=zigZagDecMat[4][5];
                    a[2][2]=zigZagDecMat[1][5];
                    a[6][2]=zigZagDecMat[4][6];
                    a[2][3]=zigZagDecMat[2][3];
                    a[6][3]=zigZagDecMat[6][0];
                    a[2][4]=zigZagDecMat[3][4];
                    a[6][4]=zigZagDecMat[6][1];
                    a[2][5]=zigZagDecMat[4][1];
                    a[6][5]=zigZagDecMat[6][5];
                    a[2][6]=zigZagDecMat[5][3];
                    a[6][6]=zigZagDecMat[6][6];
                    a[3][0]=zigZagDecMat[1][2];
                    a[3][1]=zigZagDecMat[1][4];
                    a[3][2]=zigZagDecMat[2][4];
                    a[3][3]=zigZagDecMat[3][4];
                    a[3][4]=zigZagDecMat[4][2];
                    a[3][5]=zigZagDecMat[5][2];
                    a[3][6]=zigZagDecMat[5][4];
                    break;
                default:
                    break;
            }
		return a;	
	}

}
