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
				String url_1="jdbc:mysql://localhost:3306?characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
				String url_2="jdbc:mysql://localhost:3306/users?characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true";
				user="root";
				password="111111";
				
//				建立连接
				con=DriverManager.getConnection(url_1,user,password);
//				创建数据库mydatabase
				stat=(Statement)con.createStatement();
				String sql_1="create database if not exists users;";
//				执行语句创建数据库user
				stat.executeUpdate(sql_1);
				
				con=DriverManager.getConnection(url_2,user,password);
				String sql_3="create table if not exists teachers(编号 int primary key auto_increment,"
				             +"password varchar(20),name varchar(10),subject varchar(20));";
				stat=(Statement)con.createStatement();
				stat.executeUpdate(sql_3);
				
				
				con=DriverManager.getConnection(url_2,user,password);
				String sql_2="create table if not exists studentInformations( id int(20) primary key auto_increment, "
						+ "sname varchar(20), sex char(2), birthdate date, 高数 int, 线代 int, "
						+ "离散 int,英语 int,total int);";
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
				String str="select max(编号) from teachers";
				ResultSet re=stat2.executeQuery(str);
				re.next();
				jtext[0].setText(String.valueOf(re.getInt(1)));
				
				JOptionPane.showMessageDialog(
                        null,
                        "注册成功,您的编号为"+String.valueOf(re.getInt(1))+"请返回登录！",
                        "提示",
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
		
//		添加学生的数据库方法
		public static void addStudent(JTable table) {
			Connection con=null;
			Statement stat=null,stat2=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				stat2=(Statement)con.createStatement();
				int cnt=table.getColumnCount();
				String[] jtextfield=new String[cnt];
//				执行语句
				for(int i=0;i<table.getRowCount();i++) {
					for(int j=1;j<cnt;j++) {
						jtextfield[j]=(String) table.getValueAt(i, j);
					}
					int sum=0;
					sum = Integer.parseInt("0"+jtextfield[4])+Integer.parseInt("0"+jtextfield[5])+
						Integer.parseInt("0"+jtextfield[6])+Integer.parseInt("0"+jtextfield[7]);
				    sql="insert into studentInformations(sname,sex,birthdate,高数,线代,离散,英语,total) "
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
//				提示成功
				JOptionPane.showMessageDialog(
                        null,
                        "添加学生信息成功！",
                        "提示",
                        JOptionPane.INFORMATION_MESSAGE
                );
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				处理异常情况，如学号重复，不打印异常
				e.printStackTrace();
/*				JOptionPane.showMessageDialog(
                        null,
                        "你输入的日期格式不正确",
                        "提示",
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
					sql="update studentInformations set sname='"+jtextfield[1].getText()
							+"'"+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[2].getText().equals("")) {
					sql="update studentInformations set sex='"+jtextfield[2].getText()
							+"'"+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[3].getText().equals("")) {
					sql="update studentInformations set birthdate='"+jtextfield[3].getText()
							+"'"+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[4].getText().equals("")) {
					sql="update studentInformations set 高数="+Integer.parseInt(jtextfield[4].getText())
							+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[5].getText().equals("")) {
					sql="update studentInformations set 线代="+jtextfield[5].getText()
							+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[6].getText().equals("")) {
					sql="update studentInformations set 离散="+jtextfield[6].getText()
							+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//执行语句
					stat.executeUpdate(sql);
				}
				if(!jtextfield[7].getText().equals("")) {
					sql="update studentInformations set 英语="+jtextfield[7].getText()
							+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
					//执行语句
					stat.executeUpdate(sql);
				}
//				计算总分
				sql="select * from studentInformations where id="+Integer.parseInt(jtextfield[0].getText())+";";
//				执行语句
				rs = stat.executeQuery(sql);
				int sum=0;
				rs.next();
				sum = Integer.parseInt(rs.getString(5))+Integer.parseInt(rs.getString(6))+Integer.parseInt(rs.getString(7))
				+Integer.parseInt(rs.getString(8));
//				更新总分
				sql="update studentInformations set total="+sum
						+" where id="+Integer.parseInt(jtextfield[0].getText())+";";
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
				sql="delete from studentInformations where id="+Integer.parseInt(jtextfield_1.getText())+";";
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
		public static void selectStudentid(JTextField id,JTextField jtextfield[]) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				sql="select id,sname,高数,线代,离散,英语,total from studentInformations where id="+Integer.parseInt(id.getText())+";";
//				执行语句
				rs = stat.executeQuery(sql);
//				将结果输出到文本框中
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
//				未查询到当前学生，出现异常，处理异常但不打印异常
//				e.printStackTrace();
				JOptionPane.showMessageDialog(
                        null,
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
//按姓名模糊查找		
		public static void selectStudentname(JTextField ad,JTextField jtext[],JPanel jpanel_2) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			int cnt=0;
			JTable table=null;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				sql="select id,sname,高数,线代,离散,英语,total from studentInformations "+
				    "where sname like '%"+ad.getText()+"%';";
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
					jtext[0].setText(rs.getString(1));
					jtext[1].setText(rs.getString(2));
					jtext[4].setText(rs.getString(3));
					jtext[5].setText(rs.getString(4));
					jtext[6].setText(rs.getString(5));
					jtext[7].setText(rs.getString(6));
					jtext[8].setText(rs.getString(7));
				}else {
					
					String[] head= {"学号","姓名","高等数学","线性代数","离散数学","英语","总分"};
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
		
		
//		按总分降序排序
		public static int[][] showTotal(String str,JPanel jpanel_2) {
			Connection con=null;
			Statement stat=null;
			ResultSet rs=null;
			String sql=null;
			int[][] bar=new int[4][6];
			String[] head= {"学号","姓名","高等数学","平均分","线性代数","平均分",
					"离散数学","平均分","英语","平均分","总分","平均分"};
			int length=head.length;
			try {
//				建立数据库连接
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				sql="select id,sname,高数,avg(高数),线代,avg(线代),离散,avg(离散),英语,avg(英语),total,avg(total)"+
				     " from studentInformations group by id order by total desc;";
//				执行语句
				rs = stat.executeQuery(sql);
				if(str.equals("总分排序")) {
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
					
				}else if(str.equals("导出成绩")) {
					Frame frame=new Frame("保存文件");
					FileDialog f1=new FileDialog(frame,"选择要保存的文件",FileDialog.SAVE);
					f1.setVisible(true);
					rs.beforeFirst();
					String path=f1.getDirectory();
					String name=f1.getFile();
					Workbook workbook = new SXSSFWorkbook();
					Sheet sheet=workbook.createSheet("学生成绩报表");
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
		
		
//      按科目录入学生成绩
		public static void inputGrade(String str,JTextField id,JTextField grade) {
			Connection con=null;
			Statement stat=null;
			String sql=null;
			
			try {
				con=MysqlOperation.getConnection();
				stat=(Statement)con.createStatement();
				if(str.equals("高等数学")) {
					if(!grade.getText().equals("")) {
						sql="update studentInformations set 高数="+Integer.parseInt(grade.getText())+
							" where id="+Integer.parseInt(id.getText())+";";
						stat.executeUpdate(sql);
					}
					
				}else if(str.equals("线性代数")) {
					if(!grade.getText().equals("")) {
						sql="update studentInformations set 线代="+Integer.parseInt(grade.getText())+
						" where id="+Integer.parseInt(id.getText())+";";
						stat.executeUpdate(sql);
					}
					
				}else if(str.equals("离散数学")) {
					if(!grade.getText().equals("")) {
						sql="update studentInformations set 离散="+Integer.parseInt(grade.getText())+
						" where id="+Integer.parseInt(id.getText())+";";
						stat.executeUpdate(sql);
					}
					
				}else if(str.equals("英语")) {
					if(!grade.getText().equals("")) {
						sql="update studentInformations set 英语="+Integer.parseInt(grade.getText())+
						" where id="+Integer.parseInt(id.getText())+";";
						stat.executeUpdate(sql);
					}
					
				}
				JOptionPane.showMessageDialog(
                        null,
                        "录入成功！",
                        "提示",
                        JOptionPane.WARNING_MESSAGE
					);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(
                        null,
                        "学号不存在！",
                        "警告",
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

