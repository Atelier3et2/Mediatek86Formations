package com.example.mediatek86formations.outils;

import static org.junit.Assert.*;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class MesOutilsTest {

    /**
     * Test la conversion d'une chaine Date en Date
     */
    @Test
    public void convertStringToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = formatter.parse("2020-12-28 22:00:29");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(date, MesOutils.convertStringToDate("2020-12-28 22:00:29", "yyyy-MM-dd hh:mm:ss"));


    }

    @Test
    public void testConvertStringToDate() {


    }

    /**
     * Test la conversion d'une chaine Date en String
     */
    @Test
    public void convertDateToString() {

        Date uneDate = new Date();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = date.format(uneDate);
        assertEquals(stringDate, MesOutils.convertDateToString(uneDate));

    }
}