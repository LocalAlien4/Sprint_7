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

public class LoginCourierTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private int id;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        courierClient= new CourierClient();
    }
    @Test
    @DisplayName("Логин курьера с валидными данными")
    @Description("Проверка логина курьера с валидными данными: логин, пароль")
    public void loginCourier(){
        Courier courier = randomCourier();
        Response response= courierClient.create(courier);
        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());
        Response loginResponse = courierClient.login(fromCourier(courier));
        id = loginResponse.path("id");
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
        assertTrue("Ошибка при логине- не получен логин", id!=0);
    }
    @After
    public void tearDown() {
        courierClient.delete(id);
    }
}
