public class Person {
    private int ID; //ID of person
    private String name; //name of person
    private String email; //email of person
    private String password; //password of person
    private String textFile; //text file for person

    /**
     * Constructor for Person.java
     * @param name
     * @param ID
     * @param email
     * @param password
     */
    public Person(String name, int ID, String email, String password) {
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.textFile = email + ".txt";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTextFile() {
        return textFile;
    }

    public void setTextFile(String textFile) {
        this.textFile = textFile;
    }
}
