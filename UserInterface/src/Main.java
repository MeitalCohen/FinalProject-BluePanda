import serviceHost.ServiceCommand;

public class Main {
    public static void main(String [] args)
    {
        ServiceCommand service = ServiceCommand.getInstance();
        LoginPage.login();
    }
}