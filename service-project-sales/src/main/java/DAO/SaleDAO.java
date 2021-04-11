/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.Sale;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author cdobson
 */
public class SaleDAO {

    private static final Map<String, Sale> sales = new TreeMap<String, Sale>();
    
    public List<Sale> getSales() {
        return new ArrayList<Sale>(sales.values());
    }
    
    public void createSale(Sale sale) {
        sales.put(sale.getId(), sale);
    }
    
    public Sale getSaleById(String id) {
        return sales.get(id);
    }
    
    public void deleteSale(String id) {
        sales.remove(id);
    }
    
    public void updateSale(String id, Sale updatedSale) {
        sales.put(id, updatedSale);
    }
    
    public boolean exists(String id) {
        System.out.println(sales.containsKey(id));
        return sales.containsKey(id);
    }
}
