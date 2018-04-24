/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author lukasgaedicke
 */
public class Home {
    
    private JFrame guiFrame;
    private JButton btnNext;
    private JButton btnExit;
    private Label lblAmount;
    private TextField fieldAmount;
    
    public Home() {
        createGUI();
    }
    
    private void createGUI() {
        guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Script Generator");
        guiFrame.setSize(300, 160);
        guiFrame.setLayout(null);
        guiFrame.setVisible(true);
        
        btnNext = new JButton("Next");
        btnNext.setBounds(140, 80, 100, 20);
        
        btnExit = new JButton("Exit");
        btnExit.setBounds(20, 80, 100, 20);
        
        lblAmount = new Label("Number of fields:");
        lblAmount.setBounds(20, 30, 110, 30);
        
        fieldAmount = new TextField();
        fieldAmount.setBounds(140, 35, 100, 20);
        
        guiFrame.add(fieldAmount);
        guiFrame.add(lblAmount);
        guiFrame.add(btnNext);
        guiFrame.add(btnExit);
        
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Generator generator = new Generator(Integer.parseInt(fieldAmount.getText()));
                guiFrame.dispose();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }
}
