package exercicio.I;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	private static BufferedReader br;
	private static Scanner scan;
	private static int num;
	private static String linha[];
	private static long start;
	private static long finish;
	
	public static void main(String[] args){
		System.out.println("Digite a opção desejada:\n\n1 - Buscar pessoal por código.\n"
				+ "2 - Buscar todas as pessoas.\n\nEntre com a opção: ");
		
		scan = new Scanner(System.in);
		 num = scan.nextInt();
		
		switch (num){
		
		case 1:
			
			System.out.println("digite o código da pessoa a ser buscada: ");
			scan = new Scanner(System.in);
			num = scan.nextInt();
			start = System.currentTimeMillis();
			try {
				br = Constants.setFileToRead(Constants.pessoas);
				
				while (br.ready()){
					linha = br.readLine().split("\t");
					if (linha[0].equals(String.valueOf(num))){
						Pessoa pessoa = new Pessoa(linha[0], linha[1], linha[2], linha[3]);
						System.out.print("\n\n" + pessoa.getNome() + " " + pessoa.getSobreNome() + ", ");
						pessoa.getEndereco().imprimeEndereco();
						break;
					}
				}
				finish = (System.currentTimeMillis() - start);
				System.out.println("O programa levou " + finish + " milisegundos para rodar");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 2:
			try {
				br = Constants.setFileToRead(Constants.pessoas);
				start = System.currentTimeMillis();
				while (br.ready()){
					linha = br.readLine().split("\t");
					Pessoa pessoa = new Pessoa(linha[0], linha[1], linha[2], linha[3]);
					//System.out.print(pessoa.getNome() + " " + pessoa.getSobreNome() + ", ");
					pessoa.getEndereco();//.imprimeEndereco();					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finish = (System.currentTimeMillis() - start)/1000;
			System.out.println("O programa levou " + finish + " segundos para rodar");
			break;
			
		default:
			break;
		
		}
	}
}
