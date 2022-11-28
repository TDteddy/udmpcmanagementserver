package com.urban.ROI_System;

public class Sunil{
    int size = 100;
    float dv = size / 4;
    int radius = size / 2;

    float countDv = (1.14f /dv)+(1.14f % dv);
    float incDouble = 2.0f;
    int point[] = {0, 0};

    int beforeX=0;

    public static void main(String[] args) {

        System.out.println(546/8);

    }

    public void ddd(){
        countDv=countDv/((size/100)*3.7f);
        for (int y = 0; y < radius+(dv/2); y++) {
            if (y > radius) {
                point[1] = Math.abs(y - size);
            } else {
                point[1] = y;
            }

//            System.out.print(point[1]+": "+dv+"("+incDouble+")");

            for (int x = 0; x <= size; x++) {
                point[0] = x;
                if (x > radius) {
                    point[0] = Math.round(Math.abs(x - size));
                }
                int outputPoint=Math.round(size / incDouble);
                if (outputPoint == point[0] || (outputPoint < point[0]&&point[0]>beforeX)) {
                    System.out.print("■");
                } else {
                    System.out.print("　");
                }
//                System.out.printf("%3f",incDouble);
                beforeX = outputPoint;
            }
            // 감소
            if(y > radius) {
                if (y < dv) {
                    increase();
                } else {
                    decrease();
                }
            } else {
                // 증가
                if (y < dv) {
                    increase();
                } else {
                    decrease();
                }
            }
            System.out.println();
        }
    }
    public void increase(){
        incDouble = incDouble + countDv;
    }
    public void decrease(){
        incDouble = Math.abs(incDouble - countDv);
    }
}