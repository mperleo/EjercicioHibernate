package DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import DTO.Departamento;
import utils.HibernateUtil;
import utils.StringUtil;

public class DepartamentoDAO {
	private static Logger logger = LogManager.getLogger(DepartamentoDAO.class);
	
	public static void insertarDepartamentos(Session s, ArrayList<Departamento> departamentos) {
	    for (Departamento i: departamentos) {
	    	insertarDepartamento(s, i);
	    }
	}
	  
	public static void insertarDepartamento(Session s, Departamento departamento) {
		try {
			s.save(departamento);
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la inserccion de un departamento Error: "+ e.getMessage());
		}
		
	}
	
	public static boolean borrarDepartamento(Session s, Departamento departamento) {
		try {
			s.delete(departamento);
			return true;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo al borrar un departamento Error: "+ e.getMessage());
			return false;
		}
	}
	
	public static boolean modificarDepartamento(Session s, Departamento departamento) {
		// antes de llamar a esto se llama en el main a seleccionar usuario y luego se modifica
		try {
			s.update(departamento);
			return true;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la modificacion de un departamento Error: "+ e.getMessage());
			return false;
		}
	}
	
	public static Departamento seleccionarDepartamento(Session s, String nombre) {
		try {
			String consulta = "from departamento where nombre= :nombre";
			Departamento resultado = s.createQuery(consulta, Departamento.class)
				.setParameter("nombre", nombre)
	            .setMaxResults(1)
	            .uniqueResult();
			return resultado;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de un departamento Error: "+ e.getMessage());
			return null;
		}
	}
	
	public static List <Departamento> seleccionarDepartamentos(Session s) {
		try {
			String consulta = "from departamento";
			List <Departamento> resultado = s.createQuery(consulta, Departamento.class).getResultList();
			return resultado;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de un departamento Error: "+ e.getMessage());
			return null;
		}
	}
	
	public static int buscarNuevoID(Session s){
		try {
			String consulta = "SELECT MAX(id) from departamento";
			int id = s.createNativeQuery(consulta).getFirstResult();
			return id+1;
		}
		catch(GenericJDBCException e){
			logger.error("Fallo en la busqueda de un departamento Error: "+ e.getMessage());
			return -1;
		}
	}
}
