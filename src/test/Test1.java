package test;

	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Connection;
	import java.sql.Statement;
	 
	 
	public class Test1 {
	    public static void main(String[] args) throws Exception {
	           // 驱动程序名
	           String driver = "com.mysql.jdbc.Driver";
	           
	           // URL指向要访问的数据库名scutcs
	           String url = "jdbc:mysql://120.25.227.116:3306/xsg_try?characterEncoding=GB2312";

	           // MySQL配置时的用户名
	           String user = "xsg"; 
	  
	           // MySQL配置时的密码
	           String password = "xsg123456";

	           try { 
	            // 加载驱动程序
	            Class.forName(driver);

	            // 连续数据库
	            Connection conn = DriverManager.getConnection(url, user, password);

	            if(!conn.isClosed()) 
	             System.out.println("Succeeded connecting to the Database!");

	            // statement用来执行SQL语句
	            Statement statement = conn.createStatement();

	            // 要执行的SQL语句
	            String sql = "select * from TABLE2";

	            // 结果集
	            ResultSet rs = statement.executeQuery(sql);

	            System.out.println("-----------------");
	            System.out.println("执行结果如下所示:");
	            System.out.println("-----------------");
	            System.out.println(" CAS " + "\t" + "药品名");
	            System.out.println("-----------------");

	            String name = null;

	            while(rs.next()) {
	    
	             // 选择sname这列数据
	             name = rs.getString("CNName");
	    
	             // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
	             // 然后使用GB2312字符集解码指定的字节数组
	        //name = new String(name.getBytes("ISO-8859-1"),"GB2312");

	             // 输出结果
	             System.out.println(rs.getString("CAS")+"\t" + name);
	            }

	            rs.close();
	            conn.close();

	           } catch(ClassNotFoundException e) {


	            System.out.println("Sorry,can`t find the Driver!"); 
	            e.printStackTrace();


	           } catch(SQLException e) {


	            e.printStackTrace();


	           } catch(Exception e) {


	            e.printStackTrace();


	           } 
	 
	    }
	 
	}
