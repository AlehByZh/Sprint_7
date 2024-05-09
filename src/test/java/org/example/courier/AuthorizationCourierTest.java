package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class AuthorizationCourierTest {
    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    int courierId;

    @After
    public void deleteCourier() {
        if(courierId != 0) {
            ValidatableResponse deleteResponse = client.deleteCourier(courierId);
            check.deletedSuccessfully(deleteResponse);
        }
    }
    @Step("authorization positive path")
    @Test
    public void authorizationCourierPositive() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        CourierCredentials creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);

        assertNotEquals(0, courierId);
    }
    @Step("authorization without login")
    @Test
    public void authorizationWithoutLogin(){
        var courier = Courier.withoutLoggin();
        ValidatableResponse loginResponse = client.loginCourier(CourierCredentials.from(courier));
        check.loggedUnsuccessfully(loginResponse);
    }
    @Step("authorization without password")
    @Test
    public void authorizationWithoutPassword() {
        var courier = Courier.withoutPassword();
        ValidatableResponse loginResponse = client.loginCourier(CourierCredentials.from(courier));
        check.loggedUnsuccessfully(loginResponse);
    }
    @Step("authorization with random data")
    @Test
    public void authorizationWithRandomData() {
        var courier = Courier.random();
        CourierCredentials creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.loggedUnsuccessfullyWithRandom(loginResponse);
    }
    @Step("authorization with wrong password")
    @Test
    public void authorizationWithWrongPassword() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);
        WrongCourierCredentials wrongCreds = WrongCourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourierWithWrongPassword(wrongCreds);
        check.loggedUnsuccessfullyWithRandom(loginResponse);

        CourierCredentials creds = CourierCredentials.from(courier);
        ValidatableResponse loginSuccessfullyResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginSuccessfullyResponse);
    }
}
