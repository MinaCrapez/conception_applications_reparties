public enum Identification {
    USERNAME0("test","test"), USERNAME1("name1","123"), USERNAME2("name2","456");

    private final String username;
    private final String motDePasse;

    private Identification (String username, String motDePasse) {
        this.username = username;
        this.motDePasse = motDePasse;
    }

    public String getUsername() {
        return this.username;
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }
}

