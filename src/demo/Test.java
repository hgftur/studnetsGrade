package demo;

import java.util.Random;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import operation.MysqlOperation;

public class Test {
    
	public void createData(JTable table) {
		String[] jtextfield=new String[table.getColumnCount()];
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		for(int i=0;i<20;i++) {
			jtextfield[1]=Integer.toString(new Random().nextInt(1000));
			jtextfield[2]=Integer.toString(new Random().nextInt(3));
			jtextfield[3]=Integer.toString((new Random().nextInt(2))+2002)+"-"
					+Integer.toString((new Random().nextInt(11))+1)+"-"
					+Integer.toString((new Random().nextInt(27))+1);
			jtextfield[4]=gaoshu();
			jtextfield[5]=xiandai();
			jtextfield[6]=lisan();
			jtextfield[7]=yingyu();
			model.addRow(jtextfield);
		}
		MysqlOperation.addStudent(table);
		
	}
	public String gaoshu() {
		Random random=new Random();
		int num=0;
		while(true) {
			num=(int)(random.nextGaussian()*Math.sqrt(900)+80);
			if(num>=0&&num<=100) {
				break;
			}
		}
		return Integer.toString(num);
	}
	public String xiandai() {
		Random random=new Random();
		int num=0;
		while(true) {
			num=(int)(random.nextGaussian()*Math.sqrt(900)+80);
			if(num>=0&&num<=100) {
				break;
			}
		}
		return Integer.toString(num);
	}
	public String lisan() {
		Random random=new Random();
		int num=0;
		while(true) {
			num=(int)(random.nextGaussian()*Math.sqrt(900)+80);
			if(num>=0&&num<=100) {
				break;
			}
		}
		return Integer.toString(num);
	}
	public String yingyu() {
		Random random=new Random();
		int num=0;
		while(true) {
			num=(int)(random.nextGaussian()*Math.sqrt(900)+80);
			if(num>=0&&num<=100) {
				break;
			}
		}
		return Integer.toString(num);
	}
}
