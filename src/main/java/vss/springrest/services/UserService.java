package vss.springrest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vss.springrest.model.User;
import vss.springrest.repositories.UserRepository;

import java.util.*;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final Integer DEFAULT_COUNT = 50;

    @Autowired
    private UserRepository userRepository;

    public List<User> addUser(User user) {
        logger.info("Adding new user: {}", user);
        return storeUser(user);
    }

    public List<User> updateUser(User user) {
        logger.info("Updating existent user: {}", user);
        return storeUser(user);
    }

    private List<User> storeUser(User user) {
        User storedUser = userRepository.save(user);
        return Collections.singletonList(storedUser);
    }

    public List<User> getAll(Integer count, Sort.Direction sortOrder) {
        logger.info("Count: {}, sortOrder: {}", count, sortOrder);
        Integer size = count == null ? DEFAULT_COUNT : count;
        Pageable paging = PageRequest.of(0, size, Sort.by(sortOrder, "lastName"));

        return userRepository.findAll(paging).getContent();
    }

    public List<User> findUserById(Integer id) {
        Optional<User> retrievedUser = userRepository.findById(id);
        return retrievedUser.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}
