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
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateCourierWithActualCredsTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private int id;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        courierClient= new CourierClient();
    }
    @Test
    @DisplayName("Создание курьеров с одинаковыми данными")
    @Description("Проверка ошибок при создании нового курьера с уже существующими данными: логин, пароль и имя")
    public void createCourierWithActualCreds(){
        Courier courier = randomCourier();
        Response response= courierClient.create(courier);
        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());

        Response responseAgain = courierClient.create(courier);
        assertEquals("Неверный статус код", SC_CONFLICT, responseAgain.statusCode());
        assertEquals("Некорректный текст ошибки", "Этот логин уже используется", responseAgain.path("message"));
    }
    @After
    public void tearDown() {
        courierClient.delete(id);
    }
}
