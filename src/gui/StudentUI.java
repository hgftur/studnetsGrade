package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import demo.Test;
import operation.BarChart;
import operation.MysqlOperation;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
public class StudentUI {
//	定义容器
	public static JFrame jframe_1 =new JFrame("学生成绩管理系统");
	public static JFrame jframe_2=new JFrame("登录");
//	登录界面的文本框和密码框
	JTextField jtext=new JTextField(12);
	JPasswordField jpassword=new JPasswordField(12);
//	定义面板
	public static JPanel jpanel_1=new JPanel(new FlowLayout());//流式布局
	public static JPanel jpanel_2=new JPanel(new BorderLayout());//空布局

//	学科名（为for循环服务）
	public static String[] studentInfo= {" 学        号："," 姓        名：","性        别: ","出生日期:",
			" 高 等 数 学："," 线 性 代 数："," 离 散 数 学："," 英         语：","总        分: "};
	
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
		jframe_2.setResizable(false);
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
				new RegisterView();
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
		JMenu men_1=new JMenu("成绩查询");
		JMenu men_2=new JMenu("学生信息");
//		子菜单
		JMenuItem item_1=new JMenuItem("查询成绩");
		JMenuItem item_2=new JMenuItem("全部成绩");
		JMenuItem item_3=new JMenuItem("导出成绩");
		JMenuItem item_4=new JMenuItem("录入成绩");
		
		JMenuItem item_5=new JMenuItem("添加学生");
		JMenuItem item_6=new JMenuItem("修改学生");
		JMenuItem item_7=new JMenuItem("删除学生");
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
		men_1.add(item_4);
		men_1.add(item_1);
		men_1.add(item_2);
		men_1.add(item_3);
		
		
		men_2.add(item_5);
		men_2.add(item_6);
		men_2.add(item_7);
		
		
		bar.add(men_1);
		bar.add(men_2);
		jframe_1.setJMenuBar(bar);
	
//		设置面板位置、大小、颜色
		jpanel_1.setBounds(0,0,280,600);
		jpanel_2.setBounds(280,0,730,600);
		jpanel_1.setBackground(Color.GRAY);
		jpanel_2.setBackground(Color.LIGHT_GRAY);
		jframe_1.add(jpanel_1);
		jframe_1.add(jpanel_2);
		
		
//		窗口居中
		jframe_1.setLocationRelativeTo(null);
//		窗口显示
		jframe_1.setVisible(true);
		
//		注册 添加学生 按钮的监听
		item_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				调用方法
				StudentUI.init_2();
			}
		});
//		注册 修改学生 按钮的监听
		item_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_3();
			}
		});
//		注册 删除学生 按钮的监听
		item_7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_4();
			}
		});
//		注册 查询学生 按钮的监听
		item_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_5();
			}
		});
//		按 总分降序按钮 的监听
		item_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_7();
			}
		});
//		导出成绩 按钮的监听
		item_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_9();
			}
		});
//      录入成绩 监听
		item_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.jpanel_1.removeAll();
				StudentUI.jpanel_2.removeAll();
				StudentUI.jpanel_1.updateUI();
				StudentUI.jpanel_2.updateUI();
				String [] options = {"高等数学","线性代数","离散数学","英语"};
				String s=(String) JOptionPane.showInputDialog(null,"请选择科目：",
						"提示",JOptionPane.QUESTION_MESSAGE,null,options,null);
				if(s!=null) {
					StudentUI.init_8(s);
				}
			}
			
		});
	}
	
//	添加学生界面
	public static void init_2() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.updateUI();
		StudentUI.jpanel_2.updateUI();
//		字体
		Font font=new Font("黑体",Font.PLAIN,15);

//		标签
		JLabel jlabel[]=new JLabel[studentInfo.length-1];
//		文本框
		JTextField jtextfield[]=new JTextField[studentInfo.length-1];
//		实例化
		for(int i=0;i<studentInfo.length-1;i++) {
			int length=15;
			jlabel[i]=new JLabel(studentInfo[i]);
			jlabel[i].setFont(font);
			if(i==3) {
				jlabel[i].setText(studentInfo[i]+"(****-**-**)");
				length=10;
			}
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(length);
			StudentUI.jpanel_1.add(jtextfield[i]);
		}

		jtextfield[0].setText("学号自动生成");
		jtextfield[0].setEditable(false);
//		按钮
		JButton jbutton_1=new JButton(" 确  定 ");
		JButton jbutton_2=new JButton(" 清  除 ");
		JButton jbutton_test=new JButton(" 测  试 ");
		JButton jbutton_3=new JButton(" 将右侧表格中所有学生添加 ");
//		加入按钮
		StudentUI.jpanel_1.add(jbutton_1);
		StudentUI.jpanel_1.add(jbutton_2);
		StudentUI.jpanel_1.add(jbutton_test);
		StudentUI.jpanel_1.add(jbutton_3);
		String[] head= {"学号","姓名","性别","出生日期","高数","线代","离散","英语"};
		DefaultTableModel model=new DefaultTableModel(null,head);
		JTable table=new JTable(model);
		StudentUI.jpanel_2.add(new JScrollPane(table));
		
//		注册监听		
		jbutton_test.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Test().createData(table);
			}
			
		});
		jbutton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] content=new String[head.length];
				for(int i=1;i<head.length;i++) {
					content[i]=jtextfield[i].getText();
				}
				model.addRow(content);
			}
			
		});
		jbutton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<jtextfield.length;i++) {
					if(i==0)jtextfield[0].setText("学号自动生成");
					else jtextfield[i].setText("");
				}
			}
			
		});
		StudentListen e_1=new StudentListen(table);
		jbutton_3.addActionListener(e_1);
		
	}
	
