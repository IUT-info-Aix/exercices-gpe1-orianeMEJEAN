package fr.amu.iut.exercice14;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

@SuppressWarnings("Duplicates")
public class MainPersonnes
{
    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    //private static IntegerBinding calculAgeMoyen;
    //private static IntegerBinding calculnbParisiens;

    public static void main(String[] args)
    {
        // Observation sur la propriété age
        lesPersonnes = new SimpleListProperty<>(FXCollections.observableArrayList(personne -> new Observable[]{personne.ageProperty(), personne.villeDeNaissanceProperty()}));
        ageMoyen = new SimpleIntegerProperty(0);
        nbParisiens = new SimpleIntegerProperty(0);

        // Bind de calculAgeMoyen
        IntegerBinding calculAgeMoyen = Bindings.createIntegerBinding(() -> {
            if (lesPersonnes.isEmpty())
            {
                return 0;
            }
            int totalAge = lesPersonnes.stream().mapToInt(Personne::getAge).sum();
            return totalAge / lesPersonnes.size();
        }, lesPersonnes, lesPersonnes.sizeProperty());

        IntegerBinding calculnbParisiens = Bindings.createIntegerBinding(() -> {
            return (int) lesPersonnes.stream()
                    .filter(personne -> "Paris".equals(personne.getVilleDeNaissance()))
                    .count();
        }, lesPersonnes);

        ageMoyen.bind(calculAgeMoyen);
        nbParisiens.bind(calculnbParisiens);

        question1();
        question2();
    }

    public static void question1()
    {
        System.out.println("1 - L'age moyen est de " + ageMoyen.getValue() + " ans");

        Personne Oriane = new Personne("Oriane", 20);
        lesPersonnes.add(Oriane);
        System.out.println("2 - L'age moyen est de " + ageMoyen.getValue() + " ans");

        Personne Jack = new Personne("Jack", 40);
        lesPersonnes.add(Jack);
        System.out.println("3 - L'age moyen est de " + ageMoyen.getValue() + " ans");

        Personne Lou = new Personne("Lou", 60);
        lesPersonnes.add(Lou);
        System.out.println("4 - L'age moyen est de " + ageMoyen.getValue() + " ans");

        Jack.setAge(100);
        System.out.println("5 - L'age moyen est de " + ageMoyen.getValue() + " ans");

        lesPersonnes.remove(Jack);
        System.out.println("6 - L'age moyen est de " + ageMoyen.getValue() + " ans");
    }

    public static void question2()
    {
        System.out.println("\nIl y a " + nbParisiens.getValue() + " parisiens");

        lesPersonnes.get(0).setVilleDeNaissance("Marseille");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");

        lesPersonnes.get(1).setVilleDeNaissance("Montpellier");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");

        for (Personne p : lesPersonnes)
        {
            p.setVilleDeNaissance("Paris");
        }
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
    }
}