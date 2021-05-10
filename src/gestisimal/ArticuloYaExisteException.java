package gestisimal;
/**
 * Articulo ya existe unchecked
 * @author alejandro
 *
 */
public class ArticuloYaExisteException extends RuntimeException {
  /**
   * 
   * @param string String
   */

  public ArticuloYaExisteException(String string){
    
      super(string);
  }
}
