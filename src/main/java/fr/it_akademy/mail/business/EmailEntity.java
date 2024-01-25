package fr.it_akademy.mail.business;
//Import

import java.util.Scanner;

import org.apache.commons.mail.*;

public class EmailEntity {
    private String srv = "mail.seventeeninfo.fr";
    private int port = 587;
    private String from = "";
    private String passwd = "";

    private String subject;
    private String to;
    private String message;

    private EmailAttachment attachment;

    /**
     * Demander à l'utilisateur de saisir du texte
     * @param text Le texte à afficher avant la saisie
     * @return La saisie de l'utilisateur
     */
    public String prompt(String text){
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        return scanner.nextLine();
    }

    /**
     * Demander à l'utilisateur de saisir l'adresse email du destinataire
     */
    public void promptTo() {
        this.to = prompt("Destinataire : ");
    }

    /**
     * Demander a l'utilisateur de saisir le sujet
     */
    public void promptSubject() {
        this.subject = prompt("Sujet : ");
    }

    /**
     * Demander à l'utilisateur de saisir le message
     */
    public void promptMessage() {
        this.message = prompt("Message : ");
    }

    /**
     * Ajouter une pièce jointe
     * @param url
     * @param title
     */
    public void addAttach(String url, String title){
        this.attachment = new EmailAttachment();
        attachment.setPath(url);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(title);
        attachment.setName(title);
    }

    /**
     * Envoyer le mail en utilisant commons mail
     */
    public void send() {
        try {
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName(this.srv);
            email.setSmtpPort(this.port);
            email.setAuthenticator(new DefaultAuthenticator(this.from, this.passwd));
            email.setSSLOnConnect(false);
            email.setFrom(this.from);
            email.setSubject(this.subject);
            email.setMsg(this.message);
            email.addTo(this.to);
            //Ajouter une piece jointe
            if(this.attachment != null){
                email.attach(this.attachment);
            }
            email.send();
        } catch (Exception e) {
            throw new RuntimeException("Erreur");
        }
    }
}
