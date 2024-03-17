    import java.util.ArrayList;

    /**
     * @author Liamidi Deen.
     * La classe abstraite Phase représente la classe mère de tous les formats de tournoi.
     */
    public interface Phase {

        /**
         * Programme les matchs pour la phase en utilisant la liste des équipes fournies.
         *
         * @param es Liste des équipes participant à la phase.
         * @return Liste des matchs programmés pour la phase.
         */
        public abstract ArrayList<Match> programation(ArrayList<Equipe> es);

        /**
         * Déroule la phase et renvoie la liste des équipes victorieuses de la phase.
         *
         * @return Liste des équipes victorieuses de la phase.
         */
        public abstract ArrayList<Equipe> run();
    }
