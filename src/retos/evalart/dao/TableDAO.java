package retos.evalart.dao;

import retos.evalart.model.Table;

import java.util.List;

public interface TableDAO {

    public Table getByName(String name);

    public List<Table> getAll();

}
