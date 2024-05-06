package org.example.Orders;

import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertTrue;

public class OrderChecks {
    public int orderCreated(ValidatableResponse creatResponse) {
        int track = creatResponse
                .statusCode(HTTP_CREATED)
                .extract()
                .path("track");
        return track;
    }
    public void orderCanceled(ValidatableResponse cancelRespose) {
        boolean canceled = cancelRespose
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("ok");
        assertTrue(canceled);
    }
}
