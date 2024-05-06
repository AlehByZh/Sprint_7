package org.example.Orders;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class OrdersTest {
    private final OrderClient orderClient = new OrderClient();
    private final OrderChecks orderChecks = new OrderChecks();
    int orderTrack;
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public OrdersTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2024-06-06", "Saske come to Konoha",List.of("BLACK")},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2024-06-06", "Saske come to Konoha", List.of("GREY")},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2024-06-06", "Saske come to Konoha", List.of("BLACK", "GREY")},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2024-06-06", "Saske come to Konoha", List.of()}
        });
    }
    @After
    public void cancelOrder() {
        ValidatableResponse cancelResponse = orderClient.cancelOrder(orderTrack);
        orderChecks.orderCanceled(cancelResponse);
    }
    @DisplayName("creat order parametrized color")
    @Test
    public void ordersParametrizedTest() {
        Order order = new Order();
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setMetroStation(metroStation);
        order.setPhone(phone);
        order.setRentTime(rentTime);
        order.setDeliveryDate(deliveryDate);
        order.setComment(comment);
        order.setColor(color);

        ValidatableResponse creatResponse = orderClient.creatOrder(order);
        orderTrack = orderChecks.orderCreated(creatResponse);
        assertNotEquals(0,orderTrack);
    }
}
