    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    /**
     * @author AZANDOSSESSI Jaurès et PALIERNE Erwan.
     * La classe RoundRobin représente la phase de tournoi à la ronde.
     */
    public class RoundRobin implements Phase {
        private ArrayList<Equipe> equipes;
        private int nset;
        private ArrayList<Match> lesMatchs;
        private ArrayList<MatchNSet> lMatchNSets;
        private ArrayList<Integer> points;
        private RoundRobinStats stats;
        private RoundRobinStatsNset statsNset;

        /**
         * Constructeur de la classe RoundRobin.
         *
         * @param es Liste des équipes participant à la phase à la ronde.
         * @param nset Nombre de sets par match.
         */
        public RoundRobin(ArrayList<Equipe> es, int nset) {
            this.equipes = es;
            this.nset = nset;
            this.lesMatchs = new ArrayList<>();
            this.points = new ArrayList<>(Collections.nCopies(this.equipes.size(), 0));
            this.stats = new RoundRobinStats();
            if (nset > 1) {
                this.statsNset = new RoundRobinStatsNset();
                this.lMatchNSets = new ArrayList<>();
            }
        }

        /**
         * Programme les matchs pour la phase à la ronde.
         *
         * @param es Liste des équipes participant à la phase à la ronde.
         * @return Liste des matchs programmés pour la phase à la ronde.
         */
        @Override
        public ArrayList<Match> programation(ArrayList<Equipe> es) {
            ArrayList<Match> ms = new ArrayList<>();
            for (int i = 0; i < es.size() - 1; i++) {
                for (int j = i + 1; j < es.size(); j++) {
                    Equipe e1 = es.get(i);
                    Equipe e2 = es.get(j);
                    Match m = (this.nset == 1) ? new Match(e1, e2) : new MatchNSet(e1, e2, this.nset);
                    ms.add(m);
                }
            }
            return ms;
        }

        /**
         * Vérifie s'il y a égalité de points.
         *
         * @param m Nombre maximal de points.
         * @return True s'il y a égalité, false sinon.
         */
        public boolean verifEgallite(int m) {
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i) == m) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Réactualise les matchs en cas d'égalité.
         *
         * @param m Nombre maximal de points.
         */
        public void reactualisationMatch(int m) {
            // Vérifier s'il y a plusieurs équipes avec le même nombre maximal de points
            List<Integer> indicesMaxPoints = new ArrayList<>();
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i) == m) {
                    indicesMaxPoints.add(i);
                }
            }
            ArrayList<Equipe> equipesMaxPoints = new ArrayList<>();
            for (int i = 0; i < indicesMaxPoints.size(); i++) {
                int index = indicesMaxPoints.get(i);
                equipesMaxPoints.add(this.equipes.get(index));
            }

            ArrayList<Match> matchs = programation(equipesMaxPoints);
            for (Match match : matchs) {
                match.jouer();
                points.set(this.equipes.indexOf(match.vainqueur),
                        points.get(this.equipes.indexOf(match.vainqueur)) + 3);
                if (nset == 1) {
                    lesMatchs.add(match);
                } else {
                    lMatchNSets.add((MatchNSet) match);
                }
            }
        }

        /**
         * Exécute la phase à la ronde du tournoi.
         *
         * @return Liste des équipes gagnantes de la phase à la ronde.
         */
        @Override
        public ArrayList<Equipe> run() {
            ArrayList<Equipe> gagnantes = new ArrayList<>();
            ArrayList<Match> ms = programation(this.equipes);
            Collections.shuffle(ms);

            for (Match m : ms) {
                m.jouer();
                points.set(this.equipes.indexOf(m.vainqueur), points.get(this.equipes.indexOf(m.vainqueur)) + 3);
                if (nset == 1) {
                    lesMatchs.add(m);
                } else {
                    lMatchNSets.add((MatchNSet) m);
                }
            }

            int maxPoints = Collections.max(points);
            while (verifEgallite(maxPoints)) {
                reactualisationMatch(maxPoints);
                maxPoints = Collections.max(points);
                if (verifEgallite(maxPoints)) {
                    break;
                }
            }
            int indiceVainqueur = points.indexOf(maxPoints);
            gagnantes.add(this.equipes.get(indiceVainqueur));

            if (nset == 1) {
                stats.addList(points);
                stats.addRound(lesMatchs);
            } else {
                statsNset.addList(points);
                statsNset.addRoundNset(lMatchNSets);
            }
            return gagnantes;
        }

        /**
         * Récupère les statistiques de la phase à la ronde.
         *
         * @return Statistiques de la phase à la ronde.
         */
        public RoundRobinStats getStats() {
            return this.stats;
        }

        /**
         * Récupère les matchs avec plusieurs sets.
         *
         * @return Liste des matchs avec plusieurs sets.
         */
        public ArrayList<MatchNSet> getMatchNSets() {
            return this.lMatchNSets;
        }

        /**
         * Récupère les statistiques de la phase à la ronde avec plusieurs sets.
         *
         * @return Statistiques de la phase à la ronde avec plusieurs sets.
         */
        public RoundRobinStatsNset getStatsNset() {
            return this.statsNset;
        }
    }
