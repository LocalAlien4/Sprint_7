import courier.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static courier.CourierGenerator.randomCourier;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static utils.Utils.randomString;

@RunWith(Parameterized.class)
public class CreateCourierWithoutRequiredFields {
    private final String login;
    private final String password;
    private boolean isSuccess;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private int id;
    private CourierClient courierClient;

    public CreateCourierWithoutRequiredFields(String login, String password, boolean isSuccess) {
        this.login = login;
        this.password = password;
        this.isSuccess = isSuccess;
    }

    @Parameterized.Parameters()
    public static Object[][] getData() {
        return new Object[][]{
                {randomString(8), null, false},
                {null, randomString(16), false},
                {null, null, false}
        };
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        courierClient= new CourierClient();
    }
    @Test
    @DisplayName("Создание курьера без обязательных полей")
    @Description("Проверка ошибки при создании нового курьера без обязательных полей: логин, пароль и имя")
    public void createCourierWithActualCreds(){
        Courier courier = new Courier();
        courier.setLoginAndPass(this.login,this.password);
        Response response= courierClient.create(courier);
        assertEquals("Неверный статус код", SC_BAD_REQUEST, response.statusCode());
        assertEquals("Некорректный текст ошибки", "Недостаточно данных для создания учетной записи", response.path("message"));
 }
    @After
    public void tearDown() {
        courierClient.delete(id);
    }
}
