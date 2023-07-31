import java.util.ArrayList;

public class Utils {

    /*
    To use literally just type Utils.NAME to access, very useful,
    feel free to add any final data here which u might find useful.
     */

    public static final ArrayList<String> HYBRID_MAPS = new ArrayList<>(){{
        add("numbani");
        add("kings-row");
        add("eichenwalde");
        add("blizzard-world");
        add("paraiso");
        add("midtown");
        add("hollywood");
    }};  // etc etc add the rest
    public static final ArrayList<String> PAYLOAD_MAPS = new ArrayList<>(){{
        add("junkertown");
        add("havana");
        add("circuit-royal");
        add("dorado");
        add("route-66");
        add("rialto");
        add("watchpoint-gibraltar");
        add("shambali-monestary");
    }};  // etc etc add the rest
    public static final ArrayList<String> PUSH_MAPS = new ArrayList<>(){{
        add("new-queens-street");
        add("colosseo");
        add("esperanca");
    }};  // etc etc add the rest
    public static final ArrayList<String> CONTROL_MAPS = new ArrayList<>(){{
        add("illios");
        add("busan");
        add("nepal");
        add("oasis");
        add("lijang-tower");
        add("antarctic-peninsula");
    }};  // etc etc add the rest
    public static final ArrayList<String> FLASHPOINT_MAPS = new ArrayList<>(){{
        add("the-indian-one");
        add("junkertown-two-electric-boogaloo");
    }};  // etc etc add the rest

    public static final ArrayList<String> DPS_HERO = new ArrayList<>(){{
        add("junkrat");
    }};  // etc etc add the rest

    public static final ArrayList<String> TANK_HERO = new ArrayList<>(){{
        add("doomfist");
    }};  // etc etc add the rest

    public static final ArrayList<String> SUPPORT_HERO = new ArrayList<>(){{
        add("ana");
    }};  // etc etc add the rest

    public static final int HYBRID = 1;
    public static final int PAYLOAD = 2;
    public static final int CONTROL = 3;
    public static final int PUSH = 4;
    public static final int FLASHPOINT = 5;
    public static final int ANY = 6;

}