package gestisimal;

/**
 * Si las unidades del articulo es menor que cero checked
 * @author alejandro
 *
 */

public class UnidadesArticuloEsMenorQueCeroException extends Exception {
  
  /**
   * 
   * @param string
   */
  public UnidadesArticuloEsMenorQueCeroException (String string) {
    super(string);
  }
}
