package gestisimal;

/**
 * Cantidad menor que cero unchecked
 * @author alejandro
 *
 */

public class CantidadEsMenorQueCeroException extends RuntimeException {
  /**
   * 
   * @param string String
   */
  
  public CantidadEsMenorQueCeroException (String string) {
    super(string);
  }


  
}
