package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final int PORT = 8989;
    private static final String LOCALHOST = "127.0.0.1";

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(LOCALHOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println(in.readLine()); //считываем город
            String city = null;

            BufferedReader readCity = new BufferedReader(new InputStreamReader(System.in)); //отправляем город в ответ
            city = readCity.readLine();
            out.println(city);

            System.out.println(in.readLine()); //выводим на экран ответ сервера

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
