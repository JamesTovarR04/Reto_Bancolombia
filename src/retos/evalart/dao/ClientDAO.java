package retos.evalart.dao;

import retos.evalart.model.Client;
import retos.evalart.model.Table;

import java.util.List;

public interface ClientDAO {

    public List<Client> getForTable(Table filters);

}
