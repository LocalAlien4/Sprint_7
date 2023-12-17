import courier.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Courier;
import models.CourierCreds;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;
import static utils.Utils.randomString;

@RunWith(Parameterized.class)
public class LoginCourierWIthEmptyInputTest {
    private final String login;
    private final String password;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private CourierClient courierClient;

    public LoginCourierWIthEmptyInputTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters()
    public static Object[][] getData() {
        return new Object[][]{
                {randomString(8), null},
                {null, randomString(16)},
                {null, null}
        };
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        courierClient= new CourierClient();
    }
    @Test
    @DisplayName("Логин курьера без обязательных полей")
    @Description("Проверка ошибки при логине курьера без обязательных полей: логин, пароль")
    public void loginCourierWithEmptyInputs(){
        Courier courier = new Courier();
        courier.setLoginAndPass(this.login,this.password);
        Response response= courierClient.login(CourierCreds.fromCourier(courier));
        assertEquals("Неверный статус код", SC_BAD_REQUEST, response.statusCode());
        assertEquals("Некорректный текст ошибки", "Недостаточно данных для входа", response.path("message"));
    }
}
