package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *    JSON Object ==> Java POJO (java reflection)
 *    (name, type, value) ==> Java Object
 *    lots of converter come to help
 **/
public class Servlet {
    private Map<String, String> urlClass = new HashMap<>();

    private void register() {
        urlClass.put("/reconnect/solve", "interviews.Reconnect");
    }

    public Servlet() {
        register();
    }

    public Object dispatcher(String request, String body) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String[] routes = request.split("/");
        var clazz = Class.forName(urlClass.get(request));

        // convert a json to a list of integer
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> param = gson.fromJson(body, listType);

        for (Method m: clazz.getMethods()) {
            if (m.getName().equals(routes[routes.length - 1])) {
                return m.invoke(clazz.getDeclaredConstructor().newInstance(), param);
            }
        }

        return null;
    }
}
