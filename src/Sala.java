public class Sala {
    int numSala;
    boolean[][] postiOccupati = new boolean[10][10];
    int postiDisponibili;
    public Sala(int numSala) {
        this.numSala = numSala;
        postiDisponibili = postiOccupati.length * postiOccupati[0].length;
    }

    public void removePosto() {
        this.postiDisponibili--;
    }

    public int getPostiDisponibili() {
        return postiDisponibili;
    }

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public void setPostiOccupati(int x, int y) {
        postiOccupati[x][y] = true;
    }
    public void printSala() {
        System.out.println("I posti in sala sono:");
        for (int i = 0; i < postiOccupati.length; i++) {
            if (i == 0) {
                System.out.print(" ");
                for (int j = 0; j < postiOccupati[i].length; j++) {
                    System.out.print("  " + j);
                }
                System.out.println();
            }
            System.out.print(i + "  ");
            for (int j = 0; j < postiOccupati[i].length; j++) {

                if (postiOccupati[i][j]) {
                    System.out.print("x  ");
                } else {
                    System.out.print("o  ");
                }
            }
            System.out.println();
        }
    }
    public boolean isSeatOccupied(int x, int y) {
        return postiOccupati[x][y];
    }
}
