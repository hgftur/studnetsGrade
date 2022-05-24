package studentgrades;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class StudentUI {
//	定义容器
	public static JFrame jframe_1 =new JFrame("学生成绩管理系统");
	public static JFrame jframe_2=new JFrame("登录");
//	登录界面的文本框和密码框
	JTextField jtext=new JTextField(12);
	JPasswordField jpassword=new JPasswordField(12);
//	定义面板
	public static JPanel jpanel_1=new JPanel(new FlowLayout());//流式布局
	public static JPanel jpanel_2=new JPanel(null);//空布局
//	设置文本区用于显示信息
	public static JTextArea j_1=new JTextArea();
//	学科名（为for循环服务）
	public static String[] studentInfo= {" 学       号："," 姓       名：",
			"高等数学：","线性代数：","离散数学："," 英       语："," 总       分："};
	
	public static void main(String[] args) {
//		实例化
		StudentUI ui=new StudentUI();
//		调用方法
		ui.init();
	}
	
//  登录界面
	public void init() {
		
//		窗口大小
		jframe_2.setSize(230,160);
//		流式布局
		jframe_2.setLayout(new FlowLayout());
//		窗口不可调整
//		jframe_2.setResizable(false);
//		关闭窗口则退出程序
		jframe_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		标签
		JLabel jlabel_1=new JLabel("编  号：");
		JLabel jlabel_2=new JLabel("密  码：");
//		设置密码框显示为*
		jpassword.setEchoChar('*');
//		字体
		Font font=new Font("宋体",Font.BOLD,18);
		jlabel_1.setFont(font);
		jlabel_2.setFont(font);
		//定义按钮
		JButton jbutton_1=new JButton("登录");
		JButton jbutton_2=new JButton("注册");
		jbutton_1.setFont(font);
		jbutton_2.setFont(font);
//		加入容器
		jframe_2.add(jlabel_1);
		jframe_2.add(jtext);
		jframe_2.add(jlabel_2);
		jframe_2.add(jpassword);
		jframe_2.add(jbutton_1);
		jframe_2.add(jbutton_2);
		
//		注册监听
		jbutton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(MysqlOperation.teacherLogin(jtext,jpassword)==true) {
					JOptionPane.showMessageDialog(
	                        jbutton_1,
	                        "登录成功!\n欢迎使用学生成绩管理系统！",
	                        "提示",
	                        JOptionPane.INFORMATION_MESSAGE
	                );
//					隐藏登录界面
					StudentUI.jframe_2.dispose();
//					初始化
					StudentUI.init_1();
				}else {
					JOptionPane.showMessageDialog(
	                        jbutton_1,
	                        "登录失败，编号或密码错误，请重试",
	                        "提示",
	                        JOptionPane.INFORMATION_MESSAGE
	                );
				}
				
			}
		});
		
		jbutton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
//				StudentUI.jframe_2.dispose();
				new RegisterView();
//				点击了清除按钮
//				jtext.setText("");
//				jpassword.setText("");
			}
		});
//		窗口居中
		jframe_2.setLocationRelativeTo(null);
//		窗口显示
		jframe_2.setVisible(true);
	}
	
	
//	主界面
	public static void init_1() {

//		窗口大小
		jframe_1.setSize(1010,600);
//		空布局
		jframe_1.setLayout(null);
//		窗口不可调整
		jframe_1.setResizable(false);
//		关闭窗口则退出程序
		jframe_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		菜单栏
		JMenuBar bar=new JMenuBar();
//		一级菜单
		JMenu men_1=new JMenu("基本操作");
		JMenu men_2=new JMenu("排序");
//		子菜单
		JMenuItem item_1=new JMenuItem("添加学生");
		JMenuItem item_2=new JMenuItem("修改学生");
		JMenuItem item_3=new JMenuItem("删除学生");
		JMenuItem item_4=new JMenuItem("查询学生");
		JMenuItem item_7=new JMenuItem("另存为");
		
		JMenuItem item_5=new JMenuItem("按总分降序");
		JMenuItem item_6=new JMenuItem("按某科目降序");
//		定义字体
		Font font=new Font("黑体",Font.PLAIN,15);
		
//		设置菜单字体
		men_1.setFont(font);
		men_2.setFont(font);
		item_1.setFont(font);
		item_2.setFont(font);
		item_3.setFont(font);
		item_4.setFont(font);
		item_5.setFont(font);
		item_6.setFont(font);
		item_7.setFont(font);
//		加入
		men_1.add(item_1);
		men_1.add(item_2);
		men_1.add(item_3);
		men_1.add(item_4);
		men_1.add(item_7);
		
		men_2.add(item_5);
		men_2.add(item_6);
		
		bar.add(men_1);
		bar.add(men_2);
		jframe_1.setJMenuBar(bar);
	
//		设置面板位置、大小、颜色
		jpanel_1.setBounds(0,0,320,600);
		jpanel_2.setBounds(320,0,810,600);
		jpanel_1.setBackground(Color.LIGHT_GRAY);
		jpanel_2.setBackground(Color.LIGHT_GRAY);
		jframe_1.add(jpanel_1);
		jframe_1.add(jpanel_2);
		

		//设置文本区不能编辑
		j_1.setEditable(false);
		//将j1作为可滚动面板sp的显示区域
		JScrollPane sp=new JScrollPane(j_1);
		sp.setSize(810,600);
		StudentUI.jpanel_2.add(sp);
		
//		窗口居中
		jframe_1.setLocationRelativeTo(null);
//		窗口显示
		jframe_1.setVisible(true);
		
//		注册 添加学生 按钮的监听
		item_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				调用方法
				StudentUI.init_2();
			}
		});
