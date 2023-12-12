package memoriakezeles;

import java.util.HashSet;
import java.util.Set;

public class LapTabla {
    protected Set<Lap> lapok;

    public LapTabla() {
        lapok = new HashSet<>();
    }
    public static class Lap {
        protected int id;
        protected boolean accessed;
        protected boolean inMemory;

        protected int pageLock;

        protected KeretTabla.Keret currentKeret;

        public Lap(int id) {
            this.id = id;
            accessed = false;
            inMemory = false;
            currentKeret = null;
            pageLock = 0;
        }
    }

    /**
     * Visszaadja a lapot a megadott id-vel.
     */
    public Lap getLap(int id) {
        Lap ret = null;
        for(Lap lap : lapok) {
            if(lap.id == id) {
                ret = lap;
                break;
            }
        }
        if(ret == null) {
            ret = new Lap(id);
            lapok.add(ret);
        }
        return ret;
    }
}
