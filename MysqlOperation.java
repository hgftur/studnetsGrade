package studentgrades;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.*;
//import com.mysql.jdbc.Statement;
public class MysqlOperation {
//	数据库的用户名和密码（从登录界面的文本框中获得）
	public static String user=null;
	public static String password=null;
	
	//数据库连接方法，自动创建数据库和表
		public static Connection getConnection() {
			Connection con=null;
			Statement stat=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");	
//				"jdbc:mysql://localhost:3306/mydatabase?characterEncoding=utf8&useSSL=false"
				String url_1="jdbc:mysql://localhost:3306?characterEncoding=utf8&useSSL=false";
				String url_2="jdbc:mysql://localhost:3306/mydatabase?characterEncoding=utf8&useSSL=false";
				user="root";
				password="111111";
				
//				建立连接
				con=DriverManager.getConnection(url_1,user,password);
//				创建数据库mydatabase
				stat=(Statement)con.createStatement();
				String sql_1="create database if not exists mydatabase;";
//				执行语句创建数据库mydatabase
				stat.executeUpdate(sql_1);
				
				con=DriverManager.getConnection(url_2,user,password);
				String sql_3="create table if not exists teachers(编号 int primart key auto_increment,"
				             +"password varchar(20),name varchar(10),subject varchar(20));";
				stat=(Statement)con.createStatement();
				stat.executeUpdate(sql_3);
				
				
//				建立新的连接用于在mydatabase下创建studentInformation表
				con=DriverManager.getConnection(url_2,user,password);
				String sql_2="create table if not exists studentInformation( sno int(20) primary key auto_increment, "
						+ "sname varchar(20), advanced_Mathematics int, linear_Algebra int, "
						+ "discrete_Mathematics int,English int,Total int );";
				stat=(Statement)con.createStatement();
//				执行语句创建studentInformation表
				stat.executeUpdate(sql_2);
				
			}catch (Exception e) {
//				
			} 
			return con;
		}
		
		public static boolean teacherLogin(JTextField jtext,JPasswordField jpassword) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			ResultSet re;
			try {
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				char[] pw=jpassword.getPassword();
				sql="select 编号,password from teachers where 编号="+jtext.getText()+" and password="+String.valueOf(pw)+";";
				re=stat.executeQuery(sql);
				if(re.next()==true) {
					return true;
					//JOptionPane.showMessageDialog(null, "登录成功欢迎回来","提示",JOptionPane.WARNING_MESSAGE);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return false;
		}
		
		public static void teacherRegister(JTextField jtext[],JPasswordField pw) {
			Connection con=null;
			Statement stat=null;
			Statement stat2=null;
			String sql=null;
			
			try {
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				stat2=(Statement)con.createStatement();
				char[] ch=pw.getPassword();
				
				sql="insert into teachers(password,name,subject) values('"+String.valueOf(ch)+"','"+jtext[1].getText()
					+"','"+jtext[2].getText()+"');";
				stat.executeUpdate(sql);
				JOptionPane.showMessageDialog(
                        null,
                        "注册成功,请返回登录！",
                        "提示",
                        JOptionPane.WARNING_MESSAGE
                );
				String str="select max(编号) from teachers";
				ResultSet re=stat2.executeQuery(str);
				re.next();
				jtext[0].setText(String.valueOf(re.getInt(1)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
//		添加学生的数据库方法
		public static void addStudent(JButton jbutton_1,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null,stat2=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				stat2=(Statement)con.createStatement();
//				计算总分
				int sum=0;
				for(int i=2;i<StudentUI.studentInfo.length-1;i++) {
					sum += Integer.parseInt(jtextfield[i].getText());
				}
				sql="insert into studentInformation(sname,advanced_Mathematics,linear_Algebra,"
				     +"discrete_Mathematics,English,Total) values('"
					 +jtextfield[1].getText()+"',"+Integer.parseInt(jtextfield[2].getText())+","
					 +Integer.parseInt(jtextfield[3].getText())+","+Integer.parseInt(jtextfield[4].getText())+","
					 +Integer.parseInt(jtextfield[5].getText())+","+sum+")";
//				执行语句
				stat.executeUpdate(sql);
//				提示成功
				JOptionPane.showMessageDialog(
                        jbutton_1,
                        "添加学生信息成功！",
                        "提示",
                        JOptionPane.INFORMATION_MESSAGE
                );
				String str="select max(sno) from studentInformation";
				ResultSet re=stat2.executeQuery(str);
				re.next();
				jtextfield[0].setText(String.valueOf(re.getInt(1)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				处理异常情况，如学号重复，不打印异常
				//e.printStackTrace();
				
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
//		修改学生的数据库方法
		public static void updateStudent(JButton jbutton_1,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				//判断是否为空,空值不用修改
				if(!jtextfield[1].getText().equals("")) {
					sql="update studentInformation set sname='"+jtextfield[1].getText()
							+"'"+" where sno='"+jtextfield[0].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[2].getText().equals("")) {
					sql="update studentInformation set advanced_Mathematics="+Integer.parseInt(jtextfield[2].getText())
							+" where sno='"+jtextfield[0].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[3].getText().equals("")) {
					sql="update studentInformation set linear_Algebra="+jtextfield[3].getText()
							+" where sno='"+jtextfield[0].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[4].getText().equals("")) {
					sql="update studentInformation set discrete_Mathematics="+jtextfield[4].getText()
							+" where sno='"+jtextfield[0].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[5].getText().equals("")) {
					sql="update studentInformation set English="+jtextfield[5].getText()
							+" where sno='"+jtextfield[0].getText()+"';";
					//执行语句
					stat.executeUpdate(sql);
				}
//				计算总分
				sql="select * from studentInformation where sno='"+jtextfield[0].getText()+"';";
//				执行语句
				rs = stat.executeQuery(sql);
				int sum=0;
				rs.next();
				sum = Integer.parseInt(rs.getString(3))+Integer.parseInt(rs.getString(4))+Integer.parseInt(rs.getString(5))
				+Integer.parseInt(rs.getString(6));
//				更新总分
				sql="update studentInformation set Total="+sum
						+" where sno='"+jtextfield[0].getText()+"';";
				//执行语句
				stat.executeUpdate(sql);
//				提示成功
				JOptionPane.showMessageDialog(
                        jbutton_1,
                        "修改学生信息成功！",
                        "提示",
                        JOptionPane.INFORMATION_MESSAGE
                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
//		删除学生的数据库方法
		public static void deleteStudent(JButton jbutton_1,JTextField jtextfield_1) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="delete from studentInformation where sno='"+jtextfield_1.getText()+"';";
//				执行语句
				stat.executeUpdate(sql);
//				提示成功
				JOptionPane.showMessageDialog(
                        jbutton_1,
                        "删除学生信息成功！",
                        "提示",
                        JOptionPane.INFORMATION_MESSAGE
                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
//		查询学生 的数据库方法
		public static void selectStudentid(JButton jbutton_81,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation where sno='"+jtextfield[0].getText()+"';";
//				执行语句
				rs = stat.executeQuery(sql);
//				将结果输出到文本框中
				rs.next();
				jtextfield[1].setText(rs.getString(2));
				jtextfield[2].setText(rs.getString(3));
				jtextfield[3].setText(rs.getString(4));
				jtextfield[4].setText(rs.getString(5));
				jtextfield[5].setText(rs.getString(6));
				jtextfield[6].setText(rs.getString(7));
//				提示成功
//				JOptionPane.showMessageDialog(
//                        jbutton_8,
//                        "查询学生信息成功！",
//                        "提示",
//                        JOptionPane.INFORMATION_MESSAGE
//                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				未查询到当前学生，出现异常，处理异常但不打印异常
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        jbutton_81,
                        "未查询到当前学生信息！",
                        "警告",
                        JOptionPane.WARNING_MESSAGE
                );
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public static void selectStudentname(JTextArea j_3,JTextField ad,JTextField jtext[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			int cnt=0;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				sql="select * from studentInformation where sname like '%"+ad.getText()+"%';";
//				执行语句
				rs = stat.executeQuery(sql);
				while(rs.next()==true) {
					cnt++;
				}
				rs.beforeFirst();
				if(cnt==0) {
					JOptionPane.showMessageDialog(
                        null,
                        "未查询到当前学生信息！",
                        "警告",
                        JOptionPane.WARNING_MESSAGE
					);
				}else if(cnt==1) {
					rs.next();
					jtext[1].setText(rs.getString(2));
					jtext[2].setText(rs.getString(3));
					jtext[3].setText(rs.getString(4));
					jtext[4].setText(rs.getString(5));
					jtext[5].setText(rs.getString(6));
					jtext[6].setText(rs.getString(7));
				}else {
					while(rs.next()==true) {
						j_3.append(StudentUI.studentInfo[0]+rs.getString(1)+"  ||  ");
						j_3.append(StudentUI.studentInfo[1]+rs.getString(2)+"  ||  ");
						j_3.append(StudentUI.studentInfo[2]+rs.getString(3)+"  ||  ");
						j_3.append(StudentUI.studentInfo[3]+rs.getString(4)+"  ||  ");
						j_3.append(StudentUI.studentInfo[4]+rs.getString(5)+"  ||  ");
						j_3.append(StudentUI.studentInfo[5]+rs.getString(6)+"  ||  ");
						j_3.append(StudentUI.studentInfo[6]+rs.getString(7)+"\n");
					}
				}
//				将结果输出到文本框中
				
				
				//jtextfield[1].setText(rs.getString(2));
//				提示成功
//				JOptionPane.showMessageDialog(
//                        jbutton_8,
//                        "查询学生信息成功！",
//                        "提示",
//                        JOptionPane.INFORMATION_MESSAGE
//                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				未查询到当前学生，出现异常，处理异常但不打印异常
				e.printStackTrace();
				
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
//		输出全部学生 的数据库方法
		public static void showStudent(JTextArea j_2) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation ;";
//				执行语句
				rs = stat.executeQuery(sql);
				
//				输出之前先清空文本区
				StudentUI.j_1.setText("");
//				将结果输出到文本区中
				while(rs.next()) {
					j_2.append(StudentUI.studentInfo[0]+rs.getString(1)+"  ||  ");
					j_2.append(StudentUI.studentInfo[1]+rs.getString(2)+"  ||  ");
					j_2.append(StudentUI.studentInfo[2]+rs.getString(3)+"  ||  ");
					j_2.append(StudentUI.studentInfo[3]+rs.getString(4)+"  ||  ");
					j_2.append(StudentUI.studentInfo[4]+rs.getString(5)+"  ||  ");
					j_2.append(StudentUI.studentInfo[5]+rs.getString(6)+"  ||  ");
					j_2.append(StudentUI.studentInfo[6]+rs.getString(7)+"\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
//		按总分降序排序
		public static void showTotal(JTextArea j_1) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation ORDER BY Total DESC;";
//				执行语句
				rs = stat.executeQuery(sql);
//				输出之前先清空文本区
				StudentUI.j_1.setText("");
//				将结果输出到文本区中
				while(rs.next()) {
					j_1.append(StudentUI.studentInfo[0]+rs.getString(1)+"  ||  ");
					j_1.append(StudentUI.studentInfo[1]+rs.getString(2)+"  ||  ");
					j_1.append(StudentUI.studentInfo[2]+rs.getString(3)+"  ||  ");
					j_1.append(StudentUI.studentInfo[3]+rs.getString(4)+"  ||  ");
					j_1.append(StudentUI.studentInfo[4]+rs.getString(5)+"  ||  ");
					j_1.append(StudentUI.studentInfo[5]+rs.getString(6)+"  ||  ");
					j_1.append(StudentUI.studentInfo[6]+rs.getString(7)+"\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		按某科目降序排序
		public static void showsomeone(JTextArea j_1,String str) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation ORDER BY "+str+ " DESC;";
//				执行语句
				rs = stat.executeQuery(sql);
//				输出之前先清空文本区
				StudentUI.j_1.setText("");
//				将结果输出到文本区中
				while(rs.next()) {
					j_1.append(StudentUI.studentInfo[0]+rs.getString(1)+"  ||  ");
					j_1.append(StudentUI.studentInfo[1]+rs.getString(2)+"  ||  ");
					j_1.append(StudentUI.studentInfo[2]+rs.getString(3)+"  ||  ");
					j_1.append(StudentUI.studentInfo[3]+rs.getString(4)+"  ||  ");
					j_1.append(StudentUI.studentInfo[4]+rs.getString(5)+"  ||  ");
					j_1.append(StudentUI.studentInfo[5]+rs.getString(6)+"  ||  ");
					j_1.append(StudentUI.studentInfo[6]+rs.getString(7)+"\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)
						rs.close();
					if(stat!=null)
						stat.close();
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
//		保存学生信息
		public static void saveStudent() {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			//建立输出
			FileOutputStream out=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation ;";
//				执行语句
				rs = stat.executeQuery(sql);
				//设置路径文件名,true为 添加方式
				out =new FileOutputStream("D:\\studentMessage_3.txt",true);
				String student=null;
				String explain="本次保存信息如下：\n";
				byte buf[] =explain.getBytes();
				out.write(buf);
				while(rs.next()) {
					//创建缓冲区,写入数据
					byte buffer[]=null;
					student =StudentUI.studentInfo[0]+ rs.getString(1)+"  ||  "+StudentUI.studentInfo[1]+rs.getString(2)
					+"  ||  "+StudentUI.studentInfo[2]+rs.getString(3)+"  ||  "+StudentUI.studentInfo[3]+rs.getString(4)
					+"  ||  "+StudentUI.studentInfo[4]+rs.getString(5)+"  ||  "+StudentUI.studentInfo[6]+rs.getString(6)
					+"\n";
					buffer =student.getBytes();
					out.write(buffer);
				}
				JOptionPane.showMessageDialog(null, "保存成功！！！\n保存路径为：D:\\\\studentMessage_3.txt",
						"提示",JOptionPane.PLAIN_MESSAGE);
			}catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(out!=null) 
					out.close();
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
}

