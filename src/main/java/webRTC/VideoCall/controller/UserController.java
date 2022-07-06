package webRTC.VideoCall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webRTC.VideoCall.entity.UserEntity;
import webRTC.VideoCall.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody UserEntity userEntity) {

        try {
            userService.save(userEntity);
            logger.info("User registered successfully");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @GetMapping("/search/{id}")
    public UserEntity search(@PathVariable int id, UserEntity userEntity) {

        try {

            userEntity = userService.findByIdUser(id);
            return userEntity;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    @GetMapping("/allUsers")
    public List<UserEntity> allUsers() throws Exception {

        try {
            logger.info("All users");
            return userService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
