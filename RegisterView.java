package studentgrades;
import javax.swing.*;
import java.awt.event.*;
public class RegisterView extends JFrame{
	JButton register=new JButton("注   册");
	JTextField[] jtext=new JTextField[3];
	Box mainbox=Box.createVerticalBox();
	JPasswordField pw=new JPasswordField();
	JButton revert=new JButton("返回登录");
	public RegisterView() {
		Box box1=Box.createHorizontalBox();
		Box box2=Box.createHorizontalBox();
		Box box3=Box.createHorizontalBox();
		Box box4=Box.createHorizontalBox();
		Box box5=Box.createHorizontalBox();
		
		jtext[0]=new JTextField(12);
		jtext[1]=new JTextField(12);
		jtext[2]=new JTextField(12);
		
		box1.add(Box.createHorizontalStrut(10));
		box1.add(new JLabel("编  号："));
		box1.add(jtext[0]);
		box1.add(Box.createHorizontalStrut(10));
		box2.add(new JLabel("密  码："));
		box2.add(pw);
		box1.add(Box.createHorizontalStrut(10));
		box3.add(new JLabel("姓  名："));
		box3.add(jtext[1]);
		box1.add(Box.createHorizontalStrut(10));
		box4.add(new JLabel("科  目："));
		box4.add(jtext[2]);
		box1.add(Box.createHorizontalStrut(10));
		box5.add(register);
		box5.add(Box.createHorizontalStrut(20));
		box5.add(revert);
		jtext[0].setText("编号自动生成");
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
				dispose();
//				new StudentUI().init();
			}
			
		});
		mainbox.add(Box.createVerticalStrut(20));
		mainbox.add(box1);
		mainbox.add(Box.createVerticalStrut(35));
		mainbox.add(box2);
		mainbox.add(Box.createVerticalStrut(35));
		mainbox.add(box3);
		mainbox.add(Box.createVerticalStrut(35));
		mainbox.add(box4);
		mainbox.add(Box.createVerticalStrut(40));
		mainbox.add(box5);
		mainbox.add(Box.createVerticalStrut(40));
		
		add(mainbox);
		setSize(250,350);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
