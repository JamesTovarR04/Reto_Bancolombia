package retos.evalart.model;

/**
 * Clase modelo para las mesas
 * @autor James Tovar Rodriguez
 */
public class Table {

    private String name;
    private int typeClient;
    private String codLocation;
    private double rangoInicialBalance;
    private double rangoFinalBalance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(int typeClient) {
        this.typeClient = typeClient;
    }

    public String getCodLocation() {
        return codLocation;
    }

    public void setCodLocation(String codLocation) {
        this.codLocation = codLocation;
    }

    public double getRangoInicialBalance() {
        return rangoInicialBalance;
    }

    public void setRangoInicialBalance(double rangoInicialBalance) {
        this.rangoInicialBalance = rangoInicialBalance;
    }

    public double getRangoFinalBalance() {
        return rangoFinalBalance;
    }

    public void setRangoFinalBalance(double rangoFinalBalance) {
        this.rangoFinalBalance = rangoFinalBalance;
    }

}
