package principal;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import DAO.DepartamentoDAO;
import DTO.Departamento;
import utils.HibernateUtil;

public class DepartamentoCON {
	private static Logger logger = LogManager.getLogger(DepartamentoCON.class);
	
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		int opcion = 0 ;
		boolean seguir = true;
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		while( seguir == true ) {
			
			System.out.println("Indica una opcion:");
			System.out.println("1- Insertar");
			System.out.println("2- Borrar ");
			System.out.println("3- Seleccionar y modificar ");
			System.out.println("4- Ver todos ");
			System.out.println("5- Salir ");
			opcion = sc.nextInt();
			
			switch (opcion) {
				case 1:
					Departamento nuevoDep = pedirElemento(s);
					if(nuevoDep != null) DepartamentoDAO.insertarDepartamento(s, nuevoDep);
					break;
				case 2:
					String nombre = pedirNombre();
					if(nombre != null) {
						Departamento depBorrar = DepartamentoDAO.seleccionarDepartamento(s,nombre);
						DepartamentoDAO.borrarDepartamento(s, depBorrar);
					}
					break;
				case 3:
					String nombre1 = pedirNombre();
					if(nombre1 != null) {
						Departamento depOrig = DepartamentoDAO.seleccionarDepartamento(s,nombre1);
						Departamento depModif = pedirElementoModif(depOrig);
						DepartamentoDAO.modificarDepartamento(s, depModif);
					}	
					break;
				case 5:
					seguir = false;
					break;
				case 4: 
					List<Departamento> empleados = DepartamentoDAO.seleccionarDepartamentos(s); 
					System.out.println("Empleados:");
					System.out.println("NOMBRE RESPONSABLE");
					for (Departamento i : empleados) {
						System.out.println(i.getNombre()+" "+i.getNombre()+" "+i.getCod_responsable());
					}
					break;
				default:
					System.out.println("Indica una opcion valida");
					break;
			}

		}
	}
	
	public static Departamento pedirElemento(Session s){
		Scanner sc = new Scanner(System.in);
		String nombre = ""; 
		int cod_responsable = 0;
		
		try {
			System.out.println("Indica un nombre");
			nombre = sc.next();
			System.out.println("Indica un codigo de responsable");
			cod_responsable = sc.nextInt();
		
			Departamento u = new Departamento(DepartamentoDAO.buscarNuevoID(s), nombre, cod_responsable);
			return u;
		}	
		catch (InputMismatchException e){
			logger.warn("El usuario ha indicado un tipo incorrecto, en el metodo que pide datos para un nuevo departamento");
		}	
		
		return null;
	}
	
	public static Departamento pedirElementoModif(Departamento dep){
		Scanner sc = new Scanner(System.in);
		String nombre = ""; 
		int cod_responsable = 0;
		
		try {
			System.out.println("Valor anterior: "+dep.getNombre()+" | Indica un nombre");
			nombre = sc.next();
			System.out.println("Valor anterior: "+dep.getCod_responsable()+" | Indica un codigo de responsable");
			cod_responsable = sc.nextInt();
			
			Departamento u = new Departamento(dep.getCodigo(), nombre, cod_responsable);
			return u;
		}	
		catch (InputMismatchException e){
			logger.warn("El usuario ha indicado un tipo incorrecto, en el metodo que pide datos para modificar un departamento");
		}	
		
		return null;
	}
	
	public static String pedirNombre(){
		Scanner sc = new Scanner(System.in);
		String nombre = ""; //TODO: scanner

		try {
			System.out.println("Indica el nombre del departamento");
			nombre = sc.next();
			return nombre;
		}	
		catch (InputMismatchException e){
			logger.warn("El usuario ha indicado un tipo incorrecto, en el metodo que un nombre de departamento");
		}	
		return null;
	}
}
