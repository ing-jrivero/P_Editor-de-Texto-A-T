package Editor_de_texto_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.print.attribute.AttributeSet;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.TextArea;
import java.awt.Scrollbar;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.KeyAdapter;

public class editor_texto_2 extends JFrame {
	static //declarando el objeto JtextPane de la interfaz grafica
	JTextPane miarea;
	//Declarando objetos para darle formato a las letras en el JTextPane
	SimpleAttributeSet attrs_A ; //azul
	SimpleAttributeSet attrs_N ; //negro
	SimpleAttributeSet attrs_V ; //verde
	SimpleAttributeSet attrs_R ; //rojo
	
	//Declarando variables de control de funciones
	//en este caso para la revision del texto y saber si seguiamos recibiendo parametros de una variable,una condicional o un predicado.
	boolean variable;
	boolean condicional;
	boolean predicado;
	
	JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editor_texto_2 frame = new editor_texto_2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public editor_texto_2() {
		
        //en la siguiente linea de codigo configuramos el icono de la interfaz grafica
		setIconImage(Toolkit.getDefaultToolkit().getImage(editor_texto_2.class.getResource("/Editor_de_texto_2/page_edit.png")));
		setTitle("Editor de Texto");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		//creamos un objeto JMenuBar
		JMenuBar menuBar = new JMenuBar();
		//creamos los objetos JMenu
		JMenu abrir=new JMenu("Abrir");
		JMenu guardar=new JMenu("Guardar");
		JMenu formato=new JMenu("Formato");
		JMenu revisar=new JMenu("Revisar");
		JMenu cerrar=new JMenu("Cerrar");
		//creamos los objetos JMenuItem
		JMenuItem abrir_aut=new JMenuItem("Abrir Local");
		abrir.add(abrir_aut);
		JMenuItem abrir_file=new JMenuItem("Buscar Archivo");
		abrir.add(abrir_file);
		JMenuItem guardar_aut=new JMenuItem("Guardar Local");
		guardar.add(guardar_aut);
		JMenuItem guardar_file=new JMenuItem("Guardar Archivo");
		guardar.add(guardar_file);
		JMenuItem tamaño=new JMenuItem("Tamaño");
		formato.add(tamaño);
		JMenuItem revisar_tokens=new JMenuItem("Tokens");
		revisar.add(revisar_tokens);
		JMenuItem cerrar_file=new JMenuItem("Cerrar Programa");
		cerrar.add(cerrar_file);
		
		//asignamos un evento para los objetos JMenuItem
		GestionMenu_a1 abrir_local=new GestionMenu_a1();
		abrir_aut.addActionListener(abrir_local);
		GestionMenu_a abrir_ruta=new GestionMenu_a();
		abrir_file.addActionListener(abrir_ruta);
		GestionMenu_g1 guardar_local=new GestionMenu_g1();
		guardar_aut.addActionListener(guardar_local);
		GestionMenu_g guardar_arc=new GestionMenu_g();
		guardar_file.addActionListener(guardar_arc);
		GestionMenu_f formato_tam=new GestionMenu_f();
		tamaño.addActionListener(formato_tam);
		//
		GestionMenu_r_tokens revisar_tokens_evento=new GestionMenu_r_tokens();
		revisar_tokens.addActionListener(revisar_tokens_evento);
		//
		GestionMenu_c cerrar_ventana=new GestionMenu_c();
		cerrar_file.addActionListener(cerrar_ventana);
		
		//agregamos los objetos JMenu al objeto JMenuBar
		menuBar.add(abrir);
		menuBar.add(guardar);
		menuBar.add(formato);
		menuBar.add(revisar);
		menuBar.add(cerrar);
		//agrgamos el objeto JMenuBar al panel para que asi pueda ser visible en la interfaz grafica	
		panel.add(menuBar);
		
		//inicializamos el JTextPane
		miarea=new JTextPane();
		
		
		//creamos un metodo para detectar las teclas presionadas en el JtextPane
		miarea.addKeyListener(new KeyAdapter() {
			/*en un principio pensabamos utilizar este metodo para llevar el control de los datos introducidos en el JTextPane pero luego nos dimos cuenta
			que habia otro metodo mejor para la funcion de este caso.*/
			@Override
			public void keyPressed(KeyEvent e) {
				//esta parte del codigo no se utiliza en la ejecucion en este momento pero no quicimos eliminarla por que puede ser util para funciones posteriores
				String datos;
				datos=miarea.getText();
			
				if(e.getKeyCode()==KeyEvent.VK_ENTER) 
				{
				//este if colocaba un punto al final de cada sentencia si es que no lo tenia puesto
				/*	if(datos.charAt(datos.length()-1)!='.') 
					{
						try {
							miarea.getStyledDocument().insertString(datos.length()-1,".", attrs1);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}*/
				}
			
                
			}
			
			//Creando metodos para detectar las teclas liberadas 
			@Override
			public void keyReleased(KeyEvent e) {
				//aqui creamos una variables tipo string la cual captura todos los datos introducidos hasta el momento en el que se libera una tecla
				String datos;
				datos=miarea.getText();
				
				//inicializamos los objetos que nos ayudaran a cambiar el color de las letras segun sea el caso
				attrs_A = new SimpleAttributeSet();
				StyleConstants.setForeground(attrs_A, Color.BLUE);
				
				attrs_N = new SimpleAttributeSet();
				StyleConstants.setForeground(attrs_N, Color.BLACK);
				
				attrs_V = new SimpleAttributeSet();
				StyleConstants.setForeground(attrs_V, Color.GREEN);
				
				attrs_R = new SimpleAttributeSet();
				StyleConstants.setForeground(attrs_R, Color.RED);
				
				
				/*Este primer if checa si la ultima tecla registrada es un '(' o si la variable predicado esta true
				 * la logica que usamos en este if fue si ya se habia registrado un '(' entonces debia cambiar a true la variable predicado, asi cual quier tecla 
				 * introducida luego se cambiaria al color rojo hasta que la variable predicado volviera a ser false*/
				if(datos.charAt(datos.length()-1)=='('||predicado==true) 
				 {
				
					 predicado=true;
					 /*al ser verdadera la sentencia if, en esta linea declaramos el cambio de estilo del formato de las letra en el JTextPane y le indicamos que cambie el estilo
					 desde la ultima tecla regitrada*/
 					 miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_R, true);
					/*En el dado caso que se fuera a terminar el predicado que se estaba introduciendo en el JTextPane, se creo este if que verifica si la antepenultima
					 * tecla registrada era un ')' en ese caso predicado se declara false y se regresa el formato de color negro */	
 					 if(datos.charAt(datos.length()-2)==')') 
						{
							predicado=false;
							miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_N, true);
						}
				
				 }
				/*En este if checa si las teclas liberadas coenciden con ese margen de numeros del codigo ASCII
				 * ya que todos los numeros que entran en ese margen se supone que son puras letras mayusculas pero luego no servia del todo bien
				 * asi que decidi agregar otro if para comprobar si la letra esra mayuscula o minuscula */
				else if(e.getKeyCode()>=65&&e.getKeyCode()<=90||variable==true){//inicio con letras mayusculas
					 String letra=""+e.getKeyChar();//este string se creo solo para poder comparar un string con un char
					 /*lo que se hace aqui es tener un string con la tecla liberada y se comprara con la misma tecla pero aplicando un metodo para comvertirla
					  * en mayuscula si son iguales la letra original deberia de ser mayuscula*/
					 if(letra.equals(letra.toUpperCase())||variable==true) 
					 {
						/*en este if checamos si la antepenultima tecla liberada es un '_' ya que si este es el caso por la sintaxis podria ser una variable */	
						 if(datos.charAt(datos.length()-2)=='_'||variable==true) 
						 {
							/*en este if controlamos cuando finaliza una varible ya que al dar un espaco o salto de linea ya no entra en la sintaxis de una variable*/ 
							 if(datos.charAt(datos.length()-1)==' '||datos.charAt(datos.length()-1)==KeyEvent.VK_ENTER) 
							 {
								 //como ya no estamos buscando una vartiable declaramos false a variable
								 variable=false;
							 }else 
							 {
								 /*en el caso de que no se introduzcan espacion ni saltos de linea declaramos en true a variable para comenzar a buscar letra que puedan ser variables
								  y cambiaoms el color de letra de verde*/
								 variable=true;
		    					
							   	miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-2, miarea.getStyledDocument().getLength(), attrs_V, true);
													
							 }
								
						 }
									
										
						 
					 }
					 
						
						}
				 
				/*Este if revisa si se ha liberado la tecla '-' y si es asi revisa si la antepenultima telca liberada es ':' asi podremos saber si se forma la sintaxis de la condicional*/
				else if(e.getKeyCode()==45||condicional==true){//guion
					if(datos.charAt(datos.length()-2)==':'||condicional==true)//la parte del "||condicional" en ambos if por el momento no se utilizan
					{
					//	condicional=true;
						//aqui solo cambiamos el color a azul del texto
						miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-2, miarea.getStyledDocument().getLength(), attrs_A, true);
								
							}
						}
					
