/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Collection;

/**
 *
 * @author cdobson
 */
public class Sale {
    
    private String id;
    private String saleDate;
    private Customer customer;
    private Collection<SaleItem> items;
    private Total totals;

    public Sale() {
    }

    public Sale(String id, String saleDate, Customer customer, Collection<SaleItem> items, Total totals) {
        this.id = id;
        this.saleDate = saleDate;
        this.customer = customer;
        this.items = items;
        this.totals = totals;
    }

    public String getId() {
        return id;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Collection<SaleItem> getItems() {
        return items;
    }

    public Total getTotals() {
        return totals;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItems(Collection<SaleItem> items) {
        this.items = items;
    }

    public void setTotals(Total totals) {
        this.totals = totals;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
