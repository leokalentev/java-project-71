package hexlet.code;


import java.util.*;
import java.util.stream.Collectors;

import static hexlet.code.GetData.getData;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("{ ").append("\n");
        var keys = Differ.allKeys(data1, data2);

        for (var key : keys) {
            if (data1.containsKey(key) && data2.containsKey(key) && data1.get(key).equals(data2.get(key))) {
                result.append("  ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else if (data1.containsKey(key) && !data2.containsKey(key)) {
                result.append("  ").append("- ").append(key).append(": ").append(data1.get(key)).append("\n");;
            } else if (data1.containsKey(key) && data2.containsKey(key) && !data1.get(key).equals(data2.get(key))){
                result.append("  ").append("- ").append(key).append(": ").append(data1.get(key)).append("\n");;
                result.append("  ").append("+ ").append(key).append(": ").append(data2.get(key)).append("\n");;
            } else {
                result.append("  ").append("+ ").append(key).append(": ").append(data2.get(key)).append("\n");;
            }
        }

        result.append(" }");
        String finallyResult = result.toString();
        return finallyResult;

    }

    public static List<String> allKeys(Map<String, Object> data1, Map<String, Object> data2) {
        List<String> result = new ArrayList<>();
        var keys1 = data1.keySet();
        for (var key : keys1) {
            result.add(key);
        }

        var keys2 = data2.keySet();
        for (var key : keys2) {
            result.add(key);
        }

        Collections.sort(result);
        List<String> uniqueResult = result.stream()
                .distinct()
                .collect(Collectors.toList());
        return uniqueResult;
    }

}
