package net.waterfallflower.fscriptrenewed.common.math;

import murlen.util.fscript.BasicExtension;
import murlen.util.fscript.FSException;
import murlen.util.fscript.FSUnsupportedException;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ExtensionMath extends BasicExtension {
    @Override
    public Object getVar(String name) throws FSException {
        switch (name) {
            case "math_PI": return Math.PI;
            case "math_E": return Math.E;

            default: throw new FSUnsupportedException(name);
        }
    }

    @Override
    public Object callFunction(String name, ArrayList<Object> params) throws FSException {
        switch (name) {
            case "abs": {
                if(params.size() != 1)
                    throw new FSException("Wrong amount of arguments, func -> " + name + "\n params -> " + params);
                return Math.abs((double) params.get(0));
            }

            case "floor": {
                if(params.size() != 1)
                    throw new FSException("Wrong amount of arguments, func -> " + name + "\n params -> " + params);
                return Math.floor((double) params.get(0));
            }

            case "ceil": {
                if(params.size() != 1)
                    throw new FSException("Wrong amount of arguments, func -> " + name + "\n params -> " + params);
                return Math.ceil((double) params.get(0));
            }

            case "round": {
                if(params.size() != 1)
                    throw new FSException("Wrong amount of arguments, func -> " + name + "\n params -> " + params);
                return Math.round((double) params.get(0));
            }

            default: throw new FSUnsupportedException(name);
        }
    }

    @Override
    public void setVar(String name, Object value) throws FSException {
        switch (name) {
            case "math_PI":
            case "math_E": {
                throw new FSException("Cannot set constant value \"" + name + "\"");
            }

            case "math_Unused": {

            }

            throw new FSUnsupportedException(name);
        }
    }
}
