import java.util.ArrayList;

public class Team {

    private Player tank;
    private ArrayList<Player> DPS = new ArrayList<>(){{add(null);add(null);}};
    private ArrayList<Player> support = new ArrayList<>(){{add(null);add(null);}};
    private ArrayList<Player> bench = new ArrayList<>(){};

    public Team(){

    }

    public ArrayList<Player> getFullTeam(){
        return new ArrayList<>(){{add(tank);addAll(DPS);addAll(support);addAll(bench);}};
    }

    public ArrayList<Player> getActiveTeam(){
        return new ArrayList<>(){{add(tank);addAll(DPS);addAll(support);}};
    }

    public Player getTank(){
        return tank;
    }

    public Player getDPS(int slot){
        if (slot > 2) return null;
        return DPS.get(slot-1);
    }

    public Player getSupport(int slot){
        if (slot > 2) return null;
        return support.get(slot-1);
    }

    public Player getBench(int slot){
        return bench.get(slot-1);
    }

    public void addBench(Player p){
        bench.add(p);
    }

    public void removeBench(Player p){
        bench.remove(p);
    }

    public boolean setTank(Player p){
        if (!bench.contains(p)) return false;
        if (getTank() != null){
            addBench(getTank());
        }
        tank = p;
        removeBench(p);
        return true;
    }

    public boolean setDPS(Player p, int slot){
        if (!bench.contains(p)) return false;
        if (slot > 2) return false;
        if (getDPS(slot+1) != null){
            addBench(getDPS(slot-1));
        }
        DPS.set(slot - 1, p);
        removeBench(p);
        return true;
    }

    public boolean setSupport(Player p, int slot){
        if (!bench.contains(p)) return false;
        if (slot > 2) return false;
        if (getSupport(slot+1) != null){
            addBench(getSupport(slot-1));
        }
        support.set(slot-1, p);
        removeBench(p);
        return true;
    }

    @Override
    public String toString(){
        return String.format("""
                Tank: %s
                DPS: %s, %s
                Support: %s, %s
                """, tank, getDPS(1), getDPS(2), getSupport(1), getSupport(2));
    }
}
