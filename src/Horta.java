
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yuri 
 */
public class Horta extends PlanoCarteziano {
    private int x;
    private int y;

    public void printHorta(){
        int canteiros = 0;
        System.out.println("Minha horta....\n");
        for(int x = 0; x < this.getX(); x ++){
            for(int y = 0; y < this.getY(); y++){
                System.out.println("Canteiro -> |"+x+" , "+y+"|");
                canteiros++;
            }   
        }
        System.out.println("\n Tem "+canteiros+" canteiros");
    }

}
