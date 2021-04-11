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
public class Total {
    
    private Double totalPrice;
    private Double totalTax;
    private Double totalPayment;

    public Total() {
    }

    public Total(Double totalPrice, Double totalTax, Double totalPayment) {
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.totalPayment = totalPayment;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
