package gestisimal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**Un almacen contiene articulos, no pueden existir un almacen
 * con dos articulos con el mismo codigo.
 * No podrá añadirse un articulo invalido
 * 
 * @author Alejandro Rabadan Rivas
 */
public class Almacen{
  /**
   * Almacen crear articulo XML (String)
   */
  private final static String ALMACEN = "almacen";
  /**
   * Articulo crear articulo XML (String)
   */
  private final static String ARTICULO = "articulo";
  /**
   * Codigo crear articulo XML (String)
   */
  private final static String CODIGO = "codigo";
  /**
   * Descripcion crear articulo XML (String)
   */
  private final static String DESCRIPCION = "descripcion";
  /**
   * Precio de compra crear articulo XML (String)
   */
  private final static String PRECIO_COMPRA = "precioCompra";
  /**
   * Precio de venta crear articulo XML (String)
   */
  private final static String PRECIO_VENTA = "precioVenta";
  /**
   * Cantidad de unidades crear articulo XML (String)
   */
  private final static String CANTIDAD_UNIDADES = "cantidadUnidades";
  /**
   * Stock de seguridad crear articulo XML (String)
   */
  private final static String STOCK_SEGURIDAD = "stockSeguridad";
  /**
   * Stock maximo crear articulo XML (String)
   */
  private final static String STOCK_MAXIMO = "stockMaximo";
  /**
   * CSV HEAD crear articulo XML (String)
   */
  private static final String CSV_HEAD = "descripcion,precioCompra,precioVenta,cantidadUnidades,stockSeguridad,stockMaximo";
  /**
   * Almacen de los arituclos
   */
  static ArrayList <Articulo> almacen = new ArrayList <Articulo>();


  /**
   * 
   * @param descripcion Descripción del articulo a añadir  
   * @param preciocompra Precio de compra del articulo a añadir 
   * @param precioventa Precio de venta del articulo a añadir 
   * @param cantidadunidades Cantidad de unidades del articulo a añadir 
   * @param stockseguridad Stock de seguridad del articulo a añadir 
   * @param stockmaximo Stock Maximo del articulo a añadir 
   * @throws UnidadesArticuloEsMenorQueCeroException Si la cantidad de unidades de un articulo
   * es menor que cero, no se puede guardar en el almacen
   * @throws ArticuloYaExisteException Si el articulo ya existe no se puede guardar
   */
  public void annadir (String descripcion,double preciocompra,double precioventa, int cantidadunidades, int stockseguridad, int stockmaximo) throws UnidadesArticuloEsMenorQueCeroException {
    /**
     * Este metodo añade el articulo al almacen, si el articulo no existe
     */

    Articulo articulo = new Articulo(descripcion,preciocompra,precioventa,cantidadunidades,stockseguridad,stockmaximo);

    if (!almacen.contains(articulo)) {
      almacen.add(articulo);
      System.out.println(almacen);
    }
    else {
      throw new ArticuloYaExisteException("El articulo ya existe");
    }
  }
  
  /**
   * 
   * @param codigo
   * @return true si ha eliminado el articulo del codigo pasado
   */

  public boolean eliminar (int codigo) {

    return almacen.remove(new Articulo(codigo));

  }
  
  /**
   * 
   * @param unidades
   * @param codigo
   * @throws CantidadEsMenorQueCeroException Si la cantidad de unidades pasadas para incrementar son menor que cero no se puede incrementar
   */

  public void incrementarExistencias (int unidades, int codigo) throws CantidadEsMenorQueCeroException {
    almacen.get(almacen.indexOf(seleccionarArticulo(codigo))).incrementar(unidades);
  }
  
  /**
   * 
   * @param unidades
   * @param codigo
   * @throws UnidadesArticuloEsMenorQueCeroException Si el articulo al decrementar se queda con unidades negativas 
   */

