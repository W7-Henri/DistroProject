package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DistroController {

    private String os() {
        return System.getProperty("os.name");
    }

    public void exibeDistro() {
        String so = os();
        
        if (!so.contains("Linux")) {
            System.out.println("Este sistema operacional não é Linux.");
            return;
        }
        
        String comando = "cat /etc/os-release";
        
        try {
            Process processo = Runtime.getRuntime().exec(new String[]{"bash", "-c", comando});
            BufferedReader leitor = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;
            String nome = "Desconhecido", versao = "Desconhecida";
            
            while ((linha = leitor.readLine()) != null) {
                if (linha.startsWith("PRETTY_NAME=")) {
                    nome = linha.split("=")[1].replace("\"", "");
                }
                if (linha.startsWith("VERSION_ID=")) {
                    versao = linha.split("=")[1].replace("\"", "");
                }
            }
            
            leitor.close();
            System.out.println("Distribuição: " + nome);
            System.out.println("Versão: " + versao);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
