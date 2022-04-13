package net.waterfallflower.fscriptrenewed.common.unsafe;

import murlen.util.fscript.BasicExtension;
import murlen.util.fscript.FSException;
import murlen.util.fscript.FSUnsupportedException;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Deprecated
public class ExtensionUnsafe extends BasicExtension {
    private static Unsafe unsafe;

    @Override
    public Object callFunction(String name, ArrayList<Object> params) throws FSException {
        switch (name) {
            case "unsafeLoad": {
                try {
                    Field f = Unsafe.class.getDeclaredField("theUnsafe");
                    f.setAccessible(true);
                    unsafe = (Unsafe) f.get(null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return null;
            }

            case "getStaticByte": {
                if(params.size() != 2)
                    throw new FSUnsupportedException(name + " - wrong amount of arguments");
                String className = (String) params.get(0);
                String fieldName = (String) params.get(1);

                try {
                    Class<?> f = Class.forName(className);
                    Field c = f.getDeclaredField(fieldName);
                    return unsafe.getByte(unsafe.staticFieldOffset(c));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            default: throw new FSUnsupportedException(name);
        }
    }
}
