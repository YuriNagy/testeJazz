/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yuri
 */
public class Canteiro extends PlanoCarteziano {

    public Canteiro(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.irrigado = 0;
    }
    
    public int getIrrigado() {
        return irrigado;
    }

    public void setIrrigado(int irrigado) {
        this.irrigado = irrigado;
    }

    private int irrigado;
    
    
}
