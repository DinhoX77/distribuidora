import java.util.List;

public class FaturamentoService {
    private List<Double> faturamentos;

    public FaturamentoService(List<Double> faturamentos) {
        this.faturamentos = faturamentos;
    }

    public double calcularMenorFaturamento() {
        return faturamentos.stream()
                .filter(faturamento -> faturamento > 0)
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0);
    }

    public double calcularMaiorFaturamento() {
        return faturamentos.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0);
    }

    public double calcularMediaFaturamento() {
        double soma = faturamentos.stream()
                .filter(faturamento -> faturamento > 0)
                .mapToDouble(Double::doubleValue)
                .sum();
        long diasComFaturamento = faturamentos.stream()
                .filter(faturamento -> faturamento > 0)
                .count();
        return diasComFaturamento > 0 ? soma / diasComFaturamento : 0;
    }

    public long contarDiasAcimaDaMedia() {
        double media = calcularMediaFaturamento();
        return faturamentos.stream()
                .filter(faturamento -> faturamento > media)
                .count();
    }
}
