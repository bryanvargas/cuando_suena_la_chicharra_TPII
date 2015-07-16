package vistas;

import java.io.File;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ShingoUtils {//expresiones regulares para validar todos los campos del sistema
	
	
	
	private static final String $PATTERN_TEXTO = "^[A-Z][a-z]*([ ][A-Z][a-z]+)*";
	private static final String $PATTERN_NUMERICO = "^[1-9]\\d{5,9}";
	private static final String $PATTERN_DINERO = "^[1-9]\\d*{2}\\.\\d{2}";
    private static final String $PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String $PATTERN_TELEFONO = "^\\(?\\d{2}\\)?[\\s\\.-]?\\d{4}[\\s\\.-]?\\d{4}$";
    
    /**
     * Validate given email with regular expression.
     * 
     * @param email
     *            email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile($PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    
    public static boolean validarNumeros(String numeros){
    	Pattern pattern = Pattern.compile($PATTERN_NUMERICO);
    	 Matcher matcher = pattern.matcher(numeros);
         return matcher.matches();
    }
    public static boolean validarMonto(String numeros){
    	Pattern pattern = Pattern.compile($PATTERN_DINERO);
    	 Matcher matcher = pattern.matcher(numeros);
         return matcher.matches();
    }
    public static boolean validarTexto(String texto){
    	Pattern pattern = Pattern.compile($PATTERN_TEXTO);
   	 Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
    
    public static boolean validarTelefono(String texto){
    	Pattern pattern = Pattern.compile($PATTERN_TELEFONO);
   	 Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
    
	public static  void  shingoMensaje(Object $msj,String $titulo,int $plainMessage) {		
		JOptionPane.showMessageDialog(null, $msj,$titulo,$plainMessage);		
	}
	
	

	
	 static ImageIcon createIcon(String path) {
		URL url = System.class.getResource(path);
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
	 
	 public static String getExtension(File f) {
	        String ext = null;
	        String s = f.getName();
	        int i = s.lastIndexOf('.');

	        if (i > 0 &&  i < s.length() - 1) {
	            ext = s.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }

	
	    protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = ShingoUtils.class.getResource(path);	      
       	if (imgURL != null) 
		       return new ImageIcon(imgURL);	        
	      
           return null;
	       
	    }
	
}
