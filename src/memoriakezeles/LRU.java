package memoriakezeles;

import java.util.LinkedList;

import static memoriakezeles.LapTabla.Lap;
import static memoriakezeles.KeretTabla.Keret;

public class LRU {
    LapTabla lapTabla;
    KeretTabla keretTabla;

    LinkedList<LapTabla.Lap> lapokInMemory;

    protected int laphibakSzama;
    public LRU(LapTabla lapTabla, KeretTabla keretTabla) {
        this.lapTabla = lapTabla;
        this.keretTabla = keretTabla;
        lapokInMemory = new LinkedList<>();
        laphibakSzama = 0;
    }

    public void nextTask(int id) {
        Lap lap = lapTabla.getLap(id);

        if(laphiba(lap)) {
            laphibakSzama++;
            Keret ahovaTesszuk;
            ahovaTesszuk = keretTabla.szabadKeret();
            if (ahovaTesszuk != null) { //ebben az esetben van szabad keret, egyszerű a dolgunk.
                szabadKeretbeTolt(ahovaTesszuk,lap);
                System.out.print(ahovaTesszuk.id);
            } else {
                ahovaTesszuk = felszabadit();
                if(ahovaTesszuk != null) {
                    szabadKeretbeTolt(ahovaTesszuk,lap);
                    System.out.print(ahovaTesszuk.id);
                } else {
                    System.out.print('*');
                }
            }
        } else { //nincs laphiba
            ujrarendez(lap);
            System.out.print('-');
        }
        agePageLock();

    }

    /**
     * Felszabadít egy keretet és visszaadja, vagy null-t ha nem lehetett.
     */
    protected Keret felszabadit() {
        Keret felszabaditott = null;
        for(Lap lap : lapokInMemory) {
            if(lap.pageLock == 0) {
                lap.currentKeret.taroltLap = null;
                lap.currentKeret.taken = false;
                lap.inMemory = false;
                felszabaditott = lap.currentKeret;
                lap.currentKeret = null;
                lapokInMemory.remove(lap);
                break;
            }
        }
        return felszabaditott;
    }

    /**
     * A használt lapot a list elejére teszi
     */
    protected void ujrarendez(Lap lap) {
        lap.accessed = true;
        lap.pageLock = 0;
        lapokInMemory.remove(lap);
        lapokInMemory.addLast(lap);
    }

    protected boolean laphiba(Lap lap) {
        return !lapokInMemory.contains(lap);
    }


    protected void szabadKeretbeTolt(Keret keret, Lap lap) {
        keret.taroltLap = lap;
        keret.dirty = false;
        keret.taken = true;

        lap.inMemory = true;
        lap.accessed = false;
        lap.currentKeret = keret;
        lap.pageLock = 4;

        lapokInMemory.addLast(lap);
    }

    protected void agePageLock() {
        for(Lap lap : lapokInMemory) {
            if(lap.pageLock != 0) lap.pageLock--;
        }
    }
}
