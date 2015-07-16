package vistas;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JDialog;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import entidades.ClienteRegistrado;

public class Graficos extends JDialog{

	private static final long serialVersionUID = -6242828211941516852L;
	private JFreeChart $graficos;
	private DefaultCategoryDataset Datos;
	public Graficos(){
        setTitle("Como Hacer Graficos con Java");
        setSize(800,600);
        setLocationRelativeTo(null);	    
       
    }

    public void setCharGrafico(
			List<Map<Integer, ClienteRegistrado>> $map_clientes) {
    	Datos = new DefaultCategoryDataset();
    	for (Map<Integer, ClienteRegistrado> keys : $map_clientes)
		     for (Entry<Integer, ClienteRegistrado> entry : keys.entrySet()){
		    	 Datos.addValue(entry.getKey(),"",entry.getValue().getNombres());
		     }    
    	
    	$graficos = ChartFactory.createBarChart("Ranking Clientes no cumplidores",
    			"Clientes", "Reservas no Cumplidas", Datos,
    			PlotOrientation.VERTICAL, false, true, false);
    	$graficos.setBackgroundPaint(Color.gray);
    	// Create an NumberAxis
    	CategoryPlot $plot = $graficos.getCategoryPlot();
    	NumberAxis rangeAxis = (NumberAxis) $plot.getRangeAxis();
    	rangeAxis.setRange(0, 50);
    	ChartPanel Panel = new ChartPanel($graficos);
    	
    	add(Panel);
    }


    
	


	
}
