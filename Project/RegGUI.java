import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RegGUI extends JFrame {
    private JPanel contentPane;
    private JPanel mainPane;
    private JLabel titleLabel;
    private CustomTextField userText;
    private CustomPasswordField passwordText;
    private JButton regButton;
	
    public RegGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("NAOIMISE Registration");

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));

        titleLabel = new JLabel("Registration");
        titleLabel.setFont(new Font("joystix monospace", Font.PLAIN, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        regButton = new CustomButton();
        regButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        regButton.setText("Register");
        regButton.setPreferredSize(new Dimension(200,50));
        regButton.setMinimumSize(regButton.getPreferredSize());
        regButton.setMaximumSize(regButton.getPreferredSize());
        regButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());

                try {
                    FileWriter fw = new FileWriter("data.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(user + "\t" + password);
                    bw.newLine();
                    bw.close();
                    fw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Registration successful!");
                dispose();
            }
        });

        mainPane.add(Box.createVerticalGlue());
        mainPane.add(titleLabel);
        mainPane.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPane.add(userText);
        mainPane.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPane.add(passwordText);
        mainPane.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPane.add(regButton);
        mainPane.add(Box.createVerticalGlue());

        contentPane.add(mainPane, BorderLayout.CENTER);

        setContentPane(contentPane);
    }
}


