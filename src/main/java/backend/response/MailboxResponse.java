package backend.response;

import java.util.ArrayList;
import java.util.List;

import backend.model.Mailbox;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 public class MailboxResponse {
 
     public String status;
     public String message;
     public ArrayList<Mailbox> data;

     public MailboxResponse (String status, String message, List<Mailbox> mailbox) {
         this.status = status;
         this.message = message;
         this.data = new ArrayList<Mailbox>();

         for (Mailbox item:mailbox) {
             this.data.add(item);
         }
     }

     public MailboxResponse (String status, String message, Mailbox mailbox) {
         this.status = status;
         this.message = message;
         this.data = new ArrayList<Mailbox>();

         if (mailbox != null) {
             this.data.add(mailbox);
         }
     }

     public MailboxResponse (String status, String message) {
         this.status = status;
         this.message = message;
     }
 }