package gestisimal;
/**
 * Articulo ya existe unchecked
 * @author alejandro
 *
 */
public class ArticuloYaExisteException extends RuntimeException {
  /**
   * 
   * @param string
   */

  public ArticuloYaExisteException(String string){
    
      super(string);
  }
}
