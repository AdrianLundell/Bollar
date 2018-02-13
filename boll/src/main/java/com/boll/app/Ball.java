package com.boll.app;

import java.awt.Color;

import org.w3c.dom.CDATASection;

import java.awt.*;

public class Ball {

  protected Vector vel;
  protected Vector pos;
  protected Color col;
  protected double rad;
  protected Box box;
  public enum Part {
    TOP, BOTTOM, RIGHT, LEFT
  }

  public Ball(Box b) {
    this.box = b;
    this.vel = Vector.randomVector(Math.random()*0.02);
    this.pos = new Vector(Math.random(), Math.random());
    this.col = new Color(0, 0, 0);
    this.rad = ((double) 15.0) / ((double)box.getSpaceSize());
  }

  public Ball(Box b, Vector pos, Vector vel, int rad) {
    this.box = b;
    this.vel = vel;
    this.pos = pos;
    this.col = new Color(0, 0, 0);
    this.rad = ((double) rad) / ((double)box.getSpaceSize());
  } 

  public void draw(Graphics g) {
    Vector v = toSpace(this.pos);
    int r = toSpace(this.getRad());
    int x = (int) v.getX();
    int y = (int) v.getY();

    g.setColor(this.col);
    g.drawOval(x - r, y - r, r * 2, r * 2);
    Vector vel = toSpace(this.vel).scale(10).add(v);

//    g.drawLine(x, y, (int)vel.getX(), (int)vel.getY());    //Show position
    g.drawLine(x, y, (int)vel.getX(), (int)vel.getY());  //Show velocity
  }

  public Vector toSpace(Vector v) {
    return v.scale(box.getSpaceSize());
  }

  public int toSpace(double d) {
    int i = new Double(d * box.getSpaceSize()).intValue();
    return i;
  }

  public void setVel(Vector v) {
    vel = v;
  }

  public void setPos(Vector v) {
    Vector vnew = collisionCorrection(v);
    this.pos = vnew;
  }

  public Vector collisionCorrection(Vector v) {
    if (getPos(Part.TOP) < 0) {
      setVel(getVel().flipSignY());
      return new Vector(v.getX(),getRad());
    }
    if (getPos(Part.BOTTOM) > 1) {
      setVel(getVel().flipSignY());
      return new Vector(v.getX(),1-getRad());
    }
    if (getPos(Part.RIGHT) > 1) {
      setVel(getVel().flipSignX());
      return new Vector(1-getRad(), v.getY());
    }
    if (getPos(Part.LEFT) < 0) {
      setVel(getVel().flipSignX());
      return new Vector(getRad(), v.getY());
    } 
    else
      return v;
  }

  public Vector getVel() {
    return this.vel;
  }

  public Vector getPos() {
    return this.pos;
  }

  public double getPos(Part p) {
    switch (p) {
    case TOP:
      return this.getPos().getY() - this.getRad();
    case BOTTOM:
      return this.getPos().getY() + this.getRad();
    case RIGHT:
      return this.getPos().getX() + this.getRad();
    case LEFT:
      return this.getPos().getX() - this.getRad();
    default:
      return -1;
    }

  }

  public double getRad() {
    return this.rad;
  }

  public void move(Vector v) {
    setPos(getPos().add(v));
  }

  public void move() {
    setPos(getPos().add(getVel()));
    setVel(Vector.polar(getVel().length()-0.00001, getVel().angle()));
  }

 public void collide(Ball b){
    Vector dPos = b.getPos().sub(getPos());
    Vector dVel = b.vel.sub(vel);
    double posSquared = Math.abs(dPos.length() * dPos.length());
    
    this.setVel(getVel().add(dPos.scale(dVel.dot(dPos) / posSquared)));
    b.setVel(b.getVel().add(dPos.scale(dVel.scale(-1).dot(dPos) / posSquared)));
  }

  protected void split(Projectile p) {
    Vector ppos = p.getPos();
    Vector pvel = p.getVel();
    Vector diff = this.getPos().sub(ppos);
    double diffAngle = pvel.angle(diff.add(this.pos));
    double diffLength = diff.length()*Math.sin(diffAngle);

    int a = toSpace(diffLength);
    int b = toSpace(this.rad);
    int i =1;


    // double r1=
    // double r2=
    // Vector pos1 =
    // Vector pos2 = =

    //  if(toSpace(r1)>2){
    //     Ball b1 = new Ball(this.box, pos1, this.vel, toSpace(r1));
    //     this.box.addBall(b1);
    //  }
    //  if(toSpace(r1)>2){    
    //     Ball b2 = new Ball(this.box, pos2, this.vel, toSpace(r2));
    //     this.box.addBall(b2);     
     
   }


  //link http://www.it.uu.se/edu/course/homepage/prog2/vt17/assignments/graphics/bouncingBalls/bouncingBalls.pdfimport java.awt.Color;
}