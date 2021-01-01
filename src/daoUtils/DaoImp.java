package daoUtils;

import pojo.Admin;
import pojo.Req;
import pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImp implements Dao{
    @Override
    public User selectUserByUsername(String username) {
        String sql = "select * from user where username=?";
        List<User> list = DBUtils.getList(User.class,sql,username);
        if(list.size()>=1)
            return list.get(0);
        return null;
    }

    @Override
    public Admin selectAdminByUsername(String username) {
        String sql = "select * from admin where adUsername=?";
        List<Admin> list = DBUtils.getList(Admin.class,sql,username);
        if(list.size()>=1)
            return  list.get(0);
        return null;
    }

    @Override
    public int findReqNumByUser(String username,String... condition) {
        String sql = null;
        int num = 0;
        try {
//        if (condition.length == 0) {
//            sql = "select count(*) from req where username=?";
//            num = DBUtils.getCountNumber(sql,username);
//        } else {
            sql = "select count(*) from req where username=? and state like ?";
            num = DBUtils.getCountNumber(sql,username,condition[0]);
//        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<Req> selectPageReqByUser(int start, int end, String username,String... condition) {
        String sql = null;
        List<Req> list = null;
//        if(condition.length==0) {
//            sql = "select name,reason,timeDay,state from req,user where req.username = ? and user.username = req.username limit ?,?";
//            list = DBUtils.getList(Req.class,sql,username,start,end);
//        }else {
            sql = "select id,name,reason,timeDay,state,startTime from req,user "
                    + "where req.username = ? and user.username = req.username and state like ? limit ?,?";
        System.out.println(username+" "+condition[0]+"  "+start+"  "+end);
            list = DBUtils.getList(Req.class,sql,username,condition[0],start,end);
//        }

        return list;
    }

    @Override
    public boolean addReq(Req req) {
        String sql = "insert into req(timeDay,state,reason,username,startTime)values(?,?,?,?,?)";
        boolean flag = false;
        try {
            flag = DBUtils.save(sql,req.getTimeDay(),req.getState(),req.getReason(),req.getUsername(),req.getStartTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUserMsg(User newUser) {
        String sql = "update user set password=?,name=?,age=?,gender=?,email=? where username=?";
        boolean flag = false;
        try {
            flag = DBUtils.save(sql,newUser.getPassword(),newUser.getName(),newUser.getAge(),newUser.getGender(),newUser.getEmail(),newUser.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Req selectReqById(String id) {
        String sql = "select * from req where id=?";
        List<Req> list = DBUtils.getList(Req.class,sql,id);
        if(list.size()<=0)
          return null;
        return list.get(0);
    }

    @Override
    public boolean updateReqMsq(Req req) {
        String sql = "update req set timeDay=?,reason=?,username=?,startTime=? where id=?";
        boolean flag = false;
        try {
            flag = DBUtils.save(sql,req.getTimeDay(),req.getReason(),req.getUsername(),req.getStartTime(),req.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int getAllUserReqNum(String condition) {
        String sql = "select count(*) from req where state like ?";
        int num = 0;
        try {
            num = DBUtils.getCountNumber(sql,condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<Req> selectPageAllUser(int start, int rw, String condition) {
        String sql = "select id,name,reason,timeDay,state,startTime from req,user "
                + "where user.username = req.username and state like ? limit ?,?";
        List<Req> list = DBUtils.getList(Req.class,sql,condition,start,rw);
        return list;
    }

    @Override
    public boolean updateReqState(String id) {
        String sql = "update req set state=? where id=?";
        boolean flag = false;
        try {
            flag = DBUtils.save(sql,"审批",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean addAdmin(Admin ad) {
        String sql = "insert into admin(adUsername,adPassword)values(?,?)";
        boolean flag = false;
        try {
            flag = DBUtils.save(sql,ad.getAdUsername(),ad.getAdPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int getAllUserNum(String username, String name) {
        StringBuffer sql = new StringBuffer("select count(*) from user where 1=1");
        ArrayList<String> al = new ArrayList<String>();
        if(!(username==null||"".equals(username))){
            sql.append(" and username like ?");
            al.add(username);
        }
        if(!(name==null||"".equals(name))){
            sql.append(" and name like ?");
            al.add(name);
        }
        System.out.println(sql.toString());
        int temp = 0;
        try {
            temp = DBUtils.getCountNumber(sql.toString(),al.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public List<User> selectAllUserMsg(String username, String name, int start, int rw) {
        StringBuffer sql = new StringBuffer("select * from user where 1=1");
        ArrayList<Object> al = new ArrayList<Object>();
        if(!(username==null||"".equals(username))){
            sql.append(" and username like ?");
            al.add(username);
        }
        if(!(name==null||"".equals(name))){
            sql.append(" and name like ?");
            al.add(name);
        }
        sql.append(" limit ?,?");
        al.add(start);
        al.add(rw);
        System.out.println(sql.toString());
        List<User> list = DBUtils.getList(User.class,sql.toString(),al.toArray());
        return list;
    }

}
