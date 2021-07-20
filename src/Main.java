
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yuri
 */
public class Main {
    int x_horta, y_horta;
    String  movimento, posicao;
    List<Canteiro> visitados = new ArrayList<Canteiro>();
    Robo  robo  = new Robo();
    Horta horta = new Horta();
    public static void main(String args[]){
        Main main = new Main();
        main.x_horta = main.y_horta = -1;
      
        
        do{
            try{
        
            main.x_horta = Integer.parseInt(JOptionPane.showInputDialog("Favor informar tamanho do eixo X da horta"));
            main.y_horta = Integer.parseInt(JOptionPane.showInputDialog("Favor informar tamanho do eixo Y da horta"));
            
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Error no tipo de dado informado \n Favor informar apenas inteiros e maior que 0");
            }
            
        }while(main.x_horta < 1 || main.y_horta < 1);
        System.out.println(main.x_horta);
        //informacoes da horta
        main.horta.setX(main.x_horta);
        main.horta.setY(main.y_horta);
        main.horta.printHorta();
        
        //informacoes do robo
        main.inserePosicao();
        main.insereRoboHorta();
        main.mostraPosicaoAtualRobo();
        main.movimentacao();
    }
    public void movimentacao(){
        String entrada[];
        int x, y;
        entrada = JOptionPane.showInputDialog("Informe os canteiros\n Segue o padrao (x,y) (x1,y1)").split(" ");
        
        System.out.println(entrada[0].split(","));
        
        for(int i = 0; i < entrada.length; i++){
            x = Integer.parseInt(entrada[i].split(",")[0].replace("(", ""));
            y = Integer.parseInt(entrada[i].split(",")[1].replace(")", ""));
            System.out.println("Destino: ( "+x+" , "+y+" )\n");
            andaAteDestino(x, y);
            System.out.println(" I ");
            marcaComoIrrigado(x, y);
        }
        
    }
    
    public void andaAteDestino(int x, int y){
        do{
               movimentaX(x, y);
               movimentaY(x, y);  
        }while(robo.getX() != x && robo.getY() != y);
    }
    
    public void movimentaX(int x, int y){
        boolean direcao = true;
        
        while(direcao){
            if(robo.getX() < x){
                if(robo.getPosicao().equals("S")){
                    viraRobo("E");
                }
                else if(robo.getPosicao().equals("N")){
                    viraRobo("D");
                }
                else if(robo.getPosicao().equals("O")){
                    viraRobo("D");
                }else{
                    andaRobo();
                    direcao = false;
                }
            }else if(robo.getX() > x){
                if(robo.getPosicao().equals("S")){
                    viraRobo("D");
                }
                else if(robo.getPosicao().equals("N")){
                    viraRobo("E");
                }
                else if(robo.getPosicao().equals("L")){
                    viraRobo("D");
                }else{
                    andaRobo();
                    direcao = false;
                }
            }
            else{
                direcao = false;
            }
        }
    }
    
     public void movimentaY(int x, int y){
        boolean direcao = true;
        
        while(direcao){
            if(robo.getY() < y){
                if(robo.getPosicao().equals("L")){
                    viraRobo("E");
                }
                else if(robo.getPosicao().equals("S")){
                    viraRobo("E");
                }
                else if(robo.getPosicao().equals("O")){
                    viraRobo("D");
                }else{
                    andaRobo();
                    direcao = false;
                }
            }else if(robo.getY() > y){
                if(robo.getPosicao().equals("N")){
                    viraRobo("E");
                }
                else if(robo.getPosicao().equals("O")){
                    viraRobo("E");
                }
                else if(robo.getPosicao().equals("L")){
                    viraRobo("D");
                }else{
                    andaRobo();
                    direcao = false;
                }
            }
            else{
                direcao = false;
            }
        }
    }
    public void marcaComoIrrigado(int x,int y){
        System.out.println("\nPosição: -- ( "+x+", "+y+" ) -- irrigada \n"); 
    }
    
