package io.github.alexcosta97.unify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.alexcosta97.unify.Services.HTTPService;
import okhttp3.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class HTTPServiceTest {
    HTTPService service;
    String authToken;

    @Before
    public void setUp(){
        service = new HTTPService();
        authToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1YzQxNDlhZTE0NjcwMTAwMTcwY2U0ODgiLCJyb2xlIjoiQWRtaW4iLCJjb21wYW55IjoiNWM0MTQ2NWExNDY3MDEwMDE3MGNlNDdlIiwibG9jYXRpb25zIjpbIjVjNDE0NjVhMTQ2NzAxMDAxNzBjZTQ3ZiJdLCJpYXQiOjE1NDc3ODI1NzR9.y2-HRiqi1N4qzys_nDtjvNtNVf7m1yhVOAPiTFmBq6g";
    }

    @Test
    public void postNoToken() throws Exception{
        //Arrange
        String endpoint = "companies/";
        String requestBody = "{\"name\": \"company\",\"email\": \"mail@company.com\",\"phone\": \"12345\",\"houseNumber\": \"1\",\"street\": \"Street\",\"town\": \"Town\",\"postCode\": \"PC1\",\"country\": \"Country\"}";

        //Act
        Response res = service.postNoToken(endpoint, requestBody);

        //Assert
        assertNotNull(res);
        assertTrue(res.isSuccessful());
    }

    @Test
    public void getToken() throws Exception{
        //Arrange
        String endpoint = "users/";

        //Act
        Response res = service.getToken(endpoint, authToken);

        //Assert
        assertNotNull(res);
        assertTrue(res.isSuccessful());
    }

    @Test
    public void postToken() throws Exception{
        //Arrange
        String endpoint = "locations/";
        String requestBody = "{\"name\": \"location\",\"email\": \"mail@company.com\",\"phone\": \"12345\", \"companyId\": \"5c41465a14670100170ce47e\", \"houseNumber\": \"1\",\"street\": \"Street\",\"town\": \"Town\",\"postCode\": \"PC1\",\"country\": \"Country\"}";

        //Act
        Response res = service.postToken(endpoint, requestBody, authToken);

        //Assert
        assertNotNull(res);
        assertTrue(res.isSuccessful());
    }

    @Test
    public void putToken() throws Exception{
        //Arrange
        String endpoint = "locations/5c41465a14670100170ce47f";
        String requestBody = "{\"name\": \"NewLocation\",\"email\": \"mail@company.com\",\"phone\": \"12345\", \"companyId\": \"5c41465a14670100170ce47e\", \"houseNumber\": \"1\",\"street\": \"Street\",\"town\": \"Town\",\"postCode\": \"PC1\",\"country\": \"Country\"}";

        //Act
        Response res = service.putToken(endpoint, requestBody, authToken);

        //Assert
        assertNotNull(res);
        assertTrue(res.isSuccessful());
    }

    @Test
    public void deleteToken() throws Exception{
        //Arrange
        String endpoint = "locations/5c414d1214670100170ce48c";

        //Act
        Response res = service.deleteToken(endpoint, authToken);

        //Assert
        assertNotNull(res);
        assertTrue(res.isSuccessful());
    }
}
