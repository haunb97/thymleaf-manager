package com.hau.Controller;

import com.hau.model.Customer;
import com.hau.service.CustomerService;
import com.hau.service.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("customers", customerService.findAll());
            return "index";
    }
    @GetMapping("/customer/create")
    public String create(Model model){
        model.addAttribute("customer", new Customer());
        return "create";
    }
    @PostMapping("/customer/save")
    public String save(Customer customer, RedirectAttributes redirect){
        customer.setId((int)(Math.random()*10000));
        customerService.save(customer );
        redirect.addFlashAttribute("success","saved customer successfully!");
        return "redirect:/";
    }
    @GetMapping("/customer/{id}/edit")
        public String edit(@PathVariable int id , Model model){
            model.addAttribute("customer", customerService.findById(id));
            return "edit";
    }
    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable int id , Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "delete";
    }
    @PostMapping("/customer/{id}/delete")
    public  String delete(Customer customer, RedirectAttributes redirect){
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "remove customer successfully");
        return "redirect:/";
    }
    @GetMapping("/customer/{id}/view")
    public String view(@PathVariable int id , Model model){
        model.addAttribute("customer", customerService.findById(id));
        return "view";
    }

}
