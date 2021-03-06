package murlen.util.fscript.api;

import murlen.util.fscript.FSException;

/**
 * FSVarExtension - simple variable extension to plug into FSFastExtension
 * <p>
 * <I>Copyright (C) 2002 </I></p>
 * <p>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.</p>
 * <p>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.</p>
 *
 * <p>You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA </p>
 *
 * @author Joachim Van der Auwera
 *
 * modifications by Joachim Van der Auwera
 * 02.08.2002 started
 */

public interface FSVarExtension {
    
    /**
     * <p>getVar is called whenever a variable is read in FScript that has
     * not been defined within the script iteslf</p>
     * @param name the variable name
     * @return the value of the variable (as one of FScript's supported object
     * types)
     **/
    Object getVar(String name) throws FSException;
    
    /**
     * <p>setVar is called whenever a variable is written to in FScript that has not
     * been defined within the script itself</p>
     * @param name the variable name
     * @param value the value to assign to the variable
     **/
    void setVar(String name, Object value) throws FSException;
    
}
