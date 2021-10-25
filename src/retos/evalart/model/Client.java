package retos.evalart.model;

/**
 * Clase modelo para los clientes
 * @autor James Tovar Rodriguez
 */
public class Client {

    private int id;
    private String code;
    private boolean male;
    private int type;
    private String location;
    private String company;
    private boolean encrypt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

}
