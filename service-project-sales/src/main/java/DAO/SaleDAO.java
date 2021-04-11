/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import domain.Sale;
import domain.Summary;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author cdobson
 */
public class SaleDAO {

    private static final Multimap<String, Sale> customerSale = ArrayListMultimap.create();
    private static final Map<String, Sale> sales = new HashMap<>();

    private static final Double THRESHHOLD = 500.0;

    public void saveSale(Sale sale) {
        customerSale.put(sale.getCustomer().getId(), sale);
        sales.put(sale.getId(), sale);
    }

    public void deleteSale(String saleId) {
        Sale sale = sales.get(saleId);
        customerSale.remove(sale.getCustomer().getId(), sale);
        sales.remove(sale.getId());
    }

    public Collection<Sale> getSales(String customerId) {
        return customerSale.get(customerId);
    }

    public Boolean doesSaleExist(String saleId) {
        return sales.containsKey(saleId);
    }

    public Boolean doesCustomerExist(String customerId) {
        return customerSale.containsKey(customerId);
    }

    public Summary getSummary(String customerId) {
        Collection<Sale> customerSales = getSales(customerId);

        Summary summary = new Summary();
        summary.setNumberOfSales(customerSales.size());
        Double totalPayment = customerSales.stream().mapToDouble(sale -> sale.getTotals().getTotalPayment()).sum();
        summary.setTotalPayment(totalPayment);
        summary.setGroup(totalPayment <= THRESHHOLD ? "Regular Customers" : "VIP Customers");

        return summary;
    }

}
