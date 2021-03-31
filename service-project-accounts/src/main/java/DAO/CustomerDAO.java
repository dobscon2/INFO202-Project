/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.Customer;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author dobco402
 */
public class CustomerDAO {
	
		private static final Map<String, Customer> customers = new TreeMap<>();
		
		static {
		if (customers.isEmpty()) {
		}
	}
}
