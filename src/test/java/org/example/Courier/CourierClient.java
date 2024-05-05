package org.example.Courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

import java.util.Map;

public class CourierClient extends Client {
    public static final String COURIER_PATH = "/courier";

    @Step("login courier")
    public ValidatableResponse loginCourier(CourierCredentials creds) {
        return spec()
                .body(creds)
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }
@Step("creat courier")
    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }
@Step("delete courier")
    public ValidatableResponse deleteCourier(int id) {
        return spec()
                .body(Map.of("id",id))
                .when()
                .delete(COURIER_PATH + "/" + id)
                .then().log().all();
    }
}
