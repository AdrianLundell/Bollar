package com.boll.app;

import java.awt.Graphics;

public class Projectile extends Ball {

    public Projectile(Box b) {
        super(b, new Vector(Math.random(), Math.random()), Vector.randomVector((0.02+Math.random()*0.03)*0.2), 3);
    }

    @Override
    public void draw(Graphics g) {
        Vector v = toSpace(this.pos);
        double rtest = this.rad;

        int r = toSpace(rtest);
        int x = (int) v.getX();
        int y = (int) v.getY();

        g.setColor(this.col);
        g.fillOval(x - r, y - r, r * 2, r * 2);
        //    g.drawLine(0, 0, x, y);
    }
    
    @Override
    public void setPos(Vector v) {
          this.pos = v;
      }
    @Override
    public void move() {
        setPos(getPos().add(getVel()));
      }        

      protected void split(Ball b) {
        Vector bpos = b.getPos();
        Vector v = getVel().ortagonal();

        Vector diff = this.getPos().sub(bpos);
        diff = diff.project(v);
    
        double r1 = (b.getRad()+diff.length())*0.5;
        double r2 = Math.abs(b.getRad()-diff.length())*0.5;

        Vector p1 = this.pos.add(Vector.polar(-(r1+this.getRad()), diff.angle()));
        Vector p2 = this.pos.add(Vector.polar((r2+this.getRad()), diff.angle()));   
        
        Vector v1 = b.getVel().add(Vector.polar(getVel().length(), v.angle()));
        Vector v2 = b.getVel().add(Vector.polar(getVel().length(), v.angle()));  

        this.box.removeBall(b);
        if (toSpace(r1)>2){this.box.addBall(new Ball(this.box, p1, v1, toSpace(r1)));}
        if (toSpace(r2)>2){this.box.addBall(new Ball(this.box, p2, v2, toSpace(r2)));}
       }       
}