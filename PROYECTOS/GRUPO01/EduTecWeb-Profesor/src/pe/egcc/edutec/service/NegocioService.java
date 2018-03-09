package pe.egcc.edutec.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import pe.egcc.edutec.db.AccesoDB;

public class NegocioService {

  /**
   * Solo se pueden programar cursos a partir del ciclo actual hacia adelante.
   * El precio del curso se debe leer de la tabla TARIFA.
   * Las vacantes es un campo editable, pero, por defecto debe mostrar 20.
   * Por defecto, el curso programado debe estar activado.
   * Puede ser que no se asigne profesor.
   * 
   * @return
   */
  public int programarCurso(String ciclo, String curso, String profesor, String horario, int vacantes)
  {
    int id = 0;
    Connection cn = null;
    try 
    {
      //Iniciando la Tx
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      
      // Validar el ciclo
      int anio = Integer.parseInt(ciclo.substring(0,4));
      int mes = Integer.parseInt(ciclo.substring(5,7));
      Calendar fechaActual = Calendar.getInstance();
      int anioSistema = fechaActual.get(Calendar.YEAR);
      int mesSistema = fechaActual.get(Calendar.MONTH) + 1;      
      if(anio < anioSistema)
      {
        throw new SQLException("Ciclo incorrecto.");
      }
      if(anio == anioSistema && mes < mesSistema)
      {
        throw new SQLException("Ciclo incorrecto.");
      }
            
      // Leer precio
      String sql = "select t.PrecioVenta precio "
          + "from Tarifa t join Curso c "
          + "on t.IdTarifa = c.IdTarifa "
          + "where c.IdCurso = ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, curso);
      ResultSet rs = pstm.executeQuery();
      if(!rs.next())
      {
        throw new SQLException("Codigo de curso no existe.");
      }
      double precio = rs.getDouble("precio");
      rs.close();
      pstm.close();
      
      // Profesor
      if(profesor != null && profesor.length() == 0)
      {
        profesor = null;
      }
      
      // Insertar registro
      sql = "insert into CursoProgramado(IdCiclo,IdCurso,"
          + "IdProfesor,Vacantes,Matriculados,"
          + "PreCursoProg,Horario,Activo)"
          + "values(?,?,?,?,0,?,?,1)";
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, ciclo);
      pstm.setString(2, curso);
      pstm.setString(3, profesor);
      pstm.setInt(4, vacantes);
      pstm.setDouble(5, precio);
      pstm.setString(6, horario);
      pstm.executeUpdate();
      pstm.close();
      
      // Leer el id
      sql = "select IDENT_CURRENT( 'CursoProgramado' ) id";
      pstm = cn.prepareStatement(sql);
      rs = pstm.executeQuery();
      rs.next();
      id = rs.getInt("id");
      rs.close();
      pstm.close();
      
      // Confirmar Tx
      cn.commit();
    } 
    catch (SQLException e) 
    {
      try 
      {
        cn.rollback();
      } 
      catch (Exception e2) 
      {
      }
      throw new RuntimeException(e.getMessage());
    } 
    catch (Exception e) 
    {
      try 
      {
        cn.rollback();
      } 
      catch (Exception e2) 
      {
      }
      throw new RuntimeException("Se ha producido un error.");
    } 
    finally
    {
      try 
      {
        cn.close();
      } 
      catch (Exception e2) 
      {
      }
    }
    return id;
  }

  public String GenerarCiclo(String ciclo, String fechini, String fechfin)
  {
    String  id;
    Connection cn = null;
    try 
    {
      //Iniciando la Tx
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      
      
	// Insertar registro
      String sql = 
    "INSERT INTO [dbo].[Ciclo] ([IdCiclo] ,[FecInicio] ,[FecTermino]) "
    + "VALUES ('"+ ciclo+"',convert(datetime,'"+  fechini  +"' ),convert(datetime,"+  fechfin  +"))";
      PreparedStatement pstm2 = cn.prepareStatement(sql);
      //ResultSet rs = pstm2.executeQuery();
      pstm2.executeUpdate();
      pstm2.close();
      
      // Leer el id
      //sql = "SELECT TOP 1(IDCICLO) as id FROM CICLO ORDER BY IDCICLO DESC";
      //pstm2 = cn.prepareStatement(sql);
      //rs = pstm2.executeQuery();
      //rs.next();
      //id = rs.getString("id");
      //rs.close();
      //pstm2.close();
      
      // Confirmar Tx
      cn.commit();
    } 
    catch (SQLException e) 
    {
      try 
      {
        cn.rollback();
      } 
      catch (Exception e2) 
      {
      }
      throw new RuntimeException(e.getMessage());
    } 
    catch (Exception e) 
    {
      try 
      {
        cn.rollback();
      } 
      catch (Exception e2) 
      {
      }
      throw new RuntimeException("Se ha producido un error.");
    } 
    finally
    {
      try 
      {
        cn.close();
      } 
      catch (Exception e2) 
      {
      }
    }
    return "Se registro exitosamente el ciclo" + ciclo;
  }

  
  public String registrarNota(Integer ciclo, String alumno, String fechamatricula, Double examenparcial,
		  					Double examenfinal,Double promedio,Double subsanacion,Double exsubsanacion)
  {   
    String  id;
    Connection cn = null;
    try 
    {
      //Iniciando la Tx
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      
      
	// Insertar registro
      String sql = 
    		  "INSERT INTO [dbo].[Matricula]([IdCursoProg],[IdAlumno],[FecMatricula],[ExaParcial],[ExaFinal],[Promedio],[Subsanacion],[ExaSub])"
    		  +"VALUES("+ciclo+", "+alumno+" , convert(datetime,'"+  fechamatricula  +"' ), "+examenparcial+" , "+examenfinal+", "+promedio+", "+subsanacion+", "+exsubsanacion+")";

    		  
      PreparedStatement pstm2 = cn.prepareStatement(sql);
      //ResultSet rs = pstm2.executeQuery();
      pstm2.executeUpdate();
      pstm2.close();
      
      // Leer el id
      //sql = "SELECT TOP 1(IDCICLO) as id FROM CICLO ORDER BY IDCICLO DESC";
      //pstm2 = cn.prepareStatement(sql);
      //rs = pstm2.executeQuery();
      //rs.next();
      //id = rs.getString("id");
      //rs.close();
      //pstm2.close();
      
      // Confirmar Tx
      cn.commit();
    } 
    catch (SQLException e) 
    {
      try 
      {
        cn.rollback();
      } 
      catch (Exception e2) 
      {
      }
      throw new RuntimeException(e.getMessage());
    } 
    catch (Exception e) 
    {
      try 
      {
        cn.rollback();
      } 
      catch (Exception e2) 
      {
      }
      throw new RuntimeException("Se ha producido un error.");
    } 
    finally
    {
      try 
      {
        cn.close();
      } 
      catch (Exception e2) 
      {
      }
    }
    return "Se registro exitosamente la nota correspondiente";
  }
  
}
