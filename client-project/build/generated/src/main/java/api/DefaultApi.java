package api;

import util.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DefaultApi {
  /**
   * Create a new customer
   * Create a new customer account
   * @param customer C (optional)
   * @return Call&lt;Customer&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("customer")
  Call<Customer> createCustomer(
    @retrofit2.http.Body Customer customer
  );

  /**
   * Delete a customer
   * Delete a customer account
   * @param id The customer&#39;s unique ID (required)
   * @return Call&lt;Customer&gt;
   */
  @DELETE("customer/{id}")
  Call<Customer> deleteCustomer(
    @retrofit2.http.Path("id") String id
  );

  /**
   * Get a list of all customer
   * Getting a list of all customers
   * @return Call&lt;List&lt;Customer&gt;&gt;
   */
  @GET("customer")
  Call<List<Customer>> getAllCustomers();
    

  /**
   * Update customer details
   * Update the customer details
   * @param id The customer&#39;s unique ID (required)
   * @param customer Updated customer information (required)
   * @return Call&lt;Customer&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("customer/{id}")
  Call<Customer> updateCustomer(
    @retrofit2.http.Path("id") String id, @retrofit2.http.Body Customer customer
  );

}
