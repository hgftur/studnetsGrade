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
//	��������
	public static JFrame jframe_1 =new JFrame("ѧ���ɼ�����ϵͳ");
	public static JFrame jframe_2=new JFrame("��¼");
//	��¼������ı���������
	JTextField jtext=new JTextField(12);
	JPasswordField jpassword=new JPasswordField(12);
//	�������
	public static JPanel jpanel_1=new JPanel(new FlowLayout());//��ʽ����
	public static JPanel jpanel_2=new JPanel(null);//�ղ���
//	�����ı���������ʾ��Ϣ
	public static JTextArea j_1=new JTextArea();
//	ѧ������Ϊforѭ������
	public static String[] studentInfo= {" ѧ       �ţ�"," ��       ����",
			"�ߵ���ѧ��","���Դ�����","��ɢ��ѧ��"," Ӣ       �"," ��       �֣�"};
	
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
//		jframe_2.setResizable(false);
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
				
				
//				StudentUI.jframe_2.dispose();
				new RegisterView();
//				����������ť
//				jtext.setText("");
//				jpassword.setText("");
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
		JMenu men_1=new JMenu("��������");
		JMenu men_2=new JMenu("����");
//		�Ӳ˵�
		JMenuItem item_1=new JMenuItem("���ѧ��");
		JMenuItem item_2=new JMenuItem("�޸�ѧ��");
		JMenuItem item_3=new JMenuItem("ɾ��ѧ��");
		JMenuItem item_4=new JMenuItem("��ѯѧ��");
		JMenuItem item_7=new JMenuItem("���Ϊ");
		
		JMenuItem item_5=new JMenuItem("���ֽܷ���");
		JMenuItem item_6=new JMenuItem("��ĳ��Ŀ����");
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
	
//		�������λ�á���С����ɫ
		jpanel_1.setBounds(0,0,320,600);
		jpanel_2.setBounds(320,0,810,600);
		jpanel_1.setBackground(Color.LIGHT_GRAY);
		jpanel_2.setBackground(Color.LIGHT_GRAY);
		jframe_1.add(jpanel_1);
		jframe_1.add(jpanel_2);
		

		//�����ı������ܱ༭
		j_1.setEditable(false);
		//��j1��Ϊ�ɹ������sp����ʾ����
		JScrollPane sp=new JScrollPane(j_1);
		sp.setSize(810,600);
		StudentUI.jpanel_2.add(sp);
		
//		���ھ���
		jframe_1.setLocationRelativeTo(null);
//		������ʾ
		jframe_1.setVisible(true);
		
//		ע�� ���ѧ�� ��ť�ļ���
		item_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				���÷���
				StudentUI.init_2();
			}
		});
//		ע�� �޸�ѧ�� ��ť�ļ���
		item_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_3();
			}
		});
//		ע�� ɾ��ѧ�� ��ť�ļ���
		item_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_4();
			}
		});
//		ע�� ��ѯѧ�� ��ť�ļ���
		item_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_5();
			}
		});
//		�� �ֽܷ���ť �ļ���
		item_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentUI.init_7();
			}
		});
//		�� ĳ�ƽ���ť �ļ���
		item_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String [] options = {"�ߵ���ѧ","���Դ���","��ɢ��ѧ","Ӣ��"};
				String s=(String) JOptionPane.showInputDialog(null,"���������ѡ�",
						"��ʾ",JOptionPane.QUESTION_MESSAGE,null,options,null);
				if(s!=null) {
					StudentUI.init_8(s);
				}
			}
		});
//		���� ��ť�ļ���
		item_7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MysqlOperation.saveStudent();
			}
		});
	}
	
//	���ѧ������
	public static void init_2() {
//		������
		StudentUI.jpanel_1.removeAll();
//		ˢ�����
		StudentUI.jpanel_1.updateUI();
//		����
//		Font font=new Font("����",Font.PLAIN,15);

//		��ǩ
		JLabel jlabel[]=new JLabel[studentInfo.length];
//		�ı���
		JTextField jtextfield[]=new JTextField[studentInfo.length];
//		ʵ����
		for(int i=0;i<studentInfo.length;i++) {
			jlabel[i]=new JLabel(studentInfo[i]);
//			jlabel[i].setFont(font);
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(12);
			StudentUI.jpanel_1.add(jtextfield[i]);
		}
//		�����ı���Ĭ��ֵ(�ܷ��Զ�����,���ɱ༭)
		jtextfield[0].setText("ѧ���Զ�����");
		jtextfield[0].setEditable(false);
		jtextfield[studentInfo.length-1].setText("�Զ����㣬��������");
		jtextfield[studentInfo.length-1].setEditable(false);
//		��ť
		JButton jbutton_1=new JButton("�ύ");
		JButton jbutton_2=new JButton("���");
//		���밴ť
		StudentUI.jpanel_1.add(jbutton_1);
		StudentUI.jpanel_1.add(jbutton_2);
//		ע�����
		StudentListen e_1=new StudentListen(jbutton_1, jbutton_2, jtextfield);
		jbutton_1.addActionListener(e_1);
		jbutton_2.addActionListener(e_1);
		
	}
	
