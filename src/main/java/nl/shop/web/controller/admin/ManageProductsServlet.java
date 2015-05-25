package nl.shop.web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.shop.domain.Product;
import nl.shop.domain.ProductNotFoundException;
import nl.shop.srv.ProductService;

/**
 * Servlet implementation class ManageProductsServlet
 */
@WebServlet(urlPatterns = "/admin/manageproducts")
public class ManageProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	ProductService productService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProductsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = productService.listProducts();
		request.setAttribute("products", products);
		request.getRequestDispatcher("/WEB-INF/views/admin/manageproducts.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "add":
				addProduct(request, response);
				break;
			case "delete":
				deleteProduct(request, response);
				break;
			default:
				response.sendError(400, "Ongeldige actie: " + action);
				return;
			}
			response.sendRedirect(request.getContextPath()
					+ "/admin/manageproducts");
		} catch (ProductNotFoundException pnf) {
			response.sendError(404, "Kan geen product vinden.");
		}
	}

	private void deleteProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			ProductNotFoundException {
		String productIdValue = request.getParameter("productId");
		Long productId = Long.parseLong(productIdValue);
		productService.removeProduct(productId);
	}

	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) {
		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");
		Product product = new Product(productName, "newproduct.jpg",
				Double.parseDouble(productPrice));
		productService.saveProduct(product);
	}

}
