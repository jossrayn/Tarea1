/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rayn0
 */
public class Data {
    private int pc; //mantendra el numero de la siguiente instruccion que se va a ejecutar
    private int ir; //mantendra el numero de la instruccion que se esta ejecutando
    private int ac; //mantendra el valor de lo que se haya guardado en acumulador
    private int ax; //manejo del valor del registro ax
    private int bx; //manejo del valor del registro bx
    private int cx; //manejo del valor del registro cx
    private int dx; //manejo del valor del registro dx

    
    public Data(int ir, int pc) {
        this.pc = pc;
        this.ir = ir;
        this.ac = 0;
        this.ax = 0;
        this.bx = 0;
        this.cx = 0;
        this.dx = 0;
    }
    
    public Data(){}
    
    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getIr() {
        return ir;
    }

    public void setIr(int ir) {
        this.ir = ir;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getAx() {
        return ax;
    }

    public void setAx(int ax) {
        this.ax = ax;
    }

    public int getBx() {
        return bx;
    }

    public void setBx(int bx) {
        this.bx = bx;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
    
    public int generateInstruction(){
        Random randNumber = new Random();
        pc = randNumber.nextInt(91);
        ir = pc + 1;
        return pc;
    }
    
    
}
