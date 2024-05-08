package org.example.courier;

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
    @DisplayName("courier existing path")
    @Test
    public void creatingExistingCourier() {
        var courier = Courier.generic();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdExistingCourier(createResponse);
    }
    @DisplayName("courier creating without lastName")
    @Test
    public void creatingCourierWithoutLastName() {
        var courier = Courier.withoutLoggin();
        ValidatableResponse creatResponse = client.createCourier(courier);
        check.didntCreateCourier(creatResponse);
    }
    @DisplayName("courier creating without password")
    @Test
    public void creatingCourierWithoutPassword() {
        var courier = Courier.withoutPassword();
        ValidatableResponse creatResponse = client.createCourier(courier);
        check.didntCreateCourier(creatResponse);
    }
}