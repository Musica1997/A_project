package com.iss.demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//客户端
public class ClientWin extends JFrame{
	
	private Socket socket = null;//new Socket("127.0.0.1", 9999);
	private InputStream is = null;
	private OutputStream os = null;
	private BufferedReader br = null;
	private PrintStream ps = null;
	
	private JTextArea txt=new JTextArea();
	private JScrollPane sp;
	
	private JPanel panel=new JPanel();
	private JTextField txtSend =new JTextField();
	private JButton btn=new JButton("发送消息");
	
	public ClientWin(int x) {
		
		try {
			socket =new Socket("127.0.0.1", 9999);
			is = socket.getInputStream();			//获取输入流
			os = socket.getOutputStream();			//获取输出流
			br = new BufferedReader(new InputStreamReader(is));
			ps = new PrintStream(os);
		} catch (Exception e1) {			
			e1.printStackTrace();
		} 
		
		this.setBounds(10, 10, 500, 500);
		this.setTitle("客户端"+String.valueOf(x));
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		sp=new JScrollPane();
		
		sp.setViewportView(txt);
		
		this.add(sp,BorderLayout.CENTER);
		
		panel.setLayout(new BorderLayout());
		panel.add(txtSend,BorderLayout.CENTER);
		panel.add(btn,BorderLayout.EAST);
		
		//init();
		this.add(panel,BorderLayout.SOUTH);
		
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String str=txtSend.getText();
				sendMsg(str);
				txtSend.setText("");
			}
		});
		this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.out.println("正在尝试关闭网络通讯...");
				try {
					sendMsg("quit");
					System.out.println("111111...");
					Thread.sleep(2000);
					System.out.println("2222222...");
					socket.close();
				} catch (Exception e1) {
					System.out.println("尝试关闭网络通讯出现异常！");
					e1.printStackTrace();
				}
				System.out.println("已关闭网络通讯，成功！");
			}
			
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		txt.setText(receiveMsg());		
		
	}
	
	public void sendMsg(String msg) {
		try {
			ps.println(msg);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public String receiveMsg() {
		String msg="";
		try {
			msg=br.readLine();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return msg;
	}
	
	public void init() {
		try {
			Socket socket = new Socket("127.0.0.1", 9999);		//创建Socket指定ip地址和端口号
			InputStream is = socket.getInputStream();			//获取输入流
			OutputStream os = socket.getOutputStream();			//获取输出流
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			PrintStream ps = new PrintStream(os);
			String msg=br.readLine();
			txt.setText(msg);
//			JOptionPane.showMessageDialog(null, msg);
//			System.out.println(br.readLine());
			ps.println("我想报名就业班");
//			System.out.println(br.readLine());
//			ps.println("爷不学了");
			socket.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
}
