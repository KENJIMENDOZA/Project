import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.*;
 

public class CustomButton extends JButton {
	public CustomButtonStyle getStyle() {
        return style;
    }

    public void setStyle(CustomButtonStyle style) {
        if (this.style != style) {
            this.style = style;
            currentStyle.changeStyle(style);
            setForeground(style.foreground);
        }
    } 

    private CustomButtonStyle style = CustomButtonStyle.PRIMARY;
    private ButtonColor currentStyle = new ButtonColor(CustomButtonStyle.PRIMARY);

    public CustomButton() {
    	try {
    	     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./src/joystix monospace.otf")));
    	} catch (IOException|FontFormatException e) {

    	}
    	
    	setFont(new Font("joystix monospace", Font.PLAIN, 25));
    	setFocusPainted(false);
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
            	currentStyle.backgroundHover = getStyle().backgroundHover;
            	setFont(new Font("joystix monospace", Font.BOLD, 25));
            }

            @Override
            public void mouseExited(MouseEvent me) {
            	currentStyle.backgroundHover = getStyle().background;
            	setFont(new Font("joystix monospace", Font.PLAIN, 25));
            }

            @Override
            public void mousePressed(MouseEvent me) {
            	currentStyle.background = getStyle().backgroundPress;
            	setFont(new Font("joystix monospace", Font.BOLD, 24));
            }

            @Override 
            public void mouseReleased(MouseEvent me) {
            	currentStyle.background = getStyle().background;
            	setFont(new Font("joystix monospace", Font.BOLD, 25));
            }
        });
    }


    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Area area = new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.setColor(currentStyle.background);
        g2.fill(area);
        area.subtract(new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight() - 5)));
        g2.setColor(currentStyle.backgroundHover);
        g2.fill(area);
        g2.dispose();
        
        super.paintComponent(grphcs);
    }

    public enum CustomButtonStyle {
        PRIMARY(new Color(203, 209, 219), new Color(10, 10, 10), new Color(81, 92, 108), new Color(230, 239, 255));

        private CustomButtonStyle(Color background, Color foreground, Color backgroundHover, Color backgroundPress) {
            this.background = background;
            this.foreground = foreground;
            this.backgroundHover = backgroundHover;
            this.backgroundPress = backgroundPress;
        }
        private Color background;
        private Color foreground;
        private Color backgroundHover;
        private Color backgroundPress;
    }

    protected class ButtonColor {

        public Color getBackground() {
            return background;
        }

        public void setBackground(Color background) {
            this.background = background;
        }

        public Color getForeground() {
            return foreground;
        }

        public void setForeground(Color foreground) {
            this.foreground = foreground;
        }

        public Color getBackgroundHover() {
            return backgroundHover;
        }

        public void setBackgroundHover(Color backgroundHover) {
            this.backgroundHover = backgroundHover;
        }

        public Color getBackgroundPress() {
            return backgroundPress;
        }

        public void setBackgroundPress(Color backgroundPress) {
            this.backgroundPress = backgroundPress;
        }

        public ButtonColor(CustomButtonStyle style) {
            changeStyle(style);
        }

        public ButtonColor() {
        }

        private Color background;
        private Color foreground;
        private Color backgroundHover;
        private Color backgroundPress;

        private void changeStyle(CustomButtonStyle style) {
            this.background = style.background;
            this.foreground = style.foreground;
            this.backgroundHover = style.background;
            this.backgroundPress = style.backgroundPress;
        }
    }

}
