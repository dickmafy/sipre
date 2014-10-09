package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SIPRE_USUARIO database table.
 * 
 */
@Entity(name="SipreUsuario")
@Table(name="SIPRE_USUARIO")

@NamedQueries({
	@NamedQuery(name="SipreUsuario.validarUsuario", query="SELECT s FROM SipreUsuario s where s.vusuarioNom=:nickname and s.vusuarioPass=:clave"),
    @NamedQuery(name = "SipreUsuario.findAll", query = "SELECT s FROM SipreUsuario s"),
    @NamedQuery(name = "SipreUsuario.findByCusuarioCodigo", query = "SELECT s FROM SipreUsuario s WHERE s.cusuarioCodigo = :cusuarioCodigo"),
    @NamedQuery(name = "SipreUsuario.findByVusuarioNom", query = "SELECT s FROM SipreUsuario s WHERE s.vusuarioNom = :vusuarioNom"),
    @NamedQuery(name = "SipreUsuario.findByVusuarioPass", query = "SELECT s FROM SipreUsuario s WHERE s.vusuarioPass = :vusuarioPass"),
    @NamedQuery(name = "SipreUsuario.findByDusuarioFecReg", query = "SELECT s FROM SipreUsuario s WHERE s.dusuarioFecReg = :dusuarioFecReg"),
    @NamedQuery(name = "SipreUsuario.findByCusuarioEst", query = "SELECT s FROM SipreUsuario s WHERE s.cusuarioEst = :cusuarioEst")})
public class SipreUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSUARIO_CODIGO")
	private String cusuarioCodigo;

	@Column(name="CUSUARIO_EST")
	private String cusuarioEst;

	@Temporal(TemporalType.DATE)
	@Column(name="DUSUARIO_FEC_REG")
	private Date dusuarioFecReg;

	@Column(name="VUSUARIO_NOM")
	private String vusuarioNom;

	@Column(name="VUSUARIO_PASS")
	private String vusuarioPass;

	public SipreUsuario() {
	}

	public String getCusuarioCodigo() {
		return this.cusuarioCodigo;
	}

	public void setCusuarioCodigo(String cusuarioCodigo) {
		this.cusuarioCodigo = cusuarioCodigo;
	}

	public String getCusuarioEst() {
		return this.cusuarioEst;
	}

	public void setCusuarioEst(String cusuarioEst) {
		this.cusuarioEst = cusuarioEst;
	}

	public Date getDusuarioFecReg() {
		return this.dusuarioFecReg;
	}

	public void setDusuarioFecReg(Date dusuarioFecReg) {
		this.dusuarioFecReg = dusuarioFecReg;
	}

	public String getVusuarioNom() {
		return this.vusuarioNom;
	}

	public void setVusuarioNom(String vusuarioNom) {
		this.vusuarioNom = vusuarioNom;
	}

	public String getVusuarioPass() {
		return this.vusuarioPass;
	}

	public void setVusuarioPass(String vusuarioPass) {
		this.vusuarioPass = vusuarioPass;
	}

}