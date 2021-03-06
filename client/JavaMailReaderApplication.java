/*
 * JavaMailReaderApplication.java
 *
 */

package client;

import javax.swing.DefaultListModel;
import javax.mail.*;
import java.util.Properties;
import java.util.ArrayList;
import java.io.InputStream;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import bayes.*;

/**
 * This application demonstrates the use of JavaMail in order to read (even
 * write/delete from) an email account.  We focus on the use of POP3 protocol, 
 * although IMAP is easily interchanged.  Of particular importance is the use
 * of secure email access - that is, POP3 over SSL/TLS.  This example shows
 * how you access an email account securely.  For this to work, we required
 * two other classes: 
 * 
 * <ul>
 * <li>DummySSLSocketFactory.java</li>
 * <li>DummyTrustManager.java</li>  
 * </ul>
 * 
 * The relevance of each file is described in the comments/documentation within
 * each file. For more information of javamail, please see: 
 * 
 * http://java.sun.com/products/javamail/
 * 
 * We use JavaMail version 1.4.1 
 * 
 * The default POP3/IMAP mail server that we use is: marcie.it.uts.edu.au
 * "marcie" is the default student email server for the faculty of IT. (for
 * all your email addresses that ends with "@it.uts.edu.au"). 
 * 
 * You can however use whatever server you like. Warning though: this code 
 * has only been tested using marcie.  Another Warning: You should only choose
 * mail servers that you are familiar with and know to be secure and trusted.  
 * 
 * @author  Paul
 */
public class JavaMailReaderApplication extends javax.swing.JFrame {

    // This is a global variable that you can adjust to change the number
    // of emails read in from the INBOX.  The total is defaulted to 10, since
    // this is a quick and easy number of emails to grab in order to demonstrate
    // how JavaMail and Imap/Pop3 works.     
    private int totalEmailsToBeRead = 10;

    // marcie.it.uts.edu.au 
    // Is the default student mail server at the faculty of IT. 
    // You can change this to whatever you like, however, the 
    // below code has only been tested using "marcie.it.uts.edu.au"
    //private String defaultMailServer = "marcie.it.uts.edu.au";
    private String defaultMailServer = "pop.gmail.com";
    
