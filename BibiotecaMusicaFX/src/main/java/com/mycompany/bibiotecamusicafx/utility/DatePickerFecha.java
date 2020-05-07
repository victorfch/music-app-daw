package com.mycompany.bibiotecamusicafx.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class DatePickerFecha {
    public static Date convertirFechaDatePickerADate(LocalDate fecha) throws ParseException {
        String fechaNueva = fecha.format(DateTimeFormatter.ofPattern(Constantes.PATRON_FECHA));
        SimpleDateFormat formateadorFechas = new SimpleDateFormat(Constantes.PATRON_FECHA);
        return formateadorFechas.parse(fechaNueva);
    }
    
    
}
