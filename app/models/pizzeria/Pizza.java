package models.pizzeria;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.db.jpa.JPA;

@Entity
@Table
public class Pizza extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Constraints.Required
	@Column(nullable = false)
	private String nome;
	
	@Constraints.Required
	@Column(nullable = false)
	private Long value;
	
	
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

	public static Pizza findByMaxId() {
		try {
			return (Pizza) JPA.em().createQuery("SELECT p.id FROM Pizza p desc").setMaxResults(1).getSingleResult();
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
		return "Pizza [id=" + id + ", nome=" + nome + ", value=" + value + "]";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}