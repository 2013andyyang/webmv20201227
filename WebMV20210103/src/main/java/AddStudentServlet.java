
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
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
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
		Student s1 = new Student();
		s1.setSid(Integer.parseInt(request.getParameter("sid")));
		s1.setSname(request.getParameter("sname"));
		s1.setAge(Integer.parseInt(request.getParameter("age")));
		em.persist(s1);
		Query q = em.createQuery("Select s from Student s ");
		@SuppressWarnings("unchecked")
		List<Student> li = (List<Student>) q.getResultList();
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
