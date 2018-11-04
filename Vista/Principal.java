/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */

package Vista;



import Controlador.Controladorprincipal;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.edisoncor.gui.panel.Panel;

@SuppressWarnings("serial")
public class Principal extends JFrame{

    private JScrollPane Menu;// Panel de contenedor de un jtree
    private Panel EscritorioPrincipal; // panel principal y header
    private JButton cerrarsesion, salirboton; // botones cerrar, salir y ... 
    private JPanel MenuPrincipal, jPanel1, footer,header ; // Jpanel de menuprincipal, footer . 
    private JLabel hora;
    private Label usuario,NomUsuario,tipodeusuario,Tipousuario, EstacionEquipo, Estacion,fecha;
    private JTree menu;
    private	JTextField buscarraiz;
    protected static int idUsuario;
    protected String Seleccion;
    
    public static Controladorprincipal controlador;
    
    public void setController(Controladorprincipal controlador){
    	Principal.controlador = controlador;
    }

    public static Controladorprincipal getController(){
      return Principal.controlador;
    }

    private void Oyentes(){

      menu.addTreeSelectionListener(controlador);
      menu.addTreeExpansionListener(controlador);
      salirboton.addActionListener(controlador);  
      cerrarsesion.addActionListener(controlador);

    }

    public org.edisoncor.gui.panel.Panel getPanel1(){
        return EscritorioPrincipal;
    }

    public JButton getSalir(){

        return salirboton;

    }

    public void setSalir(JButton salirboton){

        this.salirboton = salirboton;

    }

    public JButton getCerrarSesion(){

        return cerrarsesion;

    }

    public void setCerrarSesion(JButton cerrarsesion){

        this.cerrarsesion = cerrarsesion;

    }

    public JPanel getMenuPrincipal(){

        return MenuPrincipal;

    }

    public void setMenuPrincipal(JPanel MenuPrincipal){

        this.MenuPrincipal = MenuPrincipal;

    }

    public static void setIdUsuario(int idUsuario){

        Principal.idUsuario = idUsuario;

    }

    public static int getIdUsuario(){

       return idUsuario;

    }

    public JLabel getHora(){

       return hora;

    }

    public void setHora(JLabel hora){

       this.hora = hora;

    }

    public Label getFecha(){

       return fecha;

    }

    public void setFecha(Label fecha){

       this.fecha = fecha;

    }

    public Label getEstacion() {

       return Estacion;

    }

    public void setEstacion(Label Estacion){

       this.Estacion = Estacion;

    }

    public Label getNomUsuario(){

       return NomUsuario;

    }

    public void setNomUsuario(Label NomUsuario){

       this.NomUsuario = NomUsuario;

    }

    public Label getTipousuario(){

       return Tipousuario;

    }

    public void setTipousuario(Label Tipousuario){

       this.Tipousuario = Tipousuario;

    }

    public JTree getMenu() {

    return menu;

  }

    public void setMenu(JTree menu) {

    this.menu = menu;

  }
    
