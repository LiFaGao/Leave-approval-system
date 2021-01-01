package daoUtils;

import pojo.Admin;
import pojo.Req;
import pojo.User;

import java.util.List;

public interface Dao {
    public User selectUserByUsername(String Username);
    public Admin selectAdminByUsername(String username);
    public int findReqNumByUser(String username,String... condition);

    public List<Req> selectPageReqByUser(int start, int end, String username,String... condition);

    public boolean addReq(Req req);

    public boolean updateUserMsg(User newUser);

    public Req selectReqById(String id);

    public boolean updateReqMsq(Req req);

    public int getAllUserReqNum(String condition);

    public List<Req> selectPageAllUser(int start, int rw, String condition);

    public boolean updateReqState(String id);

    public boolean addAdmin(Admin ad);

    public int getAllUserNum(String username, String name);

    public List<User> selectAllUserMsg(String username, String name, int start, int rw);

}
