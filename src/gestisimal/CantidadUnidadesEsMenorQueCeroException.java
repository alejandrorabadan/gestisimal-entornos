package gestisimal;
/**
 * Cantidad de un articulo menor que cero
 * @author alejandro
 *
 */
public class CantidadUnidadesEsMenorQueCeroException extends Exception {
  /**
   * 
   * @param string String
   */
  
  public CantidadUnidadesEsMenorQueCeroException(String string) {
    super(string);
  }

  
}
