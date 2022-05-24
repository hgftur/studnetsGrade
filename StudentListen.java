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
	
//	点击了 添加学生的提交 按钮
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		点击了提交按钮
		if(e.getSource()==jbutton_1) {
			MysqlOperation.addStudent(jbutton_1, jtextfield);
				
		}else {
//			总分的默认值不清空
			for(int i=0;i<jtextfield.length-1;i++) {
				if(i==0)jtextfield[0].setText("学号自动生成");
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
	
//	点击了 查询学生的查询 按钮
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		点击了查询按钮
		if(e.getSource()==jbutton_81) {
//			学号不能为空
			if(!jtextfield[0].getText().equals("")) {
//				调用数据库方法，查询学生
				MysqlOperation.selectStudentid(jbutton_81,jtextfield);
			}else {
//				提示学号为空
				JOptionPane.showMessageDialog(
	              jbutton_81,
	              "学号和姓名不能均为空！\n查询学生信息失败！",
	              "警告",
	              JOptionPane.WARNING_MESSAGE
	      );
			}
		}else if(e.getSource()==jbutton_82) {
			
			if(jtextfield[0].getText().equals("")||!ad.getText().equals("")) {
//				调用数据库方法，查询学生
				j_3.setText("");
				for(int i=1;i<jtextfield.length;i++) {
					jtextfield[i].setText("");
				}
				MysqlOperation.selectStudentname(j_3,ad,jtextfield);
			}else {
//				提示学号为空
				JOptionPane.showMessageDialog(
	              jbutton_81,
	              "学号和学生姓名不能均为空！\n查询学生信息失败！",
	              "警告",
	              JOptionPane.WARNING_MESSAGE
	      );
			}
			
		}else	if(e.getSource()==jbutton_9) {
		
//			点击了清空按钮
//			清空文本框
			j_3.setText("");
			ad.setText("");
			jtextfield[0].setText("");
		}else {
//				点击了输出全部学生按钮
			StudentUI.init_6();
		}
	}

}

