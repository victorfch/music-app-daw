package com.mycompany.bibiotecamusicafx.utility;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdaptadorFecha extends XmlAdapter<String, Date> {
    private final DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date unmarshal(String v) throws Exception {
        return Date.valueOf(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return formateador.format(v);
    }
}
