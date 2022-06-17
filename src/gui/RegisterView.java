package gui;

import javax.swing.*;

import operation.MysqlOperation;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

public class RegisterView{
	JFrame jframe=new JFrame("ע��");
	JButton register=new JButton("ע   ��");
	JTextField[] jtext=new JTextField[3];
	JPasswordField pw=new JPasswordField(18);
	JButton revert=new JButton("���ص�¼");
	public RegisterView() {
		jframe.setLayout(new FlowLayout());
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pw.setEchoChar('*');
		jtext[0]=new JTextField(18);
		jtext[1]=new JTextField(18);
		jtext[2]=new JTextField(18);
		
		
		jframe.add(new JLabel("  ��  ��  ��"));
		jframe.add(jtext[0]);
		
		jframe.add(new JLabel("  ��  ��  ��"));
		jframe.add(pw);
		
		jframe.add(new JLabel("  ��  ��  ��"));
		jframe.add(jtext[1]);
		
		jframe.add(new JLabel("  ��  Ŀ  ��"));
		jframe.add(jtext[2]);
		
		jframe.add(register);
		jframe.add(revert);
		
		jtext[0].setText("����Զ�����");
		jtext[0].setEditable(false);
		
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MysqlOperation.teacherRegister(jtext,pw);
			}
			
		});
		revert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jframe.dispose();
//				new StudentUI().init();
			}
			
		});
		
		jframe.setLocationRelativeTo(null);
		jframe.setSize(300,400);
		jframe.setVisible(true);
	}
} 
