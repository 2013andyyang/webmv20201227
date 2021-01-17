
import java.io.IOException;
import java.util.*;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;

/**
 * Servlet implementation class DeleteStudentServlet
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentServlet() {
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
		Query q = em.createQuery("DELETE FROM Student WHERE sid=:sid");
		q.setParameter("sid", Integer.parseInt(request.getParameter("sid")));
		int r = q.executeUpdate();
		if (r > 0)
			System.out.println("Delete Successfully");
		else
			System.out.println("Delete Failed");
		Query q2 = em.createQuery("Select s from Student s ");
		@SuppressWarnings("unchecked")
		List<Student> li = (List<Student>) q2.getResultList();
		em.getTransaction().commit();
		em.close();
		emf.close();
		request.setAttribute("students", li);
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