//	�޸� ѧ������
	public static void init_3() {
//		������
		StudentUI.jpanel_1.removeAll();
//		ˢ�����
		StudentUI.jpanel_1.updateUI();
//		����
		Font font=new Font("����",Font.PLAIN,15);
		
//		��ǩ
		JLabel jlabel[]=new JLabel[studentInfo.length];
//		�ı���
		JTextField jtextfield[]=new JTextField[studentInfo.length];
//		��ť
		JButton jbutton_3=new JButton("�޸�");
		JButton jbutton_4=new JButton("���");
		jbutton_3.setFont(font);
		jbutton_4.setFont(font);
		
//		ʵ����
		for(int i=0,j=0;i<studentInfo.length;i++,j++) {
			jlabel[i]=new JLabel(studentInfo[i]);
//			jlabel[i].setFont(font);
			StudentUI.jpanel_1.add(jlabel[i]);
			jtextfield[i]=new JTextField(12);
			StudentUI.jpanel_1.add(jtextfield[i]);
//			j������Ӵ�����ֹ�ظ����
			if(j==0) {
				JLabel L_1=new JLabel("ѧ�Ų��ܱ��޸�!");
				JLabel L_2=new JLabel("��������������µ���Ϣ��");
				StudentUI.jpanel_1.add(L_1);
				StudentUI.jpanel_1.add(L_2);
			}
		}
//		�����ı���Ĭ��ֵ(�ܷ��Զ�����,���ɱ༭)
		jtextfield[studentInfo.length-1].setText("�Զ����㣬��������");
		jtextfield[studentInfo.length-1].setEditable(false);
		
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
//		ˢ�����
		StudentUI.jpanel_1.updateUI();
//		����
		Font font=new Font("����",Font.PLAIN,15);
//		��ǩ
		JLabel jlabel_1=new JLabel(" ѧ       �ţ�");
//		�ı���
		JTextField jtextfield_1=new JTextField(10);
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
//		ˢ�����
		StudentUI.jpanel_1.updateUI();
//		����
		Font font=new Font("����",Font.PLAIN,15);
		
//		��ǩ
		JLabel jlabel[]=new JLabel[studentInfo.length];
//		�ı���
		JTextField jtextfield[]=new JTextField[studentInfo.length];
//		��ť
		JButton jbutton_81=new JButton("ѧ�Ų�ѯ");
		JButton jbutton_82=new JButton("������ѯ");
		JButton jbutton_9=new JButton("���");
		JButton jbutton_10=new JButton("���ȫ��");
		jbutton_81.setFont(font);
		jbutton_82.setFont(font);
		jbutton_9.setFont(font);
		jbutton_10.setFont(font);
		JLabel add=new JLabel("��      ����");
		JTextField ad=new JTextField(12);;
//		ʵ����
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
			
			
//			j���ư�ť����Ӵ�����ֹ�ظ����
			if(j==1) {
				//StudentUI.jpanel_1.add(jbutton_8);
				StudentUI.jpanel_1.add(jbutton_9);
			}
//			�����ı��򲻿ɱ༭
			
			if(i==0)jtextfield[i].setEditable(true);
			else jtextfield[i].setEditable(false);
			
		}
		StudentUI.jpanel_1.add(jbutton_10);
		StudentUI.j_1.setText("");
//		ע�����
		StudentListen_3 e_3=new StudentListen_3(jbutton_81,jbutton_82, jbutton_9,jbutton_10, jtextfield,ad,j_1);
		jbutton_81.addActionListener(e_3);
		jbutton_82.addActionListener(e_3);
		jbutton_9.addActionListener(e_3);
		jbutton_10.addActionListener(e_3);
	}
	
//	���ȫ��ѧ������
	public static void init_6() {
		
		//��������
		JFrame j=new JFrame("����ѧ����Ϣ");
		j.setSize(615,335);
		j.setLayout(null);
		//���ڲ��ܵ���
		j.setResizable(false);
		//�����ı���������ʾ����ѧ����Ϣ
		JTextArea j_2=new JTextArea("�ı������Ի���������\n\n�رյ�ǰ���ڣ��ٴε����ѯ����ѧ����ť����ˢ�£�����\n\n\n");
		//�����ı������ܱ༭
		j_2.setEditable(false);
		//��j1��Ϊ�ɹ������sp����ʾ����
		JScrollPane sp=new JScrollPane(j_2);
		sp.setLocation(0,0);
		sp.setSize(600,300);
		j.add(sp);
//		�������ݿⷽ����ʾȫ��ѧ��
		MysqlOperation.showStudent(j_2);
//		������ʾ
		j.setLocationRelativeTo(null);
//		��ʾ����
		j.setVisible(true);	
	}
	
//	���ֽܷ����������
	public static void init_7() {
//		����ı���
		StudentUI.j_1.setText("");
//		�������ݿⷽ��
		MysqlOperation.showTotal(j_1);
	}
	
//	��ĳ�ƽ����������
	public static void init_8(String str) {
//		����ı���
		StudentUI.j_1.setText("");
		
		if(str.equals("�ߵ���ѧ")) {
			MysqlOperation.showsomeone(j_1, "advanced_Mathematics");
		}else if(str.equals("���Դ���")) {
			MysqlOperation.showsomeone(j_1, "linear_Algebra");
		}else if(str.equals("��ɢ��ѧ")) {
			MysqlOperation.showsomeone(j_1, "discrete_Mathematics");
		}else if(str.equals("Ӣ��")) {
			MysqlOperation.showsomeone(j_1, "English");
		}
	}
	
}
