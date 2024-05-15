package interview.company.amazon;

public class Currency_Exchange {
    public static void main(String[] args) throws Exception {
        /*
        Test 1:
        USD,CAD,1.3;USD,GBP,0.71;USD,JPY,109;GBP,JPY,155
        USD
        JPY

        Output: 110.05

        Test 2:
        USD,GBP,0.7;USD,JPY,109;GBP,JPY,155;CAD,CNY,5.27;CAD,KRW,921
        USD
        CNY

        Output: -1.0
        */

        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);

        Map<String, Map<String, Double>> graph = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Set<Double> results = new HashSet<>();

        String[] input = new String[3];
        String line;
        int count = 0;
        //Reading input from the buffer
        while ((line = in.readLine()) != null) {
            input[count++] = line;
        }

        String[] currencies = input[0].split(";");
        String currencyFrom = input[1];
        String currencyTo = input[2];

        //Build the graph
        for (String currency : currencies) {
            String[] splitCurr = currency.split(",");
            String from = splitCurr[0];
            String to = splitCurr[1];
            Double value = Double.parseDouble(splitCurr[2]);

            Map<String, Double> currNeighbors = graph.getOrDefault(from, new HashMap<>());
            currNeighbors.put(to, value);
            graph.put(from, currNeighbors);
        }

        System.out.println(graph);
        backtrack(currencyFrom, currencyTo, graph, 1.0, visited, results);

        // Find the maximum conversion rate
        double maxRate = results.stream().max(Double::compare).orElse(-1.0);
        System.out.println(maxRate);
    }


    public static void backtrack(String currentNode, String endNode, Map<String, Map<String, Double>> graph, double temp, Set<String> visited, Set<Double> results) {
        if (currentNode.equals(endNode)) {
            results.add(temp);
            return;
        }

        Map<String, Double> neighbors = graph.get(currentNode);
        if (neighbors != null) {
            for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
                String neighNode = entry.getKey();
                double conversionRate = entry.getValue();

                if (!visited.contains(neighNode)) {
                    visited.add(neighNode);
                    backtrack(neighNode, endNode, graph, temp * conversionRate, visited, results);
                    visited.remove(neighNode);
                }
            }
        }
    }
}
