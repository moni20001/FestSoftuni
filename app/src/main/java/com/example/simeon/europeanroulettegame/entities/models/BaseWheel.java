package com.example.simeon.europeanroulettegame.entities.models;

import com.example.simeon.europeanroulettegame.entities.enums.colors;

import java.util.Random;

public class BaseWheel {
    private Integer number;

    public BaseWheel() {
        generateNumber();
    }

    public Integer getNumber() {
        return number;
    }

    public void generateNumber() {
        Random random = new Random();

        this.number = random.nextInt(37);
    }

    public String getColor() {
        if(this.number == 0){
            return String.valueOf(colors.GREEN);
        }
        else if(isEven()){
            return String.valueOf(colors.BLACK);
        }
        else if(!isEven()){
            return String.valueOf(colors.RED);
        }
        return null;
    }


    public boolean isEven() {
        return this.number%2==0;
    }


}
