package aula2.exercicios.parte1;
public class Media{

     public static void main(String []args){
         double med = 0, num1, num2;
         
         for (int i = 0; i < 50; i++){
             num1 = (Math.random() * 10);
             num2 = (Math.random() * 10);
             
             med += (num1 + num2)/100;
         }
         
         System.out.println ("A media deu " + med);
     }
}