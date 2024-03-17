    import java.util.ArrayList;

    /**
     * @author AZANDOSSESSI Jaurès.
     * Classe représentant les statistiques d'une phase de tournoi à double élimination.
     */
    public class DoubleEliminationStats extends Statistiques {

        private ArrayList<ArrayList<Match>> losersMatches;

        /**
         * Constructeur de la classe DoubleEliminationStats.
         * Initialise les listes de matchs gagnants et perdants.
         */
        public DoubleEliminationStats() {
            super();
            this.losersMatches = new ArrayList<>();
        }

        /**
         * Récupère la liste des matchs perdants.
         *
         * @return Liste des matchs perdants.
         */
        public ArrayList<ArrayList<Match>> getLosers() {
            return this.losersMatches;
        }

        /**
         * Ajoute une liste de matchs perdants aux statistiques.
         *
         * @param losers Liste de matchs perdants à ajouter.
         */
        public void addLosers(ArrayList<Match> losers) {
            this.losersMatches.add(losers);
        }
    }
