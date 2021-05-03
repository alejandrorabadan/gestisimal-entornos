package gestisimal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.*;
/**
 * Crea un objeto gson
 * @author alejandro
 *
 */
public class AlmacenJson {
  
  /**
   * 
   * 
   * @param almacen Arraylist almacen
   * @param fileName Nombre del archivo
   * @throws IOException
   */
  static void save (ArrayList<Articulo> almacen, String fileName) throws IOException{
    Gson gson = new Gson();
    String json = gson.toJson(almacen);
    Files.writeString(Paths.get(fileName), json);
  }
  
    
  }
