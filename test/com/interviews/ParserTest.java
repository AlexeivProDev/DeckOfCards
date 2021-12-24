/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interviews;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexe
 */
public class ParserTest {
    
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
   
    

    @Test
    public void valid() {
       
        assertTrue(Parser.compiler("[]()"));
        
    }
    
    @Test
    public void invalid(){
        
        assertFalse(Parser.compiler("[(])"));
    }
     
    
     @Test
    public void valid1() {
       
        assertTrue(Parser.compiler("abc [xyz]middle (hello)end "));
        
    }
    
    @Test
    public void invalid2(){
        
        assertFalse(Parser.compiler("abc [\n( ]xyz)end"));
    }
    
    @Test
    public void invalidNull(){
        
        assertFalse(Parser.compiler(null));
    }
     
    @Test
    public void invalidEmpty(){
        
        assertFalse(Parser.compiler(""));
        assertFalse(Parser.compiler(" "));
        assertFalse(Parser.compiler("  "));
    }
    
}