//		注册 修改学生 按钮的监听
		item_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_3();
			}
		});
//		注册 删除学生 按钮的监听
		item_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_4();
			}
		});
//		注册 查询学生 按钮的监听
		item_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_5();
			}
		});
//		按 总分降序按钮 的监听
		item_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_7();
			}
		});
//		按 某科降序按钮 的监听
		item_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String [] options = {"高等数学","线性代数","离散数学","英语"};
				String s=(String) JOptionPane.showInputDialog(null,"请输入你的选项：",
						"提示",JOptionPane.QUESTION_MESSAGE,null,options,null);
				if(s!=null) {
					StudentUI.init_8(s);
				}
			}
		});
//		保存 按钮的监听
		item_7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MysqlOperation.saveStudent();
			}
		});
	}
	
//	添加学生界面
	public static void init_2() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
//		Font font=new Font("黑体",Font.PLAIN,15);

//		标签
		JLabel jlabel[]=new JLabel[studentInfo.length];
//		文本框
		JTextField jtextfield[]=new JTextField[studentInfo.length];
//		实例化
		for(int i=0;i<studentInfo.length;i++) {
			jlabel[i]=new JLabel(studentInfo[i]);
//			jlabel[i].setFont(font);
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(12);
			StudentUI.jpanel_1.add(jtextfield[i]);
		}
//		设置文本框默认值(总分自动计算,不可编辑)
		jtextfield[0].setText("学号自动生成");
		jtextfield[0].setEditable(false);
		jtextfield[studentInfo.length-1].setText("自动计算，无需输入");
		jtextfield[studentInfo.length-1].setEditable(false);
//		按钮
		JButton jbutton_1=new JButton("提交");
		JButton jbutton_2=new JButton("清除");
//		加入按钮
		StudentUI.jpanel_1.add(jbutton_1);
		StudentUI.jpanel_1.add(jbutton_2);
//		注册监听
		StudentListen e_1=new StudentListen(jbutton_1, jbutton_2, jtextfield);
		jbutton_1.addActionListener(e_1);
		jbutton_2.addActionListener(e_1);
		
	}
	
//	修改 学生界面
	public static void init_3() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
		Font font=new Font("黑体",Font.PLAIN,15);
		
//		标签
		JLabel jlabel[]=new JLabel[studentInfo.length];
//		文本框
		JTextField jtextfield[]=new JTextField[studentInfo.length];
//		按钮
		JButton jbutton_3=new JButton("修改");
		JButton jbutton_4=new JButton("清除");
		jbutton_3.setFont(font);
		jbutton_4.setFont(font);
		
//		实例化
		for(int i=0,j=0;i<studentInfo.length;i++,j++) {
			jlabel[i]=new JLabel(studentInfo[i]);
//			jlabel[i].setFont(font);
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(12);
			StudentUI.jpanel_1.add(jtextfield[i]);
//			j控制添加次数防止重复添加
			if(j==0) {
				JLabel L_1=new JLabel("学号不能被修改!");
				JLabel L_2=new JLabel("请在下面输入更新的信息：");
				StudentUI.jpanel_1.add(L_1);
				StudentUI.jpanel_1.add(L_2);
			}
		}
//		设置文本框默认值(总分自动计算,不可编辑)
		jtextfield[studentInfo.length-1].setText("自动计算，无需输入");
		jtextfield[studentInfo.length-1].setEditable(false);
		
		StudentUI.jpanel_1.add(jbutton_3);
		StudentUI.jpanel_1.add(jbutton_4);
//		注册监听
		StudentListen_1 e_1=new StudentListen_1(jbutton_3, jbutton_4, jtextfield);
		jbutton_3.addActionListener(e_1);
		jbutton_4.addActionListener(e_1);
	}
	
//	删除 学生界面
	public static void init_4() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
		Font font=new Font("黑体",Font.PLAIN,15);
//		标签
		JLabel jlabel_1=new JLabel(" 学       号：");
