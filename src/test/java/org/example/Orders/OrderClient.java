package org.example.Orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

public class OrderClient extends Client {
    public static final String ORDER_PATH = "/orders";

    @Step("get order list")
    public ValidatableResponse getOrders() {
        return spec()
                .queryParam("limit", 5)
                .get(ORDER_PATH)
                .then().log().all();
    }

    @Step("creat order")
    public ValidatableResponse creatOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    @Step("cancel order")
    public ValidatableResponse cancelOrder(int track) {
        OrderTrack orderTrack = new OrderTrack(track);
        return spec()
                .body(orderTrack)
                .when()
                .put(ORDER_PATH + "/cancel")
                .then().log().all();
    }
}
