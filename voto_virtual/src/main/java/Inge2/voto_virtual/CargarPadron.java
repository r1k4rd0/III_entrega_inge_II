package Inge2.voto_virtual;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

import au.com.bytecode.opencsv.CSVReader;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

public class CargarPadron extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_4;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;
	@AutoGenerated
	private VerticalLayout verticalLayout_5;
	@AutoGenerated
	private Table table_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_4;
	@AutoGenerated
	private Upload upload_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private Label label_3;
	@AutoGenerated
	private VerticalLayout verticalLayout_3;
	@AutoGenerated
	private Label label_2;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private Embedded embedded_1;
	private static final long serialVersionUID = 1L;
	protected Subidor up = new Subidor();
	public int idPadron;
	
	/**
	 * Constructor por defecto. Construye la ventana de carga de padrón y
	 * escucha por acciones de usuario.
	 */
	public CargarPadron() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		upload_1.setButtonCaption("Subir padrón");
		upload_1.setReceiver(up);
		upload_1.addListener(up);
	}

	@SuppressWarnings("serial")
	class Subidor implements Receiver, SucceededListener {
		protected File padronTemp;

		@Override
		public OutputStream receiveUpload(String filename, String mimeType) {
			// Create upload stream
			FileOutputStream fos = null;
			try {
				String dirbase = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
				final String dirWeb = dirbase + "/WEB-INF/"; // /Ruta de carpeta WEB-INF

				padronTemp = new File(dirWeb + filename);
				fos = new FileOutputStream(padronTemp);
				System.out.println("Creó correctamente: "+ padronTemp.getAbsolutePath());
			} catch (final java.io.FileNotFoundException e) {
				Notification.show("No se pudo abrir el archivo",
						e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
				return null;
			}
			return fos;
		}

		@Override
		public void uploadSucceeded(SucceededEvent event) {
			try {
				/* Crear un contenedor a partir del archivo CSV */
				FileReader reader = new FileReader(padronTemp);
				IndexedContainer indexedContainer = crearContenedorCSV(reader);
				reader.close();

				/* Guardar la información del padrón en la Base de Datos */
				idPadron = guardarPadronEnBD(padronTemp.getAbsolutePath());

				/* Actualizar la tabla con el contenedor */
				table_1.setCaption(padronTemp.getName());
				table_1.setContainerDataSource(indexedContainer);
				table_1.setVisible(true);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// Subidor

	/**
	 * Utiliza http://opencsv.sourceforge.net/ para leer el contenido 
	 * del archivo CSV y crea un contenedor a partir de este.
	 * 
	 * @param reader
	 * @return container
	 * @throws IOException
	 */
	protected IndexedContainer crearContenedorCSV(Reader reader) throws IOException {
		IndexedContainer contenedor = new IndexedContainer();
		CSVReader lectorCSV = new CSVReader(reader);
		String[] columnas = null;
		String[] valor;
		while ((valor = lectorCSV.readNext()) != null) {
			if (columnas == null) {
				columnas = valor;
				asignarColumnas(contenedor, columnas);
			} else {
				agregarItem(contenedor, columnas, valor);
			}
		}
		lectorCSV.close();
		return contenedor;
	}

	/**
	 * Asigna los nombres de las columnas al contenedor.
	 * 
	 * @param container Contenedor a modificar
	 * @param columnas	Nombre de las columnas (Primera fila del archivo CSV)
	 */
	private static void asignarColumnas(IndexedContainer contenedor, String[] columnas) {
		for (String nombreColumna : columnas) {
			contenedor.addContainerProperty(nombreColumna, String.class, null);
		}
	}

	/**
	 * Agrega los items al contenedor.
	 * 
	 * @param container
	 * @param propertyIds
	 * @param fields
	 */
	private static void agregarItem(IndexedContainer contenedor, String[] columnas, String[] valores) {
		if (columnas.length != valores.length) {
			throw new IllegalArgumentException(
					"La cantidad de valores difiere del número de columnas");
		}
		Object itemId = contenedor.addItem();
		Item item = contenedor.getItem(itemId);
		for (int i = 0; i < valores.length; i++) {
			String columna = columnas[i];
			String valor = valores[i];
			item.getItemProperty(columna).setValue(valor);
		}
	}
	/**
	 * Guarda la información del padrón en la base de datos.
	 * @param ruta ruta en disco del padron
	 * @return id del padron insertado
	 */
	private static int guardarPadronEnBD(String ruta){
		Conexion con = new Conexion("r1k4rd0","h3rr3r4");
		return con.insertarPadron(ruta);
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
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		mainLayout.addComponent(verticalLayout_1, "top:20.0px;left:20.0px;");
		
		// verticalLayout_3
		verticalLayout_3 = buildVerticalLayout_3();
		mainLayout.addComponent(verticalLayout_3, "top:80.0px;left:20.0px;");
		
		// verticalLayout_4
		verticalLayout_4 = buildVerticalLayout_4();
		mainLayout.addComponent(verticalLayout_4, "top:120.0px;left:20.0px;");
		
		// verticalLayout_5
		verticalLayout_5 = buildVerticalLayout_5();
		mainLayout.addComponent(verticalLayout_5, "top:180.0px;left:20.0px;");
		
		// horizontalLayout_3
		horizontalLayout_3 = new HorizontalLayout();
		horizontalLayout_3.setImmediate(false);
		horizontalLayout_3.setWidth("240px");
		horizontalLayout_3.setHeight("43px");
		horizontalLayout_3.setMargin(false);
		mainLayout.addComponent(horizontalLayout_3, "top:400.0px;left:20.0px;");
		
		// horizontalLayout_4
		horizontalLayout_4 = new HorizontalLayout();
		horizontalLayout_4.setImmediate(false);
		horizontalLayout_4.setWidth("581px");
		horizontalLayout_4.setHeight("40px");
		horizontalLayout_4.setMargin(false);
		mainLayout.addComponent(horizontalLayout_4, "top:400.0px;left:20.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("580px");
		verticalLayout_1.setHeight("60px");
		verticalLayout_1.setMargin(false);
		
		// embedded_1
		embedded_1 = new Embedded();
		embedded_1.setImmediate(false);
		embedded_1.setWidth("100.0%");
		embedded_1.setHeight("100.0%");
		embedded_1.setSource(new ThemeResource("img/component/padron.jpg"));
		embedded_1.setType(1);
		embedded_1.setMimeType("image/jpg");
		verticalLayout_1.addComponent(embedded_1);
		verticalLayout_1.setExpandRatio(embedded_1, 1.0f);
		
		return verticalLayout_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_3() {
		// common part: create layout
		verticalLayout_3 = new VerticalLayout();
		verticalLayout_3.setImmediate(false);
		verticalLayout_3.setWidth("581px");
		verticalLayout_3.setHeight("40px");
		verticalLayout_3.setMargin(false);
		
		// label_2
		label_2 = new Label();
		label_2.setImmediate(false);
		label_2.setWidth("-1px");
		label_2.setHeight("-1px");
		label_2.setValue("Herramienta de carga del padrón electoral");
		verticalLayout_3.addComponent(label_2);
		verticalLayout_3.setComponentAlignment(label_2, new Alignment(48));
		
		return verticalLayout_3;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_4() {
		// common part: create layout
		verticalLayout_4 = new VerticalLayout();
		verticalLayout_4.setImmediate(false);
		verticalLayout_4.setWidth("580px");
		verticalLayout_4.setHeight("60px");
		verticalLayout_4.setMargin(false);
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		verticalLayout_4.addComponent(verticalLayout_2);
		
		// upload_1
		upload_1 = new Upload();
		upload_1.setImmediate(false);
		upload_1.setWidth("75.56%");
		upload_1.setHeight("-1px");
		verticalLayout_4.addComponent(upload_1);
		verticalLayout_4.setComponentAlignment(upload_1, new Alignment(48));
		
		return verticalLayout_4;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("20px");
		verticalLayout_2.setMargin(false);
		
		// label_3
		label_3 = new Label();
		label_3.setImmediate(false);
		label_3.setWidth("-1px");
		label_3.setHeight("-1px");
		label_3.setValue("Buscar y subir un archivo CSV con la información del padrón:");
		verticalLayout_2.addComponent(label_3);
		
		return verticalLayout_2;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_5() {
		// common part: create layout
		verticalLayout_5 = new VerticalLayout();
		verticalLayout_5.setImmediate(false);
		verticalLayout_5.setWidth("581px");
		verticalLayout_5.setHeight("220px");
		verticalLayout_5.setMargin(false);
		
		// table_1
		table_1 = new Table();
		table_1.setImmediate(false);
		table_1.setWidth("93.4%");
		table_1.setHeight("100.0%");
		table_1.setInvalidAllowed(false);
		verticalLayout_5.addComponent(table_1);
		verticalLayout_5.setExpandRatio(table_1, 1.0f);
		verticalLayout_5.setComponentAlignment(table_1, new Alignment(24));
		
		return verticalLayout_5;
	}
}