//		文本框
		JTextField jtextfield_1=new JTextField(10);
//		添加到面板
		StudentUI.jpanel_1.add(jlabel_1);
		StudentUI.jpanel_1.add(jtextfield_1);
		
//		按钮
		JButton jbutton_5=new JButton("删除");
		JButton jbutton_6=new JButton("清除");
		jbutton_5.setFont(font);
		jbutton_6.setFont(font);
		StudentUI.jpanel_1.add(jbutton_5);
		StudentUI.jpanel_1.add(jbutton_6);
		
//		注册监听
		StudentListen_2 e_2=new StudentListen_2(jbutton_5, jbutton_6, jtextfield_1);
		jbutton_5.addActionListener(e_2);
		jbutton_6.addActionListener(e_2);
	}
	
//	查询 学生界面
	public static void init_5() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
//		刷新面板
		StudentUI.jpanel_1.updateUI();
//		字体
		Font font=new Font("黑体",Font.PLAIN,15);
		
//		标签
		JLabel jlabel[]=new JLabel[studentInfo.length];
//		文本框
		JTextField jtextfield[]=new JTextField[studentInfo.length];
//		按钮
		JButton jbutton_81=new JButton("学号查询");
		JButton jbutton_82=new JButton("姓名查询");
		JButton jbutton_9=new JButton("清除");
		JButton jbutton_10=new JButton("输出全部");
		jbutton_81.setFont(font);
		jbutton_82.setFont(font);
		jbutton_9.setFont(font);
		jbutton_10.setFont(font);
		JLabel add=new JLabel("姓      名：");
		JTextField ad=new JTextField(12);;
//		实例化
		for(int i=0,j=0;i<studentInfo.length;i++,j++) {
			jlabel[i]=new JLabel(studentInfo[i]);
//			jlabel[i].setFont(font);
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(12);
			
			StudentUI.jpanel_1.add(jtextfield[i]);
			
					
			
			if(i==0) {
				StudentUI.jpanel_1.add(jbutton_81);
				
				ad.setEditable(true);
				StudentUI.jpanel_1.add(add);
				StudentUI.jpanel_1.add(ad);
				StudentUI.jpanel_1.add(jbutton_82);
			}
			
			
//			j控制按钮的添加次数防止重复添加
			if(j==1) {
				//StudentUI.jpanel_1.add(jbutton_8);
				StudentUI.jpanel_1.add(jbutton_9);
			}
//			设置文本框不可编辑
			
			if(i==0)jtextfield[i].setEditable(true);
			else jtextfield[i].setEditable(false);
			
		}
		StudentUI.jpanel_1.add(jbutton_10);
		StudentUI.j_1.setText("");
//		注册监听
		StudentListen_3 e_3=new StudentListen_3(jbutton_81,jbutton_82, jbutton_9,jbutton_10, jtextfield,ad,j_1);
		jbutton_81.addActionListener(e_3);
		jbutton_82.addActionListener(e_3);
		jbutton_9.addActionListener(e_3);
		jbutton_10.addActionListener(e_3);
	}
	
//	输出全部学生界面
	public static void init_6() {
		
		//设置容器
		JFrame j=new JFrame("所有学生信息");
		j.setSize(615,335);
		j.setLayout(null);
		//窗口不能调整
		j.setResizable(false);
		//设置文本区用于显示所有学生信息
		JTextArea j_2=new JTextArea("文本区可以滑动！！！\n\n关闭当前窗口，再次点击查询所有学生按钮可以刷新！！！\n\n\n");
		//设置文本区不能编辑
		j_2.setEditable(false);
		//将j1作为可滚动面板sp的显示区域
		JScrollPane sp=new JScrollPane(j_2);
		sp.setLocation(0,0);
		sp.setSize(600,300);
		j.add(sp);
//		调用数据库方法显示全部学生
		MysqlOperation.showStudent(j_2);
//		居中显示
		j.setLocationRelativeTo(null);
//		显示窗口
		j.setVisible(true);	
	}
	
//	按总分降序排序界面
	public static void init_7() {
//		清空文本区
		StudentUI.j_1.setText("");
//		调用数据库方法
		MysqlOperation.showTotal(j_1);
	}
	
//	按某科降序排序界面
	public static void init_8(String str) {
//		清空文本区
		StudentUI.j_1.setText("");
		
		if(str.equals("高等数学")) {
			MysqlOperation.showsomeone(j_1, "advanced_Mathematics");
		}else if(str.equals("线性代数")) {
			MysqlOperation.showsomeone(j_1, "linear_Algebra");
		}else if(str.equals("离散数学")) {
			MysqlOperation.showsomeone(j_1, "discrete_Mathematics");
		}else if(str.equals("英语")) {
			MysqlOperation.showsomeone(j_1, "English");
		}
	}
	
}
