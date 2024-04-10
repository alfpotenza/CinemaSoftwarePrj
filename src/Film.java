
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Film {
    //variables
    private String movieName;
    private int duration;
    private Programmazione programmazione;
    //constructor
    public Film(String movieName, int duration, Programmazione programmazione) {
        this.movieName = movieName;
        this.duration = duration;
        this.programmazione = programmazione;
    }

    public Programmazione getProgrammazione() {
        return programmazione;
    }

    public String getMovieName() {
        return movieName;
    }

    public void stampaFilm(int curFilm, boolean isCheckingTime) {
        HashMap<LocalTime, ArrayList<Sala>> scheduleHashMap;
        scheduleHashMap = programmazione.schedule;
        String orari = "";
        for (int i = 0; i < scheduleHashMap.size(); i++) {
            LocalTime time = (LocalTime)scheduleHashMap.keySet().toArray()[i];
            ArrayList<Sala> sale = (ArrayList<Sala>)scheduleHashMap.values().toArray()[i];
            String saleTxt = "";
            for (Sala integer : sale) {
                saleTxt = saleTxt.concat(integer.getNumSala() + " ");
            }
            saleTxt = saleTxt.concat("|");
            //TODO: trovare un modo migliore per quest'iterazione
            if(isCheckingTime) {
                if (sale.size() > 1) orari = orari.concat("[" + i + "] " + time + "nelle sale:" + saleTxt + " ");
                else orari = orari.concat("[" + i + "] " + time + " nella sala: " + saleTxt + " ");


            } else {
                if (sale.size() > 1) orari = orari.concat(time + " nelle sale: " + saleTxt + " ");
                else orari = orari.concat(time + " nella sala: " + saleTxt + " ");

            }

        }
        if(isCheckingTime)System.out.println(movieName + " | " + duration + "min | " + "alle ore: " + orari);
        else System.out.println("[" + curFilm + "] " + movieName + " | " + duration + "min | " + "alle ore: " + orari);
    }
}
