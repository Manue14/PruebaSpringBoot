package demoapp.controller;

import demoapp.model.entities.Customer;
import demoapp.model.entities.Movie;
import demoapp.service.CustomerService;
import demoapp.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    private CustomerService customerService;
    private MovieService movieService;

    @Autowired
    public CustomerController(CustomerService customerService, MovieService movieService) {
        this.customerService = customerService;
        this.movieService = movieService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customerList";
    }

    @GetMapping("/listOne/{id}")
    public String showOne(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("arrival", movieService.findById(1L));
        model.addAttribute("eoe", movieService.findById(2L));
        model.addAttribute("lebowski", movieService.findById(3L));
        return "customerView";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    @PostMapping("/add/submit")
    public String submit(@ModelAttribute("customer") @Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/add";
        }
        Customer savedCustomer = customerService.save(customer);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        customerService.deleteById(id);
        return "redirect:/list";
    }

    @GetMapping("/listMovies")
    public String listMovies(Model model) {
        model.addAttribute("arrival", movieService.findById(1L));
        model.addAttribute("eoe", movieService.findById(2L));
        model.addAttribute("lebowski", movieService.findById(3L));
        return "movieList";
    }

    @PostMapping("/like/{id}")
    public String like(@PathVariable long id) {
        Movie movie = movieService.findById(id);
        movie.setCounter(movie.getCounter() + 1);
        movieService.save(movie);
        return "redirect:/listMovies";
    }
}
