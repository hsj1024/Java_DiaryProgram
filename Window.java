import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Window extends JFrame {
	public Window() {

        setTitle("기능 선택하기");
    
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        NewWindowContainer.setLayout(null);
		
		// 문구 출력하기
        JLabel Label1 = new JLabel("사용하고 싶은 기능을 선택하세요 !");
        Font font = new Font("함초롱바탕", Font.BOLD, 13);
        Label1.setFont(font);

        NewWindowContainer.add(Label1);
        Label1.setBounds(90, 10, 220,70);

		// 오늘의 일기 쓰기 버튼
        JButton NewButton1 = new JButton("오늘의 일기 쓰기");
        NewButton1.setBounds(100, 75, 170,40);
		NewWindowContainer.add(NewButton1);
        NewButton1.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                PICTURE pc = new PICTURE();
				Dimension frameSize = pc.getSize();
				Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
				pc.setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
                setVisible(false);
            }
        });
		
		// 오늘의 할일 쓰기 버튼
        JButton NewButton2 = new JButton("오늘의 할일 쓰기");
        NewButton2.setBounds(100, 125, 170, 40);
        NewWindowContainer.add(NewButton2);

        NewButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	ToDoList td = new ToDoList();
				Dimension frameSize = td.getSize();
				Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
				td.setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
                setVisible(false);
            }
        });

		// 오늘의 기분 체크하기 버튼
        JButton NewButton3 = new JButton("오늘의 기분 체크하기");
        NewButton3.setBounds(100, 175, 170,40);
        NewWindowContainer.add(NewButton3);

        NewButton3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	Feeling fl = new Feeling();
				Dimension frameSize = fl.getSize();
				Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
				fl.setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
                setVisible(false);
				
            }
        });
        
		JButton BackButton = new JButton("달력으로"+"\n"+" 돌아가기");
        BackButton.setBounds(220, 226, 150,30);
        NewWindowContainer.add(BackButton);

        BackButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	Calendar cl = new Calendar();
				
				Dimension frameSize = cl.getSize();
				Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
				cl.setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
				cl.setVisible(false);
                setVisible(false);
				
            }
        });
        Color color = new Color(0x99CCFF);
        NewButton1.setBackground(color);
        NewButton2.setBackground(color);
        NewButton3.setBackground(color);
        NewWindowContainer.setBackground(Color.white);

        setSize(400,300);
		
        setResizable(false);
        setVisible(true);
    }

}
