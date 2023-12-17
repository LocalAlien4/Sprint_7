import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Orders;
import order.OrderClient;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetListOrdersTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private Orders orders;
    private OrderClient orderClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
    @DisplayName("Список заказов")
    @Description("Проверка на получение корректного списка заказов")
    @Test
    public void getListOrdersTest() {
        Response response = orderClient.getOrderList();
        orders = response.body().as(Orders.class);
        assertEquals("Неверный статус код", SC_OK, response.statusCode());
        assertNotNull("Список заказов не соответствует ожидаемому", orders);
    }
}
