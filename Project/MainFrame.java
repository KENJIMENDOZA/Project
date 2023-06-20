import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JPanel productPanel;
    protected JButton deliverButton;
    private JButton searchButton;
    protected JButton cartButton;
    private JLabel breadLabel;
    private JButton breadMinusButton;
    private JButton breadPlusButton;
    private JLabel chocolateLabel;
    private JButton chocolateMinusButton;
    private JButton chocolatePlusButton;
    private JLabel ramenLabel;
    private JButton ramenMinusButton;
    private JButton ramenPlusButton;
	private JLabel cartSizeLabel;
	private ArrayList<String> shoppingCart;
    private Map<String, ImageIcon> productImages;
	
//MainFrame
    public MainFrame() {

        setTitle("NAOIMISE");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1100, 943);
        setResizable(false);
        setLayout(null);

        // Create a background label with the specified image
        ImageIcon backgroundImage = new ImageIcon("Background.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        // Create and customize the title label
        JLabel titleLabel = new JLabel("NAOIMISE");
        titleLabel.setBounds(0, 20, getWidth(), 50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("joystix monospace", Font.BOLD, 50));
        backgroundLabel.add(titleLabel);

        // Create the product panel and set its properties
        productPanel = new JPanel();
        productPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Set FlowLayout for horizontal and centered positioning
        productPanel.setOpaque(false);
        productPanel.setBounds(100, 200, getWidth() - 200, getHeight() - 300);
        backgroundLabel.add(productPanel);

        // Create and customize the buttons
        deliverButton = new JButton("Deliver");
        deliverButton.setBounds(20, 100, 200, 50);
        deliverButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        backgroundLabel.add(deliverButton);

        searchButton = new JButton("SEARCH");
        searchButton.setBounds(getWidth() - 220, 100, 200, 50);
        searchButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        backgroundLabel.add(searchButton);

        cartButton = new JButton("Cart");
        cartButton.setBounds(getWidth() - 220, 160, 200, 50);
        cartButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        backgroundLabel.add(cartButton);

		breadLabel = new JLabel("Bread");
		ImageIcon bread = new ImageIcon("Bread.png");
		breadLabel.setIcon(bread);

        // breadLabel = new JLabel(new ImageIcon("Bread.png"));

        breadMinusButton = new JButton("-");
        breadPlusButton = new JButton("+");
        breadMinusButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        breadPlusButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        customizeProductButton(breadLabel, breadMinusButton, breadPlusButton);

		chocolateLabel = new JLabel("Chocolate");
		ImageIcon chocolate = new ImageIcon("Chocolate.png");
		chocolateLabel.setIcon(chocolate);


        // chocolateLabel = new JLabel(new ImageIcon("Chocolate.png"));

        chocolateMinusButton = new JButton("-");
        chocolatePlusButton = new JButton("+");
        chocolateMinusButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        chocolatePlusButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        customizeProductButton(chocolateLabel, chocolateMinusButton, chocolatePlusButton);

		ramenLabel = new JLabel("Ramen");
		ImageIcon ramen = new ImageIcon("Ramen.png");
		ramenLabel.setIcon(ramen);



     
        ramenMinusButton = new JButton("-");
        ramenPlusButton = new JButton("+");
        ramenMinusButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        ramenPlusButton.setFont(new Font("joystix monospace", Font.BOLD, 20));
        customizeProductButton(ramenLabel, ramenMinusButton, ramenPlusButton);

        // Adjust dimensions and positions for the product elements
        int labelWidth = 220;
        int labelHeight = 180;
        int buttonWidth = 60;
        int buttonHeight = 50;

        breadLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        breadMinusButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        breadPlusButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        chocolateLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
        chocolateMinusButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        chocolatePlusButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        // Create separate panels for bread and chocolate labels along with their buttons
        JPanel breadPanel = new JPanel();
        breadPanel.setOpaque(false);
        breadPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        breadPanel.add(breadLabel);
        breadPanel.add(breadMinusButton);
        breadPanel.add(breadPlusButton);

        JPanel chocolatePanel = new JPanel();
        chocolatePanel.setOpaque(false);
        chocolatePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        chocolatePanel.add(chocolateLabel);
        chocolatePanel.add(chocolateMinusButton);
        chocolatePanel.add(chocolatePlusButton);

        // Create a separate panel for the ramen label and its buttons
        JPanel ramenPanel = new JPanel();
        ramenPanel.setOpaque(false);
        ramenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        ramenPanel.add(ramenLabel);
        ramenPanel.add(ramenMinusButton);
        ramenPanel.add(ramenPlusButton);

        // Add the product elements to the product panel
        productPanel.add(breadPanel);
        productPanel.add(chocolatePanel);
        productPanel.add(ramenPanel);

        // Initialize product images
        productImages = new HashMap<>();
        productImages.put("Bread", new ImageIcon("Bread.png"));
        productImages.put("Chocolate", new ImageIcon("Chocolate.png"));
        productImages.put("Ramen", new ImageIcon("Ramen.png"));

		//Cart frame
		cartSizeLabel = new JLabel("Cart Size: 0");
		cartSizeLabel.setBounds(20, 160, 200, 50);
		cartSizeLabel.setFont(new Font("joystix monospace", Font.BOLD, 20));
		backgroundLabel.add(cartSizeLabel);

		//Initialize Shopping cart

		shoppingCart = new ArrayList<>();

		//Add Action Listener to Cart Button
		cartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
					showCartDialog();
		}
		});

		//Add action listener to deliver Button
		deliverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				 	DeliverWindow();
			}
		});

        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSearchWindow();
            }
        });

        setVisible(true);

		//method to add to cart
    }private void addToCart(String product){
		shoppingCart.add(product);
		updateCartSizeLabel();

		//updates the cart
	}private void updateCartSizeLabel(){
		cartSizeLabel.setText("Cart Size: " + shoppingCart.size());

		//shows Item and displays the updated Cart
	}private void showCartDialog() {
    StringBuilder cartContent = new StringBuilder();
    for (String item : shoppingCart) {
        cartContent.append(item).append("\n");
    }
    JOptionPane.showMessageDialog(MainFrame.this, cartContent.toString(), "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);

		//remove from cart
} private void removeFromCart(String product){
	shoppingCart.remove(product);
	updateCartSizeLabel();

}
		// Custom Buttons
private void customizeProductButton(JLabel productLabel, JButton minusButton, JButton plusButton) {
    productLabel.setFont(new Font("joystix monospace", Font.BOLD, 40));
    productLabel.setForeground(Color.WHITE); // Set label text color
    minusButton.setFont(new Font("joystix monospace", Font.BOLD, 30));
    plusButton.setFont(new Font("joystix monospace", Font.BOLD, 30));
    Color buttonColor = new Color(255, 255, 255);
    minusButton.setBackground(buttonColor);
    plusButton.setBackground(buttonColor);

 	plusButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addToCart(productLabel.getText());
        }
    });

    minusButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeFromCart(productLabel.getText());
        }
    });

    minusButton.setPreferredSize(new Dimension(60, 50));
    plusButton.setPreferredSize(new Dimension(60, 50));
}

	

	//Delivery Frame
	private void DeliverWindow() {
    JFrame deliverFrame = new JFrame("Delivery Details");
    deliverFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    deliverFrame.setFont(new Font("joystix monospace", Font.BOLD, 30));
    deliverFrame.setSize(500, 500);
    deliverFrame.setLayout(new BorderLayout());

    // Create a JTextArea to display the cart content
    JTextArea cartTextArea = new JTextArea(10, 20);
    cartTextArea.setEditable(false);
    JScrollPane cartScrollPane = new JScrollPane(cartTextArea);

    // Create other components for delivery details
    JLabel firstNameLabel = new JLabel("First Name:");
    JTextField firstNameField = new JTextField(15);
    JLabel lastNameLabel = new JLabel("Last Name:");
    JTextField lastNameField = new JTextField(15);
    JLabel addressLabel = new JLabel("Address:");
    JTextField addressField = new JTextField(15);
    JLabel cardNumberLabel = new JLabel("Card Number:");
    JTextField cardNumberField = new JTextField(15);
    JButton payButton = new JButton("Pay");

    // Add components to the deliverFrame
    JPanel deliveryPanel = new JPanel();
    deliveryPanel.setLayout(new GridLayout(6, 2));
    deliveryPanel.add(firstNameLabel);
    deliveryPanel.add(firstNameField);
    deliveryPanel.add(lastNameLabel);
    deliveryPanel.add(lastNameField);
    deliveryPanel.add(addressLabel);
    deliveryPanel.add(addressField);
    deliveryPanel.add(cardNumberLabel);
    deliveryPanel.add(cardNumberField);
    deliveryPanel.add(new JLabel()); // empty label for spacing
    deliveryPanel.add(payButton);

    deliverFrame.add(cartScrollPane, BorderLayout.NORTH);
    deliverFrame.add(deliveryPanel, BorderLayout.CENTER);

    // Display the cart content in the JTextArea
    StringBuilder cartContent = new StringBuilder();
    for (String item : shoppingCart) {
        cartContent.append(item).append("\n");
    }
    cartTextArea.setText(cartContent.toString());

    deliverFrame.setVisible(true);

    payButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == payButton) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String cardNumber = cardNumberField.getText();

                // Perform payment processing or save user information
                // You can add your payment logic or database storage code here

                JOptionPane.showMessageDialog(deliverFrame, "Payment Successful!\nThank you, " + firstName + " " + lastName + "!");
            }
        }
    });
}

	




    private void showSearchWindow() {
        JFrame searchFrame = new JFrame("Search Products");
        searchFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchFrame.setFont(new Font("joystix monospace", Font.BOLD, 30));
        searchFrame.setSize(400, 300);
        searchFrame.setLayout(new FlowLayout());
        
        // Create search label and text field
        JLabel searchLabel = new JLabel("Search: ");
        JTextField searchField = new JTextField(20);
        searchLabel.setFont(new Font("joystix monospace", Font.BOLD, 10));
        
        // Create search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("joystix monospace", Font.BOLD, 10));
        
        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (productImages.containsKey(searchText)) {
                    // If the searched product exists, create and show a new window with the product image
                    JFrame resultFrame = new JFrame("Search Result");
                    resultFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    resultFrame.setSize(400, 400);
                    resultFrame.setLayout(new FlowLayout());
                    
                    JLabel resultLabel = new JLabel(productImages.get(searchText));
                    resultFrame.add(resultLabel);
                    
                    resultFrame.setVisible(true);
                }
            }
        });

		


        
        // Add components to the search frame
        searchFrame.add(searchLabel);
        searchFrame.add(searchField);
        searchFrame.add(searchButton);
        
        searchFrame.setVisible(true);
    }

    //  public static void main(String[] args) {
    //      new MainFrame();
    //  }
}
