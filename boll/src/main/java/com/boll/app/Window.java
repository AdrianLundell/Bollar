package com.boll.app;
import java.awt.*;
import javax.swing.*;

public class Window {

    static Box b;
    static GUI gui;
    static JFrame frame;
    final boolean INIT_START = true;
    final int INIT_BALLS = 4;
    final Dimension d = new Dimension(500, 500);

    protected Window() {
        frame = new JFrame("Bouncing balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(d);
        frame.setResizable(false);

        b = new Box(this);
        frame.add(b);
        frame.pack();      
      
        gui = new GUI(b, INIT_BALLS, INIT_START);   

        frame.setVisible(true);
    }

    public Dimension getDimension(){
        return d;
    }

    public static void main(String[] args) {
        Window w = new Window();
    }
}   