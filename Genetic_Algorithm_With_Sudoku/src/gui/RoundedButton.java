package gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class RoundedButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoundedButton(String text) {
        super(text);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2), 
            BorderFactory.createLineBorder(new Color(128, 128, 128), 8)));
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 254));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g2);
        g2.dispose();
    }
}

