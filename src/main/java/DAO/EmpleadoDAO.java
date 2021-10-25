package DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import DTO.Empleado;
import utils.HibernateUtil;
import utils.StringUtil;

public class EmpleadoDAO {
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
			//poner el loger
		}
		
	}
	
	public static boolean borrarEmpleado(Session s, Empleado empleado) {
		try {
			s.delete(empleado);
			return true;
		}
		catch(GenericJDBCException e){
			//poner el loger
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
			//poner el loger
			return false;
		}
	}
	
	public static Empleado seleccionarEmpleado(Session s, int codigo) {
		String consulta = "from empleado where codigo= :codigo";
		Empleado resultado = s.createQuery(consulta, Empleado.class)
				.setParameter("codigo", codigo)
                .setMaxResults(1)
                .uniqueResult();
		return resultado;
	}
}
