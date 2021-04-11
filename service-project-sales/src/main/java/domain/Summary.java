/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author cdobson
 */
public class Summary {
    
    private Integer numberOfSales;
    private Double totalPayment;
    private String group;

    public Summary() {
    }

    public Summary(Integer numberOfSales, Double totalPayment, String group) {
        this.numberOfSales = numberOfSales;
        this.totalPayment = totalPayment;
        this.group = group;
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public String getGroup() {
        return group;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
