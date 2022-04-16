package HistoriaMedica;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.io.File;

public class JFRAME extends JFrame {

	private JPanel contentPane;
	private JTextField textoCliente;
	private JTextField textoAtencion;
	private JTable tablaCliente;
	private JScrollPane scrollPaneCliente;
	private JTable tablaAtencion;
	private JScrollPane scrollPaneAtencion;
	private List<Cliente> clientes;
	private List<Atencion> Atenciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFRAME frame = new JFRAME();
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
	public JFRAME() {
		
		this.setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(145, 235, 221));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelCliente = new JLabel("Ingrese el Nro. DNI del cliente:");
		labelCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelCliente.setBounds(33, 18, 177, 20);
		contentPane.add(labelCliente);

		JButton botonBuscarCliente = new JButton("Buscar");
		botonBuscarCliente.setBounds(432, 18, 96, 21);
		contentPane.add(botonBuscarCliente);

		clientes = Datos.dataCLiente();
		Atenciones = Datos.dataAtenciones();

		botonBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textoAtencion.setEditable(true);
				String[][] contenidoTabla = new String[1][5];

				Cliente cliente = busqueda(textoCliente.getText(), clientes);

				contenidoTabla[0][0] = cliente.obtener_dni();
				contenidoTabla[0][1] = cliente.getFechaNacim();
				contenidoTabla[0][2] = cliente.getNombre();
				contenidoTabla[0][3] = cliente.getApellido();
				contenidoTabla[0][4] = cliente.getAtencion();
				tablaCliente.setModel(new DefaultTableModel(contenidoTabla,
						new String[] {
								"DNI", "F. NACIMIENTO", "NOMBRE", "APELLIDO", "ATENCIÓN"
						}));

			}
		});

		JButton JGuardar = new JButton("Ver datos");
		JGuardar.setBounds(410, 150, 120, 20);
		contentPane.add(JGuardar);
		JGuardar.setEnabled(false);
		// evento al clickear "guardar"
		JGuardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				GenerarTXT(e);

			}

		});

		JButton botonBuscarAtencion = new JButton("Buscar");
		botonBuscarAtencion.setBounds(446, 195, 96, 21);
		contentPane.add(botonBuscarAtencion);

		botonBuscarAtencion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JGuardar.setEnabled(true);
				String[][] contenidoTabla = new String[1][4];
				// table_1.getColumnModel().getColumn(3).setCellRenderer(new
				// TextAreaRenderer());
				// table_1.setRowHeight(0, 100);
				// table_1.setRowHeight(3, 100);
				// getContentPane().add(new JScrollPane(table_1));
				Atencion atencion = busquedaAtencion(textoAtencion.getText(), Atenciones);

				contenidoTabla[0][0] = atencion.getId();
				contenidoTabla[0][1] = atencion.getSintomas();
				contenidoTabla[0][2] = atencion.getDiagnostico();
				contenidoTabla[0][3] = atencion.getReceta();

				tablaAtencion.setModel(new DefaultTableModel(contenidoTabla,
						new String[] {
								"ATENCIÓN", "SÍNTOMAS", "DIAGNÓSTICO", "RECETA"
						}));

			}
		});

		JButton botonLimpiar = new JButton("Limpiar");
		botonLimpiar.setBounds(150, 150, 80, 20);
		contentPane.add(botonLimpiar);
		botonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				textoCliente.setText("");
				textoAtencion.setText("");
				limpiartablaCliente();
				limpiartablaAtencion();
				textoAtencion.setEditable(false);
				
			}
		});

		textoCliente = new JTextField();
		textoCliente.setBounds(204, 18, 209, 20);
		contentPane.add(textoCliente);
		textoCliente.setColumns(10);

		textoCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char valida = e.getKeyChar();
				if (Character.isLetter(valida)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Ingresar solo  números");
				}
				if (textoCliente.getText().length() > 8) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Ingrese solo 8 números");

				}
			}
		});
		JLabel labelAtencion = new JLabel("Ingrese número de atención:");
		labelAtencion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelAtencion.setBounds(33, 194, 177, 20);
		contentPane.add(labelAtencion);

		textoAtencion = new JTextField();
		textoAtencion.setColumns(10);
		textoAtencion.setBounds(218, 196, 209, 20);
		contentPane.add(textoAtencion);
		textoAtencion.setEditable(false);

		// Restricción solo numeros
		textoAtencion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char valida = e.getKeyChar();
				if ((valida >= 0 && valida <= 9)) {

				}
				if (Character.isLetter(valida)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Ingresar solo  números");
				}
				if (textoAtencion.getText().length() > 8) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Ingrese solo 8 números");

				}
			}
		});

		scrollPaneCliente = new JScrollPane();
		scrollPaneCliente.setBounds(33, 73, 616, 39);
		contentPane.add(scrollPaneCliente);

		tablaCliente = new JTable();
		scrollPaneCliente.setViewportView(tablaCliente);
		tablaCliente.setEnabled(false);
		tablaCliente.setModel(new DefaultTableModel(
				new Object[][] {
						{ null, null, null, null, null },
				},
				new String[] {
						"DNI", "F. NACIMIENTO", "NOMBRE", "APELLIDO", "ATENCIÓN"
				}));
		scrollPaneAtencion = new JScrollPane();
		scrollPaneAtencion.setBounds(33, 246, 616, 86);
		contentPane.add(scrollPaneAtencion);



		tablaAtencion = new JTable();
		scrollPaneAtencion.setViewportView(tablaAtencion);
		tablaAtencion.setEnabled(false);
		tablaAtencion.setModel(new DefaultTableModel(
				new Object[][] {
						{ null, null, null, null },
				},
				new String[] {
						"ATENCIÓN", "SÍNTOMAS", "DIAGNÓSTICO", "RECETA"
				}
			));
	}
	public Cliente busqueda(String dni, List<Cliente> listaClientes) {
		Cliente miCLiente = new Cliente();
		for (int i = 0; i < listaClientes.size(); i++) {
			if (listaClientes.get(i).obtener_dni().equals(dni)) {
				miCLiente = listaClientes.get(i);
			}
		}
		return miCLiente;
	}

	public Atencion busquedaAtencion(String atencion, List<Atencion> atenciones) {
		Atencion miAtencion = new Atencion();
		for (int i = 0; i < atenciones.size(); i++) {
			if (atenciones.get(i).getId().equals(atencion)) {
				miAtencion = atenciones.get(i);
			}
		}
		return miAtencion;
	}

	public void limpiartablaCliente() {
		DefaultTableModel temp = (DefaultTableModel) tablaCliente.getModel();
		int filas = tablaCliente.getRowCount();

		for (int a = 0; filas > a; a++) {
			temp.removeRow(0);
		}
	}

	public void limpiartablaAtencion() {
		DefaultTableModel temp = (DefaultTableModel) tablaAtencion.getModel();
		int filas = tablaAtencion.getRowCount();

		for (int a = 0; filas > a; a++) {
			temp.removeRow(0);
		}
	}

	       // guardando datos... *********************************************************************************************************
		   private void GenerarTXT(ActionEvent e) {

			try {
				try (BufferedWriter Mitxt = new BufferedWriter(new FileWriter("Historia Médica.txt"))) {
					Mitxt.write("\t\t\tHISTORIAL MÉDICO DEL CLIENTE");
					Mitxt.newLine();
					Mitxt.newLine();
					Mitxt.write("DATOS DEL CLIENTE");
					Mitxt.newLine();
					Mitxt.write("DNI\t\tF. NACIMIENTO\tNOMBRE\tAPELLIDO\tATENCIÓN");
					Mitxt.newLine();

					// guardando tablilla...
					for (int i = 0; i < tablaCliente.getRowCount(); i++) {

						Mitxt.write(tablaCliente.getValueAt(i, 0).toString() + "\t");
						Mitxt.write(tablaCliente.getValueAt(i, 1).toString() + "\t");
						Mitxt.write(tablaCliente.getValueAt(i, 2).toString() + "\t");
						Mitxt.write(tablaCliente.getValueAt(i, 3).toString() + "\t\t");
						Mitxt.write(tablaCliente.getValueAt(i, 4).toString() + "\t");
						Mitxt.newLine();

					}
					Mitxt.newLine();
					Mitxt.write("DATOS DE LA ATENCIÓN");
					Mitxt.newLine();
					Mitxt.write("ATENCIÓN\tSÍNTOMAS\t\tDIAGNÓSTICO\t\tRECETA");
					Mitxt.newLine();

					// guardando tablilla...
					for (int i = 0; i < tablaAtencion.getRowCount(); i++) {

						Mitxt.write(tablaAtencion.getValueAt(i, 0).toString() + "\t");
						Mitxt.write(tablaAtencion.getValueAt(i, 1).toString() + "\t");
						Mitxt.write(tablaAtencion.getValueAt(i, 2).toString() + "\t");
						Mitxt.write(tablaAtencion.getValueAt(i, 3).toString() + "\t");
						Mitxt.newLine();

					}
					Mitxt.newLine();
				}
				// mensaje mostrado tras haber guardado
				switch (JOptionPane.showConfirmDialog(null, "Desea mostrar .txt?", "Guardado!",
						JOptionPane.OK_CANCEL_OPTION)) {
	
					case 0:
						// función para mostrar datos
						abrirarchivo("Historia Médica.txt"); // se muestra el nombre del archivo con el cual será creado y se
													// guardará dentro de esta misma carpeta (no cambien esto prros 
													//atentamente Iber -_- )						
						break;
					case 2:
						dispose();
						break;
				}
	
			} catch (Exception x) {
				JOptionPane.showMessageDialog(null, "Error");
			}
	
		}


		public void abrirarchivo(String archivo) throws IOException {

			try {
	
				File objetofile = new File(archivo);
				Desktop.getDesktop().open(objetofile);
	
			} catch (InvalidPathException ex) {
	
				System.out.println(ex);
	
			}
	
		}

}