    public Principal(){
	    this.VentanaPrincipal(); 
	    setController(new Controladorprincipal(this));
	    this.Oyentes();
	    setTitle("SIGPROME");
	    setLocationRelativeTo(null);
	    fechahora();
	    Estacion();
	    this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
//				footer.removeAll();
//				viewfooter();
//				footer.repaint();
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
//			footer.removeAll();
//			viewfooter();
//			
//			footer.repaint();
				
			}
			   
		   });
    }

   
   private void viewmenu() {
	   	   
	   Menu = new JScrollPane();
	   MenuPrincipal = new JPanel();
	   MenuPrincipal.setBackground(Color.WHITE);
	   MenuPrincipal.setAlignmentX(0.0F);
	   MenuPrincipal.setAlignmentY(0.0F);
	   MenuPrincipal.setEnabled(false);
	   MenuPrincipal.setFocusTraversalPolicyProvider(true);
	   MenuPrincipal.setFont(new Font("Times New Roman", 0, 18));
	   MenuPrincipal.setMaximumSize(new Dimension(32767, 700));
	   MenuPrincipal.setOpaque(false);
	   
	   Menu.setBackground(new Color(255, 255, 255));
	   Menu.setForeground(new Color(255, 255, 255));
	   Menu.setAlignmentX(0.0F);
	   Menu.setAlignmentY(0.0F);
	   Menu.setPreferredSize(new Dimension(230,0));
	   Menu.setMinimumSize(Menu.getPreferredSize());
	   
	   	jPanel1 = new JPanel();
	    jPanel1.setBackground(new java.awt.Color(0, 102, 153));
	    jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	    jPanel1.setAlignmentX(0.0F);
	    jPanel1.setAlignmentY(0.0F);
	    jPanel1.setAutoscrolls(true);
	    jPanel1.setPreferredSize(new java.awt.Dimension(260, 30));
	    jPanel1.setRequestFocusEnabled(false);
	    menu = new JTree();
	    menu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	    menu.setForeground(new java.awt.Color(255, 255, 255));

	    javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
	    javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Archivos");
	    javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Patología");

	   

	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo Orden");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Vías");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo Servicios");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Paciente");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo Paciente");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo Empleado");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo Producto");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Presentación");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Potencia");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Principio activo");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Examen");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Estudios Especiales");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Medicamentos");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Opc de Servicios Administrativos");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Estados de Proceso");
	    treeNode2.add(treeNode3);
	    treeNode1.add(treeNode2);
	    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Procesos");
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Servicio");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Triage");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Consulta");
	    treeNode2.add(treeNode3);
	    treeNode1.add(treeNode2);
	    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Reportes");
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Historia Médica");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Morbilidad");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Administrativo");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Emergencia");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Reembolso");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fe de vida");
	    treeNode2.add(treeNode3);
	    treeNode1.add(treeNode2);
	    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Configuración");
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Administrar Usuario");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tipo de usuario");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Especialidad");
	    treeNode2.add(treeNode3);
	    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Mantenimiento");
	    javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Respaldo BD");
	    treeNode3.add(treeNode4);
	    treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Bitacora");
	    treeNode3.add(treeNode4);
	    treeNode2.add(treeNode3);
	    treeNode1.add(treeNode2);
	    
	    menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    menu.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
	    menu.setToolTipText("");
	    menu.setDoubleBuffered(true);
	    menu.setLargeModel(true);
	    menu.setMaximumSize(new java.awt.Dimension(92, 3267));
	    menu.setRootVisible(false);
	    menu.setToggleClickCount(1);
	    menu.setVisibleRowCount(1);
	    
	    Menu.setViewportView(menu);
    
	    EscritorioPrincipal.add(Menu,BorderLayout.WEST);
   }
 
   private void viewfooter() {
	   footer = new JPanel();
	   
	   footer.setPreferredSize(new Dimension(0,20));
	   footer.setMinimumSize(footer.getPreferredSize());
	   footer.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
	   usuario = new Label();
	   NomUsuario = new Label();
	   tipodeusuario = new Label();
	   Tipousuario = new Label();
	   EstacionEquipo = new Label();
	   Estacion = new Label();
	   fecha = new Label();
	   hora = new JLabel();
	   
	   int nro = EscritorioPrincipal.getWidth();
	   
	   System.out.println("ancho " + nro);
	   usuario.setFont(new java.awt.Font(Font.SERIF, 1, 12));
	   usuario.setPreferredSize(new Dimension(1050/8,20));
	   usuario.setText("Usuario:");
	   
	   NomUsuario.setFont(new java.awt.Font(Font.SERIF, 1, 12));
	   NomUsuario.setPreferredSize(new Dimension(1050/8,20));
	   
	   tipodeusuario.setFont(new java.awt.Font("Dialog", 1, 12));
	   tipodeusuario.setPreferredSize(new Dimension(1050/8,20));
	   tipodeusuario.setText("Tipo Usuario:");
	   
	   Tipousuario.setFont(new java.awt.Font(Font.SERIF, 1, 12));
	   Tipousuario.setPreferredSize(new Dimension(1050/8,20));
	   
	   EstacionEquipo.setFont(new java.awt.Font(Font.SERIF, 1, 12));
	   EstacionEquipo.setPreferredSize(new Dimension(1050/8,20));
	   EstacionEquipo.setMinimumSize(EstacionEquipo.getPreferredSize());
	   EstacionEquipo.setText("Estación:");
	   
	   Estacion.setFont(new java.awt.Font(Font.SERIF, 2, 12));
	   Estacion.setPreferredSize(new Dimension(1050/8,20));

	   Estacion.setMinimumSize(Estacion.getPreferredSize());
	   Estacion.setName("");
	   
	   fecha.setFont(new java.awt.Font(Font.SERIF, 1, 12));
	   fecha.setPreferredSize(new Dimension(1050/5,20));
	   fecha.setMinimumSize(fecha.getPreferredSize());
	   
	   hora.setFont(new java.awt.Font(Font.SERIF, 2, 12));
	   hora.setPreferredSize(new Dimension(1050/8,20));
	   
	   footer.add(usuario);
	   footer.add(NomUsuario);
	   footer.add(tipodeusuario);
	   footer.add(Tipousuario);
	   footer.add(EstacionEquipo);
	   footer.add(Estacion);
	   footer.add(fecha);
	   footer.add(hora);
	   
	   EscritorioPrincipal.add(footer,BorderLayout.SOUTH);
   }
   private void viewcenter() {
	   JPanel center = new JPanel();
	   center.setOpaque(false);
	   center.setLayout(new BorderLayout());
	   JPanel botones = new JPanel();
	   botones.setOpaque(false);
	   botones.setPreferredSize(new Dimension(0,40));
	   	
	   cerrarsesion = new JButton();
	   salirboton = new JButton();
	   	cerrarsesion.setFont(new java.awt.Font("Times New Roman", 0, 18)); 
	    cerrarsesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); 
	    cerrarsesion.setText("Cerrar Sesión");
	    cerrarsesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    cerrarsesion.setPreferredSize(new Dimension(200,30));
	    salirboton.setBackground(new java.awt.Color(204, 51, 51));
	    salirboton.setFont(new java.awt.Font("Times New Roman", 0, 18)); 
	    salirboton.setForeground(new java.awt.Color(255, 255, 255));
	    salirboton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png")));
	    salirboton.setText("Salir del sistema");
	    salirboton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    salirboton.setPreferredSize(new Dimension(200,30));
	    
	    botones.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    
	    botones.add(cerrarsesion);
	    botones.add(salirboton);
	   center.add(botones,BorderLayout.SOUTH);
	   EscritorioPrincipal.add(center,BorderLayout.CENTER);
   }
   private void VentanaPrincipal(){
    
	capturartamanopantalla();
   
    EscritorioPrincipal = new org.edisoncor.gui.panel.Panel();
    EscritorioPrincipal.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/fondoprincipal.png")));
    EscritorioPrincipal.setLayout(new BorderLayout(0,0));
    
    header = new JPanel();
    Color fond = new Color(0,0,0,50);
    header.setBackground(fond);
    header.setPreferredSize(new Dimension(0,100));
    header.setMaximumSize(header.getPreferredSize());
    header.setMinimumSize(header.getPreferredSize());
    header.setLayout(new BorderLayout(0,0));
//    header.setOpaque(false);

    Panel headerizq = new org.edisoncor.gui.panel.Panel();
    headerizq.setPreferredSize(new Dimension(474,100));
    headerizq.setMinimumSize(headerizq.getPreferredSize());
    headerizq.setMaximumSize(headerizq.getPreferredSize());
    headerizq.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/headerizq.png")));
    headerizq.setOpaque(false);

    
    header.add(headerizq,BorderLayout.WEST);
    
    Panel headerder = new org.edisoncor.gui.panel.Panel();
    headerder.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/logoinstitucional.png")));
    headerder.setPreferredSize(new Dimension(201,80));

    JPanel header2 = new JPanel();
    header2.setOpaque(false);
    header2.setLayout(new FlowLayout(FlowLayout.TRAILING));
    header2.add(headerder);
    header2.setPreferredSize(new Dimension(250,100));
    
    header.add(header2,BorderLayout.EAST);
    
    Panel headercent = new org.edisoncor.gui.panel.Panel();
    headercent.setOpaque(false);
  
    headercent.setLayout(new BorderLayout());
    headercent.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/headercent.png")));
    headercent.setPreferredSize(new Dimension(371,100));
    
    JPanel header1 = new JPanel();
    header1.setOpaque(false);
    header1.setPreferredSize(new Dimension(371,100));
    header1.add(headercent);
    
    header.add(header1,BorderLayout.CENTER);
    
    EscritorioPrincipal.add(header,BorderLayout.NORTH);
    
    viewmenu();  
    viewfooter();
    viewcenter();
    
    add(EscritorioPrincipal);
}

	private void fechahora(){

	Runnable iterafecha = new Iterafechahora();
      
	Thread t = new Thread(iterafecha);

	t.setPriority(Thread.NORM_PRIORITY);
       
	t.start();

}

	class Iterafechahora implements Runnable{



	@Override

	public void run() {

		boolean update = true;

//	    DateFormat hor = new SimpleDateFormat("HH:mm:ss");

//	    DateFormat fech = new SimpleDateFormat("dd/MM/yyyy");

	    

	    while(update) {

	    	getHora().setText("Hora: "+ new SimpleDateFormat("HH:mm:ss").format(new Date()));

		    getFecha().setText("Fecha: "+ new SimpleDateFormat("dd/MM/yyyy").format(new Date()));  

	    }

	}

}

	private void Estacion(){

    try {

        InetAddress localHost = InetAddress.getLocalHost();

        this.getEstacion().setText(localHost.getHostName()); 

    } catch (UnknownHostException ex) {

        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);

    }

}

    @SuppressWarnings("unchecked")

    
    public String getSeleccion() {

        return Seleccion;

    }

    public Label getNombUsuario(){

        return NomUsuario;

    }

    public void setNombusuario(Label NomUsuario){
        this.NomUsuario = NomUsuario;
    }
   
    public void capturartamanopantalla(){


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setPreferredSize(new Dimension(screenSize.width,screenSize.height - 20));
    this.setMinimumSize(new Dimension(this.getPreferredSize().width-300, this.getPreferredSize().height-300));
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setExtendedState(Frame.MAXIMIZED_BOTH);
    setBackground(new java.awt.Color(255, 255, 255));
}
    
	public static void main(String[] args) {
		   java.awt.EventQueue.invokeLater(() -> {
		          Principal ini  = new Principal();
		          ini.setVisible(true);
		        });
	}

	
}

 

