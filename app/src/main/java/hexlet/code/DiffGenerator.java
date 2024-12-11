package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class DiffGenerator {

    public static List<Map<String, Object>> generateDiff(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> diff = new ArrayList<>();
        Set<String> keys = Differ.allKeys(data1, data2);

        for (String key : keys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            Map<String, Object> entry = new HashMap<>();
            entry.put(DiffConstants.KEY, key);

            if (data1.containsKey(key) && data2.containsKey(key)) {
                if (value1 == null && value2 == null) {
                    entry.put(DiffConstants.TYPE, DiffConstants.UNCHANGED);
                    entry.put(DiffConstants.VALUE, null);
                } else if (value1 != null && value1.equals(value2)) {
                    entry.put(DiffConstants.TYPE, DiffConstants.UNCHANGED);
                    entry.put(DiffConstants.VALUE, value1);
                } else if (Differ.isNested(value1) && Differ.isNested(value2)) {
                    entry.put(DiffConstants.TYPE, DiffConstants.UNCHANGED);
                    entry.put(DiffConstants.VALUE, generateDiff(Differ.toMap(value1), Differ.toMap(value2)));
                } else {
                    entry.put(DiffConstants.TYPE, DiffConstants.UPDATED);
                    entry.put(DiffConstants.OLD_VALUE, value1);
                    entry.put(DiffConstants.NEW_VALUE, value2);
                }
            } else if (data1.containsKey(key)) {
                entry.put(DiffConstants.TYPE, DiffConstants.REMOVED);
                entry.put(DiffConstants.OLD_VALUE, value1);
            } else {
                entry.put(DiffConstants.TYPE, DiffConstants.ADDED);
                entry.put(DiffConstants.NEW_VALUE, value2);
            }

            diff.add(entry);
        }
        return diff;
    }
}
