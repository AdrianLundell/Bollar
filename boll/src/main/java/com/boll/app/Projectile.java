package com.boll.app;

import java.awt.Graphics;

public class Projectile extends Ball {

    public Projectile(Box b) {
        super(b, new Vector(Math.random(), Math.random()), Vector.randomVector((0.02+Math.random()*0.03)*0.01), 3);
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
}