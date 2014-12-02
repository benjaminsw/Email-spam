// Main driver program

package bayes;

import java.io.*;
import a2z.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

public class Bayesian {
	public static Message[] getBaysian(Message[] emails, int totalEmailsToBeRead) {
		try {
			
			// Create a new SpamFilter Object
			SpamFilter filter = new SpamFilter();
			
			// Train spam with a file of spam e-mails
			filter.trainSpam("C:\\Users\\thymemine\\Documents\\NetBeansProjects\\Baysian\\src\\spam.txt");
			// Train spam with a file of regular e-mails
			filter.trainGood("C:\\Users\\thymemine\\Documents\\NetBeansProjects\\Baysian\\src\\good.txt");
			// We are finished adding words so finalize the results
			filter.finalizeTraining();
			
			
			for (int i = 0; i < emails.length; i++) {
				// Read in a text file
				//A2ZFileReader fr = new A2ZFileReader("C:\\Users\\thymemine\\Documents\\NetBeansProjects\\Baysian\\src\\messages\\mail" + i + ".txt");
				//String stuff = fr.getContent();
                            
                           
                            // Next, we read the email message. 
                             String [] emailMsg = new String[4];
                            Object content = emails[i].getContent();
                            if(content instanceof String) {
                                 // It's a string, simply just set the variable
                                emailMsg[3] = (String)content;
                            } else if (content instanceof Multipart) {
                                // A multi-part is a message with multiple attachments
                                Multipart mp = (Multipart)content;
                                // WE look to see whether any of the parts of the email
                                // message are "Strings", and if they are, we'll keep them. 
                                // If the parts of the email are "images", or "pdfs", or
                                // any other non-String item, then we'll just ignore them
                                // 
                                // We leave any complex email handling code up to 
                                // your descretion ;)
                                for(int a = 0; a < mp.getCount();a++) {
                                    Object cont = mp.getBodyPart(a).getContent();
                                    // This is *nasty* String concatenation, and 
                                    // a bad example of how not to do it.   The 
                                    // better way is to use a StringBuffer
                                    if(cont instanceof String) {
                                        if(emailMsg[3]!=null) emailMsg[3] += ";"+emailMsg[3];
                                            else emailMsg[3] = cont.toString();
                                    }
                                }
                            } else if (content instanceof InputStream) {
                                System.out.println(" -> Unusual, the content type of the email could not be established. Email-date="+emailMsg[2]);
                                // If the content type cannot be determined, an inputsteam
                                // is returned.  Lets just 'read' the stream, and see
                                // what happens. 
                                StringBuffer sb = new StringBuffer(10000);
                                int bytesRead =0;
                                byte [] buf = new byte[50000];
                                InputStream inp = (InputStream)content;
                    
                                while((bytesRead=inp.read(buf,0,buf.length))!=0)  {
                                    sb.append(new String(buf, 0, bytesRead));
                                }
                                emailMsg[3] = sb.toString();                    
                            }
				
				// Ask the filter to analyze it
				boolean spam = filter.analyze(emailMsg[3]);
				
				// Print results
				if (spam) System.out.println("I do believe this message is spam!");
				else System.out.println("I do believe this is a genuine message!");
                                if (spam) {
                                    emails[i] = null;
                                    
                                }
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException ex) {
                            Logger.getLogger(Bayesian.class.getName()).log(Level.SEVERE, null, ex);
                        }
                return emails;
	}
}
