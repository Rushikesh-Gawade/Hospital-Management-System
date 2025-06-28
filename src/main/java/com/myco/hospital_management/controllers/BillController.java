package com.myco.hospital_management.controllers;

// Spring Framework Core Annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

//Spring Data Support
import org.springframework.data.domain.Page;

// Models and Services
import com.myco.hospital_management.models.Bill;
import com.myco.hospital_management.service.BillService;

@RestController
@RequestMapping("/bills")
public class BillController {
  
    @Autowired
    private BillService billServiceobj;

     @GetMapping
     public Page<Bill> getAllBills(@RequestParam(defaultValue = "0") int page, 
                                   @RequestParam(defaultValue = "2") int size){
        System.out.println("Fetching all the bills");

        return  billServiceobj.getAllBills(page, size);
     }

     @PostMapping
     public Bill creatBill(@RequestBody Bill billobj){
         System.out.println("Creating new  bill");

         return  billServiceobj.createBill(billobj);
     }

     @GetMapping("/{id}")
     public Bill getBillById(@PathVariable Long id){
         System.out.println("Fetching the bill by id");

         return  billServiceobj.getBillById(id);
     }

     @PutMapping("/{id}")
     public void updateBill(@PathVariable Long id, @RequestBody Bill bill){
         System.out.println("Updating bill by id");
          billServiceobj.updateBill(id, bill);
     }

     @DeleteMapping("/{id}")
     public void deleteBill(@PathVariable Long id){
         System.out.println("Deleting bill by id");
            billServiceobj.deleteBill(id);
     }
}
