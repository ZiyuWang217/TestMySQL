package test;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.Scanner;
  
public class Insert_1{  
    private Connection conn = null;  
    PreparedStatement statement = null;  
  
    // connect to MySQL  
    void connSQL() {  
        String url = "jdbc:mysql://120.25.227.116:3306/xsg_try?characterEncoding=GB2312";  
        String username = "xsg";  
        String password = "xsg123456"; // 加载驱动程序以连接数据库   
        try {   
            Class.forName("com.mysql.jdbc.Driver" );   
            conn = DriverManager.getConnection( url,username, password );   
            }  
        //捕获加载驱动程序异常  
         catch ( ClassNotFoundException cnfex ) {  
             System.err.println(  
             "装载 JDBC/ODBC 驱动程序失败。" );  
             cnfex.printStackTrace();   
         }   
         //捕获连接数据库异常  
         catch ( SQLException sqlex ) {  
             System.err.println( "无法连接数据库" );  
             sqlex.printStackTrace();   
         }  
    }  
  
    // disconnect to MySQL  
    void deconnSQL() {  
        try {  
            if (conn != null)  
                conn.close();  
        } catch (Exception e) {  
            System.out.println("关闭数据库问题 ：");  
            e.printStackTrace();  
        }  
    }  
  
    // execute selection language  
    ResultSet selectSQL(String sql) {  
        ResultSet rs = null;  
        try {  
            statement = conn.prepareStatement(sql);  
            rs = statement.executeQuery(sql);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return rs;  
    }  
  
    // execute insertion language  
    boolean insertSQL(String sql) {  
        try {  
            statement = conn.prepareStatement(sql);  
            statement.executeUpdate();  
            return true;  
        } catch (SQLException e) {  
            System.out.println("插入数据库时出错：");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("插入时出错：");  
            e.printStackTrace();  
        }  
        return false;  
    }  
   
    // show data in ju_usecrs  
    void layoutStyle2(ResultSet rs) {  
        System.out.println("-----------------");  
        System.out.println("执行结果如下所示:");  
        System.out.println("-----------------");  
        System.out.println("CAS" + "/t/t" + "CBNumber" + "/t/t" +"ENName"+ "/t/t" + "CNName");  
        System.out.println("-----------------");  
        try {  
            while (rs.next()) {  
                System.out.println(rs.getString("CAS") + "/t/t"  
                                 + rs.getString("CBNumber") + "/t/t"  
                                 + rs.getString("ENName")+ "/t/t"  
                                 + rs.getString("CNName"));  
            }  
        } catch (SQLException e) {  
            System.out.println("显示时数据库出错。");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("显示出错。");  
            e.printStackTrace();  		   											
        }  
    }  
  
    public static void main(String args[]) {  
  
        Insert_1 h = new Insert_1();  
        h.connSQL();  
        String s = "select * from TABLE2";  
        Scanner input = new Scanner(System.in);
        System.out.print("please give me the cas number ");
        String cas = input.next();
        System.out.print("please give me the CBNumber ");
        String cbn = input.next();
        System.out.print("please give me the ENName ");
        String enn = input.next();
        System.out.print("please give me the CNName ");
        String cnn = input.next();
        String insert = "insert into TABLE2(CAS,CBNumber,ENName,CNName) values("+cas+","+cbn+","+enn+","+cnn+")";  
       
  
        if (h.insertSQL(insert) == true) {  
            System.out.println("insert successfully");  
            ResultSet resultSet = h.selectSQL(s);  
            h.layoutStyle2(resultSet);  
        }  
        
        h.deconnSQL();  
    }  
}  
  