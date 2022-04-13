package net.waterfallflower.fscriptrenewed.common;

import murlen.util.fscript.BasicExtension;
import murlen.util.fscript.BasicIO;
import murlen.util.fscript.FSFastExtension;
import murlen.util.fscript.api.FSArrayExtension;
import murlen.util.fscript.api.FSExtension;
import net.waterfallflower.fscriptrenewed.api.LibArrayExtension;
import net.waterfallflower.fscriptrenewed.api.LibExtension;
import net.waterfallflower.fscriptrenewed.api.LibFunctionExtension;
import net.waterfallflower.fscriptrenewed.api.LibVarExtension;
import net.waterfallflower.fscriptrenewed.common.math.ExtensionMath;
import net.waterfallflower.fscriptrenewed.common.unsafe.ExtensionUnsafe;

public enum EnumCoreLibs {
    MATH_LIB("math", new ExtensionMath()),
    UNSAFE_LIB("unsafe", new ExtensionUnsafe()),
    ;

    public final String LIB_NAME;
    public final Object[] EXTENSIONS;

    EnumCoreLibs(String libName, Object... extension) {
        this.LIB_NAME = libName;
        this.EXTENSIONS = extension;
    }

    public void access(BasicIO io) {
        FSFastExtension fastEx = new FSFastExtension();
        for(Object o : EXTENSIONS) {
            if(o instanceof LibExtension)
                io.registerExtension((FSExtension) o);
            if(o instanceof BasicExtension)
                io.registerExtension((BasicExtension) o);
            if(o instanceof LibArrayExtension)
                fastEx.addArrayExtension(((LibArrayExtension)o).getName(), (FSArrayExtension) o);
            if(o instanceof LibFunctionExtension)
                fastEx.addFunctionExtension(((LibFunctionExtension)o).getName(), (LibFunctionExtension) o);
            if(o instanceof LibVarExtension)
                fastEx.addVarExtension(((LibVarExtension)o).getName(), (LibVarExtension) o);
        }
        io.registerExtension(fastEx);
    }
}
