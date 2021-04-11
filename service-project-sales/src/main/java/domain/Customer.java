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
public class Customer {

    private String id;
    private String email;
    private String group;

    public Customer() {
    }

    public Customer(String id, String email, String group) {
        this.id = id;
        this.email = email;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
