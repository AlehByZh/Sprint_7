package org.example.Courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
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
}
