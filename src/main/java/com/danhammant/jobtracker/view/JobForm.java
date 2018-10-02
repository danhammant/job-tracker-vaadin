package com.danhammant.jobtracker.view;

import com.danhammant.jobtracker.model.Job;
import com.danhammant.jobtracker.repository.JobRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class JobForm extends FormLayout {
    private JobTrackerUI jobTrackerUI;
    private JobRepository repository;

    private Job job;

    private TextField title = new TextField("Job Title");
    private TextField company = new TextField("Company");
    private TextField location = new TextField("Location");
    private TextField url = new TextField("URL");
    private ComboBox<String> status = new ComboBox<>("Status");

    private Binder<Job> binder = new Binder<>(Job.class);
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button clearSelection = new Button("Clear Selection");

    public JobForm(JobTrackerUI jobTrackerUI, JobRepository repository) {
        this.jobTrackerUI = jobTrackerUI;
        this.repository = repository;

        title.addFocusListener(e -> {
            if (title.isEmpty()) {
                setJob(new Job());
            }
        });

        HorizontalLayout buttons = new HorizontalLayout(save, delete, clearSelection);
        add(title, company, location, url, status, buttons);
        save.getElement().setAttribute("theme", "primary");
        status.setItems("APPLIED", "NOT APPLIED");

        binder.bindInstanceFields(this);
        setJob(null);
        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
        clearSelection.addClickListener(e -> {
            jobTrackerUI.clearSelection();
            setJob(null);
        });
    }

    public void setJob(Job job) {
        this.job = job;
        binder.setBean(job);

        boolean enabled = job != null;
        save.setEnabled(enabled);
        delete.setEnabled(enabled);
        clearSelection.setEnabled(enabled);
        if (enabled) {
            title.focus();
        }
    }

    private void save() {
        List<Job> jobs = repository.findAllByLocation(job.getLocation());

        if (!jobs.isEmpty() && jobs.contains(job) && job.getId() == null) { //Checking for duplicates
            new SaveErrorDialog().open();
        } else if (hasNullField()){
            new SaveErrorDialog().open();
        } else {
            repository.save(job);
            jobTrackerUI.updateList();
            setJob(null);
        }
    }

    private void delete() {
        if (job.getId() == null) {
            new NonExistantJobDialog().open();
        } else {
            repository.deleteById(job.getId());
            jobTrackerUI.updateList();
            setJob(null);
        }

    }

    private boolean hasNullField() {
        return job.getTitle() == null || job.getCompany() == null || job.getLocation() == null
                || job.getUrl() == null || job.getStatus() == null;
    }

}
