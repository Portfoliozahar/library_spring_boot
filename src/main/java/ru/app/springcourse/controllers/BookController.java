package ru.app.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.app.springcourse.models.Book;
import ru.app.springcourse.services.BookService;
import ru.app.springcourse.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService,
                          PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int booksPerPage,
            @RequestParam(defaultValue = "false") boolean sortByYear,
            Model model) {

        Page<Book> books =
                bookService.findAll(page, booksPerPage, sortByYear);

        model.addAttribute("books", books);

        return "books/index";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "books/search";
    }

    @PostMapping("/search")
    public String search(
            @RequestParam("title") String title,
            Model model) {

        model.addAttribute("books", bookService.search(title));

        return "books/search";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Book book = bookService.findOne(id);

        model.addAttribute("book", book);

        if (book.getOwner() != null) {
            model.addAttribute("owner", book.getOwner());
        } else {
            model.addAttribute("people", peopleService.findAll());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable int id) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable int id,
                         @RequestParam("personId") int personId) {
        bookService.assign(id, personId);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable int id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }
}