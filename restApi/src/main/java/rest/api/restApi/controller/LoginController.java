package rest.api.restApi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.api.restApi.mapper.Mapper;
import rest.api.restApi.model.Comment;

@RequestMapping("/login")
@RestController
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);
    public void getPostComments() {
        logger.info("Login Controller=====");
    }
}
