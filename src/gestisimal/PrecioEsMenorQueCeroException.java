package gestisimal;
/**
 * Precio menor que cero unchecked
 * @author alejandro
 *
 */
public class PrecioEsMenorQueCeroException extends RuntimeException {
  
  /**
   * 
   * @param string
   */
  
  public PrecioEsMenorQueCeroException(String string) {
    super(string);
  }
   
}
