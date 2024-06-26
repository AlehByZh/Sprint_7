package org.example.orders;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class OrderListTest {
    private final OrderClient orderClient = new OrderClient();
    @DisplayName("get all order list and check it not Null")
    @Test
    public void checkOrderListTest() {
               ValidatableResponse responseGetOrder = orderClient.getOrders()
                       .assertThat()
                .statusCode(HTTP_OK)
                .and()
                .body("orders", is(not(empty())));
    }
}