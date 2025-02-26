package edu.kirkwood.shared.email;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import edu.kirkwood.shared.Config;
import edu.kirkwood.shared.Helpers;
import edu.kirkwood.shared.Validators;
import io.github.cdimascio.dotenv.Dotenv;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import org.jsoup.Jsoup;

public class AzureEmail {
    public static EmailClient getEmailClient() {
        String connectionString = Config.getEnv("AZURE_EMAIL_CONNECTION");

        EmailClient emailClient = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        return emailClient;
    }
    public static String sendEmail(String toEmailAddress, String subject, String bodyHTML) {
        EmailClient emailClient = getEmailClient();
        EmailAddress toAddress = new EmailAddress(toEmailAddress);
        EmailAddress replyToAddress = new EmailAddress(Config.getEnv("ADMIN_EMAIL"));
        String body = Helpers.html2text(bodyHTML);
        EmailMessage emailMessage = new EmailMessage()
                .setSenderAddress(Config.getEnv("AZURE_EMAIL_FROM"))
                .setToRecipients(toAddress)
                .setSubject(subject)
                .setReplyTo(replyToAddress)
                .setBodyPlainText(body)
                .setBodyHtml(bodyHTML);
        SyncPoller<EmailSendResult, EmailSendResult> poller = null;
        try {
            poller = emailClient.beginSend(emailMessage, null);
        } catch(RuntimeException e) {
            return e.getMessage();
        }
        PollResponse<EmailSendResult> result = poller.waitForCompletion();

        return "";
    }

    public static void main(String[] args) {
        // Pretend this is your doPost method in a servlet
        // Get all parameters
        String toEmailAddress = Dotenv.load().get("ADMIN_EMAIL"); // Use your own email address
        String subject = "Testing";
        String bodyHTML = "<h2>This is a test email</h2><p>Testing, Testing, Testing</p>";
        String replyTo = "test@test.com";
        // Set the parameters as attribute

        // validate inputs
        boolean error =false;
        if(toEmailAddress == null || !Validators.isValidEmail(toEmailAddress)) {
            // set error attribute
            System.out.println("Invalid email address: " + toEmailAddress);
            error = true;
        }

        if(subject == null || subject.isEmpty()) {
            // set error attribute
            System.out.println("Subject is required");
            error = true;
        }

        if(bodyHTML == null || bodyHTML.isEmpty()) {
            // set error attribute
            System.out.println("Body is required");
            error = true;
        }
        if(replyTo == null || replyTo.isEmpty()) {
            // set error attribute
            System.out.println("Reply to is required");
            error = true;
        }


        if(!error) {
            EmailThread emailThread1 = new EmailThread(toEmailAddress, subject, bodyHTML);
            emailThread1.start();
            EmailThread emailThread2 = new EmailThread("ejwacker15@gmail.com", subject, bodyHTML);
            emailThread2.start();
            try {
                emailThread1.join();
                emailThread2.join();
            } catch (InterruptedException e) {

            }
            String errorMessage1 = emailThread1.getErrorMessage();
            String errorMessage2 = emailThread2.getErrorMessage();
            if (errorMessage1.isEmpty() && errorMessage2.isEmpty()) {
                // Set a success attribute
                System.out.println("Message sent to " + toEmailAddress);
            } else {
                // Set an error attribute
                System.out.println("Message not sent to " + toEmailAddress + " - " + errorMessage1);
            }
        }
    }
}
