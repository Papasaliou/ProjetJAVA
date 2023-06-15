package PSL.PACK;

//import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;


@WebServlet("/PersonnelServ")
public class PersonnelServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Connection conn;
	public Statement stm;
       
    
    public PersonnelServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String prenom=request.getParameter("prenom");
		String nom=request.getParameter("nom");
		String sexe="M";
		String addresse=request.getParameter("adress");
		String pays=request.getParameter("pays");
		try {
			//1)charger la classe de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2)creer un objet de connection
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/testjava", "root", "");
			//3) creer un objet de statement
			 stm= conn.createStatement();
			//4) execution de la requete
			String sql="insert into personne (Prenom,Nom,Sexe,Adress,Pays)"
					+ "values('"+prenom+"','"+nom+"','"+sexe+"','"+addresse+"','"+pays+"')";
			 stm.executeUpdate(sql);
			//5) fermeture de la connection
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		PrintWriter out =response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Je me nommes :"+prenom+" "+nom+"</h1>");
		out.println("<h2>Je suis de sexe:"+sexe+"</h2>");
		out.println("<h2>Mon pays s'appelle:"+pays+"</h2>");
		out.println("<h3>Mo Addresse:"+addresse+"</h3>");
		out.println("<h2>La liste des Personnes</h2>");
		out.println("<table>"
				+ "  <tr>"
				+ "    <th>IdPers</th>"
				+ "    <th>Prenom</th>"
				+ "    <th>nom</th>"
				+ "    <th>Sexe</th>"
				+ "    <th>Adresse</th>"
				+ "    <th>pays</th>"
				+ "  </tr>");
		
		try {
			String Sql1="select * from personne";
		ResultSet res =stm.executeQuery(Sql1);
		while(res.next()) {
		out.println("<tr>");
		out.println("<td>"+res.getInt("Malte")+"</td>");
		out.println("<td>"+res.getString("Prenom")+"</td>");
		out.println("<td>"+res.getString("Nom")+"</td>");
		out.println("<td>"+res.getString("Sexe")+"</td>");
		out.println("<td>"+res.getString("Adress")+"</td>");
		out.println("<td>"+res.getString("Pays")+"</td>");
		out.println("</tr>");
		}
		out.println( "</table>");
		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</body>");
		out.println("</html>");
		
	}

}
