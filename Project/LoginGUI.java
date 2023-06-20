import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class LoginGUI implements ActionListener {

	private JPanel contentPane;
	private JPanel mainPane;
    private JButton logButton;
	private JButton regButton;
	private JLabel titleLabel;
    private CustomTextField userText;
    private CustomPasswordField passwordText;
    private JFrame frame;


	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
	}

	
	public LoginGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1100, 943);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("NAOIMISE");
		
		contentPane = new JPanel(new BorderLayout()) {
	        @Override public void paintComponent(Graphics g) {
	            try {
					g.drawImage(ImageIO.read(new File("Background.png")), 0, 0, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		mainPane = new JPanel();	
		mainPane.setOpaque(false);
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
		

		Dimension buttonSize = new Dimension(300,50);
		
		titleLabel = new JLabel("LOGIN");
		titleLabel.setOpaque(false);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setFont(new Font("joystix monospace", Font.PLAIN, 50));
		
		
		userText = new CustomTextField();
		userText.setAlignmentX(Component.CENTER_ALIGNMENT);
		userText.setLabelText("USERNAME");
		userText.setFont(new Font("joystix monospace", Font.PLAIN, 18));
		userText.setPreferredSize( new Dimension(300,60));
		userText.setMinimumSize(userText.getPreferredSize());
		userText.setMaximumSize(userText.getPreferredSize());

	
		passwordText = new CustomPasswordField();
		passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordText.setLabelText("PASSWORD");
		passwordText.setFont(new Font("joystix monospace", Font.PLAIN, 18));
		passwordText.setPreferredSize( new Dimension(300,60));
		passwordText.setMinimumSize(userText.getPreferredSize());
		passwordText.setMaximumSize(userText.getPreferredSize());

		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(Box.createVerticalGlue());
		box.add(mainPane);
		box.add(Box.createVerticalGlue());
		

        logButton = new CustomButton();
        logButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logButton.setText("Login");
		logButton.setFont(new Font("joystix monospace", Font.PLAIN, 18));
        logButton.setPreferredSize(buttonSize);
        logButton.setMinimumSize(buttonSize);
        logButton.setMaximumSize(buttonSize);
        logButton.addActionListener(this); 
		
		
		regButton = new CustomButton();
		regButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		regButton.setText("Register");
		regButton.setFont(new Font("joystix monospace", Font.PLAIN, 18));
		regButton.setPreferredSize(buttonSize);
		regButton.setMinimumSize(buttonSize);
		regButton.setMaximumSize(buttonSize);
	    regButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ae) {
	            // Open registration window
	            RegGUI register = new RegGUI();
	            register.setVisible(true);
	        }
	    });
	    
	    JPanel btnPanel = new JPanel(new FlowLayout());
	    btnPanel.setOpaque(false);
	    btnPanel.add(logButton);
	    btnPanel.add(regButton);
	    
	   

		mainPane.add(titleLabel);
		mainPane.add(Box.createRigidArea(new Dimension(0,100)));
		mainPane.add(userText);
		mainPane.add(Box.createRigidArea(new Dimension(0,10)));
		mainPane.add(passwordText);
		mainPane.add(Box.createRigidArea(new Dimension(0,100)));
		mainPane.add(btnPanel);
		
		contentPane.add(box, BorderLayout.CENTER);

		frame.setContentPane(contentPane);
		frame.setVisible(true);
	}
	
	
	@Override
    public void actionPerformed(ActionEvent ae) {
        boolean matched = false;
        String user = userText.getText().toString();
        String password = new String(passwordText.getPassword());

        try{
	        FileReader fr = new FileReader("data.txt");
	        BufferedReader br = new BufferedReader(fr);
	        String line;
	        while((line=br.readLine())!=null){
	        	
	            if(line.equals(user + "\t" + password)){
	                matched = true;
	                break;
	            }
	        }
	        fr.close();
	    }catch(Exception e){}
        
        if(matched){

            JOptionPane.showMessageDialog(frame, "Welcome!");

            frame.dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
        

        }
	        
    }
}
