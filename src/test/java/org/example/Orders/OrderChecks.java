package org.example.Orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertTrue;

public class OrderChecks {
    @Step("check order is created")
    public int orderCreated(ValidatableResponse creatResponse) {
        int track = creatResponse
                .statusCode(HTTP_CREATED)
                .extract()
                .path("track");
        return track;
    }
    @Step("check order is canceled")
    public void orderCanceled(ValidatableResponse cancelResponse) {
        boolean canceled = cancelResponse
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("ok");
        assertTrue(canceled);
    }
}