//	修改 学生界面
	public static void init_3() {
//		清空组件
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.updateUI();
		StudentUI.jpanel_2.updateUI();
//		字体
		Font font=new Font("黑体",Font.PLAIN,15);
		
//		标签
		JLabel jlabel[]=new JLabel[studentInfo.length-1];
//		文本框
		JTextField jtextfield[]=new JTextField[studentInfo.length-1];
//		按钮
		JButton jbutton_3=new JButton("修改");
		JButton jbutton_4=new JButton("清除");
		jbutton_3.setFont(font);
		jbutton_4.setFont(font);
		
//		实例化
		for(int i=0,j=0;i<studentInfo.length-1;i++,j++) {
			int length=15;
			jlabel[i]=new JLabel(studentInfo[i]);
			jlabel[i].setFont(font);
			if(i==3) {
				jlabel[i].setText(studentInfo[i]+"(****-**-**)");
				length=10;
			}
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(length);
			StudentUI.jpanel_1.add(jtextfield[i]);
//			j控制添加次数防止重复添加
			if(j==0) {
				JLabel L_1=new JLabel("学号不能被修改!");
				JLabel L_2=new JLabel("请在下面输入更新的信息：");
				StudentUI.jpanel_1.add(L_1);
				StudentUI.jpanel_1.add(L_2);
			}
		}
		
		
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
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.updateUI();
		StudentUI.jpanel_2.updateUI();
//		字体
		Font font=new Font("黑体",Font.PLAIN,15);
//		标签
		JLabel jlabel_1=new JLabel(" 学       号：");
//		文本框
		JTextField jtextfield_1=new JTextField(18);
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
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.updateUI();
		StudentUI.jpanel_2.updateUI();
//		字体
		Font font=new Font("黑体",Font.PLAIN,15);
		
//		标签
		JLabel jlabel[]=new JLabel[studentInfo.length];
//		文本框
		JTextField jtextfield[]=new JTextField[studentInfo.length];
//		按钮
		JButton jbutton_81=new JButton(" 查 询 ");
		JButton jbutton_9=new JButton(" 清 除 ");
		jbutton_81.setFont(font);
		jbutton_9.setFont(font);
		JLabel add=new JLabel("  姓    名  ：");
		JTextField ad=new JTextField(18);;
		JLabel id=new JLabel("  I    D  ： ");
		JTextField ID=new JTextField(18);
//		实例化
		ID.setEditable(true);
		StudentUI.jpanel_1.add(id);
		StudentUI.jpanel_1.add(ID);
		ad.setEditable(true);
		StudentUI.jpanel_1.add(add);
		StudentUI.jpanel_1.add(ad);
		StudentUI.jpanel_1.add(jbutton_81);
		StudentUI.jpanel_1.add(jbutton_9);
		
		for(int i=0;i<studentInfo.length;i++) {
			if(i!=2&&i!=3) {
				jlabel[i]=new JLabel(studentInfo[i]);
				jlabel[i].setFont(font);
				StudentUI.jpanel_1.add(jlabel[i]);
				int length=12;
				jtextfield[i]=new JTextField(length);
				StudentUI.jpanel_1.add(jtextfield[i]);
				jtextfield[i].setEditable(false);
			}
			
		}
//		注册监听
		StudentListen_3 e_3=new StudentListen_3(jbutton_81,jbutton_9, ID, jtextfield,ad,StudentUI.jpanel_2);
		jbutton_81.addActionListener(e_3);
		jbutton_9.addActionListener(e_3);
	}
	
	
//	按总分降序排序界面
	public static void init_7() {
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_2.updateUI();
		StudentUI.jpanel_1.updateUI();
		
		MysqlOperation.showTotal("总分排序",StudentUI.jpanel_2);
		new BarChart();
		JButton gaoshu=new JButton(" 高数成绩分布柱状图 ");
		JButton xiandai=new JButton(" 线代成绩分布柱状图 ");
		JButton lisan=new JButton(" 离散成绩分布柱状图 ");
		JButton yingyu=new JButton(" 英语成绩分布柱状图 ");
		
		gaoshu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file1=new File("D:\\学生高数成绩柱状分布图.jpg");
				Desktop desktop=Desktop.getDesktop();
				try {
					desktop.open(file1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		xiandai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file1=new File("D:\\学生线代成绩柱状分布图.jpg");
				Desktop desktop=Desktop.getDesktop();
				try {
					desktop.open(file1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		lisan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file1=new File("D:\\学生离散成绩柱状分布图.jpg");
				Desktop desktop=Desktop.getDesktop();
				try {
					desktop.open(file1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		yingyu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file1=new File("D:\\学生英语成绩柱状分布图.jpg");
				Desktop desktop=Desktop.getDesktop();
				try {
					desktop.open(file1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		jpanel_1.add(gaoshu);
		jpanel_1.add(xiandai);
		jpanel_1.add(lisan);
		jpanel_1.add(yingyu);
	}
	
//录入成绩页面	
	public static void init_8(String str) {
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_1.updateUI();
		JTextField id=new JTextField(22);
		JTextField grade=new JTextField(22);
		JButton ok=new JButton("确定");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MysqlOperation.inputGrade(str,id,grade);
			}
			
		});
		
		StudentUI.jpanel_1.add(new JLabel(" I  D :"));
		StudentUI.jpanel_1.add(id);
		StudentUI.jpanel_1.add(new JLabel(" 成  绩 :"));
		StudentUI.jpanel_1.add(grade);
		StudentUI.jpanel_1.add(ok);
	}
	
//导出成绩
	public static void init_9() {
		MysqlOperation.showTotal("导出成绩",StudentUI.jpanel_2);
	}
}