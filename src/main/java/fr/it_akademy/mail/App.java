package fr.it_akademy.mail;

import fr.it_akademy.mail.business.EmailEntity;
public class App {
    public static void main(String[] args) {
        //Point entrée application
        EmailEntity email = new EmailEntity();
        email.promptTo();
        email.promptSubject();
        email.promptMessage();
        //Ajouter une pièce jointe
        email.addAttach("/Users/admin/Pictures/boule-noel.jpg", "Boule de Noël");
        //Evoyer le mail
        email.send();
    }
}