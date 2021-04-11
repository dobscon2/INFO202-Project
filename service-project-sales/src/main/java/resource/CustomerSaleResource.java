/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import DAO.SaleDAO;
import domain.ErrorMessage;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author cdobson
 */
public class CustomerSaleResource extends Jooby {

	public CustomerSaleResource(SaleDAO dao) {

		path("/api/sale/customer", () -> {

			/**
			 * Get customer sales.
			 */
			get("/:id", (req, rsp) -> {

				String customerId = req.param("id").value();

				if (dao.doesCustomerExist(customerId)) {
					rsp.send(dao.getSales(customerId));
				} else {
					rsp.status(Status.NOT_FOUND).send(new ErrorMessage("Customer ID not found"));
				}

			});

			/**
			 * Get customer sales summary.
			 */
			get("/:id/summary", (req, rsp) -> {
				String customerId = req.param("id").value();

				if (dao.doesCustomerExist(customerId)) {
					rsp.send(dao.getSummary(customerId));
				} else {
					rsp.status(Status.NOT_FOUND).send(new ErrorMessage("Customer not found"));
				}

			});

		}).produces(MediaType.json).consumes(MediaType.json);

	}

}
