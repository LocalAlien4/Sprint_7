import courier.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierCreds;
import org.junit.Before;
import org.junit.Test;

import static courier.CourierGenerator.randomCourier;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class LoginCourierWithNotValidCredsTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        courierClient= new CourierClient();
    }
    @Test
    @DisplayName("Логин несуществующего курьера")
    @Description("Проверка логина курьера с несуществующими данными: логин, пароль")
    public void loginCourier(){
        CourierCreds courier = CourierCreds.fromCourier(randomCourier());
        Response response= courierClient.login(courier);
        assertEquals("Неверный статус код", SC_NOT_FOUND, response.statusCode());
        assertEquals("Некорректный текст ошибки","Учетная запись не найдена", response.path("message"));
    }
}
