/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller_Generetor;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import utils.OpenFIle;

/**
 *
 * @author lukasgaedicke
 */
public class Generator {

    private ArrayList<TextField> textFieldList = new ArrayList<TextField>();
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    private ArrayList<Label> labelList = new ArrayList<Label>();

    private Controller_Generetor controller_Generetor;
    private JFrame guiFrame;
    private JTextArea textArea;
    private Button btnExit;
    private Button btnGenerate;
    private OpenFIle openFIle;

    public Generator(int cont) {
        createGUI(cont);

    }

    private void createGUI(int value) {
        guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Script Generator");
        guiFrame.setSize(330, ((value + 3) * 20) + 130);

        for (int i = 0; i < value; i++) {
            textFieldList.add(new TextField());
            buttonList.add(new Button());
            labelList.add(new Label("Field Name:"));
        }

        for (int i = 0; i < textFieldList.size(); i++) {

            labelList.get(i).setBounds(10, 1 + (i * 20), 100, 20);

            textFieldList.get(i).setBounds(90, 1 + (i * 20), 100, 20);

            buttonList.get(i).setBounds(190, 1 + (i * 20), 100, 20);
            buttonList.get(i).setLabel("Path File...");
            buttonList.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    openFIle = new OpenFIle();
                    File f = chooseFile();
                    textArea.append("File " + f.getName() + " loaded. \n");
                    Controller_Generetor.listStrings.add(openFIle.readFile(f.getAbsolutePath()));

                }
            });

            guiFrame.add(textFieldList.get(i));
            guiFrame.add(buttonList.get(i));
            guiFrame.add(labelList.get(i));
        }

        btnGenerate = new Button("Generate");
        btnGenerate.setBounds(190, (value + 1) * 20, 100, 20);
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                captureFields();
                controller_Generetor = new Controller_Generetor();
                controller_Generetor.generateFile();
                textArea.append("Variable file generated.");
                textArea.append("Script file generated.");

            }
        });
        btnExit = new Button("Exit");
        btnExit.setBounds(90, (value + 1) * 20, 100, 20);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        textArea = new JTextArea();
        textArea.setBounds(10, (value + 3) * 20, 300, 100);
        textArea.setEnabled(false);

        guiFrame.add(textArea);
        guiFrame.add(btnGenerate);
        guiFrame.add(btnExit);
        guiFrame.setLayout(null);
        guiFrame.setVisible(true);

    }

    private void captureFields() {
        for (int i = 0; i < textFieldList.size(); i++) {
            Controller_Generetor.fieldNameList.add(textFieldList.get(i).getText());
        }
    }

    private File chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(
                new File("."));
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".*")
                        || f.isDirectory();
            }

            public String getDescription() {
                return ".*";
            }
        }
        );
        int r = chooser.showOpenDialog(new JFrame());
        if (r == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

}
