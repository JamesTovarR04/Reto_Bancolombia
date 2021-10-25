package retos.evalart.services;

import retos.evalart.model.Client;
import retos.evalart.utils.FileConfig;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ClientService {

    public static String decryptCode(String code) throws Exception {
        StringBuilder result = new StringBuilder();

        URL url = new URL(FileConfig.getPropertie("client.urldecrypt","") + "/" + code);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;

        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        return result.toString().replace("\"","");
    }

    public static List<Client> filterClientsByGender(List<Client> clientList, int maxClients, int minClients) {
        int numMale = 0;
        int numFemale = 0;
        List<Client> replaceClients = new ArrayList<>();
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).isMale()) {
                if (numFemale > numMale && i >= maxClients) {
                    replaceClients.add(clientList.get(i));
                    if(numMale + replaceClients.size() >= numFemale - replaceClients.size()) {
                        break;
                    }
                }
                if (i < maxClients) {
                    numMale++;
                }
            } else {
                if (numFemale < numMale && i >= maxClients) {
                    replaceClients.add(clientList.get(i));
                    if(numFemale + replaceClients.size() >= numMale - replaceClients.size()) {
                        break;
                    }
                }
                if (i < 8) {
                    numFemale++;
                }
            }
        }

        List<Client> finishClients = (clientList.size() > maxClients) ? clientList.subList(0,maxClients) : clientList;

        for (int i = finishClients.size() - 1; i >= 0; i--) {
            Client client = finishClients.get(i);
            if (!client.isMale() && numFemale > numMale) {
                finishClients.remove(i);
                numFemale--;
                if (replaceClients.size() > 0) {
                    finishClients.add(replaceClients.remove(0));
                    numMale++;
                }
            } else if (client.isMale() && numFemale < numMale) {
                finishClients.remove(i);
                numMale--;
                if (replaceClients.size() > 0) {
                    finishClients.add(replaceClients.remove(0));
                    numFemale++;
                }
            }
        }

        if (finishClients.size() >= minClients) {
            return finishClients;
        }
        return null;
    }

}
