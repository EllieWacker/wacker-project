package edu.kirkwood.shared.email;

public class EmailThread extends Thread {
    private String toEmailAddress;
    private String subject;
    private String bodyHTML;
   // private String replyTo;
    private String errorMessage;

    public EmailThread(String toEmailAddress, String subject, String bodyHTML) {
        this.toEmailAddress = toEmailAddress;
        this.subject = subject;
        this.bodyHTML = bodyHTML;
       // this.replyTo = replyTo;
    }

    public void run() {
        errorMessage = edu.kirkwood.shared.email.AzureEmail.sendEmail(toEmailAddress, subject, bodyHTML);
        // TODO: Add a backup email service if an error occurs
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
