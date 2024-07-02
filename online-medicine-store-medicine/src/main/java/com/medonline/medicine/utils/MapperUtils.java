package com.medonline.medicine.utils;

import com.medonline.medicine.dto.MedicineDTO;
import com.medonline.medicine.entity.Medicine;

public class MapperUtils {

    public static Medicine toEntity(MedicineDTO source){
        Medicine target = new Medicine();
        target.setQuantity(source.getQuantity());
        target.setCategory(source.getCategory());
        target.setManufacturer(source.getManufacturer());
        target.setPrice(source.getPrice());
        target.setExpiryDate(source.getExpiryDate());
        return target;
    }

    public static MedicineDTO toDTO(Medicine source){
        MedicineDTO target = new MedicineDTO();
        target.setQuantity(source.getQuantity());
        target.setCategory(source.getCategory());
        target.setManufacturer(source.getManufacturer());
        target.setPrice(source.getPrice());
        target.setExpiryDate(source.getExpiryDate());
        return target;
    }
}
