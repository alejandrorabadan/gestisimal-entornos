package gestisimal;
/**
 * Excepcion articulo unchecked
 * @author alejandro
 *
 */
public class ArticuloErrorException extends IllegalArgumentException {
  /**
   * 
   * @param message String
   */
  public ArticuloErrorException(String message) {
    super(message);
  }
}
