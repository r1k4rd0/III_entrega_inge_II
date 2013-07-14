package Inge2.voto_virtual;

import java.text.SimpleDateFormat;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class CrearEleccion extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;
	@AutoGenerated
	private Label label_6;
	@AutoGenerated
	private Button button_3;
	@AutoGenerated
	private Button button_2;
	@AutoGenerated
	private Button button_1;
	@AutoGenerated
	private Label label_9;
	@AutoGenerated
	private PopupDateField popupDateField_6;
	@AutoGenerated
	private PopupDateField popupDateField_5;
	@AutoGenerated
	private Label label_8;
	@AutoGenerated
	private Label label_7;
	@AutoGenerated
	private PopupDateField popupDateField_4;
	@AutoGenerated
	private Label label_5;
	@AutoGenerated
	private Label label_4;
	@AutoGenerated
	private Label label_3;
	@AutoGenerated
	private Label label_2;
	@AutoGenerated
	private PopupDateField popupDateField_3;
	@AutoGenerated
	private PopupDateField popupDateField_2;
	@AutoGenerated
	private PopupDateField popupDateField_1;
	@AutoGenerated
	private TextArea textArea_1;
	@AutoGenerated
	private Label label_1;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CrearEleccion() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		textArea_1.focus();
		//Boton Regresar
		button_1.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    		MyVaadinUI.getCurrent().setContent(new VentanaPrincipal());
		    }
		});
		//Boton Aceptar
		button_2.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	if(insertarEvento()){
		    		MyVaadinUI.getCurrent().setContent(new VentanaPrincipal());
		    	}else{
		    		label_6.setValue("Error al crear el evento, favor revise los datos");
		    	}
		    }
		});
		//Boton definir padrón
		button_3.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	final Window w = new Window("Padrón electoral");
				w.setContent(new CargarPadron());
				w.setWidth("600px");
				w.setHeight("500px");
				w.center();
				//w.setClosable(false);
				// ui.getContent().setEnabled(false);
				MyVaadinUI.getCurrent().addWindow(w);
		    }
		});
	}
	
	public boolean insertarEvento(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String motivo = textArea_1.getValue();
		String f1 = popupDateField_1.getValue()!=null?sdf.format(popupDateField_1.getValue()):"";
		String f2 = popupDateField_2.getValue()!=null?sdf.format(popupDateField_2.getValue()):"";
		String f3 = popupDateField_3.getValue()!=null?sdf.format(popupDateField_3.getValue()):"";
		String f4 = popupDateField_4.getValue()!=null?sdf.format(popupDateField_4.getValue()):"";
		String f5 = popupDateField_5.getValue()!=null?sdf.format(popupDateField_5.getValue()):"";
		String f6 = popupDateField_6.getValue()!=null?sdf.format(popupDateField_6.getValue()):"";
		
		String sql = "INSERT INTO eleccion(motivo,reg_tend_inicio,reg_tend_fin,requisitos_inicio,requisitos_fin"+
		",votacion_inicio,votacion_fin) VALUES('"+motivo+"','"+f1+"','"+f2+"','"+f3+"','"+f4+"','"+f5+"','"+f6+"');";
		Conexion con = new Conexion("r1k4rd0","h3rr3r4");
		boolean exito = con.ejecutar(sql);
		//con.cerrar();
		return exito;		
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// absoluteLayout_2
		absoluteLayout_2 = buildAbsoluteLayout_2();
		mainLayout.addComponent(absoluteLayout_2, "top:40.0px;left:60.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("540px");
		absoluteLayout_2.setHeight("460px");
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("100.0%");
		label_1.setHeight("-1px");
		label_1.setValue("Nuevo evento \"Elección\"");
		absoluteLayout_2.addComponent(label_1,
				"top:20.0px;right:189.0px;left:191.0px;");
		
		// textArea_1
		textArea_1 = new TextArea();
		textArea_1.setImmediate(false);
		textArea_1.setWidth("440px");
		textArea_1.setHeight("-1px");
		absoluteLayout_2.addComponent(textArea_1, "top:80.0px;left:60.0px;");
		
		// popupDateField_1
		popupDateField_1 = new PopupDateField();
		popupDateField_1.setImmediate(false);
		popupDateField_1.setWidth("140px");
		popupDateField_1.setHeight("-1px");
		popupDateField_1.setTabIndex(1);
		absoluteLayout_2.addComponent(popupDateField_1,
				"top:200.0px;left:200.0px;");
		
		// popupDateField_2
		popupDateField_2 = new PopupDateField();
		popupDateField_2.setImmediate(false);
		popupDateField_2.setWidth("140px");
		popupDateField_2.setHeight("-1px");
		popupDateField_2.setTabIndex(2);
		absoluteLayout_2.addComponent(popupDateField_2,
				"top:200.0px;left:360.0px;");
		
		// popupDateField_3
		popupDateField_3 = new PopupDateField();
		popupDateField_3.setImmediate(false);
		popupDateField_3.setWidth("140px");
		popupDateField_3.setHeight("-1px");
		popupDateField_3.setTabIndex(3);
		absoluteLayout_2.addComponent(popupDateField_3,
				"top:240.0px;left:200.0px;");
		
		// label_2
		label_2 = new Label();
		label_2.setImmediate(false);
		label_2.setWidth("-1px");
		label_2.setHeight("-1px");
		label_2.setValue("Fecha inicial");
		absoluteLayout_2.addComponent(label_2, "top:176.0px;left:227.0px;");
		
		// label_3
		label_3 = new Label();
		label_3.setImmediate(false);
		label_3.setWidth("-1px");
		label_3.setHeight("-1px");
		label_3.setValue("Fecha final");
		absoluteLayout_2.addComponent(label_3, "top:176.0px;left:387.0px;");
		
		// label_4
		label_4 = new Label();
		label_4.setImmediate(false);
		label_4.setWidth("-1px");
		label_4.setHeight("-1px");
		label_4.setValue("Votación :");
		absoluteLayout_2.addComponent(label_4, "top:280.0px;left:60.0px;");
		
		// label_5
		label_5 = new Label();
		label_5.setImmediate(false);
		label_5.setWidth("-1px");
		label_5.setHeight("-1px");
		label_5.setValue("Descripción del evento (motivo):");
		absoluteLayout_2.addComponent(label_5, "top:62.0px;left:40.0px;");
		
		// popupDateField_4
		popupDateField_4 = new PopupDateField();
		popupDateField_4.setImmediate(false);
		popupDateField_4.setWidth("140px");
		popupDateField_4.setHeight("-1px");
		absoluteLayout_2.addComponent(popupDateField_4,
				"top:241.0px;left:360.0px;");
		
		// label_7
		label_7 = new Label();
		label_7.setImmediate(false);
		label_7.setWidth("-1px");
		label_7.setHeight("-1px");
		label_7.setValue("Cumplimiento requisitos:");
		absoluteLayout_2.addComponent(label_7, "top:240.0px;left:59.0px;");
		
		// label_8
		label_8 = new Label();
		label_8.setImmediate(false);
		label_8.setWidth("-1px");
		label_8.setHeight("-1px");
		label_8.setValue("Inscipción de tendencias:");
		absoluteLayout_2.addComponent(label_8, "top:200.0px;left:60.0px;");
		
		// popupDateField_5
		popupDateField_5 = new PopupDateField();
		popupDateField_5.setImmediate(false);
		popupDateField_5.setWidth("140px");
		popupDateField_5.setHeight("-1px");
		popupDateField_5.setTabIndex(3);
		absoluteLayout_2.addComponent(popupDateField_5,
				"top:277.0px;left:200.0px;");
		
		// popupDateField_6
		popupDateField_6 = new PopupDateField();
		popupDateField_6.setImmediate(false);
		popupDateField_6.setWidth("140px");
		popupDateField_6.setHeight("-1px");
		absoluteLayout_2.addComponent(popupDateField_6,
				"top:277.0px;left:360.0px;");
		
		// label_9
		label_9 = new Label();
		label_9.setImmediate(false);
		label_9.setWidth("-1px");
		label_9.setHeight("-1px");
		label_9.setValue("Etapas:");
		absoluteLayout_2.addComponent(label_9, "top:176.0px;left:40.0px;");
		
		// button_1
		button_1 = new Button();
		button_1.setCaption("<< Regresar");
		button_1.setImmediate(true);
		button_1.setWidth("180px");
		button_1.setHeight("-1px");
		absoluteLayout_2.addComponent(button_1, "top:411.0px;left:80.0px;");
		
		// button_2
		button_2 = new Button();
		button_2.setCaption("Aceptar >>");
		button_2.setImmediate(true);
		button_2.setWidth("180px");
		button_2.setHeight("-1px");
		absoluteLayout_2.addComponent(button_2, "top:411.0px;left:280.0px;");
		
		// button_3
		button_3 = new Button();
		button_3.setCaption("Definir padrón de votantes...");
		button_3.setImmediate(true);
		button_3.setWidth("190px");
		button_3.setHeight("-1px");
		absoluteLayout_2.addComponent(button_3, "top:339.0px;left:175.0px;");
		
		// label_6
		label_6 = new Label();
		label_6.setImmediate(false);
		label_6.setWidth("300px");
		label_6.setHeight("20px");
		label_6.setValue(" ");
		absoluteLayout_2.addComponent(label_6, "top:380.0px;left:120.0px;");
		
		return absoluteLayout_2;
	}

}
