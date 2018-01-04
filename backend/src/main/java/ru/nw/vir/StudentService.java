package ru.nw.vir;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/rest/student")
class StudentService{

   @RequestMapping(value="/",method = RequestMethod.GET) //The HTTP request methods to map to, narrowing the primary mapping: GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE, TRACE.
   public HashMap<Long,Student> getAllStudents(){
      return Inventory4fanoApplication.hmStudent;
   }
}