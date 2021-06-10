package gestisimal;
/**
 *<p>Clase Artículo que representa a los artículos del almacén.</p> 
 * <p>Su estado será: código, descripción, precio de compra, precio de venta, número de unidades (nunca negativas), stock de seguridad y stock máximo</p>
 * Como comportamiento: Consideramos que el código va a generarse de forma automática en el constructor, así no puede haber dos artículos con el 
 * mismo código. Esto implica que no puede modificarse el código de un artículo, sí el resto de las propiedades. 
 * Podremos mostrar el artículo, por lo que necesito una representación del artículo en forma de cadena (toString).</p>
 * @author alejandro rabdan rivas
 *
 */


public class Articulo {

  /**
   * codigo
   */
  private int codigo = 0;
  /**
   * codigo anterior para generar el codigo
   */
  private static int codigoAnterior = 1;
  /**
   * descripcion de un articulo
   */
  private String descripcion;
  /**
   * precio de compra de un articulo
   */
  private double precioCompra;
  /**
   * precio de venta de un articulo
   */
  private double precioVenta;
  /**
   * cantidad de unidades de un articulo
   */
  private int cantidadUnidades;
  /**
   * stock seguridad de un articulo
   */
  private int stockseguridad;
  /**
   * stock maximo de un articulo
   */
  private int stockmaximo;

  /**
   * 
   * @param descripcion Descripción del articulo a añadir 
   * @param precioCompra Precio de compra del articulo a añadir 
   * @param precioVenta Precio de venta del articulo a añadir 
   * @param cantidadUnidades Cantidad de unidades del articulo a añadir 
   * @param stockSeguridad Stock de seguridad del articulo a añadir 
   * @param stockMaximo Stock maximo del articulo a añadir 
   * @throws UnidadesArticuloEsMenorQueCeroException Las unidades del articulo son menor que cero
   */
  public Articulo (String descripcion,double precioCompra,double precioVenta, int cantidadUnidades, int stockSeguridad, int stockMaximo) throws UnidadesArticuloEsMenorQueCeroException {

    this.descripcion = descripcion;
    this.precioCompra = precioCompra;
    this.precioVenta = precioVenta;
    this.cantidadUnidades = cantidadUnidades;
    this.stockseguridad = stockSeguridad;
    this.stockmaximo = stockMaximo;
    this.codigo = codigoAnterior++;
    if (this.cantidadUnidades < 0) {
      throw new UnidadesArticuloEsMenorQueCeroException("Un articulo no puede tener 0 unidades");

    }

  }
  
  /**
   * 
   * @param codigo codigo de un articulo
   */

  public Articulo(int codigo) {
    this.codigo = codigo;

  }
  
  /**
   * 
   * @return descripcion del articulo (String)
   */

  public String getDescripcion() {
    return descripcion;
  }
  
  /**
   * 
   * @param descripcion 
   */
  
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  /**
   * 
   * @return precio de compra (Double)
   */
  public double getPreciocompra() {
    return precioCompra;
  }
  
  /**
   * Asigna el precio de compra
   * @param preciocompra Precio de compra del articulo a añadir
   */
  public void setPreciocompra(double preciocompra) {
    if (preciocompra < 0) {
      throw new PrecioEsMenorQueCeroException("No puede haber un precio negativo");
    }
    this.precioCompra = preciocompra;
  }
  
  /**
   * 
   * @return precioVenta Precio de venta (Double)
   */
  public double getPrecioventa() {
    return precioVenta;
  }
  
  /**
   * Asigna el precio de venta
   * @param precioventa Precio de venta del articulo a añadir
   */
  public void setPrecioventa(double precioventa) {
    if (precioventa < 0) {
      throw new PrecioEsMenorQueCeroException("No puede haber un precio negativo");
    }
    this.precioVenta = precioventa;
  }
  
  /**
   * 
   * @return candidad de unidades (Int)
   */
  public int getCantidadunidades() {
    return cantidadUnidades;
  }
  
  /**
   * Asigna la cantidad de unidades
   * @param cantidadunidades cantidad de unidades de un articulo
   */
  public void setCantidadunidades(int cantidadunidades) {
    if (cantidadunidades < 0) {
      throw new CantidadEsMenorQueCeroException("No puedes pasar unidades negativas para incrementar las existencias de un articulo");
    }
    this.cantidadUnidades = cantidadunidades;
  }
  
  /**
   * 
   * @return stock seguridad (Int)
   */
  public int getStockseguridad() {
    return stockseguridad;
  }
  
  /**
   * Asigna el stock de seguridad
   * @param stockseguridad  stock de seguridad de un articulo
   */
  public void setStockseguridad(int stockseguridad) {
    this.stockseguridad = stockseguridad;
  }
  
  /**
   * 
   * @return stock maximo (Int)
   */
  public int getStockmaximo() {
    return stockmaximo;
  }
  
  /**
   * Asinga el stock maximo
   * @param stockmaximo Stock Maximo del articulo
   */
  public void setStockmaximo(int stockmaximo) {
    this.stockmaximo = stockmaximo;
  }
  
  /**
   * 
   * @return devuelve el codigo (Int)
   */

  public int getCodigo() {
    return codigo;
  }

  @Override
  public String toString() {
    return "Articulo [codigo=" + codigo + ", descripcion=" + descripcion + ", preciocompra="
        + precioCompra + ", precioventa=" + precioVenta + ", cantidadunidades=" + cantidadUnidades
        + ", stockseguridad=" + stockseguridad + ", stockmaximo=" + stockmaximo + "]";
  }
  
  /**
   * Incrementa las unidades de un arituclo
   * @param unidades Unidades que pasa el usuario para incrementar la unidades de un articulo
   */

  public void incrementar(int unidades) {

    if (unidades > 0) {
      setCantidadunidades(this.cantidadUnidades + unidades);
    }

    else {

      throw new CantidadEsMenorQueCeroException("No puedes pasar unidades negativas para incrementar las existencias de un articulo");

    }
  }
  
  /**
   * 
   * @param unidades Unidades que pasa el usuario para decrementar las unidades de un articulo
   * @throws UnidadesArticuloEsMenorQueCeroException Si las unidades a restar del articulo son menor que 0 
   * @throws CantidadUnidadesEsMenorQueCeroException Si las unidades restantes al decrementar las unidades de un articulo son menor que 0
   */

  public void decrementar(int unidades) throws UnidadesArticuloEsMenorQueCeroException, CantidadUnidadesEsMenorQueCeroException {

    if (this.cantidadUnidades < 0) {
      throw new UnidadesArticuloEsMenorQueCeroException("Las unidades de este articulo son 0");

    }
    if(this.cantidadUnidades-unidades < 0) {
      throw new CantidadUnidadesEsMenorQueCeroException("Unidades totales de este articulos son menores que 0");
    }
    setCantidadunidades(this.cantidadUnidades-unidades);

  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + codigo;
    return result;
  }
  
  /**
   * Sirve para comparar el codigo de objetos articulos
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Articulo other = (Articulo) obj;
    if (codigo != other.codigo)
      return false;
    return true;
  }


}
