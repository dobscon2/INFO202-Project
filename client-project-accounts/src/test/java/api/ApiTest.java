package api;

import domain.Customer;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiTest {
	private final Retrofit retrofit = new Retrofit.Builder()
		.baseUrl("http://localhost:8080/api/")
		.addConverterFactory(GsonConverterFactory.create())
		.build();
	
private DefaultApi customerAPI;

private Customer customer1;
private Customer customer2;
private Customer customer3;


	@BeforeEach
	public void setUp() throws IOException {
		customerAPI = retrofit.create(DefaultApi.class);
		
		customer1 = new Customer();
		customer1.setId("abc123");
		customer1.setEmail("testy@tester.com");
		customer1.setFirstname("Testy");
		customer1.setLastname("Tester");
		customer1.setUsername("testy_tester");
		customer1.setGroup("Regular Customer");
		
		customer2 = new Customer();
		customer2.setId("xyz789");
		customer2.setEmail("johndoe@joker.com");
		customer2.setUsername("joker");
		customer2.setFirstname("John");
		customer2.setLastname("Doe");
		customer2.setGroup("Customer VIP");
		
		customer3 = new Customer();
		customer3.setId("uo531");
		customer3.setUsername("zucc");
		customer3.setEmail("zucc@facebook.com");
		customer3.setFirstname("Mark");
		customer3.setLastname("Zuckerberg");
		customer3.setGroup("Customer VIP");
		
		customerAPI.createCustomer(customer1);
		customerAPI.createCustomer(customer2);
	}

	@AfterEach
	public void tearDown() throws IOException {
		customerAPI.deleteCustomer(customer1.getId()).execute();
		customerAPI.deleteCustomer(customer2.getId()).execute();
	}

	@Test
	public void testCreateCustomer() throws IOException {
		Response<Customer> createResponse = customerAPI.createCustomer(customer3).execute();
		assertThat(createResponse.code(), is(201));
		Response<Customer> getResponse = customerAPI.customerIdGet(customer3.getId()).execute();
		Customer returnedCustomer = getResponse.body();
		assertThat(returnedCustomer, samePropertyValuesAs(customer3, "username"));
		assertThat(returnedCustomer, hasProperty("username", equalTo("zucc")));
		createResponse = customerAPI.createCustomer(customer3).execute();
		assertThat(createResponse.code(), is(422));
	}

	@Test
	public void testSomethingElse() throws IOException {
		fail("Test is not implemented yet.");
	}

}