					//en este if checamos si se ha liberado una coma
				else if(e.getKeyCode()==KeyEvent.VK_COMMA){//coma
					//si es asi se cambia el color a azul
						miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_A, true);
						}
				//en este if se revisa si se a liberado un punto
						else if(e.getKeyCode()==KeyEvent.VK_PERIOD){//punto
							//si es asi se cambia el color a azul
							miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_A, true);
							}
				/*en este if checamos si se ha liberado la tecla de espacio esto lo ocupamos para poder terminar ciertas condiciones de las reglas de la sintaxis
				y asi poder volver a poner el texto en color negro*/
				else if(datos.charAt(datos.length()-1)==' ') {
                    //en este if checamos si variable es true, si es asi la volvermos false y cambiamos el color a negro				
					if(variable==true){ 
						
							miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_N, true);
							variable=false;
					}
					//en este if checamos si condicional es true, si es asi lo volvemos false y cambiamos el color a negro
					if(condicional==true) 
					{
						miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_N, true);
						condicional=false;
				
					}
					
				}
				//por ultimo en este else si se liber cualquier otra tecla diferente de las antes mencionadas cambia el color a negro
				else 
				{	
			miarea.getStyledDocument().setCharacterAttributes(miarea.getStyledDocument().getLength()-1, miarea.getStyledDocument().getLength(), attrs_N, true);
			}
		//		System.out.println(datos);
		//		System.out.println("Ultimo dato:"+datos.charAt(datos.length()-1));
				
			//	System.out.println(miarea.getStyledDocument().getLength());
		
			}
		});
		JScrollPane sp = new JScrollPane(miarea);
		
		getContentPane().add(sp,BorderLayout.CENTER);
		
		
	}
	
		
	
	
	/*en este programa se crearon varias clases para definir los eventos de los objetos del programa*/
	//en esta clase se define como va a ser la funcion Abrir Local
