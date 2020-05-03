package com.mycompany.bibiotecamusicafx;

import java.io.IOException;
import javafx.event.ActionEvent;



public interface MenuInterface {
  
    public void irVentanaArtista(ActionEvent event) throws IOException;

    public void irVentanaAlbum(ActionEvent event) throws IOException;

    public void irVentanaInicio(ActionEvent event) throws IOException;

    public void irVentanaCancion(ActionEvent event) throws IOException;
}
