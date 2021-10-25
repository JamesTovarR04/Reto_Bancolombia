package retos.evalart;

import retos.evalart.dao.TableDAO;
import retos.evalart.dao.TableDAOImpl;
import retos.evalart.model.Client;
import retos.evalart.model.Table;
import retos.evalart.services.TableService;
import retos.evalart.utils.FileConfig;
import retos.evalart.views.ViewTable;

import java.util.List;

public class CenaClientes {

    public static void main(String[] args) {
        String filePath;
        if (args.length > 1) {
            filePath = args[0];
        } else {
            filePath = FileConfig.getPropertie("tables.path", "entrada.txt");
        }

        TableDAO tableDAO = new TableDAOImpl(filePath);
        List<Table> listTables = tableDAO.getAll();

        for (Table table:listTables) {
            List<Client> clients = TableService.getClients(table);
            ViewTable viewTable = new ViewTable(table);
            viewTable.printClients(clients);
        }
    }

}