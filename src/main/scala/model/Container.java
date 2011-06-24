package net.danross.toptask.model;

import java.util.ArrayList;
import java.util.List;

import net.danross.toptask.model.User;

public class Container
{
    public List<User> user_list;

    public List<User> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<User> user_list) {
        this.user_list = user_list;
    }

    public Container()
    {
	user_list = new ArrayList<User>();
    }

    public Container(List<User> user_list)
    {
        this.user_list = user_list;
    }


}
