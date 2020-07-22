import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;



public class Test1 {

	
	private static String CDRIVER;
	private static String CURL;
	private static String CUSERNAME;
	private static String CPASSWORD;
	
	 static {
	        Properties prop = new Properties();
	        InputStream in = Object.class.getResourceAsStream("/jdbc.properties");
	        try {
	            prop.load(in);
	            CDRIVER = prop.getProperty("CDRIVER").trim();
	            CURL = prop.getProperty("CURL").trim();
	            CUSERNAME = prop.getProperty("CUSERNAME").trim();
	            CPASSWORD = prop.getProperty("CPASSWORD").trim();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } 
//	 private static final String DRIVER = "com.ibm.as400.access.AS400JDBCDriver";
//   private static final String URL = "jdbc:as400://133.192.225.200/TDDTALIB;transaction isolation=none";
//   private static final String URL = "jdbc:as400://133.192.225.200/TEDTALIB;transaction isolation=none";
//   private static final String URL = "jdbc:as400://133.192.225.201/TEDTALIB;transaction isolation=none";
//   private static final String USERNAME = "TEOPER";
//   private static final String PASSWORD = "TEOPERTE";
    
    /**
     *
     * @param args parameter
     * @return 
     */
    //订单表
    public static ArrayList<EM600PR> queryTddTalib() throws ClassNotFoundException {
    	ResourceBundle rb = ResourceBundle.getBundle("sqlMap", Locale.getDefault());
    	Connection con = null;
    	Statement stmt = null;
        try {
            con = GetConnection.getConnection(CDRIVER, CURL, CUSERNAME, CPASSWORD);
            // SQLコンテナの作成
            stmt = con.createStatement();

    		ArrayList<EM600PR> tddtalibList = new ArrayList<EM600PR>();
    		
//    		Date date= new Date();
//    		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//    		String ff=dateFormat.format(date);
    		
    		String query_ed000pr = (String) rb.getObject("query_ed000pr");
//    		String concat = query_ed000pr.concat(ff);
            //ケースマーク情報の取得(E25500R)
            ResultSet rs = stmt.executeQuery(query_ed000pr);
			int rowCount = 0;
	        // 検索結果の取り出し
	        
	        while(rs.next()) {
	        	rowCount++;
	        	EM600PR tddtalib = new EM600PR();
	        	tddtalib.setODBYID(rs.getString("ODBYID").trim());
	        	tddtalib.setODCSID(rs.getString("ODCSID").trim());
	        	tddtalib.setODCUSN(rs.getString("ODCUSN").trim());
	        	tddtalib.setODSHPN(rs.getString("ODSHPN").trim());
	        	tddtalibList.add(tddtalib);
	        }
	        System.out.println(rowCount+"==sql1");
            //コネクションクローズ
            con.close();
            
            return tddtalibList;
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            return null;
        }
    }
   
}
