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
//	���ݿ���û��������루�ӵ�¼������ı����л�ã�
	public static String user=null;
	public static String password=null;
	
	//���ݿ����ӷ������Զ��������ݿ�ͱ�
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
				
//				��������
				con=DriverManager.getConnection(url_1,user,password);
//				�������ݿ�mydatabase
				stat=(Statement)con.createStatement();
				String sql_1="create database if not exists mydatabase;";
//				ִ����䴴�����ݿ�mydatabase
				stat.executeUpdate(sql_1);
				
				con=DriverManager.getConnection(url_2,user,password);
				String sql_3="create table if not exists teachers(��� int primart key auto_increment,"
				             +"password varchar(20),name varchar(10),subject varchar(20));";
				stat=(Statement)con.createStatement();
				stat.executeUpdate(sql_3);
				
				
//				�����µ�����������mydatabase�´���studentInformation��
				con=DriverManager.getConnection(url_2,user,password);
				String sql_2="create table if not exists studentInformation( sno int(20) primary key auto_increment, "
						+ "sname varchar(20), advanced_Mathematics int, linear_Algebra int, "
						+ "discrete_Mathematics int,English int,Total int );";
				stat=(Statement)con.createStatement();
//				ִ����䴴��studentInformation��
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
				sql="select ���,password from teachers where ���="+jtext.getText()+" and password="+String.valueOf(pw)+";";
				re=stat.executeQuery(sql);
				if(re.next()==true) {
					return true;
					//JOptionPane.showMessageDialog(null, "��¼�ɹ���ӭ����","��ʾ",JOptionPane.WARNING_MESSAGE);
					
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
                        "ע��ɹ�,�뷵�ص�¼��",
                        "��ʾ",
                        JOptionPane.WARNING_MESSAGE
                );
				String str="select max(���) from teachers";
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
		
