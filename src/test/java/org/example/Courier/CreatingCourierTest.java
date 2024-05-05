package org.example.Courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class CreatingCourierTest {
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
    @DisplayName("courier positive path")
    @Test
    public void creatingNewCourier() {
        var courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        CourierCredentials creds = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedSuccessfully(loginResponse);

        assertNotEquals(0, courierId);
    }
}