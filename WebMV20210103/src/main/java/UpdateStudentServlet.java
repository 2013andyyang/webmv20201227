
import java.io.IOException;
import java.util.*;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Student;
/**
 * Servlet implementation class UpdateStudentServlet
 */
@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebMV20210103");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("UPDATE Student SET age=:age WHERE	sid=:sid");
		q.setParameter("sid", Integer.parseInt(request.getParameter("sid")));
		q.setParameter("age", Integer.parseInt(request.getParameter("age")));
		int r = q.executeUpdate();
		if (r > 0)
			System.out.println("Update Successfully");
		else
			System.out.println("Update Failed");
		Query q2 = em.createQuery("Select s from Student s ");
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) q2.getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		request.setAttribute("students", list);
		request.getRequestDispatcher("ViewStudent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
