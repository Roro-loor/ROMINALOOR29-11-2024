package Ejerciciosclase1;

import java.util.Scanner;

public class ejercicio30 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//variables
		Scanner teclado=new Scanner(System.in);
		
		double euros; 
		double porcentaje; 
		int dias;
		
		System.out.println("Inserta  cantidad en euros:");
		euros=teclado.nextInt();
		System.out.println("Introduce el tipo de interes (solo numero)");
		interes = teclado.nextint();
		System.out.println ("Inserta un numero de dias: ");
		dias = teclado.nextInt();
		System.out.println ("El interes introducido es de " + (euros*interes*dias)/(360*100)+"%");
		
		teclado.clouse();
				
		
	}

}
