package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITestRent {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8085/rentService/rent";
    }

    @Test
    public void mustSaveRent_Successfully() {
        RestAssured.given()
                .body(" { \"condominium\": 777, \"dateAdjustmentIGPM\": \"2023-01-31\", \"description\": \"stringgfgdf\", \"endDateRent\": \"2024-01-16\", \"energy\": 150, \"id_customer\": 2, \"id_property\": 1, \"iptu\": 150, \"startDateRent\": \"2023-01-16\", \"value\": 150, \"water\": 150 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/save")
                .then()
                .statusCode(201);
    }

    @Test
    public void mustUpdateRent_Successfully() {
        RestAssured.given()
                .body(" { \"id\": 2, \"description\": \"atualizou\", \"energy\": 177, \"iptu\": 888, \"value\": 1555, \"water\": 80 }")
                .contentType(ContentType.JSON)
                .when()
                .put("/update")
                .then()
                .statusCode(204);
    }

    @Test
    public void mustDeleteRent_Successfully() {
        RestAssured.given()
                .when()
                .delete("/delete/2")
                .then()
                .statusCode(204);
    }

    @Test
    public void mustFindByIdRent_Successfully() {
        RestAssured.given()
                .when()
                .get("/find/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void mustReturnsListRents_Successfully() {
        RestAssured.given()
                .when()
                .get("/all")
                .then()
                .statusCode(200);
    }

    @Test
    public void mustReturnsListOfRents_WithIdOfCustomer_Successfully() {
        RestAssured.given()
                .when()
                .get("rents/customer/1")
                .then()
                .log().all()
                .statusCode(200);
    }

}
