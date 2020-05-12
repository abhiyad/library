package com.springframework.controllers;


import com.springframework.domain.Book;
import com.springframework.domain.MyUser;
import com.springframework.repositories.MyUserRepository;
import com.springframework.services.BookService;
import com.springframework.services.MyUserService;
import com.springframework.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class ProductController {
    @Autowired
    MyUserRepository userRepository;
    private MyUserService userService;
    private BookService bookService;

    @Autowired
    public void setMyUserService(MyUserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setBookService(BookService bookService){ this.bookService = bookService;}

    @GetMapping("/signup")
    public String sendForm(MyUser person){
        return "signup";
    }

    @PostMapping("/signup")
    public String process_form(MyUser myUser){
        String name = myUser.getName();
        String username = myUser.getUsername();
        String password = myUser.getPassword();

        // Here check for username availability before making an object and saving in database
        userService.saveMyUser(name,username,password,"USER");
        System.out.println("INSERTED IN DATABASE ++++++++++ " + name + " " + username + " " + password);
        return "index";
    }

    @GetMapping("/login")
    public String sendForm_login(MyUser myUser){
        return "login";
    }

    @PostMapping("/login")
    public String process_form_login(MyUser myUser){
        System.out.println("IN POST LOGIN");
        String username = myUser.getUsername();
        String password = myUser.getPassword();
        System.out.println("YOU ENTERED :" + username + " " + password);
        return "login";
    }
    @RequestMapping("/homepage")
    public String home(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        System.out.println(" =================" + username);
        session.setAttribute("username",username);
        List<Book> bookList = bookService.listAll();
        model.addAttribute("books",bookList);
        return "homepage";
    }
    @RequestMapping("/")
    public String index() {
        userService.saveMyUser("Abhishek","abhiyad","123","USER");
        bookService.saveBook("A1",1,"B1");
        bookService.saveBook("A2",3,"B2");
        bookService.saveBook("A3",2,"B3");
        bookService.saveBook("A4",5,"B4");
        bookService.saveBook("A5",3,"B5");
        Book a = bookService.findByBookName("A2");
        System.out.println("copies ===== " + a.getCopies());
        System.out.println(bookService.listAll().toString());
        return "index";
    }

    @PostMapping("/issue/{name}")
    public String issue(@PathVariable String name){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        MyUser user = userService.loadUserByUsername(username);
        Book book1 =  bookService.findByBookName("ABHI");
        if (book1==null)System.out.println("WILL WORK THIS LOGI");
        Book book = bookService.findByBookName(name);
        if (book!=null && user.getIssued_book()==null && book.getCopies()>0) {
            userService.issue(username, name);
            bookService.issue(name);
        }
        else {
            System.out.println("ERROR ::::: 1) alread has a book 2) Book not available");
            return "redirect:/homepage?error";
        }
        return "redirect:/homepage";
    }
    @GetMapping("/return_book")
    public String return_book(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        MyUser user = userService.loadUserByUsername(username);
        String book_name = user.getIssued_book();
        userService.return_book(username);
        bookService.return_book(book_name);
        return  "redirect:/homepage";
    }

}
