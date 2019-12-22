package vss.springrest.client.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vss.springrest.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserRestService {
    static final String URL_TEMPLATE = "http://localhost:8080/rest-serv/users";
    static final String URL_SEP = "/";

    private static final Logger logger = LoggerFactory.getLogger(UserRestService.class);

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getUsers(Integer count, Sort.Direction sortOrder) {
        String path = buildPath(count, sortOrder);
        ResponseEntity<User[]> resp = restTemplate.exchange(path, HttpMethod.GET, null, User[].class);
        return resp.getStatusCode() == HttpStatus.OK ? Arrays.asList(resp.getBody()) : Collections.emptyList();
    }
    public List<User> findUserById(Integer id) {
        String path = URL_TEMPLATE + URL_SEP + id;
        ResponseEntity<User[]> resp = restTemplate.exchange(path, HttpMethod.GET, null, User[].class);
        return resp.getStatusCode() == HttpStatus.OK ? Arrays.asList(resp.getBody()) : Collections.emptyList();
    }

    private String buildPath(Integer count, Sort.Direction sortOrder) {
        return URL_TEMPLATE + "?" + "count=" + count + "&sortOrder=" + sortOrder;
    }
}
