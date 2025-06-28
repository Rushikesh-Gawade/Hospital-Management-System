package com.myco.hospital_management.service;

// Spring Boot and Spring Data imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Model and Repository imports
import com.myco.hospital_management.models.Bill;
import com.myco.hospital_management.repository.BillRepository;

// Java utility
import java.util.Optional;

@Service
public class BillService {

  @Autowired
  private BillRepository billRepositoryobj;

     public Page<Bill>getAllBills(int page, int size){
        try{
            System.out.println("into service layer");
            Pageable pageable = PageRequest.of(page, size);
            return  billRepositoryobj.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
        }
    }


public Bill getBillById(Long id) {
    try{
         Optional<Bill> bill = billRepositoryobj.findById(id);
        return bill.orElse(null);
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}

    public Bill createBill(Bill bill) {
    try{
        billRepositoryobj.save(bill);
        return bill;
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}

public void deleteBill(Long id) {
    try{
       billRepositoryobj.deleteById(id);
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            
    }
}

public Bill updateBill(Long id,Bill updatebill) {
    try{
       Optional<Bill> existingBill = billRepositoryobj.findById(id); // Checks if patient exist
        if(existingBill.isPresent()){
            Bill b = existingBill.get();
            b.setAmount(updatebill.getAmount());
            b.setStatus(updatebill.getStatus());

            return billRepositoryobj.save(b);
        }else{
            System.out.println("No patient found");
            return null;
        }

    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}
}
