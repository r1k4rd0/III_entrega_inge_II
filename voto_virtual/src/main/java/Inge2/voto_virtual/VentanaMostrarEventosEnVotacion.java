package Inge2.voto_virtual;

import java.sql.SQLException;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

/**
 * @author eduardo16
 * 
 */
@SuppressWarnings("serial")
public class VentanaMostrarEventosEnVotacion extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button button_4;
	@AutoGenerated
	private Button button_3;
	@AutoGenerated
	private Button button_2;
	@AutoGenerated
	private Button button_1;
	@AutoGenerated
	private TextField textField_2;
	@AutoGenerated
	private TextField textField_1;
	@AutoGenerated
	private Label label_3;
	@AutoGenerated
	private Table table_2;
	@AutoGenerated
	private Label label_2;
	@AutoGenerated
	private Label label_1;
	@AutoGenerated
	private Table table_1;
	private final String user = "eduardo";
	private final String passwd = "steiner.garro";
	private final String[] headers_1 = new String[] { "id_evento", "motivo",
			"votacion_inicio", "votacion_fin" };
	private final String[] headers_2 = new String[] { "id_evento", "nombre",
			"votacion_inicio", "votacion_fin" };
	private final String[] titulos1 = { "Numero de votacion", "Motivo",
			"Fecha de inicio", "Fecha de finalizacion" };
	private final String[] titulos2 = { "Numero de plebisito", "Nombre",
			"Fecha de inicio", "Fecha de finalizacion" };
    
	// private Table table_1;     
	private JDBCConnectionPool pool;
	private TableQuery tq;
	private SQLContainer container;
	private Conexion cbd;
	// private MyVaadinUI ui;
	private boolean enterFlag1 = false;
	private boolean enterFlag2 = false;
	private String ID_Usuario = "112345678";
	private String ID_Evento;
	private String laTabla;

	// Para el text field 1, cuando esta focus
	private FocusListener focus1 = new FocusListener() {
		@Override
		public void focus(FocusEvent event) {
			// TODO Auto-generated method stub
			enterFlag1 = true;
			enterFlag2 = false;
		}
	};
	
	public String getID_Evento(){
		return ID_Evento;		
	}

	public String getLaTabla(){		
		return laTabla;
	}
	
	public void setUsuario(String elID) {
		this.ID_Usuario = elID;
	}


	// Para el text field 2, cuando esta focus
	private FocusListener focus2 = new FocusListener() {
		@Override
		public void focus(FocusEvent event) {
			// TODO Auto-generated method stub
			enterFlag2 = true;
			enterFlag1 = false;
		}
	};

	/*
	 * private BlurListener blur2 = new BlurListener() {
	 * 
	 * @Override public void blur(BlurEvent event) { // TODO Auto-generated
	 * method stub label_2.setValue("BLUR"); enterFlag2 = false; } };
	 */
	// private ConexionBD cbd;
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public VentanaMostrarEventosEnVotacion() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		enterFlag1 = enterFlag2 = false;
		label_3.setValue("No hay evento seleccionado para visitar");
		cbd = new Conexion(user, passwd);
		table_1.setSelectable(true);
		table_1.setImmediate(true);
		table_2.setSelectable(true);
		table_2.setImmediate(true);
		this.setTextFieldShortCut();
		// table_1.addContainerProperty("Nombre", String.class, null);
		// TODO add user code here
		button_3.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				String nombreTab = getLaTabla();
				if(nombreTab=="Eleccion"){
				    MyVaadinUI.getCurrent().setContent(new RecibirVotoEleccion());
				}
				else{
				    MyVaadinUI.getCurrent().setContent(new RecibirVotoPlebiscito());
				}
			}
		});
		
		button_4.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    		MyVaadinUI.getCurrent().setContent(new VentanaPrincipal());
		    }
		});
		
		try {
			this.fillTheTables();
		} catch (SQLException e) {
		}
		
	}

	/**
	 * Agrega el shorcur Enter para buscar
	 */
	private void setTextFieldShortCut() {

		textField_1.addListener(focus1);
		textField_2.addListener(focus2);
		// textField_1.addListener(blur1);
		// textField_2.addListener(blur2);

		// button_1.setClickShortcut(KeyCode.ENTER);
		button_1.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (enterFlag1 == true) {
					if (secureCaption(textField_1)) {
						// label_3.setValue(textField_1.getValue());
						buscarEvento("eleccion", "motivo",
								textField_1.getValue());
						llenarBusquedaDeTable(table_1, true);
					}
				}
			}
		});

		button_2.setClickShortcut(KeyCode.ENTER);
		button_2.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (enterFlag2 == true) {
					if (secureCaption(textField_2)) {
						buscarEvento("plebiscito", "nombre",
								textField_2.getValue());
						llenarBusquedaDeTable(table_2, false);
					}
				} else {
					if (enterFlag1 == true) {
						if (secureCaption(textField_1)) {
							buscarEvento("eleccion", "motivo",
									textField_1.getValue());
							llenarBusquedaDeTable(table_1, true);
						}
					}
				}
			}
		});
	}

	private boolean secureCaption(TextField tf) {
		String data = tf.getValue();
		return (data.contains("'") || data.contains("\\") || data.contains("*")
				|| data.contains("*") || data.contains("&")
				|| data.contains("-") || data.contains("#") || data
					.contains(";")) ? false : true;
	}

	/**
	 * 
	 * @param t
	 *            , Tabla a llenar
	 * @param b
	 *            , Indica que headers usar
	 */
	private void llenarBusquedaDeTable(Table t, boolean b) {
		t.removeAllItems();
		if (container.firstItemId() != null) {
			t.setContainerDataSource(container);
			t.setEnabled(true);
		} else {
			t.setEnabled(false);
		}
		try {
			this.changeColumHeaders(t, b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Busca un evento por su nombre o motivo
	 * 
	 * @param table
	 *            , Tabla en la cual buscar
	 * @param colum
	 *            , argumento con el cual comparar
	 * @param evento
	 *            , argumento a buscar
	 */
	private void buscarEvento(String table, String colum, String evento) {
		container = cbd
				.consulta("select * from "
						+ table
						+ " where ("
						+ colum
						+ " like '"
						+ evento
						+ "' AND (votacion_inicio <= current_date AND votacion_fin >= current_date));");// new
	}

	/**
	 * Llena la tabla con datos de la base de datos
	 * 
	 * @throws SQLException
	 */
	private void fillTheTables() throws SQLException {

		this.makeConectionWith("eleccion");

		if (container != null) {
			table_1.setContainerDataSource(container);
		} else {
			this.firstFill(table_1, true);
			table_1.setEnabled(false);
		}

		this.changeColumHeaders(table_1, true);
		this.setListener1();

		this.makeConectionWith("plebiscito");

		if (container != null) {
			table_2.setContainerDataSource(container);
		} else {
			this.firstFill(table_2, false);
			table_2.setEnabled(false);
		}

		this.changeColumHeaders(table_2, false);

		this.setListener2();

	}

	/**
	 * Para evitar errores si no se logra extraer informacion se llena la tabla
	 * con vacios
	 * 
	 * @param t
	 *            , Tabla a ser llenada
	 * @param b
	 *            , boolean para saber que encabezados utilizar
	 */
	public void firstFill(Table t, boolean b) {
		String[] columnas = (b) ? headers_1 : headers_2;

		for (String c : columnas) {
			t.addContainerProperty(c, String.class, null);
		}
		t.addItem(new Object[] { "No hay resultados", " ", " ", " " },
				new Integer(1));
	}

	/**
	 * Hace una conexion con la base de datos
	 * 
	 * @param table
	 *            , nombre de la tabla a extraer
	 * @throws SQLException
	 */
	private void makeConectionWith(String table) throws SQLException {
		// tq = new TableQuery(table, pool);
		container = cbd
				.consulta("select * from "
						+ table
						+ ", persona_en_padron where ( (persona_en_padron.cedula = "+ID_Usuario+")"
				+"AND (plebiscito.padron = persona_en_padron.id_padron) AND (votacion_inicio <= current_date AND votacion_fin >= current_date));");// new

		// SQLContainer(tq);
	}

	/**
	 * Cambia los encabezados de las columnas
	 * 
	 * @param t
	 *            , tabla a cambiar sus encabezados
	 * @param b
	 *            , boolean para saber que encabezados colocar
	 * @throws SQLException
	 */
	private void changeColumHeaders(Table t, boolean b) throws SQLException {

		String[] h = (b) ? headers_1 : headers_2;
		String[] titulos = (b) ? titulos1 : titulos2;

		t.setVisibleColumns(h);
		Object[] ids1 = t.getVisibleColumns();

		for (int i = 0; i < ids1.length; ++i) {
			table_1.setColumnHeader(ids1[i], titulos[i]);
		}

	}

	/**
	 * Agrega un listener a l tabla para responder a la hora de hacer click en
	 * un elemento
	 * 
	 * @param t
	 *            , tabla a agregar el listener
	 */
	private void setListener1() {
		table_1.addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				Property itemProperty = event.getItem().getItemProperty(
						"id_evento");
				// itemProperty.getValue(); // TODO: Do something with this
				// value.
				String r = itemProperty.toString();
				String tabla = "Eleccion";
				if (cbd.consulta("select * from eleccion where (id_evento = "
						+ r
						+ " AND (votacion_inicio <= current_date AND votacion_fin >= current_date));") == null) {
					label_3.setValue("El evento dejo de estar disponible");
				} else {
					label_3.setValue("Tabla: " + tabla + " id " + r);
					ID_Evento = r;
					laTabla = tabla;
				}
				final Window w = new Window("Subwindow");
				w.setContent(new ventanaFirmaDigital());
				w.setWidth("600px");
				w.setHeight("280px");
				w.center();
				// w.setPositionX(200); w.setPositionY(100);
				// w.setClosable(false);
				// ui.getContent().setEnabled(false);
				MyVaadinUI.getCurrent().addWindow(w);
				// cbd.closePool();

			}
		});
	}

	/**
	 * Agrega un listener a l tabla para responder a la hora de hacer click en
	 * un elemento
	 * 
	 * @param t
	 *            , tabla a agregar el listener
	 */
	private void setListener2() {
		table_2.addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				Property itemProperty = event.getItem().getItemProperty(
						"id_evento");
				// itemProperty.getValue(); // TODO: Do something with this
				// value.
				String r = itemProperty.toString();
				String tabla = null;

				if (cbd.consulta("select * from plebiscito where (id_evento = "
						+ r
						+ " AND (votacion_inicio <= current_date AND votacion_fin >= current_date));") == null) {				
					label_3.setValue("El evento dejo de estar disponible");
				} else {
					label_3.setValue("Tabla: " + tabla + " id " + r);
					ID_Evento = r;
					laTabla = tabla;
				}
				final Window w = new Window("Subwindow");
				w.setContent(new ventanaFirmaDigital());
				w.setWidth("600px");
				w.setHeight("280px");
				w.center();
				// w.setPositionX(200); w.setPositionY(100);
				// w.setClosable(false);
				// ui.getContent().setEnabled(false);
				MyVaadinUI.getCurrent().addWindow(w);
				// cbd.closePool();

			}
		});
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("855px");
		mainLayout.setHeight("556px");
		
		// top-level component properties
		setWidth("855px");
		setHeight("556px");
		
		// table_1
		table_1 = new Table();
		table_1.setImmediate(false);
		table_1.setDescription("eleccion");
		table_1.setWidth("360px");
		table_1.setHeight("407px");
		mainLayout.addComponent(table_1, "top:100.0px;left:20.0px;");
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Elecciones en desarrollo");
		mainLayout.addComponent(label_1, "top:40.0px;left:29.0px;");
		
		// label_2
		label_2 = new Label();
		label_2.setImmediate(false);
		label_2.setWidth("-1px");
		label_2.setHeight("-1px");
		label_2.setValue("Plebiscitos en desarrollo");
		mainLayout.addComponent(label_2, "top:40.0px;left:470.0px;");
		
		// table_2
		table_2 = new Table();
		table_2.setImmediate(false);
		table_2.setWidth("360px");
		table_2.setHeight("407px");
		mainLayout.addComponent(table_2, "top:100.0px;left:465.0px;");
		
		// label_3
		label_3 = new Label();
		label_3.setImmediate(false);
		label_3.setWidth("-1px");
		label_3.setHeight("-1px");
		label_3.setValue("Label");
		mainLayout.addComponent(label_3, "top:20.0px;left:351.0px;");
		
		// textField_1
		textField_1 = new TextField();
		textField_1.setImmediate(false);
		textField_1.setWidth("-1px");
		textField_1.setHeight("-1px");
		textField_1.setInputPrompt("Buscar");
		mainLayout.addComponent(textField_1, "top:76.0px;left:159.0px;");
		
		// textField_2
		textField_2 = new TextField();
		textField_2.setImmediate(false);
		textField_2.setWidth("-1px");
		textField_2.setHeight("-1px");
		textField_2.setInputPrompt("Buscar");
		mainLayout.addComponent(textField_2, "top:76.0px;left:594.0px;");
		
		// button_1
		button_1 = new Button();
		button_1.setCaption("Buscar");
		button_1.setImmediate(true);
		button_1.setWidth("-1px");
		button_1.setHeight("-1px");
		mainLayout.addComponent(button_1, "top:76.0px;left:310.0px;");
		
		// button_2
		button_2 = new Button();
		button_2.setCaption("Buscar");
		button_2.setImmediate(true);
		button_2.setWidth("-1px");
		button_2.setHeight("-1px");
		mainLayout.addComponent(button_2, "top:76.0px;left:750.0px;");
		
		// button_3
		button_3 = new Button();
		button_3.setCaption("Votar");
		button_3.setImmediate(true);
		button_3.setWidth("100px");
		button_3.setHeight("-1px");
		mainLayout.addComponent(button_3, "top:530.0px;left:360.0px;");
		
		// button_4
		button_4 = new Button();
		button_4.setCaption("<< Regresar");
		button_4.setImmediate(true);
		button_4.setWidth("-1px");
		button_4.setHeight("-1px");
		mainLayout.addComponent(button_4, "top:530.0px;left:74.0px;");
		
		return mainLayout;
	}

}
