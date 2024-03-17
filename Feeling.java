import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Feeling extends JFrame {
	JButton[] btn;
	JPanel epanel, ipanel, fpanel;
	JLabel explain;
	Font font, font2;
	Color color;
	ImageIcon[] images = {
		    new ImageIcon("images/i1.png"),
		    new ImageIcon("images/i2.png"),
		    new ImageIcon("images/i3.png"),
		    new ImageIcon("images/i4.png"),
		    new ImageIcon("images/i5.png"),
		    new ImageIcon("images/i6.png"),
		    new ImageIcon("images/i7.png"),
		    new ImageIcon("images/i8.png"),
	};
	
	public Feeling() {
		setTitle("오늘의 기분 선택하기");
		
		epanel = new JPanel();
		epanel.setLayout(null);
		
		explain = new JLabel("오늘의 기분을 선택해주세요!", explain.CENTER);
		font = new Font("맑은 고딕", Font.BOLD, 18);
		explain.setFont(font);
		explain.setBounds(70, 10, 300, 20);
		
		epanel.add(explain);
		
		ipanel = new JPanel();
		ipanel.setLayout(new GridLayout(2, 4, 3, 3));
		
		btn = new JButton[8];
		for (int i=0; i<btn.length; i++) {
			ipanel.add(btn[i] = new JButton(images[i]));
		}

		JButton BackButton=new JButton("뒤로 가기");
		epanel.add(BackButton);

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
			BackButton.setBounds(470, 300,100,30);
		
		fpanel = new JPanel();
		fpanel.setLayout(new GridLayout(1, 1));
		
		JTextField td = new JTextField(50);
		font2 = new Font("맑은 고딕", Font.BOLD, 15);
		td.setFont(font2);
		td.setEditable(false);
		
		for (int i=0; i<8; i++) {
			if (i==0) {
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = new Color(0xFF66FF);
						td.setForeground(color);
						td.setText("완전 행복해!!!");
					}
				});
			}
			if (i==1) {
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = new Color(0xFF9900);
						td.setForeground(color);
						td.setText("기분 좋아 ^_^");
					}
				});
			}
			if (i==2) {
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						td.setForeground(Color.darkGray);
						td.setText("기분 안좋아");
					}
				});
			}
			if (i==3) {
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = new Color(0x009999);
						td.setForeground(color);
						td.setText("묘한 기분이야");
					}
				});
			}
			if (i==4) {
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = new Color(0x0066CC);
						td.setForeground(color);
						td.setText("나 오늘 좀 멋있는 듯~");
					}
				});
			}
			if (i==5) {
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = new Color(0x006633);
						td.setForeground(color);
						td.setText("속상해");
					}
				});
			}
			if (i==6) {
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = new Color(0xCC0000);
						td.setForeground(color);
						td.setText("화가 나--");
					}
				});
			}
			if (i==7) {
				btn[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color = new Color(0xFF3366);
						td.setForeground(color);
						td.setText("깜짝 놀랐어0o0");
					}
				});
			}
			fpanel.add(td);
			setVisible(true);
		}
		
		for (int i=0; i<8; i++) {
			btn[i].addMouseListener(new MouseAdapter(){
			    public void mousePressed(MouseEvent e) {
			    	HeartThread t= new HeartThread(e.getX(), e.getY());	
			    	t.start();
			    }
			});
		}
		
		Color color = new Color(0x99CCFF);
        ipanel.setBackground(color);
		ipanel.setBounds(10, 50, 400, 250);
		
		fpanel.setBounds(10, 320, 400, 20);
		
		epanel.setBackground(Color.white);
		epanel.add(ipanel);
		epanel.add("South", fpanel);
		
		setContentPane(epanel);
		setSize(600, 400);
		setVisible(true);
	}
	
	class HeartThread extends Thread {
    	int x,y;
    	
    	HeartThread(int x, int y){
    		this.x=x;
    		this.y=y;
    	}

    	public void run() {	
    		ImageIcon icon = new ImageIcon("images/choice.png");
    		JLabel heart = new JLabel(icon);
    		heart.setSize(110,110);
    		heart.setLocation(x, y);
    		epanel.add(heart);
                        
    		while(y>-30){
    			y-=20; 
    			try{
    				Thread.sleep(200); //0.2초마다
    			}catch(Exception e){
    				e.toString();
    			}
    			heart.setLocation(x+400, y);
    		}
    		epanel.remove(heart);
    	}
	}
	
	public static void main(String[] args) {
		new Feeling();
		
	}
}