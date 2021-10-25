package DAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import DTO.Departamento;
import utils.HibernateUtil;
import utils.StringUtil;

public class DepartamentoDAO {
	public static void insertarDepartamento(Session s, ArrayList<Departamento> departamentos) {
	    for (Departamento i: departamentos) {
	    	insertarDepartamento(s, i);
	    }
	}
	  
	public static void insertarDepartamento(Session s, Departamento departamento) {
		try {
			s.save(departamento);
		}
		catch(GenericJDBCException e){
			//poner el loger
		}
		
	}
	
	public static boolean borrarDepartamento(Session s, Departamento departamento) {
		try {
			s.delete(departamento);
			return true;
		}
		catch(GenericJDBCException e){
			//poner el loger
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
			//poner el loger
			return false;
		}
	}
	
	public static Departamento seleccionarDepartamento(Session s, int codigo) {
		String consulta = "from departamento where codigo= :codigo";
		Departamento resultado = s.createQuery(consulta, Departamento.class)
				.setParameter("codigo", codigo)
                .setMaxResults(1)
                .uniqueResult();
		return resultado;
	}
}
