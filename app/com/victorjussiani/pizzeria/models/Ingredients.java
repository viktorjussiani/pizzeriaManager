package com.victorjussiani.pizzeria.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.db.jpa.JPA;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"id"}) } )
public class Ingredients extends Model{
	
	private static final long serialVersionUID = -5637454138438861901L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Constraints.Required
	@Column(nullable = false)
	private String name;

	@Constraints.Required
	@Column(nullable = false)
	private BigDecimal value;
	
	public void save() {
		if (this.id == null) {
			JPA.em().persist(this);
		} else {
			JPA.em().merge(this);
		}
	}
	
	public static Ingredients findById(Long id) {
		return JPA.em().find(Ingredients.class, id);
	}

	public static Ingredients findByMaxId() {
		try {
			return (Ingredients) JPA.em().createQuery("SELECT p.id FROM Ingredients p desc").setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Ingredients> findAll() {
		return JPA.em().createQuery("FROM Ingredients ").getResultList();
	}

	@Override
	public String toString() {
		return "Ingredients [id=" + id + ", name=" + name + ", value=" + value
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredients other = (Ingredients) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	protected Object clone() {
		try {
			Object obj = super.clone();
			if (obj != null)
				return (Ingredients) obj;
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
