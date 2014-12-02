/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * DummyTrustManager - NOT SECURE
 * 
 * Please read the DummySSLSocketFactory.java comments for more information. 
 * 
 */
public class DummyTrustManager implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] cert, String authType) {
	// everything is trusted
    }

    public void checkServerTrusted(X509Certificate[] cert, String authType) {
	// everything is trusted
    }

    public X509Certificate[] getAcceptedIssuers() {
	return new X509Certificate[0];
    }
}
