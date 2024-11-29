package examen2425;

import java.time.LocalDate;
import java.util.Scanner;
public class MainNaveEspacial {
	/***********************************************Mostrar Menu***********************************************/
	public static int mostrarmenu (Scanner teclado) {
		int eleccion=0;

		System.out.println("***************************");
		System.out.println("1-Añadir naves espaciales.");
		System.out.println("2-Mostrar naves cuya antiguedad sea superior a una dada.");
		System.out.println("3-Ordenar naves por año de lanzamiento.");
		System.out.println("4-Modificar capacidad total de naves que admiten pasajeros.");
		System.out.println("5-Mostrar todas las naves espaciales registradas.");
		System.out.println("6-Eliminar una nave espacial.");
		System.out.println("7-Salir.");
		System.out.print("Que desea hacer: ");
		eleccion=teclado.nextInt();
		System.out.println("***************************");
		return eleccion;
	}
	/********************************************Buscar Por Nombre********************************************/
	public static int buscarPorNombre(Scanner teclado, NaveEspacial naves[], int contador, String nombre) {
		boolean encontrado=false;

		for (int i=0; i<contador&&!encontrado;i++) {
			if(nombre.equalsIgnoreCase(naves[i].getNombre())) {
				encontrado=true;
				return i;
			}
		}
		return -1;
	}
	/***********************************************Añadir Naves***********************************************/
	public static int introducirNaves (Scanner teclado, NaveEspacial naves[], int contador) {		
		String nombre;
		int creacion, lanzamiento, capacidad, tripulantes;
		int pos;
		String respuesta="si";

		if(contador>=naves.length) {
			System.out.println("[ERROR] Espacio excedido.");
		}
		else {
			for(int i=contador; i<naves.length && respuesta.equalsIgnoreCase("si"); i++) {
				System.out.print("Introduzca el nombre de la nave: ");
				nombre=teclado.next();
				pos=buscarPorNombre(teclado,naves,contador,nombre);
				if (pos==-1) 
				{
					System.out.print("Introduzca el año de creacion: ");
					creacion=teclado.nextInt();
					System.out.print("Introduzca el año de lanzamiento: ");
					lanzamiento=teclado.nextInt();
					while (creacion>lanzamiento) {
						System.out.println("***************************");
						System.out.println("[ERROR] El año de lanzamiento no puede ser anterior al de creacion.");
						System.out.print("Introduzca el año de lanzamiento: ");
						lanzamiento=teclado.nextInt();
					}	
					System.out.print("Introduzca la capacidad de personas: ");
					capacidad=teclado.nextInt();
					System.out.print("Introduzca el numero de tripulantes necesario: ");
					tripulantes=teclado.nextInt();
					while (capacidad<tripulantes) {
						System.out.println("***************************");
						System.out.println("[ERROR] La cantidad de tripulantes necesarion no puede ser superor a la capacidad.");
						System.out.print("Introduzca el numero de tripulantes necesario: ");
						tripulantes=teclado.nextInt();
					}	
					System.out.println("[Guardando informacion...]");
					naves[i]= new NaveEspacial(nombre, creacion, lanzamiento, capacidad, tripulantes);
					contador++;
					System.out.println(naves[i].toString());
					System.out.println("[Nave guardada exitosamente]");
					do {
						System.out.println("***************************");
						System.out.println("Desea seguir introduciendo naves\n[SI][NO]");
						respuesta=teclado.next();
						if (!respuesta.equalsIgnoreCase("SI") && !respuesta.equalsIgnoreCase("NO")) {
							System.out.println("[ERROR]");
							System.out.println("***************************");
						}
					} while (!respuesta.equalsIgnoreCase("SI") && !respuesta.equalsIgnoreCase("NO"));
				}else {
					System.out.println("[ERROR] Esa nave ya existe.");
					System.out.println("***************************");
				}
			}
		}
		return contador;
	}
	/*****************************************Mostrar Por Antiguedad*******************************************/
	public static void mostrarPorAntiguedad (Scanner teclado, NaveEspacial naves[], int contador) {		
		int anioActual, antiguedad;
		anioActual=LocalDate.now().getYear();
		
		System.out.print("Introduce la antiguedad minima: ");
		antiguedad=teclado.nextInt();
		for(int i=0; i<contador; i++) {
			if (antiguedad<=naves[i].calcularAntiguedad(anioActual)) {
				System.out.println(naves[i].toString());
			}
		}	
	}
	/*********************************************Ordenar Por Año*********************************************/
	public static void ordenarPorAño (NaveEspacial naves[], int contador) {
		NaveEspacial aux; 

		for(int i=0; i<naves.length-1; i++){
			for(int j=0; j<(naves.length-1-i) && naves[j+1]!=null; j++){ 
				if(naves[j].getLanzamiento()<naves[j+1].getLanzamiento()){ 
					aux=naves[j];
					naves[j]=naves[j+1];
					naves[j+1]=aux;
				}   
			}
		}
		for(int i=0; i<contador; i++) {
			System.out.println(naves[i].toString());
		}	
	}
	/*******************************************Modificar Capacidad*******************************************/
	public static void modificarCapacidad (Scanner teclado, NaveEspacial naves[], int contador) {
		String respuesta="";
		int capacidad;

		for(int i=0; i<contador; i++) {
			if (naves[i].admitePasajeros()) {
				System.out.println("Desea modificar la capacidad total de la nave\n"+naves[i].toString()+"\n[SI][NO]");
				respuesta=teclado.next();
				if (respuesta.equalsIgnoreCase("SI")) {
					System.out.print("Introduzca la nueva capacidad de la nave: ");
					capacidad=teclado.nextInt();
					naves[i].setCapacidad(capacidad);
					System.out.println("La nueva capacidad de la nave "+naves[i].getNombre()+" es "+naves[i].getCapacidad()+".");
				}
			}
			else {
				System.out.println("No hay naves que admitan pasajeros actualmente.");
			}
		}
	}
	/***********************************************Mostrar Naves***********************************************/
	public static void mostrarNaves (NaveEspacial naves[], int contador) {		
		for(int i=0; i<contador; i++) {
			System.out.println(naves[i].toString());
		}	
	}
	/***********************************************Eliminar Naves***********************************************/
	public static int eliminarNaveEspacial (Scanner teclado, NaveEspacial naves[], int contador) {
		String nombre;
		int pos;

		System.out.print("Introduce el nombre de la nave que desea eliminar: ");
		nombre=teclado.next();
		pos=buscarPorNombre(teclado,naves,contador,nombre);
		if(pos!=-1) {
			System.out.println("[Eliminando...]");
			naves[pos]=null;
			contador--;
			for(int i=pos;i<contador;i++) {
				naves[i]=naves[i+1];
			}
			naves[contador]=null;
			System.out.println("La nave ha sido eliminada correctamente.");
		}
		else {
			System.out.println("[ERROR] Esa nave no existe.");
		}
		return contador; 
	}
	/***************************************************Main***************************************************/
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		NaveEspacial [] naves = new NaveEspacial [50]; 
		int eleccion=0, contador=0;

