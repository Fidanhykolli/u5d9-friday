package fidanhykolli.u5d9friday.services;

import fidanhykolli.u5d9friday.entities.Worker;
import fidanhykolli.u5d9friday.exceptions.BadRequestException;
import fidanhykolli.u5d9friday.exceptions.NotFoundException;
import fidanhykolli.u5d9friday.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
    public Worker getWorkerById(Long workerId) {
        Optional<Worker> optionalWorker = workerRepository.findById(workerId);
        return optionalWorker.orElseThrow(() -> new NotFoundException("Worker not found"));
    }    public Worker createWorker(Worker worker) {
        if (worker.getName() == null || worker.getName().isEmpty()) {
            throw new BadRequestException("Il nome del dipendente è obbligatorio");
        }
        Random rndm = new Random();
        long randomId = rndm.nextInt(1000) + 1;


        worker.setId(randomId);
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Long id, Worker worker) {

        Optional<Worker> existingWorkerOptional = workerRepository.findById(id);
        if (!existingWorkerOptional.isPresent()) {
            throw new NotFoundException("Dipendente non trovato con ID: " + id);
        }

        Worker existingWorker = existingWorkerOptional.get();


        existingWorker.setName(worker.getName());
        existingWorker.setSurname(worker.getSurname());
        existingWorker.setEmail(worker.getEmail());

        return workerRepository.save(existingWorker);
    }

    public void deleteWorker(Long id) {

        if (!workerRepository.existsById(id)) {
            throw new NotFoundException("Dipendente non trovato con ID: " + id);
        }

        workerRepository.deleteById(id);
    }

    public void uploadProfileImage(Long id, MultipartFile file) throws IOException {

        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (!workerOptional.isPresent()) {
            throw new NotFoundException("Dipendente con ID: " + id+ " non trovato");
        }

        Worker worker = workerOptional.get();


        worker.setProfileImage(file.getOriginalFilename());
        workerRepository.save(worker);
    }
}