package com.sam.Util;

public class xorandinverse {
	
	public static void main(String[] args){
		int key = 50;
		int k = (int) '?';
		int enc = key ^ k;
		System.out.print((char) (enc ^ key));
		
		
	}

}