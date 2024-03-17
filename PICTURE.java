import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class PICTURE extends JFrame {
	    int calYear;
	    int calMonth;
	    int calDayOfMon;
	    JFrame frame = new JFrame();
	    JLabel imageLabel;
	    JLabel title, date;
	    JButton openItem, save, get;
	    JTextArea ta, ta1;
	    Font font;
	    JScrollPane scroll;
	    Calendar today = Calendar.getInstance();
	    public void setToday(){
	        calYear = today.get(Calendar.YEAR);
	        calMonth = today.get(Calendar.MONTH);
	        calDayOfMon = today.get(Calendar.DATE);
	    }

	    public PICTURE() {

	        setToday();

	        setTitle("오늘의 일기");
			

	        Container c= getContentPane();
	        c.setLayout(null);

	        title= new JLabel("오늘의 일기를 작성해주세요", title.CENTER);
	        font = new Font("Dialog", Font.BOLD, 20);
	        title.setFont(font);
			
	        title.setBounds(50,10,500,50);
	        
	        c.add(title);
	        
	        date= new JLabel("날짜를 입력하세요:");
	        date.setBounds(190,40,200,50);
	        c.add(date);
			
	        ta1= new JTextArea(10,1);
			
	        ta1.setBounds(310,58,100,20);
	        c.add(ta1);

	        imageLabel= new JLabel();
	        imageLabel.setBounds(140,85,320,220);
	        c.add( imageLabel);

	        openItem= new JButton("사진 추가하기");
	        openItem.addActionListener(new OpenActionListener());
	        openItem.setBounds(220,310,150,30);
	        c.add( openItem);

	        ta= new JTextArea(20,20);
	        scroll= new JScrollPane(ta);
	        scroll.setBounds(50,350,500,200);
	        c.add(scroll);

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
	                    File f= new File("MemoData");
	                    if(!f.isDirectory()) f.mkdir();

	                    String memo = ta.getText();
	                    if(memo.length()>0){
	                        BufferedWriter out = new BufferedWriter(new FileWriter("MemoData/"+ta1.getText()+".txt"));
	                        String str = ta.getText();
	                        out.write(str);
	                        out.close();
	                    }
	                    
	                } catch (IOException e) {
	                	e.printStackTrace();
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
	                File f = new File("MemoData/"+ta1.getText()+".txt");
	                if(f.exists()){
	                    BufferedReader in = new BufferedReader(new FileReader("MemoData/"+ta1.getText()+".txt"));
	                    String memoAreaText= new String();
	                    while(true){
	                        String tempStr = in.readLine();
	                        if(tempStr == null) break;
	                        memoAreaText = memoAreaText + tempStr + System.getProperty("line.separator");
	                    }
	                    ta.setText(memoAreaText);
	                    in.close();
	                }
	                else ta.setText("");
	                File i = new File("MemoData/"+ta1.getText()+".jpg");
	                if(i.exists()){
	                    BufferedImage I = ImageIO.read(new File("MemoData/"+ta1.getText()+".jpg"));
	                    imageLabel.setIcon(new ImageIcon(I));
	                }
	            	}catch (IOException e) {
	            		e.printStackTrace();
	            	}
	            }
	        });

			JButton BackButton=new JButton("뒤로 가기");
			
			c.add(BackButton);

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
	        
	        Color color = new Color(0xE6E6FA );
	        c.setBackground(color);

	        save.setBounds(110,570,100,30);
	        get.setBounds(240,570,100,30);
			BackButton.setBounds(370, 570,100,30);
	        c.add(save);
	        c.add(get);

	        setSize(600, 700);
	        setVisible(true);
	    }

	    class OpenActionListener implements ActionListener{
	        private JFileChooser chooser;

	        public OpenActionListener() {
	            chooser = new JFileChooser();
	        }

	        public void actionPerformed(ActionEvent e) {
	            FileNameExtensionFilter filter= new FileNameExtensionFilter("Images", "jpg", "gif", "jpeg","png");

	            chooser.setFileFilter(filter);
	            File folder = new File("MemoData");
	            if(!folder.isDirectory()) folder.mkdir();
	            int ret = chooser.showOpenDialog(null);
	            if (ret != JFileChooser.APPROVE_OPTION) {
	                JOptionPane.showMessageDialog(null, "파일을 선택해주세요.","경고", JOptionPane.WARNING_MESSAGE);

	                return;
	            }
	            try {
	                String filePath = chooser.getSelectedFile().getPath();
	                Image img = ImageIO.read(new File(filePath));
	                Image changeImg = img.getScaledInstance(320, 220, Image.SCALE_SMOOTH);
					BufferedImage changeIcon = new BufferedImage(320,220, BufferedImage.TYPE_INT_RGB);
	                Graphics g = changeIcon.getGraphics();
	                g.drawImage(img, 0, 0, null);
	                ImageIO.write(changeIcon, "jpg", new File("MemoData/"+ta1.getText()+".jpg"));
	                imageLabel.setIcon(new ImageIcon(filePath));
	            }catch(Exception e1){
	                System.out.println(e1);
	            }
	        }
	    }

	    public static void main( String [] args) {
	         new PICTURE();
	
	    }
	}