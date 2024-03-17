    import java.util.ArrayList;

    /**
     * @author AZANDOSSESSI Jaurès.
     * Classe abstraite représentant les statistiques d'une phase de tournoi à élimination directe avec nset.
     */
    public abstract class StatistiquesNset {

        protected ArrayList<ArrayList<MatchNSet>> matchesNset;

        /**
         * Constructeur de la classe TournamentStatsNset.
         * Initialise la liste de matchs.
         */
        public StatistiquesNset() {
            this.matchesNset = new ArrayList<>();
        }

        /**
         * Récupère la liste des matchs avec nset.
         *
         * @return Liste des matchs avec nset.
         */
        public ArrayList<ArrayList<MatchNSet>> getMatchesNset() {
            return matchesNset;
        }

        /**
         * Ajoute une liste de matchs aux statistiques.
         *
         * @param round Liste de matchs à ajouter.
         */
        public void addRoundNset(ArrayList<MatchNSet> round) {
            this.matchesNset.add(round);
        }
    }
