import courier.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Order;
import order.OrderClient;
import order.OrderGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CreateNewOrderTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private final String[] color;
    private final boolean isCreated;
    private OrderClient orderClient;
    private Order order;
    private int orderTrack;
    public CreateNewOrderTest(String[] color, boolean isCreated) {
        this.color = color;
        this.isCreated = isCreated;
    }
    @Parameterized.Parameters()
    public static Object[][] getColorData() {
        return new Object[][]{
                {new String[]{"BLACK", "GREY"}, true},
                {new String[]{"BLACK"}, true},
                {new String[]{"GREY"}, true},
                {new String[]{""}, true},
        };
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        orderClient= new OrderClient();
    }
    @DisplayName("Создание заказа")
    @Description("Проверка создания нового заказа с разными цветами и без")
    @Test
    public void createNewOrderTest() {
       Order order = OrderGenerator.randomOrder();
        order.setColor(this.color);
        Response response = orderClient.createOrder(order);
        orderTrack = response.path("track");
        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());
        assertTrue("Ошибка при создании заказа", orderTrack != 0);
    }
}