    public void mostraPosicaoAtualRobo(){
        System.out.println("\n---------------------------\n");
        System.out.println("\nO robo está na posicao");
        System.out.println(" -- ( "+robo.getX()+" , "+robo.getY()+" ) -- olhando para o "+robo.getPosicao());       
        System.out.println("\n---------------------------\n");
    }
    
    public boolean marcaVisitado(int x, int y){
        boolean flag = false;
        for(Canteiro visitado : visitados){
            if(visitado.getX() == x && visitado.getY() == y){
                flag = true;
            }
        }
        if(!flag) visitados.add(new Canteiro(x, y));
        return flag;
    }
    
    public void insereRoboHorta(){
        do{
            JOptionPane.showMessageDialog(null, "Informe a posição Inicial do Robo");
            try{ 
                robo.setX(Integer.parseInt(JOptionPane.showInputDialog("X: ")));
                robo.setY(Integer.parseInt(JOptionPane.showInputDialog("Y: ")));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Erro valor não inteiro ");
            }
            
            if((robo.getX() >= horta.getX()) || (robo.getY() >=  horta.getY())){
                   JOptionPane.showMessageDialog(null, "Canteiro não existente ");
            }
            else{
                marcaVisitado(robo.getX(), robo.getY());
            }
        }while((robo.getX() >= horta.getX() || robo.getX() < 0) || (robo.getY() >= horta.getY() || robo.getY() < 0));
    
    }
    
    public void inserePosicao(){
        
        do{
            posicao = toUpperCase(JOptionPane.showInputDialog("Informe para que direção o robo ta olhando\nNorte(N), Sul(S),\nLeste(L), Oeste(O)\n"));

            if((posicao.equals("N") || posicao.equals("O") || posicao.equals("S") || posicao.equals("L"))){
                robo.setPosicao(posicao);

            }else{
                JOptionPane.showMessageDialog(null, "Direção inexistente");

            }
        }while(!(posicao.equals("N") || posicao.equals("O") || posicao.equals("S") || posicao.equals("L")));

    }
    
    public void viraRobo(String andar){
        switch(andar){
            case "E":
                System.out.print(" E ");
                 if(robo.getPosicao().equals("S")){
                      robo.setPosicao("L");
                 }
                 else if(robo.getPosicao().equals("L")){
                      robo.setPosicao("N");
                 }
                 else if(robo.getPosicao().equals("N")){
                      robo.setPosicao("O");
                 }
                 else if(robo.getPosicao().equals("O")){
                      robo.setPosicao("S");
                 }
            break;
            case "D": 
                 System.out.print(" D ");
                 if(robo.getPosicao().equals("S")){
                      robo.setPosicao("O");
                 }
                 else if(robo.getPosicao().equals("O")){
                      robo.setPosicao("N");
                 }
                 else if(robo.getPosicao().equals("N")){
                      robo.setPosicao("L");
                 }
                 else if(robo.getPosicao().equals("L")){
                      robo.setPosicao("S");
                 }
            break;
        }
    }
    
    public void andaRobo(){
        System.out.print(" M ");
        switch(robo.getPosicao()){
            case "N": 
                if(robo.getY()+1 < horta.getY()){
                    robo.setY(robo.getY()+1);
                    marcaVisitado(robo.getX(), robo.getY()+1);
                }else System.out.println("\nImpossivel realizar esse movimento");
            break;
            case "S": 
                if(robo.getY()-1 < horta.getY() && robo.getY()-1 >= 0){
                    robo.setY(robo.getY()-1);
                    marcaVisitado(robo.getX(), robo.getY()-1);
                }else System.out.println("\nImpossivel realizar esse movimento");
            break;            
            case "L": 
                if(robo.getX()+1 < horta.getX()){
                    robo.setX(robo.getX()+1);
                    marcaVisitado(robo.getX()+1, robo.getY());
                }else System.out.println("\nImpossivel realizar esse movimento");
            break;
            case "O": 
                if(robo.getX()-1 < horta.getX() && robo.getX()-1 >= 0){
                    robo.setX(robo.getX()-1);
                    marcaVisitado(robo.getX()-1, robo.getY());
                }else System.out.println("\nImpossivel realizar esse movimento");
            break;
        }
    }
    
}
