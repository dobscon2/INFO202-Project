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

		}).produces(MediaType.json).consumes(MediaType.json);

	}

}
