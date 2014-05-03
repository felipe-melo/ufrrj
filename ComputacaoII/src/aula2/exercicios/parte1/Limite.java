package aula2.exercicios.parte1;
public class Limite{

     public static void main(String []args){
         double n = 1, lim = 0;
         
         while (n > 0){
             lim = Math.pow (1 + (1/n), n);
             n++;
             System.out.println (lim);
         }
     }
}