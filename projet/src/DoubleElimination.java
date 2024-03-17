    import java.util.ArrayList;
    import java.util.Collections;

    /**
     * @author AZANDOSSESSI Jaurès.
     * Classe qui représente une phase de tournoi à double élimination.
     */
    public class DoubleElimination implements Phase {
        private ArrayList<Equipe> equipes;
        private int nset;
        private DoubleEliminationStats stats;
        private DoubleEliminationStatsNset statsNset;

        /**
         * Constructeur de la classe DoubleElimination.
         *
         * @param es    Liste d'équipes participant à la phase.
         * @param nset  Nombre de sets par match dans la phase.
         */
        public DoubleElimination(ArrayList<Equipe> es, int nset) {
            this.equipes = es;
            this.nset = nset;
            this.stats = new DoubleEliminationStats();
            if (nset > 1) {
                this.statsNset = new DoubleEliminationStatsNset();
            }
        }

        /**
         * Programme les matchs pour la phase de double élimination.
         *
         * @param es Liste d'équipes à programmer dans les matchs.
         * @return   Liste des matchs programmés.
         */
        @Override
        public ArrayList<Match> programation(ArrayList<Equipe> es) {
            ArrayList<Match> ms = new ArrayList<Match>();
            Equipe e1, e2;
            Match m;
            Collections.shuffle(es);

            if (es.size() % 2 == 1 && es.size() != 1) {
                EquipeSpecial mS = new EquipeSpecial();
                es.add(mS);
            }

            while (es.size() != 0) {
                e1 = es.get(0);
                e2 = es.get(1);

                if (this.nset == 1) {
                    m = new Match(e1, e2);
                } else {
                    m = new MatchNSet(e1, e2, this.nset);
                }

                es.remove(e1);
                es.remove(e2);
                ms.add(m);
            }

            return ms;
        }

        /**
         * Exécute la phase de double élimination.
         *
         * @return Liste des équipes gagnantes de la phase.
         */
        @Override
        public ArrayList<Equipe> run() {
            Match mf;
            ArrayList<Match> ms = new ArrayList<Match>();
            ArrayList<Match> mg, mp;
            ArrayList<MatchNSet> mgNset, mpNSets;
            ArrayList<Equipe> gagnantes = new ArrayList<>(this.equipes);
            ArrayList<Equipe> perdantes = new ArrayList<Equipe>();

            if (nset == 1) {
                if (gagnantes.size() == 1) {
                    System.out.println("Le gagnant est : " + gagnantes.get(0).getName());
                }

                if (gagnantes.size() == 2) {
                    mg = new ArrayList<>();
                    ms = this.programation(gagnantes);
                    ArrayList<Match> matchsJoues = new ArrayList<>();
                    gagnantes.clear();
                    for (Match m : ms) {
                        m.jouer();
                        matchsJoues.add(m);
                        stats.addRound(matchsJoues);
                        gagnantes.add(m.getVainqueur());
                    }
                }

                if (gagnantes.size() > 2) {
                    while (gagnantes.size() != 1) {
                        mg = new ArrayList<>();
                        mp = new ArrayList<>();

                        // Cycles des gagnants
                        ms = this.programation(gagnantes);
                        gagnantes.clear();

                        for (Match m : ms) {
                            m.jouer();
                            gagnantes.add(m.getVainqueur());
                            perdantes.add(m.getPerdant());
                            mg.add(m);
                        }

                        // Cycle des perdants
                        ms = this.programation(perdantes);
                        perdantes.clear();

                        for (Match m : ms) {
                            m.jouer();
                            perdantes.add(m.getVainqueur());
                            mp.add(m);
                        }

                        if (perdantes.size() == gagnantes.size() && perdantes.size() == 2) {
                            ms = this.programation(perdantes);
                            perdantes.clear();

                            for (Match m : ms) {
                                m.jouer();
                                perdantes.add(m.getVainqueur());
                                mp.add(m);
                            }
                        }
                        stats.addRound(mg);
                        stats.addLosers(mp);
                    }

                    mf = new Match(gagnantes.get(0), perdantes.get(0));
                    gagnantes.clear();
                    mf.jouer();
                    mg = new ArrayList<>();
                    mg.add(mf);
                    stats.addRound(mg);
                    gagnantes.add(mf.getVainqueur());
                }
            } else {
                if (gagnantes.size() == 2) {
                    ms = this.programation(gagnantes);
                    ArrayList<MatchNSet> matchsJoues = new ArrayList<>();
                    gagnantes.clear();
                    for (Match m : ms) {
                        m.jouer();
                        matchsJoues.add((MatchNSet) m);
                        statsNset.addRoundNset(matchsJoues);
                        gagnantes.add(m.getVainqueur());
                    }
                }

                if (gagnantes.size() > 2) {
                    while (gagnantes.size() != 1) {
                        mgNset = new ArrayList<>();
                        mpNSets = new ArrayList<>();

                        // Cycles des gagnants
                        ms = this.programation(gagnantes);
                        gagnantes.clear();

                        for (Match m : ms) {
                            m.jouer();
                            gagnantes.add(m.getVainqueur());
                            perdantes.add(m.getPerdant());
                            mgNset.add((MatchNSet) m);
                        }

                        // Cycle des perdants
                        ms = this.programation(perdantes);
                        perdantes.clear();

                        for (Match m : ms) {
                            m.jouer();
                            perdantes.add(m.getVainqueur());
                            mpNSets.add((MatchNSet) m);
                        }

                        if (perdantes.size() == gagnantes.size() && perdantes.size() == 2) {
                            ms = this.programation(perdantes);
                            perdantes.clear();

                            for (Match m : ms) {
                                m.jouer();
                                perdantes.add(m.getVainqueur());
                                mpNSets.add((MatchNSet) m);
                            }
                        }
                        this.statsNset.addRoundNset(mgNset);
                        this.statsNset.addLosersNset(mpNSets);
                    }
                    mf = new MatchNSet(gagnantes.get(0), perdantes.get(0), nset);
                    gagnantes.clear();
                    mf.jouer();
                    mgNset = new ArrayList<>();
                    mgNset.add((MatchNSet) mf);
                    statsNset.addRoundNset(mgNset);
                    gagnantes.add(mf.getVainqueur());
                }
            }

            return gagnantes;
        }

        /**
         * Récupère les statistiques de la phase de double élimination.
         *
         * @return Statistiques de la phase de double élimination.
         */
        public DoubleEliminationStats getStatsDoubleElimination() {
            return stats;
        }
        /**
         * Récupère les statistiques de la phase de double élimination avec match avec set.
         *
         * @return Statistiques de la phase de double élimination.
         */
        public DoubleEliminationStatsNset getStatsDoubleEliminationNstats() {
            return statsNset;
        }
    }
