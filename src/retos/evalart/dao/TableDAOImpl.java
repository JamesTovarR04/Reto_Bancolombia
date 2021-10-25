package retos.evalart.dao;

import retos.evalart.model.Table;
import retos.evalart.utils.FileConfig;
import retos.evalart.utils.FileFiltersTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableDAOImpl implements TableDAO {

    private FileFiltersTable fileTables;

    public TableDAOImpl(String file) {
        try {
            fileTables = new FileFiltersTable(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Table getByName(String name) {
        HashMap<String,String> tableFilters = fileTables.getFiltersByTable(name);
        Table table = new Table();
        table.setName(name);
        table = setValuesTable(table, tableFilters);
        return table;
    }

    @Override
    public List<Table> getAll() {
        List<Table> listTables = new ArrayList<>();
        List<String> tables = fileTables.getTables();
        for (String table:tables) {
            listTables.add(getByName(table));
        }
        return listTables;
    }

    private Table setValuesTable(Table table, HashMap<String,String> tableMap) {
        String tc = tableMap.get("TC");
        String ug = tableMap.get("UG");
        String ri = tableMap.get("RI");
        String rf = tableMap.get("RF");
        if (tc != null)
            table.setTypeClient(Integer.parseInt(tc));
        table.setCodLocation(ug);
        if (ri != null)
        table.setRangoInicialBalance(Double.parseDouble(ri));
        if (rf != null)
            table.setRangoFinalBalance(Double.parseDouble(rf));
        return table;
    }

}
