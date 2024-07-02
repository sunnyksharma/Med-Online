package com.medonline.medicine.service;

import com.medonline.medicine.Exception.MedOnlineMedicineException;
import com.medonline.medicine.dto.MedicineDTO;
import com.medonline.medicine.entity.Medicine;
import com.medonline.medicine.repository.MedicineRepository;
import com.medonline.medicine.utils.MapperUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class MedicineServiceImpl implements MedicineService{

    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public List<MedicineDTO> getAllMedicines(Integer pageNumber, Integer pageSize) throws MedOnlineMedicineException {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return medicineRepository.findAll(pageable).stream().map(MapperUtils::toDTO).toList();
//        return List.of();
    }

    @Override
    public List<MedicineDTO> getMedicinesByCategory(String category) throws MedOnlineMedicineException {
        return medicineRepository.findAllByCategory(category).stream().map(MapperUtils::toDTO).toList();
    }

    @Override
    public MedicineDTO getMedicineById(Integer medicineId) throws MedOnlineMedicineException {

        return MapperUtils.toDTO(medicineRepository.findByMedicineId(medicineId).orElseThrow());
    }

    @Override
    public void updateMedicineQuantityAfterOrder(Integer medicineId, Integer orderedQuantity) throws MedOnlineMedicineException {
        Medicine medicine = medicineRepository.findByMedicineId(medicineId).orElseThrow() ;
        
        medicine.setQuantity(medicine.getQuantity()-orderedQuantity);
    }
}
