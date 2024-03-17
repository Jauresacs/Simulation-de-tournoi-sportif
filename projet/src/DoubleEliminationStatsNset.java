    import java.util.ArrayList;

    /**
     * @author AZANDOSSESSI Jaurès.
     * Classe représentant les statistiques d'une phase de tournoi à double élimination avec nset.
     */
    public class DoubleEliminationStatsNset extends StatistiquesNset {

        private ArrayList<ArrayList<MatchNSet>> losersMatchesNset;

        /**
         * Constructeur de la classe DoubleEliminationStatsNset.
         * Initialise les listes de matchs gagnants et perdants.
         */
        public DoubleEliminationStatsNset() {
            super();
            this.losersMatchesNset = new ArrayList<>();
        }

        /**
         * Récupère la liste des matchs perdants avec nset.
         *
         * @return Liste des matchs perdants avec nset.
         */
        public ArrayList<ArrayList<MatchNSet>> getLosersNset() {
            return this.losersMatchesNset;
        }


        /**
         * Ajoute une liste de matchs perdants avec nset aux statistiques.
         *
         * @param losers Liste de matchs perdants avec nset à ajouter.
         */
        public void addLosersNset(ArrayList<MatchNSet> losers) {
            this.losersMatchesNset.add(losers);
        }

    }
