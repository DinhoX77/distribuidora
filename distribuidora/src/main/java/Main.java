import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ler dados do arquivo JSON
        FaturamentoData faturamentoData = null;
        try (FileReader reader = new FileReader("src/main/resources/faturamento.json")) {
            Gson gson = new GsonBuilder().create();
            faturamentoData = gson.fromJson(reader, FaturamentoData.class);
        } catch (IOException e) {
            System.out.println("Não foi possível ler os dados de faturamento.");
            e.printStackTrace();
        }

        if (faturamentoData != null && faturamentoData.getFaturamentos() != null) {
            List<Double> faturamentos = faturamentoData.getFaturamentos();

            FaturamentoService service = new FaturamentoService(faturamentos);

            double menorFaturamento = service.calcularMenorFaturamento();
            double maiorFaturamento = service.calcularMaiorFaturamento();
            double mediaFaturamento = service.calcularMediaFaturamento();
            long diasAcimaDaMedia = service.contarDiasAcimaDaMedia();

            System.out.println("Menor faturamento: " + menorFaturamento);
            System.out.println("Maior faturamento: " + maiorFaturamento);
            System.out.println("Média de faturamento: " + mediaFaturamento);
            System.out.println("Dias acima da média: " + diasAcimaDaMedia);
        } else {
            System.out.println("Não foi possível processar os dados de faturamento.");
        }
    }
}
