    import java.util.ArrayList;

    /**
     * @author LIAMIDI Deen.
     * Classe abstraite représentant les statistiques d'une phase de tournoi.
     */
    public abstract class Statistiques {

        protected ArrayList<ArrayList<Match>> matches;

        /**
         * Constructeur de la classe TournamentStats.
         * Initialise la liste de matchs.
         */
        public Statistiques() {
            this.matches = new ArrayList<>();
        }

        /**
         * Récupère la liste des matchs.
         *
         * @return Liste des matchs.
         */
        public ArrayList<ArrayList<Match>> getMatches() {
            return matches;
        }

        /**
         * Ajoute une liste de matchs aux statistiques.
         *
         * @param round Liste de matchs à ajouter.
         */
        public void addRound(ArrayList<Match> round) {
            this.matches.add(round);
        }
    }