		/*
		naves[0]=new NaveEspacial("Apolo", 2000, 2005, 4, 2);
		naves[1]=new NaveEspacial("Lancer", 2004, 2020, 7, 3);
		naves[2]=new NaveEspacial("Mercury", 2001, 2008, 5, 5);
		 */

		do {
			eleccion=mostrarmenu (teclado);
			switch (eleccion) {
			case 1: //Añadir naves espaciales
				contador=introducirNaves (teclado, naves, contador);
				break;
			case 2: //Mostrar naves cuya antiguedad sea superior a una dada
				if(contador<=0) {
					System.out.println("[ERROR] No hay naves registradas.");
				}
				else {
					mostrarPorAntiguedad (teclado, naves, contador);
				}
				break;
			case 3: //Ordenar naves por año de lanzamiento
				if(contador<=0) {
					System.out.println("[ERROR] No hay naves registradas.");
				}
				else {
					ordenarPorAño(naves, contador);
				}
				break;
			case 4: //Modificar capacidad total de naves que admiten pasajeros
				if(contador<=0) {
					System.out.println("[ERROR] No hay naves registradas.");
				}
				else {
					modificarCapacidad (teclado, naves, contador);
				}
				break;
			case 5: //Mostrar todas las naves espaciales registradas
				if(contador<=0) {
					System.out.println("[ERROR] No hay naves registradas.");
				}
				else {
					mostrarNaves (naves, contador);
				}
				break;
			case 6: //Eliminar una nave espacial
				if(contador<=0) {
					System.out.println("[ERROR] No hay naves registradas.");
				}
				else {
					contador=eliminarNaveEspacial (teclado, naves, contador);
				}
				break;
			case 7: //Salir.
				System.out.println("[Saliendo...]");
				break;
			default:
				System.out.println("Eleccion no valida.");
				break;
			}
		}while(eleccion!=7);
		teclado.close();
		System.out.println("***************************");
	}
}
