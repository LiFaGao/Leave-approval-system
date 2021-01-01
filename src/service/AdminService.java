package service;

import pojo.Admin;
import pojo.Page;
import pojo.Req;
import pojo.User;

public interface AdminService {
    public Admin getAdminByUsername(String username);

    public Page<Req> getPage(String currentPage, String row, String condition);

    public boolean updateReqState(String id);

    public boolean addAdmin(Admin ad);

    public Page<User> getPageForUser(String username, String name, String currentPage, String row);

    public  boolean deleteUser(String username);
}
