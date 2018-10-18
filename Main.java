package com.iss.demo;

public class Main {

	public static void main(String[] args) {
		
		//客户端
		for (int i = 0; i < 1; i++) {
			ClientWin cw=new ClientWin(i);
		}
		
		try {
			Class.forName("com.iss.demo.Main");//全限定类名
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