  public void decrementarExistencias(int unidades, int codigo) throws UnidadesArticuloEsMenorQueCeroException{
    try {
      almacen.get(almacen.indexOf(seleccionarArticulo(codigo))).decrementar(unidades);
    } catch (UnidadesArticuloEsMenorQueCeroException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CantidadUnidadesEsMenorQueCeroException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  /**
   * 
   * @param fileName Nombre del archivo
   * @throws IOException
   */
  public void saveJson(String fileName) throws IOException{
    AlmacenJson.save(almacen,fileName);
  }

  /**
   * 
   * @param fileName Nombre del archivo
   * @throws IOException
   */
  public void saveCSV(String fileName) throws IOException {
    var file = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.CREATE);
    saveHeadCSV(file);
    for (Articulo articulo: almacen) { //almacen cambiar por this
      saveArticuloCSV(articulo, file);
    }
    file.close();
  }

  /**
   * 
   * @param articulo 
   * @param file
   * @throws IOException
   */
  private void saveArticuloCSV(Articulo articulo, BufferedWriter file) throws IOException {
    file.write("\"" + articulo.getDescripcion() + "\",");
    file.write("\"" + articulo.getPreciocompra() + "\",");
    file.write("\"" + articulo.getPrecioventa()+ "\",");
    file.write("\"" + articulo.getCantidadunidades() + "\",");
    file.write("\"" + articulo.getStockmaximo() + "\",");
    file.write("\"" + articulo.getStockseguridad() + "\","); 
    file.newLine();

  }
  
  /**
   * 
   * @param file Nombre del archivo
   * @throws IOException
   */

  private void saveHeadCSV(BufferedWriter file) throws IOException {
    file.write(CSV_HEAD);
    file.newLine();

  }

  /**
   * 
   * @param fileName
   * @throws IOException
   * @throws AlmacenCSVException
   * @throws UnidadesArticuloEsMenorQueCeroException
   */
  public void loadCSV(String fileName) throws IOException, AlmacenCSVException, UnidadesArticuloEsMenorQueCeroException {
    var file = Files.newBufferedReader(Paths.get(fileName));
    validateHeadCSV(file);

    String line;
    while ((line = file.readLine()) != null) {
      Articulo articulo = newArticuloCSV(line);
      this.annadir(articulo);
    }
    file.close();
  }

  /**
   * 
   * @param articulo
   */

  private void annadir(Articulo articulo) {
    if (!almacen.contains(articulo)) {
      almacen.add(articulo);
    }
    else {
      throw new ArticuloYaExisteException("El articulo ya existe");
    }


  }
  
  /**
   * 
   * @param file
   * @throws AlmacenCSVException
   * @throws IOException
   */

  private static void validateHeadCSV(BufferedReader file) throws AlmacenCSVException, IOException {
    String head = file.readLine().trim();
    if (! head.equalsIgnoreCase(CSV_HEAD)) {
      throw new AlmacenCSVException("Cabecera errónea en el CSV.");
    }

  }
  
  /**
   * 
   * @param line
   * @return devuelve un articulo
   * @throws AlmacenCSVException
   * @throws UnidadesArticuloEsMenorQueCeroException
   */

  private static Articulo newArticuloCSV(String line) throws AlmacenCSVException, UnidadesArticuloEsMenorQueCeroException {
    validateArticuloCSV(line);
    String[] fieldsArticulo = line.split("\",");


    String descripcion = fieldsArticulo[0].replace("\"", "");
    String precioCompra = fieldsArticulo[1].replace("\"", "");
    String precioVenta = fieldsArticulo[2].replace("\"", "");
    String cantidadUnidades = fieldsArticulo[3].replace("\"", "");
    String stockSeguridad = fieldsArticulo[4].replace("\"", "");
    String stockMaximo = fieldsArticulo[5].replace("\"", "");
    Articulo articulo = null;


    articulo = newArticulo(descripcion, precioCompra, precioVenta, cantidadUnidades, stockSeguridad, stockMaximo);



    return articulo;
  }
  
  /**
   * 
   * @param line
   * @throws AlmacenCSVException
   */

  private static void validateArticuloCSV(String line) throws AlmacenCSVException {

    int numFieldsContact = CSV_HEAD.split(",").length;
    int numFieldsLine = line.split("\",").length;  


    if (numFieldsLine != numFieldsContact) {
      throw new AlmacenCSVException(line + ": no es un formato válido para convertirlo en Articulo.");
    }

  }

  /**
   * 
   * @param fileName
   * @throws AlmacenXMLException
   * @throws IOException
   */

  public void saveXML(String fileName) throws AlmacenXMLException, IOException {
    try {   
      Document xml = createDocumentXML();
      saveRootXML(xml);
      for (Articulo articulo : almacen) {
        saveArticuloXML(articulo, xml);
      }
      saveFileXML(xml, fileName);

    } catch (ParserConfigurationException | TransformerException e) {
      throw new AlmacenXMLException("Error al generar XML: " + e.getMessage());
    }
  }
  
  /**
   * 
   * @return devuelve un documento xml
   * @throws ParserConfigurationException
   */

  private Document createDocumentXML() throws ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.newDocument();
    return document;
  }
  
