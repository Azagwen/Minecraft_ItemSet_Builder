package com.azagwen;

import org.json.JSONObject;

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

        toolFolderTextBox.addKeyListener(lengthLimit(10, toolFolderTextBox));
        toolFolderTextBox.setFont(font);
        toolLabel.setFont(font);
        armorFolderTextBox.addKeyListener(lengthLimit(10, armorFolderTextBox));
        armorFolderTextBox.setFont(font);
        armorLabel.setFont(font);
        matTextBox.addKeyListener(lengthLimit(10, matTextBox));
        matTextBox.setFont(font);
        matLabel.setFont(font);
        namespaceTextBox.addKeyListener(lengthLimit(10, namespaceTextBox));
        namespaceTextBox.setFont(font);
        namespaceLabel.setFont(font);
        pathButton.addActionListener(new SelectPath());
        pathButton.setFont(font);
        buildButton.addActionListener(new BuildAction());
        buildButton.setFont(font);
        console.setEditable(false);
        console.setFont(font);
        console.setHorizontalAlignment(SwingConstants.CENTER);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panel.add(matLabel, assignConstraint(0, 0, 1, 1, 0, new Insets(20, 20, 5, 5)));
        panel.add(matTextBox, assignConstraint(1, 0, 1, 1, 240, new Insets(20, 5, 5, 20)));
        panel.add(new JSeparator(), assignConstraint(0, 1, 2, 0.25F, 0, new Insets(0, 20, 5, 20)));
        panel.add(toolLabel, assignConstraint(0, 2, 1, 1, 0, new Insets(0, 20, 5, 5)));
        panel.add(toolFolderTextBox, assignConstraint(1, 2, 1, 1, 240, new Insets(0, 5, 5, 20)));
        panel.add(armorLabel, assignConstraint(0, 3, 1, 1, 0, new Insets(0, 20, 5, 5)));
        panel.add(armorFolderTextBox, assignConstraint(1, 3, 1, 1, 240, new Insets(0, 5, 5, 20)));
        panel.add(namespaceLabel, assignConstraint(0, 4, 1, 1, 0, new Insets(0, 20, 5, 5)));
        panel.add(namespaceTextBox, assignConstraint(1, 4, 1, 1, 240, new Insets(0, 5, 5, 20)));
        panel.add(pathButton, assignConstraint(0, 5, 2, 1, 0, new Insets(0, 20, 5, 20)));
        panel.add(new JSeparator(), assignConstraint(0, 6, 2, 0.25F, 0, new Insets(0, 20, 5, 20)));
        panel.add(buildButton, assignConstraint(0, 7, 2, 1, 0, new Insets(0, 20, 5, 20)));
        panel.add(console, assignConstraint(0, 8, 2, 1, 0, new Insets(0, 20, 20, 20)));

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
            if (!selectedDirectory.equals("")) {
                if (!isFieldEmpty(matTextBox)) {
                    File directory = new File(selectedDirectory + "\\ISB_output");
                    if (!directory.exists()) {
                        try {
                            Files.createDirectories(Paths.get(directory.toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    new RecipeDataBuilder(directory.getAbsolutePath());
                    new ItemModelMaker(directory.getAbsolutePath());
                } else {
                    console.setText("\"Material\" must be filled.");
                    GUI.delayedConsoleMSG("", 2);
                }
            } else {
                console.setForeground(ISB_red);
                console.setText("Please select an output directory.");
                delayedConsoleMSG("", 2);
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