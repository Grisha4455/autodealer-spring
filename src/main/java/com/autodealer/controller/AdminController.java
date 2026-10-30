package com.autodealer.controller;

import com.autodealer.entity.*;
import com.autodealer.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CarModelService carModelService;
    private final SupplierService supplierService;
    private final PriceListService priceListService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final EmployeeService employeeService;
    private final CustomerRequestService customerRequestService;
    private final TestDriveService testDriveService;
    private final ServiceAppointmentService serviceAppointmentService;
    private final CreditOfferService creditOfferService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("username", userDetails != null ? userDetails.getUsername() : "Guest");
        model.addAttribute("totalCars", carModelService.findAll().size());
        model.addAttribute("totalCustomers", customerService.findAll().size());
        model.addAttribute("totalSales", saleService.findAll().size());
        model.addAttribute("newRequests", customerRequestService.findByStatus("NEW").size());
        return "admin/dashboard";
    }

    // Модели автомобилей
    @GetMapping("/cars")
    public String cars(Model model) {
        model.addAttribute("cars", carModelService.findAll());
        return "admin/cars/list";
    }

    @GetMapping("/cars/new")
    public String newCar(Model model) {
        model.addAttribute("car", new CarModel());
        model.addAttribute("suppliers", supplierService.findAll());
        return "admin/cars/form";
    }

    @GetMapping("/cars/{id}/edit")
    public String editCar(@PathVariable Long id, Model model) {
        model.addAttribute("car", carModelService.findById(id));
        model.addAttribute("suppliers", supplierService.findAll());
        return "admin/cars/form";
    }

    @PostMapping("/cars")
    public String saveCar(@ModelAttribute CarModel car) {
        carModelService.save(car);
        return "redirect:/admin/cars";
    }

    @PostMapping("/cars/{id}/update")
    public String updateCar(@PathVariable Long id, @ModelAttribute CarModel car) {
        carModelService.update(id, car);
        return "redirect:/admin/cars";
    }

    @PostMapping("/cars/{id}/delete")
    public String deleteCar(@PathVariable Long id) {
        carModelService.delete(id);
        return "redirect:/admin/cars";
    }

    // Поставщики
    @GetMapping("/suppliers")
    public String suppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        return "admin/suppliers/list";
    }

    @GetMapping("/suppliers/new")
    public String newSupplier(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "admin/suppliers/form";
    }

    @GetMapping("/suppliers/{id}/edit")
    public String editSupplier(@PathVariable Long id, Model model) {
        model.addAttribute("supplier", supplierService.findById(id));
        return "admin/suppliers/form";
    }

    @PostMapping("/suppliers")
    public String saveSupplier(@ModelAttribute Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/admin/suppliers";
    }

    @PostMapping("/suppliers/{id}/update")
    public String updateSupplier(@PathVariable Long id, @ModelAttribute Supplier supplier) {
        supplierService.update(id, supplier);
        return "redirect:/admin/suppliers";
    }

    @PostMapping("/suppliers/{id}/delete")
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.delete(id);
        return "redirect:/admin/suppliers";
    }

    // Прейскурант
    @GetMapping("/prices")
    public String prices(Model model) {
        model.addAttribute("prices", priceListService.findAll());
        return "admin/prices/list";
    }

    @GetMapping("/prices/new")
    public String newPrice(Model model) {
        model.addAttribute("price", new PriceList());
        model.addAttribute("cars", carModelService.findAll());
        return "admin/prices/form";
    }

    @GetMapping("/prices/{id}/edit")
    public String editPrice(@PathVariable Long id, Model model) {
        model.addAttribute("price", priceListService.findById(id));
        model.addAttribute("cars", carModelService.findAll());
        return "admin/prices/form";
    }

    @PostMapping("/prices")
    public String savePrice(@ModelAttribute PriceList price) {
        priceListService.save(price);
        return "redirect:/admin/prices";
    }

    @PostMapping("/prices/{id}/update")
    public String updatePrice(@PathVariable Long id, @ModelAttribute PriceList price) {
        priceListService.update(id, price);
        return "redirect:/admin/prices";
    }

    @PostMapping("/prices/{id}/delete")
    public String deletePrice(@PathVariable Long id) {
        priceListService.delete(id);
        return "redirect:/admin/prices";
    }

    // Клиенты
    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "admin/customers/list";
    }

    @GetMapping("/customers/{id}")
    public String customerDetails(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        List<Sale> sales = saleService.findByCustomerId(id);
        model.addAttribute("customer", customer);
        model.addAttribute("sales", sales);
        return "admin/customers/details";
    }

    @GetMapping("/customers/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customers/form";
    }

    @PostMapping("/customers")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/admin/customers";
    }

    // Продажи
    @GetMapping("/sales")
    public String sales(Model model) {
        model.addAttribute("sales", saleService.findAll());
        return "admin/sales/list";
    }

    @GetMapping("/sales/new")
    public String newSale(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("cars", carModelService.findAll());
        model.addAttribute("employees", employeeService.findActive());
        model.addAttribute("prices", priceListService.findAll());
        return "admin/sales/form";
    }

    @PostMapping("/sales")
    public String saveSale(@ModelAttribute Sale sale) {
        saleService.save(sale);
        return "redirect:/admin/sales";
    }

    @GetMapping("/sales/{id}")
    public String saleDetails(@PathVariable Long id, Model model) {
        model.addAttribute("sale", saleService.findById(id));
        return "admin/sales/details";
    }

    // Сотрудники
    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "admin/employees/list";
    }

    @GetMapping("/employees/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "admin/employees/form";
    }

    @GetMapping("/employees/{id}/edit")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "admin/employees/form";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/admin/employees";
    }

    @PostMapping("/employees/{id}/update")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        employeeService.update(id, employee);
        return "redirect:/admin/employees";
    }

    // Обращения
    @GetMapping("/requests")
    public String requests(Model model) {
        model.addAttribute("requests", customerRequestService.findAll());
        return "admin/requests/list";
    }

    @GetMapping("/requests/{id}")
    public String requestDetails(@PathVariable Long id, Model model,
            @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("request", customerRequestService.findById(id));
        model.addAttribute("currentUser", userDetails != null ? userDetails.getUsername() : "Guest");
        return "admin/requests/details";
    }

    @PostMapping("/requests/{id}/update")
    public String updateRequest(@PathVariable Long id, @ModelAttribute CustomerRequest request) {
        customerRequestService.update(id, request);
        return "redirect:/admin/requests";
    }

    // Аналитика
    @GetMapping("/analytics")
    public String analytics(Model model) {
        model.addAttribute("popularModels", saleService.getPopularModels());
        model.addAttribute("salesByEmployee", saleService.getSalesByEmployee());
        return "admin/analytics";
    }

    // Test Drives
    @GetMapping("/test-drives")
    public String testDrives(Model model) {
        model.addAttribute("testDrives", testDriveService.findAll());
        return "admin/test-drives/list";
    }

    @PostMapping("/test-drives/{id}/status")
    public String updateTestDriveStatus(@PathVariable Long id, @RequestParam String status) {
        testDriveService.updateStatus(id, status);
        return "redirect:/admin/test-drives";
    }

    @PostMapping("/test-drives/{id}/delete")
    public String deleteTestDrive(@PathVariable Long id) {
        testDriveService.delete(id);
        return "redirect:/admin/test-drives";
    }

    // Service Appointments
    @GetMapping("/service-appointments")
    public String serviceAppointments(Model model) {
        model.addAttribute("appointments", serviceAppointmentService.findAll());
        return "admin/service-appointments/list";
    }

    @PostMapping("/service-appointments/{id}/status")
    public String updateServiceStatus(@PathVariable Long id, @RequestParam String status) {
        serviceAppointmentService.updateStatus(id, status);
        return "redirect:/admin/service-appointments";
    }

    @PostMapping("/service-appointments/{id}/delete")
    public String deleteServiceAppointment(@PathVariable Long id) {
        serviceAppointmentService.delete(id);
        return "redirect:/admin/service-appointments";
    }

    // Credit Offers
    @GetMapping("/credit-offers")
    public String creditOffers(Model model) {
        model.addAttribute("offers", creditOfferService.findAll());
        return "admin/credit-offers/list";
    }

    @GetMapping("/credit-offers/new")
    public String newCreditOffer(Model model) {
        model.addAttribute("offer", new CreditOffer());
        return "admin/credit-offers/form";
    }

    @GetMapping("/credit-offers/{id}/edit")
    public String editCreditOffer(@PathVariable Long id, Model model) {
        model.addAttribute("offer", creditOfferService.findById(id));
        return "admin/credit-offers/form";
    }

    @PostMapping("/credit-offers")
    public String saveCreditOffer(@ModelAttribute CreditOffer offer) {
        creditOfferService.save(offer);
        return "redirect:/admin/credit-offers";
    }

    @PostMapping("/credit-offers/{id}/update")
    public String updateCreditOffer(@PathVariable Long id, @ModelAttribute CreditOffer offer) {
        offer.setId(id);
        creditOfferService.save(offer);
        return "redirect:/admin/credit-offers";
    }

    @PostMapping("/credit-offers/{id}/delete")
    public String deleteCreditOffer(@PathVariable Long id) {
        creditOfferService.delete(id);
        return "redirect:/admin/credit-offers";
    }
}
