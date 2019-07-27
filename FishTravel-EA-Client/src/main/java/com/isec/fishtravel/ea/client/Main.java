package com.isec.fishtravel.ea.client;

import com.isec.fishtravel.remote.ClientAccessRemote;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {
    
    static Scanner sc = new Scanner(System.in);
    static ClientAccessRemote car;
    //static TALog msgSender;
    
    public static void getRemoteReferences() {
        
        InitialContext ctx = null;
        Properties prop = new Properties();
        
        prop.setProperty("java.naming.factory.initial",
                     "com.sun.enterprise.naming.SerialInitContextFactory");
        prop.setProperty("java.naming.factory.url.pkgs",
                     "com.sun.enterprise.naming");
        prop.setProperty("java.naming.factory.state",
                     "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        prop.setProperty("org.omg.CORBA.ORBInitialHost", "192.168.56.175");
        prop.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

        try {
            ctx = new InitialContext(prop);
        }
        catch (NamingException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("InitialContext done");

        // app name / module name / ejb ! package.interface
        String remotename = 
            "java:global/fishtravel-v1/FishTravel-EA-ejb/ClientAccess!com.isec.fishtravel.remote.ClientAccessRemote";

        try {
            System.out.println("start lookup");
            car = (ClientAccessRemote)  ctx.lookup(remotename);
        }
        catch (NamingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  // "they" don't like that -> send to logger
            System.exit(1);
        }
        System.out.println("JNDI lookup done");
    }
    
    public static void main( String[] args ) {
        
        getRemoteReferences();
        System.out.println( "Hello World Enterprise Application Client!" );
    }
}
