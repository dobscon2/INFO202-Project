/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import DAO.CustomerDAO;
import domain.Customer;
import domain.ErrorMessage;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author cdobson
 */
public class CustomerResource extends Jooby {

	public CustomerResource(CustomerDAO dao) {

		path("/api/customer", () -> {

			get(() -> {
				return dao.getCustomers();
			});

			post((req, rsp) -> {
				Customer customer = req.body(Customer.class);

				// construct the URI for this customer
				String uri = "http://" + req.hostname() + ":" + req.port() + "" + req.path() + "/product/" + customer.getId();

				if (!dao.exists(customer.getId())) {
					// store the customer
					dao.createCustomer(customer);

					// return a response containing the complete customer
					rsp.status(Status.CREATED).send(customer);
				} else {
					// Non-unique ID
					rsp.status(Status.UNPROCESSABLE_ENTITY).send(new ErrorMessage("There is already a customer with that ID."));
				}

			});
                        
                        use("/:id", (req, rsp, chain) -> {
				String id = req.param("id").value();

				if (dao.exists(id)) {
					// ID is OK, so pass request on to the next route in the chain
					chain.next(req, rsp);
				} else {
					// Bad ID - send a 404 response
					rsp.status(Status.NOT_FOUND).send(new ErrorMessage("There is no customer matching that ID."));
				}
			});

			get("/:id", (req) -> {
				String id = req.param("id").value();
				return dao.getCustomerById(id);
			});

			delete("/:id", (req, rsp) -> {
				String id = req.param("id").value();
				dao.deleteCustomer(id);
				rsp.status(Status.NO_CONTENT);
			});

			put("/:id", (req, rsp) -> {
				String id = req.param("id").value();
				Customer customer = req.body().to(Customer.class);

				if (!id.equals(customer.getId())) {
					rsp.status(Status.CONFLICT).send(new ErrorMessage("Modifying the customer ID via this operation is not allowed."));
				} else {
					dao.updateCustomer(id, customer);
					rsp.status(Status.NO_CONTENT);
				}
			});

		}).produces(MediaType.json).consumes(MediaType.json);

	}

}
