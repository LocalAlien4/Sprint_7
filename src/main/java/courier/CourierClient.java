package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Courier;
import models.CourierCreds;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String COURIER_URL = "api/v1/courier";
    private static final String COURIER_LOGIN_URL = "api/v1/courier/login";

    @Step("Создание курьера {courier}")
    public Response create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER_URL);
    }
    @Step("Авторизация курьером с кредами {courierCreds}")
    public Response login(CourierCreds courierCreds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierCreds)
                .when()
                .post(COURIER_LOGIN_URL);
    }
    @Step("Удаление курьера")
    public Response delete(int id) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_URL+ id);
    }
}
