package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8989;
    private static String city = null;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {  // обработка одного подключения
                    if (city == null) {
                        out.println("???");
                        city = in.readLine();
                        out.println("OK");
                    } else {
                        out.println(city);
                        char endChar = city.charAt(city.length() - 1);
                        out.println("Вам город на " + endChar);
                        String answer = in.readLine();
                        if (answer.charAt(0) == endChar) {
                            out.println("OK");
                            city = answer;
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
