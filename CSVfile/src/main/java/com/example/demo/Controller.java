package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/csv")
public class Controller {

	@Autowired
	private HospitalRepository repository;
	
	@GetMapping("/csv")
	public List<Hospital> all(){
		return repository.findAll();	
	}
	
	@PostMapping("/csv")
	public Hospital create(@RequestBody Hospital hospital) {
		return repository.save(hospital);
	}
	
	@GetMapping("/csv/{id}")
	public ResponseEntity<Hospital> getById(@PathVariable Long id) throws ResourceNotFoundException{
		Hospital hospital = repository.findById(id)
				           .orElseThrow(() -> new ResourceNotFoundException("Exception occurs" +id));
		return ResponseEntity.ok().body(hospital);
	}
	
	@PutMapping("/csv/{id}")
	public ResponseEntity<Hospital> update(@PathVariable Long id , 
			@RequestBody Hospital hospital)throws ResourceNotFoundException{
		Hospital jpaentity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Exception occurs" +id));
		jpaentity.setC_batch_no(hospital.getC_batch_no());
		jpaentity.setC_manufacturer(hospital.getC_manufacturer());
		jpaentity.setC_name(hospital.getC_name());
		jpaentity.setC_packaging(hospital.getC_packaging());
		jpaentity.setC_schemes(hospital.getC_schemes());
		jpaentity.setC_unique_code(hospital.getC_unique_code());
		jpaentity.setD_expiry_date(hospital.getD_expiry_date());
		jpaentity.setHsn_code(hospital.getHsn_code());
		jpaentity.setN_balance_qty(hospital.getN_balance_qty());
		jpaentity.setN_mrp(hospital.getN_mrp());
		
		final Hospital kaka = repository.save(jpaentity);
		return ResponseEntity.ok(kaka);
	}
	
	@DeleteMapping("/cs/{id}")
	public Map<String,Boolean> delete(@PathVariable Long id)throws ResourceNotFoundException{
		Hospital kaka = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Exception delete" +id));
		
		repository.delete(kaka);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response ;
	}
}

