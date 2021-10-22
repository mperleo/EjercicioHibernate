package annotations;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


	

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "CODIGO", nullable = false)
	private int codigo;
	
	@Column(name = "NOMBRE", nullable = false)
    private String nombre;
	
	@Column(name = "COD_RESPONSABLE", nullable = false)
	private int cod_responsable;
	
	// @Transient
	// private List<Statement> statements;
	
	public Departamento() {};

	public Departamento(int codigo, String nombre, int cod_responsable) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.cod_responsable = cod_responsable;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the cod_responsable
	 */
	public int getCod_responsable() {
		return cod_responsable;
	}

	/**
	 * @param cod_responsable the cod_responsable to set
	 */
	public void setCod_responsable(int cod_responsable) {
		this.cod_responsable = cod_responsable;
	}
	
	
	  
	//@Transient
	//private List<Statement> statements;
	
	
	  
}
