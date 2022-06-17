package operation;

import java.awt.Font;


import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {
     public String[] filename= {"����","�ߴ�","��ɢ","Ӣ��"};
     public BarChart() {
    	 int[][] bar=MysqlOperation.showTotal("��״ͼ", null);
    	 for(int i=0;i<bar.length;i++) {
    		 DefaultCategoryDataset data=new DefaultCategoryDataset();
    		 data.addValue(bar[i][0]*1.0/bar[i][5], "", "100-90��");
	    	 data.addValue(bar[i][1]*1.0/bar[i][5], "", "90-80��");
	    	 data.addValue(bar[i][2]*1.0/bar[i][5], "", "80-70��");
	    	 data.addValue(bar[i][3]*1.0/bar[i][5], "", "70-60��");
	    	 data.addValue(bar[i][4]*1.0/bar[i][5], "", "60������");
    		 ChartFactory.setChartTheme(getChartTheme());
    		 JFreeChart jFreeChart=ChartFactory.createBarChart3D("ѧ��"+filename[i]+
    				 "�ɼ��ֲ���״ͼ", "��ͬ������", "ռ��", data);
    		 try {
    			 ChartUtilities.saveChartAsJPEG(new File("D:\\ѧ��"+filename[i]+
    					 "�ɼ���״�ֲ�ͼ.jpg"), jFreeChart, 2000, 800);
    		 } catch (IOException e) {
    			 // TODO Auto-generated catch block
    			 e.printStackTrace();
    		 } 
    		 
    	 }
    	 
     }
	private ChartTheme getChartTheme() {
		// TODO Auto-generated method stub
		StandardChartTheme standar=new StandardChartTheme("CN");
		standar.setLargeFont(new Font("����",Font.BOLD,30));
		standar.setExtraLargeFont(new Font("����",Font.BOLD,40));
		standar.setRegularFont(new Font("����",Font.BOLD,30));
		return standar;
	}
}
