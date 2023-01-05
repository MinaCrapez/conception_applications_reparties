import java.util.Map;
import java.util.HashMap;

public class Mdp {

    private Map<String, String> mdp;

    public Mdp(Map<String, String> mdp) {
        this.mdp = new HashMap<String, String>();
        mdp.put("authentification","test");
    }

    public Map<String, String> getMdp(){
        return this.mdp;
    }

}
