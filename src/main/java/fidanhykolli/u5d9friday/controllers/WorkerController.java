package fidanhykolli.u5d9friday.controllers;

import fidanhykolli.u5d9friday.entities.Worker;
import fidanhykolli.u5d9friday.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping ("/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;
    @GetMapping
    public List<Worker> getAllWorkers(){
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable("id") Long id) {
        Worker worker = workerService.getWorkerById(id);
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }

    @PostMapping
    public Worker createWorker(@RequestBody Worker body){
        return workerService.createWorker(body);
    }

    @PutMapping("/{id}")
    public Worker updateWorker(@PathVariable Long id, @RequestBody Worker body){
        return workerService.updateWorker(id, body);
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id){
        workerService.deleteWorker(id);
    }
}
