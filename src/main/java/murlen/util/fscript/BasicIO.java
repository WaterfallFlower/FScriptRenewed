
package murlen.util.fscript;

import java.io.*;
import java.util.*;

/**
 * <p>BasicIO - an simple IO Subclass of FScript</p>
 * <p>
 * <I>Copyright (C) 2000 murlen.</I></p>
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
 * @author murlen\
 *CVSTEST
 
 */
public class BasicIO extends FScript {
    
    private final Object[] files;
    
    /**Constructor*/
    public BasicIO() {
        super();
        files = new Object[25];
    }
    
    /**
     *<p> Overridden from FScript implements the following FScript functions </p>
     *
     * <p> note that this only provides very basic IO facilities,
     * line by line read/write
     * to files, and stdio read write.  There is a maximum of 25 open files</p>
     * <p> <b>(void) println(param...)</b> - write to stdout -
     * takes variable parameter list </p>
     * <p> <b>string readln() </b> - reads a string from stdin </p>
     * <p> <b>int open(string filename,string mode) </b> -
     * opens a file 'filename' for
     * reading (mode="r") or writing (mode="w") returns an integer which is
     * used in future calls. Returns -1 on >25 files opened </p>
     * <p> <b>string read(fp) </b> - reads one line from previously openened file
     * </p>
     * <p> <b>void write(fp,param...) - writes concatination of all params to one
     * line of file </p>
     */
    @Override
    public Object callFunction(String name, ArrayList<Object> param) throws FSException {
        switch (name) {
            //(void) println(param.....)
            case "println": {
                StringBuilder s= new StringBuilder();
                for (Object o : param) {
                    s.append(o);
                }
                System.out.println(s);
                break;
            }

            //string readln()
            case "readln": {
                try {
                    return new BufferedReader(new InputStreamReader(System.in)).readLine();
                } catch (IOException e)  {
                    throw new FSException(e.getMessage());
                }
            }

            //int open(string file,string mode)
            case "open": {
                int n;
                try {
                    for(n = 0; n < 25; n++) {
                        if (files[n] == null) {
                            if (param.get(1).equals("r")) {
                                files[n] = new BufferedReader(new FileReader((String)param.get(0)));
                                break;
                            } else if (param.get(1).equals("w")) {
                                files[n] = new BufferedWriter(new FileWriter((String)param.get(0)));
                                break;
                            } else throw new FSException("open expects 'r' or 'w' for modes");
                        }
                    }
                } catch (IOException e)  {
                    throw new FSException(e.getMessage());
                }
                return (n < 25) ? n : -1;
            }

            //(void)close(int fp)
            case "close": {
                int n = (Integer) param.get(0);
                if (files[n] == null) throw new FSException("Invalid file number passed to close");

                try {
                    if (files[n] instanceof BufferedWriter) {
                        ((BufferedWriter)files[n]).close();
                    } else {
                        ((BufferedReader)files[n]).close();
                    }
                    files[n] = null;
                } catch (IOException e) {
                    throw new FSException(e.getMessage());
                }
            }

            //(void) write(params....)
            case "write": {
                StringBuilder s = new StringBuilder();
                for(int n = 1; n < param.size(); n++)
                    s.append(param.get(n));

                int n = (Integer) param.get(0);
                if (files[n] == null) throw new FSException("Invalid file number passed to write");
                if (!(files[n] instanceof BufferedWriter)) throw new FSException("Invalid file mode for write");

                try {
                    ((BufferedWriter)files[n]).write(s.toString(), 0, s.length());
                    ((BufferedWriter)files[n]).newLine();
                } catch (IOException e) {
                    throw new FSException(e.getMessage());
                }
            }

            //string read(int fp)
            case "read": {
                int n = (Integer) param.get(0);

                if (files[n] == null)
                    throw new FSException("Invalid file number passed to read");
                if (!(files[n] instanceof BufferedReader))
                    throw new FSException("Invalid file mode for read");

                try {
                    String s = ((BufferedReader)files[n]).readLine();
                    //dodge eof problems
                    if (s == null) s = "";
                    return s;
                } catch (IOException e) {
                    throw new FSException(e.getMessage());
                }
            }

            //int eof(fp)
            case "eof": {
                int n = (Integer) param.get(0);
                if (files[n] == null) throw new FSException("Invalid file number passed to eof");
                try(BufferedReader br = (BufferedReader)files[n]) {
                    br.mark(1024);
                    if (br.readLine() == null) {
                        return 1;
                    } else {
                        br.reset();
                        return 0;
                    }
                } catch (IOException e) {
                    throw new FSException(e.getMessage());
                }
            }

            default: super.callFunction(name,param);
        }
        return 0;
    }
}
