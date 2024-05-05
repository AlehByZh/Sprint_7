package org.example;

import io.restassured.specification.RequestSpecification;
import org.example.Courier.Courier;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class Client {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static final String BASE_PATH = "/api/v1";

    public RequestSpecification spec() {
        return given().log().all()
                .contentType(JSON)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH);
    }
}
