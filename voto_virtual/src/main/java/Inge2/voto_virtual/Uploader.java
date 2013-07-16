package Inge2.voto_virtual;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@SuppressWarnings("serial")
public class Uploader  implements Receiver, SucceededListener{
	public File file;
	private VerSig v = new VerSig("113530880");
	private ventanaFirmaDigital laVentana;	
	private String nombreArchivo;
	public void setVentana(ventanaFirmaDigital v){
		laVentana = v;
	}
	
	@Override
	public void uploadSucceeded(SucceededEvent event) {
		System.out.println("Se subio correctamente");		
		boolean b = v.verify(nombreArchivo);
		if(b){
			laVentana.setLabel("Se comprobo la identidad");			
		}else{
			laVentana.setLabel("No se comprobo la identidad");		
		}	
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		nombreArchivo = filename;
		 // Create upload stream
        FileOutputStream fos = null;
        try {
            file = new File("C:\\temp\\" + filename);
            fos = new FileOutputStream(file);
            //System.out.println("FIle "+filename + "mimeType "+mimeType);
        } catch (final java.io.FileNotFoundException e) {
            Notification.show(
                    "No se pudo abrir el archivo<br/>", e.getMessage(),
                    Notification.TYPE_ERROR_MESSAGE);
            return null;
        }
        return fos; 
	}
	
	public String getRoute(){
		return file.getPath();		
	}

}
