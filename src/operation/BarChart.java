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
     public String[] filename= {"高数","线代","离散","英语"};
     public BarChart() {
    	 int[][] bar=MysqlOperation.showTotal("柱状图", null);
    	 for(int i=0;i<bar.length;i++) {
    		 DefaultCategoryDataset data=new DefaultCategoryDataset();
    		 data.addValue(bar[i][0]*1.0/bar[i][5], "", "100-90分");
	    	 data.addValue(bar[i][1]*1.0/bar[i][5], "", "90-80分");
	    	 data.addValue(bar[i][2]*1.0/bar[i][5], "", "80-70分");
	    	 data.addValue(bar[i][3]*1.0/bar[i][5], "", "70-60分");
	    	 data.addValue(bar[i][4]*1.0/bar[i][5], "", "60分以下");
    		 ChartFactory.setChartTheme(getChartTheme());
    		 JFreeChart jFreeChart=ChartFactory.createBarChart3D("学生"+filename[i]+
    				 "成绩分布柱状图", "不同分数段", "占比", data);
    		 try {
    			 ChartUtilities.saveChartAsJPEG(new File("D:\\学生"+filename[i]+
    					 "成绩柱状分布图.jpg"), jFreeChart, 2000, 800);
    		 } catch (IOException e) {
    			 // TODO Auto-generated catch block
    			 e.printStackTrace();
    		 } 
    		 
    	 }
    	 
     }
	private ChartTheme getChartTheme() {
		// TODO Auto-generated method stub
		StandardChartTheme standar=new StandardChartTheme("CN");
		standar.setLargeFont(new Font("宋体",Font.BOLD,30));
		standar.setExtraLargeFont(new Font("宋体",Font.BOLD,40));
		standar.setRegularFont(new Font("宋体",Font.BOLD,30));
		return standar;
	}
}
