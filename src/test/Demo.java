package test;

import daoUtils.DBUtils;
import daoUtils.LocalConnection;
import pojo.Req;
import pojo.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) {
//        List<User> list = DBUtils.getList(User.class,"select username,password from user");
//        for(User use:list){
//            System.out.println(use);
//        }
//        LocalConnection con = new LocalConnection();
//        System.out.println(con.getConnection());
//        System.out.println(DBUtils.getList(Req.class,"select name,reason,timeDay,state from req,user "
//                + "where req.username = ? and user.username = req.username and state like ? limit ?,?"
//                ,"741852963","%",1,5));
//        System.out.println(DBUtils.getList(Req.class,"select username,startTime,state,timeDay,reason from req where username=?","741852963"));
//        System.out.println("2018-12-22".matches("[\\d]{4}+-[\\d]{2}+-[\\d]{2}+"));
//        Date date = new Date();
//        Object o = date;
//        System.out.println(date.getClass());
        String sql = "update req set startTime=? where id=?";
        try {
            DBUtils.save(sql,new Date(),1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
