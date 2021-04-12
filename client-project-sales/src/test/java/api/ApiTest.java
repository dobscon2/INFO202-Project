package api;

import domain.Customer;
import domain.Sale;
import domain.SaleItem;
import domain.Summary;
import domain.Totals;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
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

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8081/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SaleApi sales = retrofit.create(SaleApi.class);
    CustomerSaleApi customers = retrofit.create(CustomerSaleApi.class);

    private Customer customer1;
    private Customer customer2;
    private Sale sale1;
    private Sale sale2;
    private Sale sale3;
    private Sale sale4;

    @BeforeEach
    public void setUp() throws IOException {
        customer1 = new Customer();
        customer1.id("uo531");
        customer1.email("zucc@facebook.com");
        customer1.group("VIP Customer");

        customer2 = new Customer();
        customer2.id("testy");
        customer2.email("testy@tester.com");
        customer2.group("regular");

        sale1 = new Sale();
        sale1.id("1");
        sale1.saleDate("11/04/2021");
        sale1.customer(customer1);
        sale1.addItemsItem(new SaleItem().price(100.00).productId("12345").quantity(2.0));
        sale1.totals(new Totals().totalPayment(200.00).totalPrice(200.00).totalTax(0.0));

        sale2 = new Sale();
        sale2.id("2");
        sale2.saleDate("11/04/2021");
        sale2.customer(customer1);
        sale2.addItemsItem(new SaleItem().price(5000.00).productId("54321").quantity(2.0));
        sale2.totals(new Totals().totalPayment(10000.00).totalPrice(10000.00).totalTax(0.0));

        sale3 = new Sale();
        sale3.id("3");
        sale3.saleDate("10/04/2021");
        sale3.customer(customer2);
        sale3.addItemsItem(new SaleItem().price(100.00).productId("12345").quantity(2.0));
        sale3.totals(new Totals().totalPayment(100.00).totalPrice(100.00).totalTax(0.0));

        sale4 = new Sale();
        sale4.id("4");
        sale4.saleDate("8/04/2021");
        sale4.customer(customer1);
        sale4.addItemsItem(new SaleItem().price(100.00).productId("12345").quantity(2.0));
        sale4.totals(new Totals().totalPayment(100.00).totalPrice(100.00).totalTax(0.0));

        sales.createNewSale(sale1).execute();
        sales.createNewSale(sale2).execute();
        sales.createNewSale(sale3).execute();

    }

    @AfterEach
    public void tearDown() throws IOException {
        sales.saleIdDelete(sale1.getId()).execute();
        sales.saleIdDelete(sale2.getId()).execute();
        sales.saleIdDelete(sale3.getId()).execute();
        sales.saleIdDelete(sale4.getId()).execute();
    }

    @Test
    public void testCreateSale() throws IOException {
        Response<Sale> createResponse = sales.createNewSale(sale4).execute();
        assertThat(createResponse.code(), is(201));
        List<Sale> salesResponse = customers.getAllCustomerSales(customer1.getId()).execute().body();
        assertThat(salesResponse, hasItem(sale4));
        createResponse = sales.createNewSale(sale4).execute();
        assertThat(createResponse.code(), is(422));
    }

    @Test
    public void testDeleteSale() throws IOException {
        List<Sale> salesResponse = customers.getAllCustomerSales(customer1.getId()).execute().body();
        assertThat(salesResponse, hasItem(sale1));
        Response deleteResponse = sales.saleIdDelete(sale1.getId()).execute();
        assertThat(deleteResponse.code(), is(204));
        salesResponse = customers.getAllCustomerSales(customer1.getId()).execute().body();
        assertThat(salesResponse, not(hasItem(sale1)));
    }

    @Test
    public void testGetSales() throws IOException {
        List<Sale> salesResponse = customers.getAllCustomerSales(customer1.getId()).execute().body();
        assertThat(salesResponse, hasItems(sale1, sale2));
        assertThat(salesResponse, not(hasItem(sale3)));
    }

    @Test
    public void testGetSummary() throws IOException {
        Summary summary = customers.saleCustomerIdSummaryGet(customer1.getId()).execute().body();
        assertThat(summary.getNumberOfSales(), is(2));
        assertThat(summary.getTotalPayment(), is(new BigDecimal("10200.0")));
        assertThat(summary.getGroup(), is("VIP Customers"));
    }

}
