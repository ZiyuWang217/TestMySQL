package test;

	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Connection;
	import java.sql.Statement;
	 
	 
	public class Test2 {
	    public static void main(String[] args) throws Exception {
	           // ����������
	           String driver = "com.mysql.jdbc.Driver";
	           
	           // URLָ��Ҫ���ʵ����ݿ���scutcs
	           String url = "jdbc:mysql://120.25.227.116:3306/xsg_try";

	           // MySQL����ʱ���û���
	           String user = "xsg"; 
	  
	           // MySQL����ʱ������
	           String password = "xsg123456";

	           try { 
	            // ������������
	            Class.forName(driver);

	            // �������ݿ�
	            Connection conn = DriverManager.getConnection(url, user, password);

	            if(!conn.isClosed()) 
	             System.out.println("Succeeded connecting to the Database!");

	            // statement����ִ��SQL���
	            Statement statement = conn.createStatement();

	            // Ҫִ�е�SQL���
	            String sql = "insert into TABLE2 (CAS,MF) values (1234,H2O)";

	            // �����
	            ResultSet rs = statement.executeQuery(sql);
	            
	            sql = "select * from TABLE2";

	            // �����
	            rs = statement.executeQuery(sql);
	            System.out.println("-----------------");
	            System.out.println("ִ�н��������ʾ:");
	            System.out.println("-----------------");
	            System.out.println(" CAS " + "\t" + "ҩƷ��");
	            System.out.println("-----------------");

	            String name = null;

	            while(rs.next()) {
	    
	             // ѡ��sname��������
	             name = rs.getString("CNName");
	    
	             // ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
	             // Ȼ��ʹ��GB2312�ַ�������ָ�����ֽ�����
	            // name = new String(name.getBytes("ISO-8859-1"),"GB2312");

	             // ������
	             System.out.println(rs.getString("CAS") + "\t" + name);
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
