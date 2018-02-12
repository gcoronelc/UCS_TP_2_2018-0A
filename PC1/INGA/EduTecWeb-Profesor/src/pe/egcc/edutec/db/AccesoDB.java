package pe.egcc.edutec.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.com
 */
public class AccesoDB {

  private AccesoDB() {
  }

  public static Connection getConnection() throws SQLException {
    Connection cn = null;
    try {

      // Datos SQL Server
      String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
      String url = "jdbc:sqlserver://192.168.1.105:1433;databaseName=EDUTEC";
      String user = "sa";
      String pass = "992215424";
      // Cargar el driver a memoria
      Class.forName(driver).newInstance();
      // Obtener el objeto Connection
      cn = DriverManager.getConnection(url, user, pass);
    } catch (SQLException e) {
      throw e;
    } catch(ClassNotFoundException e){
      throw new SQLException("ERROR, no se encuentra el driver.");
    } catch(Exception e){
      throw new SQLException("ERROR, no se tiene acceso al servidor.");
    }
    return cn;
  }
}