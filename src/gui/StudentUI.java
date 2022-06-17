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
//	��������
	public static JFrame jframe_1 =new JFrame("ѧ���ɼ�����ϵͳ");
	public static JFrame jframe_2=new JFrame("��¼");
//	��¼������ı���������
	JTextField jtext=new JTextField(12);
	JPasswordField jpassword=new JPasswordField(12);
//	�������
	public static JPanel jpanel_1=new JPanel(new FlowLayout());//��ʽ����
	public static JPanel jpanel_2=new JPanel(new BorderLayout());//�ղ���

//	ѧ������Ϊforѭ������
	public static String[] studentInfo= {" ѧ        �ţ�"," ��        ����","��        ��: ","��������:",
			" �� �� �� ѧ��"," �� �� �� ����"," �� ɢ �� ѧ��"," Ӣ         �","��        ��: "};
	
	public static void main(String[] args) {
//		ʵ����
		StudentUI ui=new StudentUI();
//		���÷���
		ui.init();
	}
	
//  ��¼����
	public void init() {
		
//		���ڴ�С
		jframe_2.setSize(230,160);
//		��ʽ����
		jframe_2.setLayout(new FlowLayout());
//		���ڲ��ɵ���
		jframe_2.setResizable(false);
//		�رմ������˳�����
		jframe_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		��ǩ
		JLabel jlabel_1=new JLabel("��  �ţ�");
		JLabel jlabel_2=new JLabel("��  �룺");
//		�����������ʾΪ*
		jpassword.setEchoChar('*');
//		����
		Font font=new Font("����",Font.BOLD,18);
		jlabel_1.setFont(font);
		jlabel_2.setFont(font);
		//���尴ť
		JButton jbutton_1=new JButton("��¼");
		JButton jbutton_2=new JButton("ע��");
		jbutton_1.setFont(font);
		jbutton_2.setFont(font);
//		��������
		jframe_2.add(jlabel_1);
		jframe_2.add(jtext);
		jframe_2.add(jlabel_2);
		jframe_2.add(jpassword);
		jframe_2.add(jbutton_1);
		jframe_2.add(jbutton_2);
		
//		ע�����
		jbutton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(MysqlOperation.teacherLogin(jtext,jpassword)==true) {
					JOptionPane.showMessageDialog(
	                        jbutton_1,
	                        "��¼�ɹ�!\n��ӭʹ��ѧ���ɼ�����ϵͳ��",
	                        "��ʾ",
	                        JOptionPane.INFORMATION_MESSAGE
	                );
//					���ص�¼����
					StudentUI.jframe_2.dispose();
//					��ʼ��
					StudentUI.init_1();
				}else {
					JOptionPane.showMessageDialog(
	                        jbutton_1,
	                        "��¼ʧ�ܣ���Ż��������������",
	                        "��ʾ",
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
//		���ھ���
		jframe_2.setLocationRelativeTo(null);
//		������ʾ
		jframe_2.setVisible(true);
	}
	
	
//	������
	public static void init_1() {

//		���ڴ�С
		jframe_1.setSize(1010,600);
//		�ղ���
		jframe_1.setLayout(null);
//		���ڲ��ɵ���
		jframe_1.setResizable(false);
//		�رմ������˳�����
		jframe_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		�˵���
		JMenuBar bar=new JMenuBar();
//		һ���˵�
		JMenu men_1=new JMenu("�ɼ���ѯ");
		JMenu men_2=new JMenu("ѧ����Ϣ");
//		�Ӳ˵�
		JMenuItem item_1=new JMenuItem("��ѯ�ɼ�");
		JMenuItem item_2=new JMenuItem("ȫ���ɼ�");
		JMenuItem item_3=new JMenuItem("�����ɼ�");
		JMenuItem item_4=new JMenuItem("¼��ɼ�");
		
		JMenuItem item_5=new JMenuItem("���ѧ��");
		JMenuItem item_6=new JMenuItem("�޸�ѧ��");
		JMenuItem item_7=new JMenuItem("ɾ��ѧ��");
//		��������
		Font font=new Font("����",Font.PLAIN,15);
		
//		���ò˵�����
		men_1.setFont(font);
		men_2.setFont(font);
		item_1.setFont(font);
		item_2.setFont(font);
		item_3.setFont(font);
		item_4.setFont(font);
		item_5.setFont(font);
		item_6.setFont(font);
		item_7.setFont(font);
//		����
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
	
//		�������λ�á���С����ɫ
		jpanel_1.setBounds(0,0,280,600);
		jpanel_2.setBounds(280,0,730,600);
		jpanel_1.setBackground(Color.GRAY);
		jpanel_2.setBackground(Color.LIGHT_GRAY);
		jframe_1.add(jpanel_1);
		jframe_1.add(jpanel_2);
		
		
//		���ھ���
		jframe_1.setLocationRelativeTo(null);
//		������ʾ
		jframe_1.setVisible(true);
		
//		ע�� ���ѧ�� ��ť�ļ���
		item_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				���÷���
				StudentUI.init_2();
			}
		});