//		���ѧ�������ݿⷽ��
		public static void addStudent(JButton jbutton_1,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null,stat2=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				stat2=(Statement)con.createStatement();
//				�����ܷ�
				int sum=0;
				for(int i=2;i<StudentUI.studentInfo.length-1;i++) {
					sum += Integer.parseInt(jtextfield[i].getText());
				}
				sql="insert into studentInformation(sname,advanced_Mathematics,linear_Algebra,"
				     +"discrete_Mathematics,English,Total) values('"
					 +jtextfield[1].getText()+"',"+Integer.parseInt(jtextfield[2].getText())+","
					 +Integer.parseInt(jtextfield[3].getText())+","+Integer.parseInt(jtextfield[4].getText())+","
					 +Integer.parseInt(jtextfield[5].getText())+","+sum+")";
//				ִ�����
				stat.executeUpdate(sql);
//				��ʾ�ɹ�
				JOptionPane.showMessageDialog(
                        jbutton_1,
                        "���ѧ����Ϣ�ɹ���",
                        "��ʾ",
                        JOptionPane.INFORMATION_MESSAGE
                );
				String str="select max(sno) from studentInformation";
				ResultSet re=stat2.executeQuery(str);
				re.next();
				jtextfield[0].setText(String.valueOf(re.getInt(1)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				�����쳣�������ѧ���ظ�������ӡ�쳣
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
		
//		�޸�ѧ�������ݿⷽ��
		public static void updateStudent(JButton jbutton_1,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				//�ж��Ƿ�Ϊ��,��ֵ�����޸�
				if(!jtextfield[1].getText().equals("")) {
					sql="update studentInformation set sname='"+jtextfield[1].getText()
							+"'"+" where sno='"+jtextfield[0].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[2].getText().equals("")) {
					sql="update studentInformation set advanced_Mathematics="+Integer.parseInt(jtextfield[2].getText())
							+" where sno='"+jtextfield[0].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[3].getText().equals("")) {
					sql="update studentInformation set linear_Algebra="+jtextfield[3].getText()
							+" where sno='"+jtextfield[0].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[4].getText().equals("")) {
					sql="update studentInformation set discrete_Mathematics="+jtextfield[4].getText()
							+" where sno='"+jtextfield[0].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[5].getText().equals("")) {
					sql="update studentInformation set English="+jtextfield[5].getText()
							+" where sno='"+jtextfield[0].getText()+"';";
					//ִ�����
					stat.executeUpdate(sql);
				}
//				�����ܷ�
				sql="select * from studentInformation where sno='"+jtextfield[0].getText()+"';";
//				ִ�����
				rs = stat.executeQuery(sql);
				int sum=0;
				rs.next();
				sum = Integer.parseInt(rs.getString(3))+Integer.parseInt(rs.getString(4))+Integer.parseInt(rs.getString(5))
				+Integer.parseInt(rs.getString(6));
//				�����ܷ�
				sql="update studentInformation set Total="+sum
						+" where sno='"+jtextfield[0].getText()+"';";
				//ִ�����
				stat.executeUpdate(sql);
//				��ʾ�ɹ�
				JOptionPane.showMessageDialog(
                        jbutton_1,
                        "�޸�ѧ����Ϣ�ɹ���",
                        "��ʾ",
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
		
//		ɾ��ѧ�������ݿⷽ��
		public static void deleteStudent(JButton jbutton_1,JTextField jtextfield_1) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="delete from studentInformation where sno='"+jtextfield_1.getText()+"';";
//				ִ�����
				stat.executeUpdate(sql);
//				��ʾ�ɹ�
				JOptionPane.showMessageDialog(
                        jbutton_1,
                        "ɾ��ѧ����Ϣ�ɹ���",
                        "��ʾ",
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
		
//		��ѯѧ�� �����ݿⷽ��
		public static void selectStudentid(JButton jbutton_81,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation where sno='"+jtextfield[0].getText()+"';";
//				ִ�����
				rs = stat.executeQuery(sql);
//				�����������ı�����
				rs.next();
				jtextfield[1].setText(rs.getString(2));
				jtextfield[2].setText(rs.getString(3));
				jtextfield[3].setText(rs.getString(4));
				jtextfield[4].setText(rs.getString(5));
				jtextfield[5].setText(rs.getString(6));
				jtextfield[6].setText(rs.getString(7));
//				��ʾ�ɹ�
//				JOptionPane.showMessageDialog(
//                        jbutton_8,
//                        "��ѯѧ����Ϣ�ɹ���",
//                        "��ʾ",
//                        JOptionPane.INFORMATION_MESSAGE
//                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				δ��ѯ����ǰѧ���������쳣�������쳣������ӡ�쳣
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        jbutton_81,
                        "δ��ѯ����ǰѧ����Ϣ��",
                        "����",
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
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				sql="select * from studentInformation where sname like '%"+ad.getText()+"%';";
//				ִ�����
				rs = stat.executeQuery(sql);
				while(rs.next()==true) {
					cnt++;
				}
				rs.beforeFirst();
				if(cnt==0) {
					JOptionPane.showMessageDialog(
                        null,
                        "δ��ѯ����ǰѧ����Ϣ��",
                        "����",
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
//				�����������ı�����
				
				
				//jtextfield[1].setText(rs.getString(2));
//				��ʾ�ɹ�
//				JOptionPane.showMessageDialog(
//                        jbutton_8,
//                        "��ѯѧ����Ϣ�ɹ���",
//                        "��ʾ",
//                        JOptionPane.INFORMATION_MESSAGE
//                );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				δ��ѯ����ǰѧ���������쳣�������쳣������ӡ�쳣
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
		
//		���ȫ��ѧ�� �����ݿⷽ��
		public static void showStudent(JTextArea j_2) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation ;";
//				ִ�����
				rs = stat.executeQuery(sql);
				
//				���֮ǰ������ı���
				StudentUI.j_1.setText("");
//				�����������ı�����
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
		
//		���ֽܷ�������
		public static void showTotal(JTextArea j_1) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation ORDER BY Total DESC;";
//				ִ�����
				rs = stat.executeQuery(sql);
//				���֮ǰ������ı���
				StudentUI.j_1.setText("");
//				�����������ı�����
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
		
//		��ĳ��Ŀ��������
		public static void showsomeone(JTextArea j_1,String str) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation ORDER BY "+str+ " DESC;";
//				ִ�����
				rs = stat.executeQuery(sql);
//				���֮ǰ������ı���
				StudentUI.j_1.setText("");
//				�����������ı�����
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
		
//		����ѧ����Ϣ
		public static void saveStudent() {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			//�������
			FileOutputStream out=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select * from studentInformation ;";
//				ִ�����
				rs = stat.executeQuery(sql);
				//����·���ļ���,trueΪ ��ӷ�ʽ
				out =new FileOutputStream("D:\\studentMessage_3.txt",true);
				String student=null;
				String explain="���α�����Ϣ���£�\n";
				byte buf[] =explain.getBytes();
				out.write(buf);
				while(rs.next()) {
					//����������,д������
					byte buffer[]=null;
					student =StudentUI.studentInfo[0]+ rs.getString(1)+"  ||  "+StudentUI.studentInfo[1]+rs.getString(2)
					+"  ||  "+StudentUI.studentInfo[2]+rs.getString(3)+"  ||  "+StudentUI.studentInfo[3]+rs.getString(4)
					+"  ||  "+StudentUI.studentInfo[4]+rs.getString(5)+"  ||  "+StudentUI.studentInfo[6]+rs.getString(6)
					+"\n";
					buffer =student.getBytes();
					out.write(buffer);
				}
				JOptionPane.showMessageDialog(null, "����ɹ�������\n����·��Ϊ��D:\\\\studentMessage_3.txt",
						"��ʾ",JOptionPane.PLAIN_MESSAGE);
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

