import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import com.sun.tools.javac.Main;

public class ToDoList extends JFrame {
	JTextArea tf, ta1;
	JButton cancel, save, get;
	JPanel p1, p2;
	JLabel explain, day;
	Font font, font2;
	JScrollPane scroll;
	
	public ToDoList() {
		setTitle("할 일 작성하기");

		p1 = new JPanel();
		p1.setLayout(new BorderLayout());

		explain = new JLabel("오늘의 할 일을 작성해주세요!", explain.CENTER);
		font = new Font("맑은 고딕", Font.BOLD, 18);
		explain.setFont(font);
		p1.add("North", explain);
		
		p2 = new JPanel();
		p2.setLayout(null);
		
		tf = new JTextArea(10, 50);
		scroll = new JScrollPane(tf);
		scroll.setBounds(120,60,250,200);
		
		cancel = new JButton("취소");
		cancel.setBounds(270, 280, 60, 30);
		
		
		p2.add(scroll);
		p2.add(cancel);
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "취소되었습니다.");
				tf.setText("");
				
			}
		});
		
		day = new JLabel("오늘 날짜 입력 :");
		day.setBounds(135,15,100,20);
		ta1= new JTextArea(10,1);
        ta1.setBounds(230,15,100,20);
        p2.add(ta1);
        p2.add(day);
		
		save= new JButton("SAVE");
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
				String daymemo = ta1.getText();
					if (daymemo.length()==0)
					{
						JOptionPane.showMessageDialog(null, "날짜를 입력해주세요.","에러", JOptionPane.WARNING_MESSAGE);
					}else 
					{
						JOptionPane.showMessageDialog(null, "저장이 완료되었습니다.");
						
					}
                try {
                    File f= new File("ListFolder");
                    if(!f.isDirectory()) f.mkdir();

                    String memo = tf.getText();
                    if(memo.length()>0){
                        BufferedWriter out = new BufferedWriter(new FileWriter("ListFolder/"+ta1.getText()+".txt"));
                        String str = tf.getText();
                        out.write(str);
                        out.close();
                    }
                } catch (IOException e) {
                }
            }
        });

        get= new JButton("Get");
        get.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
				String daymemo = ta1.getText();
					if (daymemo.length()==0)
					{
						JOptionPane.showMessageDialog(null, "날짜를 입력해주세요.","에러", JOptionPane.WARNING_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "이전 데이터를 불러옵니다.");
						
					}
				
				try{
                File f = new File("ListFolder/"+ta1.getText()+".txt");
                if(f.exists()){
                    BufferedReader in = new BufferedReader(new FileReader("ListFolder/"+ta1.getText()+".txt"));
                    String memoAreaText= new String();
                    while(true){
                        String tempStr = in.readLine();
                        if(tempStr == null) break;
                        memoAreaText = memoAreaText + tempStr + System.getProperty("line.separator");
                    }
                    tf.setText(memoAreaText);
                    in.close();
                }
                else tf.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        });
		JButton BackButton=new JButton("뒤로 가기");

			BackButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	Window nW = new Window();
			
				Dimension frameSize = nW.getSize();
				Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
				nW.setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
				nW.setVisible(true);
                setVisible(false);
				
				}
			});
        
        save.setBounds(100, 280, 70, 30);
        get.setBounds(180,280,70,30);
		BackButton.setBounds(360, 280, 100, 30);
        
        Color color = new Color(0x99CCFF);
        p1.setBackground(color);
        
        p2.add(save);
        p2.add(get);
		p2.add(BackButton);
        
        p1.add(p2);
		setContentPane(p1);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		ToDoList td = new ToDoList();
		Dimension frameSize = td.getSize();

		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

		System.out.println(frameSize + " " + windowSize);

		td.setLocation((windowSize.width - frameSize.width) / 2,

		(windowSize.height - frameSize.height) / 2);

		td.setVisible(true);

	}
}