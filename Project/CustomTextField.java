import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
 

public class CustomTextField extends JTextField  {

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private boolean animateHinText = true;
    private float location;
    private boolean show;
    private boolean mouseOver = false;
    private boolean mouseFocus = false;
    private String labelText = "";
    private Color lineColor = new Color(81, 92, 108);

    public CustomTextField() {
        setBorder(new EmptyBorder(20, 3, 10, 3));
        setBackground(new Color(203, 209, 219));
        setSelectionColor( new Color(147, 167, 196));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                repaint();
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
            	if (getText().equals(""))
            		location = 1f - location;
              	else
                    location = 1;
            	mouseFocus = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent fe) {
            	if (getText().equals(""))
            		location = 1f - location;
              	else
                    location = 1;
        		mouseFocus = false;
              	
                
                repaint();
            }
        });
    
        
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = getWidth();
        int height = getHeight();
        if (mouseOver || mouseFocus) {
            g2.setColor(lineColor);
            g2.fillRect(0, getHeight() - 5, getWidth(), 5);
        } else {
            g2.setColor(new Color(150, 150, 150));
            g2.fillRect(0, getHeight() - 2, getWidth(), 2);
        }
        createHintText(g2);
        g2.dispose();
    }

    private void createHintText(Graphics2D g2) {
        Insets in = getInsets();
        g2.setColor(new Color(150, 150, 150));
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(labelText, g2);
        double height = getHeight() - in.top - in.bottom;
        double textY = (height - r2.getHeight()) / 2;
        double size;
        if (animateHinText) {
            if (show) {
                size = 20 * (1 - location);
            } else {
                size = 20 * location;
            }
        } else {
            size = 20;
        }
        g2.drawString(labelText, in.right, (int) (in.top + textY + ft.getAscent() - size));
    }


    @Override
    public void setText(String string) {
        if (!getText().equals(string)) {
        	if (string.equals(""))
        		location = 1f - location;
        	else
        		location = 1;
        }
        super.setText(string);
    }
}
