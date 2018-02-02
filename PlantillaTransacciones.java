    Connection cn = null;
    try {
      // Iniciando la Tx
      cn = AccesoDB.getConnection();
      cn.setAutoCommit(false);
      
      
      
      
      
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
    } finally{
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }