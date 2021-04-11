/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import DAO.SaleDAO;
import domain.ErrorMessage;
import domain.Sale;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author cdobson
 */
public class SaleResource extends Jooby {

    public SaleResource(SaleDAO dao) {

        path("/api/sales", () -> {

            post((req, rsp) -> {

                Sale sale = req.body(Sale.class);

                System.out.println(sale);

                if (!dao.doesSaleExist(sale.getId())) {
                    dao.saveSale(sale);
                    rsp.status(Status.CREATED).send(sale);
                } else {
                    rsp.status(Status.UNPROCESSABLE_ENTITY).send(new ErrorMessage("That sale already exists"));
                }

            });

            delete("/:id", (req, rsp) -> {
                String saleId = req.param("id").value();
                if (dao.doesSaleExist(saleId)) {
                    dao.deleteSale(saleId);
                    rsp.status(Status.NO_CONTENT);
                } else {
                    System.out.println("********* IN ERROR");
                    rsp.status(Status.NOT_FOUND).send(new ErrorMessage("No sale matching that ID was found."));
                }
            });

        }).produces(MediaType.json).consumes(MediaType.json);;
    }

}
