import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Programmazione {
    //variables
    LocalDate data;
    HashMap<LocalTime, ArrayList<Sala>> schedule;
    //constructor
    public Programmazione(HashMap<LocalTime, ArrayList<Sala>> schedule, LocalDate data) {
        this.schedule = schedule;
        this.data = data;
    }

    public ArrayList<Sala> getSale(LocalTime time) {
        return schedule.get(time);
    }

    public HashMap<LocalTime, ArrayList<Sala>> getSchedule() {
        return schedule;
    }


}
