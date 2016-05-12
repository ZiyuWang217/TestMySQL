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
        String password = "xsg123456"; // ���������������������ݿ�   
        try {   
            Class.forName("com.mysql.jdbc.Driver" );   
            conn = DriverManager.getConnection( url,username, password );   
            }  
        //����������������쳣  
         catch ( ClassNotFoundException cnfex ) {  
             System.err.println(  
             "װ�� JDBC/ODBC ��������ʧ�ܡ�" );  
             cnfex.printStackTrace();   
         }   
         //�����������ݿ��쳣  
         catch ( SQLException sqlex ) {  
             System.err.println( "�޷��������ݿ�" );  
             sqlex.printStackTrace();   
         }  
    }  
  
    // disconnect to MySQL  
    void deconnSQL() {  
        try {  
            if (conn != null)  
                conn.close();  
        } catch (Exception e) {  
            System.out.println("�ر����ݿ����� ��");  
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
            System.out.println("�������ݿ�ʱ����");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("����ʱ����");  
            e.printStackTrace();  
        }  
        return false;  
    }  
   
    // show data in ju_usecrs  
    void layoutStyle2(ResultSet rs) {  
        System.out.println("-----------------");  
        System.out.println("ִ�н��������ʾ:");  
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
            System.out.println("��ʾʱ���ݿ����");  
            e.printStackTrace();  
        } catch (Exception e) {  
            System.out.println("��ʾ����");  
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
  