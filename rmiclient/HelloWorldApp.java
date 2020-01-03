import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloWorldApp{
    private static final String hostName = "localhost";
	private static final String lookupName = "IHelloWorldFromEclipse";

    public static void main(String args[]){

        System.setProperty("java.security.policy", "file:client.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
        }
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");		
            var registry = LocateRegistry.getRegistry(hostName);
            for (String string : registry.list()) {
                System.out.println(string);
            }
            var helloWorld = (IHelloWorldFromEclipse) registry.lookup(lookupName);
            String result = helloWorld.hello("Test from client without spring boot");
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

/*
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "IHelloWorldFromEclipse";
            System.out.println(args[0]);
            Registry registry = LocateRegistry.getRegistry(args[0]);
            
            for (String string : registry.list()) {
                System.out.println(string);
            }
            
            IHelloWorldFromEclipse helloWorld = (IHelloWorldFromEclipse) registry.lookup(name);
            String result = helloWorld.hello("Test from client without spring boot");
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}