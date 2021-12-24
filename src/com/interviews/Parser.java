package com.interviews;

/**
 *
 * @author alexe
 */
public class Parser {

    /*
        Your faced with the following problem:
        You need to write a "compiler", which is a program that takes in some source code
        (some text) and needs to determine whether the source-code is "valid" or not
        if it's valid, we say that it "compiles"; if invalid, not
        This is a compiler for a simple programming language; its 'grammar' is just the 
        '()[]{}' and any other text is simply ignored; the compiler needs to make sure
        that the tokens match - so every '(' must have a subsequent ')', every '[' by
        a ']', etc. and you can't close a prior pair out-of-order
        e.g. "[]()" compiles, but "[(])" doesn't
     */
    public static boolean compiler(String source) {
        if (source == null || source.trim().isEmpty()) {
            return false;
        }

        source = source.trim();
        int len = source.length();

        char open = 0;
        char c;
        for (int i = 0; i < len; i++) {
            c = source.charAt(i);
            if (open == 0) {
                if (c == '(' || c == '{' || c == '['){
                    open = c;
                    continue;
                }
            }
            else{
                if (open == '[') {
                    if (c == ']') {
                        //valid
                        open = 0;
                        continue;
                    }
                    else if (c == '(' || c == '{') {
                        //invalid
                        return false;
                    }
                }
                if (open == '(') {
                    if (c == ')') {
                        //valid
                         open = 0;
                        continue;
                    }
                    else if (c == '[' || c == '{') {
                        //invalid
                        return false;
                    }
                }
                if (open == '{') {
                    if (c == '}') {
                        //valid
                         open = 0;
                        continue;
                    }
                    else if (c == '[' || c == '(') {
                        //invalid
                        return false;
                    }
                }
            }

        }

        return true;

    }
}
