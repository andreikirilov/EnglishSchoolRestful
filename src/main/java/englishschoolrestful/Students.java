package englishschoolrestful;

public class Students {

    private String ID;
    private String name;
    private String pass;
    private String level;

    public Students() {
    }

    public Students(String ID, String name, String pass, String level) {
        this.ID = ID;
        this.name = name;
        this.pass = pass;
        this.level = level;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String toString() {
        return "ID: '" + ID + "', name: '" + this.name + "', pass: '" + this.pass + "', level: '" + this.level + "'";
    }
}