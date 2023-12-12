package memoriakezeles;

import java.util.HashMap;
import java.util.Map;

public class KeretTabla {
    protected Map<Character, Keret> keretek;

    public KeretTabla() {
        keretek = HashMap.newHashMap(3);
        keretek.put('A', new Keret('A'));
        keretek.put('B', new Keret('B'));
        keretek.put('C', new Keret('C'));
    }
    public static class Keret {
        protected boolean taken;
        protected boolean dirty;
        protected int acc;
        protected LapTabla.Lap taroltLap;

        protected char id;

        public Keret(char c) {
            id = c;
            taken = false;
            dirty = false;
            acc = 0;
            taroltLap = null;
        }
    }

    /**
     * Visszaadja az első szabad keretet, ha van
     * @return Egy Keret, ha van szabad, null, ha nincs.
     */
    public Keret szabadKeret() {
        if(keretek.get('A').taroltLap == null) {
            return keretek.get('A');
        } else if(keretek.get('B').taroltLap == null) {
            return keretek.get('B');
        } else if(keretek.get('C').taroltLap == null) {
            return keretek.get('C');
        } else return null;
    }
}
