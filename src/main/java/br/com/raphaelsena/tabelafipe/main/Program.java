package br.com.raphaelsena.tabelafipe.main;

import br.com.raphaelsena.tabelafipe.model.Dados;
import br.com.raphaelsena.tabelafipe.model.Modelos;
import br.com.raphaelsena.tabelafipe.service.ConsumoApi;
import br.com.raphaelsena.tabelafipe.service.ConverteDados;
import br.com.raphaelsena.tabelafipe.service.IConverteDados;

import java.net.URL;
import java.util.Comparator;
import java.util.Scanner;

public class Program {
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

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

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o codigo da marca para consulta: ");
        var codigoMarca = sc.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);

        var modeloLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
    }
}
