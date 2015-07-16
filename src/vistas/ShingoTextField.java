package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class ShingoTextField extends JTextField{

	private static final long serialVersionUID = 251109684337585419L;
	private Font $font_placeholder;
	private Color $color_foreground;
	private Color $placeholder_foreground = new Color(160, 160, 160);
    private boolean $hay_texto; 
    
    
    public ShingoTextField(int $longitud) {
        super($longitud);
        setPreferredSize(new Dimension($longitud,25 ));
        
      
    }  
    
    public Color getPlaceholderForeground() {
        return $placeholder_foreground;
    }
 
    public void setPlaceholderForeground(Color placeholderForeground) {
        this.$placeholder_foreground = placeholderForeground;
    }
 
    public boolean hayTexto() {
        return $hay_texto;
    }
 
    public void setHayTexto(boolean textWrittenIn) {
        this.$hay_texto = textWrittenIn;
    }
    
//    public void setEnable(boolean bool){
//    	this.setEnable(bool);
//    }
 
    public void setPlaceholder(final String text) { 
        this.customizeText(text);        
        this.addMouseListener(new MouseAdapter() {
        	  @Override
              public void mouseClicked(MouseEvent e){
        		  if (getText().equals(text)) {
        			  setText("");
                      setFont(new Font(getFont().getFamily(), Font.PLAIN, getFont().getSize()));
                      setForeground(Color.WHITE);	
        		  }                  
    		  setHayTexto(true);                  
              }
		});

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {           	
                if (!hayTexto()) {
                	setText("");                	               
                }
            }
 
            @Override
            public void focusLost(FocusEvent e) {            
                if (getText().trim().length() == 0) {
                	customizeText(text);  
                }
            }
        }); 
    }    
    
    
    
 
    private void customizeText(String text) {
        setText(text);
        /**If you change font, family and size will follow
         * changes, while style will always be italic**/
        setFont(new Font(getFont().getFamily(), Font.ITALIC, getFont().getSize()));
        setForeground(getPlaceholderForeground());
        setHayTexto(true);
    }    
    
    
    @Override
    public void setFont(Font f) {
        super.setFont(f);
        if (!hayTexto()) {
        	set$font_placeholder(f);
        }
    }
 
    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (!hayTexto()) {
        	setColor_foreground(fg);
        }
    }
    
	public Font get$font_placeholder() {
		return $font_placeholder;
	}

	public void set$font_placeholder(Font $font_placeholder) {
		this.$font_placeholder = $font_placeholder;
	}

	public Color getColor_foreground() {
		return $color_foreground;
	}

	public void setColor_foreground(Color color_foreground) {
		this.$color_foreground = color_foreground;
	}
	
	
}
