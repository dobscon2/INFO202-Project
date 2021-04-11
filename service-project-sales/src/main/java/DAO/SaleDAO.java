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

    private static final Multimap<String, Sale> salesByCustomer = ArrayListMultimap.create();
    private static final Map<String, Sale> salesBySaleId = new HashMap<>();

    private static final Double THRESHHOLD = 500.0;

    public void saveSale(Sale sale) {
        salesByCustomer.put(sale.getCustomer().getId(), sale);
        salesBySaleId.put(sale.getId(), sale);
    }

    public void deleteSale(String saleId) {
        Sale sale = salesBySaleId.get(saleId);
        salesByCustomer.remove(sale.getCustomer().getId(), sale);
        salesBySaleId.remove(sale.getId());
    }

    public Collection<Sale> getSales(String customerId) {
        return salesByCustomer.get(customerId);
    }

    public Boolean doesSaleExist(String saleId) {
        return salesBySaleId.containsKey(saleId);
    }

    public Boolean doesCustomerExist(String customerId) {
        return salesByCustomer.containsKey(customerId);
    }

    public Summary getSummary(String customerId) {
        Collection<Sale> custSales = getSales(customerId);

        Summary summary = new Summary();
        summary.setNumberOfSales(custSales.size());
        Double totalPayment = custSales.stream().mapToDouble(sale -> sale.getTotals().getTotalPayment()).sum();
        summary.setTotalPayment(totalPayment);
        summary.setGroup(totalPayment <= THRESHHOLD ? "Regular Customers" : "VIP Customers");

        return summary;
    }

}
