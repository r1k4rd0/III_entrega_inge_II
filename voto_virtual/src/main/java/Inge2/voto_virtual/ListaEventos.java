package Inge2.voto_virtual;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class ListaEventos extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button button_2;
	@AutoGenerated
	private ComboBox comboBox_2;
	@AutoGenerated
	private Button button_1;
	@AutoGenerated
	private ComboBox comboBox_1;
	@AutoGenerated
	private Label label_1;
	private Conexion conexion;
	private int idEv;
	private String valor;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ListaEventos() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		llenarCombo();
		// TODO add user code here
	}
	
	private void llenarCombo(){
		
		comboBox_2.addItem("Elección");
		comboBox_2.addItem("Plebiscito");
	
		button_2.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				valor = (String) comboBox_2.getValue();
				darNombre();
			}
			
		  });
	}

	private void darNombre(){
		if(valor=="Plebiscito"){
			String consulta = "SELECT nombre, id_evento FROM plebiscito;";
			 conexion = new Conexion("rebeca", "ramirez.arroyo");
			 if(conexion!=null){
				  SQLContainer resultado = conexion.consulta(consulta);
				  if(resultado != null){
					  comboBox_1.setInputPrompt("Seleccione un evento");
					  comboBox_1.setContainerDataSource(resultado);
					  comboBox_1.setItemCaptionPropertyId("nombre");
					  
					  button_1.addClickListener(new Button.ClickListener() {
							
							@Override
							public void buttonClick(ClickEvent event) {
								// TODO Auto-generated method stub
								final Item item = comboBox_1.getItem(comboBox_1.getValue());
								 String idEvString = item.getItemProperty("id_evento").toString();
								 idEv = Integer.parseInt(idEvString);
								 MyVaadinUI.getCurrent().setContent(new MostrarInfoEvento(idEv,valor));
					
							}
							
						  });

					  
				  }
			 }
			
		}
		if(valor=="Elección"){
			String consulta = "SELECT motivo, id_evento FROM eleccion;";
			 conexion = new Conexion("rebeca", "ramirez.arroyo");
			 if(conexion!=null){
				  SQLContainer resultado = conexion.consulta(consulta);
				  if(resultado != null){
					  comboBox_1.setInputPrompt("Seleccione un evento");
					  comboBox_1.setContainerDataSource(resultado);
					  comboBox_1.setItemCaptionPropertyId("motivo");
					  
					  button_1.addClickListener(new Button.ClickListener() {
							
							@Override
							public void buttonClick(ClickEvent event) {
								// TODO Auto-generated method stub
								final Item item = comboBox_1.getItem(comboBox_1.getValue());
								String idEvString = item.getItemProperty("id_evento").toString();
								 idEv = Integer.parseInt(idEvString);
								 MyVaadinUI.getCurrent().setContent(new MostrarInfoEvento(idEv,valor));
					
							}
							
						  });

					  
				  }
			 }
		}
		
		
		
		
		
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
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Seleccione un tipo de evento");
		mainLayout.addComponent(label_1, "top:80.0px;left:80.0px;");
		
		// comboBox_1
		comboBox_1 = new ComboBox();
		comboBox_1.setImmediate(false);
		comboBox_1.setWidth("-1px");
		comboBox_1.setHeight("-1px");
		mainLayout.addComponent(comboBox_1, "top:336.0px;left:100.0px;");
		
		// button_1
		button_1 = new Button();
		button_1.setCaption("Consultar");
		button_1.setImmediate(true);
		button_1.setWidth("-1px");
		button_1.setHeight("-1px");
		mainLayout.addComponent(button_1, "top:340.0px;left:320.0px;");
		
		// comboBox_2
		comboBox_2 = new ComboBox();
		comboBox_2.setImmediate(false);
		comboBox_2.setWidth("-1px");
		comboBox_2.setHeight("-1px");
		mainLayout.addComponent(comboBox_2, "top:140.0px;left:102.0px;");
		
		// button_2
		button_2 = new Button();
		button_2.setCaption("Escoger");
		button_2.setImmediate(true);
		button_2.setWidth("-1px");
		button_2.setHeight("-1px");
		mainLayout.addComponent(button_2, "top:140.0px;left:310.0px;");
		
		return mainLayout;
	}

}
