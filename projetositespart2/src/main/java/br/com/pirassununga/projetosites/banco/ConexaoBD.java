package br.com.pirassununga.projetosites.banco;
/**
 *
 * @author Joseph
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;


public class ConexaoBD{
   
     
    public Statement stm;
    public ResultSet rs;
    private Connection conn;
    private static Properties prop = null;
    
     public void Conexao() throws ClassNotFoundException, IOException {
        try {

            prop = new Properties();            
            prop.load(getClass().getResourceAsStream("/connection.properties"));
            
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException  e) {
            System.err.println(e.getMessage());
        }
    }
    public void executaSQL(String sql) {
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no executa SQL"+ex.getMessage());
        }
    }
    
    public Connection getConnection() throws ClassNotFoundException, IOException {
        Conexao();
        return this.conn;
    }

      public void desconecta(){
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "falha ao desconectar");
        }
    }
}