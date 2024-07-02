package com.medonline.medicine.repository;

import com.medonline.medicine.entity.Medicine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends PagingAndSortingRepository<Medicine, Integer> {
    public Optional<Medicine> findByMedicineId(Integer medicineId);
    public List<Medicine> findAllByCategory(String category);
}
