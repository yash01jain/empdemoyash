package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Emp" database table.
 * 
 */
@Entity
@Table(name="\"Emp\"")
@NamedQuery(name="Emp.findAll", query="SELECT e FROM Emp e")
public class Emp implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private String name;

	public Emp() {
	}


	@Id
	@Column(name="\"SID\"", unique=true, nullable=false)
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}


	@Column(name="\"Name\"")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}