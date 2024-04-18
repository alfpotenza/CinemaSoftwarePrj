
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
    public Film(ArrayList<String> movieInfo, ArrayList<String> timeTable) {
        ArrayList<LocalDate> dates = new ArrayList<>();
        ArrayList<HashMap<LocalTime, Sala>> times = new ArrayList<>();
        for (int i = 0; i < timeTable.size(); i++) {
            if(timeTable.get(i).equals("D")) {
                dates.add(LocalDate.of(Integer.parseInt(timeTable.get(i + 1)), Integer.parseInt(timeTable.get(i + 2)), Integer.parseInt(timeTable.get(i + 3))));
            } else if(timeTable.get(i).equals("T")) {
                HashMap<LocalTime, Sala> time = new HashMap<>();
                //se ci sono problemi con le sale guardare qui
                time.put(LocalTime.of(Integer.parseInt(timeTable.get(i + 1)), Integer.parseInt(timeTable.get(i + 2))), new Sala(Integer.parseInt(timeTable.get(i + 3))));
                times.add(time);
            }
        }
        this.movieName = movieInfo.get(0);
        this.duration = Integer.parseInt(movieInfo.get(1));
        for (int i = 0; i < dates.size(); i++) {
            for (int j = 0; j < times.get(i).keySet().size(); j++) {
                this.programmazione = new Programmazione((HashMap<LocalDate, HashMap<LocalTime, Sala>>)new HashMap<>().put(dates.get(i), new HashMap<>().put(times.get(i).keySet().toArray()[j], times.get(i).values().toArray()[j])));
            }
        }
    }

    public Programmazione getProgrammazione() {
        return programmazione;
    }

    public String getMovieName() {
        return movieName;
    }

    public String toString(int curFilm) {
        HashMap<LocalDate, HashMap<LocalTime, Sala>> scheduleHashMap;
        scheduleHashMap = programmazione.schedule;
        String orari = "";
        for (int i = 0; i < scheduleHashMap.size(); i++) {
            LocalTime time = (LocalTime)scheduleHashMap.keySet().toArray()[i];
            Sala sala = (Sala)scheduleHashMap.values().toArray()[i];
            String saleTxt = "";
            saleTxt = saleTxt.concat(sala.getNumSala() + " ");
            saleTxt = saleTxt.concat("|");
            orari = orari.concat("[" + i + "] " + time + " nella sala: " + saleTxt + " ");
        }
        return "[" + curFilm + "] " + movieName + " | " + duration + "min | " + "alle ore: " + orari;
    }
}
