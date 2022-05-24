package studentgrades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class StudentListen implements ActionListener {
	JButton jbutton_1;
	JButton jbutton_2;
	JTextField jtextfield[];
	public StudentListen(JButton jbutton_1,JButton jbutton_2,JTextField jtextfield[]) {
		this.jbutton_1=jbutton_1;
		this.jbutton_2=jbutton_2;
		this.jtextfield=jtextfield;
	}
	
//	����� ���ѧ�����ύ ��ť
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		������ύ��ť
		if(e.getSource()==jbutton_1) {
			MysqlOperation.addStudent(jbutton_1, jtextfield);
				
		}else {
//			�ֵܷ�Ĭ��ֵ�����
			for(int i=0;i<jtextfield.length-1;i++) {
				if(i==0)jtextfield[0].setText("ѧ���Զ�����");
				else jtextfield[i].setText("");
			}
		}
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
	JButton jbutton_10;
	JTextField jtextfield[];
	JTextField ad;
	JTextArea j_3;
	
	public StudentListen_3(JButton jbutton_81,JButton jbutton_82,JButton jbutton_9,JButton jbutton_10,JTextField jtextfield[],JTextField ad,JTextArea j_3) {
		this.jbutton_81=jbutton_81;
		this.jbutton_82=jbutton_82;
		this.jbutton_9=jbutton_9;
		this.jbutton_10=jbutton_10;
		this.jtextfield=jtextfield;
		this.ad=ad;
		this.j_3=j_3;
	}
	
//	����� ��ѯѧ���Ĳ�ѯ ��ť
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		����˲�ѯ��ť
		if(e.getSource()==jbutton_81) {
//			ѧ�Ų���Ϊ��
			if(!jtextfield[0].getText().equals("")) {
//				�������ݿⷽ������ѯѧ��
				MysqlOperation.selectStudentid(jbutton_81,jtextfield);
			}else {
//				��ʾѧ��Ϊ��
				JOptionPane.showMessageDialog(
	              jbutton_81,
	              "ѧ�ź��������ܾ�Ϊ�գ�\n��ѯѧ����Ϣʧ�ܣ�",
	              "����",
	              JOptionPane.WARNING_MESSAGE
	      );
			}
		}else if(e.getSource()==jbutton_82) {
			
			if(jtextfield[0].getText().equals("")||!ad.getText().equals("")) {
//				�������ݿⷽ������ѯѧ��
				j_3.setText("");
				for(int i=1;i<jtextfield.length;i++) {
					jtextfield[i].setText("");
				}
				MysqlOperation.selectStudentname(j_3,ad,jtextfield);
			}else {
//				��ʾѧ��Ϊ��
				JOptionPane.showMessageDialog(
	              jbutton_81,
	              "ѧ�ź�ѧ���������ܾ�Ϊ�գ�\n��ѯѧ����Ϣʧ�ܣ�",
	              "����",
	              JOptionPane.WARNING_MESSAGE
	      );
			}
			
		}else	if(e.getSource()==jbutton_9) {
		
//			�������հ�ť
//			����ı���
			j_3.setText("");
			ad.setText("");
			jtextfield[0].setText("");
		}else {
//				��������ȫ��ѧ����ť
			StudentUI.init_6();
		}
	}

}

