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
        CourierId courierId = new CourierId(id);
        return spec()
                .body(courierId)
                .when()
                .delete(COURIER_PATH + "/" + id)
                .then().log().all();
    }
    @Step("creat courier without loggin")
    public ValidatableResponse createCourierWithoutLoggin(Courier courier) {
        return spec()
                .body(courier)
                .post(COURIER_PATH)
                .then().log().all();
    }
    @Step("creat courier without password")
    public ValidatableResponse createCourierWithoutPassword(Courier courier) {
        return spec()
                .body(courier)
                .post(COURIER_PATH)
                .then().log().all();
    }
    @Step("login courier without data")
    public ValidatableResponse loginCourierWithoutData(Courier courier) {
        return spec()
                .body(courier)
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }
    @Step("login courier with wrong password")
    public ValidatableResponse loginCourierWithWrongPassword(WrongCourierCredentials wrongCreds) {
        return spec()
                .body(wrongCreds)
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }
}