  /**
   * 
   * @param documento xml
   */

  private void saveRootXML(Document xml) {
    Element root = xml.createElement(ALMACEN);
    xml.appendChild(root);
  }
  
  /**
   * 
   * @param articulo
   * @param xml
   */

  private void saveArticuloXML(Articulo articulo, Document xml) {

    Element root = xml.getDocumentElement();

    Element articuloElement = xml.createElement(ARTICULO);
    root.appendChild(articuloElement);

    saveFieldArticuloXML(DESCRIPCION, articulo.getDescripcion(), articuloElement);
    saveFieldArticuloXML(PRECIO_COMPRA, articulo.getPreciocompra(), articuloElement);
    saveFieldArticuloXML(PRECIO_VENTA, articulo.getPrecioventa(), articuloElement);
    saveFieldArticuloXML(CANTIDAD_UNIDADES, articulo.getCantidadunidades(), articuloElement);
    saveFieldArticuloXML(STOCK_SEGURIDAD, articulo.getStockseguridad(), articuloElement);
    saveFieldArticuloXML(STOCK_MAXIMO, articulo.getStockmaximo(), articuloElement);

  }
  
  /**
   * 
   * @param attr
   * @param value
   * @param articuloElement
   */

  private void saveFieldArticuloXML(String attr, int value,Element articuloElement) {
    String valorCadena = Integer.toString(value);

    Document xml = articuloElement.getOwnerDocument();
    Element campoElement = xml.createElement(attr);
    campoElement.appendChild(xml.createTextNode(valorCadena));
    articuloElement.appendChild(campoElement);
  }
  
  /**
   * 
   * @param attr
   * @param value
   * @param articuloElement
   */

  private void saveFieldArticuloXML(String attr, double value,Element articuloElement) {
    String valorCadena = Double.toString(value);

    Document xml = articuloElement.getOwnerDocument();
    Element campoElement = xml.createElement(attr);
    campoElement.appendChild(xml.createTextNode(valorCadena));
    articuloElement.appendChild(campoElement);
  }
  
  /**
   * 
   * @param attr
   * @param value
   * @param articuloElement
   */

  private void saveFieldArticuloXML(String attr, String value, Element articuloElement) {
    Document xml = articuloElement.getOwnerDocument();
    Element campoElement = xml.createElement(attr);
    campoElement.appendChild(xml.createTextNode(value));
    articuloElement.appendChild(campoElement);
  }

  private void saveFileXML(Document xml, String fileName) throws IOException, TransformerException {
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    DOMSource xmlSource = new DOMSource(xml);
    StreamResult output = new StreamResult(new FileWriter(fileName));
    transformer.transform(xmlSource, output);
  }

  /**
   * Recupera la agenda de un fichero XML y la devuelve como Almacen.
   * @throws IOException 
   * @throws UnidadesArticuloEsMenorQueCeroException 
   * @throws AddressBookXMLException 
   * @throws AddressBookCSVException 
   */

