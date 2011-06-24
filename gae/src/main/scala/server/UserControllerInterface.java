package server;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import model.User;

public interface UserControllerInterface {
    @Put
    void create(User user);
    
    @Get
    Container getAllUsers();
}
