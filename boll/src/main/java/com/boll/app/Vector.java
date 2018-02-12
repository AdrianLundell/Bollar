package com.boll.app;
import java.util.Locale;

public class Vector {
  
  private final double x, y;
   
  public Vector(double x, double y){
    this.x = x;
    this.y = y;
  }
  
  
  public Vector add(Vector v){
    return new Vector(getX() + v.getX(), getY() + v.getY());
  }
  
  public double angle(){
    return Math.atan2(getY(), getX());
  }
  
  public double distance(Vector v){
    return Math.sqrt((getX()-v.getX())*(getX()-v.getX())+(getY()-v.getY())*(getY()-v.getY()));
  }
  
  public double dot(Vector v){
    return length()*v.length()*Math.cos(angle()-v.angle());
  }
  
  public Vector flipSignX(){
    return new Vector(-getX(), getY());
  }
  
  public Vector flipSignY(){
    return new Vector(getX(), -getY());    
  }  
  
  public double getX(){
    return this.x;
  }
  
  public double getY(){
    return this.y;
  }  

  public double length(){
    return Math.sqrt(getX()*getX()+getY()*getY());
  }  

  public static Vector polar(double length, double angle){
    return new Vector(length*Math.cos(angle), length*Math.sin(angle));
  }
  
  public static Vector randomVector(double len){
    return polar(len, Math.random()*2*Math.PI);
  }

  public Vector scale(double d){
    return polar(length()*d, angle());
  }
  
  public Vector sub(Vector v){
    return new Vector(getX() - v.getX(), getY() - v.getY());
  }
  
  public String toString(){
    return String.format(Locale.ROOT, "<x:%.3f, y:%.3f>(length:%.2f, angle:%.2f)",getX(),getY(),length(),angle());
  }
  
  public static void main(String[] args){//Testmetod
   
    Vector v1, v2; 
    v1 = randomVector(Math.random());
    v2 = randomVector(Math.random());
    
    System.out.println(v1 + " " + v2);
    System.out.println("");    
    System.out.println("Flipped. " + v1.flipSignY() + " " +v2.flipSignX());    
    System.out.println("Added. " + v1.add(v2));    
    System.out.println("Subbed. " + v1.sub(v2));
    System.out.println("Distance. "+v1.distance(v2));
    System.out.println("Scale. " + v1.scale(10));    
    System.out.println("Dotted. " + v1.dot(v2));    
  }
}
  
//Specification: http://www.it.uu.se/edu/course/homepage/prog2/ht16/assignments/graphics/bouncingBalls/doc/