  public Almacen loadXML(String fileName) throws IOException, AlmacenXMLException, UnidadesArticuloEsMenorQueCeroException {
    var almacen = new Almacen(); 
    try {
      Document xml = newDocumentXML(fileName);
      NodeList articulos = xml.getElementsByTagName(ARTICULO);
      for (int i = 0; i < articulos.getLength(); i++) {
        Articulo articulo = newArticuloXML(articulos.item(i));
        almacen.annadir(articulo);
      }
    } catch (ParserConfigurationException | SAXException | ArticuloErrorException e) {
      throw new AlmacenXMLException("Error al cargar XML: " + e.getMessage());
    }

    return almacen;
  }
  
  /**
   * 
   * @param fileName
   * @return un nuevo documento xml
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */

  private static Document newDocumentXML(String fileName) throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(new File(fileName));
    document.getDocumentElement().normalize();
    return document;
  }
  
  /**
   * 
   * @param itemArticulo
   * @return devuelve un nuevo articulo
   * @throws UnidadesArticuloEsMenorQueCeroException
   */


  private static Articulo newArticuloXML(Node itemArticulo) throws UnidadesArticuloEsMenorQueCeroException {
    String descripcion = loadFieldArticuloXML(DESCRIPCION, itemArticulo);
    String precioCompra = loadFieldArticuloXML(PRECIO_COMPRA, itemArticulo);
    String precioVenta = loadFieldArticuloXML(PRECIO_VENTA, itemArticulo);
    String cantidadUnidades = loadFieldArticuloXML(CANTIDAD_UNIDADES, itemArticulo);
    String stockSeguridad = loadFieldArticuloXML(STOCK_SEGURIDAD, itemArticulo);
    String stockMaximo = loadFieldArticuloXML(STOCK_MAXIMO, itemArticulo);
    return newArticulo(descripcion, precioCompra, precioVenta, cantidadUnidades, stockSeguridad, stockMaximo);
  }
  
  /**
   * 
   * @param field
   * @param itemArticulo
   * @return devuelve un campo
   */

  private static String loadFieldArticuloXML(String field, Node itemArticulo) {
    Element elementoArticulo = (Element) itemArticulo;
    String textField = elementoArticulo.getElementsByTagName(field).item(0).getTextContent();
    return textField;
  }
  
  /**
   * 
   * @param codigo
   * @return el indice del articulo que contiene el codigo
   */

  public Articulo seleccionarArticulo(int codigo) {
    return almacen.get(almacen.indexOf(new Articulo (codigo)));

  }
  
  /**
   * 
   * @param descripcion
   * @param precioCompra
   * @param precioVenta
   * @param cantidadUnidades
   * @param stockSeguridad
   * @param stockMaximo
   * @return devuelve un nuevo articulo
   * @throws UnidadesArticuloEsMenorQueCeroException Si las unidades del articulo es menor que cero
   */

  private static Articulo newArticulo(String descripcion, String precioCompra,
      String precioVenta, String cantidadUnidades, String stockSeguridad, String stockMaximo) throws UnidadesArticuloEsMenorQueCeroException {

    double precioCompra2 = Double.parseDouble(precioCompra);
    double precioVenta2 = Double.parseDouble(precioVenta);
    int cantidadUnidades2 = Integer.parseInt(cantidadUnidades);
    int stockSeguridad2 = Integer.parseInt(stockSeguridad);
    int stockMaximo2 = Integer.parseInt(stockMaximo);



    Articulo articulo;

    articulo = new Articulo(descripcion, precioCompra2, precioVenta2, cantidadUnidades2, stockSeguridad2, stockMaximo2);

    return articulo;
  }
  

  @Override
  public String toString() {
    return "Almacen [almacen=" + almacen + "] ";
  }
  
  /**
   * 
   * @param codigo
   * @return devuelve el indice del articulo que se corresponde con el codigo que se encuentra en el almacen
   */

  public String mostrarArticulo(int codigo) {
    return almacen.get(almacen.indexOf(seleccionarArticulo(codigo))).toString();

  }

}
