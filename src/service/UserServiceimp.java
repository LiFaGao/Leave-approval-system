package service;

import daoUtils.DBUtils;
import daoUtils.Dao;
import daoUtils.DaoImp;
import org.apache.commons.beanutils.BeanUtils;
import pojo.Page;
import pojo.Req;
import pojo.User;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserServiceimp implements  UserService{
    /**
     * 通过用户名找用户
     * @param username 用户名
     * @return User
     */
    @Override
    public User getUserByName(String username) {
        User user = new DaoImp().selectUserByUsername(username);
        return user;
    }

    /**
     * 根据一个参数map返回一个User
     * @param map 参数列表
     * @return User
     */
    @Override
    public User getUserByMap(Map<String, String[]> map) {
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 添加新用户
     * @param user 新的用户信息
     * @return boolea 成功true 否则false
     */
    @Override
    public boolean addUser(User user) {
        StringBuffer sql = new StringBuffer("insert into user(username,password,name,gender");
        StringBuffer sb = new StringBuffer("values(?,?,?,?");
        ArrayList<Object> al = new ArrayList<Object>();
        al.add(user.getUsername());
        al.add(user.getPassword());
        al.add(user.getName());
        al.add(user.getGender());
        if(!(user.getEmail()==null||"".equals(user.getEmail()))){
            sql.append(",email,");
            sb.append(",?,");
            al.add(user.getEmail());
        }
        if(user.getAge()!=0){
            sql.append("age");
            sb.append("?");
            al.add(user.getAge());
        }
        sql.append(")");
        sb.append(")");
        sql.append(sb);
//        System.out.println(sql.toString());
//        System.out.println(al);
        boolean flag = false;
        try {
            flag = DBUtils.save(sql.toString(),al.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 给用户的申请信息进行分页
     * @param currentPage 当前所在页
     * @param row 每页的行数
     * @param username 用户名
     * @param condition 条件
     * @return Page<Req> 返回一个页面对象Page
     */
    @Override
    public Page<Req> findUserByPage(String currentPage, String row,String username,String... condition) {
        //将获取的数据转换成数字
        int cp = Integer.parseInt(currentPage);
        int rw = Integer.parseInt(row);
        //判读越界
        if(cp<=0) {
            cp = 1;
        }
//        System.out.println(cp+"  "+rw);
        //创建一个空的Page对象
        Page<Req> page = new Page<Req>();
        //未Page对象设置属性
        Dao dao = new DaoImp();
        page.setRow(rw);
        //获取总记录数
        int temp = dao.findReqNumByUser(username,condition);
        page.setTotalnumber(temp);
        temp = temp%rw==0?temp/rw:(temp/rw)+1;
        page.setTotalPagenum(temp);
        if(cp>temp&&temp>0)
            cp = temp;
        page.setCurrentPage(cp);
        int start = (cp-1)*rw;
        List<Req> list = dao.selectPageReqByUser(start,rw,username,condition);
        page.setList(list);
//        System.out.println(page);
        return page;
    }

    /**
     * 这是将请假申请写入数据库的函数
     * @param req 需要写入的请假申请
     * @return boolean 写入成功true，否则false
     */
    @Override
    public Boolean addReq(Req req) {
        Dao dao = new DaoImp();
        boolean flag = dao.addReq(req);
        return flag;
    }

    /**
     * 更新用户信息方法
     * @param newUser 新的用户信息
     * @return boolean 跟新成功true，否则false
     */
    @Override
    public boolean updateUserMsg(User newUser) {
        Dao dao = new DaoImp();
        boolean flag = dao.updateUserMsg(newUser);
        return flag;
    }

    /**
     * 根据id从数据库里获取req
     * @param id 对应的id
     * @return Req 成功返回相应的Req对象，否则null
     */
    @Override
    public Req getReqById(String id) {
        Dao dao = new DaoImp();
        Req req = dao.selectReqById(id);
        return req;
    }

    /**
     * 更新申请信息
     * @param req 新的申请信息
     * @return boolean 成功true 否则false
     */
    @Override
    public boolean updateReqMsg(Req req) {
        Dao dao = new DaoImp();
        return dao.updateReqMsq(req);
    }
}
