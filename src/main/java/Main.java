import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("server started");
        int port = 8089;

        try (ServerSocket serverSocket = new ServerSocket(port)) { //можно выбрать любой порт в доступном диапазоне
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); //ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port: %d.", clientSocket.getPort());

                    //User
                    out.println(String.format("Добрый день, port %d. Как Вас зовут?", clientSocket.getPort()));
                    final String name = in.readLine();
                    System.out.printf(" User: %s; ", name);

                    //ProgramType
                    out.println(String.format("Очень рады Вас видеть, %s! Какие учебные программы интересуют? (1 - для школьников, 2 - для взрослых)", name));
                    final String type = in.readLine();
                    System.out.println(" ProgramType: " + type);

                    //Result
                    if (type.equals("1")) {
                        out.println(String.format("%s, Вам предоставлен доступ к учебным программам для школьников", name));
                    } else if (type.equals("2")) {
                        out.println(String.format("%s, Вам предоставлен доступ к учебным программам для взрослых", name));
                    } else {
                        out.println("Вы не выбрали возрастную категорию");
                    }
                }
            }
        }
    }
}
