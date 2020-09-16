package com.azagwen;

import javax.swing.*;
import java.awt.*;

//Taken from https://stackoverflow.com/a/24571681/14268047

public class HintTextField extends JTextField {
    private final String hint;

    public HintTextField(String hint) {
        this.hint = hint;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
}
