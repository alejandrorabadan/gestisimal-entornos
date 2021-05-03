package gestisimal;
/**
 * Cantidad de un articulo menor que cero
 * @author alejandro
 *
 */
public class CantidadUnidadesEsMenorQueCeroException extends Exception {
  /**
   * 
   * @param string
   */
  
  public CantidadUnidadesEsMenorQueCeroException(String string) {
    super(string);
  }

  
}
