package rest.api.restApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.api.restApi.Role;
import rest.api.restApi.mapper.Mapper;
import rest.api.restApi.model.MyUser;
import rest.api.restApi.model.Post;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manage")
public class UserManagementController {
    @Autowired
    private Mapper mapper;
    @Role(role = "admin")
    public MyUser createUser(MyUser user)  {
        MyUser user1 = mapper.getUserByUsername(user.username);
        if (user1 == null) {
            int id = mapper.insertUser(user);
            return user;
        } else {
            throw new RuntimeException(); //todo exception
        }
    }


    @Role(role = "admin")
    public ResponseEntity<Map<String, Boolean>> deleteUser(int id) {
        mapper.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
