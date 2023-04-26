package com.alwaha.cafe.rest;
import com.alwaha.cafe.models.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping("/bill")
public interface BillRest {
    @PostMapping("/report")
    ResponseEntity<String> generateReport(@RequestBody Map<String, Object> requestMap);
    @GetMapping(path = "/bills")
    ResponseEntity<List<Bill>> getBills();
    @PostMapping("/pdf")
    ResponseEntity<byte[]> getPdf(@RequestBody Map<String, Object> requestMap);
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBill(@PathVariable Integer id);
}