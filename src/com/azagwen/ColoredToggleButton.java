package com.azagwen;

import javax.swing.*;
import java.awt.*;

public class ColoredToggleButton extends JToggleButton {

    public ColoredToggleButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false); // used for demonstration
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color green = new Color(184, 207, 229);
        Color red = new Color(235, 245, 255);

        final Graphics2D g2 = (Graphics2D) g.create();

        if (this.isSelected())
            g2.setPaint(new GradientPaint(
                new Point(0, 0), green,
                new Point(0, getHeight()), green
            ));
        else
            g2.setPaint(new GradientPaint(
                new Point(0, 0), red,
                new Point(0, getHeight()), red
            ));

        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}
