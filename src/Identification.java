public enum Identification {
    /**
     * Identification is an enumaration with the username and password of users for the serveur FTP
     * 09/01/23
     * Mina Crapez - M1 MIAGE
     */
    USERNAME0("test","test"), USERNAME1("name1","123"), USERNAME2("name2","456");

    private final String username;
    private final String motDePasse;

    /**
     * constructor of a user
     * @param username the username of the user
     * @param motDePasse the password of the user
     */
    private Identification (String username, String motDePasse) {
        this.username = username;
        this.motDePasse = motDePasse;
    }

     /**
     * @return the username of a user
     */
    public String getUsername() {
        return this.username;
    }
    
    /**
     * @return the password of a user
     */
    public String getMotDePasse() {
        return this.motDePasse;
    }
}

