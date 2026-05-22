package com.mycompany.ts;
import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class Ts {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite uma moeda (ex: USD, EUR, BRL):");
        String busca = leitura.nextLine().toUpperCase(); 

        
        String chaveAPI = "a4fe14e8e243f09d8956946f";  
        String endereco = "https://v6.exchangerate-api.com/v6/" + chaveAPI + "/latest/" + busca;

       
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        
        String responseBody = response.body();
        System.out.println(responseBody);

       
        // Solicita o valor que o usuário quer converter
        System.out.println("Digite o valor que deseja converter:");
        double valor = leitura.nextDouble();

        // Exibe as opções de conversão
        System.out.println("Escolha para qual moeda deseja converter:");
        System.out.println("1 - Dolar");
        System.out.println("2 - Euro");
        System.out.println("3 - Peso Argentino");
        System.out.println("4 - Peso Chileno");

        int opcao = leitura.nextInt();

        double valorConvertido = 0;
        
        // Verifica a opção do usuário e realiza a conversão
        switch (opcao) {
            case 1: 
                double dolar = 0.17;
                double conv1 = valor*dolar;
                System.out.println("Dolar $"+conv1);
                break;
            case 2: 
                double euro = 0.16;
                double conv2 = valor*euro;
                System.out.println("Euro $"+conv2);
                break;
            case 3:  
                double pesoarg = 192.30;
                double conv4 = valor*pesoarg;
                System.out.println("Peso Argentino $"+conv4);
                break;
            case 4:  
                double pesochile = 168.97;
                double conv5 = valor*pesochile;
                System.out.println("Peso Chileno $"+conv5);
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        leitura.close();
    }
}
