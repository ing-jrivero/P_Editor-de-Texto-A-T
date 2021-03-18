package Editor_de_texto_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tabla extends JFrame {
	//toda esta clase se uso para probar el frame de la tabla pero como se va a iniciar de la otra ventana
	//se quito todo el uso de esta clase
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	/*	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla frame = new Tabla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		/*JFrame mimarco=new MarcoTabla();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mimarco.setVisible(true);
	*/}

	/**
	 * Create the frame.
	 */
/*	public Tabla() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tabla.class.getResource("/Editor_de_texto_2/List.png")));
		setTitle("Tabla de Tokens");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}*/
}
//esta clase es la que crea la tabla
 class MarcoTabla extends JFrame{
	 //este vector lo usamos para colocar el nombre a las columnas de la tabla
	 public static String [] columnas= {"Tokens","Tipo"};
	 //en esta matriz iran los datos de la tabla
	 public static Object [][] datosFila; 
	 //en estos 2 vectores se guardaran el vector que la clase reciba como parametro
	 static String [] tokens;
	 static String [] tipos;

	 //constructor de la clase
	public MarcoTabla(String [] n,String [] m,int i) 
	{
		//aqui guardamos los vectores que son enviados como parametros al contructor
		tokens=n;
		tipos=m;
		//inicializamos la matriz con el valor que le mandan al constructor(i)
		datosFila=new String [i][2];
		//en este metodo pasamos los datos que nos mandaron del contructor a la matriz de la tabla
		inicializando_tabla(i);
		//hacemos las configuraciones de la ventana 
		setTitle("Tabla Tokens");
		setBounds(300,300,400,200);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tabla.class.getResource("/Editor_de_texto_2/List.png")));
		JTable tabla=new JTable(datosFila,columnas);
		add(new JScrollPane(tabla),BorderLayout.CENTER);
		//creamos un boton para cerrar la ventana de la tabla
		JButton boton=new JButton("Atras");
		boton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) {
				
				//le asignamos el evento del boton
				dispose();
				
			}
			
		}
		);
		//agregamos el boton al frame
		add(boton,BorderLayout.SOUTH);
//		JPanel laminaboton=new JPanel();
//		add(laminaboton,BorderLayout.SOUTH);
	
	}
	//este metodo pasamos los valores que nos pasaron como parametros desde el contructor a la matriz de esta clase
	public static void inicializando_tabla(int p) 
	{
		for(int i=0;i<p;i++) 
		{
			//aqui guardamos los datos de los tokens
			datosFila[i][0]=tokens[i];
			//y aqui los tipos de los tokens
			datosFila[i][1]=tipos[i];
		}
		
	}
	
	
	}



 
 


