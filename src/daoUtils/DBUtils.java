package daoUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class DBUtils{

    public static Connection getConnection() throws SQLException {
//        DataSource ds = new DruidDataSource();
        DataSource ds =null;
        try {
            Properties pro = new Properties();
            InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("daoUtils/db.properties");
            pro.load(in);
            ds = DruidDataSourceFactory.createDataSource(pro);
//            System.out.println(ds);
//            ds.setUrl(pro.getProperty("url"));
//            ds.setDriverClassName(pro.getProperty("drive"));
//            ds.setUsername(pro.getProperty("username"));
//            ds.setPassword("password");
//            ds.setInitialSize(10);  //初始连接数，默认0
//            ds.setMaxActive(30);  //最大连接数，默认8
//            ds.setMinIdle(10);  //最小闲置数
//            ds.setMaxWait(2000);  //获取连接的最大等待时间，单位毫秒
//            ds.setMaxOpenPreparedStatements(20); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds.getConnection();
//        LocalConnection lc = new LocalConnection();
//        return lc.getConnection();
    }
    public static <T> List<T> getList(Class<T> clazz,String sql,Object...args){
        List<T> list = new ArrayList<T>();
        Connection con = null;
        PreparedStatement pdsm = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pdsm= con.prepareStatement(sql);
            for(int i = 0; i<args.length;i++) {
                pdsm.setObject(i + 1, args[i]);
            }
            rs = pdsm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int size = rsmd.getColumnCount();
            while(rs.next()) {
                Map<String,Object> map = new HashMap<String,Object>();
                for (int i = 1; i <= size; i++) {
                    String title = rsmd.getColumnLabel(i);
                    Object value = rs.getObject(title);
                    map.put(title, value);
                }
                T temp = clazz.newInstance();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object values = entry.getValue();
                    String methodname = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
//                    System.out.println(methodname+"    "+values);
                    Method method = null;
                    if(java.sql.Date.class==values.getClass()){
                        java.sql.Date sqlDate = (Date) values;
                        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                        method = clazz.getMethod(methodname, utilDate.getClass());
                    }else {
                        method = clazz.getMethod(methodname, values.getClass());
                    }
                    method.invoke(temp,values);
//                    BeanUtils.setProperty(temp,key,values);
                }
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pdsm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static boolean save(String sql,Object... argx) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pdst = con.prepareStatement(sql);
        for(int i = 0; i < argx.length; i++){
            if(java.util.Date.class==argx[i].getClass()){
                java.util.Date utilDate = (java.util.Date)argx[i];
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                pdst.setObject(i+1,sqlDate);
            }else {
                pdst.setObject(i + 1, argx[i]);
            }
        }
        int result = pdst.executeUpdate();
        con.close();
        pdst.close();
        return result>0;
    }
    public static int getCountNumber(String sql,Object... argx) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pdst = con.prepareStatement(sql);
        for(int i = 0; i < argx.length; i++){
            pdst.setObject(i+1,argx[i]);
        }
        ResultSet rs = pdst.executeQuery();
        int num = 0;
        if(rs.next())
            num = rs.getInt(1);
        con.close();
        pdst.close();
        return num;
    }
}
