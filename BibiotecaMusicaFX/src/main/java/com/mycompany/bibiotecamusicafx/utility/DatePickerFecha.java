package com.mycompany.bibiotecamusicafx.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author victor
 */
public abstract class DatePickerFecha {
    public static Date convertirFechaDatePickerADate(LocalDate fecha) throws ParseException {
        String patron = "dd/MM/yyyy";
        String fechaNueva = fecha.format(DateTimeFormatter.ofPattern(patron));
        SimpleDateFormat formateadorFechas = new SimpleDateFormat(patron);
        Date fechaNacimiento = formateadorFechas.parse(fechaNueva);
        return fechaNacimiento;
    }
    
    
}