//		ע�� �޸�ѧ�� ��ť�ļ���
		item_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_3();
			}
		});
//		ע�� ɾ��ѧ�� ��ť�ļ���
		item_7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_4();
			}
		});
//		ע�� ��ѯѧ�� ��ť�ļ���
		item_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_5();
			}
		});
//		�� �ֽܷ���ť �ļ���
		item_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_7();
			}
		});
//		�����ɼ� ��ť�ļ���
		item_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_9();
			}
		});
//      ¼��ɼ� ����
		item_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.jpanel_1.removeAll();
				StudentUI.jpanel_2.removeAll();
				StudentUI.jpanel_1.updateUI();
				StudentUI.jpanel_2.updateUI();
				String [] options = {"�ߵ���ѧ","���Դ���","��ɢ��ѧ","Ӣ��"};
				String s=(String) JOptionPane.showInputDialog(null,"��ѡ���Ŀ��",
						"��ʾ",JOptionPane.QUESTION_MESSAGE,null,options,null);
				if(s!=null) {
					StudentUI.init_8(s);
				}
			}
			
		});
	}
	
//	���ѧ������
	public static void init_2() {
//		������
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.updateUI();
		StudentUI.jpanel_2.updateUI();
//		����
		Font font=new Font("����",Font.PLAIN,15);

//		��ǩ
		JLabel jlabel[]=new JLabel[studentInfo.length-1];
//		�ı���
		JTextField jtextfield[]=new JTextField[studentInfo.length-1];
//		ʵ����
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

		jtextfield[0].setText("ѧ���Զ�����");
		jtextfield[0].setEditable(false);
//		��ť
		JButton jbutton_1=new JButton(" ȷ  �� ");
		JButton jbutton_2=new JButton(" ��  �� ");
		JButton jbutton_test=new JButton(" ��  �� ");
		JButton jbutton_3=new JButton(" ���Ҳ���������ѧ����� ");
//		���밴ť
		StudentUI.jpanel_1.add(jbutton_1);
		StudentUI.jpanel_1.add(jbutton_2);
		StudentUI.jpanel_1.add(jbutton_test);
		StudentUI.jpanel_1.add(jbutton_3);
		String[] head= {"ѧ��","����","�Ա�","��������","����","�ߴ�","��ɢ","Ӣ��"};
		DefaultTableModel model=new DefaultTableModel(null,head);
		JTable table=new JTable(model);
		StudentUI.jpanel_2.add(new JScrollPane(table));
		
//		ע�����		
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
					if(i==0)jtextfield[0].setText("ѧ���Զ�����");
					else jtextfield[i].setText("");
				}
			}
			
		});
		StudentListen e_1=new StudentListen(table);
		jbutton_3.addActionListener(e_1);
		
	}
	
//	�޸� ѧ������
	public static void init_3() {
//		������
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.updateUI();
		StudentUI.jpanel_2.updateUI();
//		����
		Font font=new Font("����",Font.PLAIN,15);
		
//		��ǩ
		JLabel jlabel[]=new JLabel[studentInfo.length-1];
//		�ı���
		JTextField jtextfield[]=new JTextField[studentInfo.length-1];
//		��ť
		JButton jbutton_3=new JButton("�޸�");
		JButton jbutton_4=new JButton("���");
		jbutton_3.setFont(font);
		jbutton_4.setFont(font);
		
//		ʵ����
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
//			j������Ӵ�����ֹ�ظ����
			if(j==0) {
				JLabel L_1=new JLabel("ѧ�Ų��ܱ��޸�!");
				JLabel L_2=new JLabel("��������������µ���Ϣ��");
				StudentUI.jpanel_1.add(L_1);
				StudentUI.jpanel_1.add(L_2);
			}
		}
		
		
		StudentUI.jpanel_1.add(jbutton_3);
		StudentUI.jpanel_1.add(jbutton_4);
