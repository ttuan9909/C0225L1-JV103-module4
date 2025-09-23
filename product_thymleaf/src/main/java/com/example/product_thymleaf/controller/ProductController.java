package com.example.product_thymleaf.controller;

import com.example.product_thymleaf.entity.Product;
import com.example.product_thymleaf.service.IProductService;
import com.example.product_thymleaf.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService = new ProductService();

    @GetMapping
    public String list(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/list"; // Trỏ tới /WEB-INF/views/product/list.jsp
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            model.addAttribute("message", "Sản phẩm không tồn tại!");
            return "error";
        }
        model.addAttribute("product", product);
        return "product/detail"; // /WEB-INF/views/product/detail.jsp
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create"; // form.jsp
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") Product product, Model model) {
        boolean success = productService.add(product);
        if (!success) {
            model.addAttribute("message", "Không thể thêm sản phẩm (có thể tên trùng hoặc dữ liệu không hợp lệ)");
            return "product/create";
        }
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            model.addAttribute("message", "Sản phẩm không tồn tại!");
            return "error";
        }
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("product") Product product, Model model) {
        boolean success = productService.update(product);
        if (!success) {
            model.addAttribute("message", "Cập nhật thất bại (dữ liệu không hợp lệ hoặc tên trùng)");
            return "product/edit";
        }
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")  int id, Model model) {
        System.out.println(">>> Try delete id = " + id);
        productService.findAll().forEach(p -> System.out.println("Have: " + p.getId() + " - " + p.getName()));
        boolean success = productService.delete(id);
        System.out.println(">>> Success? " + success);
        if (!success) {
            model.addAttribute("message", "Xóa thất bại hoặc sản phẩm không tồn tại!");
            return "error";
        }
        return "redirect:/products";
    }
}
