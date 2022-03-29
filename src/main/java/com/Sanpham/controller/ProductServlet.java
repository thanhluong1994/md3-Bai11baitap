package com.Sanpham.controller;

import com.Sanpham.model.Product;
import com.Sanpham.service.ProductService;
import com.Sanpham.service.ProductServiceLmPl;
import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService=new ProductServiceLmPl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                ShowCreateForm(request, response);
                break;
            case "edit":
                ShowEditForm(request,response);
                break;
            case "delete":
                ShowDeleteForm(request,response);
                break;
            case "view":
                viewProduct(request,response);
                break;
            case "search":
                showSearchForm(request,response);
            default:
                listProducts(request, response);
                break;
        }

    }

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/search.jsp");
        try {
            dispatcher.forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Product product=this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product==null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product",product);
            dispatcher=request.getRequestDispatcher("product/view.jsp");
        }try{
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ShowDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Product product=this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product==null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product",product);
            dispatcher=request.getRequestDispatcher("product/delete.jsp");
        }try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ShowEditForm(HttpServletRequest request, HttpServletResponse response) {
int id =Integer.parseInt(request.getParameter("id"));
Product product=this.productService.findById(id);
RequestDispatcher dispatcher;
if (product==null){
    dispatcher=request.getRequestDispatcher("error-404.jsp");
}else {
    request.setAttribute("product",product);
    dispatcher=request.getRequestDispatcher("product/edit.jsp");
}try {
    dispatcher.forward(request,response);
        }catch (ServletException e){
    e.printStackTrace();
        }catch (IOException e){
    e.printStackTrace();
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products=this.productService.finAll();
        request.setAttribute("products",products);
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "search":
                searchProduct(request,response);
                break;
            default:
                break;
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Product product=this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product==null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }else {
            this.productService.remove(id);
        }try {
            response.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        double price=Integer.parseInt(request.getParameter("price"));
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        Product product=productService.findById(id);
        RequestDispatcher dispatcher;
        if (product==null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }else {
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            this.productService.update(id,product);
            request.setAttribute("product",product);
            request.setAttribute("message","Product information was updated");
            dispatcher=request.getRequestDispatcher("product/edit.jsp");
        }try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ShowCreateForm(HttpServletRequest request,HttpServletResponse response){
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
        }
    }
    private void createProduct(HttpServletRequest request,HttpServletResponse response){
        String name=request.getParameter("name");
        double price=Integer.parseInt(request.getParameter("price"));
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        int id = (int) (Math.random() * 10000);

        Product product =new Product(id,name,price,quantity);
        this.productService.save(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("message", "New product was created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
