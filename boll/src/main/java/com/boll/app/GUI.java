package com.boll.app;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.util.Scanner;

public class GUI extends JFrame implements ActionListener{
  
  private JButton onoff = new JButton("Stop");
  private JButton cancel = new JButton("Cancel");     
  private JButton restart = new JButton("Restart");         
  private JTextField objects = new JTextField("10",5);
  private JTextField rate = new JTextField("10",5);  
  private Box b;  
  private int ballAmnt;
  private boolean isRunning;

  public GUI(Box b, int initBalls, boolean initStart){    
    super("GUI");   
    this.ballAmnt = initBalls;
    this.isRunning = !initStart;
    this.b=b;
    this.setLayout(new FlowLayout());

    this.add(onoff); 
    this.add(cancel);    
    this.add(restart);     
    this.add(objects);     
    this.add(rate);  

    onoff.addActionListener(this);     
    cancel.addActionListener(this);       
    restart.addActionListener(this);   
    objects.addActionListener(this);     
    rate.addActionListener(this);   

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
    this.setSize(200, 300);   
    this.pack();
    this.setVisible(true);

   init();
    
  }

  public void init(){
  this.actionPerformed(new ActionEvent(onoff, ActionEvent.ACTION_PERFORMED, null));
  this.actionPerformed(new ActionEvent(objects, ActionEvent.ACTION_PERFORMED, null));
  this.actionPerformed(new ActionEvent(rate, ActionEvent.ACTION_PERFORMED, null)); 
  }

  public void run() {
    if (isRunning) {
      b.stop();
      onoff.setText("Start");
      isRunning = false;
    } else {
      b.start();
      onoff.setText("Stop");
      isRunning = true;
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == onoff){
      run();
    }
    if (e.getSource() == cancel){
        System.exit(0);  
    }
    if (e.getSource() == restart){ 
      b.changeBallAmnt(-ballAmnt);
      b.changeBallAmnt(ballAmnt);  
    }
    if (e.getSource() == objects){    
      Scanner s = new Scanner(objects.getText());    
      if (s.hasNextInt()){
        int n = s.nextInt();
        b.changeBallAmnt(n-ballAmnt);
        ballAmnt=n;
      }
      s.close();
    }
    if (e.getSource() == rate){
      Scanner s = new Scanner(rate.getText());
      if (s.hasNextInt())
        b.setDelay(s.nextInt());
      s.close();
    }
  }
}
