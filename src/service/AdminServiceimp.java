package service;

import daoUtils.DBUtils;
import daoUtils.Dao;
import daoUtils.DaoImp;
import pojo.Admin;
import pojo.Page;
import pojo.Req;
import pojo.User;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceimp implements AdminService{
    /**
     * 根据管理员的用户名获取管理员
     * @param username 用户名
     * @return Admin
     */
    @Override
    public Admin getAdminByUsername(String username) {
        Dao dao = new DaoImp();
        Admin ad = dao.selectAdminByUsername(username);
        return ad;
    }

    /**
     * 根据条件获取一个Page对象
     * @param currentPage 当前页码
     * @param row 每页记录条数
     * @param condition 每条记录的条件
     * @return Page<Req> 返回一个Page对象
     */
    @Override
    public Page<Req> getPage(String currentPage, String row, String condition) {
        Page<Req> page = new Page<Req>();
        int cp = Integer.parseInt(currentPage);
        int rw = Integer.parseInt(row);
        Dao dao = new DaoImp();
        page.setRow(rw);
        //判断越界情况
        if(cp<=0)
           cp = 1;
        //计算总记录数
        int count = 0;
        if("*".equals(condition))
            condition = "%";
        count = dao.getAllUserReqNum(condition);
        page.setTotalnumber(count);
        //计算总页数
//        System.out.println(count+"   "+(count/rw));
        count = count%rw==0?count/rw:((count/rw)+1);
        page.setTotalPagenum(count);
        if(count>0&&cp>count)
            cp = count;
        page.setCurrentPage(cp);
        //计算记录开始条数
        int start = (cp-1)*rw;
        //获取相应的记录集合
        List<Req> list = dao.selectPageAllUser(start,rw,condition);
        page.setList(list);
//        System.out.println(page);
        return page;
    }

    /**
     * 根据申请的ID跟新state为审批
     * @param id 对应的ID
     * @return boolea 成功true 否则false
     */
    @Override
    public boolean updateReqState(String id) {
        Dao dao = new DaoImp();
        return dao.updateReqState(id);
    }

    /**
     * 新增一个管理员
     * @param ad 新增管理员信息
     * @return boolean 成功true 否则false
     */
    @Override
    public boolean addAdmin(Admin ad) {
        Dao dao = new DaoImp();
        return dao.addAdmin(ad);
    }

    /**
     * 根据用户名或者姓名来获取一个Page对象
     * @param username 对应的用户名
     * @param name 对应的姓名
     * @param currentPage page的开始页
     * @param row page的每页行数
     * @return Page<User> 放回一个Page对象否则这个对象里的属性没有值
     */
    @Override
    public Page<User> getPageForUser(String username, String name, String currentPage, String row) {
        Page<User> page = new Page<User>();
        int cp = Integer.parseInt(currentPage);
        int rw = Integer.parseInt(row);
        Dao dao = new DaoImp();
        page.setRow(rw);
        //判断越界情况
        if(cp<=0)
            cp = 1;
        //计算总记录数
        int count = 0;
        count = dao.getAllUserNum(username,name);
        page.setTotalnumber(count);
        //计算总页数
//        System.out.println(count+"   "+(count/rw));
        count = count%rw==0?count/rw:((count/rw)+1);
        page.setTotalPagenum(count);
        if(count>0&&cp>count)
            cp = count;
        page.setCurrentPage(cp);
        //计算记录开始条数
        int start = (cp-1)*rw;
        //获取相应的记录集合
     List<User> list = dao.selectAllUserMsg(username,name,start,rw);
     page.setList(list);

//        System.out.println(page);
        return page;
    }

    /**
     * 删除用户的所有信息（包括提交的请假申请）
     * @param username 要删除用户的用户名
     * @return boolean 成功true 否则false
     */
    @Override
    public boolean deleteUser(String username) {
        if(!deleteUserReq(username))
            return false;
        String sql = "delete from user where username=?";
        boolean flag = false;
        try {
            flag =DBUtils.save(sql,username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    private boolean deleteUserReq(String username){
        String sql = "delete from req where username=?";
        boolean flag = false;
        try {
            flag = DBUtils.save(sql,username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
