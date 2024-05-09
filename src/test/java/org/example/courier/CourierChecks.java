package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierChecks {
    @Step("check is log in successfully")
    public int loggedSuccessfully(ValidatableResponse loginResponse) {
        int id = loginResponse
                        .assertThat()
                        .statusCode(HTTP_OK)
                        .extract()
                        .path("id");
        return id;
    }
@Step("check courier created successfully")
    public void createdSuccessfully(ValidatableResponse createResponse) {
        boolean created = createResponse
                        .assertThat()
                        .statusCode(HTTP_CREATED)
                        .extract()
                        .path("ok");
        assertTrue(created);
    }
@Step("check courier delete successfully")
    public void deletedSuccessfully(ValidatableResponse deleteRespose) {
        boolean deleted = deleteRespose
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("ok");
        assertTrue(deleted);
    }
    @Step("check existing courier don't created")
    public void createdExistingCourier(ValidatableResponse createResponse) {
        String dontCreated = createResponse
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .extract()
                .path("message");
        assertEquals(dontCreated, "Этот логин уже используется");
    }
    @Step("check courier without data didn't create")
    public void didntCreateCourier(ValidatableResponse createResponse) {
        String dontCreated = createResponse
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .extract()
                .path("message");
        assertEquals(dontCreated, "Недостаточно данных для создания учетной записи");
    }
    @Step("check is log in without login unsuccessfully")
    public void loggedUnsuccessfully(ValidatableResponse loginResponse) {
        String dontLogged = loginResponse
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .extract()
                .path("message");
        assertEquals(dontLogged,"Недостаточно данных для входа");
    }
    @Step("check is not log in with random data")
    public void loggedUnsuccessfullyWithRandom(ValidatableResponse loginResponse) {
        String dontLogged = loginResponse
                .assertThat()
                .statusCode(HTTP_NOT_FOUND)
                .extract()
                .path("message");
        assertEquals(dontLogged,"Учетная запись не найдена");
    }
}
