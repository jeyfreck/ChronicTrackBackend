package com.chronicktrack.chronictrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
List<Appointment> findByDoctorId(Long doctorId);
List<Appointment> findByPatientId(Long patientId);
}
