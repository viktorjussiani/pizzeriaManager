package com.victorjussiani.pizzeria.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.data.validation.Constraints;
import play.db.jpa.JPA;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"id"}) } )
public class Ingredient implements Cloneable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Constraints.Required
	@Column(nullable = false)
	private String name;

	public void save() {
		if (this.id == null) {
			JPA.em().persist(this);
		} else {
			JPA.em().merge(this);
		}
	}
	
	public static Ingredient findById(Long id) {
		return JPA.em().find(Ingredient.class, id);
	}

	@SuppressWarnings("unchecked")
	public static List<Ingredient> findByName(String name) {
		try {
			//TODO: Arrumar para LIKE
			return  JPA.em().createQuery("SELECT * FROM Ingredient WHERE name = ingredientName").setParameter("ingredientName", name).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Ingredient> findAll() {
		return JPA.em().createQuery("FROM Ingredients ").getResultList();
	}

	@Override
	public String toString() {
		return "Ingredients [id=" + id + ", name=" + name + "]";
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
		Ingredient other = (Ingredient) obj;
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
				return (Ingredient) obj;
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}
}
