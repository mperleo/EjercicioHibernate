package principal;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import DAO.EmpleadoDAO;
import DTO.Empleado;
import utils.HibernateUtil;

public class EmpleadoCON {
	private static Logger logger = LogManager.getLogger(EmpleadoCON.class);
	
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
				Empleado nuevoEmp = pedirElemento(s);
				if(nuevoEmp != null) EmpleadoDAO.insertarEmpleado(s, nuevoEmp);
				break;
			case 2:
				int cod = pedirCodigo();
				if(cod != -1) {
					Empleado empBorrar = EmpleadoDAO.seleccionarEmpleado(s,cod);
					EmpleadoDAO.borrarEmpleado(s, empBorrar);
				}
				break;
			case 3:
				int cod1 = pedirCodigo();
				if(cod1 != -1) {
					Empleado empOrig = EmpleadoDAO.seleccionarEmpleado(s, pedirCodigo());
					Empleado empModif = pedirElementoModif(empOrig);
					if(empModif != null) EmpleadoDAO.modificarEmpleado(s, empModif);
				}	
				break;
			case 4: 
				List<Empleado> empleados = EmpleadoDAO.seleccionarEmpleados(s); 
				System.out.println("Empleados:");
				System.out.println("NOMBRE APELLIDO APELLIDO LUGARNAC FECHANAC DIRECCION TELEFONO PUESTODEP");
				for (Empleado i : empleados) {
					System.out.println(i.getNombre()+" "+i.getApellido1()+" "+i.getApellido2()+" "+i.getLugarNacimiento()+" "+i.getFechaNacimiento()+" "+i.getDireccion()+""+i.getTelefono()+" "+i.getCodDepartamento());
				}
				break;
			case 5:
				seguir = false;
				break;
			default:
				System.out.println("Indica una opcion valida");
				break;
			}

		}
	}
	
	public static Empleado pedirElemento(Session s){
		Scanner sc = new Scanner(System.in);
		String nombre = ""; 
		String apellido1 = "";
		String apellido2 = "";
		String lugar_nacimiento = "";
		String fecha_nacimiento = "";
		String direccion = "";
		String telefono = "";
		String puesto = "";
		int cod_departamento = 0;
		
		try {
			System.out.println("Indica un nombre");
			nombre = sc.next();
			
			System.out.println("Indica un apellido");
			apellido1 = sc.next();
			System.out.println("Indica un apellido");
			apellido2 = sc.next();
			System.out.println("Indica un lugar de nacimiento");
			lugar_nacimiento = sc.next();
			System.out.println("Indica una fecha de nacimiento");
			fecha_nacimiento = sc.next();
			System.out.println("Indica un direccion");
			direccion = sc.next();
			System.out.println("Indica un telefono");
			telefono = sc.next();
			System.out.println("Indica un puesto");
			puesto = sc.next();
			
			System.out.println("Indica un codigo de departamento");
			cod_departamento = sc.nextInt();
			Empleado u = new Empleado(EmpleadoDAO.buscarNuevoID(s), nombre, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto, cod_departamento);
			
			return u;
		}	
		catch (InputMismatchException e){
			logger.warn("El usuario ha indicado un tipo incorrecto, en el metodo que pide datos para un nuevo empleado");
		}	
		
		return null;
		
	}
	
	public static Empleado pedirElementoModif(Empleado dep){
		Scanner sc = new Scanner(System.in);
		String nombre = ""; 
		String apellido1 = "";
		String apellido2 = "";
		String lugar_nacimiento = "";
		String fecha_nacimiento = "";
		String direccion = "";
		String telefono = "";
		String puesto = "";
		int cod_departamento = 0;
		
		try {
			System.out.println("Valor anterior: "+dep.getNombre()+" | Indica un nombre");
			nombre = sc.next();
			
			System.out.println("Valor anterior: "+dep.getApellido1()+" | Indica un apellido");
			apellido1 = sc.next();
			System.out.println("Valor anterior: "+dep.getApellido2()+" | Indica un apellido");
			apellido2 = sc.next();
			System.out.println("Valor anterior: "+dep.getLugarNacimiento()+" | Indica un lugar de nacimiento");
			lugar_nacimiento = sc.next();
			System.out.println("Valor anterior: "+dep.getFechaNacimiento()+" | Indica una fecha de nacimiento");
			fecha_nacimiento = sc.next();
			System.out.println("Valor anterior: "+dep.getDireccion()+" | Indica un direccion");
			direccion = sc.next();
			System.out.println("Valor anterior: "+dep.getTelefono()+" | Indica un telefono");
			telefono = sc.next();
			System.out.println("Valor anterior: "+dep.getPuesto()+" | Indica un puesto");
			puesto = sc.next();
	
			
			System.out.println("Valor anterior: "+dep.getCodDepartamento()+" | Indica un codigo de departamento");
			cod_departamento = sc.nextInt();
			
			Empleado u = new Empleado(dep.getCodigo(), nombre, apellido1, apellido2, lugar_nacimiento, fecha_nacimiento, direccion, telefono, puesto, cod_departamento);
			return u;
		}
		catch (InputMismatchException e){
			logger.warn("El usuario ha indicado un tipo incorrecto, en el metodo que pide datos para modificar un empleado");
		}
		
		return null;	
	}
	
	public static int pedirCodigo(){
		Scanner sc = new Scanner(System.in);
		int codigo = -1; //TODO: scanner
		
		try {
			System.out.println("Indica el nombre del departamento");
			codigo = sc.nextInt();
			return codigo;
		}
		catch (InputMismatchException e){
			logger.warn("El usuario ha indicado un tipo incorrecto, en el metodo que se pide un codigo de empleado");
		}
		
		return -1;
	}
}