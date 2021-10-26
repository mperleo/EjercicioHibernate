package DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import DTO.Empleado;

public class EmpleadoDAO {
	private static Logger logger = LogManager.getLogger(EmpleadoDAO.class);
	
	public static void insertarEmpleados(Session s, ArrayList<Empleado> empleados) {
	    for (Empleado i: empleados) {
	      insertarEmpleado(s, i);
	    }
	}
	  
	public static void insertarEmpleado(Session s, Empleado empleado) {
		try {
			s.save(empleado);
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la inserccion de un empleado Error: "+ e.getMessage());
		}
		
	}
	
	public static boolean borrarEmpleado(Session s, Empleado empleado) {
		try {
			s.delete(empleado);
			return true;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo al borrar un empleado Error: "+ e.getMessage());
			return false;
		}
	}
	
	public static boolean modificarEmpleado(Session s, Empleado empleado) {
		// antes de llamar a esto se llama en el main a seleccionar usuario y luego se modifica
		try {
			s.update(empleado);
			return true;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la modificacion de un empleado Error: "+ e.getMessage());
			return false;
		}
	}
	
	public static Empleado seleccionarEmpleado(Session s, int codigo) {
		try {
			String consulta = "from empleado where codigo= :codigo";
			Empleado resultado = s.createQuery(consulta, Empleado.class)
					.setParameter("codigo", codigo)
	                .setMaxResults(1)
	                .uniqueResult();
			return resultado;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de un empleado Error: "+ e.getMessage());
			return null;
		}
	}
	
	public static List <Empleado> seleccionarEmpleados(Session s) {
		try {
			String consulta = "from empleado";
			List <Empleado> resultado = s.createQuery(consulta, Empleado.class).getResultList();
			return resultado;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de un departamento Error: "+ e.getMessage());
			return null;
		}
	}
	
	public static int buscarNuevoID(Session s){
		try {
			String consulta = "SELECT MAX(id) from empleado";
			int id = s.createNativeQuery(consulta).getFirstResult();
			return id+1;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de un departamento Error: "+ e.getMessage());
			return -1;
		}
	}
}
