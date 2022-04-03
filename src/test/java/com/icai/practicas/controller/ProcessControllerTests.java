package com.icai.practicas.controller;

import static org.assertj.core.api.BDDAssertions.then;

import com.icai.practicas.controller.ProcessController.DataRequest;
import com.icai.practicas.controller.ProcessController.DataResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_app_when_login_using_right_credencials_then_ok() {

        //Given
        String address = "http://localhost:"+port+"/api/v1/process-step1";
        ProcessController.DataRequest dataPrueba = new ProcessController.DataRequest("Juan Juanes", "61701688W", "987654321");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataPrueba, headers);

        //when
        ResponseEntity<DataResponse> result = this.restTemplate.postForEntity(address, request, DataResponse.class);

        String expectedResult = "OK";
        DataResponse expectedResponse = new DataResponse(expectedResult);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        //then(result.getBody().result()).isEqualTo(expectedResult);
        then(result.getBody()).isEqualTo(expectedResponse);
    }
    @Test
    public void given_app_when_filling_using_right_credentials_then_ko() {

        //Given
        String address = "http://localhost:"+port+"/api/v1/process-step1";
        ProcessController.DataRequest dataPrueba = new ProcessController.DataRequest("Juan Juanes", "61aB1688W", "987654321");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataPrueba, headers);

        //when
        ResponseEntity<DataResponse> result = this.restTemplate.postForEntity(address, request, DataResponse.class);

        String expectedResult = "KO";
        DataResponse expectedResponse = new DataResponse(expectedResult);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        //then(result.getBody().result()).isEqualTo(expectedResult);
        then(result.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void given_app_when_filling_using_right_credentials_then_ok_legacy() {

        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        MultiValueMap<String, String> datos = new LinkedMultiValueMap<>();
        datos.add("fullName", "Pablo");
        datos.add("dni", "60593409R");
        datos.add("telefono", "622359863");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(datos, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = ResponseHTMLGenerator.message1;
        //DataResponse expectedResponse = new DataResponse(expectedResult);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        //then(result.getBody().result()).isEqualTo(expectedResult);
        //No termino de entender bien por qué devuelve null
        then(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void given_app_when_filling_using_right_credentials_then_ko_legacy() {

        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";

        MultiValueMap<String, String> datos = new LinkedMultiValueMap<>();
        datos.add("fullName", "Pablo");
        datos.add("dni", "605JK409R");
        datos.add("telefono", "622359863");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(datos, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = ResponseHTMLGenerator.message2;
        //DataResponse expectedResponse = new DataResponse(expectedResult);

        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        //then(result.getBody().result()).isEqualTo(expectedResult);
        //No termino de entender bien por qué devuelve null
        then(result.getBody()).isEqualTo(expectedResult);
    }
}