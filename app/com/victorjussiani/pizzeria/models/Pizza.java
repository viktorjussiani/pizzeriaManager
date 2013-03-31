package com.victorjussiani.pizzeria.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.data.validation.Constraints;
import play.db.jpa.JPA;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"id"}) })
public class Pizza implements Cloneable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Constraints.Required
	@Column(nullable = false)
	public String name;
	
	@Constraints.Required
	@Column(nullable = false)
	public Long value;
	
	@Constraints.Required
	@Column(nullable = true)
	@OneToMany
	public List<Ingredient> ingredients;
	
	
	public void save() {
		if (this.id == null) {
			JPA.em().persist(this);
		} else {
			JPA.em().merge(this);
		}
	}
	
	public static Pizza findById(Long id) {
		return JPA.em().find(Pizza.class, id);
	}

	@SuppressWarnings("unchecked")
	public static List<Pizza> findByName(String name) {
		try {
			//TODO: Arrumar para LIKE
			return  JPA.em().createQuery("SELECT * FROM Pizza WHERE name = pizzaName").setParameter("pizzaName", name).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Pizza> findAll() {
		return JPA.em().createQuery("FROM Pizza ").getResultList();
	}
	
	public Pizza clone() {
		try {
			Object obj = super.clone();
			if (obj != null)
				return (Pizza) obj;
		} catch (CloneNotSupportedException e) {
		}
		return null;
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
		Pizza other = (Pizza) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", value=" + value + "]";
	}
}
