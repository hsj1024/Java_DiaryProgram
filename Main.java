import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
	private Image back;
    JLabel la1, la2;
    JTextField tf;
    JPasswordField pf;
    JButton b1, b2;
    JScrollPane scrollPane;

    public Main() {
        back = Toolkit.getDefaultToolkit().getImage("images/diary.png");

        JPanel background = new JPanel(){
            public void paintComponent(Graphics g){
                g.drawImage(back,0,0,getWidth(),getHeight(),this);
                setOpaque(false);
                super.paintComponent(g);
            }
        };

        background.setLayout(null);
        la1 = new JLabel("ID", JLabel.RIGHT);
        la1.setBounds(30, 300, 80, 30);
        tf = new JTextField();
        tf.setBounds(120, 300, 150, 30);
        background.add(la1);
        background.add(tf);

        la2 = new JLabel("PW", JLabel.RIGHT);
        la2.setBounds(30, 340, 80, 30);
        pf = new JPasswordField();
        pf.setBounds(120, 340, 150, 30);
        background.add(la2);
        background.add(pf);

        b1 = new JButton("LOGIN");
        b2 = new JButton("CANCEL");
        JPanel p = new JPanel();
        p.add(b1);
        p.add(b2);
        p.setOpaque(false);
        p.setBounds(77, 380, 235, 35);
        background.add(p);

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("");
                pf.setText("");
				JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
				setVisible(false);
            }
        });

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar cl = new Calendar();

				Dimension frameSize = cl.getSize();
				Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
				cl.setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
			    setVisible(false);
				JOptionPane.showMessageDialog(null, "다이어리 프로그램을 실행합니다.");
            }
        });

        scrollPane = new JScrollPane(background);
        setContentPane(scrollPane);
        setTitle("DIARY");
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,500);
		Dimension frameSize = frame.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((windowSize.width - frameSize.width) / 2,
		(windowSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

}
