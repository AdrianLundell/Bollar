package com.boll.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.boll.app.Ball.Part;

import java.util.ArrayList;

public class Box extends JPanel implements ActionListener {

  private ArrayList<Ball> BallList = new ArrayList<Ball>();
  private ArrayList<Projectile> ProjectileList = new ArrayList<Projectile>();

  private static Timer timer;
  private Window w;

  public Box(Window w) {
    this.w = w;
    setPreferredSize(w.getDimension());
    timer = new Timer(10, this);
  }

  protected void removeBall(Ball b) {
    BallList.remove(b);
  }

  protected void removeBall() {
    BallList.remove(0);
  }

  protected void removeProjectile(Projectile p) {
    ProjectileList.remove(p);
  }

  protected void addBall(Ball b) {
    BallList.add(b);
  }

  protected void addBall() {
    BallList.add(new Ball(this));
  }

  protected int getSpaceSize() {
    return this.getHeight();
  }

  public void start() {
    timer.start();
  }

  public void stop() {
    timer.stop();
  }

  public void setDelay(int t) {
    timer.setDelay(t);
  }

  public void changeBallAmnt(int n) {
    if (n < 0) {
      for (int i = 0; i < -n; i++) {
        removeBall();
      }
    } else {
      for (int i = 0; i < n; i++) {
        addBall();

      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Ball b : BallList) {
      b.draw(g);
    }
    for (Projectile p : ProjectileList) {
      p.draw(g);
    }
  }

  public void actionPerformed(ActionEvent e) {
    step();
  }

  private void spawnProjectile() {
    if (Math.random() > 0.99)
      ProjectileList.add(new Projectile(this));
  }

  private void step() {
    spawnProjectile();

    for (int i = 0; i < ProjectileList.size(); i++) {
      Projectile p = ProjectileList.get(i);
      p.move();
      if (p.getPos(Part.TOP) < 0 || p.getPos(Part.LEFT) < 0 || p.getPos(Part.RIGHT) > 1 || p.getPos(Part.BOTTOM) > 1)
        removeProjectile(p);
    }

    for (Ball b : BallList) {
      b.move();
    }
    checkBallHit();
    repaint();
  }

  private Ball[] checkSplit(Ball b1, Ball b2) {
    double a = Math.random();
    if (a < 0.3)
      return new Ball[] { b1, b2 };
    if (a > 0.7)
      return new Ball[] { b2, b1 };
    else
      return null;

  }

  protected void checkBallHit() {
    ArrayList<Ball> BallCheck = new ArrayList<Ball>(BallList);
    ArrayList<Projectile> ProjectileCheck = new ArrayList<Projectile>(ProjectileList);
    int size = BallList.size();
    for (int i = 0; i < size; i++) {
      Ball b = BallList.get(i);
      BallCheck.remove(b);
      for (Projectile p : ProjectileCheck) {
        if (b.getPos().distance(p.getPos()) <= b.getRad() + p.getRad()) {
          Vector diff = p.getPos().sub(b.getPos());
          b.split(diff);
          removeBall(b);
        }
      }
      for (Ball b2 : BallCheck) {
        if (b.getPos().distance(b2.getPos()) <= b.getRad() + b2.getRad()) {
          b.collide(b2);
        }

      }
    }
  }
}