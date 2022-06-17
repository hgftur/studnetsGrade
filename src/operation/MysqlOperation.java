package operation;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.io.FileOutputStream;

import java.sql.*;
//import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import gui.StudentUI;
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
				String url_1="jdbc:mysql://localhost:3306?characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
				String url_2="jdbc:mysql://localhost:3306/users?characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
				user="root";
				password="111111";
				
//				��������
				con=DriverManager.getConnection(url_1,user,password);
//				�������ݿ�mydatabase
				stat=(Statement)con.createStatement();
				String sql_1="create database if not exists users;";
//				ִ����䴴�����ݿ�user
				stat.executeUpdate(sql_1);
				
				con=DriverManager.getConnection(url_2,user,password);
				String sql_3="create table if not exists teachers(��� int primary key auto_increment,"
				             +"password varchar(20),name varchar(10),subject varchar(20));";
				stat=(Statement)con.createStatement();
				stat.executeUpdate(sql_3);
				
				
				con=DriverManager.getConnection(url_2,user,password);
				String sql_2="create table if not exists studentInformations( id int(20) primary key auto_increment, "
						+ "sname varchar(20), sex char(2), birthdate date, ���� int, �ߴ� int, "
						+ "��ɢ int,Ӣ�� int,total int);";
				stat=(Statement)con.createStatement();
				stat.executeUpdate(sql_2);
				
			}catch (Exception e) {
				e.printStackTrace();
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
				String str="select max(���) from teachers";
				ResultSet re=stat2.executeQuery(str);
				re.next();
				jtext[0].setText(String.valueOf(re.getInt(1)));
				
				JOptionPane.showMessageDialog(
                        null,
                        "ע��ɹ�,���ı��Ϊ"+String.valueOf(re.getInt(1))+"�뷵�ص�¼��",
                        "��ʾ",
                        JOptionPane.WARNING_MESSAGE
                );
				
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
		public static void addStudent(JTable table) {
			Connection con=null;
			Statement stat=null,stat2=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				stat2=(Statement)con.createStatement();
				int cnt=table.getColumnCount();
				String[] jtextfield=new String[cnt];
//				ִ�����
				for(int i=0;i<table.getRowCount();i++) {
					for(int j=1;j<cnt;j++) {
						jtextfield[j]=(String) table.getValueAt(i, j);
					}
					int sum=0;
					sum = Integer.parseInt("0"+jtextfield[4])+Integer.parseInt("0"+jtextfield[5])+
						Integer.parseInt("0"+jtextfield[6])+Integer.parseInt("0"+jtextfield[7]);
				    sql="insert into studentInformations(sname,sex,birthdate,����,�ߴ�,��ɢ,Ӣ��,total) "
					     +"values('"+jtextfield[1]+"','"+jtextfield[2]+"',str_to_date('"
					     +jtextfield[3]+"','%Y-%m-%d'),"+Integer.parseInt("0"+jtextfield[4])+","
					     +Integer.parseInt("0"+jtextfield[5])+","+Integer.parseInt("0"+jtextfield[6])
					     +","+Integer.parseInt("0"+jtextfield[7])+","+sum+");";
					stat.executeUpdate(sql);
//					
				}
				String sql2="select max(id) from studentInformations";
				ResultSet re=stat2.executeQuery(sql2);
				re.next();
				int id=Integer.parseInt(re.getString(1));
				for(int i=table.getRowCount()-1;i>=0;i--) {
					
					((DefaultTableModel)table.getModel()).setValueAt(id--, i, 0);;
				}
				StudentUI.jpanel_2.updateUI();
//				��ʾ�ɹ�
				JOptionPane.showMessageDialog(
                        null,
                        "���ѧ����Ϣ�ɹ���",
                        "��ʾ",
                        JOptionPane.INFORMATION_MESSAGE
                );
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				�����쳣�������ѧ���ظ�������ӡ�쳣
				e.printStackTrace();
/*				JOptionPane.showMessageDialog(
                        null,
                        "����������ڸ�ʽ����ȷ",
                        "��ʾ",
                        JOptionPane.INFORMATION_MESSAGE
                );
*/				
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
					sql="update studentInformations set sname='"+jtextfield[1].getText()
							+"'"+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[2].getText().equals("")) {
					sql="update studentInformations set sex='"+jtextfield[2].getText()
							+"'"+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[3].getText().equals("")) {
					sql="update studentInformations set birthdate='"+jtextfield[3].getText()
							+"'"+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[4].getText().equals("")) {
					sql="update studentInformations set ����="+Integer.parseInt(jtextfield[4].getText())
							+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[5].getText().equals("")) {
					sql="update studentInformations set �ߴ�="+jtextfield[5].getText()
							+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[6].getText().equals("")) {
					sql="update studentInformations set ��ɢ="+jtextfield[6].getText()
							+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//ִ�����
					stat.executeUpdate(sql);
				}
				if(!jtextfield[7].getText().equals("")) {
					sql="update studentInformations set Ӣ��="+jtextfield[7].getText()
							+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//ִ�����
					stat.executeUpdate(sql);
				}
//				�����ܷ�
				sql="select * from studentInformations where id="+Integer.parseInt(jtextfield[0].getText())+";";
//				ִ�����
				rs = stat.executeQuery(sql);
				int sum=0;
				rs.next();
				sum = Integer.parseInt(rs.getString(5))+Integer.parseInt(rs.getString(6))+Integer.parseInt(rs.getString(7))
				+Integer.parseInt(rs.getString(8));
//				�����ܷ�
				sql="update studentInformations set total="+sum
						+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
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
				sql="delete from studentInformations where id="+Integer.parseInt(jtextfield_1.getText())+";";
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
		public static void selectStudentid(JTextField id,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select id,sname,����,�ߴ�,��ɢ,Ӣ��,total from studentInformations where id="+Integer.parseInt(id.getText())+";";
//				ִ�����
				rs = stat.executeQuery(sql);
//				�����������ı�����
				rs.next();
				jtextfield[0].setText(rs.getString(1));
				jtextfield[1].setText(rs.getString(2));
				jtextfield[4].setText(rs.getString(3));
				jtextfield[5].setText(rs.getString(4));
				jtextfield[6].setText(rs.getString(5));
				jtextfield[7].setText(rs.getString(6));
				jtextfield[8].setText(rs.getString(7));
//				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				δ��ѯ����ǰѧ���������쳣�������쳣������ӡ�쳣
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        null,
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
//������ģ������		
		public static void selectStudentname(JTextField ad,JTextField jtext[],JPanel jpanel_2) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			int cnt=0;
			JTable table=null;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				sql="select id,sname,����,�ߴ�,��ɢ,Ӣ��,total from studentInformations "+
				    "where sname like '%"+ad.getText()+"%';";
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
					jtext[0].setText(rs.getString(1));
					jtext[1].setText(rs.getString(2));
					jtext[4].setText(rs.getString(3));
					jtext[5].setText(rs.getString(4));
					jtext[6].setText(rs.getString(5));
					jtext[7].setText(rs.getString(6));
					jtext[8].setText(rs.getString(7));
				}else {
					
					String[] head= {"ѧ��","����","�ߵ���ѧ","���Դ���","��ɢ��ѧ","Ӣ��","�ܷ�"};
					String[][] content=new String[cnt][7];
					int i=0;
					while(rs.next()==true) {
						for(int j=1;j<=7;j++) {
							content[i][j-1]=rs.getString(j);
						}
						i++;
					}
					table=new JTable(content,head);
					jpanel_2.removeAll();
					jpanel_2.updateUI();
					jpanel_2.add(new JScrollPane(table));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				
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
		public static int[][] showTotal(String str,JPanel jpanel_2) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			int[][] bar=new int[4][6];
			String[] head= {"ѧ��","����","�ߵ���ѧ","ƽ����","���Դ���","ƽ����",
					"��ɢ��ѧ","ƽ����","Ӣ��","ƽ����","�ܷ�","ƽ����"};
			int length=head.length;
			try {
//				�������ݿ�����
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				sql="select id,sname,����,avg(����),�ߴ�,avg(�ߴ�),��ɢ,avg(��ɢ),Ӣ��,avg(Ӣ��),total,avg(total)"+
				     " from studentInformations group by id order by total desc;";
//				ִ�����
				rs = stat.executeQuery(sql);
				if(str.equals("�ܷ�����")) {
					rs.beforeFirst();
					DefaultTableModel rowData=new DefaultTableModel(null,head);
					while(rs.next()==true) {
						String[] data = new String[length];
						for(int j=0;j<length;j++) {
							data[j]=rs.getString(j+1);
						}
						rowData.addRow(data);
					}
					JTable table=new JTable(rowData);
					jpanel_2.add(new JScrollPane(table));
					
				}else if(str.equals("�����ɼ�")) {
					Frame frame=new Frame("�����ļ�");
					FileDialog f1=new FileDialog(frame,"ѡ��Ҫ������ļ�",FileDialog.SAVE);
					f1.setVisible(true);
					rs.beforeFirst();
					String path=f1.getDirectory();
					String name=f1.getFile();
					Workbook workbook = new SXSSFWorkbook();
					Sheet sheet=workbook.createSheet("ѧ���ɼ�����");
					Row row1=sheet.createRow(0);
					for(int i=0;i<length;i++) {
						Cell cell1=row1.createCell(i);
						cell1.setCellValue(head[i]);
					}
					int i=1;
					while(rs.next()==true) {
						Row row2=sheet.createRow(i);
						for(int j=0;j<length;j++) {
							Cell cell2=row2.createCell(j);
							cell2.setCellValue(rs.getString(j+1));
						}
						i++;
					}
					FileOutputStream files;
					try {
						files = new FileOutputStream(path+name+".xlsx");
						workbook.write(files);
						files.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else {
					rs.beforeFirst();
					while(rs.next()==true) {
					for(int j=1;j<=head.length;j++) {
						if(j==3) {
							if(Integer.parseInt(rs.getString(j))<=100&&Integer.parseInt(rs.getString(j))>=90) {
								bar[0][0]++;
							}else if(Integer.parseInt(rs.getString(j))<90&&Integer.parseInt(rs.getString(j))>=80) {
								bar[0][1]++;
							}else if(Integer.parseInt(rs.getString(j))<80&&Integer.parseInt(rs.getString(j))>=70) {
								bar[0][2]++;
							}else if(Integer.parseInt(rs.getString(j))<70&&Integer.parseInt(rs.getString(j))>=60) {
								bar[0][3]++;
							}else {
								bar[0][4]++;
							}
							bar[0][5]++;
						}else if(j==5) {
							if(Integer.parseInt(rs.getString(j))<=100&&Integer.parseInt(rs.getString(j))>=90) {
								bar[1][0]++;
							}else if(Integer.parseInt(rs.getString(j))<90&&Integer.parseInt(rs.getString(j))>=80) {
								bar[1][1]++;
							}else if(Integer.parseInt(rs.getString(j))<80&&Integer.parseInt(rs.getString(j))>=70) {
								bar[1][2]++;
							}else if(Integer.parseInt(rs.getString(j))<70&&Integer.parseInt(rs.getString(j))>=60) {
								bar[1][3]++;
							}else {
								bar[1][4]++;
							}
							bar[1][5]++;
						}else if(j==7) {
							if(Integer.parseInt(rs.getString(j))<=100&&Integer.parseInt(rs.getString(j))>=90) {
								bar[2][0]++;
							}else if(Integer.parseInt(rs.getString(j))<90&&Integer.parseInt(rs.getString(j))>=80) {
								bar[2][1]++;
							}else if(Integer.parseInt(rs.getString(j))<80&&Integer.parseInt(rs.getString(j))>=70) {
								bar[2][2]++;
							}else if(Integer.parseInt(rs.getString(j))<70&&Integer.parseInt(rs.getString(j))>=60) {
								bar[2][3]++;
							}else {
								bar[2][4]++;
							}
							bar[2][5]++;
						}else if(j==9) {
							if(Integer.parseInt(rs.getString(j))<=100&&Integer.parseInt(rs.getString(j))>=90) {
								bar[3][0]++;
							}else if(Integer.parseInt(rs.getString(j))<90&&Integer.parseInt(rs.getString(j))>=80) {
								bar[3][1]++;
							}else if(Integer.parseInt(rs.getString(j))<80&&Integer.parseInt(rs.getString(j))>=70) {
								bar[3][2]++;
							}else if(Integer.parseInt(rs.getString(j))<70&&Integer.parseInt(rs.getString(j))>=60) {
								bar[3][3]++;
							}else {
								bar[3][4]++;
							}
							bar[3][5]++;
						}
						
					}
				}
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
			return bar;
		}
		
		
//      ����Ŀ¼��ѧ���ɼ�
		public static void inputGrade(String str,JTextField id,JTextField grade) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			
			try {
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				if(str.equals("�ߵ���ѧ")) {
					if(!grade.getText().equals("")) {
						sql="update studentInformations set ����="+Integer.parseInt(grade.getText())+
							" where id="+Integer.parseInt(id.getText())+";";
						stat.executeUpdate(sql);
					}
					
				}else if(str.equals("���Դ���")) {
					if(!grade.getText().equals("")) {
						sql="update studentInformations set �ߴ�="+Integer.parseInt(grade.getText())+
						" where id="+Integer.parseInt(id.getText())+";";
						stat.executeUpdate(sql);
					}
					
				}else if(str.equals("��ɢ��ѧ")) {
					if(!grade.getText().equals("")) {
						sql="update studentInformations set ��ɢ="+Integer.parseInt(grade.getText())+
						" where id="+Integer.parseInt(id.getText())+";";
						stat.executeUpdate(sql);
					}
					
				}else if(str.equals("Ӣ��")) {
					if(!grade.getText().equals("")) {
						sql="update studentInformations set Ӣ��="+Integer.parseInt(grade.getText())+
						" where id="+Integer.parseInt(id.getText())+";";
						stat.executeUpdate(sql);
					}
					
				}
				JOptionPane.showMessageDialog(
                        null,
                        "¼��ɹ���",
                        "��ʾ",
                        JOptionPane.WARNING_MESSAGE
					);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(
                        null,
                        "ѧ�Ų����ڣ�",
                        "����",
                        JOptionPane.WARNING_MESSAGE
					);
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
		
		
}

