package net.danross.toptask.model;

import org.restlet.resource.Get;
import org.restlet.resource.Put;

import net.danross.toptask.model.User;

public interface UserControllerInterface {
    @Put("json")
    void create(User user);
    
    @Get("json")
    Container getAllUsers();
}
