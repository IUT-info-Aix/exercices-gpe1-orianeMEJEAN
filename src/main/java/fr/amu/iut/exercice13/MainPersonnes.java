package fr.amu.iut.exercice13;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes
{
    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args)
    {

        lesPersonnes = FXCollections.observableArrayList();

        unChangementListener = change ->
        {
            while (change.next()) {
                if (change.wasAdded())
                {
                    for (Personne p : change.getAddedSubList())
                    {
                        System.out.println("Ajout de : " + p.getNom());
                    }
                }
            }
        };
        lesPersonnes.addListener(unChangementListener);
        question1();
    }

    public static void question1()
    {
        Personne p1 = new Personne("Oriane/Blob", 18);
        Personne p2 = new Personne("Jack/Ichi  ", 25);
        Personne p3 = new Personne("Lou/Tilou", 18);
        lesPersonnes.add(p1);
        lesPersonnes.add(p2);
        lesPersonnes.add(p3);
    }

    public static void question2()
    {
        Personne p1 = new Personne("Oriane/Blob", 18);
        Personne p2 = new Personne("Jack/Ichi  ", 25);
        Personne p3 = new Personne("Lou/Tilou", 18);
        lesPersonnes.add(p1);
        lesPersonnes.add(p2);
        lesPersonnes.add(p3);
        lesPersonnes.remove(p1);
    }

    public static void question3()
    {
        Personne p1 = new Personne("Oriane", 18);
        Personne p2 = new Personne("Jack", 25);
        Personne p3 = new Personne("Lou", 18);
        lesPersonnes.add(p1);
        lesPersonnes.add(p2);
        lesPersonnes.add(p3);
        p1.setAge(19);
    }

    public static void question5()
    {
        Personne p1 = new Personne("Oriane", 18);
        Personne p2 = new Personne("Jack", 25);
        Personne p3 = new Personne("Lou", 18);
        lesPersonnes.addAll(p1, p2, p3);

        for (Personne p : lesPersonnes)
        {
            p.setAge(p.getAge() + 10);
        }
        lesPersonnes.removeAll(p1, p2);
    }
}