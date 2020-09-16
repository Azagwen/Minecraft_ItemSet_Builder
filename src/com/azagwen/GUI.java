package com.azagwen;

import com.azagwen.builders.BlockModelBuilder;
import com.azagwen.builders.BlockstateBuilder;
import com.azagwen.builders.ItemModelBuilder;
import com.azagwen.builders.RecipeDataBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GUI implements ISBConstants {
    private JFrame frame = new JFrame("Minecraft Item Set Builder");
    private JButton pathButton = new JButton("Select output Folder");
    private String selectedDirectory = "";

    public GUI() {
        JPanel panel = new JPanel();
        JButton buildButton = new JButton("Build");
        Font font = new Font(null, Font.PLAIN, 24);
        JTextField[] textFields = {matTextBox, namespaceTextBox, typeTextBox, toolTextBox, armorTextBox, blockTextBox};
        JLabel[] labels = {toolLabel, armorLabel, texLabel, blockLabel};

        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setFont(font);
            if (i < 3) {
                textFields[i].setMargin(new Insets(0, 20, 0, 0));
                textFields[i].addKeyListener(lengthLimit(20, textFields[i]));
            } else {
                textFields[i].setHorizontalAlignment(SwingConstants.CENTER);
                textFields[i].addKeyListener(lengthLimit(10, textFields[i]));
            }
        }
        for (JLabel label : labels) {
            label.setFont(font);
        }

        pathButton.addActionListener(new SelectPath());
        buildButton.addActionListener(new BuildAction());
        console.setEditable(false);
        console.setHorizontalAlignment(SwingConstants.CENTER);

        pathButton.setFont(font);
        console.setFont(font);
        buildButton.setFont(font);

        int thirdWidth = 175;

        panel.setLayout(new GridBagLayout());
//        panel.add(matLabel, assignConstraint(0, 0, 1, 1, 0, new Insets(20, 40, 5, 20)));
        panel.add(matTextBox, assignConstraint(0, 0, 3, 1, 0, new Insets(20, 20, 5, 20)));

        panel.add(new JSeparator(), assignConstraint(0, 1, 3, 0.25F, 0, new Insets(15, 20, 0, 20)));

        panel.add(texLabel, assignConstraint(0, 2, 3, 1, 0, new Insets(0, 20, 0, 5)));
        panel.add(toolLabel, assignConstraint(0, 3, 1, 1, 0, new Insets(0, 20, 0, 5)));
        panel.add(armorLabel, assignConstraint(1, 3, 1, 1, 0, new Insets(0, 5, 0, 5)));
        panel.add(blockLabel, assignConstraint(2, 3, 1, 1, 0, new Insets(0, 5, 0, 20)));
        panel.add(toolTextBox, assignConstraint(0, 4, 1, 1, thirdWidth, new Insets(0, 20, 5, 5)));
        panel.add(armorTextBox, assignConstraint(1, 4, 1, 1, thirdWidth, new Insets(0, 5, 5, 5)));
        panel.add(blockTextBox, assignConstraint(2, 4, 1, 1, thirdWidth, new Insets(0, 5, 5, 20)));

        panel.add(new JSeparator(), assignConstraint(0, 5, 6, 0.25F, 0, new Insets(15, 20, 0, 20)));

//        panel.add(namespaceLabel, assignConstraint(0, 6, 1, 1, 0, new Insets(0, 40, 5, 5)));
        panel.add(namespaceTextBox, assignConstraint(0, 6, 3, 1, 0, new Insets(0, 20, 5, 20)));
//        panel.add(typeLabel, assignConstraint(0, 7, 1, 1, 0, new Insets(0, 40, 5, 20)));
        panel.add(typeTextBox, assignConstraint(0, 7, 3, 1, 0, new Insets(0, 20, 5, 20)));
        panel.add(pathButton, assignConstraint(0, 8, 3, 1, 0, new Insets(0, 20, 5, 20)));

        panel.add(new JSeparator(), assignConstraint(0, 9, 6, 0.25F, 0, new Insets(15, 20, 0, 20)));

        panel.add(buildButton, assignConstraint(0, 10, 3, 1, 0, new Insets(0, 20, 5, 20)));
        panel.add(console, assignConstraint(0, 11, 3, 1, 0, new Insets(0, 20, 20, 20)));

        frame.setVisible(true);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    private GridBagConstraints assignConstraint(
            int gridX,
            int gridY,
            int gridWidth,
            float weightY,
            int ipadX,
            Insets insets
    ) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.ipady = 20;

        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.gridwidth = gridWidth;
        gbc.weighty = weightY;
        gbc.ipadx = ipadX;
        gbc.insets = insets;

        return gbc;
    }

    protected static void delayedConsoleMSG(String msg, int delay) {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> console.setText(msg), delay, delay * 1000, TimeUnit.SECONDS);
    }

    private class BuildAction implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (!isFieldEmpty(matTextBox)) {
                if (!selectedDirectory.equals("")) {
                    File directory = new File(selectedDirectory + "\\ISB_output");
                    if (!directory.exists()) {
                        try {
                            Files.createDirectories(Paths.get(directory.toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    new RecipeDataBuilder(directory.getAbsolutePath());
                    new ItemModelBuilder(directory.getAbsolutePath());
                    new BlockModelBuilder(directory.getAbsolutePath());
                    new BlockstateBuilder(directory.getAbsolutePath());
                } else {
                    console.setForeground(ISB_red);
                    console.setText("Please select an output directory.");
                    delayedConsoleMSG("", 2);
                }
            } else {
                console.setForeground(ISB_red);
                console.setText("\"Material\" must be filled.");
                GUI.delayedConsoleMSG("", 2);
            }
        }
    }

    private class SelectPath implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int dialogEvent = chooser.showDialog(frame, "Select output folder.");
            if (dialogEvent == JFileChooser.APPROVE_OPTION) {
                pathButton.setText(chooser.getSelectedFile().getAbsolutePath());
                selectedDirectory = chooser.getSelectedFile().getAbsolutePath();
            }
            if (dialogEvent == JFileChooser.CANCEL_OPTION) {
            }
        }
    }

    private KeyAdapter lengthLimit(int limit, JTextField textBox) {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textBox.getText().length() >= limit )
                    e.consume();
            }
        };
    }

    private static boolean isFieldEmpty(JTextField field) {
        return field.getText().equals("");
    }
}