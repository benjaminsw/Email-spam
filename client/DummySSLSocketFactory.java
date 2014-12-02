/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.*;

/**
 * The DummySSLSocketFactory is a kind of wrapper that wraps all 
 * out going server connection calls via SSL.  The reason is exists is because
 * we want to implement our javamail client with a secure connection, and
 * the client *must* be able to verify that the SSL certificate recieved
 * from the mail server is legitimate/trusted. 
 * 
 * In this class, we do not provide any code that does any SSL certificate
 * checking.  It is *assumed* that the mail server to which you are connecting
 * is safe.  In real world practice, this is a silly thing to do, but since
 * we assume that you will: 
 * 
 * (a) Know that this is only *demonstration* code, and *not* fully tested and 
 * secure production code; and
 * (b) Probably connect to a FIT server (safe); or 
 * (c) Probably will be familiar with the mail server that you choose to 
 * connect to. 
 *
 * So please, don't try connect to a random unknown mail server that you have
 * never used before :)
 * 
 * @author Paul
 */
public class DummySSLSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory factory;

    public DummySSLSocketFactory() {
	try {
	    SSLContext sslcontext = SSLContext.getInstance("TLS");
	    sslcontext.init(null,
				 new TrustManager[] { new DummyTrustManager()},
				 null);
	    factory = (SSLSocketFactory)sslcontext.getSocketFactory();
	} catch(Exception ex) {
	    // ignore
	}
    }

    public static SocketFactory getDefault() {
	return new DummySSLSocketFactory();
    }

    public Socket createSocket() throws IOException {
	return factory.createSocket();
    }

    public Socket createSocket(Socket socket, String s, int i, boolean flag)
				throws IOException {
	return factory.createSocket(socket, s, i, flag);
    }

    public Socket createSocket(InetAddress inaddr, int i,
				InetAddress inaddr1, int j) throws IOException {
	return factory.createSocket(inaddr, i, inaddr1, j);
    }

    public Socket createSocket(InetAddress inaddr, int i)
				throws IOException {
	return factory.createSocket(inaddr, i);
    }

    public Socket createSocket(String s, int i, InetAddress inaddr, int j)
				throws IOException {
	return factory.createSocket(s, i, inaddr, j);
    }

    public Socket createSocket(String s, int i) throws IOException {
	return factory.createSocket(s, i);
    }

    public String[] getDefaultCipherSuites() {
	return factory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
	return factory.getSupportedCipherSuites();
    }
}
