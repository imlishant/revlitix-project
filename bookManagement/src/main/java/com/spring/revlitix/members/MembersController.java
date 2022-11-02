package com.spring.revlitix.members;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/member")
@RestController
public class MembersController {
    @Autowired 
	MembersRepository membersRepository;
	
    /*
	@GetMapping("/viewall")
	public List<Members> getAllMembers(){
		return membersRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Members>>getMemberById(@PathVariable(value = "id") int memberId) {
		Optional<Members> member = membersRepository.findById(memberId);
		return ResponseEntity.ok().body(member);
	}
	
	@PostMapping
	public Members addMember(@RequestBody Members member) {
		return membersRepository.save(member);
	}

    */

    ///

    
    @PostMapping("/add")
    public ResponseEntity<Members> addNewMember(@RequestBody Members member) {
		try {
			Members _member = membersRepository.save(new Members(member.getMemberId(), member.getMemberName()));
			return new ResponseEntity<>(_member, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    //add by ID
    @PostMapping("/add/{id}/{name}")
    public ResponseEntity<Members> addMemberDetails(@PathVariable String id, @PathVariable String name) {

        try {
            Members member = new Members(id, name);
			Members _member = membersRepository.save(member);
			return new ResponseEntity<>(_member, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    //get all list
    @GetMapping("/viewall")
    public ResponseEntity<List<Members>> getMembers(@RequestParam(required = false) String title) {
		try {
			List<Members> membersList = new ArrayList<Members>();

			if (title == null)
            membersRepository.findAll().forEach(membersList::add);

			if (membersList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(membersList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    //update book by id

	@PutMapping("/update/{id}")
    public ResponseEntity<Members> updateMemberbyId(@PathVariable("id") int id, @RequestBody Members member) {
		Optional<Members> memberData = membersRepository.findById(id);

		if (memberData.isPresent()) {
			Members _member = memberData.get();
			_member.setMemberName(member.getMemberName());
			_member.setBookTaken(member.getBookTaken());
			return new ResponseEntity<>(membersRepository.save(_member), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
    //delete book by id
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteMemberById(@PathVariable("id") int id) {
		try {
			membersRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
