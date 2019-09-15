package org.jakim.petclinic.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class IndexControllerTest
{

    IndexController indexController;

    @Before
    public void setUp( )
            throws Exception
    {
        this.indexController = new IndexController( );
    }

    @Test
    public void testMockMVC( )
            throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup( indexController )
                                         .build( );

        mockMvc.perform( get( "/" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "index" ) );

        mockMvc.perform( get( "" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "index" ) );
    }


    @Test
    public void testIndex( )
    {
        assertThat( indexController.index( ),
                    is( equalTo( "index" ) ) );
    }
}