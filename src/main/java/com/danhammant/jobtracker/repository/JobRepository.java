package com.danhammant.jobtracker.repository;

import com.danhammant.jobtracker.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByLocationStartsWithIgnoreCase(String location);
    List<Job> findByLocationStartsWithIgnoreCaseAndAndStatus(String location, String status);
    List<Job> findAllByLocation(String title);

    List<Job> findByStatusIgnoreCase(String status);




}
