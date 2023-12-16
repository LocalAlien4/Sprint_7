import courier.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static courier.CourierGenerator.randomCourier;
import static models.CourierCreds.fromCourier;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateNewCourierTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private int id;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        courierClient= new CourierClient();
    }
    @Test
    @DisplayName("Создание нового курьера")
    @Description("Проверка создания нового курьера с оригинальными и полными данными: логин, пароль и имя")
    public void createCourier(){
        Courier courier = randomCourier();

       Response response= courierClient.create(courier);
        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());
        assertTrue("Ошибка при создании курьера", response.path("ok"));
        Response loginResponse = courierClient.login(fromCourier(courier));
        id = loginResponse.path("id");
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
    }
    @After
    public void tearDown() {
        courierClient.delete(id);
    }
}
