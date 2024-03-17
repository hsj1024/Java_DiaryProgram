import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Calendar extends Frame {

	private static final long serialVersionUID = 1L;
	private Panel panel = null;
	private Panel panel1 = null;
	private Choice choice = null;
	private Choice choice1 = null;
	private Label label = null;
	private Label label1 = null;
	private Button[] bt2 = new Button[42];
	private int year=0;
	private int month=0;
	
	private Panel getPanel() {
		if (panel == null) {
			label1 = new Label();
			label1.setText("년");
			label = new Label();
			label.setText("월");
			panel = new Panel();
			panel.setLayout(new FlowLayout());
			panel.add(getChoice(), null);
			panel.add(label1, null);
			panel.add(getChoice1(), null);
			panel.add(label, null);
		}
		return panel;
	}

	private Panel getPanel1() {
		if (panel1 == null) {
			panel1= new Panel();
			panel1.setLayout(new GridLayout(7,7));	

			Button[] bt1 = new Button[7]; // 요일 버튼 생성하기
			
			Font font = new Font("맑은 고딕", Font.BOLD, 12); // 달력의 폰트 지정하기

			String[] day = {"일","월","화","수","목","금","토"};
			for(int i=0 ; i<7 ; i++){
				bt1[i] = new Button(day[i]);
				bt1[i].setFont(font);
				panel1.add(bt1[i]);
			}
			
			// 일요일 버튼 색 설정하기
			Color color1 = new Color(0xFF3333, true);	
			bt1[0].setForeground(Color.white);	
			bt1[0].setBackground(color1);

			// 토요일 버튼 색 설정하기
			Color color2 = new Color(0x3366FF, true);
			bt1[6].setForeground(Color.white);
			bt1[6].setBackground(color2);
			
			//날짜 버튼 생성하기
			bt2 = new Button[42];
			
			for(int i=0 ; i<42 ; i++){
				bt2[i] = new Button("");
				bt2[i].setBackground(Color.white);
				bt2[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Window nw = new Window(); 
						Dimension frameSize = nw.getSize();
						Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
						nw.setLocation((windowSize.width - frameSize.width) / 2,
						(windowSize.height - frameSize.height) / 2);
					}
				});
				panel1.add(bt2[i]);
			}


			year = Integer.parseInt(choice1.getSelectedItem());
			month = Integer.parseInt(choice1.getSelectedItem());
			
			int startDay = 0;
			int endDay = 0;
			
			java.util.Calendar sDay=java.util.Calendar.getInstance();
			java.util.Calendar eDay=java.util.Calendar.getInstance();
			
			sDay.set(year,month-1,1);
			eDay.set(year, month,1);
			eDay.add(java.util.Calendar.DATE, -1);
			
			startDay=sDay.get(java.util.Calendar.DAY_OF_WEEK);
			endDay=eDay.get(java.util.Calendar.DATE);
			
            for(int i=1; i<=endDay ; i++){
            	bt2[i+startDay-2].setLabel(i+"");
            }
		}
		return panel1;
	}
	
	// 년도 지정하기
	private Choice getChoice() {
		if (choice == null) {
			choice = new Choice();
			choice.add("2020");
			choice.add("2021");
			choice.add("2022");
			choice.add("2023");
			choice.add("2024");

			choice.select(1);
		}
		return choice;
	}

	@SuppressWarnings("deprecation")
	private Choice getChoice1() {
		if (choice1 == null) {
			choice1 = new Choice();

			choice1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					for(int i=0 ; i<42 ; i++){
						bt2[i].setLabel("");
					}
					
					
					year = Integer.parseInt(choice.getSelectedItem());
					month = Integer.parseInt(choice1.getSelectedItem());
					
					int startDay = 0;
					int endDay = 0;
					
					java.util.Calendar sDay=java.util.Calendar.getInstance();
					java.util.Calendar eDay=java.util.Calendar.getInstance();
					
					sDay.set(year,month-1,1);
					eDay.set(year, month,1);
					eDay.add(java.util.Calendar.DATE, -1);
					
					startDay=sDay.get(java.util.Calendar.DAY_OF_WEEK);
					endDay=eDay.get(java.util.Calendar.DATE);

		            for(int i=1; i<=endDay ; i++){
		            	bt2[i+startDay-2].setLabel(i+"");
		            }
				}
			});

			// 월 지정하기
			choice1.add("1");
			choice1.add("2");
			choice1.add("3");
			choice1.add("4");
			choice1.add("5");
			choice1.add("6");
			choice1.add("7");
			choice1.add("8");
			choice1.add("9");
			choice1.add("10");
			choice1.add("11");
			choice1.add("12");

			choice1.select(5);
		}
		return choice1;
	}

	public static void main(String[] args) {
		Calendar cal = new Calendar();
		cal.setVisible(true);
		Dimension frameSize = cal.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();		
		cal.setLocation((windowSize.width - frameSize.width) / 2,
		(windowSize.height - frameSize.height) / 2);
	}

	public Calendar() {
		super();
		initialize();
		setVisible(true);
	
	}

	private void initialize() {
		this.setSize(500, 409);
		this.setTitle("Diary");
		this.add(getPanel(), BorderLayout.NORTH);
		this.add(getPanel1(), BorderLayout.CENTER);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}
}