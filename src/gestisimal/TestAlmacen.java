package gestisimal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Crea un menu de opciones:
 * <ol>
 * <li> 1.Añadir articulo</li>
 * <li> 2.Eliminar articulo </li>
 * <li> 3.Decrementar articulo </li>
 * <li> 4.Incrementar articulo </li>
 * <li> 5.Devolver articulo </li>
 * <li> 6.Exportar almacen como fichero CSV<li>
 * <li> 7.Importar almacen como fichero CSV<li>
 * <li> 8.Exportar almacen como fichero XML<li>
 * <li> 9.Importar almacen como fichero XML<li>
 * <li> 10.Exportar almacen como fichero JSON<li>
 * <li> 11.Finalizar
 * 
 * @author alejandro
 *
 */

public class TestAlmacen {
  
  /**
   * Crea el almacen
   */
  private static Almacen almacen = new Almacen();
  
  /**
   * 
   * @param args
   * @throws UnidadesArticuloEsMenorQueCeroException
   * @throws IOException
   * @throws AlmacenCSVException
   */
  public static void main (String [] args) throws UnidadesArticuloEsMenorQueCeroException, IOException, AlmacenCSVException   {

    String descripcion;
    double precioCompra;
    double precioVenta;
    int cantidadUnidades;
    int stockSeguridad;
    int stockMaximo;
    int codigo;
    int unidades;
    String fileName;

    Scanner s = new Scanner (System.in);
      
    int opcion;

    Menu menu = new Menu("Menú de opciones", 
        "Añadir articulo", "Eliminar Articulo", "Decrementar Articulo", "Incrementar Articulo", "Devolver Articulo","Exportar almacen como fichero CSV","Importar almacen como fichero CSV","Exportar almacen como fichero XML","Importar almacen como fichero XML","Exportar fichero Json","Finalizar");

    do {
      opcion = menu.elegir();

      switch (opcion) {
        case 1:
          System.out.println("Añadir articulos:");
          System.out.println("Introduce la descripción del articulo: ");
          descripcion = s.next();
          System.out.println("Introduce el precio de compra del articulo: ");
          precioCompra = s.nextDouble();
          System.out.println("Introduce el precio de venta del articulo: ");
          precioVenta = s.nextDouble();
          System.out.println("Introduce la cantidad de unidades del articulo: ");
          cantidadUnidades = s.nextInt();
          System.out.println("Introduce el stock de seguridad del articulo: ");
          stockSeguridad = s.nextInt();
          System.out.println("Introduce el stock maximo del articulo");
          stockMaximo = s.nextInt();
          almacen.annadir(descripcion, precioCompra, precioVenta, cantidadUnidades, stockSeguridad, stockMaximo);
          break;
        case 2: 
          System.out.println("Introduce el codigo para eliminar el articulo: ");
          codigo = s.nextInt();
          almacen.eliminar(codigo);
          System.out.println(almacen);
          break;
        case 3:
          System.out.println("Introduce la cantidad de existencias a decrementar: ");
          unidades = s.nextInt();
          System.out.println("Introduce el codigo del articulo para decrementar las existencias: ");
          codigo =s.nextInt();
          try {
            almacen.decrementarExistencias(unidades,codigo);
          } catch (UnidadesArticuloEsMenorQueCeroException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          System.out.println(almacen);
          break;
        case 4:
          System.out.println("Introduce la cantidad de existencias a incrementar: ");
          unidades = s.nextInt();
          System.out.println("Introduce el codigo del articulo para incrementar las existencias: ");
          codigo =s.nextInt();
          try {
            almacen.incrementarExistencias(unidades, codigo);
          } catch (CantidadEsMenorQueCeroException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          System.out.println(almacen);
          break;
        case 5:
          System.out.println("Introduce el codigo del articulo para verlo: ");
          codigo =s.nextInt();
          almacen.mostrarArticulo(codigo);
          System.out.print(almacen.mostrarArticulo(codigo));
          break;
        case 6:
          System.out.println("Introduce el nombre del fichero CSV a exportar:");
          fileName = s.next();
          try {
            almacen.saveCSV(fileName);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          break;
        case 7:
          System.out.println("Introduce el nombre del fichero CSV a importar:");
          fileName = s.next();
          almacen.loadCSV(fileName);
          System.out.println(almacen);
          break;
        case 8:
          System.out.println("Introduce el nombre del fichero XML a exportar: ");
          fileName = s.next();
          try {
            almacen.saveXML(fileName);
          } catch (AlmacenXMLException | IOException e1) {
            e1.printStackTrace();
          }
          break;
        case 9:
          System.out.println("Introduce el nombre del fichero XML a importar: ");
          fileName = s.next();
          try {
            almacen.loadXML(fileName);
          } catch (IOException | AlmacenXMLException | UnidadesArticuloEsMenorQueCeroException e) {
            e.printStackTrace();
          }
          break;
        case 10:
          System.out.println("Introduce el nombre del fichero Json a exportar: ");
          fileName = s.next();
          almacen.saveJson(fileName);
          System.out.println("Exportado con éxito");
        case 15:
          System.out.println("Terminamos");
      }
    } while (opcion < 15);
  }


}








