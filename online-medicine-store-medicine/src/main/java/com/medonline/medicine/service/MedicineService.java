package com.medonline.medicine.service;

import com.medonline.medicine.Exception.MedOnlineMedicineException;
import com.medonline.medicine.dto.MedicineDTO;

import java.util.List;

public interface MedicineService {
    List<MedicineDTO> getAllMedicines(Integer pageNumber, Integer pageSize) throws MedOnlineMedicineException;
    List<MedicineDTO> getMedicinesByCategory(String category)throws MedOnlineMedicineException;
    MedicineDTO getMedicineById(Integer medicineId) throws MedOnlineMedicineException;
    void updateMedicineQuantityAfterOrder(Integer medicineId, Integer orderedQuantity) throws MedOnlineMedicineException;
}

