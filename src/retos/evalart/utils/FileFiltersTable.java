package retos.evalart.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Clase para manejo del archivo de filtros
 * <br/>
 * <p>Formato de filtros del archivo:
 * <br/><br/>
 * <p> &lt;NombreMesa&gt;
 * <p> TC:valor
 * <p> UG:valor
 * <p> RI:valor
 * <p> RF:valor
 * <br/><br/>
 * <p>Nota: Cada uno de los filtros son opcionales
 * <br/><br/>
 * <p>Valores de los filtros:
 * <ul>
 *      <li>TC: Tipo de cliente - numérico entero</li>
 *      <li>UG: Código de ubicación geográfica - texto</li>
 *      <li>RI: Rango inicial de balance - numérico decimal</li>
 *      <li>RF: Rango Final de balance - numérico decimal</li>
 * </ul>
 *
 * @autor James Tovar Rodriguez
 */
public class FileFiltersTable {

    public final String regexpNameTable = "<[\\w ]+>";

    // Filtros de mesas y sus validaciones de valor
    private HashMap<String,String> filters;
    {
        filters = new HashMap<>();
        filters.put("TC","\\d+");
        filters.put("UG","\\w+");
        filters.put("RI","[+-]?(\\d+([\\.\\,]\\d+)?)");
        filters.put("RF","[+-]?(\\d+([\\.\\,]\\d+)?)");
    }

    private List<String> listTables;
    private HashMap<String, HashMap<String,String>> tables;
    private FileReader file;

    /**
     * Crea un nuevo objeto de la clase <code>FileFiltersTable</code>
     * usando el archivo "input.txt" por defecto.
     *
     * @exception  IOException Si ocurre un error de E/S
     * @exception  FileNotFoundException Si no existe el archivo
     */
    public FileFiltersTable() throws FileNotFoundException, IOException {
        new FileFiltersTable("input.txt");
    }

    /**
     * Crea un nuevo objeto de la clase <code>FileFiltersTable</code>
     * usando el archivo que se envía por parametro.
     *
     * @param pathFile Nombre o ruta del archivo
     *
     * @exception  IOException Si ocurre un error de E/S
     * @exception  FileNotFoundException Si no existe el archivo
     */
    public FileFiltersTable(String pathFile) throws FileNotFoundException, IOException {
        file = new FileReader(pathFile);
        processFile();
    }

    /**
     * Procesa el archivo de filtros de entrada
     * recorre cada linea del archivo y segun sea el caso agrega mesas como
     * clave y filtros como valor en el diccionario <code>tables</code>.
     *
     * @exception  IOException Si ocurre un error de E/S
     */
    private void processFile() throws IOException {
        String line;
        String tableName = null;
        tables = new HashMap<>();
        listTables = new ArrayList<>();
        BufferedReader bufferFile = new BufferedReader(file);
        while((line = bufferFile.readLine())!=null) {
            if (Pattern.matches(regexpNameTable, line)) {
                tableName = line.substring(1,line.length() - 1);
                listTables.add(tableName);
                tables.put(tableName, new HashMap<String,String>());
            } else {
                for (Map.Entry<String, String> f : filters.entrySet()) {
                    if (Pattern.matches(f.getKey() + ":" + f.getValue(), line) && tableName != null) {
                        tables.get(tableName).put(f.getKey(), line.substring(line.indexOf(':') + 1));
                        break;
                    }
                }
            }
        }
        bufferFile.close();
    }

    /**
     * @return Archivo de filtros
     */
    public FileReader getFile(){
        return file;
    }

    /**
     * @return Diccionario de mesas con sus filtros
     */
    public List<String> getTables(){
        return listTables;
    }

    /**
     * Retorna los filtros de la mesa que se envía por parametro.
     *
     * @param name nombre de la mesa
     * @return diccionario con filtros de la mesa
     */
    public HashMap<String,String> getFiltersByTable(String name){
        return tables.get(name);
    }

}
