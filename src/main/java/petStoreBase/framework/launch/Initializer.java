package petStoreBase.framework.launch;

import petStoreBase.framework.Initialize.Initialize;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Initializer extends Initialize {
    private static Initialize init;

    public Initializer(String environment) {
        super(environment);
    }

    public static Initialize getInitializer() {
        if (init == null) {

            try {
                Constructor<Initialize> constructor = (Constructor<Initialize>) Initialize.class.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object o = constructor.newInstance();
                init = (Initialize) o;
                return init;
            }catch (NoSuchMethodException e) {
                e.printStackTrace();
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return init;

    }


}
