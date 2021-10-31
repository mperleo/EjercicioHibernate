package DAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
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
			String consulta = "from Empleado where codigo= :codigo";
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
			String consulta = "from Empleado";
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
			String consulta = "SELECT MAX(id) from Empleado";
			int id = s.createNativeQuery(consulta).getFirstResult();
			return id+1;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de un departamento Error: "+ e.getMessage());
			return -1;
		}
	}
	
	public static List <Empleado> seleccionarEmpleadosDepartamento(Session s, int codDep) {
		try {
			DetachedCriteria dcr = DetachedCriteria.forClass(Empleado.class);
			dcr.add(Property.forName("codDepartamento").eq(codDep));
			return dcr.getExecutableCriteria(s).list();
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de empleados por departamento Error: "+ e.getMessage());
			return null;
		}
	}
	
	public static List<Empleado> seleccionarEmpleadosEdad(Session s, int edad){
		LocalDateTime fechaHoy = LocalDateTime.now();
		DateTimeFormatter fechaFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fechaMenosEdad = LocalDateTime.from(LocalDateTime.now()).minusYears(edad).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String fechaBusq = fechaMenosEdad.substring(0, 8);
		
		try {
			String consulta = "from Empleado where fecha_nacimiento < :fechaBusq";
			List <Empleado> resultado = s.createQuery(consulta, Empleado.class).setParameter("fechaBusq", fechaBusq).getResultList();
			return resultado;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de un empleado Error: "+ e.getMessage());
			return null;
		}
	}
}
