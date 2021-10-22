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
@Table(name = "EMPLEADO")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "CODIGO", nullable = false)
	private int codigo;
	
	@Column(name = "NOMBRE", nullable = false)
    private String nombre;
	
	@Column(name = "APELLIDO1", nullable = false)
    private String apellido1;
	
	@Column(name = "APELLIDO2", nullable = false)
    private String apellido2;
	
	@Column(name = "LUGAR_NACIMIENTO", nullable = false)
    private String lugar_nacimiento;
	
	@Column(name = "FECHA_NACIMIENTO", nullable = false)
	private String fecha_nacimiento;
	
	@Column(name = "DIRECCION", nullable = false)
	private String direccion;
	
	@Column(name = "TELEFONO", nullable = false)
	private String telefono;
	
	@Column(name = "PUESTO", nullable = false)
	private String puesto;
	
	@Column(name = "COD_DEPARTAMENTO", nullable = false)
	private int cod_departamento;
	/*
	  @Transient
	  private List<Statement> statements;
	*/
	
	public Empleado() {}
	public Empleado(int codigo, String nombre, String apellido1, String apellido2, String lugar_nacimiento,
			String fecha_nacimiento, String direccion, String telefono, String puesto, int cod_departamento) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.lugar_nacimiento = lugar_nacimiento;
		this.fecha_nacimiento = fecha_nacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.puesto = puesto;
		this.cod_departamento = cod_departamento;
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
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}
	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}
	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	/**
	 * @return the lugar_nacimiento
	 */
	public String getLugar_nacimiento() {
		return lugar_nacimiento;
	}
	/**
	 * @param lugar_nacimiento the lugar_nacimiento to set
	 */
	public void setLugar_nacimiento(String lugar_nacimiento) {
		this.lugar_nacimiento = lugar_nacimiento;
	}
	/**
	 * @return the fecha_nacimiento
	 */
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	/**
	 * @param fecha_nacimiento the fecha_nacimiento to set
	 */
	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	/**
	 * @return the cod_departamento
	 */
	public int getCod_departamento() {
		return cod_departamento;
	}
	/**
	 * @param cod_departamento the cod_departamento to set
	 */
	public void setCod_departamento(int cod_departamento) {
		this.cod_departamento = cod_departamento;
	}
	
}
