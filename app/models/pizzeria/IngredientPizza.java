package models.pizzeria;

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
public class IngredientPizza extends Model{

	private static final long serialVersionUID = -4288812439337359863L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Constraints.Required
	@Column(nullable = false)
	private Pizza pizza;

	@Constraints.Required
	@Column(nullable = false)
	private Ingredients ingredient;

	@Constraints.Required
	@Column(nullable = false)
	private BigDecimal quantity;
	
	public void save() {
		if (this.id == null) {
			JPA.em().persist(this);
		} else {
			JPA.em().merge(this);
		}
	}
	
	public static IngredientPizza findById(Long id) {
		return JPA.em().find(IngredientPizza.class, id);
	}

	@SuppressWarnings("unchecked")
	public static List<IngredientPizza> findAll() {
		return JPA.em().createQuery("FROM IngredientPizza ").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<IngredientPizza> findIngredientsByPizza(Long pizzaId){
		return JPA.em().createQuery("FROM IngredientPizza ip WHERE ip.pizza.id = pizzaID").setParameter("pizzaID", pizzaId).getResultList();
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
		IngredientPizza other = (IngredientPizza) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IngredientPizza [id=" + id + ", pizza=" + pizza
				+ ", ingredient=" + ingredient + ", quantity=" + quantity + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Ingredients getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredients ingredient) {
		this.ingredient = ingredient;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
