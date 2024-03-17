    import java.util.ArrayList;

    /**
     * @author ABDELKERIM Daoud.
     * La classe RoundRobinStatsNset représente les statistiques de la phase à la ronde d'un tournoi avec plusieurs sets.
     */
    public class RoundRobinStatsNset extends StatistiquesNset {

        private ArrayList<Integer> lesPoints;

        /**
         * Constructeur de la classe RoundRobinStatsNset.
         */
        public RoundRobinStatsNset() {
            super();
            this.lesPoints = new ArrayList<>();
        }

        /**
         * Récupère la liste des points.
         *
         * @return Liste des points.
         */
        public ArrayList<Integer> getList() {
            return this.lesPoints;
        }

        /**
         * Ajoute une liste de points aux statistiques.
         *
         * @param ls Liste des points à ajouter.
         */
        public void addList(ArrayList<Integer> ls) {
            this.lesPoints.addAll(ls);
        }
    }
