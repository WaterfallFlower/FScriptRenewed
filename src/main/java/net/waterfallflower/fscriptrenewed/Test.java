package net.waterfallflower.fscriptrenewed;

import murlen.util.fscript.BasicIO;
import murlen.util.fscript.FSException;
import net.waterfallflower.fscriptrenewed.common.EnumCoreLibs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, FSException {
        BasicIO basicIO = new BasicIO();

        try(BufferedReader bufferreader = new BufferedReader(new FileReader("D:\\Games\\GithubDesktop_2nd\\FScriptRenewed\\src\\main\\resources\\primitve_test1.script"))) {
            String s;
            while ((s = bufferreader.readLine()) != null) {
                if(s.startsWith("$")) {
                    s = s.trim();

                    if(s.startsWith("$import")) {
                        String loc = s.split(" ", 2)[1];
                        for(EnumCoreLibs libs : EnumCoreLibs.values())
                            if(loc.equals(libs.LIB_NAME)) {
                                System.out.println("Loading library " + libs.LIB_NAME);
                                libs.access(basicIO);
                            }
                    }

                    continue;
                }
                basicIO.loadLine(s);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        basicIO.run();
    }
}
