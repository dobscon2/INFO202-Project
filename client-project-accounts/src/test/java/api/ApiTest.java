package api;

import domain.Customer;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
		
		customerAPI.createCustomer(customer1);
		customerAPI.createCustomer(customer2);
	}

	@AfterEach
	public void tearDown() throws IOException {
		customerAPI.deleteCustomer(customer1.getId()).execute();
		customerAPI.deleteCustomer(customer2.getId()).execute();
	}

	@Test
	public void testSomething() throws IOException {
		fail("Test is not implemented yet.");
	}

	@Test
	public void testSomethingElse() throws IOException {
		fail("Test is not implemented yet.");
	}

}
