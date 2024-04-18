import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Programmazione {
    //variables
    HashMap<LocalDate, HashMap<LocalTime, Sala>> schedule;
    //constructor
    public Programmazione(HashMap<LocalDate, HashMap<LocalTime, Sala>> schedule) {
        this.schedule = schedule;
    }

    public Sala getSala(LocalDate date, LocalTime time) {
        return schedule.get(date).get(time);
    }

    public HashMap<LocalDate, HashMap<LocalTime, Sala>> getSchedule() {
        return schedule;
    }


}
