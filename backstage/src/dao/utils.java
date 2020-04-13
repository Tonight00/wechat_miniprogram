package dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class utils
{
    private static DataSource ds;
    private static Connection conn;
    private static ResultSet rst;
    private static PreparedStatement ppst;
    private static String sql;
    private static final BASE64Decoder decoder = new BASE64Decoder();
    private static final BASE64Encoder encoder = new BASE64Encoder();
    
    static {
        Properties pro = new Properties();
        InputStream in = utils.class.getClassLoader().getResourceAsStream("driud.properties");
        try {
            pro.load(in);
            ds =  DruidDataSourceFactory.createDataSource(pro);
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private utils () { }
    
    private static void Close(){
        try {
            if (rst!=null &&!rst.isClosed())
                rst.close();
            ppst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    private static void  Create( ) throws SQLException {
        
        conn = ds.getConnection();
        ppst = conn.prepareStatement(sql);
    }
    
    
    public static void GetPPST(Object ...params) throws SQLException
    {
        int pos = 1;
        Create();
        for (Object param : params) {
            ppst.setObject(pos,param);
            pos+=1;
        }
    }
    
    public static boolean isExists(String name, String password) {
        boolean ret = false;
        try {
            sql = "select id from 账户表 where name = ? ";
            GetPPST(name);
            rst = ppst.executeQuery();
            if(rst.next())
                ret = true;
            Close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    public static boolean login (String name,String password,String id,String tel) {
        
        System.out.println("login : " + name + password + id + tel);
        try {
            if( isExists(name,password) )
                return false;
            sql = "insert into 账户表(name,password,pid,tel) values(?,?,?,?)";
            GetPPST(name,password,id,tel);
            ppst.execute();
            Close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public  static boolean signUp (String name, String password) {
        sql = "select * from 账户表 where name = ? and password = ?";
        boolean ret = false;
        try
        {
            GetPPST(name,password);
            rst = ppst.executeQuery();
            if(rst.next())
                ret =  true;
            //if(rst.next())
             //   return true;
            Close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    public static ArrayList<User> getInfo (String name, String password) {
    
        ArrayList<User> ret = new ArrayList<>();
        sql = "select name,assets,rates from 账户表 where name = ? and password = ?";
        try {
            GetPPST(name,password);
            rst = ppst.executeQuery();
            ResultSetMetaData rsmt = rst.getMetaData();
            ArrayList<String> labels = new ArrayList<>();
            for ( int i = 0; i < rsmt.getColumnCount();i++ )
                labels.add(rsmt.getColumnName(i+1));
            while(rst.next()){
                User  user = new User();
                for (String label : labels) {
                    user.add(label,rst.getObject(label));
                }
                ret.add(user);
            }
            Close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    public static boolean store (String name,String password, BigDecimal store_money) {
        BigDecimal new_assests = ( utils
                                    .getInfo(name,password)
                                    .get(0)
                                    .getAssets()
                                    .add(store_money) );
        sql = "update 账户表 set assets = ? where name = ?";
        boolean ret  = false;
        try {
            GetPPST(new_assests,name);
            if( ppst.executeUpdate() == 1)
                ret = true;
            Close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    /**
     * type 为0的时候代表取钱，为1的时候代表存钱
     * @param name
     * @param money
     * @return
     */
    public static boolean log (String name,BigDecimal money)
    {
        int type = 0;
        if (money.compareTo(BigDecimal.ZERO) >0 )
            type = 1;
        sql = "insert into log(name,money,type) values(?,?,?)";
        boolean ret = true;
        try {
            GetPPST(name,money,type);
            ppst.execute();
            Close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    
    
    public static String Base64Encode(String s) {
        byte [] bts = s.getBytes();
        String  encode_s = encoder.encode(bts);
        return encode_s;
    }
    
   public static String Base64Decode(String es) throws  Exception {
        return new String(decoder.decodeBuffer(es));
   }
   
   
   
}
