package gui;

import java.awt.event.*;
import javax.swing.*;

import operation.MysqlOperation;

public class StudentListen implements ActionListener {

	JTable table;
	public StudentListen(JTable table) {
		this.table=table;
	}
	
//	点击了 添加学生的提交 按钮
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
//		点击了 修改 按钮
		if(e.getSource()==jbutton_3) {
//			学号不能为空
			if(!jtextfield[0].getText().equals("")) {
//				调用数据库方法,修改学生
				MysqlOperation.updateStudent(jbutton_3,jtextfield);
			}else {
//				提示学号为空
				JOptionPane.showMessageDialog(
	                    jbutton_3,
	                    "学号不能为空！\n修改学生信息失败！",
	                    "警告",
	                    JOptionPane.WARNING_MESSAGE
	            );
			}
		}else {
//			点击了清空按钮
//			清空文本框
			for(int i=0;i<jtextfield.length;i++) {
				jtextfield[i].setText("");
				}
			}
	}
}

//点击了删除学生的删除按钮
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
//		点击了删除按钮
		if(e.getSource()==jbutton_5) {
//			学号不能为空
			if(!jtextfield_1.getText().equals("")) {
//				调用数据库方法，删除学生
				MysqlOperation.deleteStudent(jbutton_5, jtextfield_1);
			}else {
//				提示学号为空
				JOptionPane.showMessageDialog(
	                jbutton_5,
	                "学号不能为空！\n删除学生信息失败！",
	                "警告",
	                JOptionPane.WARNING_MESSAGE
	        );
			}
		}else {
//			点击了清空按钮
//			清空文本框
				jtextfield_1.setText("");
			}
		}
}


// 查询学生的 查询按钮 的监听
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
	
//	点击了 查询学生的查询 按钮
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		点击了查询按钮
		if(e.getSource()==jbutton_81) {
//			学号不能为空
			
			if(!id.getText().equals("")&&ad.getText().equals("")) {
//				调用数据库方法，查询学生
				MysqlOperation.selectStudentid(id,jtextfield);
			}else if(!ad.getText().equals("")&&id.getText().equals("")) {
//				调用数据库方法，查询学生
			
				MysqlOperation.selectStudentname(ad,jtextfield,jpanel_2);
				
			}else if(ad.getText().equals("")&&id.getText().equals("")){
//				提示学号为空
				JOptionPane.showMessageDialog(
	              null,
	              "学生姓名不能为空！\n查询学生信息失败！",
	              "警告",
	              JOptionPane.WARNING_MESSAGE
				);
			}else {
				JOptionPane.showMessageDialog(
			         null,
			         "学生姓名均输入！\n查询学生信息失败！",
			         "警告",
			        JOptionPane.WARNING_MESSAGE
				);
			}
		}else if(e.getSource()==jbutton_9) {
		
//			点击了清空按钮
//			清空文本框
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

