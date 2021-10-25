package retos.evalart.dao;

import retos.evalart.model.Client;
import retos.evalart.model.Table;
import retos.evalart.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public List<Client> getForTable(Table table) {
        List<Client> listClients = new ArrayList<>();

        String whereSQL = "";
        boolean hasType = table.getTypeClient() != 0;
        boolean hasLocation = table.getCodLocation() != null;

        if (hasType || hasLocation){
            whereSQL += "WHERE ";
            if (hasType) whereSQL += "type = ? ";
            if (hasType && hasLocation) whereSQL += " AND ";
            if (hasLocation) whereSQL += "location = ? ";
        }

        String havingSQL = "";
        boolean hasRI = table.getRangoInicialBalance() != 0.0d;
        boolean hasRF = table.getRangoFinalBalance() != 0.0d;

        if (hasRI || hasRF){
            havingSQL += "HAVING ";
            if (hasRI) havingSQL += "monto >= ? ";
            if (hasRI && hasRF) havingSQL += " AND ";
            if (hasRF) havingSQL += "monto <= ? ";
        }

        String query = "SELECT id, code, male, encrypt " +
                "FROM (SELECT cl.id, code, male, encrypt,company, SUM(ac.balance) monto FROM client cl " +
                "LEFT JOIN account ac ON ac.client_id = cl.id " +
                whereSQL + "GROUP BY cl.id " + havingSQL +
                "ORDER BY monto DESC) tp " +
                "GROUP BY company " +
                "ORDER BY monto DESC, code;";

        try {
            Connection connection = ConnectionDB.getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            int parameterIndex = 1;
            if (hasType) {
                ps.setInt(parameterIndex,table.getTypeClient());
                parameterIndex++;
            }
            if (hasLocation) {
                ps.setString(parameterIndex, table.getCodLocation());
                parameterIndex++;
            }
            if (hasRI) {
                ps.setDouble(parameterIndex, table.getRangoInicialBalance());
                parameterIndex++;
            }
            if (hasRF) {
                ps.setDouble(parameterIndex, table.getRangoFinalBalance());
                parameterIndex++;
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setCode(rs.getString("code"));
                client.setMale(rs.getBoolean("male"));
                client.setEncrypt(rs.getBoolean("encrypt"));
                listClients.add(client);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listClients;
    }

}
