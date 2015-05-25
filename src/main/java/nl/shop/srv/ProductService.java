package nl.shop.srv;

import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import nl.shop.domain.Product;
import nl.shop.domain.ProductNotFoundException;

@Stateless
public class ProductService {

	@ShopDb
	@Inject
	EntityManager em;

	/**
	 * Slaat het gegeven product op.
	 * 
	 * @param product
	 */
	public void saveProduct(Product product) {
		Objects.requireNonNull(product, "Object mag niet null zijn!");
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
	}

	/**
	 * @return Geeft een lijst van alle producten terug.
	 */
	public List<Product> listProducts() {
		TypedQuery<Product> query = em.createQuery("select p from Product p",
				Product.class);
		return query.getResultList();
	}

	/**
	 * Geeft een product op basis van id terug.
	 * 
	 * @param id
	 * @return het gevonden product
	 * @throws ProductNotFoundException
	 *             Wanneer het product niet gevonden wordt.
	 */
	public Product findProductById(Long id) throws ProductNotFoundException {
		Product product = em.find(Product.class, id);
		if (product == null) {
			throw new ProductNotFoundException(id);
		}
		return product;
	}

	/**
	 * Verwijderd een product op basis van productId
	 * 
	 * @param productId
	 * @throws ProductNotFoundException
	 *             wanneer er geen product met dat id gevonden kan worden.
	 */
	public void removeProduct(Long productId) throws ProductNotFoundException {
		Product product = em.find(Product.class, productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		}
		em.getTransaction().begin();
		em.remove(product);
		em.getTransaction().commit();
	}
}
