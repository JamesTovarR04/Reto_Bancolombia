package retos.evalart.views;

import retos.evalart.model.Client;
import retos.evalart.model.Table;
import retos.evalart.services.ClientService;

import java.util.List;

public class ViewTable {

    public ViewTable(Table table) {
        System.out.println("<" + table.getName() + ">");
    }

    public void printClients(List<Client> clientsList) {
        String result = "";
        if (clientsList != null) {
            String code = "";
            for (int i = 0; i < clientsList.size(); i++) {
                code = clientsList.get(i).getCode();
                if (clientsList.get(i).isEncrypt()) {
                    try {
                        result += ClientService.decryptCode(code);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    result += code;
                }

                if (i < clientsList.size() - 1) {
                    result += ",";
                }
            }
        } else {
            result += "CANCELADA";
        }
        System.out.println(result);
    }

}
