package com.example.simeon.europeanroulettegame;

import java.util.HashMap;

public class CalculateDegree {
    private HashMap<Integer,Integer> degrees;

    public CalculateDegree() {
        this.degrees = new HashMap<>();
        populateHashMap();
    }

    private void populateHashMap(){
        double index = 9.73;
        degrees.put(0,0);
        degrees.put(26,(int)index);
        degrees.put(3,(int)(2*index));
        degrees.put(35,(int)(3*index));
        degrees.put(12,(int)(4*index));
        degrees.put(28,(int)(5*index));
        degrees.put(7,(int)(6*index));
        degrees.put(29,(int)(7*index));
        degrees.put(18,(int)(8*index));
        degrees.put(22,(int)(9*index));
        degrees.put(9,(int)(10*index));
        degrees.put(31,(int)(11*index));
        degrees.put(14,(int)(12*index));
        degrees.put(20,(int)(13*index));
        degrees.put(1,(int)(14*index));
        degrees.put(33,(int)(15*index));
        degrees.put(16,(int)(16*index));
        degrees.put(24,(int)(17*index));
        degrees.put(5,(int)(18*index));
        degrees.put(10,(int)(19*index));
        degrees.put(23,(int)(20*index));
        degrees.put(8,(int)(21*index));
        degrees.put(30,(int)(22*index));
        degrees.put(11,(int)(23*index));
        degrees.put(36,(int)(24*index));
        degrees.put(13,(int)(25*index));
        degrees.put(27,(int)(26*index));
        degrees.put(6,(int)(27*index));
        degrees.put(34,(int)(28*index));
        degrees.put(17,(int)(29*index));
        degrees.put(25,(int)(30*index));
        degrees.put(2,(int)(31*index));
        degrees.put(21,(int)(32*index));
        degrees.put(4,(int)(33*index));
        degrees.put(19,(int)(34*index));
        degrees.put(15,(int)(35*index));
        degrees.put(32,(int)(36*index));
    }
    public Integer getDegreeByNumber(Integer degree){
        return degrees.get(degree);
    }
}
