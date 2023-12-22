package br.com.raphaelsena.tabelafipe.main;

import br.com.raphaelsena.tabelafipe.service.ConsumoApi;

import java.net.URL;
import java.util.Scanner;

public class Program {
    private Scanner sc = new Scanner(System.in);

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoApi consumo = new ConsumoApi();
    public void exibeMenu() {



        var menu = """
                *** OPTIONS ***
                Car 
                Bike
                Truck
                
                Type the option for query:
                """;
        System.out.println(menu);
        var opt = sc.nextLine();

        String endereco;

        if (opt.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opt.toLowerCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

    }
}