private class GestionMenu_a1 implements ActionListener {
		
		FileReader fr;
		String linea;
	@Override
	public void actionPerformed(ActionEvent e) {
		String ruta="prueba.txt";
		File archivo = new File (ruta);
		
		try {
			
			fr = new FileReader (archivo);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Archivo no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
		BufferedReader br = new BufferedReader(fr);
		try {
			linea = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		miarea.setText(linea);
		
	}
}
    //en esta clase se define la funcion Abrir Archivo
	private class GestionMenu_a implements ActionListener {
		
		FileReader fr;
		String linea;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String ruta="";
		
		ruta=JOptionPane.showInputDialog(null, "Intrudusca la ruta del fichero", "Abrir Fichero",JOptionPane.INFORMATION_MESSAGE);
		File archivo = new File (ruta);
		
		try {
			
			fr = new FileReader (archivo);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Archivo no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			}
		BufferedReader br = new BufferedReader(fr);
		try {
			linea = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		miarea.setText(linea);
		
	}
}
	//en esta clase se define la funcion Guardar Local
	private class GestionMenu_g1 implements ActionListener {
		FileWriter fichero = null;
        PrintWriter pw = null;
        
		public void actionPerformed(ActionEvent e) {
			String archivo=miarea.getText();
		//	System.out.println(archivo);
		        try
		        {
		            fichero = new FileWriter("prueba.txt");
		            pw = new PrintWriter(fichero);
		            pw.println(archivo);
		        } catch (IOException e1) {
		            e1.printStackTrace();
			}
		        if (null != fichero)
					try {
						fichero.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        JOptionPane.showMessageDialog(null, "Archivo Guardado", "Guardado", JOptionPane.INFORMATION_MESSAGE);
				
	}
	}
	//en esta clase se define la funcion Guardar Archivo
	private class GestionMenu_g implements ActionListener {
		FileWriter fichero = null;
        PrintWriter pw = null;
        
		public void actionPerformed(ActionEvent e) {
			String archivo=miarea.getText();
		//	System.out.println(archivo);
			String ruta="";
			
			ruta=JOptionPane.showInputDialog(null, "Intrudusca la ruta del fichero", "Guardar Fichero",JOptionPane.INFORMATION_MESSAGE);
			
		        try
		        {
		            fichero = new FileWriter(ruta);
		            pw = new PrintWriter(fichero);
		            pw.println(archivo);
		        } catch (IOException e1) {
		            e1.printStackTrace();
			}
		        if (null != fichero)
					try {
						fichero.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        JOptionPane.showMessageDialog(null, "Archivo Guardado", "Guardado", JOptionPane.INFORMATION_MESSAGE);
				
	}
	}
	//en esta clase se define la funcion tamaño
	private class GestionMenu_f implements ActionListener {
		
        
		public void actionPerformed(ActionEvent e) {
			int tamaño;
			tamaño=Integer.parseInt(JOptionPane.showInputDialog(null,"Integre Tamaño de Letra","Tamaño de Letra",JOptionPane.INFORMATION_MESSAGE));
			miarea.setFont(new Font("Arial",Font.PLAIN,tamaño));
				
	}
	}
	//en esta clase se define la funcion cerrar
	private class GestionMenu_c implements ActionListener {
		

	@Override
	public void actionPerformed(ActionEvent e) {
        
        if(JOptionPane.showConfirmDialog (null, "Deseas Cerrar El Programa?","Cerrar",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }else {
              
            }
          }
		
		
	}
	//en esta clase se define la funcion revisar tokens
		private static class GestionMenu_r_tokens implements ActionListener {
			
			//variables para analizar el texto delJtextPane con la funcion analizar
			static int tamaño=100;//le damos un tamaño de strings que podemos evaluar ya que n sabes cual ser el limite
			//inicializamos los vectores
			static String[] tabla_token=new String [tamaño];
			static String[] tabla_tipo=new String [tamaño];
			//usaremos la variable n para saber cuantos registros validos hemos hecho de tokens 
			//para asi solo recorrer los vectores hasta esa posicion
			static int n;
			//esta variable la usamos para completar el token del predicado
			static String p2;
			//metodo del evento
			public void actionPerformed(ActionEvent e) {
				n=0;//cada vez que se corra el metodo se inicializa en 0 a n
				p2="";//cada vez que se corra el metodo se inicializa un espacio vacio a p2
				inicializar_tabla();//los vectores con carateres que tomaremos como vacios
				/*en esta parte capturamos todos los datos que contiene el JtextPane y los separamos por saltos de lineas*/
				String [] lineas=miarea.getText().split("\\r\\n|\\n|\\r");
				//luego en este for separamos cada linea por espacios y revisamos cada string para saber en que patron coincide
				for(int i=0;i<lineas.length;i++) 
				{
					String [] tokens=lineas[i].split(" ");
					revisar(tokens);
				}
				//inicializamos el frame tabla y le pasamos los datos que ocupa para llenar la tabla			
				JFrame mimarco=new MarcoTabla(tabla_token, tabla_tipo, n);
				mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				mimarco.setVisible(true);
				//mostrar_tablas();
					
		}
			

			//metodos para el boton analizar
			public static void inicializar_tabla()
			{
				//en este metodo llenamos ambos vectores con @ esto para nosotros es como si fuera un registro no valido
				//asi cuando queramos imprimir los valores validos sabremos hasta que posicion son los registros validos 
				for(int i=0;i<tamaño;i++) 
				{
						tabla_token[i]="@";
						tabla_tipo[i]="@";
					
				}
			}
			//en este metodo vamos a comprar los tokens para ver en que patron coinciden
			public static void revisar(String [] tokens) 
			{
				//recorremos los token de la linea que se paso como parametro
				for(int i=0;i<tokens.length;i++) 
				{
					//guardamos el token en otro string para trabajarlo mejor
					String token_linea=tokens[i];
					//comparamos el token por el metodo esPredicado si se evalua true entra en el if
					if(esPredicado(token_linea)) 
					{
						//limpiamos lo que haya en el string p2
						p2="";
						/*en este casi se analizo si el token tenia un ( pero no sabemos en que token se cierra el parentesis
						 * asi que el ps2 y numero, nos va a ser util para buscar las parte de predicado que nos faltan
						 * con numero vamos a posisiconarmos el el siguiente token al que estamos analizando*/
						int numero=i+1;
						//en este metodo buscamos la otra parte del token
						buscar_p2(numero,tokens);
						//una vez encontrado guardamos el token actual y juntamos con lo que se haya encontrado con p2
						tabla_token[n]=token_linea+p2;
						//guardamos el tipo de token en su vector correspondiente
						tabla_tipo[n]="Predicado";
						//en 'n' llevamos la cuenta de las posiciones en las que hemos guardado datos
						//asi que como aqui guardamos datos no movemos a la siguiente posicion
						n++;
					}
					//aqui evaluamos si es variable
					if(esVariable(token_linea)) 
					{
						//si es variable hacemos lo mismo que en el caso anterior
						tabla_token[n]=token_linea;
						tabla_tipo[n]="Variable";
						n++;
					}
					//aqui evaluamos si es condicional
					if(esCondicional(token_linea)) 
					{
						//en este caso de igual manera guardamos lel token y sumamos 1 a n
						tabla_token[n]=token_linea;
						tabla_tipo[n]="Condicional";
						n++;
					}
					
					
				}
				
			}
			//en este metodo vamos a buscar y guardar la otra parte del predicado
			public static void buscar_p2(int n,String [] tokens) 
			{
				for(int j=n;j<tokens.length;j++) 
				{
					//lo que hacemos es buscar el token siguiente que validamos como predicado
					//luego usamos este patron para encontrar un token con un cierre de parentesis
					Pattern pat = Pattern.compile(".*[)].*");
					Matcher mat = pat.matcher(tokens[j]);
				
					if(mat.matches()) 
					{
						//si encontramos el cierre significa que la ultima parte asi que guardamos esa parte en p2 con un espacio
						//y nos salimos de metodo
						p2+=" "+tokens[j];
						return;
					}
					else 
					{
						//si el token actual no tiene un cierre igual lo guardamos en p2 con un espacio
						//y seguimos buscando el cierre de parentesis				
						p2+=" "+tokens[j];
				
					}
				}
				
			}
			//en este metodo evaluamos si es predicado
			public static boolean esPredicado(String token) 
			{
				//comparamos si el token empieza con minuscula y tiene un parentesis abierto
				Pattern pat = Pattern.compile("^[a-z].*[(].*");
			     Matcher mat = pat.matcher(token);
			     if (mat.matches()) {
			    	 //si es asi regresamos true
			         return true;
			     } else {
			    	 //si no regresamos false
			    	 return false;
			     }

				
			}
			//aqui validamos si el token es variable
			public static boolean esVariable(String token) 
			{
				//comparamos si el token inciacia con _ o mayusculas			
				Pattern pat = Pattern.compile("^[_A-Z]");
			     Matcher mat = pat.matcher(token);
			     if (mat.find()) {
			    	 //si es asi regresamos true
			         return true;
			     } else {
			    	 //si no regresamos false
			    	 return false;
			     }
			     
						
			}
			//en este motodo validamos si es condicional
			public static boolean esCondicional(String token) 
			{
				//aqui revisamos si el token lleva : seguidos de - 
				Pattern pat = Pattern.compile(".*[:][-].*");
			     Matcher mat = pat.matcher(token);
			     if (mat.matches()) {
			    	 //si es igual al patron regresamos true
			         return true;
			     } else {
			    	 //si no regresamos false
			    	 return false;
			     }
				
			}
			//este metodo imprimia por consola el valor de los vectores 
			//solo fue hecho para la revicion de datos antes de la implementacion de la ventana de la tabla
			public static void mostrar_tablas() 
			{
				for(int i=0;i<tabla_tipo.length;i++) 
				{
					//solo recorremos el vector tabla y revisamos que si el valor en esa posicion es diferente a @ imprimimos el valor
					if(tabla_tipo[i]!="@") 
					{
						System.out.println("Token ="+tabla_token[i]+" "+"Tipo = "+tabla_tipo[i]);
					}
					else 
					{
						//si es un arroba dejamos de imprimir datos
						return;
					}
					
				}
			}
			//fin de los metodo analizar

			
		}
	
	
	
}