//		ע�����
		StudentListen_1 e_1=new StudentListen_1(jbutton_3, jbutton_4, jtextfield);
		jbutton_3.addActionListener(e_1);
		jbutton_4.addActionListener(e_1);
	}
	
//	ɾ�� ѧ������
	public static void init_4() {
//		������
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.updateUI();
		StudentUI.jpanel_2.updateUI();
//		����
		Font font=new Font("����",Font.PLAIN,15);
//		��ǩ
		JLabel jlabel_1=new JLabel(" ѧ       �ţ�");
//		�ı���
		JTextField jtextfield_1=new JTextField(18);
//		��ӵ����
		StudentUI.jpanel_1.add(jlabel_1);
		StudentUI.jpanel_1.add(jtextfield_1);
		
//		��ť
		JButton jbutton_5=new JButton("ɾ��");
		JButton jbutton_6=new JButton("���");
		jbutton_5.setFont(font);
		jbutton_6.setFont(font);
		StudentUI.jpanel_1.add(jbutton_5);
		StudentUI.jpanel_1.add(jbutton_6);
		
//		ע�����
		StudentListen_2 e_2=new StudentListen_2(jbutton_5, jbutton_6, jtextfield_1);
		jbutton_5.addActionListener(e_2);
		jbutton_6.addActionListener(e_2);
	}
	
//	��ѯ ѧ������
	public static void init_5() {
//		������
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.updateUI();
		StudentUI.jpanel_2.updateUI();
//		����
		Font font=new Font("����",Font.PLAIN,15);
		
//		��ǩ
		JLabel jlabel[]=new JLabel[studentInfo.length];
//		�ı���
		JTextField jtextfield[]=new JTextField[studentInfo.length];
//		��ť
		JButton jbutton_81=new JButton(" �� ѯ ");
		JButton jbutton_9=new JButton(" �� �� ");
		jbutton_81.setFont(font);
		jbutton_9.setFont(font);
		JLabel add=new JLabel("  ��    ��  ��");
		JTextField ad=new JTextField(18);;
		JLabel id=new JLabel("  I    D  �� ");
		JTextField ID=new JTextField(18);
//		ʵ����
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
//		ע�����
		StudentListen_3 e_3=new StudentListen_3(jbutton_81,jbutton_9, ID, jtextfield,ad,StudentUI.jpanel_2);
		jbutton_81.addActionListener(e_3);
		jbutton_9.addActionListener(e_3);
	}
	
	
//	���ֽܷ����������
	public static void init_7() {
		StudentUI.jpanel_2.removeAll();
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_2.updateUI();
		StudentUI.jpanel_1.updateUI();
		
		MysqlOperation.showTotal("�ܷ�����",StudentUI.jpanel_2);
		new BarChart();
		JButton gaoshu=new JButton(" �����ɼ��ֲ���״ͼ ");
		JButton xiandai=new JButton(" �ߴ��ɼ��ֲ���״ͼ ");
		JButton lisan=new JButton(" ��ɢ�ɼ��ֲ���״ͼ ");
		JButton yingyu=new JButton(" Ӣ��ɼ��ֲ���״ͼ ");
		
		gaoshu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File file1=new File("D:\\ѧ�������ɼ���״�ֲ�ͼ.jpg");
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
				File file1=new File("D:\\ѧ���ߴ��ɼ���״�ֲ�ͼ.jpg");
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
				File file1=new File("D:\\ѧ����ɢ�ɼ���״�ֲ�ͼ.jpg");
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
				File file1=new File("D:\\ѧ��Ӣ��ɼ���״�ֲ�ͼ.jpg");
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
	
//¼��ɼ�ҳ��	
	public static void init_8(String str) {
		StudentUI.jpanel_1.removeAll();
		StudentUI.jpanel_1.updateUI();
		JTextField id=new JTextField(22);
		JTextField grade=new JTextField(22);
		JButton ok=new JButton("ȷ��");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MysqlOperation.inputGrade(str,id,grade);
			}
			
		});
		
		StudentUI.jpanel_1.add(new JLabel(" I  D :"));
		StudentUI.jpanel_1.add(id);
		StudentUI.jpanel_1.add(new JLabel(" ��  �� :"));
		StudentUI.jpanel_1.add(grade);
		StudentUI.jpanel_1.add(ok);
	}
	
//�����ɼ�
	public static void init_9() {
		MysqlOperation.showTotal("�����ɼ�",StudentUI.jpanel_2);
	}
}