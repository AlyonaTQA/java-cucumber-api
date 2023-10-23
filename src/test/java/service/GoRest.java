package service;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import model.CreateUser;
import model.ExistingUser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GoRest {

    private String url;
    ObjectMapper mapper = new ObjectMapper();

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ExistingUser> getAllUsers() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("8aa36da167576ba7a4b27af66e351359e279ec8b5c316ee8a5b516eda737bec6");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(this.url, HttpMethod.GET, httpEntity, String.class);

        return mapper.readValue(response.getBody(), new TypeReference<List<ExistingUser>>() {
        });
    }

    public ExistingUser postUser(CreateUser user) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("8aa36da167576ba7a4b27af66e351359e279ec8b5c316ee8a5b516eda737bec6");

        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(user), headers);
        String json = restTemplate.postForObject(this.url, entity, String.class);

        return mapper.readValue(json, new TypeReference<ExistingUser>() {});
    }

    public ExistingUser putUser(ExistingUser user) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("8aa36da167576ba7a4b27af66e351359e279ec8b5c316ee8a5b516eda737bec6");

        HttpEntity<Object> requestEntity = new HttpEntity<>(mapper.writeValueAsString(user), headers);
        ResponseEntity<String> response = restTemplate.exchange(this.url + "/" + user.getId(), HttpMethod.PUT, requestEntity, String.class);

        return mapper.readValue(response.getBody(), new TypeReference<ExistingUser>() {});
    }

    public void deleteUser(ExistingUser user) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("8aa36da167576ba7a4b27af66e351359e279ec8b5c316ee8a5b516eda737bec6");

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(this.url + "/" + user.getId(), HttpMethod.DELETE, requestEntity, String.class);
    }
}
