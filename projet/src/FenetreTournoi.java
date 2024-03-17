        import javax.swing.*;
        import javax.swing.text.*;

        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.KeyAdapter;
        import java.awt.event.KeyEvent;
        import java.util.ArrayList;

        /**
         * @author Tous les membres du groupes.
         * Classe représentant la fenêtre de gestion d'un tournoi.
         */
        public class FenetreTournoi extends JFrame implements ActionListener {

            // Déclaration des composants de la fenêtre
            private JPanel pan;
            private JLabel labAccueil;
            private JTextField nom, performance;
            private JButton boutonValider, boutonAjouterEquipe, boutonModifierEquipe, boutonSuivant,
                    boutonAjoutModif, boutonSupprimer;
            private JTextArea zoneResultats;
            private JComboBox<String> phaseComboBox;
            private ArrayList<Equipe> lesEquipes = new ArrayList<>();
            private int index, e;
            private int performanceEquipe, nombreSets;
            private String nomEquipe, perf;
            private String tournoiChoisi;
            private Boolean boutonModifierEquipeAppuye, boutonValiderBoolean, ajoutModif;

            /**
             * Constructeur de la classe FenetreTournoi.
             */
            public FenetreTournoi() {
                // Initialisation de la fenêtre
                this.setTitle("Simulation de tournoi");
                this.setSize(900, 500);
                this.index = 0;
                this.boutonModifierEquipeAppuye = false;
                this.boutonValiderBoolean = false;
                this.ajoutModif = true;
                this.tournoiChoisi = " ";

                pan = new JPanel();

                // Initialisation des polices
                Font policeHuge = new Font("Fipps", Font.PLAIN, 20);
                Font police = new Font("Serif", Font.BOLD, 30);
                Font police_1 = new Font("Serif", Font.BOLD, 15);

                // Initialisation des composants
                labAccueil = new JLabel();
                labAccueil.setFont(police);
                labAccueil.setText("Bienvenu dans la partie d'ajout des caractéristiques de votre tournoi");

                // Panel de titre
                JPanel titlePanel = new JPanel();
                titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                titlePanel.add(labAccueil);

                // Boutons

                boutonValider = new JButton("Valider les ajouts");
                boutonValider.addActionListener(this);
                boutonValider.setFont(policeHuge);

                boutonAjouterEquipe = new JButton("Ajouter une équipe");
                boutonAjouterEquipe.addActionListener(this);
                boutonAjouterEquipe.setFont(policeHuge);

                boutonModifierEquipe = new JButton("Modifier une équipe");
                boutonModifierEquipe.addActionListener(this);
                boutonModifierEquipe.setFont(policeHuge);

                boutonAjoutModif = new JButton("Ajouter les modifications");
                boutonAjoutModif.addActionListener(this);
                boutonAjoutModif.setFont(policeHuge);

                boutonSupprimer = new JButton("Supprimer une équipe");
                boutonSupprimer.addActionListener(this);
                boutonSupprimer.setFont(policeHuge);

                boutonSuivant = new JButton("Aller au tournoi");
                boutonSuivant.addActionListener(this);
                boutonSuivant.setFont(policeHuge);

                // Formulaire d'ajout
                JPanel formPanel = new JPanel(new FlowLayout());

                // Zone de texte pour le nom d'équipe
                formPanel.add(new JLabel("Nom de votre équipes:"));
                nom = new JTextField(20);
                formPanel.add(nom);
                nom.setFont(police_1);
                formPanel.add(new JScrollPane(nom));

                /**
                * Applique un filtre de document au champ de texte 'nom' pour capitaliser automatiquement la première lettre saisie.
                */
                ((AbstractDocument) nom.getDocument()).setDocumentFilter(new DocumentFilter() {
                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    // Vérifie si la première lettre est en train d'être ajoutée au début du champ texte
                    if (offset == 0 && length == 0 && text.length() > 0) {
                        // Capitalise la première lettre
                        text = text.substring(0, 1).toUpperCase() + text.substring(1);
                    }
                    // Appelle la méthode de remplacement du filtre de document parent
                    super.replace(fb, offset, length, text, attrs);
                    }
                });


                // Validation du caractère alphabétique pour le nom
                nom.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!(Character.isLetter(c) || c == ' ') && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                            e.consume();
                        }
                    }
                });

                // Zone de texte pour la performance
                formPanel.add(new JLabel("Sa performance:"));
                performance = new JTextField(20);
                formPanel.add(performance);
                performance.setFont(police_1);
                formPanel.add(new JScrollPane(performance));

                // Validation du caractère numérique pour la performance
                performance.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                            e.consume();
                        }
                    }
                });

                // Ajout de phases à la JComboBox
                String[] phases = {
                    "Élimination Directe",
                    "Double Élimination",
                    "Round Robin",
                    "Phase de Groupe suivi d'Élimination Directe",
                    "Phase de Groupe suivi de Double Élimination"
                };        
                phaseComboBox = new JComboBox<>(phases);
                // Ajout de l'écouteur d'événements à la JComboBox pour lire le tournoi choisi
                phaseComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tournoiChoisi = (String) phaseComboBox.getSelectedItem();
                    }
                });

                // Panel de formulaire
                formPanel.add(new JLabel("Phase de tournoi:"));
                formPanel.add(new JScrollPane(phaseComboBox));

                // Zone de résultats
                zoneResultats = new JTextArea("AFFICHAGE DES EQUIPES CONSTRUITS A BASE DE VOS INFORMATIONS ENTREES" + "\n", 10, 10);
                zoneResultats.setEditable(false); // Pour éviter la modification manuelle des résultats
                zoneResultats.setFont(police_1);
                JScrollPane scrollPane = new JScrollPane(zoneResultats);

                // Configuration du layout
                pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
                pan.add(titlePanel, BorderLayout.NORTH);
                pan.add(formPanel, BorderLayout.CENTER);
                pan.add(scrollPane, BorderLayout.CENTER);

                // Panel de boutons
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.add(boutonAjouterEquipe);
                buttonPanel.add(boutonModifierEquipe);
                buttonPanel.add(boutonAjoutModif);
                buttonPanel.add(boutonSupprimer);
                buttonPanel.add(boutonValider);
                buttonPanel.add(boutonSuivant);
                pan.add(buttonPanel, BorderLayout.SOUTH);

                // Configuration de la fenêtre
                this.getContentPane().add(pan);
                this.setVisible(true);
                // Ferme la fenetre dès que l'utilisateur clique sur la croix.
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
            }

            /**
             * Gère les événements d'action déclenchés par divers boutons.
             *
             * @param f ActionEvent représentant l'événement déclenché.
             */
        public void actionPerformed(ActionEvent f) {
            if (f.getSource() == boutonValider) {
            // Code permettant la validation des paramètres du tournoi fournis par l'utilisateur.
            if (lesEquipes.size() != 0) {
                if (!tournoiChoisi.trim().isEmpty()) {  // Vérifie si le choix du tournoi n'est pas vide ou composé uniquement d'espaces
                    zoneResultats.setText("");
        
                    // Boucle do-while pour demander à l'utilisateur de saisir le nombre de sets jusqu'à ce qu'une valeur valide soit entrée
                    do {
                        String setsInput = JOptionPane.showInputDialog(this, "Veuillez saisir le nombre de sets pour le tournoi:");
        
                        try {
                            nombreSets = Integer.parseInt(setsInput);

                            if(nombreSets< 0 || nombreSets %2 ==0){
                                throw new NumberFormatException("Veuillez saisir un nombre entier valide pour le nombre de sets. ce nombre doit être supérieur à 0 et doit être impair");
                            }
        
                                // Utilisation de StringBuilder pour construire la chaîne de texte
                                StringBuilder resultatsText = new StringBuilder();

                                // Affiche les équipes ajoutées avec leur nom et performance
                                resultatsText.append("Les équipes ajoutées sont:\n");
                                for (int j = 0; j < lesEquipes.size(); j++) {
                                    resultatsText.append("Equipe ").append(j + 1).append(": ")
                                    .append(lesEquipes.get(j).getName()).append("\n")
                                    .append("Performance: ").append(lesEquipes.get(j).getPerformance()).append("\n\n");
                                }

                                // Affiche les détails du tournoi
                                resultatsText.append("Votre tournoi se déroulera suivant la phase de ")
                                .append(tournoiChoisi).append(" avec ").append(nombreSets).append(" sets\n");

                                // Affiche le résultat dans le TextArea
                                zoneResultats.append(resultatsText.toString());
                                boutonValiderBoolean = true;  // Active le drapeau de validation
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Veuillez saisir un nombre entier valide pour le nombre de sets. ce nombre doit être supérieur à 0 et doit être impair", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (nombreSets <= 0 || nombreSets%2==0);  // Continue tant que le nombre de sets est inférieur ou égal à zéro
                } else {
                    JOptionPane.showMessageDialog(this, "Veuillez d'abord sélectionner une phase pour votre tournoi dans la liste!!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez ajouter des équipes!!");
            }
        }

        if (f.getSource() == boutonAjouterEquipe) {
            boutonValiderBoolean = false;
            if(ajoutModif){
                try {
                // Code permettant d'ajouter une équipe.
                nomEquipe = nom.getText();
                perf = performance.getText();

                // Vérifie si les champs du nom de l'équipe ou de la performance sont vides
                if (nomEquipe.isEmpty() || perf.isEmpty()) {
                    throw new IllegalArgumentException("Veuillez remplir tous les champs.");
                }

                performanceEquipe = Integer.parseInt(perf);

                // Vérifie si la performance de l'équipe est dans la plage valide (entre 0 et 10)
                if (performanceEquipe <= 0 || performanceEquipe > 10) {
                    throw new NumberFormatException("La performance de l'équipe doit être entre 1 et 10.");
                }

                // Vérifie si l'équipe avec le même nom n'existe pas déjà
                if (!equipeExistante(nomEquipe)) {
                    Equipe equipe = new Equipe(nomEquipe, performanceEquipe);
                    lesEquipes.add(equipe);
                    index++;
                    zoneResultats.append("Equipe" + " " + index + ":" + " " + nomEquipe + " " + "avec pour performance : " + performanceEquipe + "\n");
                    JOptionPane.showMessageDialog(this, "Ajout effectué!");
                } else {
                    JOptionPane.showMessageDialog(this, "L'équipe que vous essayez de saisir est déjà enregistrée. Veuillez en saisir une autre!!");
                }

                // Réinitialise les champs de texte
                nom.setText("");
                performance.setText("");

            } catch (NumberFormatException ex) {
                // Gère l'exception de format de la performance.
                JOptionPane.showMessageDialog(this, "Veuillez saisir un nombre correct de performance pour votre équipe compris entre 1 et 10.", "Attention", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                // Gère l'exception si l'utilisateur essaie de rajouter sans rien entrer dans les champs.
                JOptionPane.showMessageDialog(this, "Les champs sont vides, veuillez les remplir!!", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Veillez ajouter vos modification!!");
        }
            }


        if (f.getSource() == boutonModifierEquipe) {
            // Code permettant de modifier une équipe fourni par l'utilisateur.
            if (lesEquipes.size() != 0) {
                ajoutModif = false;

                // Afficher la boîte de dialogue pour choisir une équipe
                String[] equipeNames = new String[lesEquipes.size()];
                for (int i = 0; i < lesEquipes.size(); i++) {
                    equipeNames[i] = lesEquipes.get(i).getName();
                }

                String equipeSelectionnee = (String) JOptionPane.showInputDialog(this, "Choisissez une équipe à modifier:",
                        "Équipes", JOptionPane.QUESTION_MESSAGE, null, equipeNames, equipeNames[0]);

                if (equipeSelectionnee != null) {
                    // Récupère l'index de l'équipe sélectionnée
                    e = trouverEquipe(equipeSelectionnee);
                    nom.setText(lesEquipes.get(e).getName());
                    performance.setText(String.valueOf(lesEquipes.get(e).getPerformance()));

                    // Vérifie si le bouton "modifier" a été utilisé avant ce bouton
                    boutonModifierEquipeAppuye = true;
                    JOptionPane.showMessageDialog(this, "Veuillez procéder à vos modifications de l'ajout précédent dans les zones de texte, merci!!");
                } else {
                    JOptionPane.showMessageDialog(this, "Aucune équipe modifiée");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Aucune équipe à modifier", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }


        if (f.getSource() == boutonAjoutModif) {
            ajoutModif = true;
            try {
                // Vérifie si le bouton "Modifier une équipe" a été utilisé avant d'appuyer sur "Ajout/Modif"
                if (!boutonModifierEquipeAppuye) {
                    throw new IllegalArgumentException("Veuillez d'abord utiliser le bouton 'Modifier une équipe'.");
                }

                // Réinitialise le drapeau après avoir vérifié
                boutonModifierEquipeAppuye = false;

                // Affiche un message d'en-tête dans la zone de résultats
                zoneResultats.setText("AFFICHAGE DES ÉQUIPES CONSTRUITES À BASE DE VOS INFORMATIONS ENTRÉES" + "\n");

                // Récupère les informations de l'équipe à modifier
                nomEquipe = nom.getText();
                perf = performance.getText();
                performanceEquipe = Integer.parseInt(perf);

                // Vérifie si la performance de l'équipe est dans la plage valide (entre 0 et 10)
                if (performanceEquipe <= 0 || performanceEquipe > 10) {
                    throw new NumberFormatException("La performance de l'équipe doit être comprise entre 0 et 10.");
                }

                // Crée une nouvelle équipe avec les informations mises à jour
                Equipe equipe = new Equipe(nomEquipe, performanceEquipe);

                // Remplace l'équipe existante par la nouvelle équipe dans la liste des équipes
                lesEquipes.set(e, equipe);

                // Réinitialise les champs de texte
                nom.setText("");
                performance.setText("");

                // Affiche un message de confirmation de la modification
                JOptionPane.showMessageDialog(this, "Modification effectuée avec succès.");

                // Affiche les détails mises à jour de toutes les équipes
                for (int l = 0; l < lesEquipes.size(); l++) {
                    zoneResultats.append("Equipe " + (l + 1) + ": " + lesEquipes.get(l).getName() + " avec une performance de : " + lesEquipes.get(l).getPerformance() + "\n");
                }
            } catch (NumberFormatException ex) {
                // Gère l'exception de format de la performance
                JOptionPane.showMessageDialog(this, "Veuillez saisir une performance valide pour votre équipe (entre 0 et 10).", "Attention", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException ex) {
                // Gère l'exception générique avec le message personnalisé
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }


        if (f.getSource() == boutonSupprimer) {
            // Vérifie s'il y a des équipes à supprimer
            if (lesEquipes.size() != 0) {
                // Affiche un message dans la zone de résultats
                zoneResultats.setText("AFFICHAGE DES ÉQUIPES CONSTRUITS À BASE DE VOS INFORMATIONS ENTRÉES" + "\n");

                // Crée un tableau de noms d'équipes pour la boîte de dialogue
                String[] equipeNames = new String[lesEquipes.size()];
                for (int i = 0; i < lesEquipes.size(); i++) {
                    equipeNames[i] = lesEquipes.get(i).getName();
                }

                // Affiche une boîte de dialogue pour choisir l'équipe à supprimer
                String equipeAsupprimee = (String) JOptionPane.showInputDialog(this, "Choisissez une équipe à supprimer:",
                        "Équipes", JOptionPane.QUESTION_MESSAGE, null, equipeNames, equipeNames[0]);

                // Vérifie si l'utilisateur a fait un choix
                if (equipeAsupprimee != null) {
                    // Trouve l'index de l'équipe à supprimer
                    int k = trouverEquipe(equipeAsupprimee);
                    lesEquipes.remove(k);
                    index = index - 1;

                    // Affiche un message de confirmation de la suppression
                    JOptionPane.showMessageDialog(this, "Suppression effectuée avec succès.");

                    // Vérifie s'il reste des équipes après la suppression
                    if (lesEquipes.size() != 0) {
                        // Affiche les détails des équipes restantes
                        for (int l = 0; l < lesEquipes.size(); l++) {
                            zoneResultats.append("Equipe" + " " + (l + 1) + ":" + " " + lesEquipes.get(l).getName() + " " + "avec pour performance :" + lesEquipes.get(l).getPerformance() + "\n");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Votre liste d'équipe est vide", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Votre liste d'équipe est vide", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }


        if (f.getSource() == boutonSuivant) {
            // Vérifie si le bouton "Valider les ajouts" a été utilisé avant d'appuyer sur "Suivant"
            if (boutonValiderBoolean) {
                boutonValiderBoolean = false;

                // Crée une instance de la classe SimulationTournoi
                SimulationTournoi sim = new SimulationTournoi();

                // Initialise la simulation avec les données du tournoi
                sim.initialiserAvecDonnees(getTournoiChoisi(), getLesEquipes(), getNombreSets());

                // Ajuste la taille de la fenêtre de simulation
                sim.pack();

                dispose();

            } else {
                // Affiche un message d'erreur si le bouton "Valider les ajouts" n'a pas été utilisé
                JOptionPane.showMessageDialog(this, "Veuillez d'abord utiliser le bouton 'Valider les ajouts' pour vérifier l'ensemble de vos ajouts.");
            }
        }

        }

            /**
            * Recherche une équipe par son nom dans la liste des équipes.
            *
            * @param nom Le nom de l'équipe à rechercher.
            * @return L'indice de l'équipe dans la liste si trouvée, sinon retourne -1.
            */
            public int trouverEquipe(String nom) {
                // Parcourt la liste des équipes
                for (int j = 0; j < lesEquipes.size(); j++) {
                    // Vérifie si le nom de l'équipe à l'indice j correspond au nom recherché
                    if (lesEquipes.get(j).getName().equals(nom)) {
                        // Retourne l'indice de l'équipe si elle est trouvée
                        return j;
                    }
                }
                // Retourne -1 si l'équipe n'est pas trouvée
                return -1;
            }


            /**
            * Vérifie si une équipe avec le nom spécifié existe déjà dans la liste des équipes.
            *
            * @param nom Le nom de l'équipe à rechercher.
            * @return True si l'équipe existe, sinon False.
            */
            public Boolean equipeExistante(String nom) {
            for (int i = 0; i < lesEquipes.size(); i++) {
            if (lesEquipes.get(i).getName().equals(nom)) {
                return true;
                }
            }
                return false;
            }

            /**
            * Récupère le tournoi choisi.
            *
            * @return Le nom du tournoi choisi.
            */
            public String getTournoiChoisi() {
                return this.tournoiChoisi;
            }

            /**
            * Récupère la liste des équipes.
            *
            * @return La liste des équipes.
            */
            public ArrayList<Equipe> getLesEquipes() {
                return this.lesEquipes;
            }

            /**
            * Récupère le nombre de sets pour le tournoi.
            *
            * @return Le nombre de sets.
            */
            public int getNombreSets() {
                return this.nombreSets;
            }
        
        }