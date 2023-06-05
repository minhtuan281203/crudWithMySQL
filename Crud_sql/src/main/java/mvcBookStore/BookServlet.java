package mvcBookStore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
@MultipartConfig
public class BookServlet extends HttpServlet {
    private List<Book> bookList;
    @Override
    public void init() throws ServletException {
        super.init();
        bookList = new ArrayList<>();
        bookList.add(new Book(1, "Dế mèn phiêu lưu kí", "Tô Hoài",48000, 20, "images/a.jpg" ));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "create":
                createBook(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateBook(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            case "search":
                String searchTerm = request.getParameter("searchTerm");
                List<Book> searchResults = searchBooksByName(searchTerm);
                request.setAttribute("bookList", searchResults);
                RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookList", bookList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }
    private void createBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameBook = request.getParameter("nameBook");
        String nameAuthor = request.getParameter("nameAuthor");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int id = bookList.size() + 1;

        Part filePart = request.getPart("image");
        String fileName = getFileName(filePart);
        String uploadDirectory = getServletContext().getRealPath("/images");
        String filePath = uploadFile(filePart, fileName, uploadDirectory);
        String fileURL = "images/" + fileName;

        Book newBook = new Book(id, nameBook, nameAuthor, price, quantity, fileURL);
        bookList.add(newBook);

        response.sendRedirect("books");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = getBookById(id);

        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }
    private List<Book> searchBooksByName(String searchTerm) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getNameBook().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

    private void deleteImage(String imageUrl, HttpServletRequest request) {
        String uploadDirectory = request.getServletContext().getRealPath("") + File.separator + "images";
        String imagePath = uploadDirectory + File.separator + imageUrl;

        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            imageFile.delete();
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nameBook = request.getParameter("nameBook");
        String nameAuthor = request.getParameter("nameAuthor");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));


        Book book = getBookById(id);
        book.setNameBook(nameBook);
        book.setNameAuthor(nameAuthor);
        book.setPrice(price);
        book.setQuantity(quantity);

        Part filePart = request.getPart("image");
        if(filePart != null && filePart.getSize() > 0) {
            deleteImage(book.getImageUrl(), request);

            String fileName = getFileName(filePart);
            String uploadDirectory = getServletContext().getRealPath("/images");
            String filePath = uploadFile(filePart, fileName, uploadDirectory);
            String fileURL = "images/" + fileName;
        }

        response.sendRedirect("books");
    }

    private String uploadFile(Part filePart, String fileName, String uploadDirectory) throws  IOException {
        String filePath = uploadDirectory + File.separator + fileName;

        try (InputStream inputStream = filePart.getInputStream()) {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            byte[] buffer = new byte[8192];
            int bytesRead;

            while((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return filePath;
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");

        for (String element : elements) {
            if(element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"","");
            }
        }
        return "";
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = getBookById(id);

        if (book != null) {
            deleteImage(book.getImageUrl(), request);
            bookList.remove(book);
        }
        response.sendRedirect("books");
    }

    private Book getBookById(int id) {
        for (Book book : bookList) {
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }



}
