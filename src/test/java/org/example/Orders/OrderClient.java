package org.example.Orders;

import io.restassured.response.ValidatableResponse;
import org.example.Client;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    public static final String ORDER_PATH = "/orders";

    public ValidatableResponse creatOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    public ValidatableResponse cancelOrder(int track) {
        OrderTrack orderTrack = new OrderTrack(track);
        return spec()
                .body(orderTrack)
                .when()
                .put(ORDER_PATH + "/cancel")
                .then().log().all();
    }
}
