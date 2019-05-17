package tablapersonalizada;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

/**
 *
 * @author eri
 */
public class TablaPersonalizada implements ActionListener{
    private JFrame ventana;
    private JTable tabla;
    private DefaultTableModel model;
    private JTextField cajaNombre;
    private JTextField cajaPrecio;
    private JTextField cajaId;
    private JLabel linea;
    private JLabel mensaje;
    private JLabel linea2;
    private JLabel nombre;
    private JLabel precio;
    private JLabel identificador;
    private JLabel nota;
    private JButton botonAgregar;
    private JButton botonEliminar;
    private int id, vuelta = 0;
    
    
    public TablaPersonalizada(){
        id = 1407;
        String data[][] = {};
        
        String label[] = {"ID", "NOMBRE", "PRECIO"};
        
        model = new DefaultTableModel(data,label);
        tabla = new JTable(model);
        
        tabla.setCellSelectionEnabled(true);
        tabla.setGridColor(Color.red);
        
        //Para editar información
        ListSelectionModel select = tabla.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        select.addListSelectionListener((ListSelectionEvent e) -> {
            String Data = null;
            
            int [] row = tabla.getSelectedRows();
            int [] columns = tabla.getSelectedColumns();
            
            for (int i = 0; i < row.length; i++){
                for (int j = 0; j< columns.length; j++){
                    Data = (String) tabla.getValueAt(row[i], columns[j]);
                }
            }
        } //Permite dar seleccion a las celdas deseadas
        );
        
        tabla.setRowHeight(20);
        JScrollPane sp = new JScrollPane(tabla);
        sp.setBounds(90, 20, 500, 400);
        ventana = new JFrame("TABLES");
        crearComponentes();
        ventana.add(sp);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 550);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
    }
    
    public void crearComponentes(){
        linea = new JLabel();
        linea.setText("---------------------------------------------------------------------------------------------------------------------------");
        linea.setBounds(0, 298, 500, 30);
        ventana.add(linea);
        
        mensaje = new JLabel();
        mensaje.setText("***NOTA: Si desea editar alguna celda, dé doble click en ella.***");
        mensaje.setForeground(Color.red);
        mensaje.setBounds(10, 310, 500, 30);
        ventana.add(mensaje);
        
        linea2 = new JLabel();
        linea2.setText("---------------------------------------------------------------------------------------------------------------------------");
        linea2.setBounds(0, 320, 500, 30);
        ventana.add(linea2);
        
        nombre = new JLabel();
        nombre.setText("NOMBRE");
        nombre.setBounds(20, 350, 50, 30);
        ventana.add(nombre);
        
        cajaNombre = new JTextField();
        cajaNombre.setBounds(90, 350, 140, 30);
        ventana.add(cajaNombre);
        
        precio = new JLabel();
        precio.setText("PRECIO");
        precio.setBounds(20, 390, 50, 30);
        ventana.add(precio);
        
        cajaPrecio = new JTextField();
        cajaPrecio.setBounds(90, 390, 140, 30);
        ventana.add(cajaPrecio);
        
        botonAgregar = new JButton();
        botonAgregar.setText("AGREGAR");
        botonAgregar.setBounds(60, 430, 120, 30);
        botonAgregar.setForeground(Color.black);
        botonAgregar.addActionListener(this);
        ventana.add(botonAgregar);
        
        nota = new JLabel();
        nota.setText("Seleccione una fila en la tabla");
        nota.setBounds(285, 340, 300, 30);
        ventana.add(nota);
        
        identificador = new JLabel();
        identificador.setText("ID");
        identificador.setBounds(285, 370, 50, 30);
        ventana.add(identificador);
        
        cajaId = new JTextField();
        cajaId.setBounds(310, 370, 140, 30);
        cajaId.setEditable(false);
        ventana.add(cajaId);
        
        botonEliminar = new JButton();
        botonEliminar.setText("ELIMINAR");
        botonEliminar.setBounds(310, 410, 110, 30);
        botonEliminar.addActionListener(this);
        botonEliminar.setEnabled(false);
        ventana.add(botonEliminar);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        try{
            id++;
            switch(e.getActionCommand()) {
                case "ELIMINAR":
                    int row = tabla.getSelectedRow();
                    if(model.getRowCount() >0){
                        model.removeRow(row);
                        vuelta--;
                    }
                    if(vuelta == 0){
                        botonEliminar.setEnabled(false);
                    }
                    
                break;
                case "AGREGAR":
                    addRow(id, cajaNombre.getText(), cajaPrecio.getText());
                    botonEliminar.setEnabled(true);
                    vuelta++;
                break;
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(ventana, "ERROR");
        }
    }
    
    public void addRow(int id, String nombre, String precio){
        //Agregar fila
        model.addRow(new Object[]{id, nombre, precio});
    }
    
    /*public void addRowSpecific(int id, String nombre, String precio){
        //Agregar a una posición en específico
        model.insertRow(3, new Object[] {id, nombre, precio});
    }*/
    
    public static void main(String[] args) {
        TablaPersonalizada table = new TablaPersonalizada();
    }
    
}
