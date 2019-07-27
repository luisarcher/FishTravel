/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fishtravel.client.remote;

import com.isec.fishtravel.dto.DTOFlight;
import com.isec.fishtravel.dto.DTOUser;
import com.isec.fishtravel.remote.ClientAccessRemote;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author LM
 */
public class FishtravelClientRemote {
    
    static Scanner sc = new Scanner(System.in);
    static ClientAccessRemote car;
    
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
            "java:global/FishTravel-EA-ear/FishTravel-EA-web-1.0-SNAPSHOT/ClientAccess!com.isec.fishtravel.remote.ClientAccessRemote";

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
    
    public static void main(String[] args) {
        
        int opcao;
        boolean proceed = true;
        getRemoteReferences();
        //msgSender = new TALog();
        
        while(proceed){
            printMenu();
            
            opcao = getMenuOption(99);
            
            switch(opcao){
                case 1:
                    login();
                    break;
                case 2:
                    listAllFlights();
                    break;
                case 22:
                    listAllFlightsConsole();
                    break;
                case 3:
                    listAllUsers();
                    break;
                case 4:
                    //listAllMsgLogEntries();
                    break;
                case 6:
                    //logout();
                    break;
                    
                case 9:
                    //msgSender.sendMsg("this is a test msg...2");
                    break;
                case 0:
                    proceed = false;
                    break;
                    
                default:
                    System.out.println("Wrong option. Try again.");
                    break;
                
            }
        }
        System.out.println("Exiting...");
        
    }
    
    public static void printMenu(){
        
        System.out.println("\n -----------------------------");
        System.out.println("======  MAIN MENU  ======");
        System.out.println("1  - Login");
        System.out.println("2  - Flights");
        System.out.println("22 - Flights - Console Mode");
        System.out.println("3  - Accounts");
        System.out.println("4  - Logs");
        System.out.println("0  - Exit");
        System.out.println("\n -----------------------------");
    }
    
    public static int getMenuOption(int max){
        
        int opcao;
        String texto;
        while(true){
            try{
                System.out.println("Option -> ");
                texto = sc.nextLine();
                opcao = Integer.parseInt(texto);
                if ((opcao >= 0) && (opcao <= max))
                    return opcao;
                
                System.out.println("Wrong option. Try again.");
            }
            catch(NumberFormatException e){
                System.out.println("Incorrect Entry!");
            }
        }
    }
    
    public static void login(){
        String login, passwd;
        
        System.out.println("\nLogin -> ");
        login = sc.nextLine();
        
        System.out.println("\nPassword -> ");
        passwd = sc.nextLine();
        
        System.out.println("");
        
        if (car.userLogin(login,passwd)){
            System.out.println("Confirmed!");
        }
        else{
            System.out.println("Incorrect Username or password!");
        }
    }
    
    public static void logout(){
        System.out.println("ok");
    }
    
    /*public static void listAllMsgLogEntries(){
        for (String entry: car.getMessageList()){
            System.out.println(entry);
        }
    }*/
    
    public static void listAllFlights(){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");                       
        String leftAlignFormat = "| %-8s | %-18s | %-18s | %-4.2f | %-9s | %-9s | %-5s |%n";

        System.out.format("+----------+--------------------+--------------------+--------+------------------+------------------+-------+%n"); //3+
        System.out.format("| Flight   | FROM               | TO                 | PRICE  | DEPARTURE        | ARRIVAL          | SEATS |%n");
        System.out.format("+----------+--------------------+--------------------+--------+------------------+------------------+-------+%n");

        for (DTOFlight f : car.getFlightList()){
            System.out.format(leftAlignFormat,
                    f.getFlightName(),
                    f.getFrom(),
                    f.getTo(),
                    f.getPrice(),
                    sdf.format(f.getTimeDeparture()),
                    sdf.format(f.getTimeArrival()),
                    (String.valueOf(f.getAvailableSeats())  + "/" + String.valueOf(f.getMaxSeats()))
                );
        }

        System.out.format("+----------+--------------------+--------------------+--------+------------------+------------------+-------+%n");
    }
    
    public static void listAllFlightsConsole(){
        
        // Consoles have 80 chars width
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");                       
        String leftAlignFormat = " %-8s | %-4s | %-4s | %-4.2f | %-16s | %-16s | %-5s %n";

        System.out.format("----------+------+------+--------+------------------+------------------+-------%n"); //width 79 chars
        System.out.format(" Flight   | FROM | TO   | PRICE  | DEPARTURE        | ARRIVAL          | SEATS %n");
        System.out.format("----------+------+------+--------+------------------+------------------+-------%n");

        for (DTOFlight f : car.getFlightList()){
            System.out.format(leftAlignFormat,
                    f.getFlightName(),
                    f.getFrom().substring(0,3),
                    f.getTo().substring(0,3),
                    f.getPrice(),
                    sdf.format(f.getTimeDeparture()),
                    sdf.format(f.getTimeArrival()),
                    (String.valueOf(f.getAvailableSeats())  + "/" + String.valueOf(f.getMaxSeats()))
                );
        }

        System.out.format("----------+------+------+--------+------------------+------------------+-------%n");
        
    }
    
    public static void listAllUsers(){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");                       
        String leftAlignFormat = "| %-6d | %-18s | %-18s | %-8s | %-10s | %-10s | %-10s |%n";

        System.out.format("+--------+--------------------+--------------------+----------+------------+------------+------------+%n"); //3+
        System.out.format("| ID     | LOGIN              | NAME               | CREDITS  | BIRTHDATE  | CREATED AT | ROLE       |%n");
        System.out.format("+--------+--------------------+--------------------+----------+------------+------------+------------+%n");

        for (DTOUser u : car.getUserList()){
            System.out.format(leftAlignFormat,
                    u.getId(),
                    u.getLogin(),
                    u.getNameUser(),
                    String.valueOf(u.getCredits()),
                    sdf.format(u.getBirthdate()),
                    sdf.format(u.getCreatedAt()),
                    u.getRole()
                );
        }

        System.out.format("+--------+--------------------+--------------------+----------+------------+------------+------------+%n");
        
    }
    
}
