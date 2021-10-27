package principal;

import java.util.Scanner;

public class main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0 ;
		boolean seguir = true;
		
		while( seguir == true ) {
			
			System.out.println("Indica una opcion:");
			System.out.println("1- Manejo departamentos");
			System.out.println("2- Manejo empleados ");
			System.out.println("3- Salir ");
			opcion = sc.nextInt();
			
			switch (opcion) {
				case 1:
					DepartamentoCON.menu();
					break;
				case 2:
					EmpleadoCON.menu();
					break;
				case 3:
					seguir = false;
					break;
				default:
					System.out.println("Indica una opcion valida");
					break;
			}
			
		}
		System.out.println("FIN PROGRAMA");
	}
}
