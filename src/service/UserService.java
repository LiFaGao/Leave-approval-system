package service;

import pojo.Page;
import pojo.Req;
import pojo.User;

import java.util.Map;

public interface UserService {
    public User getUserByName(String username);

    public User getUserByMap(Map<String, String[]> map);

    public boolean addUser(User user);

    public  Page<Req> findUserByPage(String currentPage, String row, String username,String... condition);

    public Boolean addReq(Req req);

    public boolean updateUserMsg(User newUser);

    public Req getReqById(String id);

    public boolean updateReqMsg(Req req);
}
