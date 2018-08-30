/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swi.ng2;

import java.sql.*;

public class Conexion {

    private static Connection cnx = null;

    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost/Chat", "root", "contraseña");
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return cnx;
    }

    public boolean logUser(Connection conection, String user) throws SQLException {
        PreparedStatement consulta;
        try {
            consulta = conection.prepareStatement("select user from Login where user='" + user + "'");
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next() && resultado.getString("user").equals(user)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public boolean registro(Connection conexion, String usuario, String contraseña) throws SQLException {
        PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("select user from Login where user='" + usuario + "'");
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next() && resultado.getString("user").equals(usuario)) {
                return false;
            } else {
                consulta = conexion.prepareStatement("insert into Login (user,pass) values (" + "'" + usuario + "'" + "," + "'" + contraseña + "'" + ")");
                consulta.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public boolean logPass(Connection conection, String pass) throws SQLException {
        PreparedStatement consulta;
        try {
            consulta = conection.prepareStatement("select pass from Login where pass='" + pass + "'");
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next() && resultado.getString("pass").equals(pass)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }

    public void setMensajes(Connection conexion, String remitente, String mensaje) throws SQLException {
        PreparedStatement consulta;
        consulta = conexion.prepareStatement("insert into chat (remitente,mensaje) values('" + remitente + "'" + ",'" + mensaje + "')");
        consulta.execute();
    }

    public String getMensajes(Connection conexion) throws SQLException, ClassNotFoundException {
        PreparedStatement consulta;
        consulta = conexion.prepareStatement("select remitente,mensaje from chat");
        ResultSet resultado = consulta.executeQuery();
        String texto = null;
        try {
            while (resultado.next()) {
                if (texto == null) {
                    texto = resultado.getString("remitente") + ":" + resultado.getString("mensaje") + "\n";
                } else {
                    texto += resultado.getString("remitente") + ":" + resultado.getString("mensaje") + "\n";
                }
            }
            return texto;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private PreparedStatement prepareStatement(String insert_into_Login_userpass_values_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
