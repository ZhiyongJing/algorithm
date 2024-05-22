package interview.company.amazon;

import java.util.*;

public class CurrencyConverterDFS {

    private static class Edge {
        String to;
        double rate;

        Edge(String to, double rate) {
            this.to = to;
            this.rate = rate;
        }
    }

    // Graph representation
    private static Map<String, List<Edge>> graph = new HashMap<>();

    // Function to add an edge to the graph
    private static void addEdge(String from, String to, double rate) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, rate));
        graph.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, 1 / rate));
    }

    // DFS function to find the conversion rate
    private static double dfs(String current, String target, Set<String> visited) {
        if (current.equals(target)) return 1.0;
        visited.add(current);

        if (graph.containsKey(current)) {
            for (Edge edge : graph.get(current)) {
                if (!visited.contains(edge.to)) {
                    double rate = dfs(edge.to, target, visited);
                    if (rate > 0) {
                        return edge.rate * rate;
                    }
                }
            }
        }
        return -1; // Not reachable
    }

    public static double convertCurrency(String fromCurrency, String toCurrency) {
        if (!graph.containsKey(fromCurrency) || !graph.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Currency not supported: " + fromCurrency + " or " + toCurrency);
        }

        Set<String> visited = new HashSet<>();
        double result = dfs(fromCurrency, toCurrency, visited);
        if (result < 0) {
            throw new IllegalArgumentException("Conversion not possible from " + fromCurrency + " to " + toCurrency);
        }
        return result;
    }

    public static void main(String[] args) {
        // Example rates (these would normally come from an API)
        addEdge("USD", "EUR", 0.85);
        addEdge("EUR", "GBP", 0.88);
        addEdge("GBP", "INR", 101.5);
        addEdge("USD", "JPY", 109.0);
        addEdge("JPY", "INR", 0.67);

        // Example usage
        String fromCurrency = "USD";
        String toCurrency = "INR";
        double amount = 100;

        try {
            double rate = convertCurrency(fromCurrency, toCurrency);
            double convertedAmount = amount * rate;
            System.out.printf("%.2f %s is %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
