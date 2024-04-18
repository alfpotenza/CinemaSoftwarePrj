import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main extends ReadFiles{
    public static void main(String[] args) {
        HashMap<LocalDate, ArrayList<Film>> movieList = new HashMap<>();

        ArrayList<ArrayList<String>> movieInfo = readFile("src/MovieSchedule - Foglio1.csv");
        ArrayList<ArrayList<String>> movieTimeTable = readFile("src/MovieSchedule - Foglio2.csv");

        try {

            /*for (int i = 0; i < dataMatrix.size(); i++) {
                LocalDate movieDate = LocalDate.of(Integer.parseInt(dataMatrix.get(i).get(4)), Integer.parseInt(dataMatrix.get(i).get(3)),Integer.parseInt(dataMatrix.get(i).get(2)));
                if(movieList.get(movieDate) != null) {
                    movieList.get(movieDate).add(creaFilm(dataMatrix.get(i)));
                } else {
                    ArrayList<Film> moviesToAdd = new ArrayList<>();
                    moviesToAdd.add(creaFilm(dataMatrix.get(i)));
                    movieList.put(movieDate, moviesToAdd);
                }
            }*/
            buyProcess(movieList);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    static void buyProcess(HashMap<LocalDate, ArrayList<Film>> movieList) {
        Scanner inp = new Scanner(System.in);
        String choice = "";
        System.out.println("sei interessato ad acquistare un biglietto? Y/N");
        choice = inp.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            System.out.println("Perfetto, quando vorresti vedere il film?");
            LocalDate chosenDate = printMoviesByDate(movieList);
            System.out.println("Quale film vorresti vedere? Inserisci il numero.");
            Film chosenFilm = movieList.get(chosenDate).get(inp.nextInt());
            System.out.println("Hai scelto il film " + chosenFilm.getMovieName() + ".");
            System.out.println(chosenFilm.toString(0));

            System.out.println( "quale orario preferisci? Inserisci il numero.");
            LocalTime chosenTime = (LocalTime) chosenFilm.getProgrammazione().getSchedule().keySet().toArray()[inp.nextInt()];

            System.out.println("Hai scelto il film " + chosenFilm.getMovieName() + " alle ore " + chosenTime);
            Sala chosenRoom = chosenFilm.getProgrammazione().getSala(chosenDate, chosenTime);
            System.out.println("Quanti biglietti vorresti acquistare?");
            int ticketsCount = inp.nextInt();
            if(ticketsCount <= 0) {
                System.out.println("Non si possono inserire valori minori o uguali a 0!");
            } else {
                ArrayList<ArrayList<Sala>> postiSelezionati = buyTickets(ticketsCount, chosenRoom, chosenFilm, chosenTime, chosenDate);
                String postiPrint = "";
                if(!Objects.equals(postiSelezionati, new ArrayList<ArrayList<Sala>>())) {
                    for (ArrayList<Sala> _sale : postiSelezionati) {
                        postiPrint = postiPrint.concat("riga: " + _sale.get(1).getNumSala() + " colonna: " + _sale.get(0).getNumSala() + " | ");
                    }
                    System.out.println("Dunque, Ricapitolando: Vuoi acquistare " + ticketsCount + " biglietti con posti | " + postiPrint + "per il film " + chosenFilm.getMovieName() + " alle ore " + chosenTime + " del giorno " + chosenDate);

                }
                buyProcess(movieList);
            }

        }
        else if (choice.equalsIgnoreCase("N")) {
            System.out.println("Grazie mille, arrivederci");
        } else {
            System.out.println("Risposta non valida.");
            buyProcess(movieList);
        }
    }

    //TODO: Qui utilizzerei un oggetto personalizzato che funzioni come una hashmap ma senza usare KEY e VALUE. (Array a due elementi)
    static ArrayList<ArrayList<Sala>> buyTickets(int ticketAmount, Sala chosenRoom, Film chosenFilm, LocalTime chosenTime, LocalDate chosenDate) {
        Sala sala = chosenFilm.getProgrammazione().getSala(chosenDate, chosenTime);
        Scanner inp = new Scanner(System.in);
        ArrayList<ArrayList<Sala>> seats = new ArrayList<>();
        if(sala.getPostiDisponibili() >= ticketAmount) {
            for (int i = 0; i < ticketAmount; i++) {
                sala.printSala();
                System.out.println("a quale posto saresti interessato? inserisci le coordinate nell'ordine RIGHE e COLONNE ");
                seats.add(new ArrayList<>());
                int x = inp.nextInt();
                int y = inp.nextInt();
                if(sala.isSeatOccupied(x,y)) {
                    System.out.println("ci dispiace, quel posto è occupato.");
                    seats.remove(seats.size() - 1);
                    i--;
                } else {
                    seats.get(i).add(new Sala(y));
                    seats.get(i).add(new Sala(x));
                    sala.setPostiOccupati(seats.get(i).get(1).getNumSala(), seats.get(i).get(0).getNumSala());
                    sala.removePosto();
                }
            }
            sala.printSala();
        } else {
            System.out.println("Ci dispiace, ma la sala è piena.");
        }
        return seats;
    }
    static LocalDate printMoviesByDate(HashMap<LocalDate, ArrayList<Film>> movieList) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Inserisci una data (AAAA/MM/GG)");
        LocalDate date = LocalDate.of(inp.nextInt(), inp.nextInt(), inp.nextInt());
        if(movieList.get(date) != null) {
            ArrayList<Film> movieListArray = movieList.get(date);
            for (int i = 0; i < movieListArray.size(); i++) {
                System.out.println(movieListArray.get(i).toString(i));
            }
        } else {
            System.out.println("Ci dispiace, ma in quella data non verranno riprodotti film.");
            buyProcess(movieList);
        }
        return date;
    }

   /* public static Film creaFilm(ArrayList<String> data) {
        return new Film(
                data.get(0),
                Integer.parseInt(data.get(1)),
                new Programmazione(
                        scheduleInfo(data),
                        LocalDate.of(Integer.parseInt(data.get(4)), Integer.parseInt(data.get(3)), Integer.parseInt(data.get(2)))
                )

        );
    }*/
    public static HashMap<LocalTime, ArrayList<Sala>> scheduleInfo(ArrayList<String> data) {
        HashMap<LocalTime, ArrayList<Sala>> schedule = new HashMap<>();
        for (int i = 0; i < (data.size() - 5)/3; i++) {
            LocalTime time;
            Sala sala;
            time = LocalTime.of(Integer.parseInt(data.get(3*i + 5)), Integer.parseInt(data.get(3*i + 6)));
            sala = new Sala(Integer.parseInt(data.get(3*i + 7)));
            if(schedule.get(time) != null) {
                schedule.get(time).add(sala);
            } else {
                ArrayList<Sala> salaToAdd = new ArrayList<>();
                salaToAdd.add(sala);
                schedule.put(time, salaToAdd);
            }

        }
        return schedule;
    }
}