package gui;

import java.awt.event.*;
import javax.swing.*;

import operation.MysqlOperation;

public class StudentListen implements ActionListener {

	JTable table;
	public StudentListen(JTable table) {
		this.table=table;
	}
	
//	����� ���ѧ�����ύ ��ť
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MysqlOperation.addStudent(table);
		
	}
}
class StudentListen_1 implements ActionListener{
	
	JButton jbutton_3;
	JButton jbutton_4;
	JTextField jtextfield[];
	
	public StudentListen_1(JButton jbutton_3,JButton jbutton_4,JTextField jtextfield[]) {
		this.jbutton_3=jbutton_3;
		this.jbutton_4=jbutton_4;
		this.jtextfield=jtextfield;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		����� �޸� ��ť
		if(e.getSource()==jbutton_3) {
//			ѧ�Ų���Ϊ��
			if(!jtextfield[0].getText().equals("")) {
//				�������ݿⷽ��,�޸�ѧ��
				MysqlOperation.updateStudent(jbutton_3,jtextfield);
			}else {
//				��ʾѧ��Ϊ��
				JOptionPane.showMessageDialog(
	                    jbutton_3,
	                    "ѧ�Ų���Ϊ�գ�\n�޸�ѧ����Ϣʧ�ܣ�",
	                    "����",
	                    JOptionPane.WARNING_MESSAGE
	            );
			}
		}else {
//			�������հ�ť
//			����ı���
			for(int i=0;i<jtextfield.length;i++) {
				jtextfield[i].setText("");
				}
			}
	}
}

//�����ɾ��ѧ����ɾ����ť
class StudentListen_2 implements ActionListener{
	
	JButton jbutton_5;
	JButton jbutton_6;
	JTextField jtextfield_1;
	
	public StudentListen_2(JButton jbutton_5,JButton jbutton_6,JTextField jtextfield_1) {
		this.jbutton_5=jbutton_5;
		this.jbutton_6=jbutton_6;
		this.jtextfield_1=jtextfield_1;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		�����ɾ����ť
		if(e.getSource()==jbutton_5) {
//			ѧ�Ų���Ϊ��
			if(!jtextfield_1.getText().equals("")) {
//				�������ݿⷽ����ɾ��ѧ��
				MysqlOperation.deleteStudent(jbutton_5, jtextfield_1);
			}else {
//				��ʾѧ��Ϊ��
				JOptionPane.showMessageDialog(
	                jbutton_5,
	                "ѧ�Ų���Ϊ�գ�\nɾ��ѧ����Ϣʧ�ܣ�",
	                "����",
	                JOptionPane.WARNING_MESSAGE
	        );
			}
		}else {
//			�������հ�ť
//			����ı���
				jtextfield_1.setText("");
			}
		}
}


// ��ѯѧ���� ��ѯ��ť �ļ���
class StudentListen_3 implements ActionListener{
	
	JButton jbutton_81;
	JButton jbutton_82;
	JButton jbutton_9;
	JTextField jtextfield[];
	JTextField ad;
	JTextField id;
	JPanel jpanel_2;
	
	public StudentListen_3(JButton jbutton_81,JButton jbutton_9,JTextField id,
			               JTextField jtextfield[],JTextField ad,JPanel jpanel_2) {
		this.jbutton_81=jbutton_81;
		this.jbutton_9=jbutton_9;
		this.jtextfield=jtextfield;
		this.ad=ad;
		this.jpanel_2=jpanel_2;
		this.id=id;
	}
	
//	����� ��ѯѧ���Ĳ�ѯ ��ť
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		����˲�ѯ��ť
		if(e.getSource()==jbutton_81) {
//			ѧ�Ų���Ϊ��
			
			if(!id.getText().equals("")&&ad.getText().equals("")) {
//				�������ݿⷽ������ѯѧ��
				MysqlOperation.selectStudentid(id,jtextfield);
			}else if(!ad.getText().equals("")&&id.getText().equals("")) {
//				�������ݿⷽ������ѯѧ��
			
				MysqlOperation.selectStudentname(ad,jtextfield,jpanel_2);
				
			}else if(ad.getText().equals("")&&id.getText().equals("")){
//				��ʾѧ��Ϊ��
				JOptionPane.showMessageDialog(
	              null,
	              "ѧ����������Ϊ�գ�\n��ѯѧ����Ϣʧ�ܣ�",
	              "����",
	              JOptionPane.WARNING_MESSAGE
				);
			}else {
				JOptionPane.showMessageDialog(
			         null,
			         "ѧ�����������룡\n��ѯѧ����Ϣʧ�ܣ�",
			         "����",
			        JOptionPane.WARNING_MESSAGE
				);
			}
		}else if(e.getSource()==jbutton_9) {
		
//			�������հ�ť
//			����ı���
			jpanel_2.removeAll();
			jpanel_2.updateUI();
			ad.setText("");
			id.setText("");
			for(int i=0;i<jtextfield.length;i++) {
				if(i!=3&&i!=2) {
					jtextfield[i].setText("");
				}
				
			}
		}
	}

}

