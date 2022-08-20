package database;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    private static DataSource ds = new DataSource();

    static {

        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:sqlserver://enterprise-application.database.windows.net:1433;database=enterpriseApplication;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");
        p.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        p.setUsername("login");
        p.setPassword("Admin1234");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        ds.setPoolProperties(p);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDataSource(){ }
}
