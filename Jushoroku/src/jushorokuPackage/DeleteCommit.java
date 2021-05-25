package jushorokuPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCommit
 */
@WebServlet("/DeleteCommit")
public class DeleteCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 変数の宣言
		 * 変数にrequestした値を入れる
		 */
		Connection connect = null;
		Statement stmt = null;

		String UpdQuery = "";
		String id = request.getParameter("id");;

		/*
		 * DBのdelete_flgを更新し、非表示にする。
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kensyudb?characterEncording=UTF-8&serverTimezone=JST", "Portal", "@k7EA2gUY");
			stmt = connect.createStatement();

			/*
			 * DBにリクエスト内容を登録
			 */
			UpdQuery = "UPDATE jyusyoroku"
					+ " SET delete_flg = 1"
					+ " WHERE id =" + Integer.parseInt(id);

			/*
			 * 内容を更新させる
			 */
			stmt.executeUpdate(UpdQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * connectを閉じる
		 */
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * ListBL.javaへの遷移
		 */
		getServletContext().getRequestDispatcher("/ListBL").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
