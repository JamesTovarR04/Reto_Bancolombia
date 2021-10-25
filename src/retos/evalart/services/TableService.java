package retos.evalart.services;

import retos.evalart.dao.ClientDAO;
import retos.evalart.dao.ClientDAOImpl;
import retos.evalart.model.Client;
import retos.evalart.model.Table;

import java.util.List;

public abstract class TableService {

    public static final int MAX_CHAIRS = 8;
    public static final int MIN_CHAIRS = 4;

    public static List<Client> getClients(Table table) {
        ClientDAO clientDAO = new ClientDAOImpl();
        List<Client> clients = clientDAO.getForTable(table);
        if (clients.size() >= MIN_CHAIRS) {
            return ClientService.filterClientsByGender(clients, MAX_CHAIRS, MIN_CHAIRS);
        }
        return null;
    }

}