    /** Creates new form JavaMailReaderApplication */
    public JavaMailReaderApplication() {
        initComponents();
        setSize(800,600);
        listModel = new DefaultListModel();
        jList1.setModel(listModel);
        jTextField1.setText(defaultMailServer);
        jList1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // When a user selects to show a particular email, this
                // method gets run. 
                JavaMailReaderApplication.this.displayEmailDetails();
             }
        });
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel1.setText("JavaMail Reader - Secure POP3");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(220, 10, 280, 16);

        jLabel2.setText("Login:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 100, 35, 16);

        jLabel3.setText("Password:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 130, 70, 16);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(110, 70, 280, 21);

        jLabel4.setText("POP Server:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 70, 80, 16);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(110, 100, 280, 21);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(110, 130, 280, 21);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(110, 160, 80, 25);

        jButton2.setText("Disconnect & Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(110, 200, 160, 25);

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText(" ");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(110, 40, 430, 16);

        jLabel6.setText("Downloading the 10 most recent emails from the INBOX:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 230, 350, 16);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 250, 390, 260);

        jLabel7.setText("Date:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(420, 270, 60, 16);

        jLabel8.setText("Subject:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(420, 290, 70, 16);

        jLabel9.setText("Message:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(420, 310, 60, 16);

        jLabel10.setText("From:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(420, 250, 35, 16);

        jLabel11.setText(" ");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(480, 250, 300, 16);

        jLabel12.setText(" ");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(480, 270, 300, 16);

        jLabel13.setText(" ");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(480, 290, 300, 16);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(420, 330, 360, 180);

        jLabel14.setForeground(new java.awt.Color(51, 204, 0));
        jLabel14.setText(" ");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(270, 160, 380, 16);

        jLabel15.setText("Status:");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(220, 160, 41, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jLabel5.setText("");
        jTextArea1.setText("");
        jLabel11.setText("");
        jLabel12.setText("");
        jLabel13.setText("");
        
        emailsOnDisplay.clear();
        listModel.removeAllElements();
        
        // Firstly, do the check to see whether server, login, password 
        // information have been entered. 
        String popServer = jTextField1.getText();
        if(popServer==null || popServer.trim().length()==0) {
            jLabel5.setText("'Pop3 Server' field cannot be empty");
            return;
        }
        String login = jTextField2.getText();
        if(login==null || login.trim().length()==0) {
            jLabel5.setText("'Login' field cannot be empty");
            return;
        }
        if(jPasswordField1.getPassword()==null || jPasswordField1.getPassword().length==0) {
            jLabel5.setText("'Password' field cannot be empty");
            return;
        }
        String passwd = new String(jPasswordField1.getPassword());
        
        // Connect to mailbox, and download the most recent mail. 
        connectToMailbox(popServer, login, passwd);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // This method clear/resets all of the email fields. 
        jLabel5.setText("");
        listModel.removeAllElements();
        emailsOnDisplay.clear();
        jTextArea1.setText("");
        jLabel11.setText("");
        jLabel12.setText("");
        jLabel13.setText("");   
        jLabel14.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
    // -------------------------------------------------------------------------
    // Private Global Variables/Methods
    // -------------------------------------------------------------------------
    // This is user interface's list of emails
    private DefaultListModel listModel;
    // This variable holds the last 10 emails that the user wants to read. 
    private ArrayList emailsOnDisplay = new ArrayList(100);
    // -------------------------------------------------------------------------
    /**
     * This method connects (securly, via SSL) to the specified "popServer" using 
     * the login/passwd combination.  It then reads through the most recent
     * emails, collects the From/Date/Subject/Body-Message, stores it
     * in the ArrayList, emailsOnDisplay, and shows it in the user interface. 
     * 
     * @param popServer
     * @param login
     * @param passwd
     */
    public void connectToMailbox(String popServer, String login, String passwd) {
        // Just making sure the our user interface is clear of old emails. 
        emailsOnDisplay.clear();
        listModel.removeAllElements();
        
        // Since we want to place an element of security in the way that
        // we access our email, we begin by specifying the the Security
        // manager that we want to have our DummySSLSocketFactory check 
        // that the secure connections are legit. 
        //java.security.Security.setProperty("ssl.SocketFactory.provider",
					//"javamailreader.DummySSLSocketFactory");        

        // Next, we have to declare to the JavaMail Session object that
        // we are going to use a secure connection, and specifically, want to 
        // use the secure "POP3" protocol. 
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocal", "imaps");
        props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
 
		
        login = "siwawaed@gmail.com";
        passwd = "534ElizabethSt.";
        
        try {
            // We now try to connect to the email server. 

            // Let the user know what's happening. 
            jLabel14.setText("Connecting...");

            // Our email session object. 
            //Session session = Session.getDefaultInstance(props, null);
            Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("siwawaed@gmail.com","534ElizabethSt.");
                                        				}
			});

            // We specify the 
            //URLName urln = new URLName("pop3", popServer, 995, null, login, passwd);
            URLName urln = new URLName("imaps://siwawaed%40gmail.com@pop.gmail.com");
            Store store = session.getStore("imaps");
            //System.out.println("pop = " + popServer + " login = " + login + " passwd = " + passwd);
            store.connect("pop.gmail.com", "siwawaed@gmail.com", "534ElizabethSt.");
            //System.out.println("here");
            //store.connect(popServer, login, passwd);
            
            // This method connect to the mailbox server "popServer", with the
            // login/passwd. 
            //store.connect();
            jLabel14.setText("Connected. Reading inbox...");

            
            // Get folder.  This defaults to the INBOX, although 
            // alternative folders might be used. 
            Folder folder = store.getFolder("INBOX");
            // Since we don't want to delete anything, we set the folder to
            // READ_ONLY.  There's also a READ_WRITE type, incase you want
            // to do more. 
            folder.open(Folder.READ_ONLY);

            long start = System.currentTimeMillis();
            // Get emails. 
            int totalEmails = folder.getMessageCount();
            if(totalEmails==0) {
                jLabel14.setText("Done. No emails in Inbox");
                return;
            }
            System.out.println("total emails = " + totalEmails);
            Message [] emails = null;
            // This just checks that there are enough emails in the inbox to 
            // be read.  We want a max of the "totalEmailsToBeRead" variable.
            if(totalEmails<totalEmailsToBeRead) emails = folder.getMessages(1, totalEmails);
            else emails = folder.getMessages(totalEmails-totalEmailsToBeRead+1, totalEmails);

            //send email to classify if there are any spam email
            Bayesian bayes = new Bayesian();
            emails = bayes.getBaysian(emails, totalEmailsToBeRead);
            
            
            // Once we have our email, cycle through them and extrac their
            // content to display in our UI. 
            for(int i = 0; i < emails.length; i++) {
                // Construct a new email cache object. 
                String [] emailMsg = new String[4];
                // Get the "From" email address. 
                emailMsg[0] = emails[i].getFrom()[0].toString();
                // Check that there is a "date sent/recieved". 
                if(emails[i].getSentDate()!=null) 
                    emailMsg[1] = emails[i].getSentDate().toString();
                else if (emails[i].getReceivedDate()!=null)
                    emailMsg[1] = emails[i].getReceivedDate().toString();
                // Get the email "Subject"
                emailMsg[2] = emails[i].getSubject();

                // Next, we read the email message. 
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
                
                // If everything is OK, add it to the UI. 
                emailsOnDisplay.add(0, emailMsg);
                // Show the "From" and "Subject"
                // Make a visual edit of the "from" address....
                String fromEdit = emailMsg[0];
                if(fromEdit.indexOf('<')!=-1) {
                    fromEdit = fromEdit.substring(0, fromEdit.indexOf('<'));
                }                
                listModel.add(0,(totalEmails-(totalEmailsToBeRead-i)+1)+". "+fromEdit+" :: "+emailMsg[2]);
            }
            
            System.out.println("Total Emails: "+folder.getMessageCount());            
            System.out.println("Total time to read inbox: "+((System.currentTimeMillis()-start)/1000.0)+" seconds");    
            
            jLabel14.setText("Done. ");            
            // Message message[] = folder.getMessages();
            // Close connection
            folder.close(false);
            store.close();
            return;
        } catch (javax.mail.AuthenticationFailedException ae) {
          // This exception gets thrown when login/passwd is incorrect. 
          ae.printStackTrace();
          jLabel14.setText(jLabel14.getText()+" Authentification");
        } catch (Exception ex) {
            if(ex.getCause()!=null && ex.getCause() instanceof java.net.UnknownHostException) {
                // Check if the popServer cannot be reached. 
                jLabel14.setText(jLabel14.getText()+" to 'POP server'");            
            }
          ex.printStackTrace();
        }
        jLabel14.setText(jLabel14.getText()+" Failed!");
    }
    
    
    //-------------------------------------------------------------------------
    // Simple method to display the contents of an email. 
    // This method gets run when the user clicks on the an email
    // header on the jList1. 
    public void displayEmailDetails() {
        if(jList1.getSelectedIndex()==-1) return;
        
        int ind = jList1.getSelectedIndex();
        if(emailsOnDisplay.size()>0 && ind<emailsOnDisplay.size()) {
            String [] email = (String[])emailsOnDisplay.get(ind);
            jLabel11.setText(email[0]);
            jLabel12.setText(email[1]);
            jLabel13.setText(email[2]);
            jTextArea1.setText(email[3]);
            
            //Message messages[] = folder.getMessages();// gets inbox messages
            //String [] email = (String[])emailsOnDisplay.get(ind);
            //jLabel11.setText(messages[0].getSentDate()); //print sent date
            //jLabel12.setText(messages[1].getFrom()[0]); //print email id of sender
            //jTextArea1.setText(messages[3].getSubject()); //print subject of email
        }
    }
    
    
    
    
    //-------------------------------------------------------------------------
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JavaMailReaderApplication().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
       
}
