                    import javax.swing.*;
                    import javax.swing.table.DefaultTableCellRenderer;
                    import java.awt.*;
                    import java.awt.event.ActionEvent;
                    import java.awt.event.ActionListener;
                    import java.util.ArrayList;
                    import java.util.Collections;
                    /**
                     * @author Tous les membres du groupes.
                     * Cette classe représente la fenêtre de simulation d'un tournoi sportif.
                     * Elle permet de visualiser et de simuler différents types de tournois.
                     */
                    public class SimulationTournoi extends JFrame implements ActionListener {
                        private JPanel pan, title;
                        private JLabel acceuil;
                        private JButton boutLancer, boutRetour, boutAffich, boutSortir;
                        private JTable resultTable, resultTable2, resultTable4, resultTable5;
                        private NonEditableTableModel tableModel, tableModel2, tableModel4, tableModel5;
                        private int nombreSets;
                        private String tournoiChoisi;
                        private ArrayList<Equipe> lesEquipes;
                        private boolean boutonLancerAppuye;
                        private DefaultTableCellRenderer centerRenderer;
                        private static final String ELIMINATION_DIRECTE = "Élimination Directe";
                        private static final String DOUBLE_ELIMINATION = "Double Élimination";
                        private static final String PHASE_GROUPE_ELIMINATION = "Phase de Groupe suivi d'Élimination Directe";
                        private static final String PHASE_GROUPE_DOUBLE_ELIMINATION = "Phase de Groupe suivi de Double Élimination";
                        private static final String PHASE_ROUND_ROBIN = "Round Robin";

                        /**
                         * Constructeur de la classe SimulationTournoi.
                         */
                        public SimulationTournoi() {
                            this.setTitle("Simulation de tournoi");
                            this.setSize(1100, 700);

                            pan = new JPanel();
                            pan.setBackground(Color.WHITE);

                            //Configuration des polices
                            Font policeHuge = new Font("Fipps", Font.PLAIN, 20);
                            Font police1 = new Font("Fipps", Font.PLAIN, 15);
                            Font police = new Font("Serif", Font.BOLD, 30);

                            //Création du label d'acceuil
                            acceuil = new JLabel();
                            acceuil.setFont(police);
                            acceuil.setText("Bienvenue dans la partie déroulement de votre tournoi");

                            //Panel pour le titre
                            title = new JPanel();
                            title.setLayout(new FlowLayout(FlowLayout.CENTER));
                            title.add(acceuil);

                            //Boutons
                            boutRetour = new JButton("Retour au menu d'ajout");
                            boutRetour.addActionListener(this);
                            boutRetour.setFont(policeHuge);

                            boutLancer = new JButton("Lancer le tournoi");
                            boutLancer.addActionListener(this);
                            boutLancer.setFont(policeHuge);

                            boutAffich = new JButton("Afficher les résultats");
                            boutAffich.addActionListener(this);
                            boutAffich.setFont(policeHuge);

                            boutSortir = new JButton("Quitter le tournoi");
                            boutSortir.addActionListener(this);
                            boutSortir.setFont(policeHuge);

                            centerRenderer = new DefaultTableCellRenderer();
                            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                            //Création du modèle de table avec des colonnes
                            tableModel = new NonEditableTableModel();
                            tableModel.addColumn("Match");
                            tableModel.addColumn("Equipe 1");
                            tableModel.addColumn(" ");
                            tableModel.addColumn("Equipe 2");
                            tableModel.addColumn("Vainqueur");
                            resultTable = new JTable(tableModel);
                            resultTable.setFont(police1);
                            
                            // Configuration du rendu des cellules au centre
                            for (int i = 0; i < resultTable.getColumnCount(); i++) {
                                resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                            
                            // Initialisation d'autres tables (resultTable2, resultTable4, resultTable5)
                            tableModel2 = new NonEditableTableModel();
                            tableModel2.addColumn("Match");
                            tableModel2.addColumn("Equipe 1");
                            tableModel2.addColumn("Score 1");
                            tableModel2.addColumn(" ");
                            tableModel2.addColumn("Equipe 2");
                            tableModel2.addColumn("Score 2");
                            tableModel2.addColumn("Vainqueur");

                            resultTable2 = new JTable(tableModel2);

                            resultTable2.setFont(police1);

                            for (int i = 0; i < resultTable2.getColumnCount(); i++) {
                                resultTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }


                            tableModel4 = new NonEditableTableModel();
                            tableModel4.addColumn("Equipe");
                            tableModel4.addColumn("Points");
                            tableModel4.addColumn("Equipe 1");
                            tableModel4.addColumn(" ");
                            tableModel4.addColumn("Equipe 2");
                            tableModel4.addColumn("Vainqueur");

                            resultTable4 = new JTable(tableModel4);

                            resultTable4.setFont(police1);

                            for (int i = 0; i < resultTable4.getColumnCount(); i++) {
                                resultTable4.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }

                            tableModel5 = new NonEditableTableModel();
                            tableModel5.addColumn("Equipe");
                            tableModel5.addColumn("Points");
                            tableModel5.addColumn("Equipe 1");
                            tableModel5.addColumn("Score 1");
                            tableModel5.addColumn(" ");
                            tableModel5.addColumn("Equipe 2");
                            tableModel5.addColumn("Score 1");
                            tableModel5.addColumn("Vainqueur");


                            resultTable5 = new JTable(tableModel5);

                            resultTable5.setFont(police1);

                            for (int i = 0; i < resultTable5.getColumnCount(); i++) {
                                resultTable5.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }


                            this.boutonLancerAppuye = false;

                            JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER));
                            button.add(boutRetour);
                            button.add(boutLancer);
                            button.add(boutAffich);
                            button.add(boutSortir);

                            pan.setLayout(new BorderLayout());
                            pan.add(title, BorderLayout.NORTH);
                            pan.add(button, BorderLayout.SOUTH);
                            resultTable.getTableHeader().setReorderingAllowed(false);
                            resultTable.getTableHeader().setResizingAllowed(false);

                            resultTable2.getTableHeader().setReorderingAllowed(false);
                            resultTable2.getTableHeader().setResizingAllowed(false);

                            resultTable4.getTableHeader().setReorderingAllowed(false);
                            resultTable4.getTableHeader().setResizingAllowed(false);

                            resultTable5.getTableHeader().setReorderingAllowed(false);
                            resultTable5.getTableHeader().setResizingAllowed(false);

                            this.getContentPane().add(pan);
                            this.setVisible(true);
                            this.setLocationRelativeTo(null);
                            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            this.pack();
                        }


                        /**
                        * Affiche les matches dans une table.
                        *
                        * @param l Liste de matches à afficher.
                        */
                        public void afficherMatch(ArrayList<ArrayList<Match>> l) {
                        for (int j = 0; j < l.size() - 1; j++) {
                            ArrayList<Match> m = l.get(j);
                            for (int i = 0; i < m.size(); i++) {
                                tableModel.addRow(new Object[]{
                                        "Match " + (i + 1),
                                        m.get(i).equipe1.getName(),
                                        "vs",
                                        m.get(i).equipe2.getName(),
                                        m.get(i).vainqueur.getName()
                                    });
                                }
                            }
                        }


                        /**
                        * Affiche le match final dans une table.
                        *
                        * @param l Liste de matches finaux à afficher.
                        */
                        public void afficherMatchFinal(ArrayList<ArrayList<Match>> l) {
                            // Ajout du match final
                            ArrayList<Match> dernierMatch = l.get(l.size() - 1);
                            tableModel.addRow(new Object[]{"", "", "Final", "", ""});
                            tableModel.addRow(new Object[]{
                                "Match Final",
                                dernierMatch.get(0).equipe1.getName(),
                                "vs",
                                dernierMatch.get(0).equipe2.getName(),
                                dernierMatch.get(0).vainqueur.getName()
                            });
                            tableModel.addRow(new Object[]{"", "", "", "", ""});
                            tableModel.addRow(new Object[]{"Vainqueur du tournoi", dernierMatch.get(0).vainqueur.getName()});
                        }

                        /**
                        * Affiche les matches avec des sets dans une table.
                        *
                        * @param p Liste des matches avec des sets à afficher.
                        */
                        public void afficherMatchNsetNset(ArrayList<ArrayList<MatchNSet>> p) {
                            for (int j = 0; j < p.size() - 1; j++) {
                                ArrayList<MatchNSet> m = p.get(j);
                                for (int i = 0; i < m.size(); i++) {
                                    tableModel2.addRow(new Object[]{
                                        "Match " + (i + 1),
                                        m.get(i).equipe1.getName(),
                                        m.get(i).getScore1(),
                                        "vs",
                                        m.get(i).equipe2.getName(),
                                        m.get(i).getScore2(),
                                        m.get(i).vainqueur.getName()
                                    });
                                }
                            }
                        }


                        /**
                        * Affiche le match final avec des sets dans une table.
                        *
                        * @param l Liste des matches avec des sets, incluant le match final, à afficher.
                        */
                        public void afficherMatchFinalNset(ArrayList<ArrayList<MatchNSet>> l) {
                            // Ajout du match final
                            ArrayList<MatchNSet> dernierMatch = l.get(l.size() - 1);
                            tableModel2.addRow(new Object[]{"", "", "", "Final", "", "", ""});
                            tableModel2.addRow(new Object[]{
                                "Match Final",
                                dernierMatch.get(0).equipe1.getName(),
                                dernierMatch.get(0).getScore1(),
                                "vs",
                                dernierMatch.get(0).equipe2.getName(),
                                dernierMatch.get(0).getScore2(),
                                dernierMatch.get(0).vainqueur.getName()
                            });
                            tableModel2.addRow(new Object[]{"", "", "", "", "", "", ""});
                            tableModel2.addRow(new Object[]{"Vainqueur du tournoi", dernierMatch.get(0).vainqueur.getName()});
                        }


                        /**
                        * Affiche les résultats de la phase d'élimination directe dans une table.
                        *
                        * @param tournoi L'instance de la phase d'élimination directe à afficher.
                        */
                        public void afficherResultatsEliminationDirecte(EliminationDirecte tournoi) {

                            if (nombreSets == 1) {
                                EliminationDirecteStats statistiques = tournoi.getStats();
                                ArrayList<ArrayList<Match>> lesSats = statistiques.getMatches();

                                // Si le nombre d'équipes est 1, afficher directement le vainqueur final
                                if (lesEquipes.size() == 1) {
                                    tableModel.addRow(new Object[]{"", "", "Phase élimination directe", "", ""});
                                    tableModel.addRow(new Object[]{"", "", "", "", "", "", ""});
                                    tableModel.addRow(new Object[]{"Vainqueur Final", lesEquipes.get(0).getName()});
                                } else {
                                    // Ajout du titre au milieu du tableau
                                    tableModel.addRow(new Object[]{"", "", "Phase élimination directe", "", ""});
                                    tableModel.addRow(new Object[]{"", "", "", "", "", "", ""});
                                    afficherMatch(lesSats);
                                    afficherMatchFinal(lesSats);
                                }
                            } else {
                                EliminationDirecteStatsNset statistiquesNset = tournoi.getStatsNset();
                                ArrayList<ArrayList<MatchNSet>> lesStatsNset = statistiquesNset.getMatchesNset();

                                // Si le nombre d'équipes est 1, afficher directement le vainqueur final
                                if (lesEquipes.size() == 1) {
                                    tableModel2.addRow(new Object[]{"", "", "", "Phase élimination directe", "", "", ""});
                                    tableModel2.addRow(new Object[]{"", "", "", "", "", "", ""});
                                    tableModel2.addRow(new Object[]{"Vainqueur Final", lesEquipes.get(0).getName()});
                                } else {
                                    // Ajout du titre au milieu du tableau
                                    tableModel2.addRow(new Object[]{"", "", "", "Phase élimination directe", "", "", ""});
                                    tableModel2.addRow(new Object[]{"", "", "", "", "", "", ""});
                                    afficherMatchNsetNset(lesStatsNset);
                                    afficherMatchFinalNset(lesStatsNset);
                                }
                            }
                        }

                        /**
                        * Affiche les résultats de la phase de double élimination dans une table.
                        *
                        * @param tournoi L'instance de la phase de double élimination à afficher.
                        */
                        public void afficherResultatsDoubleElimination(DoubleElimination tournoi) {
                            if (nombreSets == 1) {
                                // Si le nombre d'équipes est 1, afficher directement le vainqueur final
                                if (lesEquipes.size() == 1 || lesEquipes.size() == 2) {
                                    tableModel.addRow(new Object[]{"", "", "Phase Double Elimination", "", ""});
                                    tableModel.addRow(new Object[]{"", "", "", "", ""});
                                    tableModel.addRow(new Object[]{"Vainqueur Final", lesEquipes.get(0).getName()});
                                } else if (lesEquipes.size() > 2) {
                                    DoubleEliminationStats statistiques = tournoi.getStatsDoubleElimination();
                                    ArrayList<ArrayList<Match>> matchsGagnants = statistiques.getMatches();
                                    tableModel.addRow(new Object[]{"", "", "Phase Double Elimination", "", ""});
                                    tableModel.addRow(new Object[]{"", "", "", "", ""});

                                    // Ajout du titre au milieu du tableau pour les gagnants
                                    tableModel.addRow(new Object[]{"", "", "Tournoi des gagnants", "", ""});
                                    afficherMatch(matchsGagnants);
                                    ArrayList<Match> gagnantsFinal = matchsGagnants.get(matchsGagnants.size() - 1);
                                    tableModel.addRow(new Object[]{"", "", "", "", ""});
                                    tableModel.addRow(new Object[]{"Vainqueur 1", gagnantsFinal.get(0).vainqueur.getName()});

                                    // Vérifier s'il y a une phase de perdants
                                    if (!statistiques.getLosers().isEmpty()) {
                                        ArrayList<ArrayList<Match>> matchsPerdants = statistiques.getLosers();

                                        // Ajout du titre au milieu du tableau pour les perdants
                                        tableModel.addRow(new Object[]{"", "", "", "", ""});
                                        tableModel.addRow(new Object[]{"", "", "Tournoi des perdants", "", ""});
                                        afficherMatch(matchsPerdants);
                                        ArrayList<Match> perdantsFinal = statistiques.getLosers().get(statistiques.getLosers().size() - 1);
                                        tableModel.addRow(new Object[]{
                                            "Match 1",
                                            perdantsFinal.get(0).equipe1.getName(),
                                            "vs",
                                            perdantsFinal.get(0).equipe2.getName(),
                                            perdantsFinal.get(0).getVainqueur().getName(),
                                        });
                                        tableModel.addRow(new Object[]{"", "", "", "", ""});
                                        tableModel.addRow(new Object[]{"Vainqueur 2", perdantsFinal.get(0).vainqueur.getName()});
                                        tableModel.addRow(new Object[]{"", "", "", "", ""});

                                        tableModel.addRow(new Object[]{"", "", "FINAL", "", ""});

                                        Match matchFinal = matchsGagnants.get(matchsGagnants.size() - 1).get(matchsGagnants.get(matchsGagnants.size() - 1).size() - 1);

                                        tableModel.addRow(new Object[]{
                                            "Match final",
                                            matchFinal.equipe1.getName(),
                                            "vs",
                                            matchFinal.equipe2.getName(),
                                            matchFinal.vainqueur.getName(),
                                        });
                                        tableModel.addRow(new Object[]{"", "", "", "", ""});
                                        tableModel.addRow(new Object[]{"Vainqueur du tournoi", matchFinal.vainqueur.getName()});
                                    }
                                }
                            }else {
                                    // Si le nombre d'équipes est 1, afficher directement le vainqueur final
                                    if (lesEquipes.size() == 1) {
                                        tableModel2.addRow(new Object[]{"", "","", "Phase Double Elimination","", "", ""});
                                        tableModel2.addRow(new Object[]{"", "","", "","", "", ""});
                                        tableModel2.addRow(new Object[]{"Vainqueur Final", lesEquipes.get(0).getName()});

                                    }else if (lesEquipes.size() == 2) {
                                        tableModel2.addRow(new Object[]{"", "","", "Phase Double Elimination","", "", ""});
                                        tableModel2.addRow(new Object[]{"", "","", "","", "", ""});
                                        tableModel2.addRow(new Object[]{"", "","", "Match final","", "", ""});

                                        DoubleEliminationStatsNset statistiques = tournoi.getStatsDoubleEliminationNstats();
                                        ArrayList<ArrayList<MatchNSet>> matchsGagnants = statistiques.getMatchesNset();

                                        tableModel2.addRow(new Object[]{"Match", matchsGagnants.get(0).get(0).equipe1.getName(),matchsGagnants.get(0).get(0).getScore1(), "vs", matchsGagnants.get(0).get(0).equipe2.getName(),matchsGagnants.get(0).get(0).getScore2(), matchsGagnants.get(0).get(0).vainqueur.getName()});
                                        tableModel2.addRow(new Object[]{"", "","" ,"", ""});
                                        tableModel2.addRow(new Object[]{"Vainqueur Final", matchsGagnants.get(0).get(0).vainqueur.getName()});

                                    }else if (lesEquipes.size() > 2) {
                                        DoubleEliminationStatsNset statistiques = tournoi.getStatsDoubleEliminationNstats();
                                        ArrayList<ArrayList<MatchNSet>> matchsGagnants = statistiques.getMatchesNset();
                                        tableModel2.addRow(new Object[]{"", "","", "Phase Double Elimination","", "", ""});
                                        tableModel2.addRow(new Object[]{"", "","", "","", "", ""});
                                        // Ajout du titre au milieu du tableau pour les gagnants
                                        tableModel2.addRow(new Object[]{"", "","", "Tournoi des gagnants", "", ""});
                                        afficherMatchNsetNset(matchsGagnants);
                                        ArrayList<MatchNSet> gagnantsFinal = matchsGagnants.get(matchsGagnants.size() - 1);
                                        tableModel2.addRow(new Object[]{"", "","", "","", "", ""});
                                        tableModel2.addRow(new Object[]{"Vainqueur 1", gagnantsFinal.get(0).vainqueur.getName()});

                                        // Vérifier s'il y a une phase de perdants
                                        if (!statistiques.getLosersNset().isEmpty()) {
                                            ArrayList<ArrayList<MatchNSet>> matchsPerdants = statistiques.getLosersNset();

                                            // Ajout du titre au milieu du tableau pour les perdants
                                            tableModel2.addRow(new Object[]{"", "","", "Tournoi des perdants","", "", ""});
                                            afficherMatchNsetNset(matchsPerdants);
                                            ArrayList<MatchNSet> perdantsFinal = statistiques.getLosersNset().get(statistiques.getLosersNset().size() - 1);
                                            tableModel2.addRow(new Object[]{"Match 1",
                                            perdantsFinal.get(0).equipe1.getName(),
                                            perdantsFinal.get(0).getScore1(),
                                            "vs",
                                            perdantsFinal.get(0).equipe2.getName(),
                                            perdantsFinal.get(0).getScore2(),
                                            perdantsFinal.get(0).getVainqueur().getName()
                                        });
                                            tableModel2.addRow(new Object[]{"Vainqueur 2", perdantsFinal.get(0).vainqueur.getName()});

                                            tableModel2.addRow(new Object[]{"", "","", "FINAL","", "", ""});

                                            MatchNSet matchFinal = matchsGagnants.get(matchsGagnants.size() - 1).get(matchsGagnants.get(matchsGagnants.size() - 1).size() - 1);

                                            tableModel2.addRow(new Object[]{
                                                "Match final",
                                                matchFinal.equipe1.getName(),
                                                matchFinal.getScore1(),
                                                "vs",
                                                matchFinal.equipe2.getName(),
                                                matchFinal.getScore2(),
                                                matchFinal.vainqueur.getName(),
                                            });
                                            tableModel2.addRow(new Object[]{"", "","", "","", "", ""});
                                            tableModel2.addRow(new Object[]{"Vainqueur du tournoi", matchFinal.vainqueur.getName()});
                                        }
                                    } 
                                }
                            }

                        /**
                        * Affiche les résultats de la phase de groupe dans une table.
                        *
                        * @param tournoi L'instance de la phase de groupe à afficher.
                        */
                        public void afficherResultatsPhaseDeGroupe(PhaseDeGroupe tournoi) {
                            // Récupération des statistiques de la phase de groupe
                            PhaseDeGroupeStats stats = tournoi.getStatsGroupes();
                            ArrayList<ArrayList<Equipe>> lesgroupes = stats.getGroupes();
                            ArrayList<ArrayList<Integer>> lespoints = stats.getPoints();
                            ArrayList<ArrayList<Match>> lesmatchs = stats.getMatches();
                        
                            // Caractère pour identifier les groupes (A, B, C, ...)
                            char groupeIdentifier = 'A';

                            // Ajout du titre pour la phase de groupe dans la table
                            tableModel4.addRow(new Object[]{"", "", "", "Phase de poule"});

                            // Parcours des groupes
                            for (int p = 0; p < lesgroupes.size(); p++) {
                                // Récupération des équipes, des points et des matchs du groupe
                                ArrayList<Equipe> e = lesgroupes.get(p);
                                ArrayList<Integer> t = lespoints.get(p);
                                ArrayList<Match> m = lesmatchs.get(p);

                                // Ajout du titre pour le groupe dans la table
                                tableModel4.addRow(new Object[]{"", "", "", "Groupe " + groupeIdentifier});

                                // Indice pour parcourir les points des équipes
                                int i = 0;

                                // Parcours des équipes du groupe
                                for (Equipe equipe : e) {
                                    // Ajout des informations sur l'équipe et ses matchs joués dans la table
                                    tableModel4.addRow(new Object[]{equipe.getName(), t.get(i), "", "Matchs Joués"});

                                    // Parcours des matchs
                                    for (Match match : m) {
                                        // Vérification si l'équipe participe au match
                                        if (equipe == match.equipe1 || equipe == match.equipe2) {
                                            // Ajout des informations du match dans la table
                                            tableModel4.addRow(new Object[]{
                                                "",
                                                "",
                                                match.equipe1.getName(),
                                                "vs",
                                                match.equipe2.getName(),
                                                match.vainqueur.getName(),
                                            });
                                        }
                                    }
                                    i++;
                                }

                                // Ajout des équipes qualifiées (s'il y en a) dans la table
                                tableModel4.addRow(new Object[]{"", "", "", "Equipes Qualifiées " + "Groupe " + groupeIdentifier});
                                if (e.size() > 1) {
                                    tableModel4.addRow(new Object[]{"", "", "", e.get(0).getName()});
                                    tableModel4.addRow(new Object[]{"", "", "", e.get(1).getName()});
                                } else {
                                    tableModel4.addRow(new Object[]{"", "", "", e.get(0).getName()});
                                }

                                // Passage au groupe suivant
                                groupeIdentifier++;
                            }
                        }


                        /**
                        * Affiche les résultats du tournoi en format Round Robin dans une table.
                        *
                        * @param tournoi L'instance du tournoi Round Robin à afficher.
                        */
                        public void afficherResultatsRoundRobin(RoundRobin tournoi) {
                            if (nombreSets == 1) {
                                // Récupération des statistiques pour les matchs avec un seul set
                                RoundRobinStats stats = tournoi.getStats();
                                ArrayList<Integer> pointsMatchs = stats.getList();
                                ArrayList<Match> matchsJoues = stats.getMatches().get(0);

                                int i = 0;
                                // Parcours des équipes
                                for (Equipe equipe : lesEquipes) {
                                    // Ajout des points et du label "Matchs Joués" dans la table
                                    tableModel4.addRow(new Object[]{equipe.getName(), pointsMatchs.get(i), "", "Matchs Joués"});

                                    // Parcours des matchs joués par chaque équipe
                                    for (Match match : matchsJoues) {
                                        // Vérification si l'équipe participe au match
                                        if (match.equipe1 == equipe || match.equipe2 == equipe) {
                                            // Ajout des informations du match dans la table
                                            tableModel4.addRow(new Object[]{
                                                "", "", match.equipe1.getName(), "vs", match.equipe2.getName(), match.vainqueur.getName(),
                                            });
                                        }
                                    }
                                    i++;
                                }   

                                // Détermination de l'équipe vainqueur de la ronde
                                int p = Collections.max(pointsMatchs);
                                int indiceVainqueur = pointsMatchs.indexOf(p);
                                // Ajout d'une ligne vide et de l'équipe vainqueur dans la table
                                tableModel4.addRow(new Object[]{"", "", ""});
                                tableModel4.addRow(new Object[]{"Vainqueur de la Ronde", lesEquipes.get(indiceVainqueur).getName()});

                            } else {
                                // Récupération des statistiques pour les matchs avec plusieurs sets
                                RoundRobinStatsNset stats = tournoi.getStatsNset();
                                ArrayList<Integer> pointsMatchs = stats.getList();
                                ArrayList<MatchNSet> matchsJoues = stats.getMatchesNset().get(0);

                                int i = 0;
                                // Parcours des équipes
                                for (Equipe equipe : lesEquipes) {
                                    // Ajout des points et du label "Matchs Joués" dans la table
                                    tableModel5.addRow(new Object[]{equipe.getName(), pointsMatchs.get(i), "", "", "Matchs Joués"});

                                    // Parcours des matchs joués par chaque équipe
                                    for (MatchNSet match : matchsJoues) {
                                        // Vérification si l'équipe participe au match
                                        if (match.equipe1 == equipe || match.equipe2 == equipe) {
                                            // Ajout des informations du match dans la table
                                            tableModel5.addRow(new Object[]{
                                                "", "", match.equipe1.getName(), match.getScore1(), "vs",
                                                match.equipe2.getName(), match.getScore2(), match.vainqueur.getName(),
                                            });
                                        }
                                    }
                                    i++;
                                }

                                // Détermination de l'équipe vainqueur de la ronde
                                int p = Collections.max(pointsMatchs);
                                int indiceVainqueur = pointsMatchs.indexOf(p);
                                // Ajout d'une ligne vide et de l'équipe vainqueur dans la table
                                tableModel5.addRow(new Object[]{"", "", ""});
                                tableModel5.addRow(new Object[]{"Vainqueur de la Ronde", lesEquipes.get(indiceVainqueur).getName()});
                            }
                        }

                        /**
                        * Gère les actions liées aux événements d'interface utilisateur.
                        *
                        * @param e L'événement d'action.
                        */
                        public void actionPerformed(ActionEvent e) {
                            switch (e.getActionCommand()) {
                                case "Retour au menu d'ajout":
                                    // Réinitialise les données et masque la fenêtre actuelle
                                    lesEquipes.clear();
                                    tournoiChoisi = " ";
                                    nombreSets = 0;
                                    this.setVisible(false);
                                    FenetreTournoi fen = new FenetreTournoi();
                                    break;

                                case "Afficher les résultats":
                                    if (boutonLancerAppuye) {
                                        this.boutonLancerAppuye = false;
                                        if (ELIMINATION_DIRECTE.equals(tournoiChoisi)) {
                                            EliminationDirecte tournoi = new EliminationDirecte(lesEquipes, nombreSets);
                                            ArrayList<Equipe> es = new ArrayList<>();
                                            es = tournoi.run();
                                            afficherResultatsEliminationDirecte(tournoi);
                                        } else if (DOUBLE_ELIMINATION.equals(tournoiChoisi)) {
                                            DoubleElimination tournoi = new DoubleElimination(lesEquipes, nombreSets);
                                            ArrayList<Equipe> es = new ArrayList<>();
                                            es = tournoi.run();
                                            afficherResultatsDoubleElimination(tournoi);
                                        } else if (PHASE_ROUND_ROBIN.equals(tournoiChoisi)) {
                                            RoundRobin tournoi = new RoundRobin(lesEquipes, nombreSets);
                                            ArrayList<Equipe> es = new ArrayList<>();
                                            es = tournoi.run();
                                            afficherResultatsRoundRobin(tournoi);
                                        } else if (PHASE_GROUPE_ELIMINATION.equals(tournoiChoisi)) {
                                            PhaseDeGroupe tournoi = new PhaseDeGroupe(lesEquipes);
                                            ArrayList<Equipe> equipesSortantes = tournoi.run();
                                            afficherResultatsPhaseDeGroupe(tournoi);
                                            EliminationDirecte tournoi2 = new EliminationDirecte(equipesSortantes, nombreSets);
                                            ArrayList<Equipe> es = tournoi2.run();
                                            afficherResultatsEliminationDirecte(tournoi2);
                                        } else if (PHASE_GROUPE_DOUBLE_ELIMINATION.equals(tournoiChoisi)) {
                                            PhaseDeGroupe tournoi = new PhaseDeGroupe(lesEquipes);
                                            lesEquipes = tournoi.run();
                                            afficherResultatsPhaseDeGroupe(tournoi);
                                            DoubleElimination tournoi3 = new DoubleElimination(lesEquipes, nombreSets);
                                            ArrayList<Equipe> es = tournoi3.run();
                                            afficherResultatsDoubleElimination(tournoi3);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, " Aucun tournoi n'est en cours. Veillez lancer votre tournoi pour commencer.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                    break;

                                case "Lancer le tournoi":
                                    // Active le bouton lancer et crée une nouvelle instance de la classe MessageAnimation
                                    this.boutonLancerAppuye = true;
                                    new MessageAnimation();
                                    break;

                                case "Quitter le tournoi":
                                dispose();
                                }
                            }

                        /**
                        * Initialise l'interface avec les données spécifiées.
                        *
                        * @param tournoi Le type de tournoi.
                        * @param lesEquipes La liste des équipes participantes.
                        * @param nSets Le nombre de sets dans les matchs.
                        */
                        public void initialiserAvecDonnees(String tournoi, ArrayList<Equipe> lesEquipes, int nSets) {
                            this.tournoiChoisi = tournoi;
                            this.lesEquipes = lesEquipes;
                            this.nombreSets = nSets;

                            if (ELIMINATION_DIRECTE.equals(tournoiChoisi) || DOUBLE_ELIMINATION.equals(tournoiChoisi)) {
                                if (this.nombreSets == 1) {
                                    pan.add(new JScrollPane(resultTable), BorderLayout.CENTER);
                                } else {
                                    pan.add(new JScrollPane(resultTable2), BorderLayout.CENTER);
                                }
                            } else if (PHASE_GROUPE_ELIMINATION.equals(tournoiChoisi) || PHASE_GROUPE_DOUBLE_ELIMINATION.equals(tournoiChoisi)) {
                                if (this.nombreSets == 1) {
                                    JPanel tableauPanel = new JPanel(new GridLayout(2, 1));
                                    tableauPanel.add(new JScrollPane(resultTable4));
                                    tableauPanel.add(new JScrollPane(resultTable));
                                    pan.add(tableauPanel, BorderLayout.CENTER);
                                } else {
                                    JPanel tableauPanel1 = new JPanel(new GridLayout(2, 1));
                                    tableauPanel1.add(new JScrollPane(resultTable4));
                                    tableauPanel1.add(new JScrollPane(resultTable2));
                                    pan.add(tableauPanel1, BorderLayout.CENTER);
                                }
                            } else if (PHASE_ROUND_ROBIN.equals(tournoiChoisi)) {
                                if (nombreSets == 1) {
                                    pan.add(new JScrollPane(resultTable4), BorderLayout.CENTER);
                                } else {
                                    pan.add(new JScrollPane(resultTable5), BorderLayout.CENTER);
                                }
                            }
                            pan.revalidate();
                        }       

                        /**
                        * Obtient le nombre de sets pour le tournoi.
                        *
                        * @return Le nombre de sets.
                        */
                        public int getNset(){
                            return this.nombreSets;
                        }

                        /**
                        * Obtient la liste des équipes sortantes du tournoi.
                        *
                        * @return La liste des équipes sortantes.
                        */
                        public ArrayList<Equipe> getequipesSortantes(){
                            return this.lesEquipes;
                        }
                        /**
                        * Obtient le type de tournoi choisi.
                        *
                        * @return Le type de tournoi choisi.
                        */
                        public String getTournoi(){
                            return this.tournoiChoisi;
                        }
                    }
