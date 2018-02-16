package pe.egcc.edutec.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.ParseConversionEvent;

import pe.egcc.edutec.db.AccesoDB;
import pe.egcc.edutec.dto.ProfesorDTO;


public class NegocioService {

	/**
	 * Solo se pueden programar cursos a partir del ciclo actual hacia adelante.
	 * El precio del curso se debe leer de la tabla TARIFA. Las vacantes es un
	 * campo editable, pero, por defecto debe mostrar 20. Por defecto, el curso
	 * programado debe estar activado. Puede ser que no se asigne profesor.
	 * 
	 * @return
	 */
	public int programarCurso(String ciclo, String curso, String profesor, String horario, int vacantes) {
		int id = 0;
		Connection cn = null;
		try {

			// Iniciando la Tx
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);

			// Validar el ciclo
			int anio = Integer.parseInt(ciclo.substring(0, 4));
			int mes = Integer.parseInt(ciclo.substring(5, 7));
			Calendar fechaActual = Calendar.getInstance();
			int anioSistema = fechaActual.get(Calendar.YEAR);
			int mesSistema = fechaActual.get(Calendar.MONTH) + 1;
			if (anio < anioSistema) {
				throw new SQLException("Ciclo incorrecto.");
			}
			if (anio == anioSistema && mes < mesSistema) {
				throw new SQLException("Ciclo incorrecto.");
			}

			// Leer precio
			String sql = "select t.PrecioVenta precio " + "from Tarifa t join Curso c " + "on t.IdTarifa = c.IdTarifa "
					+ "where c.IdCurso = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, curso);
			ResultSet rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException("Codigo de curso no existe.");
			}
			double precio = rs.getDouble("precio");
			rs.close();
			pstm.close();

			// Profesor
			if (profesor != null && profesor.length() == 0) {
				profesor = null;
			}

			// Insertar registro
			sql = "insert into CursoProgramado(IdCiclo,IdCurso," + "IdProfesor,Vacantes,Matriculados,"
					+ "PreCursoProg,Horario,Activo)" + "values(?,?,?,?,0,?,?,1)";
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
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			try {
				cn.rollback();
			} catch (Exception e2) {
			}
			throw new RuntimeException("Se ha producido un error.");
		} finally {
			try {
				cn.close();
			} catch (Exception e2) {
			}
		}
		return id;
	}

	public void RegistrarProfesor(String Nombre, String Apellido, String Direccion, String Telefono, String Email,
			String Clave) {
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
			String sql = "select MAX(IdProfesor)as IdProfesor from Profesor  ";
			PreparedStatement pstm = cn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			rs.next();
			String IdP = rs.getString("IdProfesor");
			IdP = IdP.substring(1);
			int Codigo=Integer.parseInt(IdP) + 1;
			sql = "insert into Profesor (" ;
			sql+="IdProfesor,ApeProfesor,NomProfesor,DirProfesor,TelProfesor";
			sql+=",EmailProfesor,Clave) ";		         
			sql+="values(?,?,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, "P0" + Codigo);
			pstm.setString(2, Apellido);
			pstm.setString(3, Nombre);
			pstm.setString(4, Direccion);
			pstm.setString(5, Telefono);
			pstm.setString(6, Email);
			pstm.setString(7, Clave);
			pstm.executeUpdate();
			pstm.close();
			cn.commit();
			cn.close();
			
		} catch (Exception e2) {
			System.out.println(e2.toString());
		}

	}

	public List<ProfesorDTO> listarArticulos() throws SQLException {
		 
		Connection cn = null;
		List<ProfesorDTO> listaProfesorDTO = new ArrayList<ProfesorDTO>();
		String sql = "SELECT * FROM Profesor";
	
		cn = AccesoDB.getConnection();
		PreparedStatement pstm = cn.prepareStatement(sql);
		ResultSet resulSet = pstm.executeQuery();
 
		while (resulSet.next()) {
			ProfesorDTO oProfesorDTO = new ProfesorDTO();
			oProfesorDTO.setIdProfesor(resulSet.getString("IdProfesor"));
			oProfesorDTO.setApeProfesor(resulSet.getString("ApeProfesor"));
			oProfesorDTO.setNomProfesor(resulSet.getString("NomProfesor"));
			oProfesorDTO.setDirProfesor(resulSet.getString("DirProfesor"));
			oProfesorDTO.setTelProfesor(resulSet.getString("TelProfesor"));
			oProfesorDTO.setEmailProfesor(resulSet.getString("EmailProfesor"));
			oProfesorDTO.setClave(resulSet.getString("Clave"));
					
			listaProfesorDTO.add(oProfesorDTO);
		}
		cn.close();
		return listaProfesorDTO;
	}

	public void AsignarProfesor(String IdCursoProg,String IdProfesor){
		
		
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			cn.setAutoCommit(false);
		
			String	sql = "update CursoProgramado set " ;
			sql+="IdProfesor='"+IdProfesor+"'";
			sql+="where  IdCursoProg='"+IdCursoProg+"'";	
			PreparedStatement pstm = cn.prepareStatement(sql);
	
	
			pstm.executeUpdate();
			pstm.close();
			cn.commit();
			cn.close();
			
		} catch (Exception e2) {
			System.out.println(e2.toString());
		}

		
	}

public int Validar(String IdCursoProg,String IdProfesor){
	
	int id = 0;
	Connection cn = null;
	try {
			cn = AccesoDB.getConnection();
		
			
			String 	sql="select count(*) as Cantidad from [dbo].[CursoProgramado]";
			sql+="where IdCiclo in (select IdCiclo from [CursoProgramado] where IdCursoProg="+IdCursoProg+")";
			sql+="	and IdProfesor='"+IdProfesor+"' ";
					
			PreparedStatement pstm = cn.prepareStatement(sql);			
			ResultSet rs = pstm.executeQuery();
			rs.next();
			id = rs.getInt("Cantidad");
			
			return id;
		
	} catch (Exception e) {
		// TODO: handle exception
		return 0;
	}

}
}
