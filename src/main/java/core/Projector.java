package core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Projector<T> {
    private ObjectMapper mapper = new ObjectMapper();
    private Class<T> clazz;

    public enum Policy {
        MERGE,
        REPLACE
    }

    public Projector(Class<T> clazz) {
        this.clazz = clazz;
    }

    private void handleAggregationByPolicy(String fieldName, JsonNode target, JsonNode source, Policy policy) {
        if (policy == Policy.REPLACE) {
            ((ObjectNode) target).set(fieldName, source.get(fieldName));
        } else if (policy == Policy.MERGE) {
            var s = source.get(fieldName);
            var t = target.get(fieldName);
            if (s.isNull() && t.isNull()) {
                return;
            } if (s.isNull()) {
                ((ObjectNode) target).set(fieldName, t);
            } else if (t.isNull()) {
                ((ObjectNode) target).set(fieldName, s);
            } else {
                if (!s.isArray()) {
                    ((ObjectNode) target).set(fieldName, s);
                    return;
                }

                var tSet = StreamSupport
                        .stream(Spliterators.spliteratorUnknownSize(t.elements(), Spliterator.ORDERED), false)
                        .collect(Collectors.toSet());
                s.forEach(v -> {
                    if (!tSet.contains(v)) ((ArrayNode) t).add(v);
                });
                ((ObjectNode) target).set(fieldName, t);
            }
        } else {
            throw new RuntimeException("illegal policy");
        }
    }

    public JsonNode merge(JsonNode target, JsonNode source, Policy policy) {
        target.fields().forEachRemaining(fields -> {
            var fieldName = fields.getKey();
            var value = fields.getValue();

            if (!value.isNull() && value.isObject()) {
                merge(value, source.get(fieldName), policy);
            } else {
                if (source.get(fieldName) != null) {
                    handleAggregationByPolicy(fieldName, target, source, policy);
                }
            }
        });

        return target;
    }

    private JsonNode merging(Object v1, Object v2, Policy policy) {
        try {
            return merge(mapper.readTree(mapper.writeValueAsString(v1)),
                    mapper.readTree(mapper.writeValueAsString(v2)), policy);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public T aggregate(List<T> events, Policy policy) throws NoSuchMethodException,
                                                            InvocationTargetException, IllegalAccessException,
                                                            InstantiationException, JsonProcessingException {
        T o = clazz.getDeclaredConstructor().newInstance();
        var init = mapper.readTree(mapper.writeValueAsString(o));
        var r = events.stream()
                .reduce(init, (prev, next) -> merging(prev, next, policy), (v1, v2) -> merging(v1, v2, policy));
        return mapper.convertValue(r, clazz);
    }
}
