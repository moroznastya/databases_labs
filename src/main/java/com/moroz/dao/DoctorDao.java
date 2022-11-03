package com.moroz.dao;

import com.moroz.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public interface DoctorDao extends GeneralDao <Doctor, Integer> {
}
